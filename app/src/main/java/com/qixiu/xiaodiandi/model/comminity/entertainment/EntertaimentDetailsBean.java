package com.qixiu.xiaodiandi.model.comminity.entertainment;

import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

//
public class EntertaimentDetailsBean  extends BaseBean<EntertaimentDetailsBean.OBean> {


    private List<EBean> e;


    public List<EBean> getE() {
        return e;
    }

    public void setE(List<EBean> e) {
        this.e = e;
    }

    public static class OBean {
        /**
         * id : 3
         * uid : 118
         * content : 测试一下
         * type : 1
         * img : []
         * user : {"nickname":"韩韩","avatar":"/public/uploads/2019-01-22/15481256887980.png"}
         * forward : 0
         * forwardor : 0
         * collection : 0
         * collectionor : 0
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
        private List<ImageBean> img;

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

        public List<ImageBean> getImgList() {
            return img;
        }

        public void setImg(List<ImageBean> img) {
            this.img = img;
        }

        public static class UserBean {
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
        }

        public class ImageBean {


            /**
             * img : http://tz1.oss-cn-shanghai.aliyuncs.com/Upload/vedio/2019-02-17recording779622284.mp4
             */

            private String img;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }

    public static class EBean {
        /**
         * id : 1
         * uid : 179
         * content : 怪蜀黍三个
         * addtime : 2019-02-16
         * user : {"nickname":"a邓拼搏13554540382","avatar":"http://xdd.qixiuu.com/public/uploads/2019-02-16/15503090744927.png"}
         * reply : [{"content":"pappapa"}]
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
             * nickname : a邓拼搏13554540382
             * avatar : http://xdd.qixiuu.com/public/uploads/2019-02-16/15503090744927.png
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
