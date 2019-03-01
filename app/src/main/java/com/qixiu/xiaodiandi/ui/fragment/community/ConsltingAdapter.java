package com.qixiu.xiaodiandi.ui.fragment.community;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.model.comminity.news.NewsHomeBean;

/**
 * Created by my on 2019/1/14.
 */

public class ConsltingAdapter extends RecyclerBaseAdapter {


    @Override
    public int getLayoutId() {
        return R.layout.item_consulting;
    }

    @Override
    public Object createViewHolder(View itemView, Context context, int viewType) {
        return new ConsltingHolder(itemView, context, this);
    }

    public class ConsltingHolder extends RecyclerBaseHolder {
        ImageView imageView;
        TextView textViewTiltle,
                textViewTime;

        public ConsltingHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
            super(itemView, context, adapter);
            imageView = itemView.findViewById(R.id.imageView);
            textViewTiltle = itemView.findViewById(R.id.textViewTiltle);
            textViewTime = itemView.findViewById(R.id.textViewTime);
        }

        @Override
        public void bindHolder(int position) {
            if (mData instanceof NewsHomeBean.OBean.InforBean) {
                NewsHomeBean.OBean.InforBean bean = (NewsHomeBean.OBean.InforBean) mData;
                Glide.with(mContext).load(bean.getImage_input()).into(imageView);
                textViewTiltle.setText(bean.getTitle());
                textViewTime.setText(bean.getAdd_time());
            }
        }
    }
}
