package com.qixiu.xiaodiandi.ui.activity.mine;

import android.view.View;

import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;

public class ChangePhoneStepOneActivity extends RequestActivity {


    @Override
    protected void onInitData() {
        setTitle("修改手机号码");
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_change_phone_step_one;
    }

    @Override
    public void onSuccess(BaseBean data) {

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
        ChangePhoneStepTwoActivity.start(getContext(), ChangePhoneStepTwoActivity.class);
    }
}
