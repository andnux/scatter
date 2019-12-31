package top.andnux.scatter;

import android.webkit.WebView;

import top.andnux.scatter.js.ScatterJs;
import top.andnux.scatter.socket.ScatterSocket;

public class ScatterFactory {

    public static Scatter getScatter(WebView webView, ScatterClient scatterClient, boolean webExtension) {
        if (webExtension) {
            return new ScatterJs(webView, scatterClient);
        } else {
            return new ScatterSocket(scatterClient);
        }
    }
}
