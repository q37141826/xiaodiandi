package com.qixiu.xiaodiandi.ui.activity.community;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.qixiu.utils.XrecyclerViewUtil;
import com.qixiu.xiaodiandi.R;

/**
 * Created by my on 2019/1/15.
 */

public class EntertainmentDetailsCommitsAdapter extends RecyclerBaseAdapter {

    @Override
    public int getLayoutId() {
        return R.layout.item_entertaiment_commit;
    }

    @Override
    public Object createViewHolder(View itemView, Context context, int viewType) {
        return new EntertainmentDetailsCommitsHolder(itemView, context, this);
    }

    public class EntertainmentDetailsCommitsHolder extends RecyclerBaseHolder {
        RecyclerView recyclerView;

        public EntertainmentDetailsCommitsHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
            super(itemView, context, adapter);
            recyclerView = itemView.findViewById(R.id.recyclerView);
        }

        @Override
        public void bindHolder(int position) {
            EntertainmentDetailsCommitInnerAdapter adapter = new EntertainmentDetailsCommitInnerAdapter();
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            recyclerView.setAdapter(adapter);
            XrecyclerViewUtil.refreshFictiousData(adapter);
        }
    }
}
