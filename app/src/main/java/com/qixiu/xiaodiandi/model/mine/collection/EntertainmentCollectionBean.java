package com.qixiu.xiaodiandi.model.mine.collection;


import android.os.Parcel;
import android.os.Parcelable;

import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.xiaodiandi.model.IdInterfer;

import java.util.List;

//
public class EntertainmentCollectionBean extends BaseBean<List<EntertainmentCollectionBean.OBean>> {


    public static class OBean implements Parcelable ,IdInterfer {
        /**
         * id : 9
         * aid : 9
         * addtime : 1550477738
         * release : {"id":9,"uid":118,"content":"哈哈哈哈哈","img":"http://xdd.qixiuu.com/public/uploads/2019-02-18/15504697863291.png","user":{"nickname":"韩韩","avatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIveS9MIL9ngyAI7vxzQDsicNmoZ6RbSM4sGib1UXfJr1q2On6iaA5H0K0xOdAf4u5zgml7YPuJ2RX8w/132"}}
         */

        private String id;
        private int aid;
        private int addtime;
        private ReleaseBean release;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }

        public int getAddtime() {
            return addtime;
        }

        public void setAddtime(int addtime) {
            this.addtime = addtime;
        }

        public ReleaseBean getRelease() {
            return release;
        }

        public void setRelease(ReleaseBean release) {
            this.release = release;
        }

        public static class ReleaseBean implements Parcelable {
            /**
             * id : 9
             * uid : 118
             * content : 哈哈哈哈哈
             * img : http://xdd.qixiuu.com/public/uploads/2019-02-18/15504697863291.png
             * user : {"nickname":"韩韩","avatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIveS9MIL9ngyAI7vxzQDsicNmoZ6RbSM4sGib1UXfJr1q2On6iaA5H0K0xOdAf4u5zgml7YPuJ2RX8w/132"}
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

            public static class UserBean {
                /**
                 * nickname : 韩韩
                 * avatar : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIveS9MIL9ngyAI7vxzQDsicNmoZ6RbSM4sGib1UXfJr1q2On6iaA5H0K0xOdAf4u5zgml7YPuJ2RX8w/132
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
            }

            public ReleaseBean() {
            }

            protected ReleaseBean(Parcel in) {
                this.id = in.readInt();
                this.uid = in.readInt();
                this.content = in.readString();
                this.img = in.readString();
                this.user = in.readParcelable(UserBean.class.getClassLoader());
            }

            public static final Creator<ReleaseBean> CREATOR = new Creator<ReleaseBean>() {
                @Override
                public ReleaseBean createFromParcel(Parcel source) {
                    return new ReleaseBean(source);
                }

                @Override
                public ReleaseBean[] newArray(int size) {
                    return new ReleaseBean[size];
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
            dest.writeInt(this.aid);
            dest.writeInt(this.addtime);
            dest.writeParcelable(this.release, flags);
        }

        protected OBean(Parcel in) {
            this.id = in.readString();
            this.aid = in.readInt();
            this.addtime = in.readInt();
            this.release = in.readParcelable(ReleaseBean.class.getClassLoader());
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
