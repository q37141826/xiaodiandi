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
import android.widget.RelativeLayout;

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
    RelativeLayout relativeNothing;


    private TypesVipAdapter adapterTypes;
    private TypesMenueAdapter menueAdapter;
    //被选择的分类id
    private String selectedId;
    private ImageView imageViewHead;
    private ClassifyBean classifyBean;
    private ClassifyBean.OBean selectedBean;
    private String vipId;
    private boolean isVip = true;
    private TypesProductListBean.OBean.CategoryBean topBean;

    public void setVipPriceId(String vipPriceId) {
        this.vipPriceId = vipPriceId;
    }

    private String vipPriceId;//这个决定哪一个价钱的商品出现在最上面


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
            if (classifyBean.getO().size() != 0) {
                vipId = classifyBean.getO().get(0).getId() + "";
            }
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
//                        if (bean.getO().getBanner().get(0).getType().equals("1")) {
//                            GoodsDetailsActivity.start(getContext(), GoodsDetailsActivity.class, bean.getO().getBanner().get(0).getUrl());
//                        } else {
//                            GotoWebActivity.start(getContext(), GotoWebActivity.class, bean.getO().getBanner().get(0).getUrl());
//                        }
                    }
                });
                Glide.with(getContext()).load(bean.getO().getBanner().get(0).getPic()).into(imageViewHead);
            }
            //设置第二个banner的位置
            if (isVip) {
                for (int i = 0; i < bean.getO().getCategory().size(); i++) {
                    if ((bean.getO().getCategory().get(i).getCate_name()).contains("39900") && (bean.getO().getBanner().size() > 1)) {
                        bean.getO().getCategory().get(i).setBannerBean(bean.getO().getBanner().get(1));
                    }
                }
                //把点击的vip对应价格的单元放到最高处
                if (!TextUtils.isEmpty(vipPriceId)) {
                    topBean = null;
                    for (int i = 0; i < bean.getO().getCategory().size(); i++) {
                        if ((bean.getO().getCategory().get(i).getId() + "").equals(vipPriceId)) {
                            topBean = bean.getO().getCategory().get(i);
                            break;
                        }
                    }
                    if (topBean != null) {
                        bean.getO().getCategory().remove(topBean);
                        bean.getO().getCategory().add(0, topBean);
                    }
                }
            }
            adapterTypes.refreshData(bean.getO().getCategory());
        }
        if (adapterTypes.getDatas().size() == 0) {
            relativeNothing.setVisibility(View.VISIBLE);
        } else {
            relativeNothing.setVisibility(View.GONE);
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
        relativeNothing = view.findViewById(R.id.relativeNothing);
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
            isVip = vipId.equals(selectedId);
            vipPriceId = null;//选择的价钱id打空，还原排序
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
