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

public class CompanyDynamicAdapter extends RecyclerBaseAdapter {
    @Override
    public int getLayoutId() {
        return R.layout.item_company_dynamic;
    }

    @Override
    public Object createViewHolder(View itemView, Context context, int viewType) {
        return new CompanyDynamicHolder(itemView, context, this);
    }

    public class CompanyDynamicHolder extends RecyclerBaseHolder {
        ImageView imageView;
        TextView textView;

        public CompanyDynamicHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
            super(itemView, context, adapter);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }

        @Override
        public void bindHolder(int position) {
            if (mData instanceof NewsHomeBean.OBean.DynamicBean) {
                NewsHomeBean.OBean.DynamicBean bean = (NewsHomeBean.OBean.DynamicBean) mData;
                Glide.with(mContext).load(bean.getImage_input()).into(imageView);
                textView.setText(bean.getTitle());
            }
        }
    }
}
