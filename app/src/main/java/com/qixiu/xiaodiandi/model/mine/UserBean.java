package com.qixiu.xiaodiandi.model.mine;

//

import com.qixiu.qixiu.request.bean.BaseBean;

public class UserBean extends BaseBean<UserBean.OBean> {



    private EBean e;





    public EBean getE() {
        return e;
    }

    public void setE(EBean e) {
        this.e = e;
    }

    public static class OBean {
        /**
         * uid : 181
         * avatar : /public/uploads/2019-01-30/15488104973419.png
         * phone : 13554540382
         * account : s191548410986
         * level : 1
         * integral : 9.50
         * signed : 1
         * group_name : 普通会员
         * wechat_user : 2
         */

        private int uid;
        private String avatar;
        private String phone;
        private String account;
        private int level;
        private String integral;
        private int signed;
        private String group_name;
        private String servicetelephone;
        private int wechat_user;

        public String getServicetelephone() {
            return servicetelephone;
        }

        public void setServicetelephone(String servicetelephone) {
            this.servicetelephone = servicetelephone;
        }

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

        public int getSigned() {
            return signed;
        }

        public void setSigned(int signed) {
            this.signed = signed;
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
         * noBuy : 11
         * noTake : 0
         * noReply : 3
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
