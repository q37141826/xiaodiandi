package com.qixiu.xiaodiandi.ui.fragment.mine.order;

import android.content.Context;
import android.view.View;

import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.xiaodiandi.R;


/**
 * Created by HuiHeZe on 2017/5/3.
 */

public class OrderDetailsAdapter extends RecyclerBaseAdapter<OrderDetailsBean.OBean.CartInfoBean,OrderDetailsHolder> {



    @Override
    public int getLayoutId() {
        return R.layout.item_orderdetails;
    }

    @Override
    public OrderDetailsHolder createViewHolder(View itemView, Context context, int viewType) {
        return new OrderDetailsHolder(itemView,context,this);
    }
}
