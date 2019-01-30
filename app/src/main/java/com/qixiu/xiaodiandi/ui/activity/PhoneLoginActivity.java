package com.qixiu.xiaodiandi.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.CommonUtils;
import com.qixiu.qixiu.utils.ToastUtil;
import com.qixiu.wigit.myedittext.MyEditTextView;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.model.login.LoginBean;
import com.qixiu.xiaodiandi.model.login.LoginStatus;
import com.qixiu.xiaodiandi.model.login.SendCodeBean;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhoneLoginActivity extends RequestActivity {


    @BindView(R.id.myedittextPhone)
    MyEditTextView myedittextPhone;
    @BindView(R.id.myedittextCode)
    MyEditTextView myedittextCode;
    @BindView(R.id.btn_sendCode)
    Button btnSendCode;
    private String phone;
    private String code;

    @Override
    protected void onInitData() {
        setTitle("手机号登录");
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_phone_login;
    }

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof SendCodeBean) {
            SendCodeBean sendCodeBean = (SendCodeBean) data;
            verify_id = sendCodeBean.getO().getVerify_id();
            startTimeCountDown(btnSendCode);
        }
        if (data instanceof LoginBean) {
            LoginBean loginBean = (LoginBean) data;
            LoginStatus.saveState(loginBean);
            MainActivity.start(getContext(), MainActivity.class);
            finish();
        }
    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {
        if (c_codeBean.getUrl().equals(ConstantUrl.sendCodeUrl)) {
            btnSendCode.setEnabled(true);
        }
    }

    @Override
    protected void onInitViewNew() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    //登录
    public void login(View view) {
        if (!getDataOk()) {
            return;
        }
        Map<String, String> map = new HashMap();
        map.put("phone", phone);
        map.put("verify", code);
        map.put("verify_id", verify_id);
        map.put("device", deviceId);
        map.put("device_type", 1 + "");//设备类型，1、安卓，2、IOS
        post(ConstantUrl.loginUrl, map, new LoginBean());
    }

    private boolean getDataOk() {
        if (!getPhoneCheck()) {
            return false;
        }
        if (TextUtils.isEmpty(verify_id)) {
            ToastUtil.toast(R.string.edittext_register_input_verrycode_input_rule_not_send);
            return false;
        }
        code = myedittextCode.getText().toString();
        if (TextUtils.isEmpty(code)) {
            ToastUtil.toast(R.string.edittext_register_input_verrycode_input_rule_input_nothing);
            return false;
        }
        return true;
    }

    private boolean getPhoneCheck() {
        phone = myedittextPhone.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.toast(R.string.edittext_login_input_id_input_rule_input_nothing);
            return false;
        }
        if (!CommonUtils.isMobileNO(phone)) {
            ToastUtil.toast(R.string.edittext_login_input_id_input_rule_input_illegal);
            return false;
        }
        return true;
    }


    //发送验证码
    public void sendCode(View view) {
        if (!getPhoneCheck()) {
            return;
        }
        Map<String, String> map = new HashMap();
        map.put("phone", phone);
        map.put("type", 1 + "");
        post(ConstantUrl.sendCodeUrl, map, new SendCodeBean());
        btnSendCode.setEnabled(false);
    }
}
