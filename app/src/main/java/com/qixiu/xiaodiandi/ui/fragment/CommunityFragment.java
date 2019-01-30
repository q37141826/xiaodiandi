package com.qixiu.xiaodiandi.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.wigit.hviewpager.HackyViewPager;
import com.qixiu.xiaodiandi.R;
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
        EntertainmentFragment entertainmentFragment = new EntertainmentFragment();
        fragments.add(entertainmentFragment);
        NewsFragment newsFragment = new NewsFragment();
        fragments.add(newsFragment);
        initFragment(fragments, titles, tablelayout,viewpager);
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
}
