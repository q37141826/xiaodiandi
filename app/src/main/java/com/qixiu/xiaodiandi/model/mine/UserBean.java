package com.qixiu.xiaodiandi.model.mine;

import com.qixiu.qixiu.request.bean.BaseBean;

public class UserBean extends BaseBean<UserBean.OBean> {

    EBean eBean;

    public EBean geteBean() {
        return eBean;
    }

    public void seteBean(EBean eBean) {
        this.eBean = eBean;
    }

    public static class OBean {
        /**
         * uid : 179
         * avatar :
         * phone : 17612738977
         * account :
         * level : 0
         * integral : 0.00
         * group_name :
         * wechat_user : 2
         */

        private int uid;
        private String avatar;
        private String phone;
        private String account;
        private int level;
        private String integral;
        private String group_name;
        private int wechat_user;

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

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }

        public int getWechat_user() {
            return wechat_user;
        }

        public void setWechat_user(int wechat_user) {
            this.wechat_user = wechat_user;
        }
    }

    public static class EBean {
        /**
         * noBuy : 0
         * noTake : 0
         * noReply : 0
         */

        private int noBuy;
        private int noTake;
        private int noReply;

        public int getNoBuy() {
            return noBuy;
        }

        public void setNoBuy(int noBuy) {
            this.noBuy = noBuy;
        }

        public int getNoTake() {
            return noTake;
        }

        public void setNoTake(int noTake) {
            this.noTake = noTake;
        }

        public int getNoReply() {
            return noReply;
        }

        public void setNoReply(int noReply) {
            this.noReply = noReply;
        }
    }
}
