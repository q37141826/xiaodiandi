package com.qixiu.xiaodiandi.model.comminity.entertainment;

import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

//
public class EntertaimentDetailsBean extends BaseBean<EntertaimentDetailsBean.OBean> {



    private List<EBean> e;


    public List<EBean> getE() {
        return e;
    }

    public void setE(List<EBean> e) {
        this.e = e;
    }

    public static class OBean {
        /**
         * id : 9
         * uid : 118
         * content : 哈哈哈哈哈
         * type : 1
         * img : ["http://xdd.qixiuu.com/public/uploads/2019-02-18/15504697863291.png","http://xdd.qixiuu.com/public/uploads/2019-02-18/15504697865068.png"]
         * user : {"nickname":"韩韩","avatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIveS9MIL9ngyAI7vxzQDsicNmoZ6RbSM4sGib1UXfJr1q2On6iaA5H0K0xOdAf4u5zgml7YPuJ2RX8w/132"}
         * forward : 5
         * forwardor : 1
         * collection : 1
         * collectionor : 1
         */

        private int id;
        private int uid;
        private String content;
        private int type;
        private UserBean user;
        private int forward;
        private int forwardor;
        private int collection;
        private int collectionor;
        private List<String> img;

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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public int getForward() {
            return forward;
        }

        public void setForward(int forward) {
            this.forward = forward;
        }

        public int getForwardor() {
            return forwardor;
        }

        public void setForwardor(int forwardor) {
            this.forwardor = forwardor;
        }

        public int getCollection() {
            return collection;
        }

        public void setCollection(int collection) {
            this.collection = collection;
        }

        public int getCollectionor() {
            return collectionor;
        }

        public void setCollectionor(int collectionor) {
            this.collectionor = collectionor;
        }

        public List<String> getImg() {
            return img;
        }

        public void setImg(List<String> img) {
            this.img = img;
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
    }

    public static class EBean {
        /**
         * id : 9
         * uid : 181
         * content : 哥哈哈哈哈
         * addtime : 2019-02-18
         * user : {"nickname":"","avatar":"http://xdd.qixiuu.com/public/uploads/2019-02-18/15504762711811.png","phone":"13554540382"}
         * reply : []
         */

        private int id;
        private int uid;
        private String content;
        private String addtime;
        private UserBeanX user;
        private List<ReplyBean> reply;

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

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public UserBeanX getUser() {
            return user;
        }

        public void setUser(UserBeanX user) {
            this.user = user;
        }

        public List<ReplyBean> getReply() {
            return reply;
        }

        public void setReply(List<ReplyBean> reply) {
            this.reply = reply;
        }

        public static class UserBeanX {
            /**
             * nickname :
             * avatar : http://xdd.qixiuu.com/public/uploads/2019-02-18/15504762711811.png
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
        }
        public static class ReplyBean {
            /**
             * content : pappapa
             */

            private String content;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
