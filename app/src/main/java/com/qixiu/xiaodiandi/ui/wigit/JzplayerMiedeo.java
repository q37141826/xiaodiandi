package com.qixiu.xiaodiandi.ui.wigit;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.qixiu.qixiu.application.AppManager;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.ui.activity.home.PlayActivity;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class JzplayerMiedeo extends JZVideoPlayerStandard {

    Context context;
    public static final String JZBACK = "JZBACK";

    public JzplayerMiedeo(Context context) {
        super(context);
        this.context = context;
    }

    public JzplayerMiedeo(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back) {
            //日他妈的节操播放器源码写得太死了，
            // 全屏模式下back键第一次一定是退出全屏，咱们只能重写了
            AppManager.getAppManager().finishActivity(PlayActivity.class);
            JZVideoPlayer.releaseAllVideos();
            return;
        }
        super.onClick(v);

    }


    public interface BackClick {
        void back();
    }
}
