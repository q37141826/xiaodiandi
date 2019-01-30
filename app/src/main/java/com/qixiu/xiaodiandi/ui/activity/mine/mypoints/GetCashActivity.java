package com.qixiu.xiaodiandi.ui.activity.mine.mypoints;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.CommonUtils;
import com.qixiu.wigit.GotoView;
import com.qixiu.wigit.myedittext.MyEditTextView;
import com.qixiu.wigit.picker.MyPopOneListPicker;
import com.qixiu.wigit.picker.SelectedDataBean;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    protected void onInitData() {
        setTitle("提现");
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_get_cash;
    }

    @Override
    public void onSuccess(BaseBean data) {

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


    //提取全部
    public void getAll(View view) {
    }

    //普通提现
    public void getCashRequest(View view) {

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
