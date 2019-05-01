package com.qixiu.xiaodiandi.ui.activity.baseactivity;

import android.database.ContentObserver;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.qixiu.qixiu.R;
import com.qixiu.qixiu.titleview.TitleView;
import com.qixiu.qixiu.utils.StatusBarUtils;
import com.qixiu.qixiu.utils.ToastUtil;
import com.qixiu.xiaodiandi.utils.SizeUtils;


/**
 * Created by Administrator on 2017/6/14 0014.
 */

public abstract class TitleActivity extends BaseActivity implements View.OnClickListener, ViewTreeObserver.OnGlobalLayoutListener {
    //    FrameLayout content;
    private static final String TAG = "BaseActivityForAuto";
    private boolean mLayoutComplete = false;
    public boolean navigationBarIsUp = false;
    View fatherView;
    protected TitleView mTitleView;
    public int navigationState;
    String mDeviceInfo;//屏幕适配需要知道各个厂商的的名称

    @Override
    protected void onInitData() {

    }

    /**
     * 在这里添加公共的标题
     */
    @Override
    protected void onInitView() {
        mTitleView = new TitleView(this);
        setBackRes();
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.vg_title);
        if (viewGroup != null) {
            viewGroup.addView(mTitleView.getView());
            adustTitle();
        } else {
            ToastUtil.toast(R.string.main_notfindTitle);
        }
        mTitleView.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backClick();
            }
        });
        onInitViewNew();
        refreshWindowSize();
        initDeviceInfo();//获取手机厂商虚拟键标识
        registerNavigationBarObserver();//虚拟键状态判断，记得在resume方法先走一次onChange回调
    }


    public void backClick() {
        finish();
    }

    private void initDeviceInfo() {
        String brand = Build.BRAND;
        if (brand.equalsIgnoreCase("HUAWEI")) {
            mDeviceInfo = "navigationbar_is_min";
        } else if (brand.equalsIgnoreCase("XIAOMI")) {
            mDeviceInfo = "force_fsg_nav_bar";
        } else if (brand.equalsIgnoreCase("VIVO")) {
            mDeviceInfo = "navigation_gesture_on";
        } else if (brand.equalsIgnoreCase("OPPO")) {
            mDeviceInfo = "navigation_gesture_on";
        } else {
            mDeviceInfo = "navigationbar_is_min";
        }
    }

    private void registerNavigationBarObserver() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            getContentResolver().registerContentObserver(Settings.System.getUriFor
                    (mDeviceInfo), true, mNavigationBarObserver);
        } else {
            getContentResolver().registerContentObserver(Settings.Global.getUriFor
                    (mDeviceInfo), true, mNavigationBarObserver);
        }
    }


    private void unRegisterNavigationBarObserver() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            getContentResolver().unregisterContentObserver(mNavigationBarObserver);
        } else {
            getContentResolver().unregisterContentObserver(mNavigationBarObserver);
        }
    }

    private ContentObserver mNavigationBarObserver = new ContentObserver(new Handler()) {
        @Override
        public void onChange(boolean selfChange) {
            int navigationBarIsMin = 0;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                navigationBarIsMin = Settings.System.getInt(getContentResolver(),
                        mDeviceInfo, 0);
            } else {
                navigationBarIsMin = Settings.Global.getInt(getContentResolver(),
                        mDeviceInfo, 0);
            }
            Log.e(TAG, "onChange: " + navigationBarIsMin);
            if (navigationBarIsMin == 1) {
                //导航键隐藏了
            } else {
                //导航键显示了
            }
            setNavigationState(navigationBarIsMin);
            onNavigationBarStatusChanged(navigationBarIsMin);
        }
    };

    public void setNavigationState(int i) {
        navigationState = i;
        navigationBarIsUp = i == 0;
        SizeUtils.setIsBarUp(navigationBarIsUp);
    }


    @Override
    protected int getLayoutResource() {
        return 0;
    }

    public void setBackRes() {
        mTitleView.getView().setBackgroundResource(R.drawable.title_under_line);
    }


    /*
     * @param view 传入被虚拟键挤压会动的view
     * */
    public void openNavagationBar(View view) {
        fatherView = view;
    }

    @Override
    protected void onResume() {
        super.onResume();


        mNavigationBarObserver.onChange(true);//注意界面启动后要刷新一下bar的状态，否则界面不变，监听不会主动走
    }

    public void adustTitle() {
        setLayout(StatusBarUtils.getStatusBarHeight(getContext()));
    }

    protected void setLayout(int height) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mTitleView.getView().getLayoutParams();
        params.topMargin = height;
    }


    protected abstract void onInitViewNew();

    @Override
    protected void onStart() {
        super.onStart();
        StatusBarUtils.adustStateBar(this, true);//是否把statebar字体变黑
    }

    public void setTitle(String title) {
        if (mTitleView != null) {
            mTitleView.setTitle(title);
        }
    }

    public void setTitle(int res) {
        if (mTitleView != null) {
            mTitleView.setTitle(res);
        }
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void onGlobalLayout() {
        refreshWindowSize();
    }

    private void refreshWindowSize() {
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        DisplayMetrics mtr = new DisplayMetrics();
        defaultDisplay.getMetrics(mtr);
        windowHeight = mtr.heightPixels;
        SizeUtils.setWindowWith(windowWith);
        SizeUtils.setWindowHeight(windowHeight);
    }

    public void onNavigationBarStatusChanged(int navigationBarIsMin) {
        refreshWindowSize();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterNavigationBarObserver();
        try {
            fatherView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        } catch (Exception e) {
        }
    }


}
