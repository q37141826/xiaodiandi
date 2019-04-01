package com.qixiu.xiaodiandi.ui.activity.community.entertainment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.model.comminity.entertainment.EntertaimentDetailsBean;
import com.qixiu.xiaodiandi.utils.GlideUtils;
import com.qixiu.xiaodiandi.utils.ImageUrlUtils;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by my on 2019/1/15.
 */

public class EntertainmentDetailsCommitsAdapter extends RecyclerBaseAdapter {


    @Override
    public int getLayoutId() {
        return R.layout.item_entertaiment_commit;
    }

    @Override
    public Object createViewHolder(View itemView, Context context, int viewType) {
        return new EntertainmentDetailsCommitsHolder(itemView, context, this);
    }

    public class EntertainmentDetailsCommitsHolder extends RecyclerBaseHolder {
        CircleImageView circularHead;
        TextView textViewPhone;
        TextView textViewTime;
        TextView textViewBack;
        TextView textViewContent;
        RecyclerView recyclerView;

        public EntertainmentDetailsCommitsHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
            super(itemView, context, adapter);
            recyclerView = itemView.findViewById(R.id.recyclerView);
            circularHead = itemView.findViewById(R.id.circularHead);
            textViewPhone = itemView.findViewById(R.id.textViewPhone);
            textViewTime = itemView.findViewById(R.id.textViewTime);
            textViewBack = itemView.findViewById(R.id.textViewBack);
            textViewContent = itemView.findViewById(R.id.textViewContent);
        }

        @Override
        public void bindHolder(int position) {
            EntertainmentDetailsCommitInnerAdapter adapter = new EntertainmentDetailsCommitInnerAdapter();
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            recyclerView.setAdapter(adapter);
            if (mData instanceof EntertaimentDetailsBean.EBean) {
                EntertaimentDetailsBean.EBean bean = (EntertaimentDetailsBean.EBean) mData;
                adapter.refreshData(bean.getReply());
                textViewBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getClickListenner().click(view, mData);
                    }
                });
                textViewContent.setText(bean.getContent());
                textViewPhone.setText(bean.getUser().getPhone());
                textViewTime.setText(bean.getAddtime());
                GlideUtils.loadImage(ImageUrlUtils.getFinnalImageUrl(bean.getUser().getAvatar()), circularHead, mContext);
            }
        }
    }
}
