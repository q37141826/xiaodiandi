package com.qixiu.xiaodiandi.ui.activity.mine.mypoints;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.CommonUtils;
import com.qixiu.qixiu.utils.ToastUtil;
import com.qixiu.qixiu.utils.bankutil.BankCheckUtil;
import com.qixiu.wigit.GotoView;
import com.qixiu.wigit.myedittext.MyEditTextView;
import com.qixiu.wigit.myedittext.TextChangeListenner;
import com.qixiu.wigit.picker.MyPopOneListPicker;
import com.qixiu.wigit.picker.SelectedDataBean;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.constant.IntentDataKeyConstant;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GetCashActivity extends RequestActivity {


    @BindView(R.id.myedittextName)
    MyEditTextView myedittextName;
    @BindView(R.id.myedittextNum)
    MyEditTextView myedittextNum;
    @BindView(R.id.gotoSelectBank)
    GotoView gotoSelectBank;
    @BindView(R.id.edittextMoney)
    EditText edittextMoney;
    @BindView(R.id.textViewTotalMoney)
    TextView textViewTotalMoney;
    @BindView(R.id.textViewGetAll)
    TextView textViewGetAll;
    @BindView(R.id.btnCommit)
    Button btnCommit;
    @BindView(R.id.gotoSelectMethoth)
    GotoView gotoSelectMethoth;
    @BindView(R.id.textViewTotalMoney02)
    TextView textViewTotalMoney02;
    private String payType = 0 + "";
    private String getMoney;
    private String name;
    private String aliId;
    private String bankName;
    private String total;

    //   bank = 银行卡 alipay = 支付宝wx=微信
    @Override
    protected void onInitData() {
        setTitle("提现");
        total = getIntent().getStringExtra(IntentDataKeyConstant.DATA).replace("可提现", "");
        textViewTotalMoney.setText(total);
        textViewTotalMoney02.setText(total);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_get_cash;
    }

    @Override
    public void onSuccess(BaseBean data) {
        if (data.getUrl().equals(ConstantUrl.getCashUrl)) {
            ToastUtil.toast(data.getM());
            finish();
        }
    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {
        if (c_codeBean.getUrl().equals(ConstantUrl.getCashUrl)) {
            ToastUtil.toast(c_codeBean.getM());
        }
    }

    @Override
    protected void onInitViewNew() {
        myedittextNum.setTextChangeListenner(new TextChangeListenner() {
            @Override
            public void textChange(CharSequence charSequence) {
                if (payType.equals("0")) {
                    if (BankCheckUtil.isBankCard(charSequence.toString())) {
                        bankName = BankCheckUtil.check(charSequence.toString());
                        gotoSelectBank.setFirstText(bankName);
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }


    //提取全部
    public void getAll(View view) {
        if (getInputData()) {
            getMoney = total;
            requestGet();
        }
    }

    //普通提现
    public void getCashRequest(View view) {
        if (getInputData()) {
            requestGet();
        }
    }

    private void requestGet() {
        //   bank = 银行卡 alipay = 支付宝wx=微信
        Map<String, String> map = new HashMap();
        map.put("type", "alipay");
        map.put("real_name", name);
        map.put("code", aliId);
        map.put("price", getMoney);
        if (payType.equals("1")) {
            post(ConstantUrl.getCashUrl, map, new BaseBean());
        } else if (payType.equals("0")) {
            if (TextUtils.isEmpty(bankName)) {
                ToastUtil.toast("账号不正确");
                return;
            }
            map.put("address", bankName);
            post(ConstantUrl.getCashUrl, map, new BaseBean());
        }
    }

    private boolean getInputData() {
        getMoney = edittextMoney.getText().toString();
        name = myedittextName.getText().toString();
        aliId = myedittextNum.getText().toString();
        if (TextUtils.isEmpty(name)) {
            ToastUtil.toast("请输入姓名");
            return false;
        }
        if (TextUtils.isEmpty(getMoney)) {
            ToastUtil.toast("请输入提现金额");
            return false;
        }
        if (TextUtils.isEmpty(name)) {
            ToastUtil.toast("请输入账号");
            return false;
        }
        return true;
    }


    //选择提现的方式
    public void selectGetMethoed(View view) {
        List<SelectedDataBean> datas = new ArrayList<>();
        SelectedDataBean selectedDataBean = new SelectedDataBean("0", "银行卡");
        datas.add(selectedDataBean);
        selectedDataBean = new SelectedDataBean("1", "支付宝");
        datas.add(selectedDataBean);
        MyPopOneListPicker picker = new MyPopOneListPicker(getContext(), datas, new MyPopOneListPicker.Pop_selectedListenner() {
            @Override
            public void getData(SelectedDataBean data) {
                if (data.getId().equals("0")) {
                    myedittextName.setHint("请输入持卡人姓名");
                    myedittextNum.setHint("请输入银行卡号");
                    gotoSelectBank.setVisibility(View.VISIBLE);
                    gotoSelectMethoth.setFirstText("银行卡提现");
                } else {
                    myedittextName.setHint("请输入姓名");
                    myedittextNum.setHint("请输入支付宝账号");
                    gotoSelectBank.setVisibility(View.GONE);
                    gotoSelectMethoth.setFirstText("支付宝提现");
                }
                payType = data.getId();
            }
        });
        picker.show();
        CommonUtils.hiddeKeybord(gotoSelectMethoth, this);
    }

    //选择银行
    public void selectBank(View view) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
