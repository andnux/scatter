package top.andnux.scatter.js;

import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.google.gson.Gson;

import top.andnux.scatter.ScatterClient;
import top.andnux.scatter.js.models.ScatterRequest;

import static top.andnux.scatter.js.ScatterJsService.*;
import static top.andnux.scatter.models.Type.*;

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
        Log.e("TAG", "pushMessage() called with: data = [" + data + "]");
        ScatterRequest scatterRequest = gson.fromJson(data, ScatterRequest.class);
        switch (scatterRequest.getMethodName()) {
            case GET_VERSION: {
                getAppInfo(webView, scatterClient, scatterRequest);
                break;
            }
            case IDENTITY_FROM_PERMISSIONS:
            case GET_IDENTITY:
            case GET_OR_REQUEST_IDENTITY: {
                getEosAccount(webView, scatterClient, scatterRequest);
                break;
            }
            case REQUEST_SIGNATURE: {
                handleRequestSignature(webView,scatterClient,scatterRequest);
                break;
            }
            case AUTHENTICATE: {
                ScatterJsService.authenticate(webView, scatterClient, scatterRequest);
                break;
            }
            case REQUEST_ARBITRARY_SIGNATURE: {
                requestMsgSignature(webView, scatterClient, scatterRequest);
                break;
            }
            default:
                break;
        }
    }
}
