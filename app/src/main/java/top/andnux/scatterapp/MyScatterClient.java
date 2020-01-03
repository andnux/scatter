package top.andnux.scatterapp;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.paytomat.core.util.HashUtil;
import com.paytomat.eos.Eos;
import com.paytomat.eos.PrivateKey;
import com.paytomat.eos.signature.Signature;

import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import one.block.eosiojava.interfaces.ISerializationProvider;
import one.block.eosiojava.models.AbiEosSerializationObject;
import one.block.eosiojavaabieosserializationprovider.AbiEosSerializationProviderImpl;
import one.block.eosiojavarpcprovider.implementations.EosioJavaRpcProviderImpl;
import top.andnux.scatter.ScatterClient;
import top.andnux.scatter.models.AbiResponse;
import top.andnux.scatter.models.TransactionBean;
import top.andnux.scatter.models.requests.appinfo.AppInfoResponseData;
import top.andnux.scatter.models.requests.authenticate.AuthenticateRequestParams;
import top.andnux.scatter.models.requests.eosaccount.EosAccount;
import top.andnux.scatter.models.requests.getaccount.Account;
import top.andnux.scatter.models.requests.msgtransaction.MsgTransactionRequestParams;
import top.andnux.scatter.models.requests.serializedtransaction.SerializedTransaction;
import top.andnux.scatter.models.requests.serializedtransaction.SerializedTransactionRequestParams;
import top.andnux.scatter.models.requests.transaction.request.TransactionRequestParams;
import top.andnux.scatter.models.response.ResultCode;
import top.andnux.scatter.util.ScatterHelper;

public class MyScatterClient implements ScatterClient {

//    private static final String accountName = "liuliang";
//    private static final String privateKey = "5KXCLfS4b7QSwMVZ3nY3YsZJ4WusnhHw1DkHo5TkQBTWVpKtQHc";
//    private static final String publicKey = "EOS7vwabXPgunsoq286ZXjHXc4oqoP9jqek1TmKANyTYrot4nw2SR";

    private static final String accountName = "zhangchunlin";
    private static final String privateKey = "5JNNm5t64sC6HRXT2oMDJJSULyciSHztpqKqdm62RHChvBjmMSB";
    private static final String publicKey = "EOS76pwnQG8tdctc4ytSXZEGjVhQdkLXgyFFZ1dWGf3iAU4PRMbqq";

    @Override
    public void getAppInfo(Callback<AppInfoResponseData> callback) {
        callback.onSuccess(new AppInfoResponseData("1.0.0", "1",
                "1.0.0", "1"));
    }

    @Override
    public void getAccount(EosAccount account, Callback<Account> callback) {
        EosAccount.AccountsBean bean = account.getAccounts().get(0);
        callback.onSuccess(new Account(accountName, "owner", publicKey, "eos",
                bean.getChainId(), false));
    }

    @Override
    public void completeTransaction(TransactionRequestParams transactionRequestParams, Callback<String[]> callback) {
        String[] signatures = ScatterHelper.toEosTransaction(transactionRequestParams,
                new PrivateKey(privateKey)).getPackedTx().getSignatures();
        callback.onSuccess(signatures);
    }

    @Override
    public void completeSerializedTransaction(SerializedTransactionRequestParams serializedTransactionRequestParams, Callback<String[]> callback) {
        try {
            SerializedTransactionRequestParams.Network network = serializedTransactionRequestParams.getNetwork();
            ISerializationProvider abieos = new AbiEosSerializationProviderImpl();
            SerializedTransaction paramsTransaction = serializedTransactionRequestParams.getTransaction();
            String transactionJson = abieos.deserializeTransaction(paramsTransaction.getSerializedTransaction());
            String baseURL = network.toUrl();
            EosioJavaRpcProviderImpl provider = new EosioJavaRpcProviderImpl(baseURL);
            Gson gson = new Gson();
            TransactionBean bean = gson.fromJson(transactionJson, TransactionBean.class);
            List<TransactionBean.ActionsBean> actions = bean.getActions();
            for (TransactionBean.ActionsBean action : actions) {
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                HashMap<String, String> map = new HashMap<>();
                map.put("account_name", action.getAccount());
                String abi = provider.getAbi(RequestBody.create(JSON, String.valueOf(gson.toJson(map))));
                AbiResponse.AbiBean abiBean = gson.fromJson(abi, AbiResponse.class).getAbi();
                abi = gson.toJson(abiBean);
                AbiEosSerializationObject serializationObject = new AbiEosSerializationObject(action.getAccount(),
                        action.getName(), null, abi);
                serializationObject.setHex(action.getData().toString());
                abieos.deserialize(serializationObject);
                String json = serializationObject.getJson();
                action.setData(json);
            }
            String[] signatures = ScatterHelper.getSignaturesForSerializedTransaction(
                    paramsTransaction.getSerializedTransaction(),
                    paramsTransaction.getChainId(), new PrivateKey(privateKey));
            callback.onSuccess(signatures);
        } catch (Exception e) {
            e.printStackTrace();
            callback.onError(ResultCode.NO_SIGNATURE, e.getMessage());
        }
    }

    @Override
    public void completeMsgTransaction(MsgTransactionRequestParams params, Callback<String> callback) {
        byte[] data = (params.getIsHash()) ? Hex.decode(params.getData())
                : HashUtil.sha256(params.getData().getBytes(StandardCharsets.UTF_8)).getBytes();
        Signature signature = Eos.signTransactionHashed(data, new PrivateKey(privateKey));
        callback.onSuccess(signature.toString());
    }

    @Override
    public void getPublicKey(Callback<String> callback) {
        callback.onSuccess(publicKey);
    }

    @Override
    public void authenticate(AuthenticateRequestParams authenticateRequestParams, Callback<String> callback) {
        boolean isHash = authenticateRequestParams.getData() != null;
        String toSign = isHash ? authenticateRequestParams.getData() : authenticateRequestParams.getOrigin();
        if (!TextUtils.isEmpty(toSign)) {
            String signedDataSha = Hex.toHexString(HashUtil.sha256(toSign.getBytes()).getBytes());
            String nonceSha = Hex.toHexString(HashUtil.sha256(authenticateRequestParams.getNonce().getBytes()).getBytes());
            byte[] hashDataDigest = (signedDataSha + nonceSha).getBytes(StandardCharsets.UTF_8);
            byte[] hashData = HashUtil.sha256(hashDataDigest).getBytes();
            MsgTransactionRequestParams params = new MsgTransactionRequestParams(
                    Hex.toHexString(hashData),
                    authenticateRequestParams.getPublicKey() != null ? authenticateRequestParams.getPublicKey() : "",
                    isHash, "");
            completeMsgTransaction(params, callback);
        } else {
            callback.onError(ResultCode.NO_SIGNATURE, "data or origin is null");
        }
    }
}