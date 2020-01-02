package top.andnux.scatterapp;

import android.text.TextUtils;
import android.util.Log;

import com.paytomat.core.util.HashUtil;
import com.paytomat.eos.Eos;
import com.paytomat.eos.PrivateKey;
import com.paytomat.eos.signature.Signature;

import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;

import top.andnux.scatter.ScatterClient;
import top.andnux.scatter.models.requests.authenticate.AuthenticateRequestParams;
import top.andnux.scatter.models.requests.msgtransaction.MsgTransactionRequestParams;
import top.andnux.scatter.models.requests.serializedtransaction.SerializedTransaction;
import top.andnux.scatter.models.requests.serializedtransaction.SerializedTransactionRequestParams;
import top.andnux.scatter.models.requests.transaction.request.TransactionRequestParams;
import top.andnux.scatter.models.response.ResultCode;
import top.andnux.scatter.util.ScatterHelper;

public class MyScatterClient extends ScatterClient {

    private static final String accountName = "liuliang";
    private static final String privateKey = "5KXCLfS4b7QSwMVZ3nY3YsZJ4WusnhHw1DkHo5TkQBTWVpKtQHc";
    private static final String publicKey = "EOS7vwabXPgunsoq286ZXjHXc4oqoP9jqek1TmKANyTYrot4nw2SR";

//    private static final String accountName = "zhangchunlin";
//    private static final String privateKey = "5JNNm5t64sC6HRXT2oMDJJSULyciSHztpqKqdm62RHChvBjmMSB";
//    private static final String publicKey = "EOS76pwnQG8tdctc4ytSXZEGjVhQdkLXgyFFZ1dWGf3iAU4PRMbqq";

    private static final String TAG = "MyScatterClient";

    @Override
    public void getAppInfo(AppInfoReceived onAppInfoReceived) {
        Log.d(TAG, "getAppInfo() called with: onAppInfoReceived = [" + onAppInfoReceived + "]");
    }

    @Override
    public void getAccount(AccountReceived onAccountReceived) {
        Log.d(TAG, "getAccount() called with: onAccountReceived = [" + onAccountReceived + "]");
        onAccountReceived.onAccountReceivedSuccessCallback(accountName, "owner", publicKey);
    }

    @Override
    public void completeTransaction(TransactionRequestParams transactionRequestParams,
                                    TransactionCompleted onTransactionCompleted) {
        Log.d(TAG, "completeTransaction() called with: transactionRequestParams = [" + transactionRequestParams + "]," +
                " onTransactionCompleted = [" + onTransactionCompleted + "]");
        String[] signatures = ScatterHelper.toEosTransaction(transactionRequestParams, new PrivateKey(privateKey)).getPackedTx().getSignatures();
        onTransactionCompleted.onTransactionCompletedSuccessCallback(signatures);
    }

    @Override
    public void completeSerializedTransaction(SerializedTransactionRequestParams serializedTransactionRequestParams,
                                              SerializedTransactionCompleted onTransactionCompleted) {
        try {
            Log.d(TAG, "completeSerializedTransaction() called with: serializedTransactionRequestParams = [" +
                    serializedTransactionRequestParams + "]," +
                    " onTransactionCompleted = [" + onTransactionCompleted + "]");
            SerializedTransaction transaction = serializedTransactionRequestParams.getTransaction();
            String[] signatures = ScatterHelper.getSignaturesForSerializedTransaction(
                    transaction.getSerializedTransaction(),
                    transaction.getChainId(), new PrivateKey(privateKey));
            onTransactionCompleted.onTransactionCompletedSuccessCallback(signatures);
        } catch (Exception e) {
            e.printStackTrace();
            onTransactionCompleted.onTransactionCompletedErrorCallback(ResultCode.NO_SIGNATURE, e.getMessage());
        }
    }

    @Override
    public void completeMsgTransaction(MsgTransactionRequestParams params, MsgTransactionCompleted onMsgTransactionCompleted) {
        Log.d(TAG, "completeMsgTransaction() called with: params = [" + params + "], onMsgTransactionCompleted = [" + onMsgTransactionCompleted + "]");
        byte[] data = (params.getIsHash()) ? Hex.decode(params.getData())
                : HashUtil.sha256(params.getData().getBytes(StandardCharsets.UTF_8)).getBytes();
        Signature signature = Eos.signTransactionHashed(data, new PrivateKey(privateKey));
        onMsgTransactionCompleted.onMsgTransactionCompletedSuccessCallback(signature.toString());
    }

    @Override
    public void getPublicKey(PublicKeyReceived onPublicKeyReceived) {
        onPublicKeyReceived.onPublicKeyReceivedSuccessCallback(publicKey);
    }

    @Override
    public void authenticate(AuthenticateRequestParams authenticateRequestParams,
                             MsgTransactionCompleted onMsgTransactionMsgCompleted) {
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
            completeMsgTransaction(params, onMsgTransactionMsgCompleted);
        } else {
            onMsgTransactionMsgCompleted.onMsgTransactionCompletedErrorCallback(ResultCode.NO_SIGNATURE, "data or origin is null");
        }
    }
}