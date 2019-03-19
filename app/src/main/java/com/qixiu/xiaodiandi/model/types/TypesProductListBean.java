package com.qixiu.xiaodiandi.model.types;

import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

public class TypesProductListBean extends BaseBean<TypesProductListBean.OBean> {


    public static class OBean {
        private List<BannerBean> banner;
        private List<CategoryBean> category;

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

        public static class BannerBean {
            /**
             * title : banner
             * typeName : 跳转商品
             * type : 1
             * url : 1
             * pic : http://www.water.com/public/uploads/attach/2018/12/03/5c04cdbee91e7.png
             * sort : 2
             */

            private String title;
            private String typeName;
            private String type;
            private String url;
            private String pic;
            private String sort;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
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

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }
        }

        public static class CategoryBean {
            /**
             * id : 69
             * cate_name : 399元超级大礼包
             * product : [{"id":698,"image":"https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04ade0c362a.jpg","store_name":"Barsone朋森 即热式饮水机BS-06H04D","store_info":"","price":"499.00","cate_id":"65,69"},{"id":691,"image":"https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04a75f7402c.jpg","store_name":"【磨功夫 茉莉研磨真奶茶 】 原价36，现价19.9包邮 不含植脂末 无色素 无奶精 低卡路里 60g(30g*2包）*2袋","store_info":"好的","price":"19.90","cate_id":"69,70,71"}]
             */

            private int id;
            private String cate_name;
            private List<ProductBean> product;
                private BannerBean bannerBean;

            public BannerBean getBannerBean() {
                return bannerBean;
            }

            public void setBannerBean(BannerBean bannerBean) {
                this.bannerBean = bannerBean;
            }

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

            public List<ProductBean> getProduct() {
                return product;
            }

            public void setProduct(List<ProductBean> product) {
                this.product = product;
            }

            public static class ProductBean {
                /**
                 * id : 698
                 * image : https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04ade0c362a.jpg
                 * store_name : Barsone朋森 即热式饮水机BS-06H04D
                 * store_info :
                 * price : 499.00
                 * cate_id : 65,69
                 */

                private int id;
                private String image;
                private String store_name;
                private String store_info;
                private String price;
                private String cate_id;


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

                public String getCate_id() {
                    return cate_id;
                }

                public void setCate_id(String cate_id) {
                    this.cate_id = cate_id;
                }
            }
        }
    }
}
