package com.qixiu.xiaodiandi.ui.fragment.mine.order;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qixiu.qixiu.recyclerview_lib.OnRecyclerItemListener;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.XrecyclerViewUtil;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.model.login.LoginStatus;
import com.qixiu.xiaodiandi.ui.activity.mine.order.OrderDetailsActivity;
import com.qixiu.xiaodiandi.ui.fragment.basefragment.base.RequestFragment;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by my on 2019/1/11.
 */

public class OrderFragment extends RequestFragment implements XRecyclerView.LoadingListener, OnRecyclerItemListener<OrderBean.OBean>, MyOrderAdapter.MyOrderRefreshListener {
    private String type;
    @BindView(R.id.recycleView_myorederall)
    XRecyclerView recycleViewMyorederall;
    @BindView(R.id.swiprefreshOrder)
    SwipeRefreshLayout swiprefreshOrder;
    @BindView(R.id.textView_order)
    TextView textViewOrder;
    Unbinder unbinder;
    MyOrderAdapter adapter;
    private int pageNo = 1, pageSize = 10;

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof OrderBean) {
            OrderBean bean = (OrderBean) data;
            if (pageNo == 1) {
                adapter.refreshData(bean.getO());
            } else {
                adapter.addDatas(bean.getO());
            }
        }
        swiprefreshOrder.setRefreshing(false);
        recycleViewMyorederall.loadMoreComplete();
    }

    @Override
    public void onError(Exception e) {
        swiprefreshOrder.setRefreshing(false);
        recycleViewMyorederall.loadMoreComplete();
    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {
        swiprefreshOrder.setRefreshing(false);
        recycleViewMyorederall.loadMoreComplete();
    }

    @Override
    protected void onInitData() {
        adapter = new MyOrderAdapter();
        adapter.setActivity(getActivity());
        XrecyclerViewUtil.setXrecyclerView(recycleViewMyorederall, this, getContext(), false, 2, null);
        recycleViewMyorederall.setAdapter(adapter);
        swiprefreshOrder.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNo = 1;
                getData();
            }
        });
        getData();
        adapter.setOnItemClickListener(this);
        adapter.setMyOrderRefreshListener(this);
    }

    private void getData() {
        Map<String, String> map = new HashMap();
        map.put("uid", LoginStatus.getId());
        map.put("type", type);
        map.put("pageNo", pageNo + "");
        map.put("pageSize", pageSize + "");
        post(ConstantUrl.myOrderListUrl, map, new OrderBean());
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_orderlist;
    }

    @Override
    public void moveToPosition(int position) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {
        pageNo = 1;
        getData();
    }

    @Override
    public void onLoadMore() {
        pageNo++;
        getData();
    }

    @Override
    public void onItemClick(View v, RecyclerView.Adapter adapter, OrderBean.OBean data) {
        OrderDetailsActivity.start(getContext(), OrderDetailsActivity.class, data.getId() + "");
    }

    @Override
    public void onOrderRefresh(OrderBean.OBean mdata, String action) {
        getData();
    }
}
