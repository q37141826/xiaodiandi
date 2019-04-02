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

        private double integral;
        private String rmd;
        private double today;
        private double month;
        private double all;

        public double getIntegral() {
            return integral;
        }

        public void setIntegral(double integral) {
            this.integral = integral;
        }

        public String getRmd() {
            return rmd;
        }

        public void setRmd(String rmd) {
            this.rmd = rmd;
        }

        public double getToday() {
            return today;
        }

        public void setToday(int today) {
            this.today = today;
        }

        public double getMonth() {
            return month;
        }

        public void setMonth(double month) {
            this.month = month;
        }

        public double getAll() {
            return all;
        }

        public void setAll(int all) {
            this.all = all;
        }
    }
}
