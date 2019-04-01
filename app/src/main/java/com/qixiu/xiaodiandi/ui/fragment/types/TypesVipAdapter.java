package com.qixiu.xiaodiandi.ui.fragment.types;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qixiu.qixiu.recyclerview_lib.OnRecyclerItemListener;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantString;
import com.qixiu.xiaodiandi.model.types.TypesProductListBean;
import com.qixiu.xiaodiandi.ui.activity.home.GoodsDetailsActivity;

/**
 * Created by my on 2019/1/9.
 */

public class TypesVipAdapter extends RecyclerBaseAdapter {
    @Override
    public int getLayoutId() {
        return R.layout.item_types_vip;
    }

    @Override
    public Object createViewHolder(View itemView, Context context, int viewType) {
        return new TypesVipHolder(itemView, context, this);
    }

    public class TypesVipHolder extends RecyclerBaseHolder {
        RecyclerView recyclerView;
        TextView textTitle;
        LinearLayout linearlayoutItem;
        ImageView imageViewHeader;

        public TypesVipHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
            super(itemView, context, adapter);
            recyclerView = itemView.findViewById(R.id.recyclerView);
            textTitle = itemView.findViewById(R.id.textTitle);
            linearlayoutItem = itemView.findViewById(R.id.linearlayoutItem);
            imageViewHeader = itemView.findViewById(R.id.imageViewHeader);
        }

        @Override
        public void bindHolder(int position) {
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
            RecyclerBaseAdapter adapter;
            adapter = new TypeInnerAdapter();
            recyclerView.setAdapter(adapter);
            if (mData instanceof TypesProductListBean.OBean.CategoryBean) {
                TypesProductListBean.OBean.CategoryBean bean = (TypesProductListBean.OBean.CategoryBean) mData;
                textTitle.setText(bean.getCate_name());
                if (bean.getProduct() != null) {//因为最后一个item数据是自己创建的，并没有这个产品部分
                    adapter.refreshData(bean.getProduct());
                }
                if (bean.getBannerBean() != null) {
                    if ("399元超级大礼包".equals(bean.getBannerBean().getTitle())) {
                        imageViewHeader.setVisibility(View.GONE);
                    } else {
                        imageViewHeader.setVisibility(View.VISIBLE);
                    }
                    Glide.with(mContext).load(bean.getBannerBean().getPic()).into(imageViewHeader);
                    imageViewHeader.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            if (bean.getBannerBean().getType().equals("1")) {
//                                GoodsDetailsActivity.start(mContext, GoodsDetailsActivity.class, bean.getBannerBean().getUrl());
//                            } else {
//                                GotoWebActivity.start(mContext, GotoWebActivity.class, bean.getBannerBean().getUrl());
//                            }
                        }
                    });
                } else {
                    imageViewHeader.setVisibility(View.GONE);
                }
            }
            adapter.setOnItemClickListener(new OnRecyclerItemListener() {
                @Override
                public void onItemClick(View v, RecyclerView.Adapter adapter, Object data) {
                    if (data != null && data instanceof TypesProductListBean.OBean.CategoryBean.ProductBean) {
                        TypesProductListBean.OBean.CategoryBean.ProductBean bean = (TypesProductListBean.OBean.CategoryBean.ProductBean) data;
                        GoodsDetailsActivity.start(mContext, GoodsDetailsActivity.class, bean.getId() + "");
                    }
                }
            });
        }

    }


    public class TypeInnerAdapter extends RecyclerBaseAdapter {
        @Override
        public int getLayoutId() {
            return R.layout.item_types_inner02;
        }

        @Override
        public Object createViewHolder(View itemView, Context context, int viewType) {
            return new TypeInnerHolder(itemView, context, this);
        }
    }


    public class TypeInnerHolder extends RecyclerBaseHolder {
        ImageView imageView;
        TextView textViewName,
                textViewPrice;

        public TypeInnerHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
            super(itemView, context, adapter);
            imageView = itemView.findViewById(R.id.imageView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);

        }

        @Override
        public void bindHolder(int position) {
            if (mData instanceof TypesProductListBean.OBean.CategoryBean.ProductBean) {
                TypesProductListBean.OBean.CategoryBean.ProductBean bean = (TypesProductListBean.OBean.CategoryBean.ProductBean) mData;
                Glide.with(mContext).load(bean.getImage()).into(imageView);
                textViewName.setText(bean.getStore_name());
                textViewPrice.setText(ConstantString.RMB_SYMBOL + bean.getPrice());
            }
        }
    }
}
