package top.andnux.scatter.js;

import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

import top.andnux.scatter.ScatterClient;
import top.andnux.scatter.js.models.ScatterRequest;
import top.andnux.scatter.js.models.ScatterResponse;
import top.andnux.scatter.js.models.TransactionRequest;
import top.andnux.scatter.models.EosChain;
import top.andnux.scatter.models.ProtocolInfo;
import top.andnux.scatter.models.requests.appinfo.AppInfoResponseData;
import top.andnux.scatter.models.requests.authenticate.AuthenticateRequestParams;
import top.andnux.scatter.models.requests.getaccount.Account;
import top.andnux.scatter.models.requests.getaccount.GetAccountResponse;
import top.andnux.scatter.models.requests.msgtransaction.MsgTransactionRequestParams;
import top.andnux.scatter.models.requests.serializedtransaction.SerializedTransactionRequestParams;
import top.andnux.scatter.models.requests.transaction.request.TransactionRequestParams;
import top.andnux.scatter.models.requests.transaction.response.ReturnedFields;
import top.andnux.scatter.models.requests.transaction.response.TransactionResponse;
import top.andnux.scatter.models.response.ErrorResponse;
import top.andnux.scatter.models.response.ResultCode;
import top.andnux.scatter.socket.models.response.CommandsResponse;

final class ScatterJsService {
    final static private Gson gson = new Gson();

    private ScatterJsService() {
    }

    static void getAppInfo(final WebView webView, ScatterClient scatterClient, final ScatterRequest scatterRequest) {
        ScatterClient.AppInfoReceived appInfoReceived = new ScatterClient.AppInfoReceived() {
            @Override
            public void onAppInfoReceivedSuccessCallback(String appName, String appVersion) {
                String responseData = gson.toJson(new AppInfoResponseData(appName, appVersion, ProtocolInfo.name, ProtocolInfo.version));
                sendResponse(webView, scatterRequest.getCallback(), gson.toJson(
                        new ScatterResponse(ResultCode.SUCCESS.name(),
                                responseData,
                                ResultCode.SUCCESS.getCode())
                ));
            }

            @Override
            public void onAccountReceivedErrorCallback(Error error) {
            }
        };

        scatterClient.getAppInfo(appInfoReceived);
    }

    static void getEosAccount(final WebView webView, final ScatterClient scatterClient, final ScatterRequest scatterRequest) {
        ScatterClient.AccountReceived accountReceived = new ScatterClient.AccountReceived() {
            @Override
            public void onAccountReceivedSuccessCallback(String accountName, String authority, String publicKey) {
                sendResponse(webView, scatterRequest.getCallback(), gson.toJson(
                        new ScatterResponse(ResultCode.SUCCESS.name(), gson.toJson(
                                new GetAccountResponse(
                                        "db4960659fb585600be9e0ec48d2e6f4826d6f929c4bcef095356ce51424608d",
                                        publicKey,
                                        ProtocolInfo.name,
                                        false,
                                        new Account[]{new Account(accountName, authority, publicKey, EosChain.chainName, EosChain.chainId, false)})
                        ), ResultCode.SUCCESS.getCode())
                ));
            }

            @Override
            public void onAccountReceivedErrorCallback(Error error) {
                sendErrorScript(webView, scatterRequest.getCallback(), ResultCode.UPGRADE_REQUIRED, "login error");
            }
        };

        scatterClient.getAccount(accountReceived);
    }

    public static void handleRequestSignature(final WebView webView, ScatterClient scatterClient, final ScatterRequest scatterRequest) {
        TransactionRequestParams transactionRequestParams = gson.fromJson(scatterRequest.getParams(), TransactionRequestParams.class);
        if (transactionRequestParams.getBuf() != null) {
            requestSignature(webView, scatterClient, transactionRequestParams, scatterRequest);
        } else {
            Log.e("+++++++++++++++++++=", scatterRequest.getParams());
            SerializedTransactionRequestParams transactionRequest = gson.fromJson(scatterRequest.getParams(), SerializedTransactionRequestParams.class);
            requestSerializedTransactionSignature(webView, transactionRequest, scatterClient, scatterRequest);
        }
    }


