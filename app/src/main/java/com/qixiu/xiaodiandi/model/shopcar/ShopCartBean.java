package com.qixiu.xiaodiandi.model.shopcar;

import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;


public class ShopCartBean  extends BaseBean<ShopCartBean.OBean> {


    public static class OBean {
        private List<ValidBean> valid;
        private List<InvalidBean> invalid;

        public List<ValidBean> getValid() {
            return valid;
        }

        public void setValid(List<ValidBean> valid) {
            this.valid = valid;
        }

        public List<InvalidBean> getInvalid() {
            return invalid;
        }

        public void setInvalid(List<InvalidBean> invalid) {
            this.invalid = invalid;
        }

        public static class ValidBean {
            /**
             * id : 1300
             * uid : 181
             * type : product
             * product_id : 692
             * product_attr_unique : 4b78e129
             * cart_num : 1
             * add_time : 1548662512
             * is_pay : 0
             * is_del : 0
             * is_new : 0
             * combination_id : 0
             * seckill_id : 0
             * bargain_id : 0
             * productInfo : {"id":692,"image":"https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a8dcaf953.jpg","slider_image":["https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a8dce1ac7.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a8dcd1008.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a8dcc0003.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a8dcaf953.jpg"],"price":"49.90","cost":"0.00","ot_price":"55.00","vip_price":"0.00","postage":"0.00","mer_id":0,"give_integral":"0.00","cate_id":"62","sales":0,"stock":100,"store_name":"【买三罐送一罐 】九蒸九晒黑芝麻蜜丸 手工制作 柴火慢蒸 12丸/罐/108克 四罐1疗程","unit_name":"件","is_show":1,"is_del":0,"is_postage":1,"attrInfo":{"product_id":692,"suk":"400g","stock":100,"sales":0,"price":"49.90","image":"https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a8dcaf953.jpg","unique":"4b78e129","cost":"80.00"}}
             * truePrice : 49.9
             * costPrice : 80
             * trueStock : 100
             */
            private boolean selected=false;
            private String id;
            private String uid;
            private String type;
            private int product_id;
            private String product_attr_unique;
            private int cart_num;
            private int add_time;
            private int is_pay;
            private int is_del;
            private int is_new;
            private int combination_id;
            private String seckill_id;
            private String bargain_id;
            private ProductInfoBean productInfo;
            private double truePrice;
            private double costPrice;
            private double trueStock;

            public boolean isSelected() {
                return selected;
            }

            public void setSelected(boolean selected) {
                this.selected = selected;
            }

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

            public String getSeckill_id() {
                return seckill_id;
            }

            public void setSeckill_id(String seckill_id) {
                this.seckill_id = seckill_id;
            }

            public String getBargain_id() {
                return bargain_id;
            }

            public void setBargain_id(String bargain_id) {
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

            public double getTrueStock() {
                return trueStock;
            }

            public void setTrueStock(int trueStock) {
                this.trueStock = trueStock;
            }

            public static class ProductInfoBean {
                /**
                 * id : 692
                 * image : https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a8dcaf953.jpg
                 * slider_image : ["https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a8dce1ac7.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a8dcd1008.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a8dcc0003.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a8dcaf953.jpg"]
                 * price : 49.90
                 * cost : 0.00
                 * ot_price : 55.00
                 * vip_price : 0.00
                 * postage : 0.00
                 * mer_id : 0
                 * give_integral : 0.00
                 * cate_id : 62
                 * sales : 0
                 * stock : 100
                 * store_name : 【买三罐送一罐 】九蒸九晒黑芝麻蜜丸 手工制作 柴火慢蒸 12丸/罐/108克 四罐1疗程
                 * unit_name : 件
                 * is_show : 1
                 * is_del : 0
                 * is_postage : 1
                 * attrInfo : {"product_id":692,"suk":"400g","stock":100,"sales":0,"price":"49.90","image":"https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a8dcaf953.jpg","unique":"4b78e129","cost":"80.00"}
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
                     * product_id : 692
                     * suk : 400g
                     * stock : 100
                     * sales : 0
                     * price : 49.90
                     * image : https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a8dcaf953.jpg
                     * unique : 4b78e129
                     * cost : 80.00
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

        public static class InvalidBean {
            /**
             * id : 1301
             * uid : 181
             * type : product
             * product_id : 691
             * product_attr_unique : d3e9ee2e
             * cart_num : 3
             * add_time : 1548653842
             * is_pay : 0
             * is_del : 0
             * is_new : 0
             * combination_id : 0
             * seckill_id : 0
             * bargain_id : 0
             * productInfo : {"id":691,"image":"https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f7402c.jpg","slider_image":["https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f85c7b.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f7402c.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f6261a.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f51806.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f40243.jpg"],"price":"19.90","cost":"0.00","ot_price":"25.00","vip_price":"0.00","postage":"0.00","mer_id":0,"give_integral":"5.00","cate_id":"69,70,71","sales":7,"stock":398,"store_name":"【磨功夫 茉莉研磨真奶茶 】 原价36，现价19.9包邮 不含植脂末 无色素 无奶精 低卡路里 60g(30g*2包）*2袋","unit_name":"件","is_show":1,"is_del":0,"is_postage":1}
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
            private ProductInfoBeanX productInfo;

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

            public ProductInfoBeanX getProductInfo() {
                return productInfo;
            }

            public void setProductInfo(ProductInfoBeanX productInfo) {
                this.productInfo = productInfo;
            }

            public static class ProductInfoBeanX {
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

                public List<String> getSlider_image() {
                    return slider_image;
                }

                public void setSlider_image(List<String> slider_image) {
                    this.slider_image = slider_image;
                }
            }
        }
    }
}
