package com.qixiu.xiaodiandi.ui.activity.mine.vip;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.xiaodiandi.BuildConfig;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantString;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.engine.ShareLikeEngine;
import com.qixiu.xiaodiandi.model.login.LoginStatus;
import com.qixiu.xiaodiandi.model.mine.QrCodeBean;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class InviteActivity extends RequestActivity {


    @BindView(R.id.circularHead)
    CircleImageView circularHead;
    @BindView(R.id.textViewPhone)
    TextView textViewPhone;
    @BindView(R.id.textViewId)
    TextView textViewId;
    @BindView(R.id.imageErweima)
    ImageView imageErweima;

    @Override
    protected void onInitData() {
        setTitle("邀请好友");
        post(ConstantUrl.inviteQrcodeUrl, null, new QrCodeBean());
    }

    @Override
    public void adustTitle() {
//        super.adustTitle();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_invite;
    }

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof QrCodeBean) {
            QrCodeBean qrCodeBean = (QrCodeBean) data;
            Glide.with(getContext()).load(qrCodeBean.getO().getAvatar()).into(circularHead);
            Glide.with(getContext()).load(qrCodeBean.getO().getQrcode()).into(imageErweima);
            textViewId.setText(qrCodeBean.getO().getAccount() + "");
            textViewPhone.setText(qrCodeBean.getO().getPhone() + "");
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

    //分享邀请
    public void shareInvite(View view) {
        ShareLikeEngine shareLikeEngine = new ShareLikeEngine();
        shareLikeEngine.releaseShareData(getActivity(), ConstantUrl.SHARE_IMAGE_URL,
                ConstantString.INVITE_SHARECONTENT, BuildConfig.BASE_URL + "/api/home/recommend" + "?uid=" + LoginStatus.getId(), null);
        shareLikeEngine.setShareTitle(ConstantString.INVITE_SHARECONTENT);
    }
}
