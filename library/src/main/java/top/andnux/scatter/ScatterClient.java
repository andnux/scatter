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
     * @param transactionRequestParams
     * @param callback
     */
    void completeTransaction(TransactionRequestParams transactionRequestParams,
                             Callback<String[]> callback);

    /**
     * 返回签名信息
     *
     * @param serializedTransactionRequestParams
     * @param callback
     */
    void completeSerializedTransaction(SerializedTransactionRequestParams serializedTransactionRequestParams,
                                       Callback<String[]> callback);

    /**
     * 返回签名信息
     *
     * @param msgTransactionRequestParams
     * @param callback
     */
    void completeMsgTransaction(MsgTransactionRequestParams msgTransactionRequestParams,
                                Callback<String> callback);

    /**
     * 返回公钥
     *
     * @param callback
     */
    void getPublicKey(Callback<String> callback);

    /**
     * 返回签名信息
     *
     * @param authenticateRequestParams
     * @param callback
     */
    void authenticate(AuthenticateRequestParams authenticateRequestParams,
                      Callback<String> callback);

    interface Callback<T> {

        void onSuccess(T data);

        void onError(ResultCode resultCode, String message);
    }

}
