package com.qixiu.xiaodiandi.ui.fragment.mine.order;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.xiaodiandi.R;


/**
 * Created by HuiHeZe on 2017/5/3.
 */

public class MyOrderListDetailsHolder extends RecyclerBaseHolder<OrderBean.OBean.CartInfoBean> {
    ImageView imageView_orderIcon;
    TextView textView_goodsContent, textView_goodsPrice, textView_goodsCount,
            textView_goodsName, textView_goodsChrac;


    public MyOrderListDetailsHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
        super(itemView, context, adapter);
        imageView_orderIcon = (ImageView) itemView.findViewById(R.id.imageView_orderIcon);
        textView_goodsContent = (TextView) itemView.findViewById(R.id.textView_goodsContent);
        textView_goodsPrice = (TextView) itemView.findViewById(R.id.textView_goodsPrice);
        textView_goodsCount = (TextView) itemView.findViewById(R.id.textView_goodsCount);
        textView_goodsName = (TextView) itemView.findViewById(R.id.textView_goodsName);
        textView_goodsChrac = (TextView) itemView.findViewById(R.id.textView_goodsChrac);

    }

    @Override
    public void bindHolder(int position) {
        Glide.with(mContext).load(mData.getProductInfo().getImage()).error(R.mipmap.no_message2x).into(imageView_orderIcon);
        textView_goodsContent.setText(mData.getProductInfo().getStore_name());
        textView_goodsPrice.setText("¥  " + mData.getTruePrice());
        textView_goodsCount.setText("x  " + mData.getCart_num() + "");
        textView_goodsName.setVisibility(View.GONE);
        try {
            textView_goodsChrac.setText(mData.getProductInfo().getAttrInfo().getSuk());//有的商品没有attrInfo容易报错
        } catch (Exception e) {
        }
    }
}
