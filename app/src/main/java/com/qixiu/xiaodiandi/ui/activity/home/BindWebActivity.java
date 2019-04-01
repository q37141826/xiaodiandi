package com.qixiu.xiaodiandi.ui.activity.home;

import android.content.DialogInterface;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.qixiu.qixiu.request.OKHttpRequestModel;
import com.qixiu.qixiu.request.OKHttpUIUpdataListener;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.ArshowDialogUtils;
import com.qixiu.qixiu.utils.ToastUtil;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantRequest;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.constant.EventAction;
import com.qixiu.xiaodiandi.constant.IntentDataKeyConstant;
import com.qixiu.xiaodiandi.model.login.LoginStatus;
import com.qixiu.xiaodiandi.model.mine.QrUserMessageBean;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.BaseWebActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class BindWebActivity extends BaseWebActivity implements OKHttpUIUpdataListener<BaseBean> {
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.relativeLayout)
    LinearLayout relativeLayout;
    private String qrcode;
    OKHttpRequestModel okHttpRequestModel;

    @Override
    public WebView getWebView() {
        return webview;
    }


    @Override
    protected void onStart() {
        super.onStart();
        load(ConstantUrl.scanDetailsUrl);
    }

    @Override
    protected void onInitViewNew() {
        okHttpRequestModel = new OKHttpRequestModel(this);
        setTitle("我要成为VIP");
        qrcode = getIntent().getStringExtra(IntentDataKeyConstant.DATA);
        if (!TextUtils.isEmpty(qrcode)) {//1获取二维码用户信息，2扫码执行
            ConstantRequest.bindFirends(okHttpRequestModel, ConstantUrl.bindfriendsUrl, 1 + "", qrcode, new QrUserMessageBean());
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onInitData() {

    }


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_bind_web;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    public void gotoBeVip(View view) {
        EventAction.Action action = new EventAction.Action(EventAction.GOTO_TYPE);
        action.setId("");
        EventBus.getDefault().post(action);
    }


    @Override
    public void onSuccess(BaseBean data, int i) {
        if (data instanceof QrUserMessageBean) {
            QrUserMessageBean bean = (QrUserMessageBean) data;
            if (!LoginStatus.getId().equals(bean.getO().getUid() + "")) {
                ArshowDialogUtils.showDialog(getContext(), "确认合伙人\n" + bean.getO().getPhone() + "\n" + "合伙人电话", new ArshowDialogUtils.ArshowDialogListener() {
                    @Override
                    public void onClickPositive(DialogInterface dialogInterface, int which) {
                        ConstantRequest.bindFirends(okHttpRequestModel, ConstantUrl.bindfriendsUrl, 2 + "", qrcode, new QrUserMessageBean());
                    }

                    @Override
                    public void onClickNegative(DialogInterface dialogInterface, int which) {

                    }
                });
            } else {
                ToastUtil.toast("不能绑定自己");
            }

        }
        if (ConstantUrl.bindfriendsUrl.equals(data.getUrl()) && !(data instanceof QrUserMessageBean)) {
            ToastUtil.toast(data.getM());
        }
    }

    @Override
    public void onError(Call call, Exception e, int i) {

    }

    @Override
    public void onFailure(C_CodeBean c_codeBean) {
        ToastUtil.toast(c_codeBean.getM());
    }


    @Override
    public void webEvent(String methoed,String json) {
            gotoBeVip(null);
    }
}
