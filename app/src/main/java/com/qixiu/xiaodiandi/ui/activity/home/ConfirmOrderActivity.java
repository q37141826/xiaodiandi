package com.qixiu.xiaodiandi.ui.activity.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qixiu.qixiu.application.AppManager;
import com.qixiu.qixiu.request.OKHttpRequestModel;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.CommonUtils;
import com.qixiu.wigit.GotoView;
import com.qixiu.wigit.show_dialog.ArshowDialog;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantRequest;
import com.qixiu.xiaodiandi.constant.ConstantString;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.constant.IntentDataKeyConstant;
import com.qixiu.xiaodiandi.model.login.LoginStatus;
import com.qixiu.xiaodiandi.model.mine.points.PointsBean;
import com.qixiu.xiaodiandi.model.mine.ticket.TicketListBean;
import com.qixiu.xiaodiandi.model.order.CreateOrderBean;
import com.qixiu.xiaodiandi.model.order.FastPayNewBean;
import com.qixiu.xiaodiandi.model.order.GotoAddCartsData;
import com.qixiu.xiaodiandi.model.order.OrderPayData;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;
import com.qixiu.xiaodiandi.ui.activity.home.address.AddressListActivity;
import com.qixiu.xiaodiandi.ui.activity.home.confirm_order.AddressBean;
import com.qixiu.xiaodiandi.ui.activity.home.confirm_order.CartsPayBean;
import com.qixiu.xiaodiandi.ui.activity.home.confirm_order.ConfirmOrderAdapter;
import com.qixiu.xiaodiandi.ui.activity.home.confirm_order.FastPayBean;
import com.qixiu.xiaodiandi.ui.activity.mine.TicketActivity;
import com.qixiu.xiaodiandi.ui.activity.mine.order.SelectPayMethoedActivity;
import com.qixiu.xiaodiandi.utils.NumUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfirmOrderActivity extends RequestActivity implements View.OnClickListener {
    private String pay_code = "alipay", addressId, addressName, addressPhone, addressContent, addressProvince, addressCity, addressDistict;
    private RelativeLayout relativelayout_goto_address_list;
    private TextView textView_name_comforOrder, textView_phone_comforOrder, textView_address_comforOrder,
            textView_editaddress_coformorder, textView_shoildpayprice, textView_totalprice, textView_postage, textView_gotoPay, textView_totalNum;
    private OKHttpRequestModel okHttpRequestModel;
    private RecyclerView recyclerview_cofirm_order;

    GotoView gotoViewPoints;//是否使用积分的点击view

    //第一次进入该界面，还是不是第一次，根据这个判断，只刷新地址界面
    private boolean IS_FIRST_IN = true;
    //是从购物车过来，还是从立即支付过来
    private boolean IS_FROM_CARTS = false;

    //商品适配器
    private ConfirmOrderAdapter adapter;
    //生成的订单的id
    private String id = "";
    //留言
    private EditText edittext_confirmOrder;
    //总价钱，传给下一个界面用
    private CreateOrderBean.OBean orderBean;
    private GotoView gotoViewAddress, gotoViewTikets;
    private AddressBean.OBean selectedAddress;
    private GotoAddCartsData gotoAddCartsData;
    private TicketListBean.OBean selectedTicket;
    //总价钱
    double totoalFinalMoney = 0;
    //是否使用积分
    boolean isUsePoints = false;
    private PointsBean selectedPoints;//个人积分信息
    private double interger;//使用的积分数量

    @Override
    protected void onInitData() {
        EventBus.getDefault().register(this);
        okHttpRequestModel = new OKHttpRequestModel(this);
        mTitleView.setTitle("确认订单");
        mTitleView.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setclick();
        try {
            orderBean = getIntent().getParcelableExtra(IntentDataKeyConstant.DATA);
            adapter.refreshData(orderBean.getCartInfo());
            int num = 0;
            for (int i = 0; i < orderBean.getCartInfo().size(); i++) {
                num += orderBean.getCartInfo().get(i).getCart_num();
            }
            textView_totalprice.setText("¥" + orderBean.getPriceGroup().getTotalPrice() + "");
            textView_totalNum.setText(num + "");
            totoalFinalMoney = NumUtils.getDouble(orderBean.getPriceGroup().getTotalPrice());
        } catch (Exception e) {

        }
        if (orderBean == null) {
            gotoAddCartsData = getIntent().getParcelableExtra(IntentDataKeyConstant.DATA);
            textView_totalprice.setText("¥" + gotoAddCartsData.getMoney() + "");
            textView_totalNum.setText(gotoAddCartsData.getBuyNum() + "");
            List datas = new ArrayList();
            datas.add(gotoAddCartsData.getListBean());
            adapter.refreshData(datas);
            totoalFinalMoney = NumUtils.getDouble(gotoAddCartsData.getMoney());

        }
        getData();
        getPointsData();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_confirm_order;
    }

    private void setclick() {
        textView_editaddress_coformorder.setOnClickListener(this);
        textView_gotoPay.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView_editaddress_coformorder:
                break;

            case R.id.textView_gotoPay:
                getMessage();
                //快速支付确认 ---- 购物车确认
                OrderPayData orderPayData = new OrderPayData();
                if (selectedTicket != null) {
                    orderPayData.setCoupon(selectedTicket.getId() + "" + "");  //todo 这两个地方后续要补上
                }
                if (selectedPoints != null && isUsePoints) {
                    orderPayData.setIntegral(interger + "");
                }
                orderPayData.setMoney(textView_totalprice.getText().toString());
                if (orderBean == null && gotoAddCartsData != null) {//快速支付和购物车支付的判断
                    FastPayNewBean fastPayNewBean = new FastPayNewBean();
                    fastPayNewBean.setGotoAddCartsData(gotoAddCartsData);
                    fastPayNewBean.setOrderPayData(orderPayData);
                    orderPayData.setAddress(selectedAddress.getId());
                    SelectPayMethoedActivity.start(getContext(), SelectPayMethoedActivity.class, fastPayNewBean);
                } else {
                    orderPayData.setAddress(selectedAddress.getId());
                    orderPayData.setKey(orderBean.getOrderKey());
                    SelectPayMethoedActivity.start(getContext(), SelectPayMethoedActivity.class, orderPayData);
                }
                break;
        }
    }


    public void getMessage() {
        addressName = textView_name_comforOrder.getText().toString();
        addressPhone = textView_phone_comforOrder.getText().toString();
        addressContent = textView_address_comforOrder.getText().toString();
    }

    public void getDefaultAddress() {
        IS_FIRST_IN = false;
        getData();
    }

    private void setDialog() {
        ArshowDialog.Builder builder = new ArshowDialog.Builder(this);
        builder.setCanceledOnTouchOutside(false);
        builder.setCancelable(false);
        builder.setPositiveButton("新增地址", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ConfirmOrderActivity.this, AddressListActivity.class);// TODO: 2019/1/9  地址编辑界面
                startActivity(intent);
                dialog.dismiss();
            }
        });

        builder.setMessage("没有默认地址");
        builder.setNegativeButton(null, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //判断是否有默认地址
        getDefaultAddress();
    }

    @Override
    protected void onInitViewNew() {
        textView_editaddress_coformorder = (TextView) findViewById(R.id.textView_editaddress_coformorder);
        textView_name_comforOrder = (TextView) findViewById(R.id.textView_name_comforOrder);
        textView_phone_comforOrder = (TextView) findViewById(R.id.textView_phone_comforOrder);
        textView_address_comforOrder = (TextView) findViewById(R.id.textView_address_comforOrder);
        //这两个显示器中一个
        relativelayout_goto_address_list = (RelativeLayout) findViewById(R.id.relativelayout_goto_address_list);
        gotoViewAddress = findViewById(R.id.gotoViewAddress);
        gotoViewTikets = findViewById(R.id.gotoViewTikets);
        gotoViewPoints = findViewById(R.id.gotoViewPoints);

        recyclerview_cofirm_order = (RecyclerView) findViewById(R.id.recyclerview_cofirm_order);
        textView_shoildpayprice = (TextView) findViewById(R.id.textView_shoildpayprice);
        textView_postage = (TextView) findViewById(R.id.textView_postage);
        textView_totalprice = (TextView) findViewById(R.id.textView_totalprice);
        textView_totalNum = (TextView) findViewById(R.id.textView_totalNum);
        textView_gotoPay = (TextView) findViewById(R.id.textView_gotoPay);
        edittext_confirmOrder = (EditText) findViewById(R.id.edittext_confirmOrder);
        adapter = new ConfirmOrderAdapter();
        recyclerview_cofirm_order.setAdapter(adapter);
        recyclerview_cofirm_order.setLayoutManager(new LinearLayoutManager(this));
    }


    public void getData() {
        Map<String, String> map = new HashMap<>();
        BaseBean bean;
        ConstantRequest.getAddressList(okHttpRequestModel);//获取地址
    }

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof AddressBean) {
            AddressBean addressBean = (AddressBean) data;
            for (int i = 0; i < addressBean.getO().size(); i++) {
                if (addressBean.getO().get(i).getIs_default().equals("1")) {
                    setAddress(addressBean.getO().get(i));
                    selectedAddress = addressBean.getO().get(i);
                }
            }
            refreshAddressState();
        }
        if (data instanceof CartsPayBean) {
            CartsPayBean bean = (CartsPayBean) data;
            if (IS_FIRST_IN) {
                adapter.refreshData(bean.getO().getGoods());
                textView_shoildpayprice.setText("¥" + CommonUtils.dobleDecmal(bean.getO().getPay_price(), 2));
                textView_postage.setText("¥" + CommonUtils.dobleDecmal(Double.parseDouble(bean.getO().getYf()), 2));
                textView_totalprice.setText("¥" + CommonUtils.dobleDecmal(bean.getO().getPay_price(), 2));
            }
            if (bean.getO().getAddress().size() == 0) {
                setDialog();
            } else {
                textView_name_comforOrder.setText(bean.getO().getAddress().get(0).getName());
                textView_address_comforOrder.setText(bean.getO().getAddress().get(0).getAddress());
                textView_phone_comforOrder.setText(bean.getO().getAddress().get(0).getMobile());
            }

        }
        if (data instanceof FastPayBean) {
            FastPayBean bean = (FastPayBean) data;
            if (IS_FIRST_IN) {
                adapter.refreshData(bean.getO().getGoods());
                textView_shoildpayprice.setText("¥" + CommonUtils.dobleDecmal(bean.getO().getTotal_price(), 2));
                textView_postage.setText("¥" + CommonUtils.dobleDecmal(Double.parseDouble(bean.getO().getYf()), 2));
                textView_totalprice.setText("¥" + CommonUtils.dobleDecmal(bean.getO().getPay_price(), 2));
            }
            if (bean.getO().getAddress().size() == 0) {
                setDialog();
            } else {
                textView_name_comforOrder.setText(bean.getO().getAddress().get(0).getName());
                textView_address_comforOrder.setText(bean.getO().getAddress().get(0).getAddress());
                textView_phone_comforOrder.setText(bean.getO().getAddress().get(0).getMobile());
            }
        }
        if (data instanceof PointsBean) {
            selectedPoints = (PointsBean) data;
            gotoViewPoints.setFirstText("使用点滴积分" + "    (可用" + selectedPoints.getO().getIntegral() + "积分)");
        }
    }

    private void refreshAddressState() {
        //刷新地址选择的状态
        if (TextUtils.isEmpty(textView_address_comforOrder.getText().toString())) {
            relativelayout_goto_address_list.setVisibility(View.GONE);
            gotoViewAddress.setVisibility(View.VISIBLE);
        } else {
            relativelayout_goto_address_list.setVisibility(View.VISIBLE);
            gotoViewAddress.setVisibility(View.GONE);
        }
    }

    private void setAddress(AddressBean.OBean oBean) {
        textView_address_comforOrder.setText(oBean.getProvince() + oBean.getCity() + oBean.getDistrict() + oBean.getDetail());
        textView_phone_comforOrder.setText(oBean.getPhone());
        textView_name_comforOrder.setText(oBean.getReal_name());
    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {

    }

    //选择地址
    public void gotoSelectAddress(View view) {
        AddressListActivity.start(getContext(), AddressListActivity.class);
    }

    //选择优惠券
    public void gotoTicket(View view) {
        TicketActivity.start(getContext(), TicketActivity.class);
    }


    //获取地址
    @Subscribe
    public void getAddressEvent(AddressBean.OBean data) {
//        setAddress(data);
//        refreshAddressState();//GoodsDetailsActivity刷新地址选择的状态
        AppManager.getAppManager().finishActivity(AddressListActivity.class);
    }

    //获取优惠券
    @Subscribe
    public void getTicket(TicketListBean.OBean ticket) {
        selectedTicket = ticket;
        gotoViewTikets.setSecondText("满" + ticket.getUse_min_price() + "减" + selectedTicket.getCoupon_price());
        getFinnalMoney();
        AppManager.getAppManager().finishActivity(TicketActivity.class);
    }

    //选择了积分或者优惠券之后计算最终价钱
    private void getFinnalMoney() {
        double finalMoney = totoalFinalMoney;
        if (selectedTicket != null) {
            finalMoney = (totoalFinalMoney - selectedTicket.getCoupon_price());
        }
        if (selectedPoints != null && isUsePoints) {
            finalMoney = finalMoney - NumUtils.getDouble(selectedPoints.getO().getRmd());
            if (finalMoney <= 0) {//如果扣除积分之后变成了负的，那么只能使用到让金额变成0的地步
                interger = totoalFinalMoney * 10;
                finalMoney = 0.01;
            } else {
                interger = selectedPoints.getO().getIntegral();
            }
        }
        textView_totalprice.setText(ConstantString.RMB_SYMBOL + finalMoney);
    }

    //积分情况
    private void getPointsData() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", LoginStatus.getId());
        post(ConstantUrl.waterlistUrl, map, new PointsBean());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //使用积分的切换
    public void usePoints(View view) {
        isUsePoints = !isUsePoints;
        getFinnalMoney();
        if (isUsePoints) {
            gotoViewPoints.setSecondDrawable(getContext(), R.mipmap.order_switch_on);
        } else {
            gotoViewPoints.setSecondDrawable(getContext(), R.mipmap.order_switch);
        }
    }
}
