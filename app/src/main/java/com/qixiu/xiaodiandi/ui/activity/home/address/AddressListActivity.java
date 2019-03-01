package com.qixiu.xiaodiandi.ui.activity.home.address;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qixiu.qixiu.recyclerview_lib.OnRecyclerItemListener;
import com.qixiu.qixiu.request.OKHttpRequestModel;
import com.qixiu.qixiu.request.OKHttpUIUpdataListener;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.XrecyclerViewUtil;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantRequest;
import com.qixiu.xiaodiandi.constant.ConstantString;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.constant.IntentDataKeyConstant;
import com.qixiu.xiaodiandi.model.login.LoginStatus;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.TitleActivity;
import com.qixiu.xiaodiandi.ui.activity.home.confirm_order.AddressBean;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by HuiHeZe on 2017/9/7.
 */

public class AddressListActivity extends TitleActivity implements OKHttpUIUpdataListener<BaseBean>, XRecyclerView.LoadingListener, AddressListAdapter.DataListenner, OnRecyclerItemListener<AddressBean.OBean> {
    private Button buttonAdd;
    private OKHttpRequestModel okHttpRequestModel;
    private SwipeRefreshLayout swiprefresh_xrecyclerView;
    private TextView textView_name_address, textView_phone_address, textView_address_content;
    private ImageView imageView_edite_right, imageView_defualt_address;
    private RelativeLayout relativeLayout_xrecyclerview_father, relativeLayout_no_message;
    private XRecyclerView recyclerview_xrecyclerView;
    private AddressListAdapter adapter;
    private int pageNo = 1, pageSize = 10;
    private AddressBean.OBean selectedBean;

    public void getNetData() {
        ConstantRequest.getAddressList(okHttpRequestModel);
    }


    @Override
    public void onSuccess(BaseBean data, int i) {
        if (data instanceof AddressBean) {
            AddressBean bean = (AddressBean) data;
            adapter.refreshData(bean.getO());
            setBackVisblity();
            refreshLoadStop();
            if (selectedBean != null) {
                EventBus.getDefault().post(selectedBean);
            }
        }
        if (ConstantUrl.addAddressUrl.equals(data.getUrl())) {
            getNetData();
        }

    }

    private void refreshLoadStop() {
        recyclerview_xrecyclerView.loadMoreComplete();
        swiprefresh_xrecyclerView.setRefreshing(false);
    }

    private void setBackVisblity() {
        if (adapter.getDatas().size() == 0 && pageNo == 1) {
            relativeLayout_no_message.setVisibility(View.VISIBLE);
        } else {
            relativeLayout_no_message.setVisibility(View.GONE);
        }
    }

    @Override
    public void onError(Call call, Exception e, int i) {
        setBackVisblity();
        refreshLoadStop();
    }

    @Override
    public void onFailure(C_CodeBean c_codeBean) {
        setBackVisblity();
        refreshLoadStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getNetData();
    }

    public void setDefultAddress(AddressBean.OBean bean) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", LoginStatus.getId());
        map.put("is_default", "1");
        map.put("aid", bean.getId());
        map.put("real_name", bean.getReal_name());
        map.put("detail", bean.getDetail());
        map.put("phone", bean.getPhone());
        map.put("is_default", "1");
        map.put("province", bean.getProvince() + "");
        map.put("city", bean.getCity() + "");
        map.put("district", bean.getDistrict() + "");
        okHttpRequestModel.okhHttpPost(ConstantUrl.addAddressUrl, map, new BaseBean());
    }

    @Override
    protected void onInitViewNew() {
        adapter = new AddressListAdapter();
        adapter.setListenner(this);
        recyclerview_xrecyclerView = (XRecyclerView) findViewById(R.id.recyclerview_xrecyclerView);
        recyclerview_xrecyclerView.setAdapter(adapter);
        XrecyclerViewUtil.setXrecyclerView(recyclerview_xrecyclerView, this, this, false, 1, null);
        swiprefresh_xrecyclerView = (SwipeRefreshLayout) findViewById(R.id.swiprefresh_xrecyclerView);
        relativeLayout_no_message = (RelativeLayout) findViewById(R.id.relativeLayout_no_message);
        relativeLayout_xrecyclerview_father = (RelativeLayout) findViewById(R.id.relativeLayout_xrecyclerview_father);
        buttonAdd = findViewById(R.id.btn_confirm_addaddress);
        buttonAdd.setOnClickListener(this);
        okHttpRequestModel = new OKHttpRequestModel(this);
        adapter.setOnItemClickListener(this);
    }


    @Override
    protected void onInitData() {
        mTitleView.setTitle("地址管理");
        mTitleView.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getNetData();
        swiprefresh_xrecyclerView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNo = 1;
                getNetData();
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_xrecyclerview;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        pageNo++;
        getNetData();
    }


    @Override
    public void sendData(AddressBean.OBean bean, String action) {
        if (action.equals(ConstantString.ACTION_REFRSH)) {
            setDefultAddress(bean);
            adapter.notifyDataSetChanged();
        }
        if (action.equals(ConstantString.GOTO_EDIT_ADDRESS)) {
            Intent intent = new Intent(this, EditAddressActivity.class);
            intent.putExtra(IntentDataKeyConstant.DATA, bean);
            startActivityForResult(intent, 10000);
        }
    }

    @Override
    public void onItemClick(View v, RecyclerView.Adapter adapter, AddressBean.OBean data) {
        setDefultAddress(data);
        selectedBean = data;
    }

    //添加地址
    public void addAddress(View view) {
        Intent intent = new Intent(this, EditAddressActivity.class);
        startActivityForResult(intent, 10000);
    }

    @Override
    public void onClick(View view) {

    }
}
