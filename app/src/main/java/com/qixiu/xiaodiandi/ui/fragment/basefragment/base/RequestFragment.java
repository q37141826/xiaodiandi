package com.qixiu.xiaodiandi.ui.fragment.basefragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.qixiu.qixiu.request.OKHttpRequestModel;
import com.qixiu.qixiu.request.OKHttpUIUpdataListener;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.ToastUtil;
import com.qixiu.wigit.zprogress.ZProgressHUD;
import com.qixiu.xiaodiandi.model.login.LoginStatus;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by my on 2018/11/14.
 */

public abstract class RequestFragment extends TitleFragment implements OKHttpUIUpdataListener<BaseBean> {

    OKHttpRequestModel okHttpRequestModel = new OKHttpRequestModel(this);
    private ZProgressHUD zProgressHUD;

    public OKHttpRequestModel getOkHttpRequestModel() {
        return okHttpRequestModel;
    }

    public void post(String url, Map map, BaseBean bean) {
        if (map == null) {
            map = new HashMap();
        }
        if (LoginStatus.isLogin()) {
            map.put("uid", LoginStatus.getId());
        }
        okHttpRequestModel.okhHttpPost(url, map, bean);
//        showProgress();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void get(String url, Map map, BaseBean bean) {
        okHttpRequestModel.okHttpGet(url, map, bean);
        zProgressHUD.show();
    }

    public void showProgress() {
        zProgressHUD.show();
    }


    @Override
    protected void onInitViewNew(View view) {
        zProgressHUD = new ZProgressHUD(getContext());
    }

    @Override
    public void onSuccess(BaseBean data, int i) {
        onSuccess(data);
        zProgressHUD.dismiss();
    }

    public abstract void onSuccess(BaseBean data);

    public abstract void onError(Exception e);

    public abstract void onFailure(C_CodeBean c_codeBean, String m);


    @Override
    public void onError(Call call, Exception e, int i) {
        if (call.isCanceled()) {
            return;
        }
        onError(e);
        zProgressHUD.dismiss();
    }

    @Override
    public void onFailure(C_CodeBean c_codeBean) {
        onFailure(c_codeBean, c_codeBean.getM());
        zProgressHUD.dismiss();
        ToastUtil.toast(c_codeBean.getM());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(this.getClass().getSimpleName());
    }
}
