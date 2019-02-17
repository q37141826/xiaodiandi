package com.qixiu.xiaodiandi.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jude.rollviewpager.RollPagerView;
import com.qixiu.qixiu.google.zxing.client.android.CaptureActivity;
import com.qixiu.qixiu.recyclerview_lib.OnRecyclerItemListener;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.CommonUtils;
import com.qixiu.qixiu.utils.XrecyclerViewUtil;
import com.qixiu.wigit.VerticalSwipeRefreshLayout;
import com.qixiu.wigit.hviewpager.HackyViewPager;
import com.qixiu.wigit.show_dialog.ArshowContextUtil;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.constant.EventAction;
import com.qixiu.xiaodiandi.model.home.HomeBean;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.GotoWebActivity;
import com.qixiu.xiaodiandi.ui.activity.home.GoodsDetailsActivity;
import com.qixiu.xiaodiandi.ui.activity.home.SearchActivity;
import com.qixiu.xiaodiandi.ui.fragment.basefragment.base.BaseFragment;
import com.qixiu.xiaodiandi.ui.fragment.basefragment.base.RequestFragment;
import com.qixiu.xiaodiandi.ui.fragment.home.HomeAdapter;
import com.qixiu.xiaodiandi.ui.fragment.home.HomeOthersRollAdapter;
import com.qixiu.xiaodiandi.ui.fragment.home.HomeViepagerFragment;
import com.qixiu.xiaodiandi.ui.fragment.home.HomeVipAdapter;
import com.qixiu.xiaodiandi.ui.fragment.home.ImageUrlAdapter;
import com.qixiu.xiaodiandi.ui.fragment.home.ItemClickListenner;
import com.qixiu.xiaodiandi.ui.fragment.home.ViewPagerAdapter;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by my on 2019/1/2.
 */

