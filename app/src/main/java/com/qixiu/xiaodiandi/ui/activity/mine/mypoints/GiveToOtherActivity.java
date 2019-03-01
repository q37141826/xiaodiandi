package com.qixiu.xiaodiandi.ui.activity.mine.mypoints;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.CommonUtils;
import com.qixiu.qixiu.utils.ToastUtil;
import com.qixiu.wigit.myedittext.MyEditTextView;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GiveToOtherActivity extends RequestActivity {


    @BindView(R.id.myedittextPhone)
    MyEditTextView myedittextPhone;
    @BindView(R.id.myedittextPoints)
    MyEditTextView myedittextPoints;

    @Override
    protected void onInitData() {
        setTitle("积分转让");
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_give_to_other;
    }

    @Override
    public void onSuccess(BaseBean data) {
        if (data.getUrl().equals(ConstantUrl.transToOtherUrl)) {
            ToastUtil.toast(data.getM());
            finish();
        }
    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {
        if (c_codeBean.getUrl().equals(ConstantUrl.transToOtherUrl)) {
            ToastUtil.toast(c_codeBean.getM());
        }
    }

    @Override
    protected void onInitViewNew() {

    }

    @Override
    public void onClick(View v) {

    }


    //积分转让给别人
    public void comfirmGivePointsToOther(View view) {
        String phone = myedittextPhone.getText().toString();
        String points = myedittextPoints.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.toast(R.string.edittext_login_input_id_input_rule_input_nothing);
            return;
        }
        if (!CommonUtils.isMobileNO(phone)) {
            ToastUtil.toast(R.string.edittext_login_input_id_input_rule_input_illegal);
            return;
        }
        if (TextUtils.isEmpty(points)) {
            ToastUtil.toast("请输入需要转让的积分");
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("integral", points);
        post(ConstantUrl.transToOtherUrl, map, new BaseBean());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
