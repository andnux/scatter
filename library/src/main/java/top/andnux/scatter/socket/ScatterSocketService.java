package top.andnux.scatter.socket;

import android.util.Log;

import com.google.gson.Gson;

import org.java_websocket.WebSocket;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import top.andnux.scatter.ScatterClient;
import top.andnux.scatter.models.ProtocolInfo;
import top.andnux.scatter.models.requests.appinfo.AppInfoResponseData;
import top.andnux.scatter.models.requests.authenticate.AuthenticateRequestParams;
import top.andnux.scatter.models.requests.eosaccount.EosAccount;
import top.andnux.scatter.models.requests.getaccount.Account;
import top.andnux.scatter.models.requests.getaccount.GetAccountResponse;
import top.andnux.scatter.models.requests.msgtransaction.MsgTransactionRequestParams;
import top.andnux.scatter.models.requests.serializedtransaction.SerializedTransactionRequestParams;
import top.andnux.scatter.models.requests.transaction.request.TransactionRequestParams;
import top.andnux.scatter.models.requests.transaction.response.ReturnedFields;
import top.andnux.scatter.models.requests.transaction.response.TransactionResponse;
import top.andnux.scatter.models.response.ErrorResponse;
import top.andnux.scatter.models.response.ResultCode;
import top.andnux.scatter.socket.models.response.ApiResponseData;
import top.andnux.scatter.socket.models.response.CommandsResponse;

import static top.andnux.scatter.models.Type.ADD_TOKEN;
import static top.andnux.scatter.models.Type.AUTHENTICATE;
import static top.andnux.scatter.models.Type.FORGET_IDENTITY;
import static top.andnux.scatter.models.Type.GET_IDENTITY;
import static top.andnux.scatter.models.Type.GET_OR_REQUEST_IDENTITY;
import static top.andnux.scatter.models.Type.GET_PUBLIC_KEY;
import static top.andnux.scatter.models.Type.GET_VERSION;
import static top.andnux.scatter.models.Type.IDENTITY_FROM_PERMISSIONS;
import static top.andnux.scatter.models.Type.LINK_ACCOUNT;
import static top.andnux.scatter.models.Type.REQUEST_ADD_NETWORK;
import static top.andnux.scatter.models.Type.REQUEST_ARBITRARY_SIGNATURE;
import static top.andnux.scatter.models.Type.REQUEST_SIGNATURE;
import static top.andnux.scatter.socket.SocketsConstants.MESSAGE_START;

public class ScatterSocketService {
    final static private Gson gson = new Gson();

    private ScatterSocketService() {
    }

    static void handlePairResponse(WebSocket conn) {
        sendResponse(conn, "[\"" + CommandsResponse.PAIRED + "\",true]");
    }

    static void handleRekeyResponse(WebSocket conn) {
        sendResponse(conn, "[\"" + CommandsResponse.REKEY + "\"]");
    }

    static void handleConnectedResponse(WebSocket conn) {
        sendResponse(conn, "[\"" + CommandsResponse.CONNECTED + "\"]");
    }

    static void handleApiResponse(WebSocket conn, JSONArray params, ScatterClient scatterClient) throws JSONException {
        JSONObject details = params.getJSONObject(1);
        JSONObject data = details.getJSONObject("data");
        String type = data.getString("type");
        String payload = data.getString("payload");
        String id = data.getString("id");

        switch (type) {
            case GET_VERSION: {
                getVersion(conn, id, scatterClient);
                break;
            }
            case IDENTITY_FROM_PERMISSIONS:
            case GET_IDENTITY:
            case GET_OR_REQUEST_IDENTITY: {
                getIdentity(conn, id,payload, scatterClient);
                break;
            }
            case REQUEST_SIGNATURE: {
                handleRequestSignature(conn, id, payload, scatterClient);
                break;
            }
            case AUTHENTICATE: {
                authenticate(conn, id, payload, scatterClient);
                break;
            }
            case REQUEST_ADD_NETWORK:
            case LINK_ACCOUNT:
            case FORGET_IDENTITY: {
                sendBooleanTrueResponse(conn, id, scatterClient);
                break;
            }
            case REQUEST_ARBITRARY_SIGNATURE: {
                requestMsgSignature(conn, id, payload, scatterClient);
                break;
            }

            case ADD_TOKEN: {
                ScatterSocketService.addToken(conn, id, payload, scatterClient);
                break;
            }

            case GET_PUBLIC_KEY: {
                getPublicKey(conn, id, scatterClient);
                break;
            }
            default:
                break;
        }
    }

    private static void addToken(WebSocket conn, String id, String payload, ScatterClient scatterClient) {


    }

    private static void handleRequestSignature(final WebSocket conn, final String id,
                                               String payload, ScatterClient scatterClient) {
        TransactionRequestParams transactionRequestParams = gson.fromJson(payload, TransactionRequestParams.class);
        if (transactionRequestParams.getBuf() != null) {
            requestSignature(conn, id, transactionRequestParams, scatterClient);
        } else {
            SerializedTransactionRequestParams serializedTransactionRequestParams =
                    gson.fromJson(payload, SerializedTransactionRequestParams.class);
            requestSerializedTransactionSignature(conn, id, serializedTransactionRequestParams, scatterClient);
        }
    }

