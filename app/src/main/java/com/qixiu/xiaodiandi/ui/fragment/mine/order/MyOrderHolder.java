package com.qixiu.xiaodiandi.ui.fragment.mine.order;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.AliPayBean;
import com.alipay.PayResult;
import com.alipay.interf.IPay;
import com.qixiu.qixiu.recyclerview_lib.OnRecyclerItemListener;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.qixiu.request.OKHttpRequestModel;
import com.qixiu.qixiu.request.OKHttpUIUpdataListener;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.request.parameter.StringConstants;
import com.qixiu.qixiu.utils.Preference;
import com.qixiu.qixiu.utils.ToastUtil;
import com.qixiu.wigit.show_dialog.ArshowDialog;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantString;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.constant.IntentDataKeyConstant;
import com.qixiu.xiaodiandi.ui.activity.mine.order.CheckWhereActivity;
import com.qixiu.xiaodiandi.ui.activity.mine.order.OrderDetailsActivity;
import com.qixiu.xiaodiandi.ui.activity.mine.order.SelectPayMethoedActivity;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wxpay.PlatformConfigConstant;
import com.wxpay.WeixinPayModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by HuiHeZe on 2017/5/3.
 */

public class MyOrderHolder extends RecyclerBaseHolder<OrderBean.OBean.ListBean> implements View.OnClickListener, IPay, OKHttpUIUpdataListener<BaseBean> {
    AlertDialog dialog;
    Intent intent;
    RelativeLayout framelayout_myorder;
    OrderListDetailsAdapter adapterl;
    RecyclerView recycleView_myoreder_inner;
    Button btn_deleteOrder, btn_payThisOrder, btn_notice_send, btn_checkwhere_list, btn_getConform_list, btn_cancleOrder, btn_change;
    List<Button> buttons = new ArrayList<>();
    TextView textView_order_finish;
    private MyOrderAdapter.MyOrderRefreshListener myOrderRefreshListener;
    //支付参数
    String order_id, pay_code;
    AliPayBean aliPayBean;
    IWXAPI wxapi;
    //弹窗的操作针对谁，是收货还是删除订单？
    boolean IS_DELETE = false;
    private int position;
    //
    private TextView textView_order_goods_num, textView_totolPrice, textView_orderCode;
    private OKHttpRequestModel okHttpRequestModel;

