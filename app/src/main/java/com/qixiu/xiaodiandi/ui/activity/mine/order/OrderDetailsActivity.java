package com.qixiu.xiaodiandi.ui.activity.mine.order;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alipay.Alipay;
import com.alipay.PayResult;
import com.alipay.interf.AliBean;
import com.alipay.interf.IPay;
import com.qixiu.qixiu.request.OKHttpRequestModel;
import com.qixiu.qixiu.request.OKHttpUIUpdataListener;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.request.parameter.StringConstants;
import com.qixiu.qixiu.utils.Preference;
import com.qixiu.qixiu.utils.ToastUtil;
import com.qixiu.wigit.GotoView;
import com.qixiu.wigit.show_dialog.ArshowDialog;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantString;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.constant.IntentDataKeyConstant;
import com.qixiu.xiaodiandi.model.login.LoginStatus;
import com.qixiu.xiaodiandi.model.order.RefreshListBean;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.TitleActivity;
import com.qixiu.xiaodiandi.ui.fragment.mine.order.OrderDetailsAdapter;
import com.qixiu.xiaodiandi.ui.fragment.mine.order.OrderDetailsBean;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wxpay.PlatformConfigConstant;
import com.wxpay.WeixinPayModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by HuiHeZe on 2017/5/3.
 */

public class OrderDetailsActivity extends TitleActivity implements View.OnClickListener, OKHttpUIUpdataListener<BaseBean>, IPay {
    private OrderDetailsAdapter adapter;
    //订单的状态参数
    private String order_id = "";
    private Button btn_deleteOrder_detail, btn_payOrder_detail, btn_notice_send_detail, btn_checkwhere_detail, btn_getConform_detail, btn_cacleOrder_detail, btn_change;
    private RecyclerView recycleView_goods_in_order;
    private List<Button> buttons = new ArrayList<>();
    private TextView
            textView_order_state, textView_goodsNum;
    OrderDetailsBean orderDetailsBean;
    //判断是删除订单还是确认收货
    boolean IS_DELETE = false;
    OKHttpRequestModel okHttpRequestModel;
    private String status;
    TextView textView_goodstotolprice_detail;
    TextView textView_orderid_detail;
    TextView textView_createTime_orderdetail;
    TextView textViewName;
    GotoView gotoAddress;

    LinearLayout linearLayoutTimeDown;

    @Override
    protected void onInitData() {
        okHttpRequestModel = new OKHttpRequestModel(this);
        mTitleView.setTitle("订单详情");
        mTitleView.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        order_id = getIntent().getStringExtra(IntentDataKeyConstant.DATA);
        adapter = new OrderDetailsAdapter();
        recycleView_goods_in_order.setAdapter(adapter);
        recycleView_goods_in_order.setLayoutManager(new LinearLayoutManager(this));
        //设置删除订单等点击事件
        setClick();
//        //隐藏所有button
//        setVisble();
        //将请求的订单数据放入控件
        getData();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_order_details;
    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("oid", order_id);
        map.put("uid", LoginStatus.getId());
        OrderDetailsBean bean = new OrderDetailsBean();
        okHttpRequestModel.okhHttpPost(ConstantUrl.orderDetailUrl, map, bean);
    }


    private void setVisble() {
        for (Button btn :
                buttons) {
            btn.setVisibility(View.GONE);
        }
        linearLayoutTimeDown.setVisibility(View.GONE);
        //全部订单  其他状态都在全部里面
//待付款  判断paid=0
//待发货  paid=1 status=0
//待收货 paid=1 status=1
//已完成 paid=1 status=2
        String textState = "";
        if ((0 == orderDetailsBean.getO().getPaid())) {
            btn_cacleOrder_detail.setVisibility(View.VISIBLE);
            btn_payOrder_detail.setVisibility(View.VISIBLE);
            linearLayoutTimeDown.setVisibility(View.VISIBLE);
            textState = "待付款";
        } else if (1 == orderDetailsBean.getO().getStatus()) {
//            btn_checkwhere_detail.setVisibility(View.VISIBLE);// TODO: 2019/2/14  以后有了检查物流的功能再打开
            btn_getConform_detail.setVisibility(View.VISIBLE);
            btn_change.setVisibility(View.VISIBLE);
            textState = "待收货";
        } else if (0 == orderDetailsBean.getO().getStatus()) {
            textState = "待发货";
            btn_notice_send_detail.setVisibility(View.VISIBLE);
        } else if (5 == (orderDetailsBean.getO().getStatus())) {
            textState = "换货中";
        } else if (2 == orderDetailsBean.getO().getStatus()) {
            textState = "已完成";
        }
        textView_order_state.setText(textState);
    }

