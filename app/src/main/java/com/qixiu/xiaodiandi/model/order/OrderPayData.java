package com.qixiu.xiaodiandi.model.order;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderPayData implements Parcelable {

    private String key;
    private String money;
    private String address;
    private String coupon;
    private String mark;
    private String integral;
    private String paytype;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public static Creator<OrderPayData> getCREATOR() {
        return CREATOR;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public OrderPayData() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.key);
        dest.writeString(this.money);
        dest.writeString(this.address);
        dest.writeString(this.coupon);
        dest.writeString(this.mark);
        dest.writeString(this.integral);
        dest.writeString(this.paytype);
    }

    protected OrderPayData(Parcel in) {
        this.key = in.readString();
        this.money = in.readString();
        this.address = in.readString();
        this.coupon = in.readString();
        this.mark = in.readString();
        this.integral = in.readString();
        this.paytype = in.readString();
    }

    public static final Creator<OrderPayData> CREATOR = new Creator<OrderPayData>() {
        @Override
        public OrderPayData createFromParcel(Parcel source) {
            return new OrderPayData(source);
        }

        @Override
        public OrderPayData[] newArray(int size) {
            return new OrderPayData[size];
        }
    };
}
