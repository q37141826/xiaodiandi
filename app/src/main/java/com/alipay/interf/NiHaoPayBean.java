package com.alipay.interf;


import com.qixiu.qixiu.request.bean.BaseBean;

/**
 * Created by my on 2018/3/12.
 */

public class NiHaoPayBean extends BaseBean<NiHaoPayBean.OBean> {



    private String e;


    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public static class OBean {
        private String oid;
        private String name;
        private int payprice;

        public String getOid() {
            return oid;
        }

        public void setOid(String oid) {
            this.oid = oid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPayprice() {
            return payprice;
        }

        public void setPayprice(int payprice) {
            this.payprice = payprice;
        }
    }
}
