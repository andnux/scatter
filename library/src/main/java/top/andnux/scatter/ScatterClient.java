package top.andnux.scatter;

import top.andnux.scatter.models.Network;
import top.andnux.scatter.models.requests.appinfo.AppInfoResponseData;
import top.andnux.scatter.models.requests.authenticate.AuthenticateRequestParams;
import top.andnux.scatter.models.requests.eosaccount.EosAccount;
import top.andnux.scatter.models.requests.getaccount.GetAccountResponse;
import top.andnux.scatter.models.requests.msgtransaction.MsgTransactionRequestParams;
import top.andnux.scatter.models.requests.serializedtransaction.SerializedTransactionRequestParams;
import top.andnux.scatter.models.requests.suggestnetwork.SuggestNetwork;
import top.andnux.scatter.models.requests.transaction.request.TransactionRequestParams;
import top.andnux.scatter.models.response.ResultCode;

public interface ScatterClient {

    /**
     * 显示加载框
     *
     * @param message
     */
    void showLoading(String message);

    /**
     * 隐藏加载框
     */
    void hideLoading();

    /*
       返回APP信息
     */
    void getAppInfo(Callback<AppInfoResponseData> callback);

    /**
     * 返回账户信息
     *
     * @param account
     * @param callback
     */
    void getAccount(EosAccount account, Callback<GetAccountResponse> callback);

    /**
     * 返回签名信息
     *
     * @param params
     * @param callback
     */
    void completeTransaction(TransactionRequestParams params, Callback<String[]> callback);

    /**
     * 返回签名信息
     *
     * @param params
     * @param callback
     */
    void completeSerializedTransaction(SerializedTransactionRequestParams params, Callback<String[]> callback);

    /**
     * 返回签名信息
     *
     * @param params
     * @param callback
     */
    void completeMsgTransaction(MsgTransactionRequestParams params, Callback<String> callback);

    /**
     * 返回公钥
     *
     * @param callback
     */
    void getPublicKey(Callback<String> callback);

    /**
     * 返回签名信息
     *
     * @param params
     * @param callback
     */
    void authenticate(AuthenticateRequestParams params, Callback<String> callback);

    /**
     * 返回最好的网络节点
     *
     * @param params
     * @param callback
     */
    void suggestNetwork(SuggestNetwork params, Callback<Network> callback);


    /**
     * 退出登录
     *
     * @param callback
     */
    void forgetIdentity(Callback<Boolean> callback);


    /**
     * 回调
     *
     * @param <T>
     */
    interface Callback<T> {

        void onSuccess(T data);

        void onError(ResultCode resultCode, String message);
    }

}
