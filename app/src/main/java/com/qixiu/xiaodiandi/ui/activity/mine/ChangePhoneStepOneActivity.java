package com.qixiu.xiaodiandi.ui.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.MyTimer;
import com.qixiu.qixiu.utils.ToastUtil;
import com.qixiu.wigit.GotoView;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.model.login.LoginStatus;
import com.qixiu.xiaodiandi.model.login.SendCodeBean;
import com.qixiu.xiaodiandi.model.mine.UserBean;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangePhoneStepOneActivity extends RequestActivity {


    @BindView(R.id.gotoViewPhone)
    GotoView gotoViewPhone;
    @BindView(R.id.ediitextVercode)
    EditText ediitextVercode;
    @BindView(R.id.btn_sendCode)
    Button btnSendCode;
    private String phone;
    private SendCodeBean sendCodeBean;

    @Override
    protected void onInitData() {
        setTitle("修改手机号码");
        getUserData();
    }

    private void getUserData() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", LoginStatus.getId());
        post(ConstantUrl.mineCenter, map, new UserBean());
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_change_phone_step_one;
    }

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof UserBean) {
            UserBean userBean = (UserBean) data;
            gotoViewPhone.setSecondText(userBean.getO().getPhone());
            phone = userBean.getO().getPhone();
        }
        if (data instanceof SendCodeBean) {
            MyTimer myTimer = new MyTimer();
            myTimer.startTimeCountDown(btnSendCode);
            sendCodeBean = (SendCodeBean) data;
        }
        if (ConstantUrl.changePhoneUrl.equals(data.getUrl())) {
            ChangePhoneStepTwoActivity.start(getContext(), ChangePhoneStepTwoActivity.class);
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

    //下一步
    public void nextStep(View view) {
        if (sendCodeBean == null) {
            ToastUtil.toast("请先发送验证码");
            return;
        }
        Map<String, String> map = new HashMap();
        map.put("phone", phone);
        map.put("verify", ediitextVercode.getText().toString().trim());
        map.put("type", "1");
        map.put("verify_id", sendCodeBean.getO().getVerify_id());
        post(ConstantUrl.changePhoneUrl, map, new BaseBean());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void sendCode(View view) {
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("type", 2 + "");
        post(ConstantUrl.sendCodeUrl, map, new SendCodeBean());
    }


}
