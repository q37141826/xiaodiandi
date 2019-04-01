package com.qixiu.xiaodiandi.ui.activity.home;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.qixiu.qixiu.application.AppManager;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.DownLoadFileUtils;
import com.qixiu.qixiu.utils.DrawableUtils;
import com.qixiu.qixiu.utils.MyTimer;
import com.qixiu.qixiu.utils.NavagationUtils;
import com.qixiu.qixiu.utils.StatusBarUtils;
import com.qixiu.qixiu.utils.ToastUtil;
import com.qixiu.qixiu.utils.html_utils.HtmlUtils;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantRequest;
import com.qixiu.xiaodiandi.constant.ConstantString;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.constant.IntentDataKeyConstant;
import com.qixiu.xiaodiandi.engine.ShareLikeEngine;
import com.qixiu.xiaodiandi.model.home.goodsdetails.CharctorInnerBean;
import com.qixiu.xiaodiandi.model.home.goodsdetails.GetPointsTimeBean;
import com.qixiu.xiaodiandi.model.home.goodsdetails.GoodsDetailsBean;
import com.qixiu.xiaodiandi.model.order.GotoAddCartsData;
import com.qixiu.xiaodiandi.ui.activity.MainActivity;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;
import com.qixiu.xiaodiandi.ui.adapter.home.GoodsDetailsCharactorAdapter;
import com.qixiu.xiaodiandi.ui.fragment.home.ImageAndVideoAdapter;
import com.qixiu.xiaodiandi.utils.NumUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;

public class GoodsDetailsActivity extends RequestActivity {

//    @BindView(R.id.jcplayer)
//    JZVideoPlayerStandard jcplayer;

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
    @BindView(R.id.textViewGetPoints)
    TextView textViewGetPoints;
    @BindView(R.id.textViewService)
    TextView textViewService;
    @BindView(R.id.imageViewHome)
    ImageView imageViewHome;
    @BindView(R.id.fatherView)
    View fatherView;

    private PopupWindow pw;
    private GoodsDetailsBean detailsBean;
    private GoodsDetailsBean.OBean.ResultBean.ListBean selectedProduct;
    private int selectNum = 1;//选择了多少数量
    private TextView tv_goodsdetail_ppw_price_txt;
    private String id;
    private MyTimer myTimer;
    private int runOnlyOne = 0;
    private View contentView;
    private ShareLikeEngine shareLikeEngine;

