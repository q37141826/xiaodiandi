package com.qixiu.xiaodiandi.model.comminity.entertainment;

import android.os.Parcel;

import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.xiaodiandi.model.IdInterfer;

import java.util.List;

//
public class EntertainmentListBean extends BaseBean< List<EntertainmentListBean.OBean>> {



    public static class OBean implements IdInterfer {
        /**
         * id : 13
         * uid : 181
         * content : 哈哈哈哈哈
         * type : 2
         * img : http://tz1.oss-cn-shanghai.aliyuncs.com/Upload/vedio/2019-02-20recording434113518.mp4
         * user : {"nickname":"","avatar":"http://xdd.qixiuu.com/public/uploads/2019-02-20/15506262655468.png","phone":"13554540382"}
         */

        private String id;
        private int uid;
        private String content;
        private int type;
        private String img;
        private UserBean user;

        public String getId() {
            return id;
        }

        public void setId(String id) {
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

        public static class UserBean implements android.os.Parcelable {
            /**
             * nickname :
             * avatar : http://xdd.qixiuu.com/public/uploads/2019-02-20/15506262655468.png
             * phone : 13554540382
             */

            private String nickname;
            private String avatar;
            private String phone;

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

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.nickname);
                dest.writeString(this.avatar);
                dest.writeString(this.phone);
            }

            public UserBean() {
            }

            protected UserBean(Parcel in) {
                this.nickname = in.readString();
                this.avatar = in.readString();
                this.phone = in.readString();
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
            dest.writeString(this.id);
            dest.writeInt(this.uid);
            dest.writeString(this.content);
            dest.writeInt(this.type);
            dest.writeString(this.img);
            dest.writeParcelable(this.user, flags);
        }

        public OBean() {
        }

        protected OBean(Parcel in) {
            this.id = in.readString();
            this.uid = in.readInt();
            this.content = in.readString();
            this.type = in.readInt();
            this.img = in.readString();
            this.user = in.readParcelable(UserBean.class.getClassLoader());
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
