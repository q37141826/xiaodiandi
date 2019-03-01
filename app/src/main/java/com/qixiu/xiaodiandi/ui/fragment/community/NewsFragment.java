package com.qixiu.xiaodiandi.ui.fragment.community;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.qixiu.qixiu.recyclerview_lib.ItemTypesInterf;
import com.qixiu.qixiu.recyclerview_lib.OnRecyclerItemListener;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.XrecyclerViewUtil;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.model.comminity.news.NewsHomeBean;
import com.qixiu.xiaodiandi.model.comminity.news.NewsListInterf;
import com.qixiu.xiaodiandi.ui.activity.community.news.ConsultingDetailsActivity;
import com.qixiu.xiaodiandi.ui.activity.community.news.CurrentConsultingActivity;
import com.qixiu.xiaodiandi.ui.activity.community.news.NewsVideoActivity;
import com.qixiu.xiaodiandi.ui.activity.community.news.NewsVideoDetailsActivity;
import com.qixiu.xiaodiandi.ui.fragment.basefragment.base.RequestFragment;
import com.qixiu.xiaodiandi.ui.fragment.home.ImageUrlAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by my on 2019/1/14.
 */

public class NewsFragment extends RequestFragment implements XRecyclerView.LoadingListener {


    @BindView(R.id.xrecyclerView)
    XRecyclerView xrecyclerView;
    Unbinder unbinder;
    @BindView(R.id.swiprefresh)
    SwipeRefreshLayout swiprefresh;
    private RecyclerView recyclerviewVideo;
    private RollPagerView rollpager;
    private ImageUrlAdapter imageUrlAdapter;
    private VideoAdapter videoAdapter;
    private RecyclerView recyclerView_compantyAffairs;
    private CompanyDynamicAdapter dynamicAdapter;
    private ConsltingAdapter consltingAdapter;

