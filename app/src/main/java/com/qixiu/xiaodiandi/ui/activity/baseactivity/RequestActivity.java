package com.qixiu.xiaodiandi.ui.activity.baseactivity;

import com.qixiu.qixiu.request.OKHttpRequestModel;
import com.qixiu.qixiu.request.OKHttpUIUpdataListener;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.CommonUtils;
import com.qixiu.qixiu.utils.ToastUtil;
import com.qixiu.wigit.zprogress.ZProgressHUD;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.model.login.LoginStatus;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by my on 2018/8/23.
 */

public abstract class RequestActivity extends TitleActivity implements OKHttpUIUpdataListener<BaseBean> {

    public OKHttpRequestModel okHttpRequestModel = new OKHttpRequestModel(this);
    public ZProgressHUD mZProgressHUD;


    public void post(String url, Map map, BaseBean bean) {
        if (map == null) {
            map = new HashMap();
        }
        if (LoginStatus.isLogin()) {
          CommonUtils.putDataIntoMap(map,"uid", LoginStatus.getId());
        }
        okHttpRequestModel.okhHttpPost(url, map, bean);
        mZProgressHUD.show();

    }


    public void get(String url, Map map, BaseBean bean) {
        okHttpRequestModel.okHttpGet(url, map, bean);
        mZProgressHUD.show();
    }

    @Override
    protected void onInitView() {
        mZProgressHUD = new ZProgressHUD(getContext());
        super.onInitView();
    }

    @Override
    public void onSuccess(BaseBean data, int i) {
        onSuccess(data);
        mZProgressHUD.dismiss();
    }

    @Override
    public void onError(Call call, Exception e, int i) {
        onError(e);
        mZProgressHUD.dismissWithFailure(getResources().getString(R.string.link_error));
    }

    @Override
    public void onFailure(C_CodeBean c_codeBean) {
        ToastUtil.toast(c_codeBean.getM());
        onFailure(c_codeBean, c_codeBean.getM());
        mZProgressHUD.dismiss();
    }


    public abstract void onSuccess(BaseBean data);

    public abstract void onError(Exception e);

    public abstract void onFailure(C_CodeBean c_codeBean, String m);

    public OKHttpRequestModel getOkHttpRequestModel() {
        return okHttpRequestModel;
    }
}
