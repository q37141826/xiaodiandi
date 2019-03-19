package com.qixiu.xiaodiandi.ui.activity.community.entertainment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.model.comminity.entertainment.PayedShopListBean;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyPayedProductsActivity extends RequestActivity implements XRecyclerView.LoadingListener, OnRecyclerItemListener {


    @BindView(R.id.xrecyclerview)
    XRecyclerView xrecyclerview;
    @BindView(R.id.swip_refreshlayout)
    SwipeRefreshLayout swipRefreshlayout;
    int pageNo = 1, pageSize = 10;
    @BindView(R.id.relativeNothing)
    RelativeLayout relativeNothing;
    private MyPayedAdapter adapter;

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof PayedShopListBean) {
            PayedShopListBean bean = (PayedShopListBean) data;
            if (pageNo == 1) {
                adapter.refreshData(bean.getO());
            } else {
                adapter.addDatas(bean.getO());
            }
        }
        swipRefreshlayout.setRefreshing(false);
        xrecyclerview.loadMoreComplete();
        if(adapter.getDatas().size()==0){
            relativeNothing .setVisibility(View.VISIBLE);
        }else {
            relativeNothing .setVisibility(View.GONE);
        }
    }

    @Override
    public void onError(Exception e) {
        swipRefreshlayout.setRefreshing(false);
        xrecyclerview.loadMoreComplete();
    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {
        swipRefreshlayout.setRefreshing(false);
        xrecyclerview.loadMoreComplete();
    }

    @Override
    protected void onInitViewNew() {
        setTitle("我已购买");
        XrecyclerViewUtil.setXrecyclerView(xrecyclerview, this, this, false, 1, null);
        adapter = new MyPayedAdapter();
        xrecyclerview.setAdapter(adapter);
        requestData();
        swipRefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNo = 1;
                requestData();
            }
        });
        adapter.setOnItemClickListener(this);
    }

    private void requestData() {
        Map<String, String> map = new HashMap<>();
        map.put("pageNo", pageNo + "");
        map.put("pageSize", pageSize + "");
        post(ConstantUrl.payedProductListUrl, map, new PayedShopListBean());
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onInitData() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_my_payed_products;
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

    @Override
    public void onItemClick(View v, RecyclerView.Adapter adapter, Object data) {
        EventBus.getDefault().post(data);
    }

    public class MyPayedAdapter extends RecyclerBaseAdapter {
        @Override
        public int getLayoutId() {
            return R.layout.item_my_payed;
        }

        @Override
        public Object createViewHolder(View itemView, Context context, int viewType) {
            return new MyPayedHolder(itemView, context, this);
        }

        public class MyPayedHolder extends RecyclerBaseHolder {
            ImageView imageView;
            TextView textViewDescribe;

            public MyPayedHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
                super(itemView, context, adapter);
                imageView = itemView.findViewById(R.id.imageView);
                textViewDescribe = itemView.findViewById(R.id.textViewDescribe);
            }

            @Override
            public void bindHolder(int position) {
                if (mData instanceof PayedShopListBean.OBean) {
                    PayedShopListBean.OBean bean = (PayedShopListBean.OBean) mData;
                    Glide.with(mContext).load(bean.getImage()).into(imageView);
                    textViewDescribe.setText(bean.getStore_name());
                }
            }
        }
    }

}