    @Override
    public void moveToPosition(int position) {

    }

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof NewsHomeBean) {
            NewsHomeBean bean = (NewsHomeBean) data;
            imageUrlAdapter.refreshData(bean.getO().getBanner());
            for (int i = 0; i < bean.getO().getVideo().size(); i++) {
                bean.getO().getVideo().get(i).setType(i == 0 ? 0 : 1);
                bean.getO().getVideo().get(i).setLayoutRes(i == 0 ? R.layout.item_video01 : R.layout.item_video02);
            }
            videoAdapter.refreshData(bean.getO().getVideo());
            dynamicAdapter.refreshData(bean.getO().getDynamic());
            consltingAdapter.refreshData(bean.getO().getInfor());
        }
        try {
            xrecyclerView.loadMoreComplete();
            swiprefresh.setRefreshing(false);
        } catch (Exception e) {

        }
    }

    @Override
    public void onError(Exception e) {
        xrecyclerView.loadMoreComplete();
        swiprefresh.setRefreshing(false);
    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {
        xrecyclerView.loadMoreComplete();
        swiprefresh.setRefreshing(false);
    }

    @Override
    protected void onInitViewNew(View view) {
        super.onInitViewNew(view);
        View headerView = View.inflate(getContext(), R.layout.video_header, null);
        findHeader(headerView);
        xrecyclerView.addHeaderView(headerView);
        swiprefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
        loadData();
    }

    private void loadData() {
        post(ConstantUrl.newsUrl, null, new NewsHomeBean());
    }

    private void findHeader(View view) {
        recyclerviewVideo = view.findViewById(R.id.recyclerviewVideo);
        rollpager = view.findViewById(R.id.rollpager);
        recyclerView_compantyAffairs = view.findViewById(R.id.recyclerView_compantyAffairs);
        TextView textViewVideo = view.findViewById(R.id.textViewVideo);
        dynamicAdapter = new CompanyDynamicAdapter();
        recyclerView_compantyAffairs.setAdapter(dynamicAdapter);
        recyclerView_compantyAffairs.setLayoutManager(new GridLayoutManager(getContext(), 2));
        RelativeLayout relativelayoutVideoMore = view.findViewById(R.id.relativelayoutVideoMore);
        RelativeLayout relativelayoutConsultingMore = view.findViewById(R.id.relativelayoutConsultingMore);
        RelativeLayout relativelayoutCompanyMore = view.findViewById(R.id.relativelayoutCompanyMore);
        relativelayoutVideoMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsVideoActivity.start(getContext(), NewsVideoActivity.class);
            }
        });
        textViewVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewsVideoActivity.start(getContext(), NewsVideoActivity.class);
            }
        });
        relativelayoutConsultingMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentConsultingActivity.start(getContext(), CurrentConsultingActivity.class, 3 + "");//	类型1视频专题，2公司动态，3最新资讯，4点滴学院
            }
        });

        relativelayoutCompanyMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentConsultingActivity.start(getContext(), CurrentConsultingActivity.class, 2 + "");//	类型1视频专题，2公司动态，3最新资讯，4点滴学院
            }
        });
        //轮播图
        imageUrlAdapter = new ImageUrlAdapter(rollpager);
        rollpager.setAdapter(imageUrlAdapter);
        ColorPointHintView colorPointHintView = new ColorPointHintView(getActivity(), getResources().getColor(R.color.theme_color), getResources().getColor(R.color.alpha_black_50));
        rollpager.setHintView(colorPointHintView);
        dynamicAdapter.setOnItemClickListener(new OnRecyclerItemListener() {
            @Override
            public void onItemClick(View v, RecyclerView.Adapter adapter, Object data) {
                if (data instanceof NewsHomeBean.OBean.DynamicBean) {
                    NewsHomeBean.OBean.DynamicBean bean = (NewsHomeBean.OBean.DynamicBean) data;
                    bean.setType(2 + "");
                    ConsultingDetailsActivity.start(getContext(), ConsultingDetailsActivity.class, bean);
                }
            }
        });
    }

    @Override
    protected void onInitData() {
        consltingAdapter = new ConsltingAdapter();
        XrecyclerViewUtil.setXrecyclerView(xrecyclerView, this, getContext(), false, 1, null);
        xrecyclerView.setAdapter(consltingAdapter);
        //一个recyclerview加入不同的布局
        setVideoListData();
        consltingAdapter.setOnItemClickListener(new OnRecyclerItemListener() {
            @Override
            public void onItemClick(View v, RecyclerView.Adapter adapter, Object data) {
                if (data instanceof NewsListInterf) {
                    NewsHomeBean.OBean.InforBean bean = (NewsHomeBean.OBean.InforBean) data;
                    bean.setType(3 + "");
                    ConsultingDetailsActivity.start(getContext(), ConsultingDetailsActivity.class, bean);
                }
            }
        });
    }

    private void setVideoListData() {
        recyclerviewVideo.setLayoutManager(new LinearLayoutManager(getContext()));
        List<ItemTypesInterf> datas = new ArrayList<>();
        TestVideoBean testVideoBean = new TestVideoBean(R.layout.item_video01, 0);
        datas.add(testVideoBean);
        testVideoBean = new TestVideoBean(R.layout.item_video02, 1);
        datas.add(testVideoBean);
        videoAdapter = new VideoAdapter(datas);
        recyclerviewVideo.setAdapter(videoAdapter);
        videoAdapter.setOnItemClickListener(new OnRecyclerItemListener() {
            @Override
            public void onItemClick(View v, RecyclerView.Adapter adapter, Object data) {
                if (data instanceof NewsHomeBean.OBean.VideoBean) {
                    NewsHomeBean.OBean.VideoBean bean = (NewsHomeBean.OBean.VideoBean) data;
                    NewsVideoDetailsActivity.start(getContext(), NewsVideoDetailsActivity.class, bean.getId() + "");
                }
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_news;
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
        xrecyclerView.loadMoreComplete();
    }
}
