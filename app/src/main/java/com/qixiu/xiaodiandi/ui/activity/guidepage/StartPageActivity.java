package com.qixiu.xiaodiandi.ui.activity.guidepage;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.model.login.LoginStatus;
import com.qixiu.xiaodiandi.ui.activity.Loginactivity;
import com.qixiu.xiaodiandi.ui.activity.MainActivity;


public class StartPageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_page);
        onInitData();
    }


    protected void onInitData() {
//        try {
//            if (!Preference.getBoolean(ConstantString.IS_FIRST_LOGIN)) {
//                GuidePageActivity.start(this);
//                Preference.putBoolean(ConstantString.IS_FIRST_LOGIN, true);
//                StartPageActivity.this.finish();
//                return;
//            }
//        } catch (Exception e) {
//        }
        try {
            if (!LoginStatus.isLogin()) {
                handeler.postDelayed(new MyRunnable(1), 1000);
            } else {
                handeler.postDelayed(new MyRunnable(2), 1000);
            }
        } catch (Exception e) {
        }
    }


    //跳转的延迟线程
    Handler handeler = new Handler();

    class MyRunnable implements Runnable {
        private int type;

        public MyRunnable(int type) {
            this.type = type;
        }

        @Override
        public void run() {
            if (type == 1) {
                Loginactivity.start(StartPageActivity.this, Loginactivity.class);
            } else {
                MainActivity.start(StartPageActivity.this, MainActivity.class);
            }
            StartPageActivity.this.finish();
        }
    }
}
