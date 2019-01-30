package com.qixiu.xiaodiandi.model.mine.collection;

import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

public class SimilarBean extends BaseBean< List<SimilarBean.OBean>> {




    public static class OBean {
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
