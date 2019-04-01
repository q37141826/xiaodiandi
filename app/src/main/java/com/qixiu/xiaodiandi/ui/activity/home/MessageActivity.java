package com.qixiu.xiaodiandi.ui.activity.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qixiu.qixiu.recyclerview_lib.OnRecyclerItemListener;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.XrecyclerViewUtil;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.model.home.MessageBean;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MessageActivity extends RequestActivity implements XRecyclerView.LoadingListener, OnRecyclerItemListener {


    int pageNo = 1, pageSize = 20;
    @BindView(R.id.xrecyclerView)
    XRecyclerView xrecyclerView;
    @BindView(R.id.swiprefresh)
    SwipeRefreshLayout swiprefresh;
    @BindView(R.id.relativeNothing)
    RelativeLayout relativeNothing;
    private MessageAdapter adapter;
    private String selectedId;

    @Override
    protected void onInitData() {
        setTitle("消息中心");
        swiprefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNo = 1;
                getData();
            }
        });
        getData();
        XrecyclerViewUtil.setXrecyclerView(xrecyclerView, this, this, false, 1, null);
        adapter = new MessageAdapter();
        xrecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }


    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("pageNo", pageNo + "");
        map.put("pageSize", pageSize + "");
        post(ConstantUrl.messageListUrl, map, new MessageBean());

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_message;
    }

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof MessageBean) {
            MessageBean bean = (MessageBean) data;
            if (pageNo == 1) {
                adapter.refreshData(bean.getO().getList());
            } else {
                adapter.addDatas(bean.getO().getList());
            }
        }
        swiprefresh.setRefreshing(false);
        xrecyclerView.loadMoreComplete();
        if (ConstantUrl.readMsgUrl.equals(data.getUrl())) {
            List<MessageBean.OBean.ListBean> datas = adapter.getDatas();
            for (int i = 0; i < datas.size(); i++) {
                if (selectedId.equals(datas.get(i).getId() + "")) {
                    datas.get(i).setIs_see(1);
                }
            }
            adapter.notifyDataSetChanged();
        }


        if (adapter.getDatas().size() == 0) {
            relativeNothing.setVisibility(View.VISIBLE);
        } else {
            relativeNothing.setVisibility(View.GONE);
        }
    }

    @Override
    public void onError(Exception e) {
        swiprefresh.setRefreshing(false);
        xrecyclerView.loadMoreComplete();
    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {
        swiprefresh.setRefreshing(false);
        xrecyclerView.loadMoreComplete();
    }

    @Override
    protected void onInitViewNew() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageViewMessage:
                MessageActivity.start(getContext(), MessageActivity.class);
                break;
        }
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
        pageNo++;
        getData();
    }

    @Override
    public void onItemClick(View v, RecyclerView.Adapter adapter, Object data) {
        if (data instanceof MessageBean.OBean.ListBean) {
            MessageBean.OBean.ListBean bean = (MessageBean.OBean.ListBean) data;
            if (bean.getIs_see() == 0) {
                Map<String, String> map = new HashMap<>();
                map.put("nid", bean.getId() + "");
                selectedId = bean.getId() + "";
                post(ConstantUrl.readMsgUrl, map, new BaseBean());
            }
            TextHtmlActivity.start(getContext(), TextHtmlActivity.class, bean);
        }
    }


    public class MessageAdapter extends RecyclerBaseAdapter {
        @Override
        public int getLayoutId() {
            return R.layout.item_message;
        }

        @Override
        public Object createViewHolder(View itemView, Context context, int viewType) {
            return new MessageHolder(itemView, context, this);
        }
    }

    public class MessageHolder extends RecyclerBaseHolder {
        ImageView imageView;
        TextView textViewTitle;
        TextView textViewTime;

        public MessageHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
            super(itemView, context, adapter);
            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewTime = itemView.findViewById(R.id.textViewTime);
        }

        @Override
        public void bindHolder(int position) {
            if (mData instanceof MessageBean.OBean.ListBean) {
                MessageBean.OBean.ListBean bean = (MessageBean.OBean.ListBean) mData;
                if (bean.getIs_see() == 0) {
                    imageView.setImageResource(R.drawable.shape_circle_theme);
                    textViewTitle.setTextColor(mContext.getResources().getColor(R.color.font_grey));
                    textViewTime.setTextColor(mContext.getResources().getColor(R.color.font_grey));
                } else {
                    imageView.setImageResource(R.drawable.shape_grey_circle);
                    textViewTitle.setTextColor(mContext.getResources().getColor(R.color.text_grey));
                    textViewTime.setTextColor(mContext.getResources().getColor(R.color.text_grey));
                }
                textViewTitle.setText(bean.getContent());
                textViewTime.setText(bean.getAdd_time().substring(0, 10));

            }
        }
    }
}
