package top.andnux.scatter.js;

import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.google.gson.Gson;

import top.andnux.scatter.ScatterClient;
import top.andnux.scatter.js.models.ScatterRequest;

import static top.andnux.scatter.models.Type.ADD_TOKEN;
import static top.andnux.scatter.models.Type.AUTHENTICATE;
import static top.andnux.scatter.models.Type.FORGET_IDENTITY;
import static top.andnux.scatter.models.Type.GET_IDENTITY;
import static top.andnux.scatter.models.Type.GET_OR_REQUEST_IDENTITY;
import static top.andnux.scatter.models.Type.GET_VERSION;
import static top.andnux.scatter.models.Type.IDENTITY_FROM_PERMISSIONS;
import static top.andnux.scatter.models.Type.REQUEST_ARBITRARY_SIGNATURE;
import static top.andnux.scatter.models.Type.REQUEST_SIGNATURE;
import static top.andnux.scatter.models.Type.SUGGEST_NETWORK;

class ScatterWebInterface {

    private WebView webView;
    private ScatterClient scatterClient;

    final static private Gson gson = new Gson();

    ScatterWebInterface(WebView webView, ScatterClient scatterClient) {
        this.webView = webView;
        this.scatterClient = scatterClient;
    }

    @JavascriptInterface
    public void pushMessage(String data) {
        scatterClient.showLoading("loading...");
        Log.e("TAG", "pushMessage() called with: data = [" + data + "]");
        ScatterRequest scatterRequest = gson.fromJson(data, ScatterRequest.class);
        switch (scatterRequest.getMethodName()) {
            case GET_VERSION: {
                ScatterJsService.getAppInfo(webView, scatterClient, scatterRequest);
                break;
            }
            case IDENTITY_FROM_PERMISSIONS:
            case GET_IDENTITY:
            case GET_OR_REQUEST_IDENTITY: {
                ScatterJsService.getEosAccount(webView, scatterClient, scatterRequest);
                break;
            }
            case REQUEST_SIGNATURE: {
                ScatterJsService.handleRequestSignature(webView, scatterClient, scatterRequest);
                break;
            }
            case AUTHENTICATE: {
                ScatterJsService.authenticate(webView, scatterClient, scatterRequest);
                break;
            }
            case ADD_TOKEN: {
                ScatterJsService.addToken(webView, scatterClient, scatterRequest);
                break;
            }
            case REQUEST_ARBITRARY_SIGNATURE: {
                ScatterJsService.requestMsgSignature(webView, scatterClient, scatterRequest);
                break;
            }
            case FORGET_IDENTITY: {
                ScatterJsService.forgetIdentity(webView, scatterClient, scatterRequest);
                break;
            }
            case SUGGEST_NETWORK: {
                ScatterJsService.suggestNetwork(webView, scatterClient, scatterRequest);
                break;
            }
            default:
                scatterClient.hideLoading();
                break;
        }
    }
}
