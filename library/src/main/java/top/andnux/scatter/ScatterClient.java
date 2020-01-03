package top.andnux.scatter;

import top.andnux.scatter.models.requests.authenticate.AuthenticateRequestParams;
import top.andnux.scatter.models.requests.msgtransaction.MsgTransactionRequestParams;
import top.andnux.scatter.models.requests.serializedtransaction.SerializedTransactionRequestParams;
import top.andnux.scatter.models.requests.transaction.request.TransactionRequestParams;
import top.andnux.scatter.models.response.AccountReceivedResponse;
import top.andnux.scatter.models.response.AppInfoResponse;
import top.andnux.scatter.models.response.ResultCode;

public interface ScatterClient {

    /*
       返回APP信息
     */
    void getAppInfo(Callback<AppInfoResponse> callback);

    /**
     * 返回账户信息
     *
     * @param callback
     */
    void getAccount(Callback<AccountReceivedResponse> callback);

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

    interface Callback<T> {

        void onSuccess(T data);

        void onError(ResultCode resultCode, String message);
    }

}
