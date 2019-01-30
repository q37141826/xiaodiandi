package com.qixiu.xiaodiandi.ui.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DtestActivity extends RequestActivity {


    @BindView(R.id.imageView)
    ImageView imageView;

    @Override
    protected void onInitData() {
//        imageView.setImageBitmap(ThreeDUtils.create3DImg(getContext()));
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_dtest;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
