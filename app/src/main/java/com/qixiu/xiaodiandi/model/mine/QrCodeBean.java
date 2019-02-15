package com.qixiu.xiaodiandi.model.mine;

import com.qixiu.qixiu.request.bean.BaseBean;

public class QrCodeBean extends BaseBean<QrCodeBean.OBean> {


    public static class OBean {
        /**
         * uid : 118
         * avatar : http://www.water.com/public/uploads/2019-01-22/15481256887980.png
         * phone : 1515999999
         * account : wx1181543580809
         * qrcode : http://www.water.com/public/uploads/2019-01-25/15483799686277.png
         */

        private int uid;
        private String avatar;
        private String phone;
        private String account;
        private String qrcode;

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

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }
    }
}
