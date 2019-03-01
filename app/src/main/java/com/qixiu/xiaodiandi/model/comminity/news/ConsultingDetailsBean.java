package com.qixiu.xiaodiandi.model.comminity.news;

import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

public class ConsultingDetailsBean extends BaseBean<ConsultingDetailsBean.OBean> {



    private List<EBean> e;


    public List<EBean> getE() {
        return e;
    }

    public void setE(List<EBean> e) {
        this.e = e;
    }

    public static class OBean {
        /**
         * id : 160
         * title : fgh
         * image_input : http://xdd.qixiuu.com/public/uploads/editor/20181203/s_5c04ce4c12e36.png
         * content : <p>gh</p>
         */

        private int id;
        private String title;
        private String image_input;
        private String content;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
    public static class EBean {
        /**
         * uid : 181
         * content : 哈哈哈
         * addtime : 2019-02-19
         * user : {"nickname":"","avatar":"http://xdd.qixiuu.com/public/uploads/2019-02-18/15504762711811.png"}
         */

        private int uid;
        private String content;
        private String addtime;
        private NewsDetailsBean.EBean.UserBean user;

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

        public NewsDetailsBean.EBean.UserBean getUser() {
            return user;
        }

        public void setUser(NewsDetailsBean.EBean.UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * nickname :
             * avatar : http://xdd.qixiuu.com/public/uploads/2019-02-18/15504762711811.png
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
}
