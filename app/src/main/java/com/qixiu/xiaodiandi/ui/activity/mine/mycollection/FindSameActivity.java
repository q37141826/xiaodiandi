package com.qixiu.xiaodiandi.ui.activity.mine.mycollection;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qixiu.qixiu.recyclerview_lib.OnRecyclerItemListener;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.XrecyclerViewUtil;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantString;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.constant.IntentDataKeyConstant;
import com.qixiu.xiaodiandi.model.mine.collection.MyGoodsCollectionBean;
import com.qixiu.xiaodiandi.model.mine.collection.SimilarBean;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;
import com.qixiu.xiaodiandi.ui.activity.home.GoodsDetailsActivity;
import com.qixiu.xiaodiandi.ui.fragment.home.HomeAdapter;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FindSameActivity extends RequestActivity implements XRecyclerView.LoadingListener, OnRecyclerItemListener {
    @BindView(R.id.imageViewIcon)
    ImageView imageViewIcon;
    @BindView(R.id.textViewName)
    TextView textViewName;
    @BindView(R.id.textViewInfo)
    TextView textViewInfo;
    @BindView(R.id.textViewPrice)
    TextView textViewPrice;
    @BindView(R.id.xrecyclerview)
    XRecyclerView xrecyclerview;
    @BindView(R.id.swiprefresh)
    SwipeRefreshLayout swiprefresh;
    private String id;
    private int pageNo = 1, pageSize = 10;
    private HomeAdapter adapter;

    @Override
    protected void onInitData() {
        setTitle("找相似");
        adapter = new HomeAdapter();

        XrecyclerViewUtil.setXrecyclerView(xrecyclerview, this, this, false, 1,
                new GridLayoutManager(getContext(), 2));
        xrecyclerview.setAdapter(adapter);
        XrecyclerViewUtil.refreshFictiousData(adapter);

        MyGoodsCollectionBean.OBean bean = getIntent().getParcelableExtra(IntentDataKeyConstant.DATA);
        id = bean.getCate_id() + "";//todo 如果这个地方有不同的地方进来，那么要做判断
        setData(bean);
        getData();
        swiprefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNo = 1;
                getData();
            }
        });
        adapter.setOnItemClickListener(this);
    }

    private void setData(MyGoodsCollectionBean.OBean bean) {
        textViewInfo.setText(bean.getStore_info());
        textViewName.setText(bean.getStore_name());
        Glide.with(getContext()).load(bean.getImage()).into(imageViewIcon);
        textViewPrice.setText(ConstantString.RMB_SYMBOL + bean.getPrice());
    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("cid", id);
        map.put("pageNo", pageNo + "");
        map.put("pageSize", pageSize + "");
        post(ConstantUrl.findSimilarUrl, map, new SimilarBean());

    }


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_find_same;
    }

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof SimilarBean) {
            SimilarBean bean = (SimilarBean) data;
            if (pageNo == 1) {
                adapter.refreshData(bean.getO());
            } else {
                adapter.addDatas(bean.getO());
            }
        }
        swiprefresh.setRefreshing(false);
        xrecyclerview.loadMoreComplete();
    }

    @Override
    public void onError(Exception e) {
        swiprefresh.setRefreshing(false);
        xrecyclerview.loadMoreComplete();
    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {
        swiprefresh.setRefreshing(false);
        xrecyclerview.loadMoreComplete();
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
        getData();
    }

    @Override
    public void onItemClick(View v, RecyclerView.Adapter adapter, Object data) {
        if (data instanceof SimilarBean.OBean) {
            SimilarBean.OBean bean = (SimilarBean.OBean) data;
            GoodsDetailsActivity.start(getContext(), GoodsDetailsActivity.class, bean.getId() + "");
        }
    }
}
