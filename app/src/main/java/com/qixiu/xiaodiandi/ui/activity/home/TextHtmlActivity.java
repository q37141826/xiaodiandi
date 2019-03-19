package com.qixiu.xiaodiandi.ui.activity.home;

import android.os.Bundle;
import android.widget.TextView;

import com.qixiu.qixiu.utils.html_utils.HtmlUtils;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.IntentDataKeyConstant;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.TitleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TextHtmlActivity extends TitleActivity {


    @BindView(R.id.textView)
    TextView textView;
    private String content;

    @Override
    protected void onInitViewNew() {
        setTitle("详情");
        content = getIntent().getStringExtra(IntentDataKeyConstant.DATA);
        HtmlUtils.getInstance().setHtml(textView,content,this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_text_html;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
