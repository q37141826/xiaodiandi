package com.qixiu.xiaodiandi.ui.activity.home;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jude.rollviewpager.RollPagerView;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.CommonUtils;
import com.qixiu.qixiu.utils.StatusBarUtils;
import com.qixiu.qixiu.utils.ToastUtil;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantRequest;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.constant.EventAction;
import com.qixiu.xiaodiandi.constant.IntentDataKeyConstant;
import com.qixiu.xiaodiandi.model.home.goodsdetails.CharctorInnerBean;
import com.qixiu.xiaodiandi.model.home.goodsdetails.GoodsDetailsBean;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;
import com.qixiu.xiaodiandi.ui.adapter.home.GoodsDetailsCharactorAdapter;
import com.qixiu.xiaodiandi.ui.fragment.home.ImageUrlAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.qixiu.xiaodiandi.constant.EventAction.GOTO_CARTS;

public class GoodsDetailsActivity extends RequestActivity {


    @BindView(R.id.textViewAddCart)
    TextView textViewAddCart;
    @BindView(R.id.textViewBuy)
    TextView textViewBuy;
    @BindView(R.id.textViewPText)
    TextView textViewPText;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.textViewCartsNum)
    TextView textViewCartsNum;
    @BindView(R.id.textViewCollect)
    TextView textViewCollect;
    @BindView(R.id.rollpager)
    RollPagerView rollpager;
    @BindView(R.id.textViewProductname)
    TextView textViewProductname;
    @BindView(R.id.textViewProductDescribe)
    TextView textViewProductDescribe;
    @BindView(R.id.textViewPriceNow)
    TextView textViewPriceNow;
    @BindView(R.id.textViewPricePrevious)
    TextView textViewPricePrevious;
    @BindView(R.id.textViewCart)
    TextView textViewCart;
    private PopupWindow pw;
    private GoodsDetailsBean detailsBean;
    private GoodsDetailsBean.OBean.ResultBean.ListBean selectedProduct;
    private int selectNum = 1;//选择了多少数量
    private TextView tv_goodsdetail_ppw_price_txt;

    @Override
    protected void onInitData() {
        mTitleView.setTitle("商品详情");
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);//设置黑色
        StatusBarUtils.setWindowStatusBarColor(this, Color.WHITE);
        loadDetails();
    }

    private void loadDetails() {
        String id = getIntent().getStringExtra(IntentDataKeyConstant.DATA);
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        post(ConstantUrl.shopinfoUrl, map, new GoodsDetailsBean());
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_goods_details;
    }

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof GoodsDetailsBean) {
            detailsBean = (GoodsDetailsBean) data;
            CommonUtils.setWebview(webview, detailsBean.getO().getProduct().getDescription(), true);
//            HtmlUtils.getInstance().setWindowWith(windowWith);
//            HtmlUtils.getInstance().setHtml(textViewPText, detailsBean.getO().getProduct().getDescription());
            textViewCartsNum.setText(detailsBean.getO().getProduct().getCartnum() + "");
            if (detailsBean.getO().getProduct().getCollect() == 1) {
                textViewCollect.setText("已收藏");
            } else {
                textViewCollect.setText("收藏");
            }
            textViewProductname.setText(detailsBean.getO().getProduct().getStore_name());
            textViewProductDescribe.setText(detailsBean.getO().getProduct().getStore_info());
            textViewPriceNow.setText("¥   " + detailsBean.getO().getProduct().getPrice());
            textViewPricePrevious.setText("¥   " + detailsBean.getO().getProduct().getOt_price());
            ImageUrlAdapter adapter = new ImageUrlAdapter(rollpager);
            rollpager.setAdapter(adapter);
            adapter.refreshData(detailsBean.getO().getProduct().getSlider_image());
        }
        if (ConstantUrl.addShopCarURl.equals(data.getUrl())) {
            ToastUtil.toast(data.getM());
            if (pw != null) {
                pw.dismiss();
            }
            loadDetails();
        }
        if (data.getUrl().equals(ConstantUrl.addToCollectUrl)) {
            loadDetails();
            ToastUtil.toast(data.getM());
        }
    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {

    }

    @Override
    protected void onInitViewNew() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    //弹窗显示
    public void showPop(View view) {
        selectedProduct = null;
        View contentView = View.inflate(getActivity(), R.layout.pop_goodsdetail, null);
        contentView.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_bottom_in_2));
        pw = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,
                true);
        pw.setBackgroundDrawable(new BitmapDrawable());
        pw.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        pw.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//这句话让popuwindow沉浸状态栏
        pw.setClippingEnabled(false);
        if (!pw.isShowing())
            pw.showAtLocation(
                    getActivity().getWindow().getDecorView().findViewById(android.R.id.content),
                    Gravity.BOTTOM, 0, 0);
        Button bt_buy_pp = contentView.findViewById(R.id.bt_buy_pp);
        RecyclerView rv_good_details_spec = contentView.findViewById(R.id.rv_good_details_spec);

        TextView tv_goodsdetail_ppw_name_txt = contentView.findViewById(R.id.tv_goodsdetail_ppw_name_txt);
        tv_goodsdetail_ppw_name_txt.setText(detailsBean.getO().getProduct().getStore_name());
        tv_goodsdetail_ppw_price_txt = contentView.findViewById(R.id.tv_goodsdetail_ppw_price_txt);
        tv_goodsdetail_ppw_price_txt.setText("¥" + detailsBean.getO().getProduct().getPrice());


        TextView tv_shopcar_minus = contentView.findViewById(R.id.tv_shopcar_minus);
        TextView tv_repertory = contentView.findViewById(R.id.tv_repertory);
        TextView tv_add = contentView.findViewById(R.id.tv_add);
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedProduct == null) {
                    ToastUtil.toast("请选择规格");
                    return;
                }
                if (getNum(tv_repertory.getText().toString()) >= selectedProduct.getStock()) {
                    ToastUtil.toast("没有更多库存了");
                    return;
                } else {
                    selectNum = getNum(tv_repertory.getText().toString()) + 1;
                    tv_repertory.setText(selectNum + "");
                }
            }
        });
        tv_shopcar_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedProduct == null) {
                    ToastUtil.toast("请选择规格");
                    return;
                }
                if (getNum(tv_repertory.getText().toString()) <= 1) {
                    ToastUtil.toast("至少选择一个");
                    return;
                } else {
                    selectNum = getNum(tv_repertory.getText().toString()) - 1;
                    tv_repertory.setText(selectNum + "");
                }
            }
        });
        //设置属性
        setChara(rv_good_details_spec);
        bt_buy_pp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmOrderActivity.start(getContext(), ConfirmOrderActivity.class);
            }
        });

        Button bt_addto_shopcar_pp = contentView.findViewById(R.id.bt_addto_shopcar_pp);
        bt_addto_shopcar_pp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedProduct == null) {
                    ToastUtil.toast("请选择产品规格");
                    return;
                }
                ConstantRequest.addTopShopCart(getOkHttpRequestModel(), detailsBean.getO().getProduct().getId() + "", selectNum + "", selectedProduct == null ? "" : selectedProduct.getUnique());
            }
        });

        View fl_out_click = contentView.findViewById(R.id.fl_out_click);
        fl_out_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw.dismiss();
            }
        });


    }

    private int getNum(String str) {
        int num = 0;
        try {
            num = Integer.parseInt(str);
        } catch (Exception e) {
        }
        return num;
    }

    private void setChara(RecyclerView rv_good_details_spec) {
        Map<String, CharctorInnerBean> selectedDatas = new HashMap<>();

        GoodsDetailsCharactorAdapter adapter = new GoodsDetailsCharactorAdapter();
        rv_good_details_spec.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_good_details_spec.setAdapter(adapter);

        for (int i = 0; i < detailsBean.getO().getResult().getCharc().size(); i++) {
            List<CharctorInnerBean> datas = new ArrayList<>();
            for (int j = 0; j < detailsBean.getO().getResult().getCharc().get(i).getAttr_values().size(); j++) {
                CharctorInnerBean charctorInnerBean = new CharctorInnerBean(false, detailsBean.getO().getResult().getCharc().get(i).getAttr_values().get(j));
                charctorInnerBean.setAttr_name(detailsBean.getO().getResult().getCharc().get(i).getAttr_name());
                datas.add(charctorInnerBean);
            }
            detailsBean.getO().getResult().getCharc().get(i).setInners(datas);
        }

        adapter.refreshData(detailsBean.getO().getResult().getCharc());
        adapter.setClickListenner(new RecyclerBaseAdapter.ClickListenner() {
            @Override
            public void click(View view, Object data) {
                adapter.notifyDataSetChanged();
                if (data instanceof CharctorInnerBean) {
                    CharctorInnerBean innerBean = (CharctorInnerBean) data;
                    selectedDatas.put(innerBean.getAttr_name(), innerBean);
                }
                if (selectedDatas.keySet().size() == detailsBean.getO().getResult().getCharc().size()) {
                    //如果所有特征都选择了，那么去搜索数据，得到库存
                    searchStoreData(selectedDatas);
                }
            }
        });
    }


    private void searchStoreData(Map<String, CharctorInnerBean> selectedDatas) {
        for (int i = 0; i < detailsBean.getO().getResult().getList().size(); i++) {
            boolean contains = true;
            Set<String> keys = selectedDatas.keySet();
            for (String key : keys) {
                if (!detailsBean.getO().getResult().getList().get(i).getSuk().contains(selectedDatas.get(key).getText())) {
                    contains = false;
                    break;
                }
            }
            if (contains) {
                selectedProduct = detailsBean.getO().getResult().getList().get(i);
            }
        }
        tv_goodsdetail_ppw_price_txt.setText(selectedProduct.getPrice());
    }


    //跳转购物车
    public void gotoCarts(View view) {
        EventAction.Action action = new EventAction.Action(GOTO_CARTS);
        EventBus.getDefault().post(action);
        finish();
    }

    //加入收藏
    public void addToCollect(View view) {
        Map<String, String> map = new HashMap<>();
        map.put("type", detailsBean.getO().getProduct().getCollect() == 1 ? "2" : "1");
        map.put("sid", detailsBean.getO().getProduct().getId() + "");
        post(ConstantUrl.addToCollectUrl, map, new BaseBean());
    }
}
