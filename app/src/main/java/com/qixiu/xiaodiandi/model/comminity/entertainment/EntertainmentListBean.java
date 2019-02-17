package com.qixiu.xiaodiandi.model.comminity.entertainment;

import android.os.Parcel;
import android.os.Parcelable;

import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

public class EntertainmentListBean extends BaseBean< List<EntertainmentListBean.OBean> > {




    public static class OBean implements Parcelable {
        /**
         * id : 2
         * uid : 118
         * content : 哈哈哈
         * img :
         * user : {"nickname":"韩韩","avatar":"/public/uploads/2019-01-22/15481256887980.png"}
         */

        private int id;
        private int uid;
        private String content;
        private String img;
        private UserBean user;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean implements Parcelable {
            /**
             * nickname : 韩韩
             * avatar : /public/uploads/2019-01-22/15481256887980.png
             */

            private String nickname;
            private String avatar;

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.nickname);
                dest.writeString(this.avatar);
            }

            public UserBean() {
            }

            protected UserBean(Parcel in) {
                this.nickname = in.readString();
                this.avatar = in.readString();
            }

            public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
                @Override
                public UserBean createFromParcel(Parcel source) {
                    return new UserBean(source);
                }

                @Override
                public UserBean[] newArray(int size) {
                    return new UserBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeInt(this.uid);
            dest.writeString(this.content);
            dest.writeString(this.img);
            dest.writeParcelable(this.user, flags);
        }

        public OBean() {
        }

        protected OBean(Parcel in) {
            this.id = in.readInt();
            this.uid = in.readInt();
            this.content = in.readString();
            this.img = in.readString();
            this.user = in.readParcelable(UserBean.class.getClassLoader());
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
