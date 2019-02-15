package com.qixiu.xiaodiandi.model.order;

import android.os.Parcel;
import android.os.Parcelable;

import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

public class CreateOrderBean extends BaseBean<CreateOrderBean.OBean> {


    public static class OBean implements Parcelable {
        /**
         * usableCoupon :
         * cartInfo : [{"id":1300,"uid":181,"type":"product","product_id":692,"product_attr_unique":"4b78e129","cart_num":2,"add_time":1548653785,"is_pay":0,"is_del":0,"is_new":0,"combination_id":0,"seckill_id":0,"bargain_id":0,"productInfo":{"id":692,"image":"https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a8dcaf953.jpg","slider_image":["https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a8dce1ac7.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a8dcd1008.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a8dcc0003.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a8dcaf953.jpg"],"price":"49.90","cost":"0.00","ot_price":"55.00","vip_price":"0.00","postage":"0.00","mer_id":0,"give_integral":"0.00","cate_id":"62","sales":0,"stock":100,"store_name":"【买三罐送一罐 】九蒸九晒黑芝麻蜜丸 手工制作 柴火慢蒸 12丸/罐/108克 四罐1疗程","unit_name":"件","is_show":1,"is_del":0,"is_postage":1,"attrInfo":{"product_id":692,"suk":"400g","stock":100,"sales":0,"price":"49.90","image":"https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a8dcaf953.jpg","unique":"4b78e129","cost":"80.00"}},"truePrice":49.9,"costPrice":80,"trueStock":100},{"id":1301,"uid":181,"type":"product","product_id":691,"product_attr_unique":"d3e9ee2e","cart_num":3,"add_time":1548653842,"is_pay":0,"is_del":0,"is_new":0,"combination_id":0,"seckill_id":0,"bargain_id":0,"productInfo":{"id":691,"image":"https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f7402c.jpg","slider_image":["https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f85c7b.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f7402c.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f6261a.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f51806.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f40243.jpg"],"price":"19.90","cost":"0.00","ot_price":"25.00","vip_price":"0.00","postage":"0.00","mer_id":0,"give_integral":"5.00","cate_id":"69,70,71","sales":7,"stock":398,"store_name":"【磨功夫 茉莉研磨真奶茶 】 原价36，现价19.9包邮 不含植脂末 无色素 无奶精 低卡路里 60g(30g*2包）*2袋","unit_name":"件","is_show":1,"is_del":0,"is_postage":1,"attrInfo":{"product_id":691,"suk":"小,绿","stock":100,"sales":0,"price":"100.00","image":"https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f7402c.jpg","unique":"d3e9ee2e","cost":"11.00"}},"truePrice":100,"costPrice":11,"trueStock":100}]
         * priceGroup : {"storePostage":0,"storeFreePostage":20,"totalPrice":"399.80","costPrice":"193.00"}
         * orderKey : 22d067d13220f7140504fc0d081131e1
         */

        private PriceGroupBean priceGroup;
        private String orderKey;
        private List<CartInfoBean> cartInfo;
        /**
         * usableCoupon : {"id":140,"cid":93,"uid":181,"coupon_title":"测试优惠","coupon_price":"0.01","use_min_price":"0.02","add_time":1550199487,"end_time":1551927487,"use_time":0,"type":"get","status":0,"is_fail":0}
         */

//        private UsableCouponBean usableCoupon;


        public PriceGroupBean getPriceGroup() {
            return priceGroup;
        }

        public void setPriceGroup(PriceGroupBean priceGroup) {
            this.priceGroup = priceGroup;
        }

        public String getOrderKey() {
            return orderKey;
        }

        public void setOrderKey(String orderKey) {
            this.orderKey = orderKey;
        }

        public List<CartInfoBean> getCartInfo() {
            return cartInfo;
        }

        public void setCartInfo(List<CartInfoBean> cartInfo) {
            this.cartInfo = cartInfo;
        }

//        public UsableCouponBean getUsableCoupon() {
//            return usableCoupon;
//        }
//
//        public void setUsableCoupon(UsableCouponBean usableCoupon) {
//            this.usableCoupon = usableCoupon;
//        }

        public static class PriceGroupBean implements Parcelable {
            /**
             * storePostage : 0
             * storeFreePostage : 20
             * totalPrice : 399.80
             * costPrice : 193.00
             */

            private int storePostage;
            private int storeFreePostage;
            private String totalPrice;
            private String costPrice;

            public int getStorePostage() {
                return storePostage;
            }

            public void setStorePostage(int storePostage) {
                this.storePostage = storePostage;
            }

            public int getStoreFreePostage() {
                return storeFreePostage;
            }

            public void setStoreFreePostage(int storeFreePostage) {
                this.storeFreePostage = storeFreePostage;
            }

            public String getTotalPrice() {
                return totalPrice;
            }

            public void setTotalPrice(String totalPrice) {
                this.totalPrice = totalPrice;
            }

