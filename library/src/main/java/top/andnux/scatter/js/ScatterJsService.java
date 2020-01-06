package top.andnux.scatter.js;

import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebView;

import com.google.gson.Gson;

import top.andnux.scatter.ScatterClient;
import top.andnux.scatter.js.models.ScatterRequest;
import top.andnux.scatter.js.models.ScatterResponse;
import top.andnux.scatter.models.Network;
import top.andnux.scatter.models.requests.appinfo.AppInfoResponseData;
import top.andnux.scatter.models.requests.authenticate.AuthenticateRequestParams;
import top.andnux.scatter.models.requests.eosaccount.EosAccount;
import top.andnux.scatter.models.requests.getaccount.GetAccountResponse;
import top.andnux.scatter.models.requests.msgtransaction.MsgTransactionRequestParams;
import top.andnux.scatter.models.requests.serializedtransaction.SerializedTransactionRequestParams;
import top.andnux.scatter.models.requests.suggestnetwork.SuggestNetwork;
import top.andnux.scatter.models.requests.transaction.request.TransactionRequestParams;
import top.andnux.scatter.models.requests.transaction.response.ReturnedFields;
import top.andnux.scatter.models.requests.transaction.response.TransactionResponse;
import top.andnux.scatter.models.response.ErrorResponse;
import top.andnux.scatter.models.response.ResultCode;

final class ScatterJsService {

    final static private Gson gson = new Gson();

    private ScatterJsService() {

    }

    static void getAppInfo(final WebView webView, final ScatterClient scatterClient, final ScatterRequest scatterRequest) {
        scatterClient.getAppInfo(new ScatterClient.Callback<AppInfoResponseData>() {
            @Override
            public void onSuccess(AppInfoResponseData data) {
                String responseData = gson.toJson(data);
                sendResponse(webView, scatterClient, scatterRequest.getCallback(), gson.toJson(
                        new ScatterResponse(ResultCode.SUCCESS.name(),
                                responseData,
                                ResultCode.SUCCESS.getCode())
                ));
            }

            @Override
            public void onError(ResultCode resultCode, String message) {
                sendErrorScript(webView, scatterClient,scatterRequest.getCallback(), ResultCode.UPGRADE_REQUIRED, message);
            }
        });
    }

    static void getEosAccount(final WebView webView, final ScatterClient scatterClient, final ScatterRequest scatterRequest) {
        EosAccount account = gson.fromJson(scatterRequest.getParams(), EosAccount.class);
        scatterClient.getAccount(account, new ScatterClient.Callback<GetAccountResponse>() {
            @Override
            public void onSuccess(GetAccountResponse data) {
                sendResponse(webView, scatterClient, scatterRequest.getCallback(), gson.toJson(
                        new ScatterResponse(ResultCode.SUCCESS.name(), gson.toJson(data),
                                ResultCode.SUCCESS.getCode())
                ));
            }

            @Override
            public void onError(ResultCode resultCode, String message) {
                sendErrorScript(webView,scatterClient, scatterRequest.getCallback(), ResultCode.UPGRADE_REQUIRED, message);
            }
        });
    }

    static void handleRequestSignature(final WebView webView, ScatterClient scatterClient, final ScatterRequest scatterRequest) {
        TransactionRequestParams transactionRequestParams = gson.fromJson(scatterRequest.getParams(), TransactionRequestParams.class);
        if (transactionRequestParams.getBuf() != null) {
            requestSignature(webView, scatterClient, transactionRequestParams, scatterRequest);
        } else {
            SerializedTransactionRequestParams transactionRequest = gson.fromJson(scatterRequest.getParams(), SerializedTransactionRequestParams.class);
            requestSerializedTransactionSignature(webView, transactionRequest, scatterClient, scatterRequest);
        }
    }


    private static void requestSerializedTransactionSignature(final WebView webView,
                                                              SerializedTransactionRequestParams serializedTransactionRequestParams,
                                                              final ScatterClient scatterClient,
                                                              final ScatterRequest scatterRequest) {
        scatterClient.completeSerializedTransaction(serializedTransactionRequestParams, new ScatterClient.Callback<String[]>() {
            @Override
            public void onSuccess(String[] data) {
                sendResponse(webView, scatterClient, scatterRequest.getCallback(), gson.toJson(new ScatterResponse(ResultCode.SUCCESS.name(),
                        gson.toJson(new TransactionResponse(data, new ReturnedFields())), ResultCode.SUCCESS.getCode())));
            }

            @Override
            public void onError(ResultCode resultCode, String message) {
                sendErrorScript(webView,scatterClient, scatterRequest.getCallback(), resultCode, message);
            }
        });
    }

