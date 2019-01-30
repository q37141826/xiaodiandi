package com.qixiu.xiaodiandi.ui.activity.baseactivity;

import android.view.View;
import android.view.ViewGroup;

import com.qixiu.qixiu.R;
import com.qixiu.qixiu.titleview.TitleView;
import com.qixiu.qixiu.utils.StatusBarUtils;
import com.qixiu.qixiu.utils.ToastUtil;


/**
 * Created by Administrator on 2017/6/14 0014.
 */

public abstract class TitleActivity extends BaseActivity implements View.OnClickListener {

    protected TitleView mTitleView;

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
                finish();
            }
        });
        onInitViewNew();
    }

    public void setBackRes(){
        mTitleView.getView().setBackgroundResource(R.drawable.title_under_line);
    }


    public void adustTitle() {
        setLayout(StatusBarUtils.getStatusBarHeight(getContext()));
    }

    protected void setLayout(int height) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mTitleView.getView().getLayoutParams();
        params.topMargin = height;
//        mTitleView.getView().setY(StatusBarUtils.getStatusBarHeight(getContext()));
//        mTitleView.getView().setLayoutParams(params);
//        mTitleView.getView().setY( StatusBarUtils.getStatusBarHeight(getContext()));
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
        }    }
}
