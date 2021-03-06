package com.qixiu.xiaodiandi.ui.activity.community.entertainment;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.model.comminity.entertainment.EntertaimentDetailsBean;

/**
 * Created by my on 2019/1/15.
 */

public class EntertainmentDetailsCommitInnerAdapter extends RecyclerBaseAdapter {


    @Override
    public int getLayoutId() {
        return R.layout.item_entertainment_commit_inner;
    }

    @Override
    public Object createViewHolder(View itemView, Context context, int viewType) {
        return new InnerHolder(itemView, context, this);
    }

    public class InnerHolder extends RecyclerBaseHolder {
        TextView textViewInnerContent;

        public InnerHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
            super(itemView, context, adapter);
            textViewInnerContent = itemView.findViewById(R.id.textViewInnerContent);
        }

        @Override
        public void bindHolder(int position) {
            if (mData instanceof EntertaimentDetailsBean.EBean.ReplyBean) {
                EntertaimentDetailsBean.EBean.ReplyBean replyBean = (EntertaimentDetailsBean.EBean.ReplyBean) mData;
                SpannableString spannableString = new SpannableString("[回复]  " + replyBean.getContent());
                spannableString.setSpan(new ForegroundColorSpan(Color.GRAY), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), 4, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                textViewInnerContent.setText(spannableString);
            }
        }
    }
}
