package com.qixiu.xiaodiandi.ui.adapter.home;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qixiu.qixiu.recyclerview_lib.OnRecyclerItemListener;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.model.home.goodsdetails.CharctorInnerBean;
import com.qixiu.xiaodiandi.model.home.goodsdetails.GoodsDetailsBean;

public class GoodsDetailsCharactorAdapter extends RecyclerBaseAdapter {


    @Override
    public int getLayoutId() {
        return R.layout.item_goods_details_charactor;
    }

    @Override
    public Object createViewHolder(View itemView, Context context, int viewType) {
        return new GoodsDetailsCharactorHolder(itemView, context, this);
    }

    public class GoodsDetailsCharactorHolder extends RecyclerBaseHolder {
        TextView textViewTitle;
        RecyclerView recyclerView;

        public GoodsDetailsCharactorHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
            super(itemView, context, adapter);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            recyclerView = itemView.findViewById(R.id.recyclerView);
        }

        @Override
        public void bindHolder(int position) {
            if (mData instanceof GoodsDetailsBean.OBean.ResultBean.CharcBean) {
                GoodsDetailsBean.OBean.ResultBean.CharcBean bean = (GoodsDetailsBean.OBean.ResultBean.CharcBean) mData;
                textViewTitle.setText(bean.getAttr_name());

                InnerAdapter adapter = new InnerAdapter();
                recyclerView.setAdapter(adapter);
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
//                linearLayoutManager.setOrientation(LinearLayout.HORIZONTAL);
                RecyclerView.LayoutManager layoutManager=new GridLayoutManager(mContext,4);
                recyclerView.setLayoutManager(layoutManager);

                adapter.refreshData(bean.getInners());
                adapter.setOnItemClickListener(new OnRecyclerItemListener() {
                    @Override
                    public void onItemClick(View v, RecyclerView.Adapter adapter, Object data) {
                        getClickListenner().click(v, data);

                        CharctorInnerBean innerBean = (CharctorInnerBean) data;
                        for (int i = 0; i < bean.getInners().size(); i++) {
                            bean.getInners().get(i).setSelected(false);
                            if (bean.getInners().get(i).getText().equals(innerBean.getText())) {
                                bean.getInners().get(i).setSelected(true);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }
    }


    public class InnerAdapter extends RecyclerBaseAdapter {
        @Override
        public int getLayoutId() {
            return R.layout.item_goods_charactor_inner;
        }

        @Override
        public Object createViewHolder(View itemView, Context context, int viewType) {
            return new InnerHolder(itemView, context, this);
        }

        public class InnerHolder extends RecyclerBaseHolder {
            TextView textView;

            public InnerHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
                super(itemView, context, adapter);
                textView = itemView.findViewById(R.id.textView);
            }

            @Override
            public void bindHolder(int position) {
                if (mData instanceof CharctorInnerBean) {
                    CharctorInnerBean bean = (CharctorInnerBean) mData;
                    if (bean.isSelected()) {
                        textView.setBackgroundResource(R.drawable.btn_theme_solid);
                        textView.setTextColor(mContext.getResources().getColor(R.color.white));
                    } else {
                        textView.setBackgroundResource(R.drawable.btn_grey_stroke_3dp);
                        textView.setTextColor(mContext.getResources().getColor(R.color.font_grey));
                    }
                    textView.setText(bean.getText());
                }
            }
        }
    }
}
