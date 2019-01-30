package com.qixiu.xiaodiandi.ui.fragment.mine.order;


import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

/**
 * Created by HuiHeZe on 2017/5/3.
 */

public class OrderDetailsBean extends BaseBean<OrderDetailsBean.OBean> {

    public static class OBean {
        /**
         * id : 745
         * order_id : wx2019013016282210005
         * uid : 181
         * real_name : 和呵呵呵呵
         * user_phone : 13000000000
         * user_address : 内蒙古自治区 呼和浩特市 新城区 啦啦啦拉拉裤
         * cart_id : [1307]
         * total_num : 2
         * total_price : 0.02
         * total_postage : 0.00
         * pay_price : 0.02
         * pay_postage : 0.00
         * deduction_price : 0.00
         * coupon_id : 0
         * coupon_price : 0.00
         * paid : 0
         * pay_time :
         * pay_type : 1
         * add_time : 1548836902
         * status : 0
         * refund_status : 0
         * refund_reason_wap_img :
         * refund_reason_wap_explain :
         * refund_reason_time :
         * refund_reason_wap :
         * refund_reason :
         * refund_price : 0.00
         * delivery_name :
         * delivery_type :
         * delivery_id :
         * gain_integral : 0.00
         * use_integral : 0.00
         * back_integral :
         * mark :
         * is_del : 0
         * unique : b03d2a0270c9b03a7aa403b60d5f69f9
         * remark :
         * mer_id : 0
         * is_mer_check : 0
         * combination_id : 0
         * pink_id : 0
         * cost : 400.00
         * seckill_id : 0
         * bargain_id : 0
         * is_channel : 0
         * cartInfo : [{"id":1307,"uid":181,"type":"product","product_id":698,"product_attr_unique":"e4598983","cart_num":2,"add_time":1548836886,"is_pay":0,"is_del":0,"is_new":0,"combination_id":0,"seckill_id":0,"bargain_id":0,"productInfo":{"id":698,"image":"https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04ade0c362a.jpg","slider_image":["https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04ade0d3535.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04ade0c362a.jpg"],"price":"499.00","cost":"0.00","ot_price":"550.00","vip_price":"0.00","postage":"0.00","mer_id":0,"give_integral":"0.00","cate_id":"65,69","sales":7,"stock":94,"store_name":"Barsone朋森 即热式饮水机BS-06H04D","unit_name":"个","is_show":1,"is_del":0,"is_postage":1,"attrInfo":{"product_id":698,"suk":"10L","stock":93,"sales":1,"price":"0.01","image":"https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04ade0c362a.jpg","unique":"e4598983","cost":"200.00"}},"truePrice":0.01,"costPrice":200,"trueStock":93,"unique":"b8687871f51fb14d80adb26f71c7b307"}]
         * _status : {"_type":0,"_title":"未支付","_msg":"立即支付订单吧","_class":"nobuy","_payType":"支付宝"}
         */

        private int id;
        private String order_id;
        private int uid;
        private String real_name;
        private String user_phone;
        private String user_address;
        private int total_num;
        private String total_price;
        private String total_postage;
        private String pay_price;
        private String pay_postage;
        private String deduction_price;
        private int coupon_id;
        private String coupon_price;
        private int paid;
        private String pay_time;
        private String pay_type;
        private int add_time;
        private int status;
        private int refund_status;
        private String refund_reason_wap_img;
        private String refund_reason_wap_explain;
        private String refund_reason_time;
        private String refund_reason_wap;
        private String refund_reason;
        private String refund_price;
        private String delivery_name;
        private String delivery_type;
        private String delivery_id;
        private String gain_integral;
        private String use_integral;
        private String back_integral;
        private String mark;
        private int is_del;
        private String unique;
        private String remark;
        private int mer_id;
        private int is_mer_check;
        private int combination_id;
        private int pink_id;
        private String cost;
        private int seckill_id;
        private int bargain_id;
        private int is_channel;
        private StatusBean _status;
        private List<Integer> cart_id;
        private List<CartInfoBean> cartInfo;

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

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getUser_address() {
            return user_address;
        }

