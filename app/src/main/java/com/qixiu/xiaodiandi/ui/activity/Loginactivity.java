package com.qixiu.xiaodiandi.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;

import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.engine.PlatformLoginEngine;
import com.qixiu.xiaodiandi.engine.bean.UserInfo;
import com.qixiu.xiaodiandi.model.login.LoginBean;
import com.qixiu.xiaodiandi.model.login.LoginStatus;
import com.qixiu.xiaodiandi.services.version.ApkDownloadBean;
import com.qixiu.xiaodiandi.services.version.DownloadService;
import com.qixiu.xiaodiandi.services.version.VersionCheckUtil;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;
import com.qixiu.xiaodiandi.ui.wigit.ApkDownloadProgressPop;

import java.util.HashMap;
import java.util.Map;

import cn.sharesdk.wechat.friends.Wechat;

public class Loginactivity extends RequestActivity implements PlatformLoginEngine.PlatformResultListener {
    private PlatformLoginEngine engine;
    private BroadcastReceiver downLoadReceiver;
    private ApkDownloadProgressPop apkPop;

    @Override
    protected void onInitData() {
        engine = new PlatformLoginEngine(this);
        mTitleView.getView().setVisibility(View.GONE);
        VersionCheckUtil.checkVersion(getContext(), getActivity(), new VersionCheckUtil.IsNewVerSion() {
            @Override
            public void call(boolean isNew) {

            }
        });
        downLoadReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                showDownloadProgress(intent);
            }
        };
        IntentFilter intentFilter = new IntentFilter(DownloadService.APK_DOWLOAD_ACTION);
        registerReceiver(downLoadReceiver, intentFilter);
    }

    private void showDownloadProgress(Intent intent) {
        if(apkPop!=null){
            apkPop.show();
        }else {
            apkPop = new ApkDownloadProgressPop(getContext());
            apkPop.show();
        }
        ApkDownloadBean bean = intent.getParcelableExtra(DownloadService.APK_DOWNLOAD_DATA);
        apkPop.setProgress(bean.getDownloadSize(),bean.getTotalSize());
        apkPop.setTextProgress(bean.getDownloadSize(),bean.getTotalSize());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(downLoadReceiver);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_loginactivity;
    }

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof LoginBean) {
            LoginBean bean = (LoginBean) data;
            if (data.getUrl().equals(ConstantUrl.loginUrl)) {
                LoginStatus.saveState(bean);
                finish();
                MainActivity.start(getContext(), MainActivity.class);
            } else if (data.getUrl().equals(ConstantUrl.wxloginUrl)) {
                if (data.getC() == 2) {
                    PhoneLoginActivity.start(getContext(), PhoneLoginActivity.class, bean.getO());
                } else {
                    LoginStatus.saveState(bean);
                    MainActivity.start(getContext(), MainActivity.class);
                    finish();
                }
            }
        }
    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {

    }

    @Override
    protected void onInitViewNew() {

    }

    @Override
    public void onClick(View v) {

    }

    public void gotoPhoneLogin(View view) {
        PhoneLoginActivity.start(getContext(), PhoneLoginActivity.class);
    }

    //微信登录
    public void weichatLogin(View view) {
        engine.startAuthorize(Wechat.NAME);
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onSuccess(UserInfo userInfo) {
        Map<String, String> map = new HashMap();
        map.put("openid", userInfo.getUserId());
        map.put("nickname", userInfo.getUserName());
        map.put("avatar", userInfo.getUserIcon());
        map.put("device", deviceId);
        map.put("device_type", "1");//设备类型，1、安卓，2、IOS
        map.put("type", "1");//设备类型，1、安卓，2、IOS
        post(ConstantUrl.wxloginUrl, map, new LoginBean());
    }
}
