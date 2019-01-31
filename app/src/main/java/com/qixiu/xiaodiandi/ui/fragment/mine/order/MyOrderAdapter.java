package com.qixiu.xiaodiandi.ui.fragment.mine.order;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.xiaodiandi.R;


/**
 * Created by HuiHeZe on 2017/5/3.
 */

public class MyOrderAdapter extends RecyclerBaseAdapter<OrderBean.OBean, MyOrderHolder> {
    private Activity activity;
    private MyOrderRefreshListener myOrderRefreshListener;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item1_myholder;
    }

    @Override
    public MyOrderHolder createViewHolder(View itemView, Context context, int viewType) {
        MyOrderHolder myOrderHolder = new MyOrderHolder(itemView, context, this);
        myOrderHolder.setMyOrderRefreshListener(myOrderRefreshListener);
        myOrderHolder.setActivity(activity);
        return myOrderHolder;
    }

    public void setMyOrderRefreshListener(MyOrderRefreshListener myOrderRefreshListener) {
        this.myOrderRefreshListener = myOrderRefreshListener;
    }

    public interface MyOrderRefreshListener {
        void onOrderRefresh(OrderBean.OBean mdata, String action);
    }

}
