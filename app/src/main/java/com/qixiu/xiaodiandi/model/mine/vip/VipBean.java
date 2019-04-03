package com.qixiu.xiaodiandi.model.mine.vip;

import com.qixiu.qixiu.request.bean.BaseBean;

public class VipBean extends BaseBean<VipBean.OBean> {


    /**
     * e : {"charge":"3","chargemsg":"111111111111111"}
     */

    private EBean e;

    public EBean getE() {
        return e;
    }

    public void setE(EBean e) {
        this.e = e;
    }

    /**
     * c : 1
     * m : 查询成功
     * o : {"uid":118,"avatar":"/public/uploads/2019-01-22/15481256887980.png","phone":"1515999999","account":"wx1181543580809","level":1,"integral":5,"group_name":"普通会员","rmd":"0.50","today":0,"month":0,"all":2,"friend":0,"friendsum":0}
     * e :
     */


    public static class OBean {
        /**
         * uid : 118
         * avatar : /public/uploads/2019-01-22/15481256887980.png
         * phone : 1515999999
         * account : wx1181543580809
         * level : 1
         * integral : 5
         * group_name : 普通会员
         * rmd : 0.50
         * today : 0
         * month : 0
         * all : 2
         * friend : 0
         * friendsum : 0
         */

        private int uid;
        private String avatar;
        private String phone;
        private String account;
        private double level;
        private double integral;
        private String group_name;
        private String rmd;
        private double today;
        private double month;
        private double all;
        private int friend;
        private int friendsum;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
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

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public double getLevel() {
            return level;
        }

        public void setLevel(double level) {
            this.level = level;
        }

        public double getIntegral() {
            return integral;
        }

        public void setIntegral(double integral) {
            this.integral = integral;
        }

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
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

        public int getFriend() {
            return friend;
        }

        public void setFriend(int friend) {
            this.friend = friend;
        }

        public int getFriendsum() {
            return friendsum;
        }

        public void setFriendsum(int friendsum) {
            this.friendsum = friendsum;
        }
    }


    public static class EBean {
        /**
         * charge : 3
         * chargemsg : 111111111111111
         */

        private String charge;
        private String chargemsg;

        public String getCharge() {
            return charge;
        }

        public void setCharge(String charge) {
            this.charge = charge;
        }

        public String getChargemsg() {
            return chargemsg;
        }

        public void setChargemsg(String chargemsg) {
            this.chargemsg = chargemsg;
        }
    }
}
