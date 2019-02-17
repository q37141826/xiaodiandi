package com.qixiu.xiaodiandi.ui.activity.community.upload;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qixiu.qixiu.application.AppManager;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.ToastUtil;
import com.qixiu.wigit.GotoView;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.model.comminity.entertainment.PayedShopListBean;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.upload.UploadPictureActivityNew;
import com.qixiu.xiaodiandi.ui.activity.community.MyPayedProductsActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EntertainmentPhotoUploadActivity extends UploadPictureActivityNew {


    @BindView(R.id.edittextComments)
    EditText edittextComments;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.gotoViewSelectProduct)
    GotoView gotoViewSelectProduct;
    @BindView(R.id.imageViewProductIcon)
    ImageView imageViewProductIcon;
    @BindView(R.id.textViewProductName)
    TextView textViewProductName;
    @BindView(R.id.relativeSelectProduct)
    RelativeLayout relativeSelectProduct;
    private PayedShopListBean.OBean selectedProductBean;

    @Override
    protected void onInitData() {
        mTitleView.setTitle("发布");
        EventBus.getDefault().register(this);
        setConnectionSymbol(",");
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_entertainment_photo_upload;
    }

    @Override
    protected void onUpLoad(Object data) {

    }

    @Override
    public void initUpLoadView() {

    }

    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
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
    public void onClick(View view) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void gotoSelectProduct(View view) {
        MyPayedProductsActivity.start(getContext(), MyPayedProductsActivity.class);
    }


    @Subscribe
    public void getProductEvent(PayedShopListBean.OBean data) {
        selectedProductBean = data;
        gotoViewSelectProduct.setVisibility(View.GONE);
        relativeSelectProduct.setVisibility(View.VISIBLE);
        textViewProductName.setText(data.getStore_name());
        Glide.with(getContext()).load(selectedProductBean.getImage()).into(imageViewProductIcon);
        AppManager.getAppManager().finishActivity(MyPayedProductsActivity.class);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //发布娱乐
    public void publishEntertainment(View view) {
        if (selectedProductBean == null) {
            ToastUtil.toast("请选择商品");
            return;
        }
        if (selectPhotos.size() == 0) {
            ToastUtil.toast("请选择至少一张图片");
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("id", selectedProductBean.getId() + "");
        map.put("sid", selectedProductBean.getSid() + "");
        map.put("type", 1 + "");//		是	类型：1图片，2视频
        map.put("content", edittextComments.getText().toString());
        requestUpLoad(ConstantUrl.sendEntertaimentUrl, map);
    }
}
