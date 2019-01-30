package com.qixiu.xiaodiandi.ui.activity.mine.order;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qixiu.qixiu.request.OKHttpRequestModel;
import com.qixiu.qixiu.request.OKHttpUIUpdataListener;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.Preference;
import com.qixiu.qixiu.utils.ToastUtil;
import com.qixiu.wigit.show_dialog.ArshowDialog;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantString;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.constant.IntentDataKeyConstant;
import com.qixiu.xiaodiandi.model.login.LoginStatus;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.TitleActivity;
import com.qixiu.xiaodiandi.ui.fragment.mine.order.OrderDetailsAdapter;
import com.qixiu.xiaodiandi.ui.fragment.mine.order.OrderDetailsBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by HuiHeZe on 2017/5/3.
 */

public class OrderDetailsActivity extends TitleActivity implements View.OnClickListener, OKHttpUIUpdataListener<BaseBean> {
    private OrderDetailsAdapter adapter;
    //订单的状态参数
    private String order_id = "";
    private Button btn_deleteOrder_detail, btn_payOrder_detail, btn_notice_send_detail, btn_checkwhere_detail, btn_getConform_detail, btn_cacleOrder_detail, btn_judge_detail;
    private RecyclerView recycleView_goods_in_order;
    private List<Button> buttons = new ArrayList<>();
    private TextView textView_payIsOver,
            textView_order_state, textView_goodsNum;
    OrderDetailsBean orderDetailsBean;
    //判断是删除订单还是确认收货
    boolean IS_DELETE = false;
    OKHttpRequestModel okHttpRequestModel;
    private String status;

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
        //隐藏所有button
        setVisble();
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
        textView_payIsOver.setVisibility(View.GONE);
        if (status == null) {
            return;
        }
        if ("0".equals(status)) {
            btn_deleteOrder_detail.setVisibility(View.VISIBLE);
            textView_order_state.setText("已取消");
        } else if ("1".equals(status)) {
            btn_cacleOrder_detail.setVisibility(View.VISIBLE);
            btn_payOrder_detail.setVisibility(View.VISIBLE);
            textView_order_state.setText("待付款");
        } else if ("8".equals(status)) {
            btn_judge_detail.setVisibility(View.VISIBLE);
            btn_deleteOrder_detail.setVisibility(View.VISIBLE);
            textView_order_state.setText("待评价");
        } else if ("5".equals(status)) {
            textView_order_state.setText("已完成");
        } else if ("3".equals(status)) {
            btn_checkwhere_detail.setVisibility(View.VISIBLE);
            btn_getConform_detail.setVisibility(View.VISIBLE);
            textView_order_state.setText("待收货");
        } else if ("2".equals(status)) {
//            btn_checkwhere_detail.setVisibility(View.VISIBLE);
//            btn_getConform_detail.setVisibility(View.VISIBLE);
            textView_order_state.setText("已付款");
        }
    }

    private void setClick() {
        btn_deleteOrder_detail.setOnClickListener(this);
        btn_payOrder_detail.setOnClickListener(this);
        btn_notice_send_detail.setOnClickListener(this);
        btn_checkwhere_detail.setOnClickListener(this);
        btn_getConform_detail.setOnClickListener(this);
        btn_cacleOrder_detail.setOnClickListener(this);
        btn_judge_detail.setOnClickListener(this);
        buttons.add(btn_deleteOrder_detail);
        buttons.add(btn_payOrder_detail);
        buttons.add(btn_notice_send_detail);
        buttons.add(btn_checkwhere_detail);
        buttons.add(btn_getConform_detail);
        buttons.add(btn_cacleOrder_detail);
        buttons.add(btn_judge_detail);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_deleteOrder_detail:
                IS_DELETE = true;
                setDialog("确认删除订单？");
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
            case R.id.btn_cacleOrder_detail:
                startCancleOrder();
                break;


        }
    }

    private void startCancleOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("orderid", orderDetailsBean.getO().getId() + "");
        map.put("vipid", Preference.get(ConstantString.USERID, ""));
        okHttpRequestModel.okhHttpPost(ConstantUrl.cancleOrder, map, new BaseBean());
    }

    private void startpay() {
        Intent intent = new Intent(mContext, SelectPayMethoedActivity.class);
        //// TODO: 2017/9/18 放入订单id
        intent.putExtra(IntentDataKeyConstant.ID, orderDetailsBean.getO().getId());
        intent.putExtra("orderid", orderDetailsBean.getO().getId());
        intent.putExtra(IntentDataKeyConstant.MONEY, (orderDetailsBean.getO().getTotal_price()));
        mContext.startActivity(intent);
    }

    private void startGetGoods() {
        Map<String, String> map = new HashMap<>();
        map.put("vipid", Preference.get(ConstantString.USERID, ""));
        map.put("orderid", orderDetailsBean.getO().getId() + "");
        okHttpRequestModel.okhHttpPost(ConstantUrl.getGoodsUrl, map, new BaseBean());
    }

    private void startNotice() {

    }

    private void startDeleteOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("vipid", Preference.get(ConstantString.USERID, ""));
        map.put("orderid", orderDetailsBean.getO().getId() + "");
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
        textView_payIsOver = (TextView) findViewById(R.id.textView_payIsOver);
        textView_order_state = (TextView) findViewById(R.id.textView_order_state);
        btn_deleteOrder_detail = (Button) findViewById(R.id.btn_deleteOrder_detail);
        btn_payOrder_detail = (Button) findViewById(R.id.btn_payOrder_detail);
        btn_notice_send_detail = (Button) findViewById(R.id.btn_notice_send_detail);
        btn_checkwhere_detail = (Button) findViewById(R.id.btn_checkwhere_detail);
        btn_getConform_detail = (Button) findViewById(R.id.btn_getConform_detail);
        btn_cacleOrder_detail = (Button) findViewById(R.id.btn_cacleOrder_detail);
        btn_judge_detail = (Button) findViewById(R.id.btn_judge_detail);
        recycleView_goods_in_order = (RecyclerView) findViewById(R.id.recycleView_goods_in_order);
    }

    @Override
    public void onSuccess(BaseBean data, int i) {
        if (data instanceof OrderDetailsBean) {
            orderDetailsBean = (OrderDetailsBean) data;
            status = orderDetailsBean.getO().getStatus() + "";
            order_id = orderDetailsBean.getO().getId() + "";
            adapter.refreshData(orderDetailsBean.getO().getCartInfo());
        }
        if (ConstantUrl.cancleOrder.equals(data.getUrl()) || ConstantUrl.getGoodsUrl.equals(data.getUrl())) {
            ToastUtil.toast(data.getM());
            getData();
        }
        if (ConstantUrl.orderDeleteUrl.equals(data.getUrl())) {
            ToastUtil.toast(data.getM());
            Intent intent = new Intent(ConstantString.ACTION_REFRSH);
            sendBroadcast(intent);
            finish();
        }
        setVisble();
    }

    @Override
    public void onError(Call call, Exception e, int i) {
        ToastUtil.toast(R.string.link_error);

    }

    @Override
    public void onFailure(C_CodeBean c_codeBean) {
        ToastUtil.toast(c_codeBean.getM());
    }
}
