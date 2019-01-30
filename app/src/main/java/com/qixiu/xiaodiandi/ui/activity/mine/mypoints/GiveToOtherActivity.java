package com.qixiu.xiaodiandi.ui.activity.mine.mypoints;

import android.os.Bundle;
import android.view.View;

import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.wigit.myedittext.MyEditTextView;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;

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


    //积分转让给别人
    public void comfirmGivePointsToOther(View view) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
