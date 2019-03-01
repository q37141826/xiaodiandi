package com.qixiu.xiaodiandi.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
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
import com.qixiu.xiaodiandi.ui.activity.baseactivity.GotoWebActivity;
import com.qixiu.xiaodiandi.ui.activity.home.GoodsDetailsActivity;
import com.qixiu.xiaodiandi.ui.activity.home.SearchActivity;
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

public class TypesFragment extends RequestFragment implements XRecyclerView.LoadingListener, OnRecyclerItemListener, View.OnClickListener {
    @BindView(R.id.recyclerviewMenue)
    RecyclerView recyclerviewMenue;
    Unbinder unbinder;

    @BindView(R.id.xrecyclerView)
    XRecyclerView xrecyclerView;
    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.imageViewGotoSearch)
    ImageView imageViewGotoSearch;
    private TypesVipAdapter adapterTypes;
    private TypesMenueAdapter menueAdapter;
    //被选择的分类id
    private String selectedId;
    private ImageView imageViewHead;
    private ClassifyBean classifyBean;
    private ClassifyBean.OBean selectedBean;

    public String getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(String selectedId) {
        this.selectedId = selectedId;
    }

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof ClassifyBean) {
            classifyBean = (ClassifyBean) data;
            if (!TextUtils.isEmpty(selectedId)) {
                for (int i = 0; i < classifyBean.getO().size(); i++) {
                    if ((classifyBean.getO().get(i).getId() + "").equals(selectedId)) {
                        classifyBean.getO().get(i).setSelected(true);
                        selectedBean = classifyBean.getO().get(i);
                    }
                }
            } else {
                if (classifyBean.getO().size() != 0) {
                    classifyBean.getO().get(0).setSelected(true);
                    selectedId = classifyBean.getO().get(0).getId() + "";
                }
            }
            menueAdapter.refreshData(classifyBean.getO());
            getRightData(selectedId);
        }
        if (data instanceof TypesProductListBean) {
            TypesProductListBean bean = (TypesProductListBean) data;
            //头部banner
            if (bean.getO().getBanner().size() == 0) {
                imageViewHead.setImageResource(R.drawable.white_btn_bg);
            } else {
                imageViewHead.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (bean.getO().getBanner().get(0).getType().equals("1")) {
                            GoodsDetailsActivity.start(getContext(), GoodsDetailsActivity.class, bean.getO().getBanner().get(0).getUrl());
                        } else {
                            GotoWebActivity.start(getContext(), GotoWebActivity.class, bean.getO().getBanner().get(0).getUrl());
                        }
                    }
                });
                Glide.with(getContext()).load(bean.getO().getBanner().get(0).getPic()).into(imageViewHead);
            }
            //是否为第一个vip
            if (classifyBean != null) {
                if (classifyBean.getO().get(0).isSelected()) {//如果选中了会员专区，那么所有的item刷为vip
                    adapterTypes.setIs_vip(true);
                } else {
                    adapterTypes.setIs_vip(false);
                }
            }
            adapterTypes.refreshData(bean.getO().getCategory());
            if (!adapterTypes.isIs_vip()) {
                //脚底下的foot
                if (bean.getO().getBanner().size() > 2) {
                    bean.getO().getCategory().get(bean.getO().getCategory().size() - 1).setBannerFoot(bean.getO().getBanner().get(1).getPic());
                }
            } else {
                TypesProductListBean.OBean.CategoryBean lastBean = new TypesProductListBean.OBean.CategoryBean();
                lastBean.setLast(true);
                bean.getO().getCategory().add(lastBean);//添加这一个
            }


        }
    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {

    }

    @Override
    protected void onInitView(View view) {
        super.onInitView(view);
        View headerView = View.inflate(getContext(), R.layout.header_types, null);
        xrecyclerView.addHeaderView(headerView);
        imageViewHead = headerView.findViewById(R.id.imageViewHead);
        imageViewGotoSearch.setOnClickListener(this);
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
        getTypes();
        adapterTypes.setOnItemClickListener(this);
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
        try {
            xrecyclerView.loadMoreComplete();
        } catch (Exception e) {
        }

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
        adapterTypes.refreshData(null);
        Map<String, String> map = new HashMap<>();
        map.put("cid", cid);
        post(ConstantUrl.productsUrl, map, new TypesProductListBean());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            getTypes();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageViewGotoSearch:
                SearchActivity.start(getContext(), SearchActivity.class, edittext.getText().toString());
                break;
        }
    }
}
