package com.qixiu.xiaodiandi.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.qixiu.qixiu.application.AppManager;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.DrawableUtils;
import com.qixiu.qixiu.utils.StatusBarUtils;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.EventAction;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;
import com.qixiu.xiaodiandi.ui.activity.mine.mycollection.MyCollectionActivity;
import com.qixiu.xiaodiandi.ui.fragment.CommunityFragment;
import com.qixiu.xiaodiandi.ui.fragment.HomeFragment;
import com.qixiu.xiaodiandi.ui.fragment.MarketFragment;
import com.qixiu.xiaodiandi.ui.fragment.MineFragment;
import com.qixiu.xiaodiandi.ui.fragment.TypesFragment;
import com.qixiu.xiaodiandi.ui.fragment.basefragment.base.BaseFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends RequestActivity {
    int bottomResSelected[] = {R.mipmap.home_selected, R.mipmap.home_type_selected, R.mipmap.home_community_selected,
            R.mipmap.home_cart_selected, R.mipmap.home_mine_selected};
    int bottomResNotSelected[] = {R.mipmap.home_not_selected, R.mipmap.home_type_not_selected, R.mipmap.home_community_not_selected,
            R.mipmap.home_cart_not_selected, R.mipmap.home_mine_not_selected};

    @BindView(R.id.framlayoutForFragment)
    FrameLayout framlayoutForFragment;
    @BindView(R.id.textViewHome)
    TextView textViewHome;
    @BindView(R.id.textViewTypes)
    TextView textViewTypes;
    @BindView(R.id.textViewCommunity)
    TextView textViewCommunity;
    @BindView(R.id.textViewMarket)
    TextView textViewMarket;
    @BindView(R.id.textViewMine)
    TextView textViewMine;
    List<BaseFragment> fragments = new ArrayList<>();
    private HomeFragment homeFragment;
    private TypesFragment typesFragment;
    private CommunityFragment communityFragment;
    private MarketFragment marketFragment;
    private MineFragment mineFragment;
    private int eventAction;

    @Override
    protected void onInitData() {
        EventBus.getDefault().register(this);
        mTitleView.getView().setVisibility(View.GONE);
        homeFragment = new HomeFragment();
        fragments.add(homeFragment);
        typesFragment = new TypesFragment();
        fragments.add(typesFragment);
        communityFragment = new CommunityFragment();
        fragments.add(communityFragment);
        marketFragment = new MarketFragment();
        fragments.add(marketFragment);
        mineFragment = new MineFragment();
        fragments.add(mineFragment);

        FragmentTransaction fragmentTransaction = mSupportFragmentManager.beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            fragmentTransaction.add(R.id.framlayoutForFragment, fragments.get(i),
                    fragments.get(i).getClass().getName());
        }
        fragmentTransaction.commit();
        switchFragment(homeFragment, framlayoutForFragment.getId());
        setClick();
    }

    private void setClick() {
        textViewCommunity.setOnClickListener(this);
        textViewHome.setOnClickListener(this);
        textViewMarket.setOnClickListener(this);
        textViewMine.setOnClickListener(this);
        textViewTypes.setOnClickListener(this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
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
        restDrawble();
        DrawableUtils.setTopDrawableResouce(textViewHome, getContext(), bottomResSelected[0]);
    }

    @Override
    public void onClick(View v) {
//        StatusBarUtils.setWindowStatusBarColor(this, Color.TRANSPARENT);
        StatusBarUtils.adustStateBar(this, true);
        restDrawble();
        switch (v.getId()) {
            case R.id.textViewHome:
                switchFragment(homeFragment, framlayoutForFragment.getId());
                DrawableUtils.setTopDrawableResouce(textViewHome, getContext(), bottomResSelected[0]);
                textViewHome.setTextColor(getResources().getColor(R.color.theme_color));
                break;

            case R.id.textViewTypes:
                switchFragment(typesFragment, framlayoutForFragment.getId());
                DrawableUtils.setTopDrawableResouce(textViewTypes, getContext(), bottomResSelected[1]);
                textViewTypes.setTextColor(getResources().getColor(R.color.theme_color));
                break;

            case R.id.textViewCommunity:
                switchFragment(communityFragment, framlayoutForFragment.getId());
                DrawableUtils.setTopDrawableResouce(textViewCommunity, getContext(), bottomResSelected[2]);
                textViewCommunity.setTextColor(getResources().getColor(R.color.theme_color));
                break;

            case R.id.textViewMarket:
                switchFragment(marketFragment, framlayoutForFragment.getId());
                DrawableUtils.setTopDrawableResouce(textViewMarket, getContext(), bottomResSelected[3]);
                textViewMarket.setTextColor(getResources().getColor(R.color.theme_color));
                break;
            case R.id.textViewMine:
                switchFragment(mineFragment, framlayoutForFragment.getId());
                DrawableUtils.setTopDrawableResouce(textViewMine, getContext(), bottomResSelected[4]);
                StatusBarUtils.setWindowStatusBarColor(this, Color.TRANSPARENT);
                textViewMine.setTextColor(getResources().getColor(R.color.theme_color));
                break;
        }
    }

    private void restDrawble() {
        DrawableUtils.setTopDrawableResouce(textViewHome, getContext(), bottomResNotSelected[0]);
        DrawableUtils.setTopDrawableResouce(textViewTypes, getContext(), bottomResNotSelected[1]);
        DrawableUtils.setTopDrawableResouce(textViewCommunity, getContext(), bottomResNotSelected[2]);
        DrawableUtils.setTopDrawableResouce(textViewMarket, getContext(), bottomResNotSelected[3]);
        DrawableUtils.setTopDrawableResouce(textViewMine, getContext(), bottomResNotSelected[4]);
        textViewHome.setTextColor(getResources().getColor(R.color.font_grey));
        textViewTypes.setTextColor(getResources().getColor(R.color.font_grey));
        textViewCommunity.setTextColor(getResources().getColor(R.color.font_grey));
        textViewMarket.setTextColor(getResources().getColor(R.color.font_grey));
        textViewMine.setTextColor(getResources().getColor(R.color.font_grey));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        StatusBarUtils.adustStateBar(this, true);
    }

    @Subscribe
    public void getEvent(EventAction.Action action) {
        eventAction = action.getAction();//先保存常量，在onResume方法切换fragment
        try {
            //如果是收藏界面过来的 ，杀死收藏界面
            AppManager.getAppManager().finishActivity(MyCollectionActivity.class);
        } catch (Exception e) {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (eventAction == EventAction.GOTO_CARTS) {//
            onClick(textViewMarket);
            eventAction = 0;//让这个变量只能使用一次
        }
    }
}
