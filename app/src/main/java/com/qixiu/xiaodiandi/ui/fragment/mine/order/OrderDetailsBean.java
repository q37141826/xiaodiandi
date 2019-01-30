package com.qixiu.xiaodiandi.ui.fragment.mine.order;


import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

/**
 * Created by HuiHeZe on 2017/5/3.
 */

public class OrderDetailsBean extends BaseBean<OrderDetailsBean.OBean> {
    /**
     * c : 1
     * m : 查询成功
     * o : {"id":"217","oid":"20170920114559-217","status":"1","totalnum":"2","totalprice":"1.01","pay_price":"1.01","yf":"0","vipname":"啥时候","vipmobile":"13658965679","vipaddress":"也许就是手机啊哈","msg":"","items":[{"goodsid":"19","num":"1","name":"一号商品","skuattr":"","price":"1","total":1,"pic":"http://cherry.whtkl.cn/Upload/img/2017-09-05/59ae09e41354a.jpg"},{"goodsid":"17","num":"1","name":"沁颜光感面膜","skuattr":"","price":"0.01","total":0.01,"pic":"http://cherry.whtkl.cn/Upload/img/2015-06-20/558445021b2f3.jpg"}],"ctime":"2017-09-20 11:45:59"}
     * e :
     */



    public static class OBean {
        /**
         * id : 217
         * oid : 20170920114559-217
         * status : 1
         * totalnum : 2
         * totalprice : 1.01
         * pay_price : 1.01
         * yf : 0
         * vipname : 啥时候
         * vipmobile : 13658965679
         * vipaddress : 也许就是手机啊哈
         * msg :
         * items : [{"goodsid":"19","num":"1","name":"一号商品","skuattr":"","price":"1","total":1,"pic":"http://cherry.whtkl.cn/Upload/img/2017-09-05/59ae09e41354a.jpg"},{"goodsid":"17","num":"1","name":"沁颜光感面膜","skuattr":"","price":"0.01","total":0.01,"pic":"http://cherry.whtkl.cn/Upload/img/2015-06-20/558445021b2f3.jpg"}]
         * ctime : 2017-09-20 11:45:59
         */

        private String id;
        private String oid;
        private String status;
        private String totalnum;
        private String totalprice;
        private String pay_price;
        private String yf;
        private String vipname;
        private String vipmobile;
        private String vipaddress;
        private String msg;
        private String ctime;
        private List<ItemsBean> items;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOid() {
            return oid;
        }

        public void setOid(String oid) {
            this.oid = oid;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTotalnum() {
            return totalnum;
        }

        public void setTotalnum(String totalnum) {
            this.totalnum = totalnum;
        }

        public String getTotalprice() {
            return totalprice;
        }

        public void setTotalprice(String totalprice) {
            this.totalprice = totalprice;
        }

        public String getPay_price() {
            return pay_price;
        }

        public void setPay_price(String pay_price) {
            this.pay_price = pay_price;
        }

        public String getYf() {
            return yf;
        }

        public void setYf(String yf) {
            this.yf = yf;
        }

        public String getVipname() {
            return vipname;
        }

        public void setVipname(String vipname) {
            this.vipname = vipname;
        }

        public String getVipmobile() {
            return vipmobile;
        }

        public void setVipmobile(String vipmobile) {
            this.vipmobile = vipmobile;
        }

        public String getVipaddress() {
            return vipaddress;
        }

        public void setVipaddress(String vipaddress) {
            this.vipaddress = vipaddress;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * goodsid : 19
             * num : 1
             * name : 一号商品
             * skuattr :
             * price : 1
             * total : 1
             * pic : http://cherry.whtkl.cn/Upload/img/2017-09-05/59ae09e41354a.jpg
             */

            private String goodsid;
            private String num;
            private String name;
            private String skuattr;
            private String price;
            private String total;
            private String pic;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSkuattr() {
                return skuattr;
            }

            public void setSkuattr(String skuattr) {
                this.skuattr = skuattr;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }
    }
    /**
     * c : 1
     * m : 查询成功
     * o : {"id":"135","oid":"20170918174505-135","status":"0","totalnum":"1","totalprice":"0.00","pay_price":"15.00","yf":"0","vipname":"是不是不准备","vipmobile":"13948797979","vipaddress":"17","msg":"","items":[{"goodsid":"17","name":"沁颜光感面膜","skuattr":"","num":"1","price":"","total":0,"pic":{"imgurl":"http://192.168.168.39/cherry/cherry/Upload/img/2015-06-20/558445021b2f3.jpg"}}],"ctime":"2017-09-18 17:45:05"}
     * e :
     */






}
