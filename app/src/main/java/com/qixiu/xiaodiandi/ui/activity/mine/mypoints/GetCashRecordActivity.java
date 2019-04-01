package com.qixiu.xiaodiandi.ui.activity.mine.mypoints;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.ToastUtil;
import com.qixiu.qixiu.utils.XrecyclerViewUtil;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantString;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.model.mine.points.GetCatshRecordBean;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GetCashRecordActivity extends RequestActivity implements XRecyclerView.LoadingListener {
    int pageNo = 1, pageSize = 10;
    @BindView(R.id.xrecyclerView)
    XRecyclerView xrecyclerView;
    @BindView(R.id.swip_refreshlayout)
    SwipeRefreshLayout swipRefreshlayout;
    @BindView(R.id.relativeNothing)
    RelativeLayout relativeNothing;
    private GetCashRecordAdapter adapter;

    @Override
    protected void onInitData() {
        setTitle("提现明细");
        swipRefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNo = 1;
                requestData();
            }
        });
        XrecyclerViewUtil.setXrecyclerView(xrecyclerView, this, this, false, 1, null);
        adapter = new GetCashRecordAdapter();
        xrecyclerView.setAdapter(adapter);
        requestData();
    }

    private void requestData() {
        Map<String, String> map = new HashMap<>();
        map.put("pageNo", pageNo + "");
        map.put("pageSize", pageSize + "");
        post(ConstantUrl.getCashRecordUrl, map, new GetCatshRecordBean());
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_points_record;
    }

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof GetCatshRecordBean) {
            GetCatshRecordBean pointsRecordsBean = (GetCatshRecordBean) data;
            if (pageNo == 1) {
                adapter.refreshData(pointsRecordsBean.getO());
            } else {
                adapter.addDatas(pointsRecordsBean.getO());
            }
        }
        xrecyclerView.loadMoreComplete();
        swipRefreshlayout.setRefreshing(false);

        if (adapter.getDatas().size() == 0) {
            relativeNothing.setVisibility(View.VISIBLE);
        } else {
            relativeNothing.setVisibility(View.GONE);
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
    protected void onInitViewNew() {

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

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        pageNo++;
        requestData();
    }


    public class GetCashRecordAdapter extends RecyclerBaseAdapter {
        @Override
        public int getLayoutId() {
            return R.layout.item_getcash_record;
        }

        @Override
        public Object createViewHolder(View itemView, Context context, int viewType) {
            return new GetCashRecordHolder(itemView, context, this);
        }

        public class GetCashRecordHolder extends RecyclerBaseHolder {
            TextView textViewContent,
                    textViewMoney,
                    textViewState,
                    textViewTime,textViewReason;
            public GetCashRecordHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
                super(itemView, context, adapter);
                textViewContent = itemView.findViewById(R.id.textViewContent);
                textViewMoney = itemView.findViewById(R.id.textViewMoney);
                textViewState = itemView.findViewById(R.id.textViewState);
                textViewTime = itemView.findViewById(R.id.textViewTime);
                textViewReason = itemView.findViewById(R.id.textViewReason);
            }

            @Override
            public void bindHolder(int position) {
                if (mData instanceof GetCatshRecordBean.OBean) {
                    GetCatshRecordBean.OBean bean = (GetCatshRecordBean.OBean) mData;
                    textViewContent.setText("转至" + bean.getExtract_type() + "账户");
                    textViewTime.setText(bean.getAdd_time());
                    textViewMoney.setText(ConstantString.RMB_SYMBOL + bean.getBalance());
                    if (bean.getStatus() == -1) {
                        textViewState.setText("提现失败");
                        textViewReason.setVisibility(View.VISIBLE);
                    } else if (bean.getStatus() == 0) {
                        textViewReason.setVisibility(View.GONE);
                        textViewState.setText("提现中");
                    } else {
                        textViewReason.setVisibility(View.GONE);
                        textViewState.setText("已到账");
                    }
                    textViewReason.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ToastUtil.toast(bean.getFail_msg());
                        }
                    });
                }
            }
        }
    }
}
