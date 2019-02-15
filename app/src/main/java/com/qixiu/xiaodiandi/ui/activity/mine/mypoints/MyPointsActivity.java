package com.qixiu.xiaodiandi.ui.activity.mine.mypoints;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.model.login.LoginStatus;
import com.qixiu.xiaodiandi.model.mine.points.PointsBean;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyPointsActivity extends RequestActivity {


    @BindView(R.id.textViewPointsAll)
    TextView textViewPointsAll;
    @BindView(R.id.textViewPointsCanGet)
    TextView textViewPointsCanGet;
    @BindView(R.id.textViewPointsToday)
    TextView textViewPointsToday;
    @BindView(R.id.textViewPointsMonth)
    TextView textViewPointsMonth;
    @BindView(R.id.textViewPointsGet)
    TextView textViewPointsGet;

    @Override
    protected void onInitData() {
        setTitle("点滴积分");
        mTitleView.setRightText("积分明细");
        mTitleView.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PointsRecordActivity.start(getContext(), PointsRecordActivity.class);
            }
        });
        getData();
    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", LoginStatus.getId());
        post(ConstantUrl.waterlistUrl, map, new PointsBean());

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_my_points;
    }

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof PointsBean) {
            PointsBean bean = (PointsBean) data;
            textViewPointsAll.setText(bean.getO().getIntegral() + "积分");
            textViewPointsCanGet.setText("可提现" + bean.getO().getRmd() + "");
            textViewPointsToday.setText(bean.getO().getToday() + "积分");
            textViewPointsMonth.setText(bean.getO().getMonth() + "积分");
            textViewPointsGet.setText(bean.getO().getAll() + "积分");
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


    //提现
    public void getCash(View view) {
        GetCashActivity.start(getContext(), GetCashActivity.class, textViewPointsCanGet.getText().toString());
    }

    //转让
    public void giveToOther(View view) {
        GiveToOtherActivity.start(getContext(), GiveToOtherActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    //提现明细
    public void getCashDetails(View view) {
        GetCashRecordActivity.start(getContext(),GetCashRecordActivity.class);
    }
}
