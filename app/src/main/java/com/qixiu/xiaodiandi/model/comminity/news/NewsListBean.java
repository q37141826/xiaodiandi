package com.qixiu.xiaodiandi.model.comminity.news;

/*
 * 公司动态和最新资讯都是这个
 * */

import android.os.Parcel;
import android.os.Parcelable;

import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

public class NewsListBean extends BaseBean<List<NewsListBean.OBean>> {

    public static class OBean implements Parcelable, NewsListInterf {
        /**
         * id : 156
         * title : ff
         * image_input : http://xdd.qixiuu.com/public/uploads/editor/20181203/s_5c04ce7d95575.png
         * visit : 0
         * add_time : 2019-02-18
         */
        private int id;
        private String title;
        private String image_input;
        private String visit;
        private String add_time;
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage_input() {
            return image_input;
        }

        public void setImage_input(String image_input) {
            this.image_input = image_input;
        }

        public String getVisit() {
            return visit;
        }

        public void setVisit(String visit) {
            this.visit = visit;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public OBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.title);
            dest.writeString(this.image_input);
            dest.writeString(this.visit);
            dest.writeString(this.add_time);
            dest.writeString(this.type);
        }

        protected OBean(Parcel in) {
            this.id = in.readInt();
            this.title = in.readString();
            this.image_input = in.readString();
            this.visit = in.readString();
            this.add_time = in.readString();
            this.type = in.readString();
        }

        public static final Creator<OBean> CREATOR = new Creator<OBean>() {
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
