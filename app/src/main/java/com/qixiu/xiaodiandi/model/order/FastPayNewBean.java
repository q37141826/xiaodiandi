package com.qixiu.xiaodiandi.model.order;

import android.os.Parcel;
import android.os.Parcelable;

public class FastPayNewBean implements Parcelable {

    OrderPayData  orderPayData;
    GotoAddCartsData gotoAddCartsData;

    public OrderPayData getOrderPayData() {
        return orderPayData;
    }

    public void setOrderPayData(OrderPayData orderPayData) {
        this.orderPayData = orderPayData;
    }

    public GotoAddCartsData getGotoAddCartsData() {
        return gotoAddCartsData;
    }

    public void setGotoAddCartsData(GotoAddCartsData gotoAddCartsData) {
        this.gotoAddCartsData = gotoAddCartsData;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.orderPayData, flags);
        dest.writeParcelable(this.gotoAddCartsData, flags);
    }

    public FastPayNewBean() {
    }

    protected FastPayNewBean(Parcel in) {
        this.orderPayData = in.readParcelable(OrderPayData.class.getClassLoader());
        this.gotoAddCartsData = in.readParcelable(GotoAddCartsData.class.getClassLoader());
    }

    public static final Parcelable.Creator<FastPayNewBean> CREATOR = new Parcelable.Creator<FastPayNewBean>() {
        @Override
        public FastPayNewBean createFromParcel(Parcel source) {
            return new FastPayNewBean(source);
        }

        @Override
        public FastPayNewBean[] newArray(int size) {
            return new FastPayNewBean[size];
        }
    };
}
