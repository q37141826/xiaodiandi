package com.qixiu.xiaodiandi.ui.fragment.community;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qixiu.qixiu.recyclerview_lib.ItemTypesInterf;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.XrecyclerViewUtil;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.ui.activity.community.CurrentConsultingActivity;
import com.qixiu.xiaodiandi.ui.activity.community.NewsVideoActivity;
import com.qixiu.xiaodiandi.ui.fragment.basefragment.base.RequestFragment;

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
    private RecyclerView recyclerviewVideo;

    @Override
    public void moveToPosition(int position) {

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
        View headerView = View.inflate(getContext(), R.layout.video_header, null);
        findHeader(headerView);
        xrecyclerView.addHeaderView(headerView);
    }

    private void findHeader(View view) {
        recyclerviewVideo = view.findViewById(R.id.recyclerviewVideo);
        RelativeLayout relativelayoutVideoMore = view.findViewById(R.id.relativelayoutVideoMore);
        RelativeLayout relativelayoutConsultingMore = view.findViewById(R.id.relativelayoutConsultingMore);
        relativelayoutVideoMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsVideoActivity.start(getContext(),NewsVideoActivity.class);
            }
        });
        relativelayoutConsultingMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentConsultingActivity.start(getContext(),CurrentConsultingActivity.class);
            }
        });

    }

    @Override
    protected void onInitData() {
        ConsltingAdapter adapter = new ConsltingAdapter();
        XrecyclerViewUtil.setXrecyclerView(xrecyclerView, this, getContext(), false, 0, null);
        xrecyclerView.setAdapter(adapter);
        XrecyclerViewUtil.refreshFictiousData(adapter);
        //测试一下一个recyclerview加入不同的布局
        setVideoListData();
    }

    private void setVideoListData() {
        recyclerviewVideo.setLayoutManager(new LinearLayoutManager(getContext()));
        List<ItemTypesInterf> datas = new ArrayList<>();
        TestVideoBean testVideoBean = new TestVideoBean(R.layout.item_video01, 0);
        datas.add(testVideoBean);
        testVideoBean = new TestVideoBean(R.layout.item_video02, 1);
        datas.add(testVideoBean);
        VideoAdapter adapter = new VideoAdapter(datas);
        recyclerviewVideo.setAdapter(adapter);
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

    }
}
