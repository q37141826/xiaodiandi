package com.qixiu.xiaodiandi.ui.activity.mine.order;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.Alipay;
import com.alipay.PayResult;
import com.alipay.interf.AliBean;
import com.alipay.interf.IPay;
import com.qixiu.qixiu.application.AppManager;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.request.parameter.StringConstants;
import com.qixiu.qixiu.utils.CommonUtils;
import com.qixiu.qixiu.utils.Preference;
import com.qixiu.qixiu.utils.ToastUtil;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantRequest;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.constant.IntentDataKeyConstant;
import com.qixiu.xiaodiandi.model.login.LoginStatus;
import com.qixiu.xiaodiandi.model.order.CreateOrderBean;
import com.qixiu.xiaodiandi.model.order.FastPayNewBean;
import com.qixiu.xiaodiandi.model.order.OrderPayData;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;
import com.qixiu.xiaodiandi.ui.activity.home.ConfirmOrderActivity;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wxpay.PlatformConfigConstant;
import com.wxpay.WeixinPayModel;

import java.util.HashMap;
import java.util.Map;

public class SelectPayMethoedActivity extends RequestActivity implements IPay {
    private ImageView imageView_alipay, imageView_weixinpay;
    private RelativeLayout relativeLayout_alipay, relativeLayout_weixinpay;
    private int type;
    //微信支付成功后的广播
    BroadcastReceiver receiver;
    private TextView textView_howmuch;
    private RelativeLayout relativeLayout_yinlianpay;
    private ImageView imageView_yinlian_selected;
    private OrderPayData payData;
    private FastPayNewBean fastPayNewBean;

    @Override

    protected void onInitViewNew() {
        imageView_alipay = (ImageView) findViewById(R.id.imageView_alipay);
        imageView_weixinpay = (ImageView) findViewById(R.id.imageView_weixinpay);
        imageView_yinlian_selected = (ImageView) findViewById(R.id.imageView_weixin_selected);
        relativeLayout_alipay = (RelativeLayout) findViewById(R.id.relativeLayout_alipay);
        relativeLayout_weixinpay = (RelativeLayout) findViewById(R.id.relativeLayout_weixinpay);
        textView_howmuch = (TextView) findViewById(R.id.textView_howmuch);
        relativeLayout_yinlianpay = (RelativeLayout) findViewById(R.id.relativeLayout_yinlianpay);
        setClick();
    }

