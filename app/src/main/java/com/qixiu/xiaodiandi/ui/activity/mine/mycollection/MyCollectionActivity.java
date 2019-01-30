package com.qixiu.xiaodiandi.ui.activity.mine.mycollection;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.RadioButton;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qixiu.qixiu.recyclerview_lib.OnRecyclerItemListener;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.XrecyclerViewUtil;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.model.mine.collection.MyGoodsCollectionBean;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;
import com.qixiu.xiaodiandi.ui.activity.home.GoodsDetailsActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCollectionActivity extends RequestActivity implements XRecyclerView.LoadingListener, OnRecyclerItemListener {


    @BindView(R.id.radioProduct)
    RadioButton radioProduct;
    @BindView(R.id.radioCommunity)
    RadioButton radioCommunity;
    @BindView(R.id.xrecyclerviewProduct)
    XRecyclerView xrecyclerviewProduct;
    @BindView(R.id.xrecyclerviewCommunity)
    XRecyclerView xrecyclerviewCommunity;
    @BindView(R.id.swip_refreshlayout)
    SwipeRefreshLayout swipRefreshlayout;
    int pageNo = 1, pageSize = 10;
    private CollectionProductAdapter productAdapter;

    @Override
    protected void onInitData() {
        setTitle("我的收藏");
        XrecyclerViewUtil.setXrecyclerView(xrecyclerviewProduct, this, this, false, 1, null);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        XrecyclerViewUtil.setXrecyclerView(xrecyclerviewCommunity, this, this, false, 1, staggeredGridLayoutManager);
        radioProduct.setChecked(true);
        productAdapter = new CollectionProductAdapter();
        xrecyclerviewProduct.setAdapter(productAdapter);
        CollectionCommunityAdapter communityAdapter = new CollectionCommunityAdapter();
        xrecyclerviewCommunity.setAdapter(communityAdapter);
        xrecyclerviewCommunity.setVisibility(View.GONE);
//        XrecyclerViewUtil.refreshFictiousData(productAdapter);
//        XrecyclerViewUtil.refreshFictiousData(communityAdapter);
        swipRefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNo = 1;
                getdata();
            }
        });
        getdata();
        productAdapter.setOnItemClickListener(this);
    }

    private void getdata() {
        Map<String, String> map = new HashMap<>();
        map.put("pageNo", pageNo + "");
        map.put("pageSize", pageSize + "");
        post(ConstantUrl.collectionlist, map, new MyGoodsCollectionBean());
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_my_collection;
    }

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof MyGoodsCollectionBean) {
            MyGoodsCollectionBean bean = (MyGoodsCollectionBean) data;
            if (pageNo == 1) {
                productAdapter.refreshData(bean.getO());
            } else {
                productAdapter.addDatas(bean.getO());
            }
        }
        swipRefreshlayout.setRefreshing(false);
        XrecyclerViewUtil.stopXrecyclerRefreshLoading(xrecyclerviewCommunity);
        XrecyclerViewUtil.stopXrecyclerRefreshLoading(xrecyclerviewProduct);
    }

    @Override
    public void onError(Exception e) {
        swipRefreshlayout.setRefreshing(false);
        XrecyclerViewUtil.stopXrecyclerRefreshLoading(xrecyclerviewCommunity);
        XrecyclerViewUtil.stopXrecyclerRefreshLoading(xrecyclerviewProduct);
    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {
        swipRefreshlayout.setRefreshing(false);
        XrecyclerViewUtil.stopXrecyclerRefreshLoading(xrecyclerviewCommunity);
        XrecyclerViewUtil.stopXrecyclerRefreshLoading(xrecyclerviewProduct);
    }

    @Override
    protected void onInitViewNew() {

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

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        pageNo++;
        getdata();
    }

    @OnClick({R.id.radioProduct, R.id.radioCommunity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.radioProduct:
                xrecyclerviewCommunity.setVisibility(View.GONE);
                xrecyclerviewProduct.setVisibility(View.VISIBLE);
                break;
            case R.id.radioCommunity:
                xrecyclerviewCommunity.setVisibility(View.VISIBLE);
                xrecyclerviewProduct.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onItemClick(View v, RecyclerView.Adapter adapter, Object data) {
        if (data instanceof MyGoodsCollectionBean.OBean) {
            MyGoodsCollectionBean.OBean oBean = (MyGoodsCollectionBean.OBean) data;
            GoodsDetailsActivity.start(getContext(), GoodsDetailsActivity.class, oBean.getId() + "");
        }
    }
}
