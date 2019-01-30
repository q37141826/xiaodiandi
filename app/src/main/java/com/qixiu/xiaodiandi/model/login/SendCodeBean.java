package com.qixiu.xiaodiandi.model.login;

import com.qixiu.qixiu.request.bean.BaseBean;

public class SendCodeBean extends BaseBean<SendCodeBean.OBean> {

    public class OBean {

        private String verify_id;

        public void setVerify_id(String verify_id) {
            this.verify_id = verify_id;
        }

        public String getVerify_id() {
            return verify_id;
        }
    }

}
