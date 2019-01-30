package com.qixiu.xiaodiandi.ui.fragment.mine.order;

import android.content.Context;
import android.view.View;

import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.xiaodiandi.R;


/**
 * Created by HuiHeZe on 2017/5/3.
 */

public class OrderListDetailsAdapter extends RecyclerBaseAdapter<OrderBean.OBean.CartInfoBean, MyOrderListDetailsHolder> {

    @Override
    public int getLayoutId() {
        return R.layout.item2_myorder_detais;
    }

    @Override
    public MyOrderListDetailsHolder createViewHolder(View itemView, Context context, int viewType) {
        return new MyOrderListDetailsHolder(itemView, context, this);
    }
}