    public MyOrderHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
        super(itemView, context, adapter);
        recycleView_myoreder_inner = (RecyclerView) itemView.findViewById(R.id.recycleView_myoreder_inner);
        btn_deleteOrder = (Button) itemView.findViewById(R.id.btn_deleteOrder);
        btn_payThisOrder = (Button) itemView.findViewById(R.id.btn_payThisOrder);
        btn_notice_send = (Button) itemView.findViewById(R.id.btn_notice_send);
        btn_checkwhere_list = (Button) itemView.findViewById(R.id.btn_checkwhere_list);
        btn_getConform_list = (Button) itemView.findViewById(R.id.btn_getConform_list);
        btn_cancleOrder = (Button) itemView.findViewById(R.id.btn_cancleOrder);
        btn_change = (Button) itemView.findViewById(R.id.btn_change);
        textView_order_finish = (TextView) itemView.findViewById(R.id.textView_order_finish);
        framelayout_myorder = (RelativeLayout) itemView.findViewById(R.id.relativelayout_myorder);
        buttons.add(btn_deleteOrder);
        buttons.add(btn_payThisOrder);
        buttons.add(btn_notice_send);
        buttons.add(btn_checkwhere_list);
        buttons.add(btn_getConform_list);
        buttons.add(btn_cancleOrder);
        buttons.add(btn_change);
        textView_order_goods_num = (TextView) itemView.findViewById(R.id.textView_order_goods_num);
        textView_totolPrice = (TextView) itemView.findViewById(R.id.textView_totolPrice);
        textView_orderCode = (TextView) itemView.findViewById(R.id.textView_orderCode);

    }

    public void setMyOrderRefreshListener(MyOrderAdapter.MyOrderRefreshListener myOrderRefreshListener) {
        this.myOrderRefreshListener = myOrderRefreshListener;
    }

    @Override
    public void bindHolder(int position) {
        okHttpRequestModel = new OKHttpRequestModel(this);
        textView_order_goods_num.setText("共计" + mData.getTotalnum() + "件商品");
        textView_totolPrice.setText("¥" + mData.getTotalprice());
        textView_orderCode.setText("订单编号:" + mData.getOid());
        this.position = position;
        adapterl = new OrderListDetailsAdapter();
        //
        adapterl.refreshData(mData.getItems());
        adapterl.setOnItemClickListener(new OnRecyclerItemListener<OrderBean.OBean.ListBean.ItemsBean>() {
                                            @Override
                                            public void onItemClick(View v, RecyclerView.Adapter adapter, OrderBean.OBean.ListBean.ItemsBean data) {
                                                Intent intent = new Intent(mContext, OrderDetailsActivity.class);
                                                intent.putExtra(IntentDataKeyConstant.ORDER_ID, mData.getId());
                                                intent.putExtra("order_status", mData.getStatus());
                                                mContext.startActivity(intent);
                                            }
                                        }
        );
        recycleView_myoreder_inner.setLayoutManager(new LinearLayoutManager(mContext));
        recycleView_myoreder_inner.setAdapter(adapterl);
        btn_deleteOrder.setOnClickListener(this);
        btn_payThisOrder.setOnClickListener(this);
        btn_notice_send.setOnClickListener(this);
        framelayout_myorder.setOnClickListener(this);
        btn_checkwhere_list.setOnClickListener(this);
        btn_getConform_list.setOnClickListener(this);
        btn_cancleOrder.setOnClickListener(this);
        btn_change.setOnClickListener(this);
        order_id = mData.getId();
        setButtonVisible();
    }

    private void setButtonVisible() {
        for (Button btn : buttons) {
            btn.setVisibility(View.GONE);
        }
        String textState = "";
        if (("1").equals(mData.getStatus())) {
            btn_cancleOrder.setVisibility(View.VISIBLE);
            btn_payThisOrder.setVisibility(View.VISIBLE);
            btn_change.setVisibility(View.VISIBLE);
            textState = "待付款";
        } else if ("3".equals(mData.getStatus())) {
            btn_checkwhere_list.setVisibility(View.VISIBLE);
            btn_getConform_list.setVisibility(View.VISIBLE);
            textState = "待收货";
        } else if ("2".equals(mData.getStatus())) {
            btn_change.setVisibility(View.VISIBLE);
            textState = "已付款";
        } else if ("8".equals(mData.getStatus())) {
            btn_deleteOrder.setVisibility(View.VISIBLE);
            textState = "待评价";
        } else if ("5".equals(mData.getStatus())) {
//            btn_giveComments.setVisibility(View.VISIBLE);
            btn_deleteOrder.setVisibility(View.VISIBLE);
            btn_change.setVisibility(View.VISIBLE);
            textState = "已完成";
        } else if ("0".equals(mData.getStatus())) {
//            btn_giveComments.setVisibility(View.VISIBLE);
            btn_deleteOrder.setVisibility(View.VISIBLE);
            btn_change.setVisibility(View.VISIBLE);
            textState = "已取消";
        }
        textView_order_finish.setText(textState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relativelayout_myorder:
                break;

            case R.id.btn_deleteOrder:
                IS_DELETE = true;
                setDialog("确认删除订单？");
                break;
            case R.id.btn_payThisOrder:
                startPay();
                break;
            case R.id.btn_notice_send:
                if (mData.getStatus().equals("2")) {
                    ToastUtil.showToast(mContext, "您已经提醒发货，请勿重复提醒");
                    return;
                }
                startNotice();
                break;
            case R.id.btn_checkwhere_list:
                Intent intentCheckWhere = new Intent(mContext, CheckWhereActivity.class);
                intentCheckWhere.putExtra("order_id", mData.getId());
                mContext.startActivity(intentCheckWhere);
                break;
            case R.id.btn_getConform_list:
                IS_DELETE = false;
                setDialog("确认收货吗？");
                break;
            case R.id.btn_cancleOrder:
                startCancleOrder();
                break;
        }
    }

    private void startCancleOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("orderid", mData.getId());
        map.put("vipid", Preference.get(ConstantString.USERID, ""));
        okHttpRequestModel.okhHttpPost(ConstantUrl.cancleOrder, map, new BaseBean());
    }

    private void startPay() {
        intent = new Intent(mContext, SelectPayMethoedActivity.class);
        //// TODO: 2017/9/18 放入订单id
        intent.putExtra("orderid", mData.getId());
        intent.putExtra("money", mData.getTotalprice());
        mContext.startActivity(intent);
    }

    private void startNotice() {

    }

    private void startDeleteOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("vipid", Preference.get(ConstantString.USERID, ""));
        map.put("orderid", mData.getId());
        okHttpRequestModel.okhHttpPost(ConstantUrl.orderDeleteUrl, map, new BaseBean());
    }

    private void setDialog(String message) {
        ArshowDialog.Builder builder = new ArshowDialog.Builder(mContext);
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

    private void startGetGoods() {
        Map<String, String> map = new HashMap<>();
        map.put("vipid", Preference.get(ConstantString.USERID, ""));
        map.put("orderid", mData.getId());
        okHttpRequestModel.okhHttpPost(ConstantUrl.getGoodsUrl, map, new BaseBean());
    }

    @Override
    public void onSuccess(String msg) {

    }

    @Override
    public void onFailure(PayResult payResult) {

    }


    private void startWeixinPay(WeixinPayModel baseModel) {
        wxapi = WXAPIFactory.createWXAPI(mContext, PlatformConfigConstant.WEIXIN_APP_ID);
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
        Preference.put(StringConstants.PAY_NAME, pay_code);
        Preference.put(StringConstants.OR_DERID, baseModel.getO().getOrder_id());

    }


    //接口数据
    @Override
    public void onSuccess(BaseBean data, int i) {
        if (ConstantUrl.cancleOrder.equals(data.getUrl()) || ConstantUrl.getGoodsUrl.equals(data.getUrl()) || ConstantUrl.orderDeleteUrl.equals(data.getUrl())) {
            myOrderRefreshListener.onOrderRefresh(mData, ConstantString.ACTION_REFRSH);
        }


        ToastUtil.toast(data.getM());
    }

    @Override
    public void onError(Call call, Exception e, int i) {

    }

    @Override
    public void onFailure(C_CodeBean c_codeBean) {

    }
}
