package com.qixiu.xiaodiandi.ui.activity;

import android.view.View;

import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.engine.PlatformLoginEngine;
import com.qixiu.xiaodiandi.engine.bean.UserInfo;
import com.qixiu.xiaodiandi.model.login.LoginBean;
import com.qixiu.xiaodiandi.model.login.LoginStatus;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;

import java.util.HashMap;
import java.util.Map;

import cn.sharesdk.wechat.friends.Wechat;

public class Loginactivity extends RequestActivity implements PlatformLoginEngine.PlatformResultListener {
    private PlatformLoginEngine engine;

    @Override
    protected void onInitData() {
        engine = new PlatformLoginEngine(this);
        mTitleView.getView().setVisibility(View.GONE);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_loginactivity;
    }

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof LoginBean) {
            LoginBean bean = (LoginBean) data;
            LoginStatus.saveState(bean);
            Map<String, String> map = new HashMap<>();
            map.put("id", 90 + "");
            map.put("uid", LoginStatus.getId() + "");
            post(ConstantUrl.getTicketUrl, map, new BaseBean());
        }
        if(ConstantUrl.getTicketUrl.equals(data.getUrl())){
            finish();
        }
    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {
        if(ConstantUrl.getTicketUrl.equals(c_codeBean.getUrl())){
            finish();
        }
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
        post(ConstantUrl.wxloginUrl, map, new LoginBean());
    }
}
