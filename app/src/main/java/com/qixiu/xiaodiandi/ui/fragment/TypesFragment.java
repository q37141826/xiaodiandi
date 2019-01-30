package com.qixiu.xiaodiandi.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qixiu.qixiu.recyclerview_lib.OnRecyclerItemListener;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.XrecyclerViewUtil;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.model.types.ClassifyBean;
import com.qixiu.xiaodiandi.model.types.TypesProductListBean;
import com.qixiu.xiaodiandi.ui.fragment.basefragment.base.RequestFragment;
import com.qixiu.xiaodiandi.ui.fragment.types.TypesMenueAdapter;
import com.qixiu.xiaodiandi.ui.fragment.types.TypesVipAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by my on 2019/1/2.
 */

public class TypesFragment extends RequestFragment implements XRecyclerView.LoadingListener, OnRecyclerItemListener {
    @BindView(R.id.recyclerviewMenue)
    RecyclerView recyclerviewMenue;
    Unbinder unbinder;
    @BindView(R.id.imageViewHead)
    ImageView imageViewHead;
    @BindView(R.id.xrecyclerView)
    XRecyclerView xrecyclerView;
    private TypesVipAdapter adapterTypes;
    private TypesMenueAdapter menueAdapter;
    //被选择的分类id
    private String selectedId;

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof ClassifyBean) {
            ClassifyBean bean = (ClassifyBean) data;
            menueAdapter.refreshData(bean.getO());
            if (bean.getO().size() != 0) {
                bean.getO().get(0).setSelected(true);
                selectedId = bean.getO().get(0).getId() + "";
                getRightData(selectedId);
            }
        }
        if (data instanceof TypesProductListBean) {
            TypesProductListBean bean = (TypesProductListBean) data;
            adapterTypes.refreshData(bean.getO().getCategory());
        }
    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {

    }

    @Override
    protected void onInitData() {
        mTitleView.getView().setVisibility(View.GONE);
        recyclerviewMenue.setLayoutManager(new LinearLayoutManager(getContext()));
        menueAdapter = new TypesMenueAdapter();
        recyclerviewMenue.setAdapter(menueAdapter);
        menueAdapter.setOnItemClickListener(this);
        XrecyclerViewUtil.setXrecyclerView(xrecyclerView, this, getContext(), false, 1, null);
        adapterTypes = new TypesVipAdapter();
        xrecyclerView.setAdapter(adapterTypes);
//        XrecyclerViewUtil.refreshFictiousData(adapterTypes);
        getTypes();
    }

    private void getTypes() {
        post(ConstantUrl.classifyUrl, null, new ClassifyBean());
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_types;
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
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void moveToPosition(int position) {

    }


    @Override
    public void onItemClick(View v, RecyclerView.Adapter adapter, Object data) {
        if (data instanceof ClassifyBean.OBean) {
            ClassifyBean.OBean bean = (ClassifyBean.OBean) data;
            List<ClassifyBean.OBean> datas = ((RecyclerBaseAdapter) adapter).getDatas();
            for (int i = 0; i < datas.size(); i++) {
                datas.get(i).setSelected(false);
            }
            if (selectedId != null && !selectedId.equals(bean.getId() + "")) {
                //刷新右侧
                getRightData(bean.getId() + "");
            }
            selectedId = bean.getId() + "";
            bean.setSelected(true);
            menueAdapter.notifyDataSetChanged();
        }
    }

    private void getRightData(String cid) {
        Map<String, String> map = new HashMap<>();
        map.put("cid", cid);
        post(ConstantUrl.productsUrl, map, new TypesProductListBean());
    }
}
