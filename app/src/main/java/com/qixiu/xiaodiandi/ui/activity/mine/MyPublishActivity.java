package com.qixiu.xiaodiandi.ui.activity.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qixiu.qixiu.recyclerview_lib.OnRecyclerItemListener;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.XrecyclerViewUtil;
import com.qixiu.wigit.VerticalSwipeRefreshLayout;
import com.qixiu.wigit.show_dialog.ArshowContextUtil;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.model.comminity.entertainment.EntertainmentListBean;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;
import com.qixiu.xiaodiandi.ui.activity.community.entertainment.EntertainmentDetailsActivity;
import com.qixiu.xiaodiandi.ui.activity.community.upload.EntertainmentPhotoUploadActivity;
import com.qixiu.xiaodiandi.ui.activity.mine.mycollection.CollectionCommunityAdapter;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyPublishActivity extends RequestActivity implements OnRecyclerItemListener, XRecyclerView.LoadingListener {


    @BindView(R.id.xrecyclerView)
    XRecyclerView xrecyclerView;
    @BindView(R.id.swiprefresh)
    VerticalSwipeRefreshLayout swiprefresh;
    int pageNo = 1, pageSize = 10;
    @BindView(R.id.relativeNothing)
    RelativeLayout relativeNothing;
    private CollectionCommunityAdapter adapter;

    @Override
    protected void onInitData() {
        mTitleView.setRightText("我要发布");
        mTitleView.getRightText().setBackgroundResource(R.drawable.shape_blue_btn_bg);
        mTitleView.getRightText().getLayoutParams().height = ArshowContextUtil.dp2px(25);
        mTitleView.getRightText().setTextSize(12);
        mTitleView.getRightText().setCompoundDrawablePadding(ArshowContextUtil.dp2px(3));
        mTitleView.getRightText().setTextColor(Color.WHITE);
        mTitleView.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoPicker();
            }
        });
        setTitle("我的发布");
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        XrecyclerViewUtil.setXrecyclerView(xrecyclerView, this, getContext(), false, 1
                , staggeredGridLayoutManager);
        adapter = new CollectionCommunityAdapter();
        xrecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        swiprefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNo = 1;
                requestData();
            }
        });
    }

    private void shoPicker() {
        EntertainmentPhotoUploadActivity.start(getContext(), EntertainmentPhotoUploadActivity.class);
    }


    private void requestData() {
        Map<String, String> map = new HashMap<>();
        map.put("pageNo", pageNo + "");
        map.put("pageSize", pageSize + "");
        post(ConstantUrl.myreleaseUrl, map, new EntertainmentListBean());
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
            swiprefresh.setRefreshing(false);
        } catch (Exception e) {
        }

        if (adapter.getDatas().size() == 0) {
            relativeNothing.setVisibility(View.VISIBLE);
        } else {
            relativeNothing.setVisibility(View.GONE);
        }
    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {

    }

    @Override
    protected void onInitViewNew() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_my_publish;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onItemClick(View v, RecyclerView.Adapter adapter, Object data) {
        if (data instanceof EntertainmentListBean.OBean) {
            EntertainmentListBean.OBean bean = (EntertainmentListBean.OBean) data;
            bean.setCanDelete(true);
            EntertainmentDetailsActivity.start(getContext(), EntertainmentDetailsActivity.class, bean);
        }
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
    protected void onStart() {
        super.onStart();
        pageNo = 1;
        requestData();
    }
}