    private void setClick() {
        imageView_alipay.setOnClickListener(this);
        imageView_weixinpay.setOnClickListener(this);
        relativeLayout_alipay.setOnClickListener(this);
        relativeLayout_weixinpay.setOnClickListener(this);
        relativeLayout_yinlianpay.setOnClickListener(this);
        imageView_yinlian_selected.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView_alipay:
            case R.id.relativeLayout_alipay:
                type = 1;
                imageView_alipay.setImageResource(R.mipmap.shopcar_goods_select);
                imageView_yinlian_selected.setImageResource(R.mipmap.shopcar_goods_notselect);
                imageView_weixinpay.setImageResource(R.mipmap.shopcar_goods_notselect);
                getOrderData();
                break;
            case R.id.imageView_weixinpay:
            case R.id.relativeLayout_weixinpay:
                type = 2;
                imageView_weixinpay.setImageResource(R.mipmap.shopcar_goods_notselect);
                imageView_alipay.setImageResource(R.mipmap.shopcar_goods_notselect);
                getOrderData();
                break;

            case R.id.relativeLayout_yinlianpay:
            case R.id.imageView_weixin_selected:
                type = 2;
                imageView_yinlian_selected.setImageResource(R.mipmap.shopcar_goods_select);
                imageView_alipay.setImageResource(R.mipmap.shopcar_goods_notselect);
                getOrderData();
                break;
        }
    }

    private void getOrderData() {
        if (payData == null) {
            //如果还没加入购物车 ，那么先加入购物车
            ConstantRequest.addTopShopCart(okHttpRequestModel, fastPayNewBean.getGotoAddCartsData().getProdeuctId(),
                    fastPayNewBean.getGotoAddCartsData().getBuyNum(), fastPayNewBean.getGotoAddCartsData().getUnique());
        } else {
            startGetPaData();
        }
    }

    private void startGetPaData() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", LoginStatus.getId() + "");
        map.put("paytype", type + "");
        map.put("key", payData.getKey() + "");
        map.put("address", payData.getAddress() + "");
        CommonUtils.putDataIntoMap(map, "coupon", payData.getCoupon());

        CommonUtils.putDataIntoMap(map, "integral", payData.getIntegral());
        BaseBean bean;
        if (type == 1) {
            bean = new AliBean();
        } else {
            bean = new WeixinPayModel();
        }
        post(ConstantUrl.payOrderUrl, map, bean);


    }

    private void setEnable(boolean f) {
        imageView_alipay.setEnabled(f);
        relativeLayout_alipay.setEnabled(f);
        imageView_weixinpay.setEnabled(f);
        relativeLayout_weixinpay.setEnabled(f);
    }

    @Override
    protected void onInitData() {
        mTitleView.setTitle("支付方式");
        mTitleView.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        try {
            //这个地方有太多界面进来
            payData = getIntent().getParcelableExtra(IntentDataKeyConstant.DATA);
            textView_howmuch.setText(("¥" + payData.getMoney()).replace("¥¥", "¥"));
        } catch (Exception e) {
        }
        try {
            fastPayNewBean = getIntent().getParcelableExtra(IntentDataKeyConstant.DATA);
            textView_howmuch.setText(("¥" + fastPayNewBean.getGotoAddCartsData().getMoney()).replace("¥¥", "¥"));
        } catch (Exception e) {
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(IntentDataKeyConstant.BROADCAST_PAY_SHOPCARREFRESH_ACTION
        );
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                finish();
            }
        };
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_select_pay_methoed;
    }


    @Override
    public void onSuccess(BaseBean data) {
        if (type == 2) {
            if (data instanceof WeixinPayModel) {
                WeixinPayModel bean = (WeixinPayModel) data;
                startWeixinPay(bean);
            }
        }
        if (type == 1) {
            if (data instanceof AliBean) {
                AliBean aliBean = (AliBean) data;
                startAlipay(aliBean);
            }
        }
        if (data.getUrl().equals(ConstantUrl.addShopCarURl)) {
            Map<String, String> map = new HashMap<>();
            map.put("id", data.getO().toString());
            post(ConstantUrl.cartsPayUrl, map, new CreateOrderBean());
        }
        if (data instanceof CreateOrderBean) {
            //加入购物车之后,确认订单
            CreateOrderBean createOrderBean = (CreateOrderBean) data;
            payData = fastPayNewBean.getOrderPayData();
            payData.setKey(createOrderBean.getO().getOrderKey());
            payData.setMoney(createOrderBean.getO().getPriceGroup().getCostPrice());
//                orderPayData.setCoupon();  //todo 这两个地方后续要补上
//                orderPayData.setIntegral();
            startGetPaData();
        }
    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {

    }


    private void startAlipay(AliBean bean) {
        new Alipay(this, this).startPay(bean.getO());
    }


    //支付宝支付成功
    @Override
    public void onSuccess(String msg) {
        try {
            AppManager.getAppManager().finishActivity(ConfirmOrderActivity.class);
        } catch (Exception e) {
        }
        finish();
    }

    @Override
    public void onFailure(PayResult payResult) {

    }

    private void startWeixinPay(WeixinPayModel baseModel) {
        IWXAPI wxapi = WXAPIFactory.createWXAPI(mContext, PlatformConfigConstant.WEIXIN_APP_ID);
        wxapi.registerApp(PlatformConfigConstant.WEIXIN_APP_ID);
        PayReq payReq = new PayReq();
        payReq.appId = PlatformConfigConstant.WEIXIN_APP_ID;
        payReq.partnerId = baseModel.getO().getPartnerid();
        payReq.prepayId = baseModel.getO().getPrepayid();
        payReq.packageValue = baseModel.getO().getPackageX();
        payReq.nonceStr = baseModel.getO().getNoncestr();
        payReq.timeStamp = baseModel.getO().getTimestamp() + "";
        payReq.sign = baseModel.getO().getSign();
        payReq.extData = "app data";
        wxapi.sendReq(payReq);
        if (payReq.prepayId.equals("")) {
            ToastUtil.showToast(mContext, "支付失败,请联系客服");
        }
        Log.e("prepayid", payReq.prepayId + "---");
        Preference.put(StringConstants.OR_DERID, baseModel.getO().getOrder_id());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }


}
