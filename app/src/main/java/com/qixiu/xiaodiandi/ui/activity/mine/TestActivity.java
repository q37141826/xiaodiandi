package com.qixiu.xiaodiandi.ui.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.model.login.LoginBean;
import com.qixiu.xiaodiandi.model.login.SendCodeBean;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends RequestActivity {
    Map<String, String> mapUidString = new HashMap<>();
    @BindView(R.id.edittextQr)
    TextView edittextQr;
    @BindView(R.id.edittextStart)
    EditText edittextStart;
    @BindView(R.id.edittextEnd)
    EditText edittextEnd;
    @BindView(R.id.edittextPhone)
    EditText edittextPhone;

    @Override
    protected void onInitData() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_test;
    }

    @Override
    public void onSuccess(BaseBean data) {
        if (data.getUrl().equals(ConstantUrl.loginUrl)) {
            LoginBean bean = (LoginBean) data;
        }
        if (data instanceof SendCodeBean) {
            SendCodeBean sendCodeBean = (SendCodeBean) data;
            startRegist(sendCodeBean);
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
    public void onClick(View view) {

    }

    //开始扫描
    public void startScan(View view) {

    }

    //批量注册
    public void register(View view) {
        sendsms();
    }

    //发送验证码
    private void sendsms() {

    }

    //开始注册
    private void startRegist(SendCodeBean sendCodeBean) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    public class LoginCurrentBean {
        String phone;
        String uid;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }

}
