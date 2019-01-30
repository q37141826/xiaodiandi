package com.qixiu.xiaodiandi.ui.fragment.mine.order;

import android.content.Context;
import android.view.View;

import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.xiaodiandi.R;


/**
 * Created by HuiHeZe on 2017/5/3.
 */

public class MyOrderAdapter extends RecyclerBaseAdapter<OrderBean.OBean.ListBean, MyOrderHolder> {

    private MyOrderRefreshListener myOrderRefreshListener;

    @Override
    public int getLayoutId() {
        return R.layout.item1_myholder;
    }

    @Override
    public MyOrderHolder createViewHolder(View itemView, Context context, int viewType) {
        MyOrderHolder myOrderHolder = new MyOrderHolder(itemView, context, this);
        myOrderHolder.setMyOrderRefreshListener(myOrderRefreshListener);
        return myOrderHolder;
    }

    public void setMyOrderRefreshListener(MyOrderRefreshListener myOrderRefreshListener) {
        this.myOrderRefreshListener = myOrderRefreshListener;
    }

    public interface MyOrderRefreshListener {
        void onOrderRefresh(OrderBean.OBean.ListBean mdata, String action);
    }

}
