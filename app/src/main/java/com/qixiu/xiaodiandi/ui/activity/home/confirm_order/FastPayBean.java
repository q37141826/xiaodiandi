package com.qixiu.xiaodiandi.ui.activity.home.confirm_order;


import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

/**
 * Created by HuiHeZe on 2017/9/15.
 */

public class FastPayBean extends BaseBean<FastPayBean.OBean> {
    /**
     * c : 1
     * m : 立即购买成功
     * o : {"goods":[{"id":"17","name":"沁颜光感面膜","price":"0.01","pic":"http://192.168.168.39/cherry/cherry/Upload/img/2015-06-20/558445021b2f3.jpg","skuid":"0","num":"1"}],"address":[""],"total_price":0.01,"yf":"15","pay_price":15.01}
     * e :
     */
    public static class OBean {
        /**
         * goods : [{"id":"17","name":"沁颜光感面膜","price":"0.01","pic":"http://192.168.168.39/cherry/cherry/Upload/img/2015-06-20/558445021b2f3.jpg","skuid":"0","num":"1"}]
         * address : [""]
         * total_price : 0.01
         * yf : 15
         * pay_price : 15.01
         */

        private double total_price;
        private String yf;
        private double pay_price;
        private List<GoodsBean> goods;
        private List<AddressBean> address;

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

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public List<AddressBean> getAddress() {
            return address;
        }

        public void setAddress(List<AddressBean> address) {
            this.address = address;
        }

        public static class GoodsBean  implements GoodsBeanFatherIntfer{
            /**
             * id : 17
             * name : 沁颜光感面膜
             * price : 0.01
             * pic : http://192.168.168.39/cherry/cherry/Upload/img/2015-06-20/558445021b2f3.jpg
             * skuid : 0
             * num : 1
             */

            private String id;
            private String name;
            private String price;
            private String pic;
            private String skuid;
            private String num;

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

            public String getSkuid() {
                return skuid;
            }

            public void setSkuid(String skuid) {
                this.skuid = skuid;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }
        }
        public static class AddressBean {
            /**
             * addressid : 66
             * name : 何
             * mobile : 1112
             * address : 武大科技园
             */
            private String id;
            private String addressid;
            private String name;
            private String mobile;
            private String address;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAddressid() {
                return addressid;
            }

            public void setAddressid(String addressid) {
                this.addressid = addressid;
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
    }


    /**
     * c : 1
     * m : 立即购买成功
     * o : {"goods":{"id":"18","name":"香香","price":"1","pic":"http://localhost/cherry/php/cherry/Upload/img/2017-09-05/59ae09e41354a.jpg","skuid":"166","num":"3"},"address":{"addressid":"66","name":"何","mobile":"1112","address":"武大科技园"},"total_price":3,"yf":"15","pay_price":18}
     * e :
     */


}
