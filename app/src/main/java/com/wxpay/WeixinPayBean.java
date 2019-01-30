package com.wxpay;

import com.google.gson.annotations.SerializedName;
import com.qixiu.qixiu.request.bean.BaseBean;

/**
 * Created by HuiHeZe on 2017/6/28.
 */

public class WeixinPayBean extends BaseBean<WeixinPayBean.OBean> {


    /**
     * o : {"appid":"wx0f281587255df173","noncestr":"50645ca5663c056f15567169f49e86c3","package":"Sign=WXPay","partnerid":"1488790342","prepayid":"wx20170915114110bf664b11640463174104","timestamp":1505446904,"sign":"13718219F7958F312D9914615F7CB90F"}
     * e :
     */

    public static class OBean {
        /**
         * appid : wx0f281587255df173
         * noncestr : 50645ca5663c056f15567169f49e86c3
         * package : Sign=WXPay
         * partnerid : 1488790342
         * prepayid : wx20170915114110bf664b11640463174104
         * timestamp : 1505446904
         * sign : 13718219F7958F312D9914615F7CB90F
         */

        private String appid;
        private String noncestr;
        @SerializedName("package")
        private String packageX;
        private String partnerid;
        private String prepayid;
        private int timestamp;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
