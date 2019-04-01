package com.qixiu.xiaodiandi.ui.activity.mine.vip;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.DrawableUtils;
import com.qixiu.wigit.GotoView;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.model.login.LoginStatus;
import com.qixiu.xiaodiandi.model.mine.points.PointsBean;
import com.qixiu.xiaodiandi.model.mine.vip.VipBean;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;
import com.qixiu.xiaodiandi.ui.activity.mine.mypoints.MyPointsActivity;
import com.qixiu.xiaodiandi.utils.GlideUtils;
import com.qixiu.xiaodiandi.utils.ImageUrlUtils;
import com.qixiu.xiaodiandi.utils.NumUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class VipActivity extends RequestActivity {


    @BindView(R.id.circularhead)
    CircleImageView circularhead;
    @BindView(R.id.textViewPointsAll)
    TextView textViewPointsAll;
    @BindView(R.id.textViewPointsCanGet)
    TextView textViewPointsCanGet;
    @BindView(R.id.textViewPointsToday)
    TextView textViewPointsToday;
    @BindView(R.id.textViewPointsMonth)
    TextView textViewPointsMonth;
    @BindView(R.id.textViewPointsGet)
    TextView textViewPointsGet;
    @BindView(R.id.textViewAccount)
    TextView textViewAccount;
    @BindView(R.id.textViewVipName)
    TextView textViewVipName;
    @BindView(R.id.textViewPhone)
    TextView textViewPhone;
    @BindView(R.id.gotoMyFriends)
    GotoView gotoMyFriends;
    @BindView(R.id.gotoFriendNumGroup)
    GotoView gotoFriendNumGroup;
    @BindView(R.id.gotoInvite)
    GotoView gotoInvite;

    @Override
    protected void onInitData() {
        mTitleView.setTitle("个人中心");
        mTitleView.getTitleView().setTextColor(getResources().getColor(R.color.white));
        mTitleView.getLeftView().setTextColor(getResources().getColor(R.color.white));
        DrawableUtils.setLeftDrawableResouce(mTitleView.getLeftView(), getContext(), R.mipmap.titile_back3x);
        getPointsData();
        getVipData();
    }

    @Override
    public void adustTitle() {
//        super.adustTitle();
    }

    private void getVipData() {
        post(ConstantUrl.mywaterUrl, null, new VipBean());
    }

    private void getPointsData() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", LoginStatus.getId());
        post(ConstantUrl.waterlistUrl, map, new PointsBean());
    }

    @Override
    public void setBackRes() {
//        super.setBackRes();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_vip;
    }

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof PointsBean) {
            PointsBean bean = (PointsBean) data;
            textViewPointsAll.setText(NumUtils.formatDouble3(bean.getO().getIntegral(),true) + "积分");
            textViewPointsCanGet.setText("" + bean.getO().getRmd() + "");
            textViewPointsToday.setText(NumUtils.formatDouble3(bean.getO().getToday(),true)+ "积分");
            textViewPointsMonth.setText(NumUtils.formatDouble3(bean.getO().getMonth(),true) + "积分");
            textViewPointsGet.setText(NumUtils.formatDouble3(bean.getO().getAll(),true)+ "积分");
        }
        if (data instanceof VipBean) {
            VipBean bean = (VipBean) data;
            Glide.with(getContext()).load(ImageUrlUtils.getFinnalImageUrl(bean.getO().getAvatar())).into(circularhead);
            GlideUtils.loadImage(ImageUrlUtils.getFinnalImageUrl(bean.getO().getAvatar()), circularhead, getContext());
            textViewPhone.setText("ID: " + bean.getO().getAccount());
            textViewAccount.setText(bean.getO().getPhone());
            textViewVipName.setText(bean.getO().getGroup_name());
            gotoMyFriends.setSecondText(bean.getO().getFriend() + "");
            gotoFriendNumGroup.setSecondText(bean.getO().getFriendsum() + "");

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


    //好友列表	类型：1我的好友，2社群好友
    public void gotoFriends(View view) {
        FriendsListActivity.start(getContext(), FriendsListActivity.class, 1 + "");
    }

    public void gotoMember(View view) {
        FriendsListActivity.start(getContext(), FriendsListActivity.class, 2 + "");
    }

    //我的点滴
    public void myPoints(View view) {
        MyPointsActivity.start(getContext(), MyPointsActivity.class);
    }


    //跳转二维码推荐
    public void gotoSugestion(View view) {
        InviteActivity.start(getContext(), InviteActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
