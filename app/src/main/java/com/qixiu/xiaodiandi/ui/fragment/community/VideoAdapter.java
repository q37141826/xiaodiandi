package com.qixiu.xiaodiandi.ui.fragment.community;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapterMoreTypes;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.model.comminity.news.NewsHomeBean;

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
        ImageView imageView;
        TextView textView;

        public Holder01(View itemView, Context context, RecyclerView.Adapter adapter) {
            super(itemView, context, adapter);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }

        @Override
        public void bindHolder(int position) {
            if (mData instanceof NewsHomeBean.OBean.VideoBean) {
                NewsHomeBean.OBean.VideoBean bean = (NewsHomeBean.OBean.VideoBean) mData;
                Glide.with(mContext).load(bean.getImage_input()).into(imageView);
                textView.setText(bean.getTitle());
            }
        }
    }

    public class Holder02 extends RecyclerBaseHolder {
        ImageView imageView;
        TextView textViewTiltile, textViewTimes;

        public Holder02(View itemView, Context context, RecyclerView.Adapter adapter) {
            super(itemView, context, adapter);
            imageView = itemView.findViewById(R.id.imageView);
            textViewTiltile = itemView.findViewById(R.id.textViewTiltile);
            textViewTimes = itemView.findViewById(R.id.textViewTimes);
        }

        @Override
        public void bindHolder(int position) {
            if (mData instanceof NewsHomeBean.OBean.VideoBean) {
                NewsHomeBean.OBean.VideoBean bean = (NewsHomeBean.OBean.VideoBean) mData;
                Glide.with(mContext).load(bean.getImage_input()).into(imageView);
                textViewTiltile.setText(bean.getTitle());
                textViewTimes.setText("播放量：   " + bean.getVisit() + "次");
            }
        }
    }
}
