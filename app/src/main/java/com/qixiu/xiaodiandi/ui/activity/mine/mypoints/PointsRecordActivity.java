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
import com.qixiu.qixiu.utils.XrecyclerViewUtil;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.model.mine.points.PointsRecordsBean;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PointsRecordActivity extends RequestActivity implements XRecyclerView.LoadingListener {

    int pageNo = 1, pageSize = 20;
    @BindView(R.id.xrecyclerView)
    XRecyclerView xrecyclerView;
    @BindView(R.id.swip_refreshlayout)
    SwipeRefreshLayout swipRefreshlayout;
    @BindView(R.id.relativeNothing)
    RelativeLayout relativeNothing;
    private PointsRecordAdapter adapter;

    @Override
    protected void onInitData() {
        setTitle("积分明细");
        swipRefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNo = 1;
                requestData();
            }
        });
        XrecyclerViewUtil.setXrecyclerView(xrecyclerView, this, this, false, 1, null);
        adapter = new PointsRecordAdapter();
        xrecyclerView.setAdapter(adapter);
        requestData();
    }

    private void requestData() {
        Map<String, String> map = new HashMap<>();
        map.put("pageNo", pageNo + "");
        map.put("pageSize", pageSize + "");
        post(ConstantUrl.pointsListUrl, map, new PointsRecordsBean());
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_points_record;
    }

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof PointsRecordsBean) {
            PointsRecordsBean pointsRecordsBean = (PointsRecordsBean) data;
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


    public class PointsRecordAdapter extends RecyclerBaseAdapter {
        @Override
        public int getLayoutId() {
            return R.layout.item_points_record;
        }

        @Override
        public Object createViewHolder(View itemView, Context context, int viewType) {
            return new PointsRecordHolder(itemView, context, this);
        }

        public class PointsRecordHolder extends RecyclerBaseHolder {
            TextView textViewName,
                    textViewTime,
                    textViewPoints;

            public PointsRecordHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
                super(itemView, context, adapter);
                textViewName = itemView.findViewById(R.id.textViewName);
                textViewTime = itemView.findViewById(R.id.textViewTime);
                textViewPoints = itemView.findViewById(R.id.textViewPoints);
            }

            @Override
            public void bindHolder(int position) {
                if (mData instanceof PointsRecordsBean.OBean) {
                    PointsRecordsBean.OBean bean = (PointsRecordsBean.OBean) mData;
                    if (bean.getAdd() == 1) {
                        textViewPoints.setText("+" + bean.getIntegral());
                        textViewPoints.setTextColor(mContext.getResources().getColor(R.color.theme_color));
                    } else {
                        textViewPoints.setText("-" + bean.getIntegral());
                        textViewPoints.setTextColor(mContext.getResources().getColor(R.color.font_grey));
                    }
                    textViewName.setText(bean.getContent());
                    textViewTime.setText(bean.getAddtime());
                }
            }
        }
    }
}
