package com.qixiu.xiaodiandi.model.comminity.news;

import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

//
public class NewsDetailsBean  extends BaseBean<NewsDetailsBean.OBean> {



    private List<EBean> e;


    public List<EBean> getE() {
        return e;
    }

    public void setE(List<EBean> e) {
        this.e = e;
    }

    public static class OBean {
        /**
         * id : 161
         * title : 周建的儿子真可爱
         * image_input : http://xdd.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04cdbee91e7.png
         * video : /public/uploads/config/file/5c6b6d0273e9e.mp4
         * message : [{"id":161,"title":"周建的儿子真可爱","image_input":"http://xdd.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04cdbee91e7.png","visit":"5","num":1}]
         * forward : 0
         * content : <p>难道这么快就好了把</p>
         */

        private int id;
        private String title;
        private String visit;
        private String image_input;
        private String video;
        private int forward;
        private String content;
        private List<MessageBean> message;

        public String getVisit() {
            return visit;
        }

        public void setVisit(String visit) {
            this.visit = visit;
        }

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

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public int getForward() {
            return forward;
        }

        public void setForward(int forward) {
            this.forward = forward;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<MessageBean> getMessage() {
            return message;
        }

        public void setMessage(List<MessageBean> message) {
            this.message = message;
        }

        public static class MessageBean {
            /**
             * id : 161
             * title : 周建的儿子真可爱
             * image_input : http://xdd.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04cdbee91e7.png
             * visit : 5
             * num : 1
             */

            private int id;
            private String title;
            private String image_input;
            private String visit;
            private int num;

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

            public String getVisit() {
                return visit;
            }

            public void setVisit(String visit) {
                this.visit = visit;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }
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
        private UserBean user;

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

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
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
