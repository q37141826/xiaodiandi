package com.qixiu.xiaodiandi.model.home.goodsdetails;

import com.qixiu.qixiu.request.bean.BaseBean;

public class GetPointsTimeBean  extends BaseBean<GetPointsTimeBean.OBean> {




    public static class OBean {
        /**
         * timefen : 10
         * numci : 5
         * dayci : 5
         */

        private String timefen;
        private String numci;
        private String dayci;

        public String getTimefen() {
            return timefen;
        }

        public void setTimefen(String timefen) {
            this.timefen = timefen;
        }

        public String getNumci() {
            return numci;
        }

        public void setNumci(String numci) {
            this.numci = numci;
        }

        public String getDayci() {
            return dayci;
        }

        public void setDayci(String dayci) {
            this.dayci = dayci;
        }
    }
}
