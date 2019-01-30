package com.qixiu.xiaodiandi.ui.fragment.mine.order;


import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

/**
 * Created by HuiHeZe on 2017/5/3.
 */

public class OrderBean extends BaseBean< List<OrderBean.OBean>> {




    public static class OBean {
        /**
         * combination_id : 0
         * id : 744
         * order_id : wx2019013015264710004
         * pay_price : 260.00
         * total_num : 4
         * total_price : 260.00
         * pay_postage : 0.00
         * total_postage : 0.00
         * paid : 0
         * status : 0
         * refund_status : 0
         * pay_type : 1
         * coupon_price : 0.00
         * deduction_price : 0.00
         * pink_id : 0
         * delivery_type :
         * cartInfo : [{"id":1304,"uid":181,"type":"product","product_id":691,"product_attr_unique":"e096ab0a","cart_num":1,"add_time":1548745440,"is_pay":0,"is_del":0,"is_new":0,"combination_id":0,"seckill_id":0,"bargain_id":0,"productInfo":{"id":691,"image":"https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f7402c.jpg","slider_image":["https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f85c7b.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f7402c.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f6261a.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f51806.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f40243.jpg"],"price":"19.90","cost":"0.00","ot_price":"25.00","vip_price":"0.00","postage":"0.00","mer_id":0,"give_integral":"5.00","cate_id":"69,70,71","sales":7,"stock":398,"store_name":"【磨功夫 茉莉研磨真奶茶 】 原价36，现价19.9包邮 不含植脂末 无色素 无奶精 低卡路里 60g(30g*2包）*2袋","unit_name":"件","is_show":1,"is_del":0,"is_postage":1,"attrInfo":{"product_id":691,"suk":"小,绿","stock":100,"sales":0,"price":"80.00","image":"https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f7402c.jpg","unique":"e096ab0a","cost":"11.00"}},"truePrice":80,"costPrice":11,"trueStock":100,"unique":"e8005c206b8e46997478ce24afc142e2"},{"id":1305,"uid":181,"type":"product","product_id":691,"product_attr_unique":"bf861cca","cart_num":3,"add_time":1548755496,"is_pay":0,"is_del":0,"is_new":0,"combination_id":0,"seckill_id":0,"bargain_id":0,"productInfo":{"id":691,"image":"https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f7402c.jpg","slider_image":["https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f85c7b.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f7402c.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f6261a.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f51806.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f40243.jpg"],"price":"19.90","cost":"0.00","ot_price":"25.00","vip_price":"0.00","postage":"0.00","mer_id":0,"give_integral":"5.00","cate_id":"69,70,71","sales":7,"stock":398,"store_name":"【磨功夫 茉莉研磨真奶茶 】 原价36，现价19.9包邮 不含植脂末 无色素 无奶精 低卡路里 60g(30g*2包）*2袋","unit_name":"件","is_show":1,"is_del":0,"is_postage":1,"attrInfo":{"product_id":691,"suk":"小,红","stock":100,"sales":0,"price":"60.00","image":"https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f7402c.jpg","unique":"bf861cca","cost":"11.00"}},"truePrice":60,"costPrice":11,"trueStock":100,"unique":"f5246ae73c582c340b7d8f0e1412e27d"}]
         * _status : {"_type":0,"_title":"未支付","_msg":"立即支付订单吧","_class":"nobuy","_payType":"支付宝"}
         */

        private int combination_id;
        private int id;
        private String order_id;
        private String pay_price;
        private int total_num;
        private String total_price;
        private String pay_postage;
        private String total_postage;
        private int paid;
        private int status;
        private int refund_status;
        private String pay_type;
        private String coupon_price;
        private String deduction_price;
        private int pink_id;
        private String delivery_type;
        private StatusBean _status;
        private List<CartInfoBean> cartInfo;

        public int getCombination_id() {
            return combination_id;
        }