        public void setUser_address(String user_address) {
            this.user_address = user_address;
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

        public String getTotal_postage() {
            return total_postage;
        }

        public void setTotal_postage(String total_postage) {
            this.total_postage = total_postage;
        }

        public String getPay_price() {
            return pay_price;
        }

        public void setPay_price(String pay_price) {
            this.pay_price = pay_price;
        }

        public String getPay_postage() {
            return pay_postage;
        }

        public void setPay_postage(String pay_postage) {
            this.pay_postage = pay_postage;
        }

        public String getDeduction_price() {
            return deduction_price;
        }

        public void setDeduction_price(String deduction_price) {
            this.deduction_price = deduction_price;
        }

        public int getCoupon_id() {
            return coupon_id;
        }

        public void setCoupon_id(int coupon_id) {
            this.coupon_id = coupon_id;
        }

        public String getCoupon_price() {
            return coupon_price;
        }

        public void setCoupon_price(String coupon_price) {
            this.coupon_price = coupon_price;
        }

        public int getPaid() {
            return paid;
        }

        public void setPaid(int paid) {
            this.paid = paid;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
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

        public String getRefund_reason_wap_img() {
            return refund_reason_wap_img;
        }

        public void setRefund_reason_wap_img(String refund_reason_wap_img) {
            this.refund_reason_wap_img = refund_reason_wap_img;
        }

        public String getRefund_reason_wap_explain() {
            return refund_reason_wap_explain;
        }

        public void setRefund_reason_wap_explain(String refund_reason_wap_explain) {
            this.refund_reason_wap_explain = refund_reason_wap_explain;
        }

        public String getRefund_reason_time() {
            return refund_reason_time;
        }

        public void setRefund_reason_time(String refund_reason_time) {
            this.refund_reason_time = refund_reason_time;
        }

        public String getRefund_reason_wap() {
            return refund_reason_wap;
        }

        public void setRefund_reason_wap(String refund_reason_wap) {
            this.refund_reason_wap = refund_reason_wap;
        }

        public String getRefund_reason() {
            return refund_reason;
        }

        public void setRefund_reason(String refund_reason) {
            this.refund_reason = refund_reason;
        }

        public String getRefund_price() {
            return refund_price;
        }

        public void setRefund_price(String refund_price) {
            this.refund_price = refund_price;
        }

        public String getDelivery_name() {
            return delivery_name;
        }

        public void setDelivery_name(String delivery_name) {
            this.delivery_name = delivery_name;
        }

        public String getDelivery_type() {
            return delivery_type;
        }

        public void setDelivery_type(String delivery_type) {
            this.delivery_type = delivery_type;
        }

        public String getDelivery_id() {
            return delivery_id;
        }

        public void setDelivery_id(String delivery_id) {
            this.delivery_id = delivery_id;
        }

        public String getGain_integral() {
            return gain_integral;
        }

        public void setGain_integral(String gain_integral) {
            this.gain_integral = gain_integral;
        }

        public String getUse_integral() {
            return use_integral;
        }

        public void setUse_integral(String use_integral) {
            this.use_integral = use_integral;
        }

        public String getBack_integral() {
            return back_integral;
        }

        public void setBack_integral(String back_integral) {
            this.back_integral = back_integral;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public int getIs_del() {
            return is_del;
        }

        public void setIs_del(int is_del) {
            this.is_del = is_del;
        }

        public String getUnique() {
            return unique;
        }

        public void setUnique(String unique) {
            this.unique = unique;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getMer_id() {
            return mer_id;
        }

        public void setMer_id(int mer_id) {
            this.mer_id = mer_id;
        }

        public int getIs_mer_check() {
            return is_mer_check;
        }

        public void setIs_mer_check(int is_mer_check) {
            this.is_mer_check = is_mer_check;
        }

        public int getCombination_id() {
            return combination_id;
        }

        public void setCombination_id(int combination_id) {
            this.combination_id = combination_id;
        }

        public int getPink_id() {
            return pink_id;
        }

        public void setPink_id(int pink_id) {
            this.pink_id = pink_id;
        }

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
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

        public int getIs_channel() {
            return is_channel;
        }

        public void setIs_channel(int is_channel) {
            this.is_channel = is_channel;
        }

        public StatusBean get_status() {
            return _status;
        }

        public void set_status(StatusBean _status) {
            this._status = _status;
        }

        public List<Integer> getCart_id() {
            return cart_id;
        }

        public void setCart_id(List<Integer> cart_id) {
            this.cart_id = cart_id;
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
             * id : 1307
             * uid : 181
             * type : product
             * product_id : 698
             * product_attr_unique : e4598983
             * cart_num : 2
             * add_time : 1548836886
             * is_pay : 0
             * is_del : 0
             * is_new : 0
             * combination_id : 0
             * seckill_id : 0
             * bargain_id : 0
             * productInfo : {"id":698,"image":"https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04ade0c362a.jpg","slider_image":["https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04ade0d3535.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04ade0c362a.jpg"],"price":"499.00","cost":"0.00","ot_price":"550.00","vip_price":"0.00","postage":"0.00","mer_id":0,"give_integral":"0.00","cate_id":"65,69","sales":7,"stock":94,"store_name":"Barsone朋森 即热式饮水机BS-06H04D","unit_name":"个","is_show":1,"is_del":0,"is_postage":1,"attrInfo":{"product_id":698,"suk":"10L","stock":93,"sales":1,"price":"0.01","image":"https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04ade0c362a.jpg","unique":"e4598983","cost":"200.00"}}
             * truePrice : 0.01
             * costPrice : 200
             * trueStock : 93
             * unique : b8687871f51fb14d80adb26f71c7b307
             */

            private int id;
            private int uid;
            private String type;
            private int product_id;
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
            private int costPrice;
            private int trueStock;
            private String unique;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
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

            public int getCostPrice() {
                return costPrice;
            }

            public void setCostPrice(int costPrice) {
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
                 * id : 698
                 * image : https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04ade0c362a.jpg
                 * slider_image : ["https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04ade0d3535.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04ade0c362a.jpg"]
                 * price : 499.00
                 * cost : 0.00
                 * ot_price : 550.00
                 * vip_price : 0.00
                 * postage : 0.00
                 * mer_id : 0
                 * give_integral : 0.00
                 * cate_id : 65,69
                 * sales : 7
                 * stock : 94
                 * store_name : Barsone朋森 即热式饮水机BS-06H04D
                 * unit_name : 个
                 * is_show : 1
                 * is_del : 0
                 * is_postage : 1
                 * attrInfo : {"product_id":698,"suk":"10L","stock":93,"sales":1,"price":"0.01","image":"https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04ade0c362a.jpg","unique":"e4598983","cost":"200.00"}
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
                     * product_id : 698
                     * suk : 10L
                     * stock : 93
                     * sales : 1
                     * price : 0.01
                     * image : https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04ade0c362a.jpg
                     * unique : e4598983
                     * cost : 200.00
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
