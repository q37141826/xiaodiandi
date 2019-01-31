package com.qixiu.xiaodiandi.model.home.goodsdetails;

import android.os.Parcel;
import android.os.Parcelable;

import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

//
public class GoodsDetailsBean    extends BaseBean<GoodsDetailsBean.OBean> {



    public static class OBean {
        /**
         * product : {"id":691,"image":"https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f7402c.jpg","video":"/public/uploads/video/2019/01/21/5c45dd292763e.mp4","slider_image":["https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f85c7b.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f7402c.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f6261a.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f51806.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f40243.jpg"],"store_name":"【磨功夫 茉莉研磨真奶茶 】 原价36，现价19.9包邮 不含植脂末 无色素 无奶精 低卡路里 60g(30g*2包）*2袋","store_info":"好的","price":"19.90","ot_price":"25.00","description":"<p><img src=\"https://shop.qixiuu.com/public/uploads/editor/20181203/5c04a862c8a0c.jpg\" style=\"\"/><\/p><p><img src=\"https://shop.qixiuu.com/public/uploads/editor/20181203/5c04a86c995b2.jpg\" style=\"\"/><\/p><p><br/><\/p>","collect":2,"cartnum":0}
         * result : {"charc":[{"product_id":691,"attr_name":"大小","attr_values":["大","小"]},{"product_id":691,"attr_name":"颜色","attr_values":["红","绿"]}],"list":[{"product_id":691,"suk":"大,红","stock":100,"sales":0,"price":"100.00","image":"https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f7402c.jpg","unique":"f818993a","cost":"11.00"},{"product_id":691,"suk":"大,绿","stock":100,"sales":0,"price":"100.00","image":"https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f7402c.jpg","unique":"ede13d67","cost":"11.00"},{"product_id":691,"suk":"小,红","stock":100,"sales":0,"price":"100.00","image":"https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f7402c.jpg","unique":"3e15a9ba","cost":"11.00"},{"product_id":691,"suk":"小,绿","stock":100,"sales":0,"price":"100.00","image":"https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f7402c.jpg","unique":"d3e9ee2e","cost":"11.00"}]}
         */

        private ProductBean product;
        private ResultBean result;

        public ProductBean getProduct() {
            return product;
        }

        public void setProduct(ProductBean product) {
            this.product = product;
        }

        public ResultBean getResult() {
            return result;
        }

        public void setResult(ResultBean result) {
            this.result = result;
        }

        public static class ProductBean {
            /**
             * id : 691
             * image : https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f7402c.jpg
             * video : /public/uploads/video/2019/01/21/5c45dd292763e.mp4
             * slider_image : ["https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f85c7b.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f7402c.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f6261a.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f51806.jpg","https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f40243.jpg"]
             * store_name : 【磨功夫 茉莉研磨真奶茶 】 原价36，现价19.9包邮 不含植脂末 无色素 无奶精 低卡路里 60g(30g*2包）*2袋
             * store_info : 好的
             * price : 19.90
             * ot_price : 25.00
             * description : <p><img src="https://shop.qixiuu.com/public/uploads/editor/20181203/5c04a862c8a0c.jpg" style=""/></p><p><img src="https://shop.qixiuu.com/public/uploads/editor/20181203/5c04a86c995b2.jpg" style=""/></p><p><br/></p>
             * collect : 2
             * cartnum : 0
             */

            private int id;
            private String image;
            private String video;
            private String store_name;
            private String store_info;
            private String price;
            private String ot_price;
            private String description;
            private int collect;
            private int cartnum;
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

            public String getVideo() {
                return video;
            }

            public void setVideo(String video) {
                this.video = video;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getStore_info() {
                return store_info;
            }

            public void setStore_info(String store_info) {
                this.store_info = store_info;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getOt_price() {
                return ot_price;
            }

            public void setOt_price(String ot_price) {
                this.ot_price = ot_price;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getCollect() {
                return collect;
            }

            public void setCollect(int collect) {
                this.collect = collect;
            }

            public int getCartnum() {
                return cartnum;
            }

            public void setCartnum(int cartnum) {
                this.cartnum = cartnum;
            }

            public List<String> getSlider_image() {
                return slider_image;
            }

            public void setSlider_image(List<String> slider_image) {
                this.slider_image = slider_image;
            }
        }

        public static class ResultBean {
            private List<CharcBean> charc;
            private List<ListBean> list;

            public List<CharcBean> getCharc() {
                return charc;
            }

            public void setCharc(List<CharcBean> charc) {
                this.charc = charc;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class CharcBean {
                /**
                 * product_id : 691
                 * attr_name : 大小
                 * attr_values : ["大","小"]
                 */

                private int product_id;
                private String attr_name;
                private List<String> attr_values;
                private List<CharctorInnerBean>  inners;

                public List<CharctorInnerBean> getInners() {
                    return inners;
                }

                public void setInners(List<CharctorInnerBean> inners) {
                    this.inners = inners;
                }

                public int getProduct_id() {
                    return product_id;
                }

                public void setProduct_id(int product_id) {
                    this.product_id = product_id;
                }

                public String getAttr_name() {
                    return attr_name;
                }

                public void setAttr_name(String attr_name) {
                    this.attr_name = attr_name;
                }

                public List<String> getAttr_values() {
                    return attr_values;
                }

                public void setAttr_values(List<String> attr_values) {
                    this.attr_values = attr_values;
                }
            }

            public static class ListBean implements Parcelable {



                /**
                 * product_id : 691
                 * suk : 大,红
                 * stock : 100
                 * sales : 0
                 * price : 100.00
                 * image : https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f7402c.jpg
                 * unique : f818993a
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

                public ListBean() {
                }

                protected ListBean(Parcel in) {
                    this.product_id = in.readInt();
                    this.suk = in.readString();
                    this.stock = in.readInt();
                    this.sales = in.readInt();
                    this.price = in.readString();
                    this.image = in.readString();
                    this.unique = in.readString();
                    this.cost = in.readString();
                }

                public static final Parcelable.Creator<ListBean> CREATOR = new Parcelable.Creator<ListBean>() {
                    @Override
                    public ListBean createFromParcel(Parcel source) {
                        return new ListBean(source);
                    }

                    @Override
                    public ListBean[] newArray(int size) {
                        return new ListBean[size];
                    }
                };
            }
        }
    }
}
