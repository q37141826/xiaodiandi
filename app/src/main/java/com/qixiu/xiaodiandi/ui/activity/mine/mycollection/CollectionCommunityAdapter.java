package com.qixiu.xiaodiandi.ui.activity.mine.mycollection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.model.comminity.entertainment.EntertainmentListBean;
import com.qixiu.xiaodiandi.model.mine.collection.EntertainmentCollectionBean;
import com.qixiu.xiaodiandi.utils.ImageUrlUtils;

import de.hdodenhof.circleimageview.CircleImageView;

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
        return new CollectionCommunityHolder(itemView, context, this);
    }

    public class CollectionCommunityHolder extends RecyclerBaseHolder {

        ImageView imageView;
        TextView textViewInfo;
        CircleImageView circularHead;
        TextView textViewName;

        public CollectionCommunityHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
            super(itemView, context, adapter);
            imageView = itemView.findViewById(R.id.imageView);
            textViewInfo = itemView.findViewById(R.id.textViewInfo);
            circularHead = itemView.findViewById(R.id.circularHead);
            textViewName = itemView.findViewById(R.id.textViewName);
        }

        @Override
        public void bindHolder(int position) {
            if (mData instanceof EntertainmentCollectionBean.OBean) {
                EntertainmentCollectionBean.OBean bean = (EntertainmentCollectionBean.OBean) mData;
                Glide.with(mContext).load(bean.getRelease().getImg()).into(imageView);
                Glide.with(mContext).load(ImageUrlUtils.getFinnalImageUrl(bean.getRelease().getUser().getAvatar())).into(circularHead);
                textViewInfo.setText(bean.getRelease().getContent());
                textViewName.setText(bean.getRelease().getUser().getNickname());
            }
            if (mData instanceof EntertainmentListBean.OBean) {
                EntertainmentListBean.OBean bean = (EntertainmentListBean.OBean) mData;
                Glide.with(mContext).load(bean.getImg()).into(imageView);
                Glide.with(mContext).load(ImageUrlUtils.getFinnalImageUrl(bean.getUser().getAvatar())).into(circularHead);
                textViewInfo.setText(bean.getContent());
                textViewName.setText(TextUtils.isEmpty(bean.getUser().getNickname()) ? bean.getUser().getNickname() : bean.getUser().getPhone());
            }
        }
    }
}
