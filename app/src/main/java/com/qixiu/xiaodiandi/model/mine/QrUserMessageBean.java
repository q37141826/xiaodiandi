package com.qixiu.xiaodiandi.model.mine;

import com.qixiu.qixiu.request.bean.BaseBean;

public class QrUserMessageBean  extends BaseBean<QrUserMessageBean.OBean> {




    public static class OBean {
        /**
         * uid : 181
         * phone : 13554540382
         */

        private int uid;
        private String phone;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
