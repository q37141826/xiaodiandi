package com.qixiu.xiaodiandi.ui.activity.community;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.XrecyclerViewUtil;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


//        android 获取webview内容真实高度(webview上下可滚动距离)
//        正常获取：
//
//        mainWebView.getContentHeight()//获取html高度
//
//        mainWebView.getScale()//手机上网页缩放比例
//
//        mainWebView.getHeight()//WebView控件的高度
//
//
//
//        mainWebView.getContentHeight() * mainWebView.getScale()//得到的是网页在手机上真实的高度
//
//        mainWebView.getContentHeight() * mainWebView.getScale()-mainWebView.getHeight()//减去webview控件的高度得到的是网页上下可滚动的范围
//
//        mWebViewTotalHeight = mainWebView.getContentHeight() * mainWebView.getScale() - mainWebView.getHeight();
//
//
//        如果WebView是嵌套在ScrollView里：
//
//        mainWebView.getHeight()//此时WebView控件的高度 == 网页内容真实的高度，因为webview嵌套在ScrollView里，WebView的控件高度会根据网页内容自动改变
//
//        mScrollView.getHeight()//ScrollView控件的高度
//
//        mWebViewTotalHeight = mainWebView.getHeight() - mScrollView.getHeight();
//
//
//        总结：要获取WebView上下滚动的范围，首先要得到网页内容真实的高度，然后减去外层容器的高度


public class ConsultingDetailsActivity extends RequestActivity implements XRecyclerView.LoadingListener {


    @BindView(R.id.xrecyclerView)
    XRecyclerView xrecyclerView;

    @Override
    protected void onInitData() {
        setTitle("咨询详情");
        XrecyclerViewUtil.setXrecyclerView(xrecyclerView, this, this, false, 1, null);
        CurrentConsultingAdapter adapter = new CurrentConsultingAdapter();
        xrecyclerView.setAdapter(adapter);
        XrecyclerViewUtil.refreshFictiousData(adapter);
    }

    @Override
    public void adustTitle() {
//        super.adustTitle();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_consulting_details;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }


    public class CurrentConsultingAdapter extends RecyclerBaseAdapter {

        @Override
        public int getLayoutId() {
            return R.layout.item_consulting_commit;
        }

        @Override
        public Object createViewHolder(View itemView, Context context, int viewType) {
            return new CurrentConsultingHolder(itemView, context, this);
        }

        public class CurrentConsultingHolder extends RecyclerBaseHolder {

            public CurrentConsultingHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
                super(itemView, context, adapter);
            }

            @Override
            public void bindHolder(int position) {

            }
        }
    }

}