    private static void requestSerializedTransactionSignature(final WebView webView,
                                                              SerializedTransactionRequestParams serializedTransactionRequestParams,
                                                              ScatterClient scatterClient,
                                                              final ScatterRequest scatterRequest) {
        ScatterClient.SerializedTransactionCompleted transactionCompleted = new ScatterClient.SerializedTransactionCompleted() {
            @Override
            public void onTransactionCompletedSuccessCallback(String[] signatures) {
                sendResponse(webView, scatterRequest.getCallback(),gson.toJson( new ScatterResponse(ResultCode.SUCCESS.name(),
                        gson.toJson(new TransactionResponse(signatures, new ReturnedFields())), ResultCode.SUCCESS.getCode())));
            }

            @Override
            public void onTransactionCompletedErrorCallback(ResultCode resultCode, String messageToUser) {
                sendErrorScript(webView, scatterRequest.getCallback(), resultCode, messageToUser);
            }
        };

        scatterClient.completeSerializedTransaction(serializedTransactionRequestParams, transactionCompleted);
    }

    private static void requestSignature(final WebView webView, ScatterClient scatterClient,
                                         final TransactionRequestParams transactionRequestParams,
                                         final ScatterRequest scatterRequest) {

        ScatterClient.TransactionCompleted transactionCompleted = new ScatterClient.TransactionCompleted() {
            @Override
            public void onTransactionCompletedSuccessCallback(String[] signatures) {
                String response = gson.toJson(new TransactionResponse(signatures, new ReturnedFields()));
                sendResponse(webView, scatterRequest.getCallback(), gson.toJson(
                        new ScatterResponse(ResultCode.SUCCESS.name(), response, ResultCode.SUCCESS.getCode())
                ));
            }

            @Override
            public void onTransactionCompletedErrorCallback(ResultCode resultCode, String messageToUser) {
                sendErrorScript(webView, scatterRequest.getCallback(), resultCode, messageToUser);
            }
        };

        scatterClient.completeTransaction(transactionRequestParams, transactionCompleted);
    }

    static void requestMsgSignature(final WebView webView, ScatterClient scatterClient, final ScatterRequest scatterRequest) {
        final MsgTransactionRequestParams msgTransactionRequestParams = gson.fromJson(scatterRequest.getParams(), MsgTransactionRequestParams.class);

        ScatterClient.MsgTransactionCompleted msgTransactionCompleted = new ScatterClient.MsgTransactionCompleted() {
            @Override
            public void onMsgTransactionCompletedSuccessCallback(String signature) {
                sendResponse(webView, scatterRequest.getCallback(), gson.toJson(
                        new ScatterResponse(ResultCode.SUCCESS.name(), gson.toJson(signature), ResultCode.SUCCESS.getCode())
                ));
            }

            @Override
            public void onMsgTransactionCompletedErrorCallback(ResultCode resultCode, String messageToUser) {
                sendErrorScript(webView, scatterRequest.getCallback(), resultCode, messageToUser);
            }
        };

        scatterClient.completeMsgTransaction(msgTransactionRequestParams, msgTransactionCompleted);
    }

    static void authenticate(final WebView webView, ScatterClient scatterClient, final ScatterRequest scatterRequest) {
        AuthenticateRequestParams authRequestParams = gson.fromJson(scatterRequest.getParams(), AuthenticateRequestParams.class);
        ScatterClient.MsgTransactionCompleted transactionCompleted = new ScatterClient.MsgTransactionCompleted() {
            @Override
            public void onMsgTransactionCompletedSuccessCallback(String signature) {
                sendResponse(webView, scatterRequest.getCallback(),
                        gson.toJson(new ScatterResponse(ResultCode.SUCCESS.name(), gson.toJson(signature), ResultCode.SUCCESS.getCode())));
            }

            @Override
            public void onMsgTransactionCompletedErrorCallback(ResultCode resultCode, String messageToUser) {
                sendErrorScript(webView, scatterRequest.getCallback(), resultCode, messageToUser);
            }
        };

        scatterClient.authenticate(authRequestParams, transactionCompleted);
    }

    private static void sendErrorScript(WebView webView, String callback, ResultCode resultCode, String messageToUser) {
        sendResponse(webView, callback, gson.toJson(
                new ErrorResponse(resultCode.getCode(), messageToUser, resultCode.name())
        ));
    }

    private static void sendResponse(WebView webView, String callback, String response) {
        Log.d("ScatterJsService", response);
        injectJs(webView, "javascript:callbackResult('" + callback + "','" + response + "')");
    }

    static void injectJs(final WebView webView, final String script) {
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.evaluateJavascript(script, new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        Log.e("onReceiveValue", value);
                    }
                });
            }
        });
    }
}
