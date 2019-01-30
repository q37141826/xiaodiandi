package com.qixiu.xiaodiandi.ui.activity.home.confirm_order;

import android.content.Context;
import android.view.View;

import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.xiaodiandi.R;


/**
 * Created by HuiHeZe on 2017/9/15.
 */

public class ConfirmOrderAdapter extends RecyclerBaseAdapter {



    @Override
    public int getLayoutId() {
        return R.layout.item2_myorder_detais;
    }

    @Override
    public Object createViewHolder(View itemView, Context context, int viewType) {
        return new ConfirmGoodsHolder(itemView,context,this);
    }


}
