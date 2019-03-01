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

    /**
     * @param
     * @param okHttpRequestModel 请求体，绑定了进度view
     * @param url                动态的文本
     * @param type               分享的url连接
     * @param nid                分享的第三方SDK名字 比如QQ.NAME
     * @param content            分享的第三方SDK名字 比如QQ.NAME
     * @param chat               分享的第三方SDK名字 比如QQ.NAME
     * @param hid                分享的第三方SDK名字 比如QQ.NAME
     */
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

    /**
     * @param okHttpRequestModel
     * @param url                动态的文本
     * @param id                 娱乐社区ID
     * @param type               类型：1收藏，2转发
     * @param state              收藏状态，1收藏2取消
     */
    public static void collectionOrTrans(OKHttpRequestModel okHttpRequestModel, String url, String id, String type, String state) {
        Map<String, String> map = getMap();
        map.put("type", type);
        map.put("id", id);
        map.put("state", state);
        okHttpRequestModel.okhHttpPost(url, map, new BaseBean());
    }

    /**
     * @param okHttpRequestModel
     * @param url                动态的文本
     * @param type               类型1视频专题，2公司动态，3最新资讯，4点滴学院
     * @param pageNo             当前页数
     * @param pageSize           条数
     * @param baseBean           返回的类型
     */
    public static void newsListRequest(OKHttpRequestModel okHttpRequestModel, String url, String type, String pageNo, String pageSize, BaseBean baseBean) {
        Map<String, String> map = getMap();
        map.put("type", type);
        map.put("pageNo", pageNo);
        map.put("pageSize", pageSize);
        okHttpRequestModel.okhHttpPost(url, map, baseBean);
    }

    /**
     * @param okHttpRequestModel
     * @param url                动态的文本
     * @param type               类型1视频专题，2公司动态，3最新资讯，4点滴学院
     * @param nid                详情ID
     * @param baseBean           返回的类型
     */
    public static void newsDetailsRequest(OKHttpRequestModel okHttpRequestModel, String url, String type, String nid, BaseBean baseBean) {
        Map<String, String> map = getMap();
        map.put("type", type);
        map.put("nid", nid);
        okHttpRequestModel.okhHttpPost(url, map, baseBean);
    }


    /**
     * @param okHttpRequestModel
     * @param url                动态的文本
     * @param type               类型1视频专题，2公司动态，3最新资讯，4娱乐社区
     * @param nid                列表id
     * @param content            是	留言内容
     * @param chat               否	回复ID--4娱乐社区生效
     * @param hid                否	回复用户ID--4娱乐社区生效
     * @param baseBean           返回的类型
     */
    public static void leaveComments(OKHttpRequestModel okHttpRequestModel, String url, String type, String nid, String content,
                                     String chat, String hid, BaseBean baseBean) {
        Map<String, String> map = getMap();
        //ConstantUrl.leaveCommentsUrl
        map.put("type", type);
        map.put("nid", nid);
        map.put("content", content);
        CommonUtils.putDataIntoMap(map, "chat", chat);
        CommonUtils.putDataIntoMap(map, "hid", hid);
        okHttpRequestModel.okhHttpPost(url,map,baseBean );
    }
    /**
     * @param okHttpRequestModel
     * @param url                动态的文本
     * @param type                type		是	类型：1获取二维码用户信息，2扫码执行
     * @param key                  key		是	二维码值
     * @param baseBean           返回的类型
     */
    public static void bindFirends(OKHttpRequestModel okHttpRequestModel, String url,String type,String key, BaseBean baseBean){
        Map<String, String> map = getMap();
        map.put("type", type);
        map.put("key", key);
        okHttpRequestModel.okhHttpPost(url,map,baseBean );
    }

}
