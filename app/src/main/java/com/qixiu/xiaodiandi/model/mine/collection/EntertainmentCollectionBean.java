package com.qixiu.xiaodiandi.model.mine.collection;


import android.os.Parcel;
import android.os.Parcelable;

import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.xiaodiandi.model.IdInterfer;
import com.qixiu.xiaodiandi.model.comminity.entertainment.SizeBean;

import java.util.List;

//
public class EntertainmentCollectionBean extends BaseBean<List<EntertainmentCollectionBean.OBean>> {



    public static class OBean implements Parcelable ,IdInterfer{
        /**
         * id : 73
         * aid : 91
         * addtime : 1551930640
         * release : {"id":91,"uid":315,"content":"好好的生活","img":"http://app.ssxdd.cn/public/uploads/2019-03-07/15519246391997.png","user":{"nickname":"Linda","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/BUcfMPzbCZSryc3yHaiclBLuFWQejhxtunAOqJUq2UxEq0R0haIjPB2BiafU1duia48ARFakAPRccaKNicp66WaT3w/132"},"size":{"width":1080,"height":1080}}
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

        public static class ReleaseBean implements Parcelable,IdInterfer {
            /**
             * id : 91
             * uid : 315
             * content : 好好的生活
             * img : http://app.ssxdd.cn/public/uploads/2019-03-07/15519246391997.png
             * user : {"nickname":"Linda","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/BUcfMPzbCZSryc3yHaiclBLuFWQejhxtunAOqJUq2UxEq0R0haIjPB2BiafU1duia48ARFakAPRccaKNicp66WaT3w/132"}
             * size : {"width":1080,"height":1080}
             */

            private String id;
            private int uid;
            private String content;
            private String img;
            private UserBean user;
            private SizeBean size;

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
                 * nickname : Linda
                 * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/BUcfMPzbCZSryc3yHaiclBLuFWQejhxtunAOqJUq2UxEq0R0haIjPB2BiafU1duia48ARFakAPRccaKNicp66WaT3w/132
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


            public ReleaseBean() {
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
                dest.writeString(this.img);
                dest.writeParcelable(this.user, flags);
                dest.writeParcelable(this.size, flags);
            }

            protected ReleaseBean(Parcel in) {
                this.id = in.readString();
                this.uid = in.readInt();
                this.content = in.readString();
                this.img = in.readString();
                this.user = in.readParcelable(UserBean.class.getClassLoader());
                this.size = in.readParcelable(SizeBean.class.getClassLoader());
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
