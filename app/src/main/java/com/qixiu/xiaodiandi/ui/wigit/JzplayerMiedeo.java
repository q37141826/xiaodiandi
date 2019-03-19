package com.qixiu.xiaodiandi.ui.wigit;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;

import com.qixiu.xiaodiandi.R;

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
        super.onClick(v);
        if (v.getId() == R.id.back) {
            Intent intent = new Intent();
            intent.setAction(JZBACK);
            context.sendBroadcast(intent);//日他妈的节操播放器源码写得太死了，
            // 全屏模式下back键第一次一定是退出全屏，咱们只能重写了
        }
    }


    public interface BackClick {
        void back();
    }
}
