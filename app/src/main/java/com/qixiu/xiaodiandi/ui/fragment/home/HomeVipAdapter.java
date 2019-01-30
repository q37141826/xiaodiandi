package com.qixiu.xiaodiandi.ui.fragment.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.model.home.HomeBean;

public class HomeVipAdapter extends RecyclerBaseAdapter {


    @Override
    public int getLayoutId() {
        return R.layout.item_home_vip;
    }

    @Override
    public Object createViewHolder(View itemView, Context context, int viewType) {
        return new HomeVipHolder(itemView, context, this);
    }

    public class HomeVipHolder extends RecyclerBaseHolder {
        ImageView imageView;
        TextView textViewTitle;

        public HomeVipHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
            super(itemView, context, adapter);
            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
        }

        @Override
        public void bindHolder(int position) {
            if (mData instanceof HomeBean.OBean.VipCategoryBean) {
                HomeBean.OBean.VipCategoryBean bean = (HomeBean.OBean.VipCategoryBean) mData;
                Glide.with(mContext).load(bean.getPic()).into(imageView);
                textViewTitle.setText(bean.getCate_name());
            }
        }
    }
}
