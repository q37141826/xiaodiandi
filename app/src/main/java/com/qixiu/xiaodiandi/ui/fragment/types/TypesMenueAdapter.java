package com.qixiu.xiaodiandi.ui.fragment.types;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.model.types.ClassifyBean;

/**
 * Created by my on 2019/1/8.
 */

public class TypesMenueAdapter extends RecyclerBaseAdapter {

    @Override
    public int getLayoutId() {
        return R.layout.item_types_menue;
    }

    @Override
    public Object createViewHolder(View itemView, Context context, int viewType) {
        return new TypesHolder(itemView, context, this);
    }

    public class TypesHolder extends RecyclerBaseHolder {
        TextView textView;
       View viewLine;
        public TypesHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
            super(itemView, context, adapter);
            textView = itemView.findViewById(R.id.textView);
            viewLine = itemView.findViewById(R.id.viewLine);
        }

        @Override
        public void bindHolder(int position) {
            if (mData instanceof ClassifyBean.OBean) {
                ClassifyBean.OBean bean = (ClassifyBean.OBean) mData;
                textView.setText(bean.getCate_name());
                if(bean.isSelected()){
                    viewLine.setVisibility(View.VISIBLE);
                    textView.setTextColor(mContext.getResources().getColor(R.color.theme_color02));
                }else {
                    viewLine.setVisibility(View.INVISIBLE);
                    textView.setTextColor(mContext.getResources().getColor(R.color.font_grey));
                }
            }
        }
    }
}
