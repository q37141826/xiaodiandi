package com.qixiu.xiaodiandi.ui.fragment.basefragment.base;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.List;

/**
 * Created by my on 2018/11/15.
 */

public  abstract  class MenueFragment extends RequestFragment {

    public void initFragment(List<BaseFragment> fragments, List<String> titles, TabLayout tablayout, ViewPager viewPager) {
        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getChildFragmentManager(), fragments);//注意这个地方  fragmentmanager  在fragment里面不要用getSurrpotFragmentmanager
        adapter.setPageTitle(titles);
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);
    }
    public void initFragment(List<BaseFragment> fragments, List<String> titles, SlidingTabLayout tablayout, ViewPager viewPager) {
        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getChildFragmentManager(), fragments);//注意这个地方  fragmentmanager  在fragment里面不要用getSurrpotFragmentmanager
        adapter.setPageTitle(titles);
        viewPager.setAdapter(adapter);
        tablayout.setViewPager(viewPager);
    }

}
