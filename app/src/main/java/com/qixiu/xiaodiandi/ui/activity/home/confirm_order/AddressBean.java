package com.qixiu.xiaodiandi.ui.activity.home.confirm_order;


import android.os.Parcel;
import android.os.Parcelable;

import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

/**
 * Created by HuiHeZe on 2017/9/7.
 */

public class AddressBean  extends BaseBean<List<AddressBean.OBean>> {



    public static class OBean implements Parcelable {
        /**
         * id : 38
         * real_name : 陈
         * phone : 13512344321
         * province : 武汉市
         * city : 洪山区
         * district : 洪山区
         * detail : 家里蹲
         * is_default : 0
         */

        private String id;
        private String real_name;
        private String phone;
        private String province;
        private String city;
        private String district;
        private String detail;
        private String is_default;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.real_name);
            dest.writeString(this.phone);
            dest.writeString(this.province);
            dest.writeString(this.city);
            dest.writeString(this.district);
            dest.writeString(this.detail);
            dest.writeString(this.is_default);
        }

        public OBean() {
        }

        protected OBean(Parcel in) {
            this.id = in.readString();
            this.real_name = in.readString();
            this.phone = in.readString();
            this.province = in.readString();
            this.city = in.readString();
            this.district = in.readString();
            this.detail = in.readString();
            this.is_default = in.readString();
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
