package com.qixiu.xiaodiandi.ui.activity.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qixiu.qixiu.recyclerview_lib.OnRecyclerItemListener;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.CommonUtils;
import com.qixiu.qixiu.utils.XrecyclerViewUtil;
import com.qixiu.wigit.show_dialog.ArshowContextUtil;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantString;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.constant.IntentDataKeyConstant;
import com.qixiu.xiaodiandi.model.home.HomeSearchBean;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends RequestActivity implements XRecyclerView.LoadingListener, OnRecyclerItemListener {
    @BindView(R.id.edittext_search)
    EditText edittextSearch;
    @BindView(R.id.xrecyclerview)
    XRecyclerView xrecyclerview;
    private SearchAdapter adapter;

    int pageNo = 1, pageSize = 10;

    @Override
    protected void onInitData() {
        String search = getIntent().getStringExtra(IntentDataKeyConstant.DATA);
        edittextSearch.setText(search);
        if (!TextUtils.isEmpty(search)) {
            edittextSearch.setSelection(search.length());
        }
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        XrecyclerViewUtil.setXrecyclerView(xrecyclerview, this, this, true,
                1, manager);
        adapter = new SearchAdapter();
        xrecyclerview.setAdapter(adapter);
        edittextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                getData();
            }
        });
        getData();
        adapter.setOnItemClickListener(this);
    }

    private void getData() {
        Map<String, String> map = new HashMap();
        CommonUtils.putDataIntoMap(map, "keywords", edittextSearch.getText().toString());
        CommonUtils.putDataIntoMap(map, "pageNo", pageNo + "");
        CommonUtils.putDataIntoMap(map, "pageSize", pageSize + "");
        getOkHttpRequestModel().okhHttpPost(ConstantUrl.homeSearch, map, new HomeSearchBean());
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_search;
    }

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof HomeSearchBean) {
            HomeSearchBean bean = (HomeSearchBean) data;
            if (pageNo == 1) {
                adapter.refreshData(bean.getO());
            } else {
                adapter.addDatas(bean.getO());
            }
        }
        XrecyclerViewUtil.stopXrecyclerRefreshLoading(xrecyclerview);
    }

    @Override
    public void onError(Exception e) {
        XrecyclerViewUtil.stopXrecyclerRefreshLoading(xrecyclerview);
    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {
        XrecyclerViewUtil.stopXrecyclerRefreshLoading(xrecyclerview);
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
        pageNo = 1;
        getData();
    }

    @Override
    public void onLoadMore() {
        pageNo++;
        getData();
    }

    public void back(View view) {
        finish();
    }


    //搜索
    public void search(View view) {
        pageNo = 1;
        getData();
    }

    @Override
    public void onItemClick(View v, RecyclerView.Adapter adapter, Object data) {
        if (data instanceof HomeSearchBean.OBean) {
            HomeSearchBean.OBean bean = (HomeSearchBean.OBean) data;
            GoodsDetailsActivity.start(getContext(), GoodsDetailsActivity.class, bean.getId() + "");
        }
    }


    public class SearchAdapter extends RecyclerBaseAdapter {
        @Override
        public int getLayoutId() {
            return R.layout.item_search;
        }

        @Override
        public Object createViewHolder(View itemView, Context context, int viewType) {
            return new SearchHolder(itemView, context, this);
        }

        public class SearchHolder extends RecyclerBaseHolder {
            ImageView imageView;
            TextView textViewName,
                    textViewPrice;

            public SearchHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
                super(itemView, context, adapter);
                imageView = itemView.findViewById(R.id.imageView);
                textViewName = itemView.findViewById(R.id.textViewPhone);
                textViewPrice = itemView.findViewById(R.id.textViewPrice);
            }

            @Override
            public void bindHolder(int position) {
                if (mData instanceof HomeSearchBean.OBean) {
                    HomeSearchBean.OBean bean = (HomeSearchBean.OBean) mData;
                    Glide.with(mContext).load(bean.getImage()).into(imageView);
                    textViewName.setText(bean.getStore_name());
                    textViewPrice.setText(ConstantString.RMB_SYMBOL + bean.getPrice());
                }
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        ArshowContextUtil.hideSoftKeybord(edittextSearch);
    }
}
