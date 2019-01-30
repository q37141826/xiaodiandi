package com.qixiu.xiaodiandi.ui.fragment.mine.order;


import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

/**
 * Created by HuiHeZe on 2017/5/3.
 */

public class OrderBean extends BaseBean<OrderBean.OBean> {


    /**
     * c : 1
     * m : 查询成功
     * o : {"list":[{"id":"217","oid":"20170920114559-217","status":"1","totalnum":"2","totalprice":"1.01","items":[{"goodsid":"19","num":"1","name":"一号商品","skuattr":"","price":"1","total":1,"pic":"http://cherry.whtkl.cn/Upload/img/2017-09-05/59ae09e41354a.jpg"},{"goodsid":"17","num":"1","name":"沁颜光感面膜","skuattr":"","price":"0.01","total":0.01,"pic":"http://cherry.whtkl.cn/Upload/img/2015-06-20/558445021b2f3.jpg"}]}],"page":1}
     * e :
     */



    public static class OBean {
        /**
         * list : [{"id":"217","oid":"20170920114559-217","status":"1","totalnum":"2","totalprice":"1.01","items":[{"goodsid":"19","num":"1","name":"一号商品","skuattr":"","price":"1","total":1,"pic":"http://cherry.whtkl.cn/Upload/img/2017-09-05/59ae09e41354a.jpg"},{"goodsid":"17","num":"1","name":"沁颜光感面膜","skuattr":"","price":"0.01","total":0.01,"pic":"http://cherry.whtkl.cn/Upload/img/2015-06-20/558445021b2f3.jpg"}]}]
         * page : 1
         */

        private int page;
        private List<ListBean> list;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 217
             * oid : 20170920114559-217
             * status : 1
             * totalnum : 2
             * totalprice : 1.01
             * items : [{"goodsid":"19","num":"1","name":"一号商品","skuattr":"","price":"1","total":1,"pic":"http://cherry.whtkl.cn/Upload/img/2017-09-05/59ae09e41354a.jpg"},{"goodsid":"17","num":"1","name":"沁颜光感面膜","skuattr":"","price":"0.01","total":0.01,"pic":"http://cherry.whtkl.cn/Upload/img/2015-06-20/558445021b2f3.jpg"}]
             */

            private String id;
            private String oid;
            private String status;
            private String totalnum;
            private String totalprice;
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
                /**
                 * pic : {"imgurl":"http://192.168.168.39/cherry/cherry/Upload/img/2017-09-14/59ba525fa8712.jpg"}
                 */

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
    }
}
