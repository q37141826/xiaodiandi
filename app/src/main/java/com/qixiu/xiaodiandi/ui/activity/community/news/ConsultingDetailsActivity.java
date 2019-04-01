package com.qixiu.xiaodiandi.ui.activity.community.news;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.ToastUtil;
import com.qixiu.qixiu.utils.XrecyclerViewUtil;
import com.qixiu.qixiu.utils.html_utils.HtmlUtils;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantRequest;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.constant.IntentDataKeyConstant;
import com.qixiu.xiaodiandi.model.comminity.news.ConsultingDetailsBean;
import com.qixiu.xiaodiandi.model.comminity.news.NewsListInterf;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;
import com.qixiu.xiaodiandi.ui.wigit.WritePop;

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
    @BindView(R.id.swip_refreshlayout)
    SwipeRefreshLayout swipRefreshlayout;
    private NewsListInterf listBean;
    private TextView textViewHtml;
    CommentAdapter adapter;
    private WritePop writePop;
    private ConsultingDetailsBean detailsBean;
    private RelativeLayout relativeComments;

    @Override
    protected void onInitData() {
        setTitle("资讯详情");
        XrecyclerViewUtil.setXrecyclerView(xrecyclerView, this, this, false, 1, null);
        adapter = new CommentAdapter();
        xrecyclerView.setAdapter(adapter);
        listBean = getIntent().getParcelableExtra(IntentDataKeyConstant.DATA);
        if (listBean.getType().equals("4")) {
            relativeComments.setVisibility(View.GONE);
        }
        getData();
        swipRefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
    }

    private void getData() {
//        类型1视频专题，2公司动态，3最新资讯，4点滴学院
        ConstantRequest.newsDetailsRequest(getOkHttpRequestModel(),
                ConstantUrl.newsinfoUrl, listBean.getType() + "", listBean.getId() + "", new ConsultingDetailsBean());
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
        if (data instanceof ConsultingDetailsBean) {
            detailsBean = (ConsultingDetailsBean) data;
            HtmlUtils.getInstance().setHtml(textViewHtml, detailsBean.getO().getContent(), this);
            adapter.refreshData(detailsBean.getE());
        }
        if (data.getUrl().equals(ConstantUrl.leaveCommentsUrl)) {
            ToastUtil.toast(data.getM());
            writePop.dismiss();
            getData();
        }
        swipRefreshlayout.setRefreshing(false);
        xrecyclerView.loadMoreComplete();
    }

    @Override
    public void onError(Exception e) {
        swipRefreshlayout.setRefreshing(false);
        xrecyclerView.loadMoreComplete();
    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {
        swipRefreshlayout.setRefreshing(false);
        xrecyclerView.loadMoreComplete();
    }

    @Override
    protected void onInitViewNew() {
        View headerview = View.inflate(getContext(), R.layout.header_consulting_details, null);
        xrecyclerView.addHeaderView(headerview);
        initHead(headerview);
    }

    private void initHead(View view) {
        textViewHtml = view.findViewById(R.id.textViewHtml);
        relativeComments = view.findViewById(R.id.relativeComments);
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
        xrecyclerView.loadMoreComplete();
    }

    public void giveComments(View view) {
        writePop = new WritePop(getContext());
        writePop.setSendListenner(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConstantRequest.leaveComments(getOkHttpRequestModel(), ConstantUrl.leaveCommentsUrl,
                        listBean.getType() + "", detailsBean.getO().getId() + "", writePop.getText().toString(), "", "", new BaseBean());
            }
        });

    }
}