    private void setClick() {
        btn_deleteOrder_detail.setOnClickListener(this);
        btn_payOrder_detail.setOnClickListener(this);
        btn_notice_send_detail.setOnClickListener(this);
        btn_checkwhere_detail.setOnClickListener(this);
        btn_getConform_detail.setOnClickListener(this);
        btn_cacleOrder_detail.setOnClickListener(this);
        btn_change.setOnClickListener(this);
        buttons.add(btn_deleteOrder_detail);
        buttons.add(btn_payOrder_detail);
        buttons.add(btn_notice_send_detail);
        buttons.add(btn_checkwhere_detail);
        buttons.add(btn_getConform_detail);
        buttons.add(btn_cacleOrder_detail);
        buttons.add(btn_change);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_deleteOrder_detail:
            case R.id.btn_cacleOrder_detail:
                IS_DELETE = true;
                setDialog("确认取消订单？");
                break;
            case R.id.btn_payOrder_detail:
                startpay();
                break;
            case R.id.btn_notice_send_detail:
                startNotice();
                break;
            case R.id.btn_checkwhere_detail:
                Intent intent = new Intent(this, CheckWhereActivity.class);
                intent.putExtra("order_id", orderDetailsBean.getO().getId());
                startActivity(intent);
                break;
            case R.id.btn_getConform_detail:
                IS_DELETE = false;
                setDialog("确认收货吗？");
                break;
        }
    }


    //    private void startpay() {
