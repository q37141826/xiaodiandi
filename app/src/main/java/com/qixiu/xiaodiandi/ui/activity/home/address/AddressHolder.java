package com.qixiu.xiaodiandi.ui.activity.home.address;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantString;
import com.qixiu.xiaodiandi.ui.activity.home.confirm_order.AddressBean;


public class AddressHolder extends RecyclerBaseHolder<AddressBean.OBean> {
    private TextView textView_name_address, textView_phone_address, textView_address_content;
    private ImageView imageView_edite_right, imageView_defualt_address;
    private AddressListAdapter.DataListenner listenner;

    public void setListenner(AddressListAdapter.DataListenner listenner) {
        this.listenner = listenner;
    }

    public AddressHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
        super(itemView, context, adapter);
        textView_name_address = (TextView) itemView.findViewById(R.id.textView_name_address);
        textView_phone_address = (TextView) itemView.findViewById(R.id.textView_phone_address);
        textView_address_content = (TextView) itemView.findViewById(R.id.textView_address_content);
        imageView_edite_right = (ImageView) itemView.findViewById(R.id.imageView_edite_right);
        imageView_defualt_address = (ImageView) itemView.findViewById(R.id.imageView_defualt_address);
    }

    @Override
    public void bindHolder(int position) {

        textView_name_address.setText("姓名：" + mData.getReal_name());
        textView_phone_address.setText("电话：" + mData.getPhone());
        textView_address_content.setText("地址：" + mData.getDetail());
        imageView_edite_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenner.sendData(mData, ConstantString.GOTO_EDIT_ADDRESS);
            }
        });
        if (mData.getIs_default().equals("1")) {
            imageView_defualt_address.setImageResource(R.mipmap.shopcar_goods_select);
            imageView_defualt_address.setEnabled(false);
        } else {
            imageView_defualt_address.setImageResource(R.mipmap.shopcar_goods_notselect);
            imageView_defualt_address.setEnabled(true);
        }
        imageView_defualt_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenner.sendData(mData, ConstantString.ACTION_REFRSH);
            }
        });

    }
}