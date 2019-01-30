package com.qixiu.xiaodiandi.ui.fragment.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.wigit.show_dialog.ArshowContextUtil;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.model.home.HomeBean;
import com.qixiu.xiaodiandi.model.mine.collection.SimilarBean;

/**
 * Created by my on 2019/1/8.
 */

public class HomeAdapter extends RecyclerBaseAdapter {

    @Override
    public int getLayoutId() {
        return R.layout.item_home;
    }

    @Override
    public Object createViewHolder(View itemView, Context context, int viewType) {
        return new HomeHolder(itemView, context, this);
    }

    public class HomeHolder extends RecyclerBaseHolder {
        ImageView imageView;
        TextView textViewName,
                textViewPrice;

        public HomeHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
            super(itemView, context, adapter);
            imageView = itemView.findViewById(R.id.imageView);
            textViewName = itemView.findViewById(R.id.textViewPhone);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
        }

        @Override
        public void bindHolder(int position) {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) itemView.getLayoutParams();
            if (position % 2 == 0) {
                layoutParams.leftMargin = ArshowContextUtil.dp2px(15);
            } else {
                layoutParams.rightMargin = ArshowContextUtil.dp2px(15);
            }

            if (mData instanceof HomeBean.OBean.ProductBean) {
                HomeBean.OBean.ProductBean bean = (HomeBean.OBean.ProductBean) mData;
                Glide.with(mContext).load(bean.getImage()).into(imageView);
                textViewName.setText(bean.getStore_name());
                textViewPrice.setText(bean.getPrice());
            }

            if (mData instanceof SimilarBean.OBean) {
                SimilarBean.OBean bean = (SimilarBean.OBean) mData;
                Glide.with(mContext).load(bean.getImage()).into(imageView);
                textViewName.setText(bean.getStore_name());
                textViewPrice.setText(bean.getPrice());
            }
        }
    }
}
