package com.qixiu.xiaodiandi.model.home;

import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

public class MessageBean extends BaseBean<MessageBean.OBean> {




    public static class OBean {
        /**
         * lastpage : 1
         * list : [{"id":43,"user":"系统管理员","title":"22","content":"22","add_time":"2019-01-20 23:02:42","is_see":1},{"id":42,"user":"系统管理员","title":"11","content":"11","add_time":"2019-01-20 22:44:32","is_see":0}]
         */

        private int lastpage;
        private List<ListBean> list;

        public int getLastpage() {
            return lastpage;
        }

        public void setLastpage(int lastpage) {
            this.lastpage = lastpage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 43
             * user : 系统管理员
             * title : 22
             * content : 22
             * add_time : 2019-01-20 23:02:42
             * is_see : 1
             */

            private int id;
            private String user;
            private String title;
            private String content;
            private String add_time;
            private int is_see;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUser() {
                return user;
            }

            public void setUser(String user) {
                this.user = user;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public int getIs_see() {
                return is_see;
            }

            public void setIs_see(int is_see) {
                this.is_see = is_see;
            }
        }
    }
}
