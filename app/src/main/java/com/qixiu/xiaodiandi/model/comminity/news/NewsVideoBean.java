package com.qixiu.xiaodiandi.model.comminity.news;

import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

public class NewsVideoBean extends BaseBean<List<NewsVideoBean.OBean>> {




    public static class OBean {
        /**
         * id : 158
         * title : hahahhaha
         * image_input : http://xdd.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04ade0c362a.jpg
         * visit : 0
         * add_time : 2019-02-18
         */

        private int id;
        private String title;
        private String image_input;
        private String visit;
        private String add_time;

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

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }
    }
}
