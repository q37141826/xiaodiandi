package com.qixiu.xiaodiandi.services.version;

import android.os.Parcel;
import android.os.Parcelable;

public class ApkDownloadBean implements Parcelable {
    private long totalSize;
    private long downloadSize;
    private int state;
    private Parcelable data;

    public Parcelable getData() {
        return data;
    }

    public void setData(Parcelable data) {
        this.data = data;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public long getDownloadSize() {
        return downloadSize;
    }

    public void setDownloadSize(long downloadSize) {
        this.downloadSize = downloadSize;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.totalSize);
        dest.writeLong(this.downloadSize);
        dest.writeInt(this.state);
        dest.writeParcelable(this.data, flags);
    }

    public ApkDownloadBean() {
    }

    protected ApkDownloadBean(Parcel in) {
        this.totalSize = in.readLong();
        this.downloadSize = in.readLong();
        this.state = in.readInt();
        this.data = in.readParcelable(Parcelable.class.getClassLoader());
    }

    public static final Parcelable.Creator<ApkDownloadBean> CREATOR = new Parcelable.Creator<ApkDownloadBean>() {
        @Override
        public ApkDownloadBean createFromParcel(Parcel source) {
            return new ApkDownloadBean(source);
        }

        @Override
        public ApkDownloadBean[] newArray(int size) {
            return new ApkDownloadBean[size];
        }
    };
}
