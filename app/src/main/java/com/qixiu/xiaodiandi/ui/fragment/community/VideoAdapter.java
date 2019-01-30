package com.qixiu.xiaodiandi.ui.fragment.community;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapterMoreTypes;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;

import java.util.List;

/**
 * Created by my on 2019/1/14.
 */

public class VideoAdapter extends RecyclerBaseAdapterMoreTypes {

    public VideoAdapter(List datas) {
        super(datas);
    }

    @Override
    public Object createViewHolder(View itemView, Context context, int viewType) {
        if (viewType == 0) {
            return new Holder01(itemView, context, this);
        } else {
            return new Holder02(itemView, context, this);
        }
    }


    public class Holder01 extends RecyclerBaseHolder {

        public Holder01(View itemView, Context context, RecyclerView.Adapter adapter) {
            super(itemView, context, adapter);
        }

        @Override
        public void bindHolder(int position) {

        }
    }

    public class Holder02 extends RecyclerBaseHolder {

        public Holder02(View itemView, Context context, RecyclerView.Adapter adapter) {
            super(itemView, context, adapter);
        }

        @Override
        public void bindHolder(int position) {

        }
    }
}