    @Override
    protected void onInitData() {
        mZProgressHUD.show();
        id = getIntent().getStringExtra(IntentDataKeyConstant.DATA);
        mTitleView.setTitle("商品详情");
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);//设置黑色
        StatusBarUtils.setWindowStatusBarColor(this, Color.WHITE);
//        loadDetails();
        addPoints(1 + "");//浏览获得积分
    }

    private void addPoints(String type) {
        if (AppManager.getAppManager().currentActivity() instanceof GoodsDetailsActivity) {

        } else {
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("type", type + "");
        post(ConstantUrl.scanPointsUrl, map, new GetPointsTimeBean());
    }

    private void loadDetails() {
        id = getIntent().getStringExtra(IntentDataKeyConstant.DATA);
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
//            CommonUtils.setWebview(webview, detailsBean.getO().getProduct().getDescription(), true);
            if (detailsBean.getO().getProduct().getIntergral() == null) {
                textViewGetPoints.setText("购买得赠送" + 0 + "点滴");
            } else {
                textViewGetPoints.setText("购买得赠送" + detailsBean.getO().getProduct().getIntergral() + "点滴");
            }
            //这个位置非常耗内存，因为是从SD卡加载的，所以只加载一次
            runOneMethoned();
            textViewCartsNum.setText(detailsBean.getO().getProduct().getCartnum() + "");
            textViewCartsNum.setVisibility(detailsBean.getO().getProduct().getCartnum() != 0 ? View.VISIBLE : View.GONE);
            if (detailsBean.getO().getProduct().getCollect() == 1) {
                textViewCollect.setText("已收藏");
                DrawableUtils.setTopDrawableResouce(textViewCollect, getContext(), R.mipmap.aready_collect);
            } else {
                textViewCollect.setText("收藏");
                DrawableUtils.setTopDrawableResouce(textViewCollect, getContext(), R.mipmap.goods_favorites);
            }
            textViewService.setText(detailsBean.getO().getProduct().getKeyword());
            textViewProductname.setText(detailsBean.getO().getProduct().getStore_name());
            textViewProductDescribe.setText(detailsBean.getO().getProduct().getStore_info());
            textViewPriceNow.setText("¥ " + detailsBean.getO().getProduct().getPrice());
            textViewPricePrevious.setText("¥ " + detailsBean.getO().getProduct().getOt_price());
            textViewPricePrevious.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//中划线
            ImageAndVideoAdapter adapter = new ImageAndVideoAdapter(rollpager);
            rollpager.setAdapter(adapter);
            rollpager.setHintView(new ColorPointHintView(getContext(), getContext().getResources().getColor(R.color.theme_color),
                    getContext().getResources().getColor(R.color.alpha_black_50)));
            adapter.refreshDatas(detailsBean.getO().getProduct().getSlider_image());
            if (detailsBean.getO().getProduct().getSlider_image().size() > 0) {
                adapter.setThumb(detailsBean.getO().getProduct().getSlider_image().get(0));
                adapter.setVideoUrl(detailsBean.getO().getProduct().getVideo());
            }
            adapter.setItemClickListenner(new ImageAndVideoAdapter.itemClickListenner() {
                @Override
                public void onItemClick(Object data, int position) {
                    if (position == 0 && !TextUtils.isEmpty(detailsBean.getO().getProduct().getVideo())) {
//                        playUrl();
//                        downloadAndPlay();//先下载后跳转
                        Intent intent = new Intent(getContext(), PlayActivity.class);
                        intent.putExtra(IntentDataKeyConstant.DATA, detailsBean.getO().getProduct().getVideo());
                        intent.putExtra("thumb", detailsBean.getO().getProduct().getSlider_image().get(0));
                        startActivity(intent);
                    }
                }
            });
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
//            ToastUtil.toast(data.getM());
        }
        if (data instanceof GetPointsTimeBean) {
            GetPointsTimeBean bean = (GetPointsTimeBean) data;
            long limit = (long) (NumUtils.getDouble(bean.getO().getTimefen()) * 60 * 1000);
            myTimer = new MyTimer(limit, 1000);
            myTimer.setListenner(new MyTimer.TimeStateListenner() {
                @Override
                public void onRunning(long lastTime) {

                }

                @Override
                public void onFinished() {
                    addPoints(2 + "");//浏览获得积分
                }
            });
            myTimer.start();
        }
        if (!(data instanceof GetPointsTimeBean) && data.getUrl().equals(ConstantUrl.scanPointsUrl)) {
            ToastUtil.toast(data.getM());
        }
        if (mZProgressHUD != null) {
            mZProgressHUD.dismiss();
        }
    }


    private void runOneMethoned() {
        runOnlyOne++;
        if (runOnlyOne <= 1) {
            HtmlUtils.getInstance().setWindowWith(windowWith);
            HtmlUtils.getInstance().setHtml(textViewPText, detailsBean.getO().getProduct().getDescription(), this);
        }
    }

    private void playUrl() {
        Intent intent = new Intent(getContext(), PlayActivity.class);
        intent.putExtra(IntentDataKeyConstant.DATA, detailsBean.getO().getProduct().getVideo());
        intent.putExtra("thumb", detailsBean.getO().getProduct().getSlider_image().get(0));
        startActivity(intent);
    }

    private void downloadAndPlay() {
        mZProgressHUD.setMessage("下载视频..");
        mZProgressHUD.show();
        DownLoadFileUtils.InitFile.callFile(detailsBean.getO().getProduct().getVideo(), new DownLoadFileUtils.FileCallBack() {
            @Override
            public void callBack(String path) {
//                                initVideoView(path);
                Intent intent = new Intent(getContext(), PlayActivity.class);
                intent.putExtra(IntentDataKeyConstant.DATA, path);
                intent.putExtra("thumb", detailsBean.getO().getProduct().getSlider_image().get(0));
                startActivity(intent);
                mZProgressHUD.dismiss();
            }
        });
    }


    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {

    }

    @Override
    public void toastFailue(C_CodeBean c_codeBean) {
//        super.toastFailue(c_codeBean);
    }

    @Override
    protected void onInitViewNew() {
        imageViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppManager.getAppManager().finishAllActivity();
                MainActivity.start(getContext(), MainActivity.class);
            }
        });
        openNavagationBar(fatherView);//界面OK后加载布局变动监听
        textViewAddCart.setOnClickListener(this);
        textViewBuy.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textViewAddCart:
                showBuyPop(v);
                break;
            case R.id.textViewBuy:
                showBuyPop(v);
                break;
        }
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
        contentView = View.inflate(getActivity(), R.layout.pop_goodsdetail, null);
        contentView.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_bottom_in_2));//关闭动画
        pw = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,
                true);
//        pw.setBackgroundDrawable(new BitmapDrawable());
        pw.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        pw.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
