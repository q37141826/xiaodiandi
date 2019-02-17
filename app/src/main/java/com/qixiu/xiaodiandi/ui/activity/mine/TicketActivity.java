package com.qixiu.xiaodiandi.ui.activity.mine;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.qixiu.qixiu.recyclerview_lib.OnRecyclerItemListener;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantString;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.model.mine.ticket.TicketListBean;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TicketActivity extends RequestActivity implements OnRecyclerItemListener {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swip_refreshlayout)
    SwipeRefreshLayout swipRefreshlayout;
    private TicketAdapter adapter;

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof TicketListBean) {
            TicketListBean bean = (TicketListBean) data;
            adapter.refreshData(bean.getO());
        }
        swipRefreshlayout.setRefreshing(false);
    }

    @Override
    public void onError(Exception e) {
        swipRefreshlayout.setRefreshing(false);
    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {
        swipRefreshlayout.setRefreshing(false);
    }

    @Override
    protected void onInitViewNew() {
        setTitle("我的优惠券");
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onInitData() {
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TicketAdapter();
        recyclerview.setAdapter(adapter);
//        XrecyclerViewUtil.refreshFictiousData(adapter);
        adapter.setOnItemClickListener(this);
        getData();
        swipRefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
    }

    private void getData() {
        post(ConstantUrl.ticketsUrl, null, new TicketListBean());

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_titcket;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onItemClick(View v, RecyclerView.Adapter adapter, Object data) {
        EventBus.getDefault().post(data);
    }

    public class TicketAdapter extends RecyclerBaseAdapter {

        @Override
        public int getLayoutId() {
            return R.layout.item_ticket;
        }

        @Override
        public Object createViewHolder(View itemView, Context context, int viewType) {
            return new TicketHolder(itemView, context, this);
        }

        public class TicketHolder extends RecyclerBaseHolder {
            TextView textViewMoney;
            TextView textViewHighFloor;
            TextView textViewLimit;
            TextView textViewUserRule;

            public TicketHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
                super(itemView, context, adapter);
                textViewMoney = itemView.findViewById(R.id.textViewMoney);
                textViewHighFloor = itemView.findViewById(R.id.textViewHighFloor);
                textViewLimit = itemView.findViewById(R.id.textViewLimit);
                textViewUserRule = itemView.findViewById(R.id.textViewUserRule);
            }

            @Override
            public void bindHolder(int position) {
                if (mData instanceof TicketListBean.OBean) {
                    TicketListBean.OBean bean = (TicketListBean.OBean) mData;
                    textViewHighFloor.setText(bean.getCoupon_title());
                    textViewMoney.setText(ConstantString.RMB_SYMBOL + bean.getCoupon_price() + "");
                    textViewLimit.setText("领取后" + bean.getUse_time() / 3600 / 24 / 1000 + "天内有效");
                    textViewUserRule.setText("满" + bean.getUse_min_price() + "元使用");
                }
            }
        }
    }
}
