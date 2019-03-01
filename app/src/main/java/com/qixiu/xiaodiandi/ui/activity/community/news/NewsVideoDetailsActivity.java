package com.qixiu.xiaodiandi.ui.activity.community.news;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.ToastUtil;
import com.qixiu.qixiu.utils.XrecyclerViewUtil;
import com.qixiu.qixiu.utils.html_utils.HtmlUtils;
import com.qixiu.xiaodiandi.BuildConfig;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantRequest;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.constant.IntentDataKeyConstant;
import com.qixiu.xiaodiandi.engine.ShareLikeEngine;
import com.qixiu.xiaodiandi.model.comminity.news.NewsDetailsBean;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;
import com.qixiu.xiaodiandi.ui.wigit.WritePop;
import com.qixiu.xiaodiandi.utils.ImageUrlUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class NewsVideoDetailsActivity extends RequestActivity implements XRecyclerView.LoadingListener {

    @BindView(R.id.xrecyclerview)
    XRecyclerView xrecyclerview;
    @BindView(R.id.swiprefresh)
    SwipeRefreshLayout swiprefresh;
    private String id;
    private JZVideoPlayerStandard jcplayer;
    private TextView textViewContent;
    private TextView textViewPlayTimes;
    private TextView textViewTransTimes;
    private TextView textViewGiveComments;
    private ImageView imageViewShare;
    private RecyclerView recyclerviewSuggistion;
    private NewsDetailsBean detailsBean;
    private CommentAdapter commentAdapter;
    private WritePop writePop;
    private LinearLayout linearlayout_parent;
    private SuggestionAdapter suggestionAdapter;

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof NewsDetailsBean) {
            detailsBean = (NewsDetailsBean) data;
            jcplayer.setUp(BuildConfig.BASE_URL + detailsBean.getO().getVideo().replace(BuildConfig.BASE_URL, ""),
                    JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL);
            commentAdapter.refreshData(detailsBean.getE());
            HtmlUtils.getInstance().setHtml(textViewContent, detailsBean.getO().getContent(), this);
            textViewPlayTimes.setText(detailsBean.getO().getMessage().get(0).getVisit() + "次播放");
            textViewTransTimes.setText(detailsBean.getO().getForward() + "");
            suggestionAdapter.refreshData(detailsBean.getO().getMessage());
        }
        mTitleView.getView().setVisibility(View.GONE);
        swiprefresh.setRefreshing(false);
        xrecyclerview.loadMoreComplete();
        if (ConstantUrl.leaveCommentsUrl.equals(data.getUrl())) {
            ToastUtil.toast(data.getM());
            writePop.dismiss();
            getData();
        }
    }

    @Override
    public void onError(Exception e) {
        swiprefresh.setRefreshing(false);
        xrecyclerview.loadMoreComplete();
    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {
        swiprefresh.setRefreshing(false);
        xrecyclerview.loadMoreComplete();
    }

    @Override
    protected void onInitViewNew() {
        setTitle("");
        mTitleView.getView().setVisibility(View.GONE);
        id = getIntent().getStringExtra(IntentDataKeyConstant.DATA);
        XrecyclerViewUtil.setXrecyclerView(xrecyclerview, this, this, false, 1, null);
        View headerView = View.inflate(getContext(), R.layout.header_videos_details, null);
        xrecyclerview.addHeaderView(headerView);
        initHead(headerView);
        swiprefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
        commentAdapter = new CommentAdapter();
        xrecyclerview.setAdapter(commentAdapter);
    }

    private void initHead(View view) {
        jcplayer = view.findViewById(R.id.jcplayer);

        textViewContent = view.findViewById(R.id.textViewContent);
        textViewPlayTimes = view.findViewById(R.id.textViewPlayTimes);
        textViewTransTimes = view.findViewById(R.id.textViewTransTimes);
        textViewGiveComments = view.findViewById(R.id.textViewGiveComments);
        imageViewShare = view.findViewById(R.id.imageViewShare);
        linearlayout_parent = view.findViewById(R.id.linearlayout_parent);
        recyclerviewSuggistion = view.findViewById(R.id.recyclerviewSuggistion);


        jcplayer.batteryTimeLayout.setVisibility(View.GONE);
        jcplayer.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        suggestionAdapter = new SuggestionAdapter();
        recyclerviewSuggistion.setLayoutManager(new LinearLayoutManager(this));
        recyclerviewSuggistion.setAdapter(suggestionAdapter);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onInitData() {
        getData();
    }

    private void getData() {
        ConstantRequest.newsDetailsRequest(getOkHttpRequestModel(), ConstantUrl.newsinfoUrl, 1 + "", id + "", new NewsDetailsBean());
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_news_video_details;
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
        swiprefresh.setRefreshing(false);
        xrecyclerview.loadMoreComplete();
    }

    //留言
    public void giveComments(View view) {
        writePop = new WritePop(getContext());
        writePop.setSendListenner(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConstantRequest.leaveComments(getOkHttpRequestModel(), ConstantUrl.leaveCommentsUrl,
                        1 + "", detailsBean.getO().getId() + "", writePop.getText().toString(), "", "", new BaseBean());
            }
        });
    }

    //分享
    public void shareVideo(View view) {
        ShareLikeEngine shareLikeEngine = new ShareLikeEngine();
        shareLikeEngine.releaseShareData(this, ConstantUrl.SHARE_IMAGE_URL, "视频分享", ConstantUrl.SHARE_CLICK_GO_URL, "");
    }


    public class SuggestionAdapter extends RecyclerBaseAdapter {


        @Override
        public int getLayoutId() {
            return R.layout.item_video_suggestion;
        }

        @Override
        public Object createViewHolder(View itemView, Context context, int viewType) {
            return new SuggestionHolder(itemView, context, this);
        }

        public class SuggestionHolder extends RecyclerBaseHolder {
            TextView textViewTitle,
                    textViewTimes,
                    textViewComments;
            ImageView imageView;

            public SuggestionHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
                super(itemView, context, adapter);
                textViewTitle = itemView.findViewById(R.id.textViewTitle);
                textViewTimes = itemView.findViewById(R.id.textViewTimes);
                textViewComments = itemView.findViewById(R.id.textViewComments);
                imageView = itemView.findViewById(R.id.imageView);
            }

            @Override
            public void bindHolder(int position) {
                if (mData instanceof NewsDetailsBean.OBean.MessageBean) {
                    NewsDetailsBean.OBean.MessageBean bean = (NewsDetailsBean.OBean.MessageBean) mData;
                    textViewTitle.setText(bean.getTitle());
                    textViewTimes.setText(bean.getVisit() + "次播放");
                    textViewComments.setText(bean.getNum() + "评论");
                    Glide.with(mContext).load(ImageUrlUtils.getFinnalImageUrl(bean.getImage_input())).into(imageView);

                }
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //把播放器干掉
        JZVideoPlayer.releaseAllVideos();
    }
}
