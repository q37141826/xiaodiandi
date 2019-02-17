package com.qixiu.xiaodiandi.model.mine.vip;

import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

public class FriendsListBean extends BaseBean<List<FriendsListBean.OBean>> {


    public static class OBean {
        /**
         * uid : 119
         * account : rt1191543691940
         * phone :
         * avatar : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIveS9MIL9ngyAI7vxzQDsicNmoZ6RbSM4sGib1UXfJr1q2On6iaA5H0K0xOdAf4u5zgml7YPuJ2RX8w/132
         * level : 1
         * group_name : 普通会员
         */

        private int uid;
        private String account;
        private String phone;
        private String avatar;
        private int level;
        private String group_name;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }
    }
}
