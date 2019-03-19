package com.qixiu.xiaodiandi.ui.activity.community.upload;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
import com.qixiu.qixiu.utils.video.VideoThumbUtils;
import com.qixiu.wigit.GotoView;
import com.qixiu.wigit.WechatTakeCameraSelectPop;
import com.qixiu.wigit.show_dialog.ArshowContextUtil;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.model.comminity.entertainment.PayedShopListBean;
import com.qixiu.xiaodiandi.model.login.LoginStatus;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.upload.UploadPictureActivityNew;
import com.qixiu.xiaodiandi.ui.activity.community.entertainment.MyPayedProductsActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.qixiu.qixiu.camera.CircleViedoActivity.FILE_PATH;

public class EntertainmentPhotoUploadActivity extends UploadPictureActivityNew {
    private String[] permissions = {Manifest.permission.RECORD_AUDIO};

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
    @BindView(R.id.imageViewVideo)
    ImageView imageViewVideo;
    @BindView(R.id.relativeLayoutImage)
    RelativeLayout relativeLayoutImage;
    private PayedShopListBean.OBean selectedProductBean;
    String filePath;
    String type = 1 + "";
    private WechatTakeCameraSelectPop wechatTakeCameraSelectPop;

    @Override
    protected void onInitData() {
        mTitleView.setTitle("发布");
        EventBus.getDefault().register(this);
        setConnectionSymbol(",");
        setImageKey("img");
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
        ArshowContextUtil.hideSoftInput(getActivity());
    }

    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }


    //
    @Override
    protected void onItemClickNew(View v, RecyclerView.Adapter adapter, Object data) {
        wechatTakeCameraSelectPop = new WechatTakeCameraSelectPop(getContext());
        wechatTakeCameraSelectPop.showContainsBar(getContext(), navigationBarIsUp);
        wechatTakeCameraSelectPop.setClickListenner(new WechatTakeCameraSelectPop.ClickListenner() {
            @Override
            public void takeVideo() {
                if (!hasPermission(permissions)) {
                    hasRequse(1, permissions);
                } else {
                    gotoRecordView();
                }
            }

            @Override
            public void takePhoto() {
                itemClickNormal(v);
                type = 1 + "";
            }
        });
    }

    private void gotoRecordView() {
        Intent intent = new Intent(getContext(), CircleViedoActivity.class);
        startActivityForResult(intent, 1000);
        recyclerView.setVisibility(View.GONE);
        relativeLayoutImage.setVisibility(View.VISIBLE);
        type = 2 + "";
    }

    //如果这个地方返回ture择默认选择照片，否则就自己定义
    @Override
    public boolean isSelectPhoto() {
        return false;
    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {

    }


    @Override
    public void onSuccess(BaseBean data) {
        super.onSuccess(data);
        ToastUtil.toast(data.getM());
        finish();
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
        Map<String, String> map = new HashMap<>();
        map.put("type", type + "");//		是	类型：1图片，2视频
        map.put("uid", LoginStatus.getId());
        map.put("id", selectedProductBean.getId() + "");
        map.put("sid", selectedProductBean.getSid() + "");
        map.put("content", edittextComments.getText().toString());
        if (type.equals(1 + "")) {
            if (selectPhotos.size() == 0) {
                ToastUtil.toast("请选择至少一张图片");
                return;
            }
            requestUpLoad(ConstantUrl.sendEntertaimentUrl, map);
        } else {
            VideoThumbUtils.getVideoThumbnailBase64(getActivity(), filePath, new VideoThumbUtils.ResultListenner() {
                @Override
                public void result(String base64) {
                    uolpadPhoto(base64);
                }
            });
        }
        mZProgressHUD.show();
    }

    private void uolpadPhoto(String base64) {
        AliOssEngine engine = new AliOssEngine(new AliOssEngine.SendSuccess() {
            @Override
            public void onSuccess(String url) {
                Map<String, String> map = new HashMap<>();
                map.put("id", selectedProductBean.getId() + "");
                map.put("sid", selectedProductBean.getSid() + "");
                map.put("type", type + "");//	图片base64，多个用英文逗号隔开;type=2是视频oss
                map.put("content", edittextComments.getText().toString());
                map.put("img", base64 + "," + url);
                post(ConstantUrl.sendEntertaimentUrl, map, new BaseBean());
            }
        });
        engine.startUpload((filePath), new AliOssEngine.UploadProgress() {
            @Override
            public void sendProgress(String progress) {
                mZProgressHUD.setMessage("" + progress);
            }

            @Override
            public void onSucccess(String msg) {

            }

            @Override
            public void onFailure(String msg) {
                ToastUtil.toast("您的手机环境暂不支持大文件上传");
                mZProgressHUD.dismiss();
            }
        });

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (hasPermission(permissions)) {
            gotoRecordView();
        }
    }

    @Override
    public void onNavigationBarStatusChanged(int navigationBarIsMin) {
        super.onNavigationBarStatusChanged(navigationBarIsMin);
        if (wechatTakeCameraSelectPop != null && wechatTakeCameraSelectPop.isShowing()) {
            wechatTakeCameraSelectPop.dismiss();
            wechatTakeCameraSelectPop.showContainsBar(getContext(), navigationBarIsUp);
        }
    }
}
