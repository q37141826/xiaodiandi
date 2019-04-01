package com.qixiu.xiaodiandi.ui.activity.baseactivity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.qixiu.qixiu.utils.camera.Logger;
import com.qixiu.wigit.zprogress.ZProgressHUD;


/**
 * Created by Long on 2017/1/5
 */
@SuppressLint("Registered")
public abstract class BaseWebActivity extends TitleActivity {
    ZProgressHUD zProgressHUD;

    private static final String TAG = "BaseWebActivity";

    private WebView webView;
    private String currentUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doAfterSetContent();
        webView = getWebView();
        setupWebView();
        zProgressHUD = new ZProgressHUD(this);
    }


    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    protected void setupWebView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setSupportZoom(false);
        webSettings.setDisplayZoomControls(false);
        //JsBridge
        webView.addJavascriptInterface(new JsBridge(), "JsInterface");
        webView.setWebChromeClient(chromeClient);
        webView.setWebViewClient(webViewClient);

    }


    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
    }

    @Override
    protected void onPause() {
        webView.onPause();
        super.onPause();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onDestroy() {
        webView.destroy();
        super.onDestroy();
    }

    protected void doAfterSetContent() {
    }

    public abstract WebView getWebView();

    /**
     * 加载网页
     *
     * @param url url
     */
    protected void load(String url) {
        webView.loadUrl(url);
    }


    //-----------------------------WebChromeClient-------------------------------------------

    protected boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        result.cancel();
        new AlertDialog.Builder(this)
                .setTitle("提醒")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
        return true;
    }

    //---------------------------------WebChromeClient---------------------------------------


    //---------------------------------WebViewClient---------------------------------------

    protected boolean shouldOverrideUrlLoading(WebView view, String url) {
        Logger.d(TAG, "shouldOverrideUrlLoading: " + url);
        if (url.startsWith("http")) {
            view.loadUrl(url);
            currentUrl = url;
        }
        return true;
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void onReceivedHttpError(WebView view, WebResourceRequest request,
                                       WebResourceResponse errorResponse) {
        Logger.d(TAG, "onReceivedHttpError: " + request.getUrl());
    }


    protected void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        Logger.d(TAG, "onReceivedError: " + "errorCode====" + errorCode + "--------failingUrl====" + failingUrl +
                "--------description ====" + description);

        /*String htmlData = "<html><body><div align=\"center\" >This is the description for the load fail : "
                + description + "</div></body></html>";
        view.loadData(htmlData, "text/html", "utf-8");*/

    }


    protected void onPageStarted(WebView view, String url, Bitmap favicon) {
        Logger.d(TAG, "onPageStarted: " + url);
    }


    protected void onPageFinished(WebView view, String url) {
        Logger.d(TAG, "onPageFinished: " + url);
        //refreshLayout.setRefreshing(false);
    }


    //---------------------------------WebViewClient---------------------------------------


    private final WebChromeClient chromeClient = new WebChromeClient() {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {

        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return BaseWebActivity.this.onJsAlert(view, url, message, result);
        }

    };

    private final WebViewClient webViewClient = new WebViewClient() {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return BaseWebActivity.this.shouldOverrideUrlLoading(view, url);
        }


        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest request,
                                        WebResourceResponse errorResponse) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                BaseWebActivity.this.onReceivedHttpError(view, request, errorResponse);
            }
        }


        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            BaseWebActivity.this.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            BaseWebActivity.this.onPageStarted(view, url, favicon);
            zProgressHUD.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            BaseWebActivity.this.onPageFinished(view, url);
            zProgressHUD.dismiss();
        }

    };

    @Override
    protected void onRestart() {
        super.onRestart();
        if (!TextUtils.isEmpty(currentUrl)) {
            webView.loadUrl(currentUrl);
        }
    }

    private class JsBridge {
        @JavascriptInterface
        public void pushCommonPage(String json) {
            webEvent(Thread.currentThread().getStackTrace()[1].getMethodName(),json);
        }
    }

    public abstract void webEvent(String methoed,String json);
}
