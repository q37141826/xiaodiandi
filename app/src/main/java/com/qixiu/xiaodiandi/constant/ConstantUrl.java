package com.qixiu.xiaodiandi.constant;

import com.qixiu.xiaodiandi.BuildConfig;

/**
 * Created by my on 2018/11/22.
 */

public class ConstantUrl {

    //登录
    public static String loginUrl = BuildConfig.BASE_URL + "/api/home/login";

    //发送验证码
    public static String sendCodeUrl = BuildConfig.BASE_URL + "/api/home/sendsms";
//    phone		是	手机号
//    type		是	类型，1登录（注册，绑定），2换绑

    //退出登录
    public static String outLoginUrl = BuildConfig.BASE_URL + "/api/home/logout";

    //首页
    public static String homeUrl = BuildConfig.BASE_URL + "/api/home/index";
    //首页搜索
    public static String homeSearch = BuildConfig.BASE_URL + "/api/home/search";


    //个人中心
    public static String mineCenter = BuildConfig.BASE_URL + "/api/home/user";

    //签到
    public static String sign = BuildConfig.BASE_URL + "/api/home/signin";
    //编辑用户资料
    public static String editProfile = BuildConfig.BASE_URL + "/api/home/myinfoedit";

    //我的积分
    public static String waterlistUrl = BuildConfig.BASE_URL + "/api/home/waterlist";


    //我的优惠券
    public static String ticketsUrl = BuildConfig.BASE_URL + "/api/home/coupon";


    //我的收藏
    public static String collectionlist = BuildConfig.BASE_URL +  "/api/home/collectionlist";

    //分类类表
    public static String classifyUrl = BuildConfig.BASE_URL + "/api/home/classify";
    //分类商品列表
    public static String productsUrl = BuildConfig.BASE_URL + "/api/home/classifyinfo";

    //商品详情
    public static String shopinfoUrl = BuildConfig.BASE_URL + "/api/home/shopinfo";

    //加入收藏
    public static String addToCollectUrl = BuildConfig.BASE_URL +   "/api/home/collection";
        //寻找相似
        public static String findSimilarUrl = BuildConfig.BASE_URL +     "/api/home/similar";



    //添加购物车
    public static String addShopCarURl = BuildConfig.BASE_URL + "/api/home/joincart";
    //购物车列表
    public static String shopCarListURl = BuildConfig.BASE_URL + "/api/home/cartlist";


    //删除购物车
    public static String ClearShopCarURl = BuildConfig.BASE_URL + "/api/home/cartdel";//todo 以后要更改正确的URL



    //更改数量
    public static String STORE_SHOPCAR_EDIT_COUNT = BuildConfig.BASE_URL + "";
    //删除
    public static String STORE_SHOPCAR_DELECTED = BuildConfig.BASE_URL + "";

    //购物车生成订单
    public static String cartsPayUrl = BuildConfig.BASE_URL + "/api/home/settlement";


    //快速支付
    public static String fastPayUrl = BuildConfig.BASE_URL + "";
    //购物车支付
//    public static String cartsPayUrl = BuildConfig.BASE_URL + "";

    public static String fastPayOrderMakeUrl = BuildConfig.BASE_URL + "";

    public static String cartsPayOrderMakeUrl = BuildConfig.BASE_URL + "";


    //地址
    public static String addressUrl = BuildConfig.BASE_URL + "/api/home/addresslist";
    //添加地址
    public static String addAddressUrl = BuildConfig.BASE_URL + "/api/home/addressadd";
    //删除地址
    public static String addressDelUrl = BuildConfig.BASE_URL + "/api/home/addressdel";


    //取消订单
    public static String cancleOrder = BuildConfig.BASE_URL + "";
    //订单详情
    public static String orderDeleteUrl = BuildConfig.BASE_URL + "";
    //确认收货
    public static String getGoodsUrl = BuildConfig.BASE_URL + "";


    //看物流
    public static String CheckWhereUrl = BuildConfig.BASE_URL + "";

    //支付订单
    public static String payOrderUrl = BuildConfig.BASE_URL + "/api/home/orderpay";

    //订单详情
    public static String orderDetailUrl = BuildConfig.BASE_URL + "";
}
