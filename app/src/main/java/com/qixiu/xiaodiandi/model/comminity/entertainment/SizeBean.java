package com.qixiu.xiaodiandi.model.comminity.entertainment;

import android.os.Parcel;
import android.os.Parcelable;

public  class SizeBean implements Parcelable {
            /**
             * width : 1440
             * height : 1080
             */

            private int width;
            private int height;

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.width);
        dest.writeInt(this.height);
    }

    public SizeBean() {
    }

    protected SizeBean(Parcel in) {
        this.width = in.readInt();
        this.height = in.readInt();
    }

    public static final Parcelable.Creator<SizeBean> CREATOR = new Parcelable.Creator<SizeBean>() {
        @Override
        public SizeBean createFromParcel(Parcel source) {
            return new SizeBean(source);
        }

        @Override
        public SizeBean[] newArray(int size) {
            return new SizeBean[size];
        }
    };
}