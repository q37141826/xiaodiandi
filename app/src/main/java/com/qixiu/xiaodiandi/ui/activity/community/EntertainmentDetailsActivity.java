package com.qixiu.xiaodiandi.ui.activity.community;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.DrawableUtils;
import com.qixiu.qixiu.utils.XrecyclerViewUtil;
import com.qixiu.wigit.VerticalSwipeRefreshLayout;
import com.qixiu.wigit.show_dialog.ArshowContextUtil;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;
import com.qixiu.xiaodiandi.ui.wigit.WritePop;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EntertainmentDetailsActivity extends RequestActivity implements XRecyclerView.LoadingListener {


    @BindView(R.id.xrecyclerView)
    XRecyclerView xrecyclerView;
    @BindView(R.id.swip_refreshlayout)
    VerticalSwipeRefreshLayout swipRefreshlayout;

    @Override
    protected void onInitData() {
        setTitle("娱乐社区");
        DrawableUtils.setLeftDrawableResouce(mTitleView.getRightText(), getContext(), R.mipmap.start_commit);
        mTitleView.setRightText("我要发布");
        mTitleView.getRightText().setBackgroundResource(R.drawable.shape_blue_btn_bg);
        mTitleView.getRightText().getLayoutParams().height = ArshowContextUtil.dp2px(25);
        mTitleView.getRightText().setTextSize(12);
        mTitleView.getRightText().setTextColor(Color.WHITE);
        mTitleView.getRightText().setCompoundDrawablePadding(10);
        mTitleView.getRightText().setGravity(Gravity.CENTER);
        mTitleView.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyPayedProductsActivity.start(getContext(), MyPayedProductsActivity.class);
            }
        });
        XrecyclerViewUtil.setXrecyclerView(xrecyclerView, this, this, false, 1, null);
        View headerView = View.inflate(getContext(), R.layout.header_entertainment_details, null);
        xrecyclerView.addHeaderView(headerView);
        findHeaderView(headerView);
        EntertainmentDetailsCommitsAdapter adapter = new EntertainmentDetailsCommitsAdapter();
        xrecyclerView.setAdapter(adapter);
        XrecyclerViewUtil.refreshFictiousData(adapter);
    }

    private void findHeaderView(View view) {
        ImageView imageViewStartCommit = view.findViewById(R.id.imageViewStartCommit);
        imageViewStartCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WritePop writePop = new WritePop(getContext());
                writePop.show();
                writePop.setSendListenner(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(writePop.getText())) {
                            return;
                        }
                    }
                });
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_entertainment_details;
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

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
