package com.qixiu.xiaodiandi.ui.activity.community;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jude.rollviewpager.RollPagerView;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.DrawableUtils;
import com.qixiu.qixiu.utils.ToastUtil;
import com.qixiu.qixiu.utils.XrecyclerViewUtil;
import com.qixiu.wigit.VerticalSwipeRefreshLayout;
import com.qixiu.wigit.show_dialog.ArshowContextUtil;
import com.qixiu.xiaodiandi.BuildConfig;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantRequest;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.constant.IntentDataKeyConstant;
import com.qixiu.xiaodiandi.model.comminity.entertainment.EntertaimentDetailsBean;
import com.qixiu.xiaodiandi.model.comminity.entertainment.EntertainmentListBean;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;
import com.qixiu.xiaodiandi.ui.fragment.home.ImageUrlAdapter;
import com.qixiu.xiaodiandi.ui.wigit.WritePop;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EntertainmentDetailsActivity extends RequestActivity implements XRecyclerView.LoadingListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener, View.OnTouchListener {
    private int replyWho = 0;
    private final int REPLY_COMMENTS = 1;
    private final int COMMENTS = 0;


    @BindView(R.id.xrecyclerView)
    XRecyclerView xrecyclerView;
    @BindView(R.id.swip_refreshlayout)
    VerticalSwipeRefreshLayout swipRefreshlayout;
    private EntertainmentListBean.OBean entertainmentBean;
    private EntertaimentDetailsBean detailsBean;
    private WritePop writePop;//输入框
    private ImageView imageViewTrance, imageViewCollect;//转发次数额图标
    private RollPagerView rollpager;
    private ImageView imageViewHead;
    private TextView textViewName, textViewContent;
    private TextView textViewShare;
    private TextView textViewTranceNum, textViewCollectionNum;
    private EntertainmentDetailsCommitsAdapter adapter;
    private EntertaimentDetailsBean.EBean clickCommentsBean;//点击了哪一条评论的回复

    VideoView videoView;
    private View headerView;

    @Override
    protected void onInitData() {
        setTitle("娱乐社区");
        DrawableUtils.setLeftDrawableResouce(mTitleView.getRightText(), getContext(), R.mipmap.start_commit);
        mTitleView.setRightText("我要发布");
        mTitleView.getRightText().setBackgroundResource(R.drawable.shape_blue_btn_bg);
        mTitleView.getRightText().getLayoutParams().height = ArshowContextUtil.dp2px(25);
        mTitleView.getRightText().setTextSize(12);
        mTitleView.getRightText().setTextColor(Color.WHITE);
        mTitleView.getRightText().setCompoundDrawablePadding(10);
        mTitleView.getRightText().setGravity(Gravity.CENTER);
        mTitleView.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyPayedProductsActivity.start(getContext(), MyPayedProductsActivity.class);
            }
        });
        XrecyclerViewUtil.setXrecyclerView(xrecyclerView, this, this, false, 1, null);
        headerView = View.inflate(getContext(), R.layout.header_entertainment_details, null);
        xrecyclerView.addHeaderView(headerView);
        findHeaderView(headerView);
        adapter = new EntertainmentDetailsCommitsAdapter();
        xrecyclerView.setAdapter(adapter);
        entertainmentBean = getIntent().getParcelableExtra(IntentDataKeyConstant.DATA);
        getData();
        adapter.setClickListenner(new RecyclerBaseAdapter.ClickListenner() {
            @Override
            public void click(View view, Object data) {
                if (data instanceof EntertaimentDetailsBean.EBean) {
                    writePop = new WritePop(getContext());
                    writePop.setSendListenner(EntertainmentDetailsActivity.this);
                    writePop.show();
                    //创建一个常量，是评论还是回复
                    replyWho = REPLY_COMMENTS;
                    clickCommentsBean = (EntertaimentDetailsBean.EBean) data;
                }
            }
        });
        swipRefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });

    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("id", entertainmentBean.getId() + "");
        post(ConstantUrl.entertainmentDetailsUrl, map, new EntertaimentDetailsBean());// TODO: 2019/2/16
    }

    private void findHeaderView(View view) {
        ImageView imageViewStartCommit = view.findViewById(R.id.imageViewStartCommit);
        imageViewStartCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writePop = new WritePop(getContext());
                writePop.setSendListenner(EntertainmentDetailsActivity.this);
                writePop.show();
                replyWho = COMMENTS;
            }
        });

        rollpager = view.findViewById(R.id.rollpager);
        imageViewHead = view.findViewById(R.id.imageViewHead);
        textViewName = view.findViewById(R.id.textViewName);
        textViewShare = view.findViewById(R.id.textViewShare);
        imageViewTrance = view.findViewById(R.id.imageViewTrance);
        textViewTranceNum = view.findViewById(R.id.textViewTranceNum);
        imageViewCollect = view.findViewById(R.id.imageViewCollect);
        textViewContent = view.findViewById(R.id.textViewContent);
        textViewCollectionNum = view.findViewById(R.id.textViewCollectionNum);

        videoView = view.findViewById(R.id.videoView);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_entertainment_details;
    }

    @Override
    public void onSuccess(BaseBean data) {
        swipRefreshlayout.setRefreshing(false);
        if (data instanceof EntertaimentDetailsBean) {
            detailsBean = (EntertaimentDetailsBean) data;
            Glide.with(getContext()).load(BuildConfig.BASE_URL + detailsBean.getO().getUser().getAvatar().replace(BuildConfig.BASE_URL, "")).into(imageViewHead);
            textViewName.setText(detailsBean.getO().getUser().getNickname());
            textViewTranceNum.setText(detailsBean.getO().getForward() + "");
            textViewContent.setText(detailsBean.getO().getContent());
            textViewCollectionNum.setText(detailsBean.getO().getCollection() + "");
            adapter.refreshData(detailsBean.getE());
            if (detailsBean.getO().getType() == 1) {
                videoView.setVisibility(View.GONE);
                ImageUrlAdapter adapter = new ImageUrlAdapter(rollpager);
                rollpager.setAdapter(adapter);
                adapter.refreshData(detailsBean.getO().getImgList());
            }else {
                rollpager.setVisibility(View.GONE);
                videoView.setVisibility(View.VISIBLE);
                initVideoView(headerView);
            }
        }
        if (data.getUrl().equals(ConstantUrl.leaveMessageUrl)) {
            ToastUtil.toast("回复成功");
            writePop.dismiss();
            getData();
        }
    }

    @Override
    public void onError(Exception e) {
        swipRefreshlayout.setRefreshing(false);
    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {
        swipRefreshlayout.setRefreshing(false);
    }

    @Override
    protected void onInitViewNew() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == writePop.getViewId()) {
            if (TextUtils.isEmpty(writePop.getText())) {
                return;
            }
            if (replyWho == COMMENTS) {
                ConstantRequest.leaveMessage(okHttpRequestModel, ConstantUrl.leaveMessageUrl, 4 + "", detailsBean.getO().getId() + "", writePop.getText().toString());
            } else {
                ConstantRequest.leaveMessage(okHttpRequestModel, ConstantUrl.leaveMessageUrl, 4 + "",
                        detailsBean.getO().getId() + "", writePop.getText().toString(), clickCommentsBean.getId() + "", clickCommentsBean.getUid() + "");
            }
        }
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

    }


    /**
     * 初始化videoview播放
     */
    public void initVideoView(View view) {
        //初始化进度条
        ProgressBar progressBar = view.findViewById(R.id.progress);
        //初始化VideoView
        VideoView videoView = view.findViewById(R.id.videoView);
        //初始化videoview控制条
        MediaController mediaController = new MediaController(getContext());
        //设置videoview的控制条
        videoView.setMediaController(mediaController);
        //设置显示控制条
        mediaController.show(0);
        //设置播放完成以后监听
        videoView.setOnCompletionListener(this);
        //设置发生错误监听，如果不设置videoview会向用户提示发生错误
        videoView.setOnErrorListener(this);
        //设置在视频文件在加载完毕以后的回调函数
        videoView.setOnPreparedListener(this);
        //设置videoView的点击监听
        videoView.setOnTouchListener(this);
        //设置网络视频路径
        Uri uri = Uri.parse(detailsBean.getO().getImgList().get(0).getImg());
        videoView.setVideoURI(uri);
    }


    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

    }

    /**
     * 视频播放发生错误时调用的回调函数
     */
    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                Log.e("text", "发生未知错误");

                break;
            case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
                Log.e("text", "媒体服务器死机");
                break;
            default:
                Log.e("text", "onError+" + what);
                break;
        }
        switch (extra) {
            case MediaPlayer.MEDIA_ERROR_IO:
                //io读写错误
                Log.e("text", "文件或网络相关的IO操作错误");
                break;
            case MediaPlayer.MEDIA_ERROR_MALFORMED:
                //文件格式不支持
                Log.e("text", "比特流编码标准或文件不符合相关规范");
                break;
            case MediaPlayer.MEDIA_ERROR_TIMED_OUT:
                //一些操作需要太长时间来完成,通常超过3 - 5秒。
                Log.e("text", "操作超时");
                break;
            case MediaPlayer.MEDIA_ERROR_UNSUPPORTED:
                //比特流编码标准或文件符合相关规范,但媒体框架不支持该功能
                Log.e("text", "比特流编码标准或文件符合相关规范,但媒体框架不支持该功能");
                break;
            default:
                Log.e("text", "onError+" + extra);
                break;
        }
        //如果未指定回调函数， 或回调函数返回假，VideoView 会通知用户发生了错误。这点需要特别注意</span>
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
