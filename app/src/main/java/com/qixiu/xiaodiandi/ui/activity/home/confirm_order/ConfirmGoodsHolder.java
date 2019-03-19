package com.qixiu.xiaodiandi.ui.activity.home.confirm_order;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.model.home.goodsdetails.GoodsDetailsBean;
import com.qixiu.xiaodiandi.model.order.CreateOrderBean;

/**
 * Created by HuiHeZe on 2017/9/15.
 */

public class ConfirmGoodsHolder extends RecyclerBaseHolder {
    private ImageView imageView_orderIcon;
    private TextView textView_goodsPrice, textView_goodsCount, textView_goodsContent, textView_goodsName,textView_goodsChrac;

    public ConfirmGoodsHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
        super(itemView, context, adapter);
        imageView_orderIcon = (ImageView) itemView.findViewById(R.id.imageView_orderIcon);
        textView_goodsPrice = (TextView) itemView.findViewById(R.id.textView_goodsPrice);
        textView_goodsCount = (TextView) itemView.findViewById(R.id.textView_goodsCount);
        textView_goodsContent = (TextView) itemView.findViewById(R.id.textView_goodsContent);
        textView_goodsName = (TextView) itemView.findViewById(R.id.textView_goodsName);
        textView_goodsChrac = (TextView) itemView.findViewById(R.id.textView_goodsChrac);
    }

    @Override
    public void bindHolder(int position) {
        if (mData instanceof GoodsBeanFatherIntfer) {
            GoodsBeanFatherIntfer bean = (GoodsBeanFatherIntfer) mData;
            Glide.with(mContext).load(bean.getPic()).into(imageView_orderIcon);
            textView_goodsPrice.setText("¥  " + bean.getPrice());
            textView_goodsPrice.setTextColor(mContext.getResources().getColor(R.color.red));
            textView_goodsCount.setText("X  " + bean.getNum());
            textView_goodsContent.setText(bean.getName());

        }

        if (mData instanceof CreateOrderBean.OBean.CartInfoBean) {
            CreateOrderBean.OBean.CartInfoBean bean = (CreateOrderBean.OBean.CartInfoBean) mData;
            Glide.with(mContext).load(bean.getProductInfo().getImage()).into(imageView_orderIcon);
            textView_goodsPrice.setText("¥  " +CartPriceParse.getPrice(bean.getProductInfo()));
            textView_goodsPrice.setTextColor(mContext.getResources().getColor(R.color.red));
            textView_goodsCount.setText("X  " + +bean.getCart_num());
            textView_goodsContent.setText(bean.getProductInfo().getStore_name());
            textView_goodsChrac.setText(bean.getProductInfo().getAttrInfo().getSuk());
        }
        if(mData instanceof GoodsDetailsBean.OBean.ResultBean.ListBean){
            GoodsDetailsBean.OBean.ResultBean.ListBean bean= (GoodsDetailsBean.OBean.ResultBean.ListBean) mData;
            Glide.with(mContext).load(bean.getImage()).into(imageView_orderIcon);
            textView_goodsPrice.setText("¥  " + bean.getPrice());
            textView_goodsPrice.setTextColor(mContext.getResources().getColor(R.color.red));
            textView_goodsCount.setText("X  " + +bean.getNum());
            textView_goodsContent.setText(bean.getInfo());
            textView_goodsChrac.setText(bean.getSuk());

        }
    }

}
