package top.andnux.scatter;

import java.util.List;

import top.andnux.scatter.js.models.TransactionRequest;
import top.andnux.scatter.models.requests.authenticate.AuthenticateRequestParams;
import top.andnux.scatter.models.requests.msgtransaction.MsgTransactionRequestParams;
import top.andnux.scatter.models.requests.serializedtransaction.SerializedTransactionRequestParams;
import top.andnux.scatter.models.requests.transaction.request.TransactionRequestParams;
import top.andnux.scatter.models.response.ResultCode;

public abstract class ScatterClient {

    public interface AppInfoReceived {

        void onAppInfoReceivedSuccessCallback(String appName, String appVersion);

        void onAccountReceivedErrorCallback(Error error);
    }

    public interface AccountReceived {

        void onAccountReceivedSuccessCallback(String accountName, String authority, String publicKey);

        void onAccountReceivedErrorCallback(Error error);
    }

    public interface TransactionCompleted {

        void onTransactionCompletedSuccessCallback(String[] signatures);

        void onTransactionCompletedErrorCallback(ResultCode resultCode, String messageToUser);
    }

    public interface SerializedTransactionCompleted {

        void onTransactionCompletedSuccessCallback(String[] signatures);

        void onTransactionCompletedErrorCallback(ResultCode resultCode, String messageToUser);
    }

    public interface MsgTransactionCompleted {

        void onMsgTransactionCompletedSuccessCallback(String signature);

        void onMsgTransactionCompletedErrorCallback(ResultCode resultCode, String messageToUser);
    }

    public interface PublicKeyReceived {

        void onPublicKeyReceivedSuccessCallback(String publicKey);

        void onPublicKeyReceivedErrorCallback(Error error);
    }

    public abstract void getAppInfo(AppInfoReceived onAppInfoReceived);

    public abstract void getAccount(AccountReceived onAccountReceived);

    public abstract void completeTransaction(TransactionRequestParams transactionRequestParams,
                                             TransactionCompleted onTransactionCompleted);

    public abstract void completeSerializedTransaction(SerializedTransactionRequestParams serializedTransactionRequestParams,
                                                       SerializedTransactionCompleted onSerializedTransactionCompleted);

    public abstract void completeMsgTransaction(MsgTransactionRequestParams msgTransactionRequestParams,
                                                MsgTransactionCompleted onMsgTransactionMsgCompleted);

    public abstract void getPublicKey(PublicKeyReceived onPublicKeyReceived);

    public abstract void authenticate(AuthenticateRequestParams authenticateRequestParams,
                                      MsgTransactionCompleted onMsgTransactionMsgCompleted);
}
