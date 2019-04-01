package com.qixiu.xiaodiandi.ui.activity.mine.mycollection;

import android.content.Context;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantString;
import com.qixiu.xiaodiandi.model.mine.collection.MyGoodsCollectionBean;

/**
 * Created by my on 2019/1/14.
 */

public class CollectionProductAdapter extends RecyclerBaseAdapter {


    @Override
    public int getLayoutId() {
        return R.layout.item_collection_product;
    }

    @Override
    public Object createViewHolder(View itemView, Context context, int viewType) {
        return new CollectionProductHolder(itemView, context, this);
    }

    public class CollectionProductHolder extends RecyclerBaseHolder {
        Button btnFindSame;

        ImageView imageViewIcon;
        TextView textViewName;
        TextView textViewInfo;
        TextView textViewPrice;

        public CollectionProductHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
            super(itemView, context, adapter);
            btnFindSame = itemView.findViewById(R.id.btnFindSame);
            imageViewIcon = itemView.findViewById(R.id.imageViewIcon);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewInfo = itemView.findViewById(R.id.textViewInfo);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
        }

        @Override
        public void bindHolder(int position) {
            if (mData instanceof MyGoodsCollectionBean.OBean) {
                MyGoodsCollectionBean.OBean bean = (MyGoodsCollectionBean.OBean) mData;
                Glide.with(mContext).load(bean.getImage()).into(imageViewIcon);
                textViewName.setText(bean.getStore_name());
                textViewInfo.setText(bean.getStore_info());
                textViewPrice.setText(ConstantString.RMB_SYMBOL + bean.getPrice());
            }

            btnFindSame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FindSameActivity.start(mContext, FindSameActivity.class, (Parcelable) mData);
                }
            });
        }
    }
}
