package com.qixiu.xiaodiandi.ui.activity.mine.order;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;

import java.util.HashMap;
import java.util.Map;

public class CheckWhereActivity extends RequestActivity {
    private String order_id;
    private RecyclerView recycleView_checktrance;
    private ImageView imageView_checkwhere;
    private TextView textView_tranceportState, textView_tranceportName, textView_tranceportId;
    private CheckWhereAdapter adapter;
    RelativeLayout relativelayout_back_checkwhere, relativelaout_all, relativelayout_nothing;
    private ImageView imageView_check_nothing;


    @Override
    protected void onInitViewNew() {
        mTitleView.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleView.setTitle("物流详情");
        relativelayout_nothing = (RelativeLayout) findViewById(R.id.relativelayout_nothing);
        relativelaout_all = (RelativeLayout) findViewById(R.id.relativelaout_all);
        imageView_check_nothing = (ImageView) findViewById(R.id.imageView_check_nothing);

        recycleView_checktrance = (RecyclerView) findViewById(R.id.recycleView_checktrance);
        imageView_checkwhere = (ImageView) findViewById(R.id.imageView_checkwhere);
        textView_tranceportState = (TextView) findViewById(R.id.textView_tranceportState);
        textView_tranceportName = (TextView) findViewById(R.id.textView_tranceportName);
        textView_tranceportId = (TextView) findViewById(R.id.textView_tranceportId);
        order_id = getIntent().getStringExtra("order_id");
    }

    @Override
    protected void onInitData() {
        Map<String, String> map = new HashMap<>();
        map.put("order_id", order_id);
        post(ConstantUrl.CheckWhereUrl, map, new BaseBean());
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_check_where;
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof CheckWhereBean) {
            CheckWhereBean.OBean checkWhereBean = ((CheckWhereBean) data).getO();
            Glide.with(CheckWhereActivity.this).load(checkWhereBean.getImgUrl()).into(imageView_checkwhere);
            textView_tranceportState.setText(checkWhereBean.getState().equals("3") ? "已签收" : "未签收");
            textView_tranceportName.setText(checkWhereBean.getShipping_name());
            textView_tranceportId.setText(checkWhereBean.getNu());
            adapter = new CheckWhereAdapter();
            recycleView_checktrance.setLayoutManager(new LinearLayoutManager(CheckWhereActivity.this));
            recycleView_checktrance.setAdapter(adapter);
            adapter.refreshData(checkWhereBean.getData());
        }
    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {

    }
}
