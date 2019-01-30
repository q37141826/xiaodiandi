package com.alipay.interf;


import com.qixiu.qixiu.request.bean.BaseBean;

/**
 * Created by HuiHeZe on 2017/6/27
 */

public class AliBean  extends BaseBean<AliBean.OBean> {



    public static class OBean {
        /**
         * orderId : 960fd08dd2ada5d2a82b451b60980d7f
         * key : 960fd08dd2ada5d2a82b451b60980d7f
         */

        private String orderId;
        private String key;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
