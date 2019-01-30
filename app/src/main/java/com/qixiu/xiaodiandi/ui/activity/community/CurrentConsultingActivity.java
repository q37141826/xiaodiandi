package com.qixiu.xiaodiandi.ui.activity.community;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qixiu.qixiu.recyclerview_lib.OnRecyclerItemListener;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.XrecyclerViewUtil;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;
import com.qixiu.xiaodiandi.ui.wigit.WritePop;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrentConsultingActivity extends RequestActivity implements OnRecyclerItemListener, XRecyclerView.LoadingListener {


    @BindView(R.id.xrecyclerView)
    XRecyclerView xrecyclerView;
    private WritePop writePop;

    @Override
    protected void onInitData() {
        setTitle("最新咨询");
        XrecyclerViewUtil.setXrecyclerView(xrecyclerView, this, this, false, 1, null);
        CurrentConsultingAdapter adapter = new CurrentConsultingAdapter();
        xrecyclerView.setAdapter(adapter);
        XrecyclerViewUtil.refreshFictiousData(adapter);
        adapter.setOnItemClickListener(this);

    }



    @Override
    protected int getLayoutResource() {
        return R.layout.activity_current_consulting;
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
        ConsultingDetailsActivity.start(getContext(), ConsultingDetailsActivity.class);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

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

            public CurrentConsultingHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
                super(itemView, context, adapter);
            }

            @Override
            public void bindHolder(int position) {

            }
        }
    }
}
