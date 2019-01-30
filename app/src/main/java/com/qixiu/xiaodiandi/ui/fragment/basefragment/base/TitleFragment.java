package com.qixiu.xiaodiandi.ui.fragment.basefragment.base;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.qixiu.qixiu.titleview.TitleView;
import com.qixiu.qixiu.utils.StatusBarUtils;
import com.qixiu.qixiu.utils.ToastUtil;


/**
 * Created by Administrator on 2017/7/18 0018.
 */

public abstract class TitleFragment extends BaseFragment {

    protected TitleView mTitleView;

    @Override
    protected void onInitView(View view) {
        mTitleView = new TitleView(view.getContext());
        ViewGroup viewGroup = (ViewGroup) view.findViewById(com.qixiu.qixiu.R.id.vg_title);
        if (viewGroup != null) {
            viewGroup.addView(mTitleView.getView());
//            adustTitle();
        } else {
            ToastUtil.toast(com.qixiu.qixiu.R.string.main_notfindTitle);
        }
        onInitViewNew(view);
    }

    protected abstract void onInitViewNew(View view);
    public void startActivity(Class T){
        Intent intent=new Intent(getActivity(),T);
        startActivity(intent);
    }
    public void adustTitle() {
        setLayout(StatusBarUtils.getStatusBarHeight(getContext()));
    }
    protected void setLayout(int height) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mTitleView.getView().getLayoutParams();
        params.topMargin =height;
        mTitleView.getView().setY( StatusBarUtils.getStatusBarHeight(getContext()));
        mTitleView.getView().setLayoutParams(params);
//        mTitleView.getView().setY( StatusBarUtils.getStatusBarHeight(getContext()));
    }
}
