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

public class OrderDetailsHolder extends RecyclerBaseHolder<OrderDetailsBean.OBean.ItemsBean> {
    ImageView imageView_orderdetails_icon;
    TextView textView_goodsContent_orderdetails, textView_price_orderdetails, textView_count_orderdetails;

    public OrderDetailsHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
        super(itemView, context, adapter);
        imageView_orderdetails_icon= (ImageView) itemView.findViewById(R.id.imageView_orderdetails_icon);
        textView_goodsContent_orderdetails= (TextView) itemView.findViewById(R.id.textView_goodsContent_orderdetails);
        textView_price_orderdetails= (TextView) itemView.findViewById(R.id.textView_price_orderdetails);
        textView_count_orderdetails= (TextView) itemView.findViewById(R.id.textView_count_orderdetails);

    }

    @Override
    public void bindHolder(int position) {
        Glide.with(mContext).load(mData.getPic()).into(imageView_orderdetails_icon);
        textView_goodsContent_orderdetails.setText(mData.getName());
        textView_price_orderdetails.setText("¥ "+mData.getPrice());
        textView_count_orderdetails.setText("x "+mData.getNum());


    }
}