            public String getCostPrice() {
                return costPrice;
            }

            public void setCostPrice(String costPrice) {
                this.costPrice = costPrice;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.storePostage);
                dest.writeInt(this.storeFreePostage);
                dest.writeString(this.totalPrice);
                dest.writeString(this.costPrice);
            }

            public PriceGroupBean() {
            }

            protected PriceGroupBean(Parcel in) {
                this.storePostage = in.readInt();
                this.storeFreePostage = in.readInt();
                this.totalPrice = in.readString();
                this.costPrice = in.readString();
            }

            public static final Creator<PriceGroupBean> CREATOR = new Creator<PriceGroupBean>() {
                @Override
                public PriceGroupBean createFromParcel(Parcel source) {
                    return new PriceGroupBean(source);
                }

                @Override
                public PriceGroupBean[] newArray(int size) {
                    return new PriceGroupBean[size];
                }
            };
        }

        public static class CartInfoBean implements Parcelable {



            /**
             * id : 1300
             * uid : 181
             * type : product
             * product_id : 692
             * product_attr_unique : 4b78e129
             * cart_num : 2
             * add_time : 1548653785
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
            private double costPrice;
            private int trueStock;

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

            public static class ProductInfoBean implements Parcelable {



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

                public static class AttrInfoBean implements Parcelable {



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

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {
                        dest.writeInt(this.product_id);
                        dest.writeString(this.suk);
                        dest.writeInt(this.stock);
                        dest.writeInt(this.sales);
                        dest.writeString(this.price);
                        dest.writeString(this.image);
                        dest.writeString(this.unique);
                        dest.writeString(this.cost);
                    }

                    public AttrInfoBean() {
                    }

                    protected AttrInfoBean(Parcel in) {
                        this.product_id = in.readInt();
                        this.suk = in.readString();
                        this.stock = in.readInt();
                        this.sales = in.readInt();
                        this.price = in.readString();
                        this.image = in.readString();
                        this.unique = in.readString();
                        this.cost = in.readString();
                    }

                    public static final Creator<AttrInfoBean> CREATOR = new Creator<AttrInfoBean>() {
                        @Override
                        public AttrInfoBean createFromParcel(Parcel source) {
                            return new AttrInfoBean(source);
                        }

                        @Override
                        public AttrInfoBean[] newArray(int size) {
                            return new AttrInfoBean[size];
                        }
                    };
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(this.id);
                    dest.writeString(this.image);
                    dest.writeString(this.price);
                    dest.writeString(this.cost);
                    dest.writeString(this.ot_price);
                    dest.writeString(this.vip_price);
                    dest.writeString(this.postage);
                    dest.writeInt(this.mer_id);
                    dest.writeString(this.give_integral);
                    dest.writeString(this.cate_id);
                    dest.writeInt(this.sales);
                    dest.writeInt(this.stock);
                    dest.writeString(this.store_name);
                    dest.writeString(this.unit_name);
                    dest.writeInt(this.is_show);
                    dest.writeInt(this.is_del);
                    dest.writeInt(this.is_postage);
                    dest.writeParcelable(this.attrInfo, flags);
                    dest.writeStringList(this.slider_image);
                }

                public ProductInfoBean() {
                }

                protected ProductInfoBean(Parcel in) {
                    this.id = in.readInt();
                    this.image = in.readString();
                    this.price = in.readString();
                    this.cost = in.readString();
                    this.ot_price = in.readString();
                    this.vip_price = in.readString();
                    this.postage = in.readString();
                    this.mer_id = in.readInt();
                    this.give_integral = in.readString();
                    this.cate_id = in.readString();
                    this.sales = in.readInt();
                    this.stock = in.readInt();
                    this.store_name = in.readString();
                    this.unit_name = in.readString();
                    this.is_show = in.readInt();
                    this.is_del = in.readInt();
                    this.is_postage = in.readInt();
                    this.attrInfo = in.readParcelable(AttrInfoBean.class.getClassLoader());
                    this.slider_image = in.createStringArrayList();
                }

                public static final Creator<ProductInfoBean> CREATOR = new Creator<ProductInfoBean>() {
                    @Override
                    public ProductInfoBean createFromParcel(Parcel source) {
                        return new ProductInfoBean(source);
                    }

                    @Override
                    public ProductInfoBean[] newArray(int size) {
                        return new ProductInfoBean[size];
                    }
                };
            }

            public CartInfoBean() {
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeInt(this.uid);
                dest.writeString(this.type);
                dest.writeInt(this.product_id);
                dest.writeString(this.product_attr_unique);
                dest.writeInt(this.cart_num);
                dest.writeInt(this.add_time);
                dest.writeInt(this.is_pay);
                dest.writeInt(this.is_del);
                dest.writeInt(this.is_new);
                dest.writeInt(this.combination_id);
                dest.writeInt(this.seckill_id);
                dest.writeInt(this.bargain_id);
                dest.writeParcelable(this.productInfo, flags);
                dest.writeDouble(this.truePrice);
                dest.writeDouble(this.costPrice);
                dest.writeInt(this.trueStock);
            }

            protected CartInfoBean(Parcel in) {
                this.id = in.readInt();
                this.uid = in.readInt();
                this.type = in.readString();
                this.product_id = in.readInt();
                this.product_attr_unique = in.readString();
                this.cart_num = in.readInt();
                this.add_time = in.readInt();
                this.is_pay = in.readInt();
                this.is_del = in.readInt();
                this.is_new = in.readInt();
                this.combination_id = in.readInt();
                this.seckill_id = in.readInt();
                this.bargain_id = in.readInt();
                this.productInfo = in.readParcelable(ProductInfoBean.class.getClassLoader());
                this.truePrice = in.readDouble();
                this.costPrice = in.readDouble();
                this.trueStock = in.readInt();
            }

            public static final Creator<CartInfoBean> CREATOR = new Creator<CartInfoBean>() {
                @Override
                public CartInfoBean createFromParcel(Parcel source) {
                    return new CartInfoBean(source);
                }

                @Override
                public CartInfoBean[] newArray(int size) {
                    return new CartInfoBean[size];
                }
            };
        }

        public OBean() {
        }

        public static class UsableCouponBean implements Parcelable {



            /**
             * id : 140
             * cid : 93
             * uid : 181
             * coupon_title : 测试优惠
             * coupon_price : 0.01
             * use_min_price : 0.02
             * add_time : 1550199487
             * end_time : 1551927487
             * use_time : 0
             * type : get
             * status : 0
             * is_fail : 0
             */