public class HomeFragment extends RequestFragment implements XRecyclerView.LoadingListener, OnRecyclerItemListener, View.OnClickListener, ItemClickListenner {
    int pageNo = 1, pageSize = 10;
    private XRecyclerView recyclerView;
    VerticalSwipeRefreshLayout swipeRefreshLayout;
    private EditText edittext;
    private RollPagerView rollpager;
    private ImageView imageViewSearch;
    private ImageUrlAdapter imageUrlAdapter;
    private RollPagerView rollpagerProduct;
    private HomeOthersRollAdapter rollAdapter;
    private List<BaseFragment> viewpagerFragments;//分页的10个items
    private View headView;
    private HomeAdapter homeAdapter;
    private RecyclerView recyclerViewVipList;
    private HackyViewPager viewpagerFragment;
    private TabLayout tablelayout;
    private RadioGroup radiogroup;
    private TextView textViewGotoScan;

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof HomeBean) {
            HomeBean homeBean = (HomeBean) data;
            if (pageNo == 1) {
                imageUrlAdapter.refreshData(homeBean.getO().getBanner());//轮播图

                rollAdapter.refreshData(homeBean.getO().getProduct_best());//尖端商品

                //按照列表数据设计分页数
                List<HomeBean.OBean.CategoryBean> category = homeBean.getO().getCategory();
                setViewpagerList(headView, category);//设置10个item能左右滑动页面

                homeAdapter.refreshData(homeBean.getO().getProduct());

                //vip部分的东西
                HomeVipAdapter homeVipAdapter = new HomeVipAdapter();
                homeVipAdapter.setOnItemClickListener(new OnRecyclerItemListener() {
                    @Override
                    public void onItemClick(View v, RecyclerView.Adapter adapter, Object data) {
                        if (data instanceof HomeBean.OBean.VipCategoryBean) {
                            HomeBean.OBean.VipCategoryBean bean = (HomeBean.OBean.VipCategoryBean) data;
                            EventAction.Action action = new EventAction.Action(EventAction.GOTO_TYPE);
                            EventBus.getDefault().post(action);
                        }
                    }
                });
                recyclerViewVipList.setAdapter(homeVipAdapter);
                homeVipAdapter.refreshData(homeBean.getO().getVip_category());
            } else {
                homeAdapter.addDatas(homeBean.getO().getProduct());
            }
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    private void setViewpagerList(View view, List<HomeBean.OBean.CategoryBean> category) {
        viewpagerFragment.removeAllViews();
        viewpagerFragments.clear();
        radiogroup.removeAllViews();
        List<String> titles = new ArrayList<>();
        int max = category.size() / 10 + 1;//页数
        for (int i = 0; i < max; i++) {
            HomeViepagerFragment viepagerFragment01 = new HomeViepagerFragment();
            viewpagerFragments.add(viepagerFragment01);
            List<HomeBean.OBean.CategoryBean> datas = new ArrayList<>();
            if (i < max - 1) {
                for (int j = i * 10; j < (i + 1) * 10; j++) {
                    datas.add(category.get(j));
                }
                viepagerFragment01.setDatas(datas);
            } else {
                for (int j = i * 10; j < category.size(); j++) {
                    datas.add(category.get(j));
                }
                viepagerFragment01.setDatas(datas);
            }
            titles.add(i + "");
        }
        if (viewpagerFragment.getAdapter() != null) {
            ViewPagerAdapter adapter = (ViewPagerAdapter) viewpagerFragment.getAdapter();
            adapter.notifyDataSetChanged();
        } else {
            initFragment(viewpagerFragments, tablelayout, titles, viewpagerFragment);
            viewpagerFragment.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    ((RadioButton) radiogroup.getChildAt(position)).setChecked(true);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

        }
        //便利一下页面数，添加radiobutton
        for (int i = 0; i < viewpagerFragments.size(); i++) {
            RadioButton radioButton = new RadioButton(view.getContext());
            radioButton.setBackgroundResource(R.drawable.selector_home);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ArshowContextUtil.dp2px(20), ArshowContextUtil.dp2px(5));
            params.setMargins(ArshowContextUtil.dp2px(5), 0, 0, 0);
            radioButton.setLayoutParams(params);
            Bitmap a = null;
            radioButton.setButtonDrawable(new BitmapDrawable(a));
            radiogroup.addView(radioButton);
        }
        if (viewpagerFragments.size() != 0) {
            ((RadioButton) radiogroup.getChildAt(0)).setChecked(true);
        }

    }

    @Override
    public void onError(Exception e) {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        pageNo++;
        getData();
    }

    @Override
    protected void onInitViewNew(View view) {
        super.onInitViewNew(view);
        recyclerView = view.findViewById(R.id.recyclerView);
        XrecyclerViewUtil.setXrecyclerView(recyclerView, this, getContext(), false, 1, new GridLayoutManager(getContext(), 2));
        headView = LayoutInflater.from(getContext()).inflate(R.layout.home_header, null);
        recyclerView.addHeaderView(headView);
        findHeadView(headView);
        swipeRefreshLayout = view.findViewById(R.id.swip_refreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNo = 1;
                getData();
            }
        });
        homeAdapter = new HomeAdapter();
        recyclerView.setAdapter(homeAdapter);
        homeAdapter.setOnItemClickListener(this);
    }

    private void findHeadView(View view) {
        recyclerViewVipList = view.findViewById(R.id.recyclerViewVipList);
        recyclerViewVipList.setLayoutManager(new GridLayoutManager(getContext(), 3));

        //设置小列表
        radiogroup = view.findViewById(R.id.radiogroup);
        radiogroup.removeAllViews();
        viewpagerFragments = new ArrayList<>();
        viewpagerFragment = view.findViewById(R.id.viewpagerFragment);
        tablelayout = view.findViewById(R.id.tablelayout);

        edittext = view.findViewById(R.id.edittext_search);
        //设置轮播图
        rollpager = view.findViewById(R.id.rollpager);
        imageViewSearch = view.findViewById(R.id.imageViewSearch);
        imageViewSearch.setOnClickListener(this);
        rollpager.getViewPager().setPageMargin(20);//设置page间间距，自行根据需求设置

        imageUrlAdapter = new ImageUrlAdapter(rollpager);
        rollpager.setAdapter(imageUrlAdapter);
        imageUrlAdapter.setItemClickListenner(this);
//        adapter.refreshData(banners);
        //设置轮播商品
        rollpagerProduct = view.findViewById(R.id.rollpagerProduct);
        rollAdapter = new HomeOthersRollAdapter(rollpagerProduct);
        rollpagerProduct.setAdapter(rollAdapter);
        rollAdapter.setItemClickListenner(this);
        rollpagerProduct.getViewPager().setPageMargin(ArshowContextUtil.dp2px(24));//设置page间间距，自行根据需求设置
        rollpagerProduct.getViewPager().setOffscreenPageLimit(3);
        rollpagerProduct.getViewPager().setClipChildren(false);
        rollpagerProduct.getViewPager().setPageTransformer(true, new ScaleInTransformer());
        RelativeLayout.LayoutParams layoutParams02 = (RelativeLayout.LayoutParams) rollpagerProduct.getViewPager().getLayoutParams();
        layoutParams02.setMargins(ArshowContextUtil.dp2px(89), 0, ArshowContextUtil.dp2px(89), 0);
//        rollAdapter.refreshData(productBeans);
        textViewGotoScan = view.findViewById(R.id.textViewGotoScan);
        textViewGotoScan.setOnClickListener(this);
    }

    @Override
    protected void onInitData() {
        mTitleView.getView().setVisibility(View.GONE);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }


    public void initFragment(List<BaseFragment> fragments, TabLayout tablayout, List<String> titles, ViewPager viewPager) {
//        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getChildFragmentManager(), fragments);//注意这个地方  fragmentmanager  在fragment里面不要用getSurrpotFragmentmanager
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(), titles, fragments);//注意这个地方  fragmentmanager  在fragment里面不要用getSurrpotFragmentmanager
        viewPager.setAdapter(adapter);
        tablayout.setVisibility(View.GONE);
        tablayout.setupWithViewPager(viewPager);
    }


    //recyclerview条目点击
    @Override
    public void onItemClick(View v, RecyclerView.Adapter adapter, Object data) {
        if (data instanceof HomeBean.OBean.ProductBean) {
            HomeBean.OBean.ProductBean bean = (HomeBean.OBean.ProductBean) data;
            GoodsDetailsActivity.start(getContext(), GoodsDetailsActivity.class, bean.getId() + "");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        rollpager.requestFocus();
        ArshowContextUtil.hideSoftKeybord(edittext);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewSearch:
                SearchActivity.start(getContext(), SearchActivity.class, edittext.getText().toString());
                break;
            case R.id.textViewGotoScan:
                Intent intent = new Intent(getContext(), CaptureActivity.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    public void moveToPosition(int position) {

    }

    @Override
    public void onStart() {
        super.onStart();
        pageNo = 1;
        getData();
    }

    private void getData() {
        Map<String, String> map = new HashMap();
        CommonUtils.putDataIntoMap(map, "pageNo", pageNo + "");
        CommonUtils.putDataIntoMap(map, "pageSize", pageSize + "");
        post(ConstantUrl.homeUrl, map, new HomeBean());
    }


    //条目点击事件
    @Override
    public void onclick(Object data) {
        if (data instanceof HomeBean.OBean.BannerBean) {
            HomeBean.OBean.BannerBean bean = (HomeBean.OBean.BannerBean) data;
            //如果type=2，那么就是url
            if (bean.getType().equals("2")) {
                GotoWebActivity.start(getContext(), GotoWebActivity.class, bean.getUrl());
            } else {
                GoodsDetailsActivity.start(getContext(), GoodsDetailsActivity.class, bean.getUrl());
            }
        }
        if (data instanceof HomeBean.OBean.ProductBestBean) {
            HomeBean.OBean.ProductBestBean bean = (HomeBean.OBean.ProductBestBean) data;
            GoodsDetailsActivity.start(getContext(), GoodsDetailsActivity.class, bean.getId() + "");
        }
    }


}
