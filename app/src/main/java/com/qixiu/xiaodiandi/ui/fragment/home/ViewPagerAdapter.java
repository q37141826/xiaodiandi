package com.qixiu.xiaodiandi.ui.fragment.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.qixiu.xiaodiandi.ui.fragment.basefragment.base.BaseFragment;

import java.util.List;

//这个adapter 能够让viewpager 中的fragment发生变化时刷新不造成其他异常
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<String> mTitles;
    List<BaseFragment>  fragments;
    public ViewPagerAdapter(FragmentManager fm,List<String> titles,List<BaseFragment> baseFragments) {
        super(fm);
        mTitles = titles;
        this.fragments=baseFragments;
    }
    @Override
    public Fragment getItem(int position) {
        BaseFragment fragment =fragments.get(position);
        Log.e("zrg", "getItem: 当前位置position=" + position);
        return fragment;
    }
 
    @Override
    public int getCount() {
        return mTitles == null ? 0 : mTitles.size();
    }
 
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.e("zrg", "instantiateItem: 当前位置position=" + position);
        return super.instantiateItem(container, position);
    }
 
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles == null ? "" : mTitles.get(position);
    }
 
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
 
    public void setTitles(List<String> titles) {
        mTitles = titles;
        notifyDataSetChanged();
    }
}
