package com.qixiu.xiaodiandi.model.comminity.entertainment;

import android.os.Parcel;
import android.os.Parcelable;

import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.xiaodiandi.model.IdInterfer;

import java.util.List;

//
public class EntertainmentListBean  extends BaseBean< List<EntertainmentListBean.OBean>> {


    public static class OBean implements IdInterfer {
        /**
         * id : 94
         * uid : 309
         * content : 图拉土快乐旅途
         * type : 1
         * img : http://xdd.qixiuu.com/public/uploads/2019-03-08/15520404809692.png
         * user : {"phone":"s191551836158","nickname":"13554540383","avatar":"http://app.ssxdd.cn/public/uploads/2019-03-06/15518361776533.png"}
         * size : {"width":720,"height":1280}
         */

        private String id;
        private int uid;
        private String content;
        private int type;
        private String img;
        private UserBean user;
        private SizeBean size;
        private boolean canDelete=false;

        public boolean isCanDelete() {
            return canDelete;
        }

        public void setCanDelete(boolean canDelete) {
            this.canDelete = canDelete;
        }

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

        public SizeBean getSize() {
            return size;
        }

        public void setSize(SizeBean size) {
            this.size = size;
        }

        public static class UserBean implements Parcelable {
            /**
             * phone : s191551836158
             * nickname : 13554540383
             * avatar : http://app.ssxdd.cn/public/uploads/2019-03-06/15518361776533.png
             */

            private String phone;
            private String nickname;
            private String avatar;

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

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
                dest.writeString(this.phone);
                dest.writeString(this.nickname);
                dest.writeString(this.avatar);
            }

            public UserBean() {
            }

            protected UserBean(Parcel in) {
                this.phone = in.readString();
                this.nickname = in.readString();
                this.avatar = in.readString();
            }

            public static final Parcelable.Creator<UserBean> CREATOR = new Parcelable.Creator<UserBean>() {
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


        public OBean() {
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
            dest.writeParcelable(this.size, flags);
            dest.writeByte(this.canDelete ? (byte) 1 : (byte) 0);
        }

        protected OBean(Parcel in) {
            this.id = in.readString();
            this.uid = in.readInt();
            this.content = in.readString();
            this.type = in.readInt();
            this.img = in.readString();
            this.user = in.readParcelable(UserBean.class.getClassLoader());
            this.size = in.readParcelable(SizeBean.class.getClassLoader());
            this.canDelete = in.readByte() != 0;
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
