package com.qixiu.xiaodiandi.ui.activity.home.confirm_order;


import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

/**
 * Created by HuiHeZe on 2017/9/15.
 */

public class CartsPayBean  extends BaseBean<CartsPayBean.OBean> {


    /**
     * c : 1
     * m : 结算成功
     * o : {"address":[{"id":"69","name":"大街上还是","mobile":"13688096798","address":"这就是就是就是距"}],"goods":[{"id":"87","goodsid":"17","num":"1","skuid":0,"name":"沁颜光感面膜","price":"0.01","pic":"http://cherry.whtkl.cn/Upload/img/2015-06-20/558445021b2f3.jpg"}],"total_price":0.01,"yf":"0","pay_price":0.01}
     * e :
     */


    public static class OBean {
        /**
         * address : [{"id":"69","name":"大街上还是","mobile":"13688096798","address":"这就是就是就是距"}]
         * goods : [{"id":"87","goodsid":"17","num":"1","skuid":0,"name":"沁颜光感面膜","price":"0.01","pic":"http://cherry.whtkl.cn/Upload/img/2015-06-20/558445021b2f3.jpg"}]
         * total_price : 0.01
         * yf : 0
         * pay_price : 0.01
         */

        private double total_price;
        private String yf;
        private double pay_price;
        private List<AddressBean> address;
        private List<GoodsBean> goods;

        public double getTotal_price() {
            return total_price;
        }

        public void setTotal_price(double total_price) {
            this.total_price = total_price;
        }

        public String getYf() {
            return yf;
        }

        public void setYf(String yf) {
            this.yf = yf;
        }

        public double getPay_price() {
            return pay_price;
        }

        public void setPay_price(double pay_price) {
            this.pay_price = pay_price;
        }

        public List<AddressBean> getAddress() {
            return address;
        }

        public void setAddress(List<AddressBean> address) {
            this.address = address;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class AddressBean {
            /**
             * id : 69
             * name : 大街上还是
             * mobile : 13688096798
             * address : 这就是就是就是距
             */

            private String id;
            private String name;
            private String mobile;
            private String address;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }

        public static class GoodsBean implements GoodsBeanFatherIntfer {
            /**
             * id : 87
             * goodsid : 17
             * num : 1
             * skuid : 0
             * name : 沁颜光感面膜
             * price : 0.01
             * pic : http://cherry.whtkl.cn/Upload/img/2015-06-20/558445021b2f3.jpg
             */

            private String id;
            private String goodsid;
            private String num;
            private String skuid;
            private String name;
            private String price;
            private String pic;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getGoodsid() {
                return goodsid;
            }

            public void setGoodsid(String goodsid) {
                this.goodsid = goodsid;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getSkuid() {
                return skuid;
            }

            public void setSkuid(String skuid) {
                this.skuid = skuid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }
    }
}
