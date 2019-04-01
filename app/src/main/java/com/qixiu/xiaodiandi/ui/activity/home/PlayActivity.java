package com.qixiu.xiaodiandi.ui.activity.home;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import com.qixiu.qixiu.service.FileDownloadService;
import com.qixiu.qixiu.utils.PictureCut;
import com.qixiu.wigit.zprogress.ZProgressHUD;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.IntentDataKeyConstant;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.TitleActivity;
import com.qixiu.xiaodiandi.ui.wigit.JzplayerMiedeo;

import org.song.videoplayer.media.BaseMedia;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class PlayActivity extends TitleActivity {

    @BindView(R.id.jcplayer)
    JzplayerMiedeo jcplayer;
    @BindView(R.id.videoView)
    VideoView videoView;
    //    @BindView(R.id.qs_videoview)
//    DemoQSVideoView qsVideoview;
    private String path;
    private String thumb;
    Class<? extends BaseMedia> decodeMedia;
    ZProgressHUD zProgressHUD;
    private SensorManager sensorManager;
    private JZVideoPlayer.JZAutoFullscreenListener sensorEventListener;

    @Override
    protected void onInitData() {
        zProgressHUD = new ZProgressHUD(this);
        mTitleView.getView().setVisibility(View.GONE);
        path = getIntent().getStringExtra(IntentDataKeyConstant.DATA);
        thumb = getIntent().getStringExtra("thumb");
        if (path.startsWith("http")) {
            String filepath = PictureCut.getPath(PictureCut.getCode(path), FileDownloadService.downloadPath);
            if (TextUtils.isEmpty(filepath)) {
                FileDownloadService.DownLoadDetailsBean bean = new FileDownloadService.DownLoadDetailsBean();
                bean.setName(PictureCut.getCode(path));
                bean.setUrl(path);
                Intent intent = new Intent(getContext(), FileDownloadService.class);
                intent.putExtra(FileDownloadService.DATA, bean);
                startService(intent);
                initJCView(path);
            } else {
                initJCView(filepath);
            }
        } else {
            initJCView(path);
        }
//        initVideoView();
//        initQsVideoView();
    }

//    private void initQsVideoView() {
//        qsVideoview.setUp(path, QSVideoView.TAG);
//        qsVideoview.enterWindowFullscreen();
//    }
//
//
//    private void play(String url, Class<? extends BaseMedia> decodeMedia) {
//        qsVideoview.release();
//        qsVideoview.setDecodeMedia(decodeMedia);
//        qsVideoview.setUp(url, "这是一一一一一一一一一个标题");
//        //demoVideoView.seekTo(12000);
//        qsVideoview.openCache();//缓存配置见最后
//        qsVideoview.play();
////        this.url = url;
//        this.decodeMedia = decodeMedia;
//    }


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_play;
    }

    @Override
    protected void onInitViewNew() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void initJCView(String path) {
        //下面是手动横竖屏设置--------
//        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;  //横向
//        JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;  //纵向
        //下面是重力感应横竖屏设置  -------
          /*
         重力感应，横竖屏监听  注意清单中的activity一定要定死
         android:configChanges="orientation|screenSize|keyboardHidden"
            android:screenOrientation="portrait"
        */
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorEventListener = new JZVideoPlayer.JZAutoFullscreenListener();
        if (jcplayer == null) {
            return;
        }
        jcplayer.startButton.setVisibility(View.GONE);
        jcplayer.setVisibility(View.VISIBLE);
//        jcplayer.setUp(detailsBean.getO().getProduct().getVideo(), JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL);
        jcplayer.setUp(path, JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL);
//        Glide.with(getContext()).load(thumb).into(jcplayer.thumbImageView);
        jcplayer.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        jcplayer.battery_level.setVisibility(View.GONE);
        jcplayer.startVideo();
        jcplayer.startWindowFullscreen();
//        jcplayer.backButton.setOnClickListener(finishClick);
//        jcplayer.tinyBackImageView.setOnClickListener(finishClick);

    }

    View.OnClickListener finishClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            PlayActivity.this.finish();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(sensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onBackPressed() {
//        if (JZVideoPlayer.backPress()) {
//            return;
//        }
//        super.onBackPressed();
        finish();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
        sensorManager.unregisterListener(sensorEventListener);
    }


    private void initVideoView() {

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlaybackVideo();
            }
        });
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                stopPlaybackVideo();
                return true;
            }
        });

        try {
            Uri uri = Uri.parse(path);
            videoView.setVideoURI(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
        videoView.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopPlaybackVideo();
    }

    private void stopPlaybackVideo() {
        try {
            videoView.stopPlayback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
