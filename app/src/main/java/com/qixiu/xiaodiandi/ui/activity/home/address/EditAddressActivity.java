package com.qixiu.xiaodiandi.ui.activity.home.address;

import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qixiu.qixiu.request.OKHttpRequestModel;
import com.qixiu.qixiu.request.OKHttpUIUpdataListener;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.CommonUtils;
import com.qixiu.qixiu.utils.Preference;
import com.qixiu.qixiu.utils.ToastUtil;
import com.qixiu.wigit.myedittext.MyEditTextView;
import com.qixiu.wigit.picker.AddressInitTask;
import com.qixiu.wigit.show_dialog.ArshowDialog;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantString;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.constant.IntentDataKeyConstant;
import com.qixiu.xiaodiandi.model.login.LoginStatus;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.TitleActivity;
import com.qixiu.xiaodiandi.ui.activity.home.confirm_order.AddressBean;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

public class EditAddressActivity extends TitleActivity implements OKHttpUIUpdataListener, AddressInitTask.PickListene {
    private MyEditTextView editText_name_address, editText_addressContent_address, editText_phone_address;
    private String name, address, phone, id;
    private Button btn_address_edite;
    private boolean IS_ADD = true;
    private OKHttpRequestModel okmodel;
    private AddressBean.OBean addressoBean;
    private TextView textViewAddress;
    private String province;
    private String city;
    private String count;

    @Override
    protected void onInitViewNew() {
        okmodel = new OKHttpRequestModel(this);
        btn_address_edite = (Button) findViewById(R.id.btn_address_edite);
        textViewAddress = findViewById(R.id.textViewAddress);
        editText_name_address = (MyEditTextView) findViewById(R.id.editText_name_address);
        editText_addressContent_address = (MyEditTextView) findViewById(R.id.editText_addressContent_address);
        editText_phone_address = (MyEditTextView) findViewById(R.id.editText_phone_address);
        try {
            addressoBean = getIntent().getParcelableExtra(IntentDataKeyConstant.DATA);
            name = addressoBean.getReal_name();
            address = addressoBean.getDetail();
            phone = addressoBean.getPhone();
            id = addressoBean.getId();
            province = addressoBean.getProvince();
            city = addressoBean.getCity();
            count = addressoBean.getDistrict();
            textViewAddress.setText(province + city + count);
        } catch (Exception e) {
        }
        if (id == null) {
            IS_ADD = true;
            mTitleView.setRightTextVisible(View.GONE);
        } else {
            IS_ADD = false;
            mTitleView.setRightTextVisible(View.VISIBLE);
            mTitleView.setRightImage(getContext(), R.mipmap.shopcar_delect);
        }
        editText_name_address.setText(name == null ? "" : name);
        editText_phone_address.setText(phone == null ? "" : phone);
        editText_addressContent_address.setText(address == null ? "" : address);
        if (!TextUtils.isEmpty(name)) {
            editText_name_address.setSelection(name.length());
        }
        setClick();
    }

    private void setClick() {
        btn_address_edite.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_address_edite:
                getdata();
                startEdite();
                break;
        }
    }

    private void startEdite() {

        Map<String, String> map = new HashMap<>();
        map.put("uid", LoginStatus.getId());
        CommonUtils.putDataIntoMap(map, "aid", id);
        map.put("real_name", name);
        map.put("detail", address);
        map.put("phone", phone);
        map.put("is_default", addressoBean!=null?addressoBean.getIs_default():0 + "");
        map.put("province", province + "");
        map.put("city", city + "");
        map.put("district", count + "");
        if (TextUtils.isEmpty(province) || TextUtils.isEmpty(city) || TextUtils.isEmpty(count) ||
                TextUtils.isEmpty(name) || TextUtils.isEmpty(address) || TextUtils.isEmpty(phone)) {
            ToastUtil.toast("请把信息填完整");
            return;
        }
        String url = ConstantUrl.addAddressUrl;
        if (IS_ADD) {
        } else {
            map.put("id", id);
        }
        BaseBean basebean = new BaseBean();
        okmodel.okhHttpPost(url, map, basebean);
    }

    @Override
    protected void onInitData() {
        if (name == null) {
            btn_address_edite.setText("确定新增");
        } else {
            btn_address_edite.setText("确定保存");
        }
        mTitleView.setTitle("地址管理");
        mTitleView.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(10000, new Intent(EditAddressActivity.this, AddressListActivity.class));
                finish();
            }
        });
        mTitleView.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDailog();
            }
        });
    }

    private void setDailog() {
        ArshowDialog.Builder builder = new ArshowDialog.Builder(this);
        builder.setCanceledOnTouchOutside(false);
        builder.setCancelable(false);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startDelete();
                dialog.dismiss();
            }
        });
        builder.setMessage("确定删除该地址？");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void startDelete() {
        Map<String, String> map = new HashMap<>();
        map.put("aid", id);
        map.put("uid", Preference.get(ConstantString.USERID, ""));
        okmodel.okhHttpPost(ConstantUrl.addressDelUrl, map, new BaseBean());
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_edit_address;
    }

    public void getdata() {
        name = editText_name_address.getText().toString();
        phone = editText_phone_address.getText().toString();
        address = editText_addressContent_address.getText().toString();
    }

    @Override
    public void onSuccess(Object data, int i) {
        BaseBean bean = (BaseBean) data;
        setResult(10000, new Intent(EditAddressActivity.this, AddressListActivity.class));
        finish();
        ToastUtil.toast(bean.getM());
    }

    @Override
    public void onError(Call call, Exception e, int i) {
        ToastUtil.toast(R.string.link_error);
    }

    @Override
    public void onFailure(C_CodeBean c_codeBean) {
        ToastUtil.toast(c_codeBean.getM());
    }


    //选择地址
    public void selectAddress(View view) {
        AddressInitTask execute = new AddressInitTask(this);
        //todo被测试要求不准设置默认地址
        execute.execute("", "", "");
        execute.setOnAddressPickListene(this);
    }

    //选择地址回调
    @Override
    public void setOnAddressPickListene(String province, String city, String count) {
        this.province = province;
        this.city = city;
        this.count = count;
        textViewAddress.setText(province + city + count);
    }
}
