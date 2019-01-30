package com.qixiu.xiaodiandi.ui.fragment.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qixiu.qixiu.recyclerview_lib.OnRecyclerItemListener;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.model.home.HomeBean;
import com.qixiu.xiaodiandi.ui.activity.home.GoodsDetailsActivity;
import com.qixiu.xiaodiandi.ui.fragment.basefragment.base.RequestFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by my on 2019/1/7.
 */

public class HomeViepagerFragment extends RequestFragment implements OnRecyclerItemListener {
    List<HomeBean.OBean.CategoryBean> datas = new ArrayList();
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private HomeViepagerRecyclerAdapter adapter;

    public void setDatas(List<HomeBean.OBean.CategoryBean> datas) {
        this.datas = datas;
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
    protected void onInitViewNew(View view) {
        super.onInitViewNew(view);
    }

    @Override
    protected void onInitData() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 5));
        adapter = new HomeViepagerRecyclerAdapter();
        recyclerView.setAdapter(adapter);
//        adapter.refreshData(datas);//注意不要再这里刷新数据
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.refreshData(datas);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home_viewpager;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void moveToPosition(int position) {

    }

    @Override
    public void onItemClick(View v, RecyclerView.Adapter adapter, Object data) {
        if(data instanceof HomeBean.OBean.CategoryBean){
            HomeBean.OBean.CategoryBean bean= (HomeBean.OBean.CategoryBean) data;
            GoodsDetailsActivity.start(getContext(),GoodsDetailsActivity.class,bean.getId()+"");
        }
    }

    public class HomeViepagerRecyclerAdapter extends RecyclerBaseAdapter {
        @Override
        public int getLayoutId() {
            return R.layout.item_home_viewpager;
        }

        @Override
        public Object createViewHolder(View itemView, Context context, int viewType) {
            return new HomeViepagetRecyclerHolder(itemView, context, this);
        }

        public class HomeViepagetRecyclerHolder extends RecyclerBaseHolder {
            ImageView imageView;
            TextView textView;

            public HomeViepagetRecyclerHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
                super(itemView, context, adapter);
                imageView = itemView.findViewById(R.id.imageView);
                textView = itemView.findViewById(R.id.textView);
            }

            @Override
            public void bindHolder(int position) {
                if (mData instanceof HomeBean.OBean.CategoryBean) {
                    HomeBean.OBean.CategoryBean bean = (HomeBean.OBean.CategoryBean) mData;
                    Glide.with(mContext).load(bean.getPic()).into(imageView);
                    textView.setText(bean.getCate_name());
                }
            }
        }
    }
}
