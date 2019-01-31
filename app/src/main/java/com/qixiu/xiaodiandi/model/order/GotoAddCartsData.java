package com.qixiu.xiaodiandi.model.order;

import android.os.Parcel;
import android.os.Parcelable;

import com.qixiu.xiaodiandi.model.home.goodsdetails.GoodsDetailsBean;

public class GotoAddCartsData implements Parcelable {

    private String prodeuctId;
    private String buyNum;
    private String unique;
    private String money;
    private GoodsDetailsBean.OBean.ResultBean.ListBean listBean;

    public GoodsDetailsBean.OBean.ResultBean.ListBean getListBean() {
        return listBean;
    }

    public void setListBean(GoodsDetailsBean.OBean.ResultBean.ListBean listBean) {
        this.listBean = listBean;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getProdeuctId() {
        return prodeuctId;
    }

    public void setProdeuctId(String prodeuctId) {
        this.prodeuctId = prodeuctId;
    }

    public String getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(String buyNum) {
        this.buyNum = buyNum;
    }

    public String getUnique() {
        return unique;
    }

    public void setUnique(String unique) {
        this.unique = unique;
    }

    public GotoAddCartsData() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.prodeuctId);
        dest.writeString(this.buyNum);
        dest.writeString(this.unique);
        dest.writeString(this.money);
        dest.writeParcelable(this.listBean, flags);
    }

    protected GotoAddCartsData(Parcel in) {
        this.prodeuctId = in.readString();
        this.buyNum = in.readString();
        this.unique = in.readString();
        this.money = in.readString();
        this.listBean = in.readParcelable(GoodsDetailsBean.OBean.ResultBean.ListBean.class.getClassLoader());
    }

    public static final Creator<GotoAddCartsData> CREATOR = new Creator<GotoAddCartsData>() {
        @Override
        public GotoAddCartsData createFromParcel(Parcel source) {
            return new GotoAddCartsData(source);
        }

        @Override
        public GotoAddCartsData[] newArray(int size) {
            return new GotoAddCartsData[size];
        }
    };
}
