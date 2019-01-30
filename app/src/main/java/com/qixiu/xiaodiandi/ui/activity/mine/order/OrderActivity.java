package com.qixiu.xiaodiandi.ui.activity.mine.order;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.RelativeLayout;

import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.wigit.hviewpager.HackyViewPager;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.IntentDataKeyConstant;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;
import com.qixiu.xiaodiandi.ui.fragment.basefragment.base.FragmentInterface;
import com.qixiu.xiaodiandi.ui.fragment.basefragment.base.OrderFragmentAdapter;
import com.qixiu.xiaodiandi.ui.fragment.mine.order.OrderFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderActivity extends RequestActivity {


    @BindView(R.id.tablayout_myorder)
    TabLayout tablayoutMyorder;
    @BindView(R.id.viewpager_myoreder)
    HackyViewPager viewpagerMyoreder;
    @BindView(R.id.activity_order)
    RelativeLayout activityOrder;
    private String titles[] = {"全部订单", "待付款", "待发货", "待收货", "已完成"};
    private int index = 0;//跳转到哪一页
    List<FragmentInterface> fragmentInterfaces = null;


    @Override
    protected void onInitData() {
        setTitle("我的订单");
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_order;
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
        if (fragmentInterfaces == null) {
            fragmentInterfaces = new ArrayList<>();
        }

        for (int i = 0; i < titles.length; i++) {
            OrderFragment orderFragment = new OrderFragment();
            //订单类型://全部订单all //待付款0 //待发货1 //待收货2 //已完成3
            switch (i) {
                case 0:
                    orderFragment.setType("all");
                    break;
                case 1:
                    orderFragment.setType("0");
                    break;
                case 2:
                    orderFragment.setType("1");
                    break;
                case 3:
                    orderFragment.setType("2");
                    break;
                case 4:
                    orderFragment.setType("3");
                    break;
            }
            fragmentInterfaces.add(orderFragment);
        }
        OrderFragmentAdapter orderFragmentAdapter = new OrderFragmentAdapter(getSupportFragmentManager(), fragmentInterfaces);
        orderFragmentAdapter.setPageTitle(titles);
        viewpagerMyoreder.setAdapter(orderFragmentAdapter);
        tablayoutMyorder.setupWithViewPager(viewpagerMyoreder);
        index = getIntent().getIntExtra(IntentDataKeyConstant.DATA, 0);
        viewpagerMyoreder.setCurrentItem(index);
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
}
