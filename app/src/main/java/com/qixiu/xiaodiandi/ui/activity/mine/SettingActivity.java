package com.qixiu.xiaodiandi.ui.activity.mine;

import android.os.Bundle;
import android.view.View;

import com.qixiu.qixiu.application.AppManager;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.CleanDataUtils;
import com.qixiu.qixiu.utils.Preference;
import com.qixiu.qixiu.utils.ToastUtil;
import com.qixiu.wigit.GotoView;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.ui.activity.Loginactivity;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends RequestActivity {


    @BindView(R.id.gotoview_clean)
    GotoView gotoviewClean;

    @Override
    protected void onInitData() {
        setTitle("设置");
        //获取缓存信息
        try {
            String totalCacheSize = CleanDataUtils.getTotalCacheSize(getContext());
            gotoviewClean.setSecondText(totalCacheSize);
        } catch (Exception e) {
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_setting;
    }

    @Override
    public void onSuccess(BaseBean data) {
        if (data.getUrl().equals(ConstantUrl.outLoginUrl)) {
            ToastUtil.toast(data.getM());
            Preference.clearAllFlag();
            AppManager.getAppManager().finishAllActivity();
            Loginactivity.start(getContext(), Loginactivity.class);
        }
    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {

    }

    @Override
    protected void onInitViewNew() {

    }

    @Override
    public void onClick(View v) {

    }


    public void outLogin(View view) {
        post(ConstantUrl.outLoginUrl, null, new BaseBean());
    }


    //清除缓存
    public void clean(View view) {
        try {
            CleanDataUtils.clearAllCache(getContext());
            String size = CleanDataUtils.getTotalCacheSize(getContext());
            gotoviewClean.setSecondText(size);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    //更换手机号
    public void gotoChangePhone(View view) {
        ChangePhoneStepOneActivity.start(getContext(), ChangePhoneStepOneActivity.class);
    }
}