            private int id;
            private int cid;
            private int uid;
            private String coupon_title;
            private String coupon_price;
            private String use_min_price;
            private int add_time;
            private int end_time;
            private int use_time;
            private String type;
            private int status;
            private int is_fail;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCid() {
                return cid;
            }

            public void setCid(int cid) {
                this.cid = cid;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getCoupon_title() {
                return coupon_title;
            }

            public void setCoupon_title(String coupon_title) {
                this.coupon_title = coupon_title;
            }

            public String getCoupon_price() {
                return coupon_price;
            }

            public void setCoupon_price(String coupon_price) {
                this.coupon_price = coupon_price;
            }

            public String getUse_min_price() {
                return use_min_price;
            }

            public void setUse_min_price(String use_min_price) {
                this.use_min_price = use_min_price;
            }

            public int getAdd_time() {
                return add_time;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
            }

            public int getEnd_time() {
                return end_time;
            }

            public void setEnd_time(int end_time) {
                this.end_time = end_time;
            }

            public int getUse_time() {
                return use_time;
            }

            public void setUse_time(int use_time) {
                this.use_time = use_time;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getIs_fail() {
                return is_fail;
            }

            public void setIs_fail(int is_fail) {
                this.is_fail = is_fail;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeInt(this.cid);
                dest.writeInt(this.uid);
                dest.writeString(this.coupon_title);
                dest.writeString(this.coupon_price);
                dest.writeString(this.use_min_price);
                dest.writeInt(this.add_time);
                dest.writeInt(this.end_time);
                dest.writeInt(this.use_time);
                dest.writeString(this.type);
                dest.writeInt(this.status);
                dest.writeInt(this.is_fail);
            }

            public UsableCouponBean() {
            }

            protected UsableCouponBean(Parcel in) {
                this.id = in.readInt();
                this.cid = in.readInt();
                this.uid = in.readInt();
                this.coupon_title = in.readString();
                this.coupon_price = in.readString();
                this.use_min_price = in.readString();
                this.add_time = in.readInt();
                this.end_time = in.readInt();
                this.use_time = in.readInt();
                this.type = in.readString();
                this.status = in.readInt();
                this.is_fail = in.readInt();
            }

            public static final Creator<UsableCouponBean> CREATOR = new Creator<UsableCouponBean>() {
                @Override
                public UsableCouponBean createFromParcel(Parcel source) {
                    return new UsableCouponBean(source);
                }

                @Override
                public UsableCouponBean[] newArray(int size) {
                    return new UsableCouponBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(this.priceGroup, flags);
            dest.writeString(this.orderKey);
            dest.writeTypedList(this.cartInfo);
//            dest.writeParcelable(this.usableCoupon, flags);
        }

        protected OBean(Parcel in) {
            this.priceGroup = in.readParcelable(PriceGroupBean.class.getClassLoader());
            this.orderKey = in.readString();
            this.cartInfo = in.createTypedArrayList(CartInfoBean.CREATOR);
//            this.usableCoupon = in.readParcelable(UsableCouponBean.class.getClassLoader());
        }

        public static final Creator<OBean> CREATOR = new Creator<OBean>() {
            @Override
            public OBean createFromParcel(Parcel source) {
                return new OBean(source);
            }

            @Override
            public OBean[] newArray(int size) {
                return new OBean[size];
            }
        };
    }
}
