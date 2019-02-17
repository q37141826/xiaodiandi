package com.qixiu.xiaodiandi.ui.activity.mine.vip;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.XrecyclerViewUtil;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.model.mine.vip.FriendsListBean;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.RequestActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class FriendsListActivity extends RequestActivity implements XRecyclerView.LoadingListener {

    @BindView(R.id.xrecyclerView)
    XRecyclerView xrecyclerView;

    @Override
    protected void onInitData() {
        setTitle("我的好友");
        loadFriendsList();
    }

    private void loadFriendsList() {
        post(ConstantUrl.friendsUrl, null, new FriendsListBean());
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_friends_list;
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
        FriendsAdapter adapter = new FriendsAdapter();
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

            public FriendsListHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
                super(itemView, context, adapter);
                circularHead = itemView.findViewById(R.id.circularHead);
                textViewPhone = itemView.findViewById(R.id.textViewPhone);
                textViewName = itemView.findViewById(R.id.textViewName);
                textViewId = itemView.findViewById(R.id.textViewId);
            }

            @Override
            public void bindHolder(int position) {
                if(mData instanceof FriendsListBean.OBean){
                    FriendsListBean.OBean bean= (FriendsListBean.OBean) mData;
                    Glide.with(mContext).load(bean.getAvatar()).into(circularHead);
                    textViewPhone.setText(bean.getPhone());
                    textViewName.setText(bean.getGroup_name());
                    textViewId.setText(bean.getAccount());
                }
            }
        }
    }
}
