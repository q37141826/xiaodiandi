package com.qixiu.xiaodiandi.ui.activity.community;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.XrecyclerViewUtil;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsVideoActivity extends RequestActivity implements XRecyclerView.LoadingListener {


    @BindView(R.id.xrecyclerView)
    XRecyclerView xrecyclerView;

    @Override
    protected void onInitData() {
        setTitle("视频专栏");
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_news_video;
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
        XrecyclerViewUtil.setXrecyclerView(xrecyclerView, this, this, false, 1, null);
        NewsVideoAdapter adapter = new NewsVideoAdapter();
        xrecyclerView.setAdapter(adapter);
        XrecyclerViewUtil.refreshFictiousData(adapter);
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

            public NewsVideoHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
                super(itemView, context, adapter);
            }

            @Override
            public void bindHolder(int position) {

            }
        }
    }
}