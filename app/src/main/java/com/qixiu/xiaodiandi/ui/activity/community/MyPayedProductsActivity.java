package com.qixiu.xiaodiandi.ui.activity.community;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.XrecyclerViewUtil;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyPayedProductsActivity extends RequestActivity {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

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
        setTitle("我已购买");
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        MyPayedAdapter adapter = new MyPayedAdapter();
        recyclerview.setAdapter(adapter);
        XrecyclerViewUtil.refreshFictiousData(adapter);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onInitData() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_my_payed_products;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public class MyPayedAdapter extends RecyclerBaseAdapter {
        @Override
        public int getLayoutId() {
            return R.layout.item_my_payed;
        }

        @Override
        public Object createViewHolder(View itemView, Context context, int viewType) {
            return new MyPayedHolder(itemView, context, this);
        }

        public class MyPayedHolder extends RecyclerBaseHolder {

            public MyPayedHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
                super(itemView, context, adapter);
            }

            @Override
            public void bindHolder(int position) {

            }
        }
    }

}
