package com.qixiu.xiaodiandi.ui.activity.mine.vip;

import android.view.View;

import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;
import com.qixiu.xiaodiandi.ui.activity.mine.mypoints.MyPointsActivity;

public class VipActivity extends RequestActivity {


    @Override
    protected void onInitData() {
        mTitleView.setTitle("个人中心");
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


    //好友列表
    public void gotoFriends(View view) {
        FriendsListActivity.start(getContext(), FriendsListActivity.class);
    }


    //我的点滴
    public void myPoints(View view) {
        MyPointsActivity.start(getContext(),MyPointsActivity.class);
    }


    //跳转二维码推荐
    public void gotoSugestion(View view) {
        InviteActivity.start(getContext(),InviteActivity.class);

    }
}
