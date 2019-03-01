package com.qixiu.xiaodiandi.ui.activity.community.news;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qixiu.qixiu.recyclerview_lib.OnRecyclerItemListener;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.XrecyclerViewUtil;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantRequest;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.model.comminity.news.NewsVideoBean;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsVideoActivity extends RequestActivity implements XRecyclerView.LoadingListener, OnRecyclerItemListener {


    @BindView(R.id.xrecyclerView)
    XRecyclerView xrecyclerView;
    int pageNo = 1, pageSize = 10;
    @BindView(R.id.swiprefresh)
    SwipeRefreshLayout swiprefresh;
    private NewsVideoAdapter adapter;

    @Override
    protected void onInitData() {
        setTitle("视频专栏");
        getData();
        swiprefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNo = 1;
                getData();
            }
        });
    }

    private void getData() {
        ConstantRequest.newsListRequest(getOkHttpRequestModel(), ConstantUrl.newsListUrl, 1 + "", pageNo + "", pageSize + "", new NewsVideoBean());
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_news_video;
    }

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof NewsVideoBean) {
            NewsVideoBean bean = (NewsVideoBean) data;
            if (pageNo == 1) {
                adapter.refreshData(bean.getO());
            } else {
                adapter.addDatas(bean.getO());
            }
        }
        swiprefresh.setRefreshing(false);
        xrecyclerView.loadMoreComplete();
    }

    @Override
    public void onError(Exception e) {
        swiprefresh.setRefreshing(false);
        xrecyclerView.loadMoreComplete();
    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {
        swiprefresh.setRefreshing(false);
        xrecyclerView.loadMoreComplete();
    }

    @Override
    protected void onInitViewNew() {
        XrecyclerViewUtil.setXrecyclerView(xrecyclerView, this, this, false, 1, null);
        adapter = new NewsVideoAdapter();
        xrecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
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
        if (data instanceof NewsVideoBean.OBean) {
            NewsVideoBean.OBean bean = (NewsVideoBean.OBean) data;
            NewsVideoDetailsActivity.start(getContext(), NewsVideoDetailsActivity.class, bean.getId() + "");
        }
    }


    public class NewsVideoAdapter extends RecyclerBaseAdapter {
        @Override
        public int getLayoutId() {
            return R.layout.item_news_video;
        }

        @Override
        public Object createViewHolder(View itemView, Context context, int viewType) {
            return new NewsVideoHolder(itemView, context, this);
        }

        public class NewsVideoHolder extends RecyclerBaseHolder {
            ImageView imageView;
            TextView textViewDescribe,
                    textViewTimes;

            public NewsVideoHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
                super(itemView, context, adapter);
                imageView = itemView.findViewById(R.id.imageView);
                textViewDescribe = itemView.findViewById(R.id.textViewDescribe);
                textViewTimes = itemView.findViewById(R.id.textViewTimes);
            }

            @Override
            public void bindHolder(int position) {
                if (mData instanceof NewsVideoBean.OBean) {
                    NewsVideoBean.OBean oBean = (NewsVideoBean.OBean) mData;
                    Glide.with(mContext).load(oBean.getImage_input()).into(imageView);
                    textViewDescribe.setText(oBean.getTitle());
                    textViewTimes.setText("播放量：   " + oBean.getVisit() + "次");

                }
            }
        }
    }
}
