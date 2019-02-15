package com.qixiu.xiaodiandi.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.ArshowDialogUtils;
import com.qixiu.qixiu.utils.DrawableUtils;
import com.qixiu.qixiu.utils.ToastUtil;
import com.qixiu.wigit.zprogress.ZProgressHUD;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantRequest;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.constant.IntentDataKeyConstant;
import com.qixiu.xiaodiandi.model.login.LoginStatus;
import com.qixiu.xiaodiandi.model.order.CreateOrderBean;
import com.qixiu.xiaodiandi.model.shopcar.ShopCartBean;
import com.qixiu.xiaodiandi.presenter.home.MarketPresenter;
import com.qixiu.xiaodiandi.ui.activity.home.ConfirmOrderActivity;
import com.qixiu.xiaodiandi.ui.fragment.basefragment.base.RequestFragment;
import com.qixiu.xiaodiandi.ui.fragment.cart.ShopCarAdapter;
import com.qixiu.xiaodiandi.utils.NumUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by my on 2019/1/2.
 */

public class MarketFragment extends RequestFragment implements MarketPresenter.RefreshInterf, ArshowDialogUtils.ArshowDialogListener {
    @BindView(R.id.tv_shopcar_totalnum)
    TextView tvShopcarTotalnum;
    @BindView(R.id.rl_clearall)
    RelativeLayout rlClearall;
    @BindView(R.id.rv_shopcar)
    RecyclerView rvShopcar;
    @BindView(R.id.srl_shopcar)
    SwipeRefreshLayout srlShopcar;
    @BindView(R.id.tv_all_selected)
    TextView tvAllSelected;
    @BindView(R.id.tv_totalpiece)
    TextView tvTotalpiece;
    @BindView(R.id.tv_settleaccounts)
    TextView tvSettleaccounts;
    @BindView(R.id.tv_totalprice_txt)
    TextView tvTotalpriceTxt;
    @BindView(R.id.tv_totalprice)
    TextView tvTotalprice;
    @BindView(R.id.rl_settleaccounts_info)
    RelativeLayout rlSettleaccountsInfo;
    @BindView(R.id.rl_shopcar_bottom)
    RelativeLayout rlShopcarBottom;
    Unbinder unbinder;
    @BindView(R.id.tv_deleteAll)
    TextView tvDeleteAll;
    private ShopCarAdapter adapter;

    private boolean isAllSelected = false;//是否全部选中
    private StringBuffer selectedId;
    ZProgressHUD zProgressHUD;
    @Override
    public void onSuccess(BaseBean data) {
        zProgressHUD.dismiss();
        if (data instanceof ShopCartBean) {
            ShopCartBean bean = (ShopCartBean) data;
            if (getDatas() != null && getDatas().size() != 0) {//如果有历史数据了，那么选中状态要保持
                for (int i = 0; i < getDatas().size(); i++) {
                    for (int j = 0; j < bean.getO().getValid().size(); j++) {
                        if (getDatas().get(i).getId().equals(bean.getO().getValid().get(j).getId())) {//循环遍历一下
                            bean.getO().getValid().get(j).setSelected(getDatas().get(i).isSelected());
                        }
                    }
                }
            }
            adapter.refreshData(bean.getO().getValid());
            selected(null);//刷新一下外部统计的view
        }
        srlShopcar.setRefreshing(false);
        if (data.getUrl().equals(ConstantUrl.addShopCarURl)) {
            requestData();
        }

        if (ConstantUrl.ClearShopCarURl.equals(data.getUrl())) {
            ToastUtil.toast(data.getM());
            refresh();
        }
        if (data instanceof CreateOrderBean) {
            CreateOrderBean bean = (CreateOrderBean) data;
            ConfirmOrderActivity.start(getContext(), ConfirmOrderActivity.class, bean.getO());
        }
    }

