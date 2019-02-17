package com.qixiu.xiaodiandi.ui.fragment;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.wigit.hviewpager.HackyViewPager;
import com.qixiu.wigit.picker.MyPopOneListPicker;
import com.qixiu.wigit.picker.SelectedDataBean;
import com.qixiu.wigit.show_dialog.ArshowContextUtil;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.ui.activity.community.upload.EntertainmentPhotoUploadActivity;
import com.qixiu.xiaodiandi.ui.activity.community.upload.EntertainmentVideoUploadActivity;
import com.qixiu.xiaodiandi.ui.fragment.basefragment.base.BaseFragment;
import com.qixiu.xiaodiandi.ui.fragment.basefragment.base.MenueFragment;
import com.qixiu.xiaodiandi.ui.fragment.community.EntertainmentFragment;
import com.qixiu.xiaodiandi.ui.fragment.community.NewsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by my on 2019/1/2.
 */

public class CommunityFragment extends MenueFragment {
    List<BaseFragment> fragments = new ArrayList<>();
    List<String> titles = new ArrayList<>();
    @BindView(R.id.tablelayout)
    SlidingTabLayout tablelayout;
    @BindView(R.id.viewpager)
    HackyViewPager viewpager;
    Unbinder unbinder;
    String permissions[]={Manifest.permission.RECORD_AUDIO};
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
    protected void onInitData() {
        titles.clear();
        fragments.clear();

        titles.add("娱乐社区");
        titles.add("新闻社区");
        mTitleView.getLeftView().setVisibility(View.GONE);
        mTitleView.setTitle("社区");
        mTitleView.setRightText("我要发布");
        mTitleView.getRightText().setBackgroundResource(R.drawable.shape_blue_btn_bg);
        mTitleView.getRightText().getLayoutParams().height = ArshowContextUtil.dp2px(25);
        mTitleView.getRightText().setTextSize(12);
        mTitleView.getRightText().setTextColor(Color.WHITE);
        mTitleView.getRightText().setCompoundDrawablePadding(10);
        mTitleView.getRightText().setGravity(Gravity.CENTER);
        mTitleView.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPicker();
            }
        });


        EntertainmentFragment entertainmentFragment = new EntertainmentFragment();
        fragments.add(entertainmentFragment);
        NewsFragment newsFragment = new NewsFragment();
        fragments.add(newsFragment);
        initFragment(fragments, titles, tablelayout, viewpager);
    }

    private void showPicker() {
        List<SelectedDataBean> datas = new ArrayList<>();
        SelectedDataBean selectedDataBean = new SelectedDataBean("0", "相册");
        datas.add(selectedDataBean);
        selectedDataBean = new SelectedDataBean("1", "拍摄");
        datas.add(selectedDataBean);
        MyPopOneListPicker picker = new MyPopOneListPicker(getContext(), datas, new MyPopOneListPicker.Pop_selectedListenner() {
            @Override
            public void getData(SelectedDataBean data) {
                if (data.getId().equals("0")) {
                    EntertainmentPhotoUploadActivity.start(getContext(), EntertainmentPhotoUploadActivity.class);
                } else {
                    if(hasPermission(getContext(),permissions)){
                        EntertainmentVideoUploadActivity.start(getContext(),EntertainmentVideoUploadActivity.class);
                    }else {
                        hasRequse(getActivity(),1,permissions);
                    }

                }
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_community;
    }

    @Override
    public void moveToPosition(int position) {

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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(hasPermission(getContext(),permissions)){
            EntertainmentVideoUploadActivity.start(getContext(),EntertainmentVideoUploadActivity.class);
        }
    }
}
