package com.qixiu.xiaodiandi.ui.activity.baseactivity;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qixiu.qixiu.application.AppManager;
import com.qixiu.qixiu.application.NetStatusCheck;
import com.qixiu.xiaodiandi.constant.IntentDataKeyConstant;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/11 0011.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    public String verify_id = "";
    private FragmentTransaction mFragmentTransaction;
    protected FragmentManager mSupportFragmentManager;
    protected String[] photoPermission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE};

    public int windowHeight, windowWith;
    private Toolbar toolbar;
    private TextView tvTitle;
    BroadcastReceiver receiver;
    public String deviceId;

    public Context getContext() {
        return this;
    }

    public Activity getActivity() {
        return this;
    }


    //启动方法
    public static void start(Context context, Class activity) {
        Intent intent = new Intent(context, activity);
        context.startActivity(intent);
    }

    //启动方法
    public static void start(Context context, Class activity, ArrayList<? extends Parcelable> datas) {
        Intent intent = new Intent(context, activity);
        intent.putParcelableArrayListExtra(IntentDataKeyConstant.DATA, datas);
        context.startActivity(intent);
    }

    //启动方法
    public static void start(Context context, Class activity, Parcelable data) {
        Intent intent = new Intent(context, activity);
        intent.putExtra(IntentDataKeyConstant.DATA, data);
        context.startActivity(intent);
    }

    //启动方法
    public static void start(Context context, Class activity, String data) {
        Intent intent = new Intent(context, activity);
        intent.putExtra(IntentDataKeyConstant.DATA, data);
        context.startActivity(intent);
    }

    //启动方法
    public static void start(Context context, Class activity, int data) {
        Intent intent = new Intent(context, activity);
        intent.putExtra(IntentDataKeyConstant.DATA, data);
        context.startActivity(intent);
    }

    //检查权限
    public boolean hasPermission(String... permission) {
        for (String permissiom : permission) {
            if (ActivityCompat.checkSelfPermission(this, permissiom) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    //添加权限
    public void hasRequse(int code, String... permission) {
        ActivityCompat.requestPermissions(this, permission, 1);
    }

    /**
     * 切换Fragment，与上面的方法不同的是判断是否需要添加堆栈中
     *
     * @param fragment
     * @param resId
     * @param arguments
     * @param isBack
     */
    public void switchFragment(Fragment thisFragment, Fragment fragment, int resId,
                               Bundle arguments, boolean isBack) {
        switchFragment(thisFragment, fragment, resId, arguments, isBack, false);
    }

    /**
     * @param fragment  要替换的Fragment
     * @param arguments 需要携带的参数
     * @param isBack    是否添加到回退栈
     * @param isHide    是否隐藏
     */
    public void switchFragment(Fragment thisFragment, Fragment fragment, int resId,
                               Bundle arguments, boolean isBack, boolean isHide) {
        switchFragment(thisFragment, fragment, resId, arguments, isBack, null, isHide);
    }

    /**
     * 切换Fragment
     *
     * @param fragment
     * @param resId
     * @param arguments
     * @param isBack
     * @param tag       Fragment的标签
     * @param isHide
     */
    public void switchFragment(Fragment thisFragment, Fragment fragment, int resId,
                               Bundle arguments, boolean isBack, String tag, boolean isHide) {
        if (arguments != null) {
            fragment.setArguments(arguments);
        }
        mFragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);// 设置动画效果
        mFragmentTransaction.replace(resId, fragment);
        if (isBack)
            mFragmentTransaction.addToBackStack(tag);
        if (isHide)
            mFragmentTransaction.hide(thisFragment);
        mFragmentTransaction.commit();
    }

    /**
     * 切换Fragment
     *
     * @param fragment
     * @param resId
     * @param arguments
     */
    public void switchFragment(Fragment fragment, int resId, Bundle arguments) {
        switchFragment(null, fragment, resId, arguments, false, null, false);
    }

    /**
     * 切换Fragment
     *
     * @param fragment
     * @param resId
     * @param arguments
     * @param tag
     */
    public void switchFragment(Fragment fragment, int resId, Bundle arguments, String tag) {
        switchFragment(null, fragment, resId, arguments, false, tag, false);
    }

    /**
     * 切换Fragment
     *
     * @param fragment
     * @param resId
     */
    public void switchFragment(Fragment fragment, int resId) {
        switchFragment(null, fragment, resId, null, false, null, false);
    }

    /**
     * 切换Fragment
     *
     * @param fragment
     * @param resId
     * @param tag
     */
    public void switchFragment(Fragment fragment, int resId, String tag) {
        switchFragment(null, fragment, resId, null, false, tag, false);
    }

    /**
     * 添加Fragment
     *
     * @param layoutId
     * @param fragment
     * @param tag      Fragment的标签
     */
    protected void addFragment(int layoutId, Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction().add(layoutId, fragment, tag).commit();
    }

    protected void addFragment(int layoutId, Fragment fragment, String tag, Bundle bundle) {
        if (fragment != null) {

            fragment.setArguments(bundle);

        }
        addFragment(layoutId, fragment, tag);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        AppManager.getAppManager().addActivity(this);

        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        DisplayMetrics mtr = new DisplayMetrics();
        defaultDisplay.getMetrics(mtr);
        mContext = this;
        ButterKnife.bind(this);
        windowHeight = mtr.heightPixels;
        windowWith = mtr.widthPixels;
        Log.e("windowheight:windowWith", windowHeight + "---" + windowWith);
        Log.e("where", this.getClass().getSimpleName());
        mSupportFragmentManager = getSupportFragmentManager();
        setScreenOrentation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        onInitView();
        onInitData();
        try {
            if (hasPermission(photoPermission)) {
                getdevice();
            } else {
                hasRequse(1, photoPermission);
            }
            Log.e("deviceId", deviceId);
        } catch (Exception e) {
        }
        setStatebar(Color.TRANSPARENT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("com.qixiu.example.broadcast.normal");
        intentFilter.setPriority(600);
        registerReceiver(receiver, intentFilter);
        NetStatusCheck.checkNetWork(getContext());//检查网络状况
//        ShortcutBadger.applyCount(this, 0);//角标
    }

    @Override
    protected void onPause() {
        super.onPause();
//        unregisterReceiver(receiver);
    }

    protected abstract void onInitData();

    protected abstract void onInitView();


    protected abstract int getLayoutResource();

    TextView codeText;
    public Timer timer;
    private String originalText;

    public void startTimeCountDown(TextView codeText) {
        codeText.setEnabled(false);
        this.codeText = codeText;
//        String token = MD5Util.getToken(Constant.SendCodeTag);
        //3表示是验证码登录
        if (!TextUtils.isEmpty(codeText.getText())) {
            this.originalText = codeText.getText().toString();
        }
        timer = new Timer();
        timer.start();
    }

    public void onNavigateUpClicked() {
        onBackPressed();
    }


    public Toolbar getToolBar() {
        return toolbar;
    }

    public void getdevice() {
        TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        deviceId = tm.getDeviceId();
    }


    private class Timer extends CountDownTimer {

        public Timer() {

            super(60 * 1000, 1000);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            Log.i("test", "??");
            codeText.setText(millisUntilFinished / 1000 + "秒后重发");
            codeText.setEnabled(false);
        }

        @Override
        public void onFinish() {
            if (!TextUtils.isEmpty(originalText)) {
                codeText.setText(originalText);
            } else {
                codeText.setText("发送验证码");
            }

            codeText.setEnabled(true);

        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        try {
            codeText.setText("发送验证码");
            codeText.setEnabled(true);
        } catch (Exception e) {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Glide.get(this).clearMemory();
        AppManager.getAppManager().removeActivity(this);
    }


    public void setStatebar(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);// 修改状态栏的颜色，一般设为透明
        }
    }


    //提示，参数在ActivityInfo里面
    public void setScreenOrentation(int screenOrentation) {
        setRequestedOrientation(screenOrentation);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (hasPermission(photoPermission)) {
            getdevice();
        }
    }
}
