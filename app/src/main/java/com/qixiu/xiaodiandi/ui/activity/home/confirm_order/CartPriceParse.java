package com.qixiu.xiaodiandi.ui.activity.home.confirm_order;

import com.qixiu.xiaodiandi.model.order.CreateOrderBean;
import com.qixiu.xiaodiandi.model.shopcar.ShopCartBean;

public class CartPriceParse {


    public static String getPrice(CreateOrderBean.OBean.CartInfoBean.ProductInfoBean bean){
        String price = 0 + "";
        if (bean.getAttrInfo() == null) {
            price = bean.getPrice() + "";
        } else {
            price =bean.getAttrInfo().getPrice();
        }
        return price;
    }
    public static String getPrice(ShopCartBean.OBean.ValidBean.ProductInfoBean bean){
        String price = 0 + "";
        if (bean.getAttrInfo() == null) {
            price = bean.getPrice() + "";
        } else {
            price =bean.getAttrInfo().getPrice();
        }
        return price;
    }
}
