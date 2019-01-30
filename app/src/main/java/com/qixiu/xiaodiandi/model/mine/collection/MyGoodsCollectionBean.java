package com.qixiu.xiaodiandi.model.mine.collection;

import android.os.Parcel;
import android.os.Parcelable;

import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

public class MyGoodsCollectionBean extends BaseBean< List<MyGoodsCollectionBean.OBean>> {




    public static class OBean implements Parcelable {
        /**
         * id : 693
         * image : https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04aa00c4daf.jpg
         * store_name : 云南大理冬桃 精选果礼品装4.5斤约12枚
         * store_info :
         * price : 59.00
         * cate_id : 63
         */

        private int id;
        private String image;
        private String store_name;
        private String store_info;
        private String price;
        private String cate_id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getStore_info() {
            return store_info;
        }

        public void setStore_info(String store_info) {
            this.store_info = store_info;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.image);
            dest.writeString(this.store_name);
            dest.writeString(this.store_info);
            dest.writeString(this.price);
            dest.writeString(this.cate_id);
        }

        public OBean() {
        }

        protected OBean(Parcel in) {
            this.id = in.readInt();
            this.image = in.readString();
            this.store_name = in.readString();
            this.store_info = in.readString();
            this.price = in.readString();
            this.cate_id = in.readString();
        }

        public static final Parcelable.Creator<OBean> CREATOR = new Parcelable.Creator<OBean>() {
            @Override
            public OBean createFromParcel(Parcel source) {
                return new OBean(source);
            }

            @Override
            public OBean[] newArray(int size) {
                return new OBean[size];
            }
        };
    }
}
