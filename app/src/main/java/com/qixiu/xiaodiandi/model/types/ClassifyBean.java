package com.qixiu.xiaodiandi.model.types;

import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

public class ClassifyBean extends BaseBean<List<ClassifyBean.OBean>> {




    public static class OBean {
        /**
         * id : 68
         * cate_name : 会员专区
         */
        private  boolean  selected=false;

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        private int id;
        private String cate_name;

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
    }
}
