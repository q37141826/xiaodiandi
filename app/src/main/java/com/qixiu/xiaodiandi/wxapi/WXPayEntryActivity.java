package com.qixiu.xiaodiandi.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wxpay.PlatformConfigConstant;


public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    private IWXAPI api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(android.R.layout.select_dialog_item);
        api = WXAPIFactory.createWXAPI(this, PlatformConfigConstant.WEIXIN_APP_ID);
        api.handleIntent(getIntent(), this);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {


    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            Intent intent = new Intent(PlatformConfigConstant.PAY_ACTION);
            if (resp.errCode == Integer.valueOf(PlatformConfigConstant.WXPAY_SUCCESS)) {

                intent.putExtra(PlatformConfigConstant.WXPAY_SUCCESS_KEY, PlatformConfigConstant.WXPAY_SUCCESS);

                //成功应该处理的事情
                startNoticeMarket(resp);
            } else if (resp.errCode == Integer.valueOf(PlatformConfigConstant.WXPAY_FAILURE)) {
                intent.putExtra(PlatformConfigConstant.WXPAY_FAILURE_KEY, PlatformConfigConstant.WXPAY_FAILURE);
            } else if (resp.errCode == Integer.valueOf(PlatformConfigConstant.WXPAY_CANCEL)) {
                intent.putExtra(PlatformConfigConstant.WXPAY_CANCEL_KEY, PlatformConfigConstant.WXPAY_CANCEL);
            }
            sendOrderedBroadcast(intent, null);
        }
        finish();
    }

    private void startNoticeMarket(BaseResp resp) {
        Bundle bundle = new Bundle();
        //type是本项目中车费类型

    }

}