package top.andnux.scatterapp;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import top.andnux.scatter.Scatter;
import top.andnux.scatter.ScatterFactory;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private ProgressBar progressBar;
    private Scatter scatter;
    private SwipeRefreshLayout mRefreshLayout;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressbar);//进度条
        webView = findViewById(R.id.webview);
        mRefreshLayout = findViewById(R.id.swipeRefreshLayout);
//        webView.loadUrl("http://192.168.1.206:8080/mock-sites/eosjs2/");
//        webView.loadUrl("https://www.ggsplay.com/#/");//加载url
//        webView.loadUrl("https://www.eosx.io");//加载url
//        webView.loadUrl("http://192.168.1.185:9529");//加载url
//        webView.loadUrl("https://tp-lab.tokenpocket.pro/scatter-demo-eosjs2/index.html#/");//加载url
//        webView.loadUrl("http://beta.ggsplay.com:8001/#/");
//        webView.loadUrl("https://chain.pro/candybox");
//        webView.loadUrl("https://bloks.io/");
        webView.loadUrl("http://192.168.1.185:9529/#/");
//        webView.loadUrl("https://dice.one/");
//        webView.loadUrl("https://earnbet.io/");
//        webView.loadUrl("https://dappspinach.io/dapp/pc/dist/#/main/home");
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.reload();
            }
        });
        scatter = ScatterFactory.getScatter(webView, new MyScatterClient(), false);
        //设置两个Client，必须设置，本次其实没有用到两个Client的太多功能
        //可以看一下两个Client与webview之间的事件循环周期，增加断点调试
        webView.setWebChromeClient(webChromeClient);
        webView.setWebViewClient(webViewClient);

        //无关紧要的一些设置
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);//允许使用js
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//允许使用弹窗
        WebView.setWebContentsDebuggingEnabled(true);
        //设置缓存，不然会有奇怪bug
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheMaxSize(1024 * 1024 * 64);
    }

    private WebViewClient webViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {//页面加载完成
            //通过onPageFinished的事件，实现滚动条的移动，你们根据前端需求自行添加人性化交互
            progressBar.setVisibility(View.GONE);
            mRefreshLayout.setRefreshing(false);
            super.onPageFinished(view, url);
            scatter.start();
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {//页面开始加载
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }
    };

    //WebChromeClient主要辅助WebView处理Javascript的对话框、网站图标、网站title、加载进度等
    private WebChromeClient webChromeClient = new WebChromeClient() {
        //不支持js的alert弹窗，需要自己监听然后通过dialog弹窗
        @Override
        public boolean onJsAlert(WebView webView, String url, String message, JsResult result) {
            //可做可不做的一些拦截，具体可以参考麦子钱包的界面
            //网页的一些原生alert弹出就是通过此方法拦截的
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(webView.getContext());
            localBuilder.setMessage(message).setPositiveButton("确定", null);
            localBuilder.setCancelable(false);
            localBuilder.create().show();
            //注意:
            //必须要这一句代码:result.confirm()表示:
            //处理结果为确定状态同时唤醒WebCore线程
            //否则不能继续点击按钮
            result.confirm();
            return true;
        }

        //获取网页标题
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            Log.i("ty", "网页标题:" + title);
        }

        //加载进度回调
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            progressBar.setProgress(newProgress);
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i("ansen", "是否有上一个页面:" + webView.canGoBack());
        if (webView.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK) {//点击返回按钮的时候判断有没有上一页
            webView.goBack(); // goBack()表示返回webView的上一页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //资源释放，由于这里socket.setReuseAddr(true); 所以这里socket是否释放也无关紧要。
    @Override
    protected void onDestroy() {
        super.onDestroy();
        scatter.destroy();
        //释放资源
        webView.destroy();
        webView = null;
    }
}