////这句话让popuwindow沉浸状态栏
        pw.setClippingEnabled(false);
        Button bt_buy_pp = contentView.findViewById(R.id.bt_buy_pp);
        RecyclerView rv_good_details_spec = contentView.findViewById(R.id.rv_good_details_spec);

        TextView tv_goodsdetail_ppw_name_txt = contentView.findViewById(R.id.tv_goodsdetail_ppw_name_txt);
        tv_goodsdetail_ppw_name_txt.setText(detailsBean.getO().getProduct().getStore_name());
        tv_goodsdetail_ppw_price_txt = contentView.findViewById(R.id.tv_goodsdetail_ppw_price_txt);
        tv_goodsdetail_ppw_price_txt.setText("¥" + detailsBean.getO().getProduct().getPrice());


        TextView tv_shopcar_minus = contentView.findViewById(R.id.tv_shopcar_minus);
        TextView tv_repertory = contentView.findViewById(R.id.tv_repertory);
        TextView tv_add = contentView.findViewById(R.id.tv_add);
        ImageView iv_goodsdetail_ppw_picture = contentView.findViewById(R.id.iv_goodsdetail_ppw_picture);
        Glide.with(getContext()).load(detailsBean.getO().getProduct().getImage()).into(iv_goodsdetail_ppw_picture);
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedProduct == null && isHaveParams(detailsBean)) {
                    ToastUtil.toast("请选择规格");
                    return;
                }
                int stock = getStock();
                if (getNum(tv_repertory.getText().toString()) >= stock) {
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
                if (selectedProduct == null && isHaveParams(detailsBean)) {
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
                if (selectedProduct == null && isHaveParams(detailsBean)) {
                    ToastUtil.toast("请选择产品规格");
                    return;
                }
                //立即支付需要加入购物车和订单一起请求，所以把加入购物车的信息一起送到支付界面
                GotoAddCartsData gotoAddCartsData = new GotoAddCartsData();
                gotoAddCartsData.setBuyNum(selectNum + "");
                gotoAddCartsData.setProdeuctId(detailsBean.getO().getProduct().getId() + "");
                gotoAddCartsData.setUnique(selectedProduct == null ? "" : selectedProduct.getUnique());
                if (selectedProduct == null) {
                    selectedProduct = new GoodsDetailsBean.OBean.ResultBean.ListBean();
                    selectedProduct.setPrice(detailsBean.getO().getProduct().getPrice());
                    selectedProduct.setImage(detailsBean.getO().getProduct().getImage());
                }
                gotoAddCartsData.setMoney(NumUtils.getDouble(selectedProduct.getPrice()) * selectNum + "");
                gotoAddCartsData.setListBean(selectedProduct);
                selectedProduct.setNum(selectNum);
                selectedProduct.setInfo(detailsBean.getO().getProduct().getStore_name());
                selectedProduct.setCate_id(detailsBean.getO().getProduct().getCate_id() + "");
                ConfirmOrderActivity.start(getContext(), ConfirmOrderActivity.class, gotoAddCartsData);
            }
        });

        Button bt_addto_shopcar_pp = contentView.findViewById(R.id.bt_addto_shopcar_pp);
        bt_addto_shopcar_pp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedProduct == null && isHaveParams(detailsBean)) {
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
        adustPop();
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
//                adapter.notifyDataSetChanged();
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


    private boolean searchStoreData(Map<String, CharctorInnerBean> selectedDatas) {
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
        if (selectedProduct == null) {
            ToastUtil.toast("暂时没有这个规格");
            return false;//找不到就返回false
        }
        tv_goodsdetail_ppw_price_txt.setText(ConstantString.RMB_SYMBOL + selectedProduct.getPrice());
        return true;//找到了就返回ture
    }


    //跳转购物车
    public void gotoCarts(View view) {
        AppManager.getAppManager().finishActivity(MarketActivity.class);
        MarketActivity.start(getContext(), MarketActivity.class);
    }

    //加入收藏
    public void addToCollect(View view) {
        Map<String, String> map = new HashMap<>();
        map.put("type", detailsBean.getO().getProduct().getCollect() == 1 ? "2" : "1");
        map.put("sid", detailsBean.getO().getProduct().getId() + "");
        post(ConstantUrl.addToCollectUrl, map, new BaseBean());
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            myTimer.cancleTimer();//注销时干掉他
        } catch (Exception e) {
        }
    }


    //弹出窗体
    public void showBuyPop(View view) {
        if (pw != null) {
            adustPop();
        } else {
            showPop(view);
        }
    }

    //判断是不是有规格的商品
    private boolean isHaveParams(GoodsDetailsBean goodsDetailsBean) {
        if (goodsDetailsBean.getO().getResult().getCharc().size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    //获取库存
    private int getStock() {
        int stock;
        if (selectedProduct == null) {
            stock = detailsBean.getE();
        } else {
            stock = selectedProduct.getStock();
        }
        return stock;
    }

    //分享
    public void shareGoods(View view) {
        shareLikeEngine = new ShareLikeEngine();
        shareLikeEngine.releaseShareData(this, ConstantUrl.SHARE_IMAGE_URL, ConstantString.PRODUCT_SHARECONTENT, ConstantUrl.SHARE_CLICK_GO_URL, null);
        shareLikeEngine.setShareTitle(detailsBean.getO().getProduct().getStore_name());
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadDetails();
    }


    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onNavigationBarStatusChanged(int navigationBarIsMin) {
        super.onNavigationBarStatusChanged(navigationBarIsMin);
        if (pw != null) {
            if (pw.isShowing()) {
                pw.dismiss();
                adustPop();
            }
        }
        if (shareLikeEngine != null) {
            shareLikeEngine.refreshPop(getActivity());
        }
    }

    private void adustPop() {
        int[] location = new int[2];
        contentView.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        if (navigationBarIsUp && NavagationUtils.hasNavBar(getContext())) {
            pw.showAtLocation(contentView, Gravity.BOTTOM, 0, NavagationUtils.getNavigationBarHeight(getContext()));
        } else {
            pw.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
        }
    }

    @Override
    public void showProgress() {
//        super.showProgress();
    }
}