//        Intent intent = new Intent(mContext, SelectPayMethoedActivity.class);
//        //// TODO: 2017/9/18 放入订单id
//        intent.putExtra(IntentDataKeyConstant.ID, orderDetailsBean.getO().getId());
//        intent.putExtra("orderid", orderDetailsBean.getO().getId());
//        intent.putExtra(IntentDataKeyConstant.MONEY, (orderDetailsBean.getO().getTotal_price()));
//        mContext.startActivity(intent);
//    }
    private void startpay() {
        Map<String, String> map = new HashMap<>();
        map.put("key", orderDetailsBean.getO().getOrder_id() + "");
        map.put("uid", LoginStatus.getId());
        map.put("paytype", orderDetailsBean.getO().getPay_type());
        BaseBean baseBean;
        if (orderDetailsBean.getO().getPay_type().equals("1")) {
            baseBean = new AliBean();
        } else {
            baseBean = new WeixinPayModel();
        }
        okHttpRequestModel.okhHttpPost(ConstantUrl.payOrderUrl, map, baseBean);
    }

    private void startGetGoods() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", LoginStatus.getId());
        map.put("oid", orderDetailsBean.getO().getId() + "");
        okHttpRequestModel.okhHttpPost(ConstantUrl.getGoodsUrl, map, new BaseBean());
    }

    private void startNotice() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", LoginStatus.getId());
        map.put("oid", orderDetailsBean.getO().getId() + "");
        okHttpRequestModel.okhHttpPost(ConstantUrl.remindUrl, map, new BaseBean());
    }

    private void startDeleteOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", LoginStatus.getId());
        map.put("oid", orderDetailsBean.getO().getOrder_id() + "");
        okHttpRequestModel.okhHttpPost(ConstantUrl.orderDeleteUrl, map, new BaseBean());
    }

    private void setDialog(String message) {
        ArshowDialog.Builder builder = new ArshowDialog.Builder(this);
        builder.setCanceledOnTouchOutside(false);
        builder.setCancelable(false);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (IS_DELETE) {
                    startDeleteOrder();
                } else {
                    startGetGoods();
                }
                dialog.dismiss();
            }
        });

        builder.setMessage(message);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }


    @Override
    protected void onInitViewNew() {
        textView_goodsNum = (TextView) findViewById(R.id.textView_goodsNum);
        textView_orderid_detail = (TextView) findViewById(R.id.textView_orderid_detail);
        textView_createTime_orderdetail = (TextView) findViewById(R.id.textView_createTime_orderdetail);
        linearLayoutTimeDown = findViewById(R.id.linearLayoutTimeDown);
//        textView_payIsOver = (TextView) findViewById(R.id.textView_payIsOver);
        textView_order_state = (TextView) findViewById(R.id.textView_order_state);
        btn_deleteOrder_detail = (Button) findViewById(R.id.btn_deleteOrder_detail);
        btn_payOrder_detail = (Button) findViewById(R.id.btn_payOrder_detail);
        btn_notice_send_detail = (Button) findViewById(R.id.btn_notice_send_detail);
        btn_checkwhere_detail = (Button) findViewById(R.id.btn_checkwhere_detail);
        btn_getConform_detail = (Button) findViewById(R.id.btn_getConform_detail);
        btn_cacleOrder_detail = (Button) findViewById(R.id.btn_cacleOrder_detail);
        btn_change = (Button) findViewById(R.id.btn_change);
        recycleView_goods_in_order = (RecyclerView) findViewById(R.id.recycleView_goods_in_order);
        textView_goodstotolprice_detail = findViewById(R.id.textView_goodstotolprice_detail);
        textViewName = findViewById(R.id.textViewName);
        gotoAddress = findViewById(R.id.gotoAddress);
        textView_goodstotolprice_detail = findViewById(R.id.textView_goodstotolprice_detail);
    }

    @Override
    public void onSuccess(BaseBean data, int i) {
        if (data instanceof OrderDetailsBean) {
            orderDetailsBean = (OrderDetailsBean) data;
            status = orderDetailsBean.getO().getStatus() + "";
            order_id = orderDetailsBean.getO().getId() + "";
            adapter.refreshData(orderDetailsBean.getO().getCartInfo());
            textView_goodstotolprice_detail.setText(ConstantString.RMB_SYMBOL + "    " + orderDetailsBean.getO().getTotal_price());
            textView_orderid_detail.setText(orderDetailsBean.getO().getOrder_id() + "");
            textView_createTime_orderdetail.setText(orderDetailsBean.getO().getAdd_time());
            textView_goodsNum.setText(orderDetailsBean.getO().getTotal_num() + "");
            textViewName.setText(orderDetailsBean.getO().getReal_name());
            gotoAddress.setFirstText(orderDetailsBean.getO().getUser_address());
            setVisble();
        }
        if (ConstantUrl.getGoodsUrl.equals(data.getUrl())) {
            ToastUtil.toast(data.getM());
            getData();
        }
        if (ConstantUrl.orderDeleteUrl.equals(data.getUrl())) {
            ToastUtil.toast(data.getM());
            EventBus.getDefault().post(new RefreshListBean());
            finish();
        }
        if (ConstantUrl.remindUrl.equals(data.getUrl())) {
            ToastUtil.toast(data.getM());
        }

        if (data instanceof WeixinPayModel) {
            WeixinPayModel bean = (WeixinPayModel) data;
            startWeixinPay(bean);
        }
        if (data instanceof AliBean) {
            AliBean aliBean = (AliBean) data;
            startAlipay(aliBean);
        }
    }

    private void startAlipay(AliBean bean) {
        new Alipay(this, this).startPay(bean.getO());
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
    public void onError(Call call, Exception e, int i) {
        ToastUtil.toast(R.string.link_error);

    }

    @Override
    public void onFailure(C_CodeBean c_codeBean) {
        ToastUtil.toast(c_codeBean.getM());
    }

    @Override
    public void onSuccess(String msg) {
        EventBus.getDefault().post(new RefreshListBean());
        finish();
    }

    @Override
    public void onFailure(PayResult payResult) {
        ToastUtil.toast(payResult.getResult());
    }
}
