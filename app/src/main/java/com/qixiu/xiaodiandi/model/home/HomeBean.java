package com.qixiu.xiaodiandi.model.home;

import android.os.Parcel;
import android.os.Parcelable;

import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

public class HomeBean extends BaseBean<HomeBean.OBean> {



    public static class OBean {
        private List<BannerBean> banner;
        private List<CategoryBean> category;
        private List<VipCategoryBean> vip_category;
        private List<ProductBestBean> product_best;
        private List<ProductBean> product;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<CategoryBean> getCategory() {
            return category;
        }

        public void setCategory(List<CategoryBean> category) {
            this.category = category;
        }

        public List<VipCategoryBean> getVip_category() {
            return vip_category;
        }

        public void setVip_category(List<VipCategoryBean> vip_category) {
            this.vip_category = vip_category;
        }

        public List<ProductBestBean> getProduct_best() {
            return product_best;
        }

        public void setProduct_best(List<ProductBestBean> product_best) {
            this.product_best = product_best;
        }

        public List<ProductBean> getProduct() {
            return product;
        }

        public void setProduct(List<ProductBean> product) {
            this.product = product;
        }

        public static class BannerBean {
            /**
             * titile : banner2
             * typeName : 跳转链接
             * type : 2
             * url : http://baidu.com
             * pic : http://xdd.qixiuu.com/uploads/attach/2019/01/21/5c452d578a4b9.png
             */

            private String titile;
            private String typeName;
            private String type;
            private String url;
            private String pic;

            public String getTitile() {
                return titile;
            }

            public void setTitile(String titile) {
                this.titile = titile;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }

        public static class CategoryBean {
            /**
             * id : 62
             * cate_name : 特色食品
             * pic : http://xdd.qixiuu.com/uploads/attach/2019/01/21/s_5c452d578a4b9.png
             */

            private int id;
            private String cate_name;
            private String pic;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCate_name() {
                return cate_name;
            }

            public void setCate_name(String cate_name) {
                this.cate_name = cate_name;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }

        public static class VipCategoryBean implements Parcelable {
            /**
             * id : 69
             * cate_name : 399元超级大礼包
             * pic : http://xdd.qixiuu.com/public/uploads/attach/2019/01/12/s_5c3a0e29b1c7b.jpg
             */

            private int id;
            private String cate_name;
            private String pic;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCate_name() {
                return cate_name;
            }

            public void setCate_name(String cate_name) {
                this.cate_name = cate_name;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeString(this.cate_name);
                dest.writeString(this.pic);
            }

            public VipCategoryBean() {
            }

            protected VipCategoryBean(Parcel in) {
                this.id = in.readInt();
                this.cate_name = in.readString();
                this.pic = in.readString();
            }

            public static final Parcelable.Creator<VipCategoryBean> CREATOR = new Parcelable.Creator<VipCategoryBean>() {
                @Override
                public VipCategoryBean createFromParcel(Parcel source) {
                    return new VipCategoryBean(source);
                }

                @Override
                public VipCategoryBean[] newArray(int size) {
                    return new VipCategoryBean[size];
                }
            };
        }

        public static class ProductBestBean {
            /**
             * id : 698
             * image : https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04ade0c362a.jpg
             * store_name : Barsone朋森 即热式饮水机BS-06H04D
             * store_info :
             * price : 499.00
             */

            private int id;
            private String image;
            private String store_name;
            private String store_info;
            private String price;

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
        }

        public static class ProductBean {
            /**
             * id : 698
             * image : https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04ade0c362a.jpg
             * store_name : Barsone朋森 即热式饮水机BS-06H04D
             * store_info :
             * price : 499.00
             */

            private int id;
            private String image;
            private String store_name;
            private String store_info;
            private String price;

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
        }
    }
}