    private static void requestSignature(final WebView webView, final ScatterClient scatterClient,
                                         final TransactionRequestParams transactionRequestParams,
                                         final ScatterRequest scatterRequest) {
        scatterClient.completeTransaction(transactionRequestParams, new ScatterClient.Callback<String[]>() {
            @Override
            public void onSuccess(String[] data) {
                String response = gson.toJson(new TransactionResponse(data, new ReturnedFields()));
                sendResponse(webView, scatterClient, scatterRequest.getCallback(), gson.toJson(
                        new ScatterResponse(ResultCode.SUCCESS.name(), response, ResultCode.SUCCESS.getCode())
                ));
            }

            @Override
            public void onError(ResultCode resultCode, String message) {
                sendErrorScript(webView,scatterClient, scatterRequest.getCallback(), resultCode, message);
            }
        });
    }

    static void requestMsgSignature(final WebView webView, final ScatterClient scatterClient, final ScatterRequest scatterRequest) {
        final MsgTransactionRequestParams msgTransactionRequestParams = gson.fromJson(scatterRequest.getParams(), MsgTransactionRequestParams.class);
        msgTransactionRequestParams.setRow(msgTransactionRequestParams.getData());
        scatterClient.completeMsgTransaction(msgTransactionRequestParams, new ScatterClient.Callback<String>() {
            @Override
            public void onSuccess(String data) {
                sendResponse(webView, scatterClient, scatterRequest.getCallback(), gson.toJson(
                        new ScatterResponse(ResultCode.SUCCESS.name(), gson.toJson(data), ResultCode.SUCCESS.getCode())
                ));
            }

            @Override
            public void onError(ResultCode resultCode, String message) {
                sendErrorScript(webView,scatterClient, scatterRequest.getCallback(), resultCode, message);
            }
        });
    }


    static void forgetIdentity(final WebView webView, final ScatterClient scatterClient, final ScatterRequest scatterRequest) {
        scatterClient.forgetIdentity(new ScatterClient.Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean data) {
                sendResponse(webView, scatterClient, scatterRequest.getCallback(), gson.toJson(
                        new ScatterResponse(ResultCode.SUCCESS.name(), gson.toJson(data), ResultCode.SUCCESS.getCode())
                ));
            }

            @Override
            public void onError(ResultCode resultCode, String message) {
                sendErrorScript(webView, scatterClient, scatterRequest.getCallback(), resultCode, message);
            }
        });
    }

    static void authenticate(final WebView webView, final ScatterClient scatterClient, final ScatterRequest scatterRequest) {
        AuthenticateRequestParams authRequestParams = gson.fromJson(scatterRequest.getParams(), AuthenticateRequestParams.class);
        scatterClient.authenticate(authRequestParams, new ScatterClient.Callback<String>() {
            @Override
            public void onSuccess(String data) {
                sendResponse(webView, scatterClient, scatterRequest.getCallback(),
                        gson.toJson(new ScatterResponse(ResultCode.SUCCESS.name(), gson.toJson(data),
                                ResultCode.SUCCESS.getCode())));
            }

            @Override
            public void onError(ResultCode resultCode, String message) {
                sendErrorScript(webView, scatterClient, scatterRequest.getCallback(), resultCode, message);
            }
        });
    }

    static void addToken(WebView webView, ScatterClient scatterClient, ScatterRequest scatterRequest) {


    }

    static void suggestNetwork(final WebView webView, final ScatterClient scatterClient, final ScatterRequest scatterRequest) {
        SuggestNetwork suggestNetwork = gson.fromJson(scatterRequest.getParams(), SuggestNetwork.class);
        scatterClient.suggestNetwork(suggestNetwork, new ScatterClient.Callback<Network>() {
            @Override
            public void onSuccess(Network data) {
                sendResponse(webView, scatterClient, scatterRequest.getCallback(),
                        gson.toJson(new ScatterResponse(ResultCode.SUCCESS.name(), gson.toJson(data),
                                ResultCode.SUCCESS.getCode())));
            }

            @Override
            public void onError(ResultCode resultCode, String message) {
                sendErrorScript(webView, scatterClient, scatterRequest.getCallback(), resultCode, message);
            }
        });
    }

    private static void sendErrorScript(WebView webView, final ScatterClient scatterClient, String callback, ResultCode resultCode, String messageToUser) {
        sendResponse(webView, scatterClient, callback, gson.toJson(
                new ErrorResponse(resultCode.getCode(), messageToUser, resultCode.name())
        ));
    }

    private static void sendResponse(WebView webView, ScatterClient scatterClient, String callback, String response) {
        Log.d("ScatterJsService", response);
        scatterClient.hideLoading();
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