    @Override
    public void onError(Exception e) {
        srlShopcar.setRefreshing(false);
        zProgressHUD.dismiss();
    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {
        srlShopcar.setRefreshing(false);
        zProgressHUD.dismiss();
    }

    @Override
    protected void onInitData() {
        zProgressHUD=new ZProgressHUD(getContext());
        mTitleView.setTitle("购物车");
        mTitleView.getLeftView().setVisibility(View.GONE);
        rvShopcar.setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapter = new ShopCarAdapter();
        rvShopcar.setAdapter(this.adapter);
        srlShopcar.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                zProgressHUD.show();
                ConstantRequest.getShopCarList(getOkHttpRequestModel());
            }
        });
        adapter.setInterf(this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_market;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void moveToPosition(int position) {

    }

    @Override
    public void onStart() {
        super.onStart();
        requestData();
    }

    private void requestData() {
        zProgressHUD.show();
        ConstantRequest.getShopCarList(getOkHttpRequestModel());
    }


    @Override
    public void refresh() {
        requestData();
    }

    @Override
    public void delete(Object data) {
        if (data instanceof ShopCartBean.OBean.ValidBean) {
            ShopCartBean.OBean.ValidBean bean = (ShopCartBean.OBean.ValidBean) data;
            Map<String, String> requestParameter = new HashMap<>();
            requestParameter.put(IntentDataKeyConstant.VIPID, LoginStatus.getId());
            requestParameter.put(IntentDataKeyConstant.ID, bean.getId() + "");
            requestDelete(requestParameter);
        }
    }

    //删除订单
    private void requestDelete(Map map) {
        post(ConstantUrl.ClearShopCarURl, map, new BaseBean());
    }

    @Override
    public void add(Object data) {
        if (data instanceof ShopCartBean.OBean.ValidBean) {
            ShopCartBean.OBean.ValidBean bean = (ShopCartBean.OBean.ValidBean) data;
            updateData(bean);
        }
    }

    @Override
    public void minus(Object data) {
        if (data instanceof ShopCartBean.OBean.ValidBean) {
            ShopCartBean.OBean.ValidBean bean = (ShopCartBean.OBean.ValidBean) data;
            updateData(bean);
        }
    }

    public void updateData(ShopCartBean.OBean.ValidBean data) {
        showProgress();
        ConstantRequest.addTopShopCart(getOkHttpRequestModel(), data.getProduct_id()+"", data.getCart_num() + "", data.getProduct_attr_unique());
    }

    @Override
    public void selected(Object data) {
        int num = 0;
        double money = 0;
        selectedId = new StringBuffer("");
        for (int i = 0; i < getDatas().size(); i++) {
            if (getDatas().get(i).isSelected()) {
                num += getDatas().get(i).getCart_num();//商品数量
                money += getDatas().get(i).getCart_num() * NumUtils.getDouble(getDatas().get(i).getProductInfo().getAttrInfo().getPrice());//商品总价

                if (i == getDatas().size()) {
                    selectedId.append(getDatas().get(i).getId());
                } else {
                    selectedId.append(getDatas().get(i).getId() + ",");
                }
            } else {
                //一旦出现没有被选中的，我们认为没有被全选
                isAllSelected = false;
            }
        }
        tvShopcarTotalnum.setText("共计" + num + "件商品");
        tvTotalprice.setText("¥   " + money);

        DrawableUtils.setLeftDrawableResouce(tvAllSelected, getContext(), isAllSelected ? R.mipmap.shopcar_goods_select : R.mipmap.shopcar_goods_notselect);
    }

    public List<ShopCartBean.OBean.ValidBean> getDatas() {
        return adapter.getDatas();
    }


    @OnClick({R.id.tv_deleteAll, R.id.tv_all_selected, R.id.tv_settleaccounts})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_deleteAll:
                ArshowDialogUtils.showDialog(getContext(), "确定删除吗？", this);
                break;
            case R.id.tv_all_selected:
                isAllSelected = !isAllSelected;
                for (int i = 0; i < getDatas().size(); i++) {
                    getDatas().get(i).setSelected(isAllSelected);
                }
                adapter.notifyDataSetChanged();//刷新列表
                selected(null);//刷新外面的统计view
                break;
            case R.id.tv_settleaccounts:
                if (selectedId == null || TextUtils.isEmpty(selectedId.toString())) {
                    ToastUtil.toast("没有选中商品");
                    return;
                }
                ConstantRequest.cartsToOrder(getOkHttpRequestModel(), selectedId.toString());
                break;
        }
    }


    @Override
    public void onClickPositive(DialogInterface dialogInterface, int which) {
        if (selectedId == null || TextUtils.isEmpty(selectedId.toString())) {
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put(IntentDataKeyConstant.ID, selectedId.toString());
        requestDelete(map);
    }

    @Override
    public void onClickNegative(DialogInterface dialogInterface, int which) {

    }
}
