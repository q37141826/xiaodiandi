package com.qixiu.xiaodiandi.ui.activity.home;

import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import com.qixiu.qixiu.utils.html_utils.HtmlUtils;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.IntentDataKeyConstant;
import com.qixiu.xiaodiandi.model.home.MessageBean;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.TitleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TextHtmlActivity extends TitleActivity {


    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.textViewTiltle)
    TextView textViewTiltle;
    @BindView(R.id.textViewTime)
    TextView textViewTime;
    private Parcelable data;

    @Override
    protected void onInitViewNew() {
        setTitle("详情");
        data = getIntent().getParcelableExtra(IntentDataKeyConstant.DATA);
        if (data instanceof MessageBean.OBean.ListBean) {
            MessageBean.OBean.ListBean bean = (MessageBean.OBean.ListBean) data;
            textViewTiltle.setText(bean.getTitle());
            textViewTime.setText(bean.getAdd_time());
            HtmlUtils.getInstance().setHtml(textView, bean.getContent(), this);
        }


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
