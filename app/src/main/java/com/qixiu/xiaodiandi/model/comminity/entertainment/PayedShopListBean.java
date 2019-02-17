package com.qixiu.xiaodiandi.model.comminity.entertainment;

import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

public class PayedShopListBean extends BaseBean<List<PayedShopListBean.OBean>> {



    public static class OBean {
        /**
         * id : 1324
         * sid : 698
         * store_name : Barsone朋森 即热式饮水机BS-06H04D
         * image : https://shop.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04ade0c362a.jpg
         */

        private int id;
        private int sid;
        private String store_name;
        private String image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
