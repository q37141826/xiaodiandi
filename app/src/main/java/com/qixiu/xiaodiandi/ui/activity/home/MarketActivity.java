package com.qixiu.xiaodiandi.ui.activity.home;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.TitleActivity;
import com.qixiu.xiaodiandi.ui.fragment.MarketFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MarketActivity extends TitleActivity {


    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;

    @Override
    protected void onInitData() {
        setTitle("购物车");
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_market;
    }

    @Override
    protected void onInitViewNew() {
        MarketFragment marketFragment = new MarketFragment();
        switchFragment(marketFragment, frameLayout.getId());
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
