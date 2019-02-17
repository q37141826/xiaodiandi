package com.qixiu.xiaodiandi.constant;

import com.qixiu.qixiu.request.OKHttpRequestModel;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.utils.CommonUtils;
import com.qixiu.xiaodiandi.model.login.LoginStatus;
import com.qixiu.xiaodiandi.model.order.CreateOrderBean;
import com.qixiu.xiaodiandi.model.shopcar.ShopCartBean;
import com.qixiu.xiaodiandi.ui.activity.home.confirm_order.AddressBean;

import java.util.HashMap;
import java.util.Map;

public class ConstantRequest {

    public static Map<String, String> getMap() {
        Map<String, String> map = new HashMap<>();
        CommonUtils.putDataIntoMap(map, "uid", LoginStatus.getId());
        return map;
    }


    //添加购物车
    public static void addTopShopCart(OKHttpRequestModel okHttpRequestModel, String sid, String num, String unique) {
        Map<String, String> map = new HashMap<>();
        CommonUtils.putDataIntoMap(map, "uid", LoginStatus.getId());
        CommonUtils.putDataIntoMap(map, "sid", sid);
        CommonUtils.putDataIntoMap(map, "num", num);
        CommonUtils.putDataIntoMap(map, "unique", unique);
        okHttpRequestModel.okhHttpPost(ConstantUrl.addShopCarURl, map, new BaseBean());
    }


    public static void getShopCarList(OKHttpRequestModel okHttpRequestModel) {
        okHttpRequestModel.okhHttpPost(ConstantUrl.shopCarListURl, getMap(), new ShopCartBean());
    }

    public static void cartsToOrder(OKHttpRequestModel okHttpRequestModel, String ids) {
        Map map = getMap();
        map.put("id", ids);
        okHttpRequestModel.okhHttpPost(ConstantUrl.cartsPayUrl, map, new CreateOrderBean());
    }


    public static void getAddressList(OKHttpRequestModel okHttpRequestModel) {
        Map<String, String> map = getMap();
//        map.put("pageNo", pageNo + "");
//        map.put("pageSize", pageSize + "");
        AddressBean bean = new AddressBean();
        okHttpRequestModel.okhHttpPost(ConstantUrl.addressUrl, map, bean);
    }

    public static void leaveMessage(OKHttpRequestModel okHttpRequestModel, String url, String type, String nid, String content) {
        leaveMessage(okHttpRequestModel, url, type, nid, content, "", "");
    }

    public static void leaveMessage(OKHttpRequestModel okHttpRequestModel, String url, String type, String nid, String content, String chat, String hid) {
        //	类型1视频专题，2公司动态，3最新资讯，4娱乐社区
        Map<String, String> map = getMap();
        map.put("type", type);
        map.put("nid", nid);
        map.put("content", content);
        CommonUtils.putDataIntoMap(map, "chat", chat);
        CommonUtils.putDataIntoMap(map, "hid", hid);
        okHttpRequestModel.okhHttpPost(url, map, new BaseBean());
    }


}
