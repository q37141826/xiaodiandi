package com.qixiu.xiaodiandi.ui.activity.mine.vip;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.XrecyclerViewUtil;
import com.qixiu.wigit.VerticalSwipeRefreshLayout;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.constant.IntentDataKeyConstant;
import com.qixiu.xiaodiandi.model.mine.vip.FriendsListBean;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;
import com.qixiu.xiaodiandi.utils.GlideUtils;
import com.qixiu.xiaodiandi.utils.ImageUrlUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class FriendsListActivity extends RequestActivity implements XRecyclerView.LoadingListener {

    @BindView(R.id.xrecyclerView)
    XRecyclerView xrecyclerView;
    @BindView(R.id.swiprefresh)
    VerticalSwipeRefreshLayout swiprefresh;
    @BindView(R.id.relativeNothing)
    RelativeLayout relativeNothing;
    private String type;
    private FriendsAdapter adapter;

    @Override
    protected void onInitData() {
        type = getIntent().getStringExtra(IntentDataKeyConstant.DATA);
        if (type.equals("1")) {
            setTitle("我的好友");
        } else {
            setTitle("社群好友");
        }
        loadFriendsList();
        swiprefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadFriendsList();
            }
        });
    }

    private void loadFriendsList() {
//        type		是	类型：1我的好友，2社群好友
        Map<String, String> map = new HashMap<>();
        map.put("type", type);
        post(ConstantUrl.friendsUrl, map, new FriendsListBean());
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_friends_list;
    }

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof FriendsListBean) {
            FriendsListBean friendsListBean = (FriendsListBean) data;
            adapter.refreshData(friendsListBean.getO());
        }
        swiprefresh.setRefreshing(false);

        if (adapter.getDatas().size() == 0) {
            relativeNothing.setVisibility(View.VISIBLE);
        } else {
            relativeNothing.setVisibility(View.GONE);
        }
    }

    @Override
    public void onError(Exception e) {
        swiprefresh.setRefreshing(false);
    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {
        swiprefresh.setRefreshing(false);
    }

    @Override
    protected void onInitViewNew() {
        adapter = new FriendsAdapter();
        XrecyclerViewUtil.setXrecyclerView(xrecyclerView, this, this, false, 1, null);
        xrecyclerView.setAdapter(adapter);
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
        xrecyclerView.loadMoreComplete();
    }

    @Override
    public void onLoadMore() {
        xrecyclerView.loadMoreComplete();
    }


    public class FriendsAdapter extends RecyclerBaseAdapter {
        @Override
        public int getLayoutId() {
            return R.layout.item_friends;
        }

        @Override
        public Object createViewHolder(View itemView, Context context, int viewType) {
            return new FriendsListHolder(itemView, context, this);
        }

        public class FriendsListHolder extends RecyclerBaseHolder {
            CircleImageView circularHead;
            TextView textViewPhone, textViewName, textViewId;
            View viewLine;
            public FriendsListHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
                super(itemView, context, adapter);
                circularHead = itemView.findViewById(R.id.circularHead);
                textViewPhone = itemView.findViewById(R.id.textViewPhone);
                textViewName = itemView.findViewById(R.id.textViewName);
                textViewId = itemView.findViewById(R.id.textViewId);
                viewLine = itemView.findViewById(R.id.viewLine);
            }

            @Override
            public void bindHolder(int position) {
                if (mData instanceof FriendsListBean.OBean) {
                    FriendsListBean.OBean bean = (FriendsListBean.OBean) mData;
                    GlideUtils.loadImage(ImageUrlUtils.getFinnalImageUrl(bean.getAvatar()), circularHead, mContext);
                    textViewPhone.setText(bean.getPhone());
                    textViewName.setText(bean.getGroup_name());
                    textViewId.setText(bean.getAccount());
                    if(position==0){
                        viewLine.setVisibility(View.INVISIBLE);
                    }else {
                        viewLine.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }
}
