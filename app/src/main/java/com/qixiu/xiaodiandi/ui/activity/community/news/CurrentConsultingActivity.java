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
import com.qixiu.wigit.VerticalSwipeRefreshLayout;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantRequest;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.constant.IntentDataKeyConstant;
import com.qixiu.xiaodiandi.model.comminity.news.NewsListBean;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;
import com.qixiu.xiaodiandi.ui.wigit.WritePop;
import com.qixiu.xiaodiandi.utils.ImageUrlUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrentConsultingActivity extends RequestActivity implements OnRecyclerItemListener, XRecyclerView.LoadingListener {


    @BindView(R.id.xrecyclerView)
    XRecyclerView xrecyclerView;
    @BindView(R.id.swip_refreshlayout)
    VerticalSwipeRefreshLayout swipRefreshlayout;
    private WritePop writePop;
    int pageNo = 1, pageSize = 10;
    private String type;//最新资讯还是公司动态的判断
    private CurrentConsultingAdapter adapter;

    @Override
    protected void onInitData() {
        type = getIntent().getStringExtra(IntentDataKeyConstant.DATA);
        setTitle("最新咨询");
        XrecyclerViewUtil.setXrecyclerView(xrecyclerView, this, this, false, 1, null);
        adapter = new CurrentConsultingAdapter();
        xrecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        getData();
        swipRefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNo = 1;
                getData();
            }
        });
    }

    private void getData() {
        ConstantRequest.newsListRequest(getOkHttpRequestModel(), ConstantUrl.newsListUrl, type,
                pageNo + "", pageSize + "", new NewsListBean());

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_current_consulting;
    }

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof NewsListBean) {
            NewsListBean bean = (NewsListBean) data;
            if (pageNo == 1) {
                adapter.refreshData(bean.getO());
            } else {
                adapter.addDatas(bean.getO());
            }

        }
        swipRefreshlayout.setRefreshing(false);
        xrecyclerView.loadMoreComplete();
    }

    @Override
    public void onError(Exception e) {
        swipRefreshlayout.setRefreshing(false);
        xrecyclerView.loadMoreComplete();
    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {
        swipRefreshlayout.setRefreshing(false);
        xrecyclerView.loadMoreComplete();
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
    public void onItemClick(View v, RecyclerView.Adapter adapter, Object data) {
        if (data instanceof NewsListBean.OBean) {
            NewsListBean.OBean bean = (NewsListBean.OBean) data;
            bean.setType(type);
            ConsultingDetailsActivity.start(getContext(), ConsultingDetailsActivity.class, bean);
        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        pageNo++;
        getData();
    }

    public class CurrentConsultingAdapter extends RecyclerBaseAdapter {
        @Override
        public int getLayoutId() {
            return R.layout.item_current_consulting;
        }

        @Override
        public Object createViewHolder(View itemView, Context context, int viewType) {
            return new CurrentConsultingHolder(itemView, context, this);
        }

        public class CurrentConsultingHolder extends RecyclerBaseHolder {
            TextView textViewDescribe;
            ImageView imageView;
            TextView textViewTime;

            public CurrentConsultingHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
                super(itemView, context, adapter);
                textViewDescribe = itemView.findViewById(R.id.textViewDescribe);
                imageView = itemView.findViewById(R.id.imageView);
                textViewTime = itemView.findViewById(R.id.textViewTime);
            }

            @Override
            public void bindHolder(int position) {
                if (mData instanceof NewsListBean.OBean) {
                    NewsListBean.OBean bean = (NewsListBean.OBean) mData;
                    Glide.with(mContext).load(ImageUrlUtils.getFinnalImageUrl(bean.getImage_input())).into(imageView);
                    textViewDescribe.setText(bean.getTitle());
                    textViewTime.setText("发布时间：    " + bean.getAdd_time());

                }
            }
        }
    }
}
