package com.qixiu.xiaodiandi.ui.activity.mine;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.qixiu.qixiu.application.AppManager;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.MyTimer;
import com.qixiu.qixiu.utils.ToastUtil;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.model.login.SendCodeBean;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangePhoneStepTwoActivity extends RequestActivity {


    @BindView(R.id.ediitextPhone)
    EditText ediitextPhone;
    @BindView(R.id.ediitexVercode)
    EditText ediitexVercode;
    @BindView(R.id.btn_sendCode)
    Button btnSendCode;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    private String phone;
    private SendCodeBean sendCodeBean;
    private String vercode;

    @Override
    protected void onInitData() {
        setTitle("修改手机号码");
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_change_phone_step_two;
    }

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof SendCodeBean) {
            MyTimer myTimer = new MyTimer();
            myTimer.startTimeCountDown(btnSendCode);
            sendCodeBean = (SendCodeBean) data;
        }
        if (ConstantUrl.changePhoneUrl.equals(data.getUrl())) {
            ToastUtil.toast(data.getM());
            AppManager.getAppManager().finishActivity(ChangePhoneStepOneActivity.class);
            finish();
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void confirmChangePhone(View view) {
        phone = ediitextPhone.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.toast(R.string.edittext_login_input_id_input_rule_input_nothing);
            return;
        }
        if (sendCodeBean == null) {
            ToastUtil.toast("请先发送验证码");
            return;
        }
        vercode = ediitextPhone.getText().toString();
        if (TextUtils.isEmpty(vercode)) {
            ToastUtil.toast(R.string.edittext_register_input_verrycode_input_rule_input_nothing);
            return;
        }
        Map<String, String> map = new HashMap();
        map.put("phone", phone);
        map.put("verify", ediitexVercode.getText().toString().trim());
        map.put("type", "2");
        map.put("verify_id", sendCodeBean.getO().getVerify_id());
        post(ConstantUrl.changePhoneUrl, map, new BaseBean());
    }


    public void sendVercode(View view) {
        phone = ediitextPhone.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.toast(R.string.edittext_login_input_id_input_rule_input_nothing);
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("type", 2+"");
        post(ConstantUrl.sendCodeUrl, map, new SendCodeBean());
    }
}
