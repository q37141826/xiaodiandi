package com.qixiu.xiaodiandi.ui.activity.community.news;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.model.comminity.news.ConsultingDetailsBean;
import com.qixiu.xiaodiandi.model.comminity.news.NewsDetailsBean;
import com.qixiu.xiaodiandi.utils.ImageUrlUtils;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentAdapter extends RecyclerBaseAdapter {
    @Override
    public int getLayoutId() {
        return R.layout.item_comments_news;
    }

    @Override
    public Object createViewHolder(View itemView, Context context, int viewType) {
        return new CommentsHolder(itemView, context, this);
    }

    public class CommentsHolder extends RecyclerBaseHolder {
        CircleImageView circularHead;
        TextView textViewPhone;
        TextView textViewTime;
        TextView textViewContent;

        public CommentsHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
            super(itemView, context, adapter);
            circularHead = itemView.findViewById(R.id.circularHead);
            textViewPhone = itemView.findViewById(R.id.textViewPhone);
            textViewTime = itemView.findViewById(R.id.textViewTime);
            textViewContent = itemView.findViewById(R.id.textViewContent);
        }

        @Override
        public void bindHolder(int position) {
            if (mData instanceof NewsDetailsBean.EBean) {
                NewsDetailsBean.EBean bean = (NewsDetailsBean.EBean) mData;
                Glide.with(mContext).load(ImageUrlUtils.getFinnalImageUrl(bean.getUser().getAvatar())).into(circularHead);
                textViewContent.setText(bean.getContent());
                textViewTime.setText(bean.getAddtime());
                textViewPhone.setText(bean.getUser().getNickname());
            }
            if (mData instanceof ConsultingDetailsBean.EBean) {
                ConsultingDetailsBean.EBean bean = (ConsultingDetailsBean.EBean) mData;
                Glide.with(mContext).load(ImageUrlUtils.getFinnalImageUrl(bean.getUser().getAvatar())).into(circularHead);
                textViewContent.setText(bean.getContent());
                textViewTime.setText(bean.getAddtime());
                textViewPhone.setText(bean.getUser().getNickname());
            }
        }
    }
}

