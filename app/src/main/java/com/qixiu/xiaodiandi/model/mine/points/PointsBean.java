package com.qixiu.xiaodiandi.model.mine.points;

import com.qixiu.qixiu.request.bean.BaseBean;

public class PointsBean extends BaseBean<PointsBean.OBean> {




    public static class OBean {
        /**
         * integral : 4
         * rmd : 0.40
         * today : 0
         * month : 0
         * all : 0
         */

        private int integral;
        private String rmd;
        private int today;
        private int month;
        private int all;

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public String getRmd() {
            return rmd;
        }

        public void setRmd(String rmd) {
            this.rmd = rmd;
        }

        public int getToday() {
            return today;
        }

        public void setToday(int today) {
            this.today = today;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getAll() {
            return all;
        }

        public void setAll(int all) {
            this.all = all;
        }
    }
}
