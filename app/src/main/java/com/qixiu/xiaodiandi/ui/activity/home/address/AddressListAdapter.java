package com.qixiu.xiaodiandi.ui.activity.home.address;

import android.content.Context;
import android.view.View;

import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.ui.activity.home.confirm_order.AddressBean;


public class AddressListAdapter extends RecyclerBaseAdapter<AddressBean.OBean, AddressHolder> {
    private DataListenner listenner;

    @Override
    public int getLayoutId() {
        return R.layout.item_address;
    }

    @Override
    public AddressHolder createViewHolder(View itemView, Context context, int viewType) {
        AddressHolder holder = new AddressHolder(itemView, context, this);
        holder.setListenner(getListenner());
        return holder;
    }

    public void setListenner(DataListenner listenner) {
        this.listenner = listenner;
    }

    public DataListenner getListenner() {
        return listenner;
    }

    public interface DataListenner {
        void sendData(AddressBean.OBean bean, String action);
    }

}