package com.qixiu.xiaodiandi.model.home.goodsdetails;

public class CharctorInnerBean {

    private boolean selected;

    private String text;
    
    private String attr_name;

    public String getAttr_name() {
        return attr_name;
    }

    public void setAttr_name(String attr_name) {
        this.attr_name = attr_name;
    }

    public CharctorInnerBean() {
    }

    public CharctorInnerBean(boolean selected, String text) {
        this.selected = selected;
        this.text = text;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
