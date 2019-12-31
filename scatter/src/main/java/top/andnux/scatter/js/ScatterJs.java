package top.andnux.scatter.js;

import android.content.Context;
import android.util.Log;
import android.webkit.WebView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;

import top.andnux.scatter.Scatter;
import top.andnux.scatter.ScatterClient;


public class ScatterJs extends Scatter {

    private String javascriptInterfaceName = "DappJsBridge";
    private WeakReference<WebView> webView;

    public ScatterJs(WebView webView, ScatterClient scatterClient) {
        super(scatterClient);
        this.webView = new WeakReference<>(webView);
        initInterface();
    }

    private void initInterface() {
        if (webView.get() != null) {
            ScatterWebInterface obj = new ScatterWebInterface(webView.get(), scatterClient);
            webView.get().addJavascriptInterface(obj, javascriptInterfaceName);
        }
    }

    private void loadJavaScript(String fileName) {
        try {
            if (webView.get() != null) {
                Context context = webView.get().getContext().getApplicationContext();
                InputStream inputStream = context.getAssets().open(fileName);
                ByteArrayOutputStream result = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) != -1) {
                    result.write(buffer, 0, length);
                }
                String script = result.toString("UTF-8");
                ScatterJsService.injectJs(webView.get(), script);
            }
        } catch (Exception e) {
            Log.d("TAG", "Some error with ScatterJs js file");
        }
    }

    @Override
    public void start() {
        loadJavaScript("scatter.js");
    }

    @Override
    public void destroy() {
        if (webView.get() != null) {
            webView.get().removeJavascriptInterface(javascriptInterfaceName);
            webView.clear();
        }
    }
}