        public void setCombination_id(int combination_id) {
            this.combination_id = combination_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getPay_price() {
            return pay_price;
        }

        public void setPay_price(String pay_price) {
            this.pay_price = pay_price;
        }

        public int getTotal_num() {
            return total_num;
        }

        public void setTotal_num(int total_num) {
            this.total_num = total_num;
        }

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public String getPay_postage() {
            return pay_postage;
        }

        public void setPay_postage(String pay_postage) {
            this.pay_postage = pay_postage;
        }

        public String getTotal_postage() {
            return total_postage;
        }

        public void setTotal_postage(String total_postage) {
            this.total_postage = total_postage;
        }

        public int getPaid() {
            return paid;
        }

        public void setPaid(int paid) {
            this.paid = paid;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getRefund_status() {
            return refund_status;
        }

        public void setRefund_status(int refund_status) {
            this.refund_status = refund_status;
        }

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public String getCoupon_price() {
            return coupon_price;
        }

        public void setCoupon_price(String coupon_price) {
            this.coupon_price = coupon_price;
        }

        public String getDeduction_price() {
            return deduction_price;
        }

        public void setDeduction_price(String deduction_price) {
            this.deduction_price = deduction_price;
        }

        public int getPink_id() {
            return pink_id;
        }

        public void setPink_id(int pink_id) {
            this.pink_id = pink_id;
        }

        public String getDelivery_type() {
            return delivery_type;
        }

        public void setDelivery_type(String delivery_type) {
            this.delivery_type = delivery_type;
        }

        public StatusBean get_status() {
            return _status;
        }

        public void set_status(StatusBean _status) {
            this._status = _status;
        }

        public List<CartInfoBean> getCartInfo() {
            return cartInfo;
        }

        public void setCartInfo(List<CartInfoBean> cartInfo) {
            this.cartInfo = cartInfo;
        }

        public static class StatusBean {
            /**
             * _type : 0
             * _title : 未支付
             * _msg : 立即支付订单吧
             * _class : nobuy
             * _payType : 支付宝
             */

            private int _type;
            private String _title;
            private String _msg;
            private String _class;
            private String _payType;

            public int get_type() {
                return _type;
            }

            public void set_type(int _type) {
                this._type = _type;
            }

            public String get_title() {
                return _title;
            }

            public void set_title(String _title) {
                this._title = _title;
            }

            public String get_msg() {
                return _msg;
            }

            public void set_msg(String _msg) {
                this._msg = _msg;
            }

            public String get_class() {
                return _class;
            }

            public void set_class(String _class) {
                this._class = _class;
            }

            public String get_payType() {
                return _payType;
            }

            public void set_payType(String _payType) {
                this._payType = _payType;
            }
        }

        public static class CartInfoBean {
            /**
             * id : 1304
             * uid : 181
             * type : product
             * product_id : 691
             * product_attr_unique : e096ab0a
             * cart_num : 1
             * add_time : 1548745440
             * is_pay : 0
             * is_del : 0
             * is_new : 0
             * combination_id : 0
             * seckill_id : 0
             * bargain_id : 0
             * productInfo : {"id":691,"image":"https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f7402c.jpg","slider_image":["https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f85c7b.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f7402c.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f6261a.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f51806.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f40243.jpg"],"price":"19.90","cost":"0.00","ot_price":"25.00","vip_price":"0.00","postage":"0.00","mer_id":0,"give_integral":"5.00","cate_id":"69,70,71","sales":7,"stock":398,"store_name":"【磨功夫 茉莉研磨真奶茶 】 原价36，现价19.9包邮 不含植脂末 无色素 无奶精 低卡路里 60g(30g*2包）*2袋","unit_name":"件","is_show":1,"is_del":0,"is_postage":1,"attrInfo":{"product_id":691,"suk":"小,绿","stock":100,"sales":0,"price":"80.00","image":"https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f7402c.jpg","unique":"e096ab0a","cost":"11.00"}}
             * truePrice : 80
             * costPrice : 11
             * trueStock : 100
             * unique : e8005c206b8e46997478ce24afc142e2
             */

            private String id;
            private String uid;
            private String type;
            private String product_id;
            private String product_attr_unique;
            private int cart_num;
            private int add_time;
            private int is_pay;
            private int is_del;
            private int is_new;
            private int combination_id;
            private int seckill_id;
            private int bargain_id;
            private ProductInfoBean productInfo;
            private double truePrice;
            private double costPrice;
            private int trueStock;
            private String unique;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getProduct_attr_unique() {
                return product_attr_unique;
            }

            public void setProduct_attr_unique(String product_attr_unique) {
                this.product_attr_unique = product_attr_unique;
            }

            public int getCart_num() {
                return cart_num;
            }

            public void setCart_num(int cart_num) {
                this.cart_num = cart_num;
            }

            public int getAdd_time() {
                return add_time;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
            }

            public int getIs_pay() {
                return is_pay;
            }

            public void setIs_pay(int is_pay) {
                this.is_pay = is_pay;
            }

            public int getIs_del() {
                return is_del;
            }

            public void setIs_del(int is_del) {
                this.is_del = is_del;
            }

            public int getIs_new() {
                return is_new;
            }

            public void setIs_new(int is_new) {
                this.is_new = is_new;
            }

            public int getCombination_id() {
                return combination_id;
            }

            public void setCombination_id(int combination_id) {
                this.combination_id = combination_id;
            }

            public int getSeckill_id() {
                return seckill_id;
            }

            public void setSeckill_id(int seckill_id) {
                this.seckill_id = seckill_id;
            }

            public int getBargain_id() {
                return bargain_id;
            }

            public void setBargain_id(int bargain_id) {
                this.bargain_id = bargain_id;
            }

            public ProductInfoBean getProductInfo() {
                return productInfo;
            }

            public void setProductInfo(ProductInfoBean productInfo) {
                this.productInfo = productInfo;
            }

            public double getTruePrice() {
                return truePrice;
            }

            public void setTruePrice(double truePrice) {
                this.truePrice = truePrice;
            }

            public double getCostPrice() {
                return costPrice;
            }

            public void setCostPrice(double costPrice) {
                this.costPrice = costPrice;
            }

            public int getTrueStock() {
                return trueStock;
            }

            public void setTrueStock(int trueStock) {
                this.trueStock = trueStock;
            }

            public String getUnique() {
                return unique;
            }

            public void setUnique(String unique) {
                this.unique = unique;
            }

            public static class ProductInfoBean {
                /**
                 * id : 691
                 * image : https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f7402c.jpg
                 * slider_image : ["https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f85c7b.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f7402c.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f6261a.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f51806.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f40243.jpg"]
                 * price : 19.90
                 * cost : 0.00
                 * ot_price : 25.00
                 * vip_price : 0.00
                 * postage : 0.00
                 * mer_id : 0
                 * give_integral : 5.00
                 * cate_id : 69,70,71
                 * sales : 7
                 * stock : 398
                 * store_name : 【磨功夫 茉莉研磨真奶茶 】 原价36，现价19.9包邮 不含植脂末 无色素 无奶精 低卡路里 60g(30g*2包）*2袋
                 * unit_name : 件
                 * is_show : 1
                 * is_del : 0
                 * is_postage : 1
                 * attrInfo : {"product_id":691,"suk":"小,绿","stock":100,"sales":0,"price":"80.00","image":"https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f7402c.jpg","unique":"e096ab0a","cost":"11.00"}
                 */

                private int id;
                private String image;
                private String price;
                private String cost;
                private String ot_price;
                private String vip_price;
                private String postage;
                private int mer_id;
                private String give_integral;
                private String cate_id;
                private int sales;
                private int stock;
                private String store_name;
                private String unit_name;
                private int is_show;
                private int is_del;
                private int is_postage;
                private AttrInfoBean attrInfo;
                private List<String> slider_image;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getCost() {
                    return cost;
                }

                public void setCost(String cost) {
                    this.cost = cost;
                }

                public String getOt_price() {
                    return ot_price;
                }

                public void setOt_price(String ot_price) {
                    this.ot_price = ot_price;
                }

                public String getVip_price() {
                    return vip_price;
                }

                public void setVip_price(String vip_price) {
                    this.vip_price = vip_price;
                }

                public String getPostage() {
                    return postage;
                }

                public void setPostage(String postage) {
                    this.postage = postage;
                }

                public int getMer_id() {
                    return mer_id;
                }

                public void setMer_id(int mer_id) {
                    this.mer_id = mer_id;
                }

                public String getGive_integral() {
                    return give_integral;
                }

                public void setGive_integral(String give_integral) {
                    this.give_integral = give_integral;
                }

                public String getCate_id() {
                    return cate_id;
                }

                public void setCate_id(String cate_id) {
                    this.cate_id = cate_id;
                }

                public int getSales() {
                    return sales;
                }

                public void setSales(int sales) {
                    this.sales = sales;
                }

                public int getStock() {
                    return stock;
                }

                public void setStock(int stock) {
                    this.stock = stock;
                }

                public String getStore_name() {
                    return store_name;
                }

                public void setStore_name(String store_name) {
                    this.store_name = store_name;
                }

                public String getUnit_name() {
                    return unit_name;
                }

                public void setUnit_name(String unit_name) {
                    this.unit_name = unit_name;
                }

                public int getIs_show() {
                    return is_show;
                }

                public void setIs_show(int is_show) {
                    this.is_show = is_show;
                }

                public int getIs_del() {
                    return is_del;
                }

                public void setIs_del(int is_del) {
                    this.is_del = is_del;
                }

                public int getIs_postage() {
                    return is_postage;
                }

                public void setIs_postage(int is_postage) {
                    this.is_postage = is_postage;
                }

                public AttrInfoBean getAttrInfo() {
                    return attrInfo;
                }

                public void setAttrInfo(AttrInfoBean attrInfo) {
                    this.attrInfo = attrInfo;
                }

                public List<String> getSlider_image() {
                    return slider_image;
                }

                public void setSlider_image(List<String> slider_image) {
                    this.slider_image = slider_image;
                }

                public static class AttrInfoBean {
                    /**
                     * product_id : 691
                     * suk : 小,绿
                     * stock : 100
                     * sales : 0
                     * price : 80.00
                     * image : https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f7402c.jpg
                     * unique : e096ab0a
                     * cost : 11.00
                     */

                    private int product_id;
                    private String suk;
                    private int stock;
                    private int sales;
                    private String price;
                    private String image;
                    private String unique;
                    private String cost;

                    public int getProduct_id() {
                        return product_id;
                    }

                    public void setProduct_id(int product_id) {
                        this.product_id = product_id;
                    }

                    public String getSuk() {
                        return suk;
                    }

                    public void setSuk(String suk) {
                        this.suk = suk;
                    }

                    public int getStock() {
                        return stock;
                    }

                    public void setStock(int stock) {
                        this.stock = stock;
                    }

                    public int getSales() {
                        return sales;
                    }

                    public void setSales(int sales) {
                        this.sales = sales;
                    }

                    public String getPrice() {
                        return price;
                    }

                    public void setPrice(String price) {
                        this.price = price;
                    }

                    public String getImage() {
                        return image;
                    }

                    public void setImage(String image) {
                        this.image = image;
                    }

                    public String getUnique() {
                        return unique;
                    }

                    public void setUnique(String unique) {
                        this.unique = unique;
                    }

                    public String getCost() {
                        return cost;
                    }

                    public void setCost(String cost) {
                        this.cost = cost;
                    }
                }
            }
        }
    }
}
