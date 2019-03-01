package com.qixiu.xiaodiandi.ui.fragment.community;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qixiu.qixiu.recyclerview_lib.OnRecyclerItemListener;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.XrecyclerViewUtil;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.model.comminity.entertainment.EntertainmentListBean;
import com.qixiu.xiaodiandi.ui.activity.community.entertainment.EntertainmentDetailsActivity;
import com.qixiu.xiaodiandi.ui.activity.mine.mycollection.CollectionCommunityAdapter;
import com.qixiu.xiaodiandi.ui.fragment.basefragment.base.RequestFragment;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by my on 2019/1/14.
 */

public class EntertainmentFragment extends RequestFragment implements XRecyclerView.LoadingListener, OnRecyclerItemListener {
    @BindView(R.id.xrecyclerView)
    XRecyclerView xrecyclerView;
    Unbinder unbinder;
    @BindView(R.id.swip_refreshlayout)
    SwipeRefreshLayout swipRefreshlayout;
    private CollectionCommunityAdapter adapter;
    int pageNo = 1, pageSize = 10;

    @Override
    public void moveToPosition(int position) {

    }

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof EntertainmentListBean) {
            EntertainmentListBean bean = (EntertainmentListBean) data;
            if (pageNo == 1) {
                adapter.refreshData(bean.getO());
            } else {
                adapter.addDatas(bean.getO());
            }
        }
        try {
            xrecyclerView.loadMoreComplete();
            swipRefreshlayout.setRefreshing(false);
        } catch (Exception e) {
        }
    }

    @Override
    public void onError(Exception e) {
        xrecyclerView.loadMoreComplete();
        swipRefreshlayout.setRefreshing(false);
    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {
        xrecyclerView.loadMoreComplete();
        swipRefreshlayout.setRefreshing(false);
    }

    @Override
    protected void onInitData() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        XrecyclerViewUtil.setXrecyclerView(xrecyclerView, this, getContext(), false, 1
                , staggeredGridLayoutManager);
        adapter = new CollectionCommunityAdapter();
        xrecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        swipRefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNo = 1;
                requestData();
            }
        });
        requestData();
    }

    private void requestData() {
        Map<String, String> map = new HashMap<>();
        map.put("pageNo", pageNo + "");
        map.put("pageSize", pageSize + "");
        post(ConstantUrl.entertainmentListUrl, map, new EntertainmentListBean());

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_entertainment;
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
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        pageNo++;
        requestData();
    }

    @Override
    public void onItemClick(View v, RecyclerView.Adapter adapter, Object data) {
        if (data instanceof EntertainmentListBean.OBean) {
            EntertainmentListBean.OBean bean = (EntertainmentListBean.OBean) data;
            EntertainmentDetailsActivity.start(getContext(), EntertainmentDetailsActivity.class, bean);
        }

    }
}
