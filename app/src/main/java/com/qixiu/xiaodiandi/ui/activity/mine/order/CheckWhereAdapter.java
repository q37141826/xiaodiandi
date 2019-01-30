package com.qixiu.xiaodiandi.ui.activity.mine.order;

import android.content.Context;
import android.view.View;

import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.xiaodiandi.R;


/**
 * Created by HuiHeZe on 2017/5/24.
 */

public class CheckWhereAdapter extends RecyclerBaseAdapter<CheckWhereBean.OBean.DataBean,CheckWhereHolder> {

    @Override
    public int getLayoutId() {
        return R.layout.item_check_trance;
    }

    @Override
    public CheckWhereHolder createViewHolder(View itemView, Context context, int viewType) {
        return new CheckWhereHolder(itemView,context,this);
    }
}
