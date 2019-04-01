package com.qixiu.xiaodiandi.ui.activity.community.entertainment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.ArshowDialogUtils;
import com.qixiu.qixiu.utils.DownLoadFileUtils;
import com.qixiu.qixiu.utils.ToastUtil;
import com.qixiu.qixiu.utils.XrecyclerViewUtil;
import com.qixiu.wigit.VerticalSwipeRefreshLayout;
import com.qixiu.wigit.show_dialog.ArshowContextUtil;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantRequest;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.constant.IntentDataKeyConstant;
import com.qixiu.xiaodiandi.engine.ShareLikeEngine;
import com.qixiu.xiaodiandi.model.IdInterfer;
import com.qixiu.xiaodiandi.model.comminity.entertainment.EntertaimentDetailsBean;
import com.qixiu.xiaodiandi.model.login.LoginStatus;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;
import com.qixiu.xiaodiandi.ui.activity.community.upload.EntertainmentPhotoUploadActivity;
import com.qixiu.xiaodiandi.ui.activity.home.PlayActivity;
import com.qixiu.xiaodiandi.ui.fragment.home.ImageUrlAdapter;
import com.qixiu.xiaodiandi.ui.wigit.WritePop;
import com.qixiu.xiaodiandi.utils.ImageUrlUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class EntertainmentDetailsActivity extends RequestActivity implements XRecyclerView.LoadingListener {

    private int replyWho = 0;
    private final int REPLY_COMMENTS = 1;
    private final int COMMENTS = 0;
    String permissions[] = {Manifest.permission.RECORD_AUDIO};

    @BindView(R.id.xrecyclerView)
    XRecyclerView xrecyclerView;
    @BindView(R.id.swip_refreshlayout)
    VerticalSwipeRefreshLayout swipRefreshlayout;
    private IdInterfer entertainmentBean;
    private EntertaimentDetailsBean detailsBean;
    private WritePop writePop;//输入框
    private ImageView imageViewTrance, imageViewCollect;//转发次数额图标
    private RollPagerView rollpager;
    private ImageView imageViewHead;
    private TextView textViewName, textViewContent;
    private TextView textViewShare, textViewDetele;
    private TextView textViewTranceNum, textViewCollectionNum;
    private EntertainmentDetailsCommitsAdapter adapter;
    private EntertaimentDetailsBean.EBean clickCommentsBean;//点击了哪一条评论的回复

    JZVideoPlayerStandard jcplayer;
    private View headerView;


    RelativeLayout relativeLayoutVideo;
    ImageView imageViewVideoThumb;
    private ImageView player_icon;

    @Override
    protected void onInitData() {
        setTitle("娱乐社区");
//        DrawableUtils.setLeftDrawableResouce(mTitleView.getRightText(), getContext(), R.mipmap.start_commit);
        mTitleView.setRightText("我要发布");
        mTitleView.getRightText().setBackgroundResource(R.drawable.shape_blue_btn_bg);
        mTitleView.getRightText().getLayoutParams().height = ArshowContextUtil.dp2px(25);
        mTitleView.getRightText().setTextSize(12);
        mTitleView.getRightText().setCompoundDrawablePadding(ArshowContextUtil.dp2px(3));
        mTitleView.getRightText().setTextColor(Color.WHITE);
        mTitleView.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoPicker();
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

    private void shoPicker() {
        EntertainmentPhotoUploadActivity.start(getContext(), EntertainmentPhotoUploadActivity.class);
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
        rollpager.setHintView(new ColorPointHintView(getActivity(), getResources().getColor(R.color.theme_color), getResources().getColor(R.color.alpha_black_50)));
        imageViewHead = view.findViewById(R.id.imageViewHead);
        textViewName = view.findViewById(R.id.textViewName);
        textViewShare = view.findViewById(R.id.textViewShare);
        imageViewTrance = view.findViewById(R.id.imageViewTrance);
        textViewTranceNum = view.findViewById(R.id.textViewTranceNum);
        imageViewCollect = view.findViewById(R.id.imageViewCollect);
        textViewContent = view.findViewById(R.id.textViewContent);
        textViewDetele = view.findViewById(R.id.textViewDetele);
        textViewCollectionNum = view.findViewById(R.id.textViewCollectionNum);
        jcplayer = view.findViewById(R.id.jcplayer);
        relativeLayoutVideo = view.findViewById(R.id.relativeLayoutVideo);
        player_icon = view.findViewById(R.id.player_icon);
        imageViewVideoThumb = view.findViewById(R.id.imageViewVideoThumb);
        imageViewVideoThumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoPlayVideo();
            }
        });
        textViewDetele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteThis();
            }
        });
    }

    private void deleteThis() {
        ArshowDialogUtils.showDialog(getContext(), "是否确认删除", new ArshowDialogUtils.ArshowDialogListener() {
            @Override
            public void onClickPositive(DialogInterface dialogInterface, int which) {
                Map<String, String> map = new HashMap<>();
                map.put("rid", detailsBean.getO().getId() + "");
                post(ConstantUrl.deletePublishUrl, map, new BaseBean());
            }

            @Override
            public void onClickNegative(DialogInterface dialogInterface, int which) {

            }
        });

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
            Glide.with(getContext()).load(ImageUrlUtils.getFinnalImageUrl(detailsBean.getO().getUser().getAvatar())).error(R.mipmap.mine_head).into(imageViewHead);
            textViewName.setText(detailsBean.getO().getUser().getNickname());
            textViewTranceNum.setText(detailsBean.getO().getForward() + "");
            textViewContent.setText(detailsBean.getO().getContent());
            textViewCollectionNum.setText(detailsBean.getO().getCollection() + "");
            adapter.refreshData(detailsBean.getE());
            //如果已经收藏，把图标变更一下
            imageViewCollect.setImageResource(detailsBean.getO().getCollectionor() == 0 ? R.mipmap.entertainment_collection : R.mipmap.aready_collect);
            if ((detailsBean.getO().getUid() + "").equals(LoginStatus.getId())) {//用ID判断是否显示删除
                textViewShare.setVisibility(View.GONE);
                textViewDetele.setVisibility(View.VISIBLE);
            } else {
                textViewShare.setVisibility(View.GONE);
                textViewDetele.setVisibility(View.GONE);
            }

            if (detailsBean.getO().getType() == 1) {
                jcplayer.setVisibility(View.GONE);
                //如果只有一张图，那么就不用轮播了，直接定死
                if (detailsBean.getO().getImg().size() == 1) {
                    relativeLayoutVideo.setVisibility(View.VISIBLE);
                    rollpager.setVisibility(View.GONE);
                    Glide.with(getContext()).load(detailsBean.getO().getImg().get(0)).into(imageViewVideoThumb);
                    player_icon.setVisibility(View.GONE);
                    imageViewVideoThumb.setEnabled(false);//不允许点击
                    return;
                }
                relativeLayoutVideo.setVisibility(View.GONE);
                ImageUrlAdapter adapter = new ImageUrlAdapter(rollpager);
                rollpager.setAdapter(adapter);
                List<String> images = new ArrayList<>();
                for (int i = 0; i < detailsBean.getO().getImg().size(); i++) {
                    images.add(detailsBean.getO().getImg().get(i));
                }
                adapter.refreshData(images);
            } else {
                relativeLayoutVideo.setVisibility(View.VISIBLE);
                rollpager.setVisibility(View.GONE);
                Glide.with(getContext()).load(detailsBean.getO().getImg().get(0)).into(imageViewVideoThumb);
                jcplayer.setVisibility(View.GONE);//todo 不考虑屏幕内播放呢
//                initJCView(headerView);
            }
        }
        if (data.getUrl().equals(ConstantUrl.leaveMessageUrl)) {
            ToastUtil.toast("回复成功");
            writePop.dismiss();
            getData();
        }
        if (data.getUrl().equals(ConstantUrl.forwardCollectionUrl)) {
            getData();
        }
        if (data.getUrl().equals(ConstantUrl.deletePublishUrl)) {
            ToastUtil.toast(data.getM());
            finish();
        }
    }


    @Override
    public void onError(Exception e) {
        swipRefreshlayout.setRefreshing(false);
    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {
        swipRefreshlayout.setRefreshing(false);
        if (c_codeBean.getM().contains("不存在")) {
            finish();
        }
    }

    @Override
    protected void onInitViewNew() {

    }

    private void gotoPlayVideo() {
        mZProgressHUD.show();
        if (detailsBean.getO().getImg().size() == 2) {
            DownLoadFileUtils.InitFile.callFile(detailsBean.getO().getImg().get(1), new DownLoadFileUtils.FileCallBack() {
                @Override
                public void callBack(String path) {
//                                initVideoView(path);
                    Intent intent = new Intent(getContext(), PlayActivity.class);
                    intent.putExtra(IntentDataKeyConstant.DATA, path);
                    intent.putExtra("thumb", detailsBean.getO().getImg().get(0));
                    startActivity(intent);
                    mZProgressHUD.dismiss();
                }
            });
        }

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
        xrecyclerView.loadMoreComplete();
    }


    private void initJCView(View view) {
        jcplayer = view.findViewById(R.id.jcplayer);
        if (detailsBean.getO().getImg().size() == 2) {
            jcplayer.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(getContext()).load(detailsBean.getO().getImg().get(0)).into(jcplayer.thumbImageView);
            jcplayer.setUp(detailsBean.getO().getImg().get(1), JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL);
        } else {
            jcplayer.setUp(detailsBean.getO().getImg().get(0), JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL);
        }
        jcplayer.battery_level.setVisibility(View.GONE);

    }


    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }


    //分享
    public void shareEnterTainment(View view) {
        ShareLikeEngine shareLikeEngine = new ShareLikeEngine();
        shareLikeEngine.releaseShareData(this, ConstantUrl.SHARE_IMAGE_URL, "测试一下", ConstantUrl.SHARE_CLICK_GO_URL, "");
        shareLikeEngine.setShareResultListenner(new ShareLikeEngine.ShareResultListenner() {
            @Override
            public void shareSuccess() {
                ConstantRequest.collectionOrTrans(getOkHttpRequestModel(), ConstantUrl.forwardCollectionUrl, detailsBean.getO().getId() + "", 2 + "", "");
            }

            @Override
            public void shareFailure() {

            }

            @Override
            public void shareCancle() {

            }
        });
    }

    //添加收藏
    public void addToCollect(View view) {
        int state = 0;
        if (detailsBean.getO().getCollectionor() == 0) {
            state = 1;
        }
        ConstantRequest.collectionOrTrans(getOkHttpRequestModel(), ConstantUrl.forwardCollectionUrl, detailsBean.getO().getId() + "", 1 + "", state + "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JZVideoPlayer.releaseAllVideos();
    }
}
