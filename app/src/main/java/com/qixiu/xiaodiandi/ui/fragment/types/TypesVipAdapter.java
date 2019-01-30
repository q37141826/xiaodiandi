package com.qixiu.xiaodiandi.ui.fragment.types;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.model.types.TypesProductListBean;

/**
 * Created by my on 2019/1/9.
 */

public class TypesVipAdapter extends RecyclerBaseAdapter {

    boolean is_vip = false;

    public boolean isIs_vip() {
        return is_vip;
    }

    public void setIs_vip(boolean is_vip) {
        this.is_vip = is_vip;
    }

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

        public TypesVipHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
            super(itemView, context, adapter);
            recyclerView = itemView.findViewById(R.id.recyclerView);
            textTitle = itemView.findViewById(R.id.textTitle);
        }

        @Override
        public void bindHolder(int position) {
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
            TypeInnerAdapter adapter = new TypeInnerAdapter();
            recyclerView.setAdapter(adapter);
            if (mData instanceof TypesProductListBean.OBean.CategoryBean) {
                TypesProductListBean.OBean.CategoryBean bean = (TypesProductListBean.OBean.CategoryBean) mData;
                adapter.refreshData(bean.getProduct());
                textTitle.setText(bean.getCate_name());
            }
        }

    }


    public class TypeInnerAdapter extends RecyclerBaseAdapter {
        @Override
        public int getLayoutId() {
            if (is_vip) {
                return R.layout.item_types_inner;
            } else {
                return R.layout.item_types_inner02;
            }
        }

        @Override
        public Object createViewHolder(View itemView, Context context, int viewType) {
            return new TypeInnerHolder(itemView, context, this);
        }

        public class TypeInnerHolder extends RecyclerBaseHolder {
            ImageView imageView;
            TextView textViewName,
                    textViewPrice;
            /*上面是非VIP
             * */

            public TypeInnerHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
                super(itemView, context, adapter);
                if (is_vip) {

                } else {
                    imageView = itemView.findViewById(R.id.imageView);
                    textViewName = itemView.findViewById(R.id.textViewName);
                    textViewPrice = itemView.findViewById(R.id.textViewPrice);
                }
            }

            @Override
            public void bindHolder(int position) {
                if (is_vip) {

                } else {
                    if (mData instanceof TypesProductListBean.OBean.CategoryBean.ProductBean) {
                        TypesProductListBean.OBean.CategoryBean.ProductBean bean = (TypesProductListBean.OBean.CategoryBean.ProductBean) mData;
                        Glide.with(mContext).load(bean.getImage()).into(imageView);
                        textViewName.setText(bean.getStore_name());
                        textViewPrice.setText(bean.getPrice());
                    }
                }
            }
        }

    }

}