    private static void getVersion(final WebSocket conn, final String id, ScatterClient scatterClient) {
        scatterClient.getAppInfo(new ScatterClient.Callback<AppInfoResponseData>() {
            @Override
            public void onSuccess(AppInfoResponseData data) {
                sendResponse(conn,
                        gson.toJson(new ArrayList<>(Arrays.asList(CommandsResponse.API,
                                new ApiResponseData(id, gson.toJson(data)))))
                );
            }

            @Override
            public void onError(ResultCode resultCode, String message) {
                sendErrorResponse(conn, id, resultCode, message);
            }
        });

    }

    private static void authenticate(final WebSocket conn, final String id, String payload, ScatterClient scatterClient) {
        AuthenticateRequestParams authRequestParams = gson.fromJson(payload, AuthenticateRequestParams.class);
        scatterClient.authenticate(authRequestParams, new ScatterClient.Callback<String>() {
            @Override
            public void onSuccess(String data) {
                sendResponse(conn,
                        gson.toJson(new ArrayList<>(Arrays.asList(CommandsResponse.API,
                                new ApiResponseData(id, gson.toJson(data)))))
                );
            }

            @Override
            public void onError(ResultCode resultCode, String message) {
                sendErrorResponse(conn, id, resultCode, message);
            }
        });
    }

    private static void getIdentity(final WebSocket conn, final String id,String payload, ScatterClient scatterClient) {
        final EosAccount account = gson.fromJson(payload, EosAccount.class);
        scatterClient.getAccount(account, new ScatterClient.Callback<GetAccountResponse>() {
            @Override
            public void onSuccess(GetAccountResponse data) {
                sendResponse(conn, gson.toJson(new ArrayList<>(Arrays.asList(CommandsResponse.API,
                        new ApiResponseData(id, gson.toJson(data))))));
            }

            @Override
            public void onError(ResultCode resultCode, String message) {
                sendErrorResponse(conn, id, resultCode, message);
            }
        });
    }

    private static void getPublicKey(final WebSocket conn, final String id, ScatterClient scatterClient) {

        scatterClient.getPublicKey(new ScatterClient.Callback<String>() {
            @Override
            public void onSuccess(String data) {
                sendResponse(conn, gson.toJson(new ArrayList<>(Arrays.asList(CommandsResponse.API,
                        new ApiResponseData(id, gson.toJson(data))))));
            }

            @Override
            public void onError(ResultCode resultCode, String message) {
                sendErrorResponse(conn, id, resultCode, message);
            }
        });
    }

    private static void requestSignature(final WebSocket conn, final String id,
                                         TransactionRequestParams transactionRequestParams,
                                         ScatterClient scatterClient) {
        scatterClient.completeTransaction(transactionRequestParams, new ScatterClient.Callback<String[]>() {
            @Override
            public void onSuccess(String[] data) {
                sendResponse(conn,
                        gson.toJson(new ArrayList<>(Arrays.asList(CommandsResponse.API, new ApiResponseData(id,
                                gson.toJson(new TransactionResponse(data, new ReturnedFields()))))))
                );
            }

            @Override
            public void onError(ResultCode resultCode, String message) {
                sendErrorResponse(conn, id, resultCode, message);
            }
        });
    }

    private static void requestSerializedTransactionSignature(final WebSocket conn, final String id,
                                                              SerializedTransactionRequestParams serializedTransactionRequestParams,
                                                              ScatterClient scatterClient) {
        scatterClient.completeSerializedTransaction(serializedTransactionRequestParams, new ScatterClient.Callback<String[]>() {
            @Override
            public void onSuccess(String[] data) {
                sendResponse(conn, gson.toJson(new ArrayList<>(Arrays.asList(CommandsResponse.API,
                        new ApiResponseData(id, gson.toJson(new TransactionResponse(data, new ReturnedFields())))))));
            }

            @Override
            public void onError(ResultCode resultCode, String message) {
                sendErrorResponse(conn, id, resultCode, message);
            }
        });
    }

    private static void requestMsgSignature(final WebSocket conn, final String id,
                                            String payload, ScatterClient scatterClient) {
        MsgTransactionRequestParams msgTransactionRequestParams = gson.fromJson(payload, MsgTransactionRequestParams.class);
        scatterClient.completeMsgTransaction(msgTransactionRequestParams, new ScatterClient.Callback<String>() {
            @Override
            public void onSuccess(String data) {
                sendResponse(conn, gson.toJson(new ArrayList<>(Arrays.asList(CommandsResponse.API,
                        new ApiResponseData(id, gson.toJson(data))))));

            }

            @Override
            public void onError(ResultCode resultCode, String message) {
                sendErrorResponse(conn, id, resultCode, message);
            }
        });
    }

    private static void sendErrorResponse(WebSocket conn, String id,
                                          ResultCode resultCode, String messageToUser) {
        sendResponse(conn, gson.toJson(
                new ArrayList<>(Arrays.asList(CommandsResponse.API, new ApiResponseData(id,
                        gson.toJson(new ErrorResponse(resultCode.getCode(), messageToUser, resultCode.name()))))))
        );
    }

    private static void sendBooleanTrueResponse(WebSocket conn, String id, ScatterClient scatterClient) {
        sendResponse(conn,
                gson.toJson(new ArrayList<>(Arrays.asList(CommandsResponse.API, new ApiResponseData(id, gson.toJson(true)))))
        );
    }

    private static void sendResponse(WebSocket conn, String response) {
        String text = MESSAGE_START + response;
        Log.d("SOCKET", text);
        conn.send(text);
    }

}
