package com.qixiu.xiaodiandi.ui.activity.community.upload;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qixiu.qixiu.application.AppManager;
import com.qixiu.qixiu.camera.CircleViedoActivity;
import com.qixiu.qixiu.engine.oss.AliOssEngine;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.ToastUtil;
import com.qixiu.wigit.GotoView;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.model.comminity.entertainment.PayedShopListBean;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;
import com.qixiu.xiaodiandi.ui.activity.community.MyPayedProductsActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.qixiu.qixiu.camera.CircleViedoActivity.FILE_PATH;

public class EntertainmentVideoUploadActivity extends RequestActivity  {


    @BindView(R.id.gotoViewSelectProduct)
    GotoView gotoViewSelectProduct;
    @BindView(R.id.imageViewProductIcon)
    ImageView imageViewProductIcon;
    @BindView(R.id.textViewProductName)
    TextView textViewProductName;
    @BindView(R.id.relativeSelectProduct)
    RelativeLayout relativeSelectProduct;
    @BindView(R.id.edittextComments)
    EditText edittextComments;
    @BindView(R.id.imageViewVideo)
    ImageView imageViewVideo;
    private PayedShopListBean.OBean selectedProductBean;
    String filePath;

    @Override
    protected void onInitData() {
        mTitleView.setTitle("发布");
        EventBus.getDefault().register(this);
        Intent intent = new Intent(this, CircleViedoActivity.class);
        startActivityForResult(intent, 1000);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_entertainment_video_upload;
    }

    @Override
    public void onSuccess(BaseBean data) {
        if (data.getUrl().equals(ConstantUrl.sendEntertaimentUrl)) {
            ToastUtil.toast(data.getM());
            finish();
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
    public void onClick(View view) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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


    public void gotoSelectProduct(View view) {
        MyPayedProductsActivity.start(getContext(), MyPayedProductsActivity.class);
    }


    //发布娱乐
    public void publishEntertainment(View view) {
        if (selectedProductBean == null) {
            ToastUtil.toast("请选择商品");
            return;
        }
        AliOssEngine engine = new AliOssEngine(new AliOssEngine.SendSuccess() {
            @Override
            public void onSuccess(String url) {
                Map<String, String> map = new HashMap<>();
                map.put("id", selectedProductBean.getId() + "");
                map.put("sid", selectedProductBean.getSid() + "");
                map.put("type", 2 + "");//	图片base64，多个用英文逗号隔开;type=2是视频oss
                map.put("content", edittextComments.getText().toString());
                map.put("img", url);
                post(ConstantUrl.sendEntertaimentUrl, map, new BaseBean());
            }
        });
        mZProgressHUD.show();
        engine.startUpload((filePath), new AliOssEngine.UploadProgress() {
            @Override
            public void sendProgress(String progress) {
                mZProgressHUD.setMessage("上传进度" + progress);
            }

            @Override
            public void onSucccess(String msg) {

            }

            @Override
            public void onFailure(String msg) {

            }
        });

//
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            filePath = data.getStringExtra(FILE_PATH);
            Glide.with(getContext()).load(filePath).into(imageViewVideo);
        } else {
            finish();
        }
    }


}
