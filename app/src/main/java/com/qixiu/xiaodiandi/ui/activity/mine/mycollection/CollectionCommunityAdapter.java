package com.qixiu.xiaodiandi.ui.activity.mine.mycollection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.xiaodiandi.R;

/**
 * Created by my on 2019/1/14.
 */

public class CollectionCommunityAdapter extends RecyclerBaseAdapter {

    @Override
    public int getLayoutId() {
        return R.layout.item_collection_community;
    }

    @Override
    public Object createViewHolder(View itemView, Context context, int viewType) {
        return new CollectionCommunityHolder(itemView,context,this);
    }

    public class CollectionCommunityHolder extends RecyclerBaseHolder {

        public CollectionCommunityHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
            super(itemView, context, adapter);
        }

        @Override
        public void bindHolder(int position) {

        }
    }
}
