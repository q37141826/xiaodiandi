package com.qixiu.xiaodiandi.ui.activity.baseactivity;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;

import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.EventAction;
import com.qixiu.xiaodiandi.constant.IntentDataKeyConstant;

import org.greenrobot.eventbus.EventBus;

public class GotoWebActivity extends BaseWebActivity {
    WebView webView;
    String url;

    @Override
    protected void onInitData() {
        setTitle("详情");
        url = getIntent().getStringExtra(IntentDataKeyConstant.DATA);
    }

    @Override
    protected void onStart() {
        super.onStart();
        load(url);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_goto_web;
    }

    @Override
    public WebView getWebView() {
        return webView;
    }

    @Override
    protected void onInitViewNew() {
        webView = findViewById(R.id.webview);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



}
