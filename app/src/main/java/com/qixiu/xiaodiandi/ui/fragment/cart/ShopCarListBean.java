package com.qixiu.xiaodiandi.ui.fragment.cart;


import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/15 0015.
 */

public class ShopCarListBean extends BaseBean<ShopCarListBean.ShopCarListInfo> {


    /**
     * o : {"list":[{"id":"4","goodsid":"18","sku":"0","num":"1","name":"香香","skuattr":"","price":"3121","pic":"http://localhost/cherry/php/cherry/Upload/img/2015-06-20/558445021b2f3.jpg"},{"id":"5","goodsid":"19","sku":"166","num":"1","name":"一号商品","skuattr":"M","price":"1","pic":"http://localhost/cherry/php/cherry/Upload/img/2017-09-05/59ae09e41354a.jpg"}],"totalnum":2,"totalprice":3122}
     * e :
     */

    private String e;


    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public static class ShopCarListInfo {
        /**
         * list : [{"id":"4","goodsid":"18","sku":"0","num":"1","name":"香香","skuattr":"","price":"3121","pic":"http://localhost/cherry/php/cherry/Upload/img/2015-06-20/558445021b2f3.jpg"},{"id":"5","goodsid":"19","sku":"166","num":"1","name":"一号商品","skuattr":"M","price":"1","pic":"http://localhost/cherry/php/cherry/Upload/img/2017-09-05/59ae09e41354a.jpg"}]
         * totalnum : 2
         * totalprice : 3122
         */

        private String totalnum;
        private String totalprice;
        private List<ListBean> list;

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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 4
             * goodsid : 18
             * sku : 0
             * num : 1
             * name : 香香
             * skuattr :
             * price : 3121
             * pic : http://localhost/cherry/php/cherry/Upload/img/2015-06-20/558445021b2f3.jpg
             */

            private String id;
            private String goodsid;
            private String sku;
            private String num;
            private String name;
            private String skuattr;
            private String price;
            private String pic;
            private boolean isSelected;

            public boolean isSelected() {
                return isSelected;
            }

            public void setSelected(boolean selected) {
                isSelected = selected;
            }

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

            public String getSku() {
                return sku;
            }

            public void setSku(String sku) {
                this.sku = sku;
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

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }
    }
}
