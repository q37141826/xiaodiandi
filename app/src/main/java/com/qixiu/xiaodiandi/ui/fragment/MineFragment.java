package com.qixiu.xiaodiandi.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.utils.ToastUtil;
import com.qixiu.wigit.GotoView;
import com.qixiu.xiaodiandi.BuildConfig;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.model.login.LoginStatus;
import com.qixiu.xiaodiandi.model.mine.UserBean;
import com.qixiu.xiaodiandi.ui.activity.home.address.AddressListActivity;
import com.qixiu.xiaodiandi.ui.activity.mine.MyprofileActivity;
import com.qixiu.xiaodiandi.ui.activity.mine.SettingActivity;
import com.qixiu.xiaodiandi.ui.activity.mine.TicketActivity;
import com.qixiu.xiaodiandi.ui.activity.mine.mycollection.MyCollectionActivity;
import com.qixiu.xiaodiandi.ui.activity.mine.mypoints.MyPointsActivity;
import com.qixiu.xiaodiandi.ui.activity.mine.order.OrderActivity;
import com.qixiu.xiaodiandi.ui.activity.mine.vip.VipActivity;
import com.qixiu.xiaodiandi.ui.fragment.basefragment.base.RequestFragment;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by my on 2019/1/2.
 */

public class MineFragment extends RequestFragment {
    @BindView(R.id.textViewWaitPay)
    TextView textViewWaitPay;
    Unbinder unbinder;
    @BindView(R.id.imageViewSetting)
    ImageView imageViewSetting;
    @BindView(R.id.textViewWaitSend)
    TextView textViewWaitSend;
    @BindView(R.id.textViewWaitReceive)
    TextView textViewWaitReceive;
    @BindView(R.id.textViewWaitBack)
    TextView textViewWaitBack;
    @BindView(R.id.circularHead)
    CircleImageView circularHead;
    @BindView(R.id.textViewGotoVip)
    TextView textViewGotoVip;
    @BindView(R.id.textViewPhone)
    TextView textViewPhone;
    @BindView(R.id.textViewVipname)
    TextView textViewVipname;
    @BindView(R.id.textViewSign)
    TextView textViewSign;
    @BindView(R.id.textViewVipId)
    TextView textViewVipId;
    @BindView(R.id.gotoAllOrder)
    GotoView gotoAllOrder;
    @BindView(R.id.gotoMyPoints)
    GotoView gotoMyPoints;
    @BindView(R.id.gotoTicket)
    GotoView gotoTicket;
    @BindView(R.id.gotoAddress)
    GotoView gotoAddress;
    @BindView(R.id.gotoMyCollection)
    GotoView gotoMyCollection;

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof UserBean) {
            UserBean bean = (UserBean) data;
            Glide.with(getContext()).load(BuildConfig.BASE_URL+bean.getO().getAvatar().replace(BuildConfig.BASE_URL,"")).into(circularHead);
            textViewPhone.setText(bean.getO().getPhone());
            switch (bean.getO().getLevel()) {
                case 0:
                    textViewVipname.setText("普通会员");
                    break;
                case 1:
                    textViewVipname.setText("金牌会员");
                    break;
                case 2:
                    textViewVipname.setText("钻石会员");
                    break;
                case 3:
                    textViewVipname.setText("黑卡会员");
                    break;
            }
            textViewVipId.setText(bean.getO().getAccount());
            textViewWaitPay.setText(bean.geteBean().getNoBuy() + "");
            textViewWaitSend.setText(bean.geteBean().getNoTake() + "");
            textViewWaitBack.setText(bean.geteBean().getNoReply() + "");
        }
        if (data.getUrl().equals(ConstantUrl.sign)) {
            ToastUtil.toast(data.getM());
        }
    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onFailure(C_CodeBean c_codeBean, String m) {

    }

    @Override
    protected void onInitData() {
        mTitleView.getView().setVisibility(View.GONE);

    }

    @Override
    public void onStart() {
        super.onStart();
        getData();
    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("uid",LoginStatus.getId());
        post(ConstantUrl.mineCenter, map, new UserBean());
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_mine;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.circularHead, R.id.imageViewSetting, R.id.gotoAddress,
            R.id.textViewGotoVip, R.id.textViewWaitPay, R.id.textViewWaitSend,
            R.id.textViewWaitReceive, R.id.textViewWaitBack, R.id.gotoAllOrder,
            R.id.gotoMyPoints, R.id.gotoMyCollection, R.id.gotoTicket, R.id.textViewSign
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textViewSign:
                gotoSign();
                break;
            case R.id.circularHead:
                MyprofileActivity.start(getContext(), MyprofileActivity.class);
                break;
            case R.id.imageViewSetting:
                SettingActivity.start(getContext(), SettingActivity.class);
                break;
            case R.id.gotoAddress:
                AddressListActivity.start(getContext(), AddressListActivity.class);
                break;
            case R.id.textViewGotoVip:
                VipActivity.start(getContext(), VipActivity.class);
                break;
            case R.id.textViewWaitPay:
                OrderActivity.start(getContext(), OrderActivity.class, 1);
                break;
            case R.id.textViewWaitSend:
                OrderActivity.start(getContext(), OrderActivity.class, 2);
                break;
            case R.id.textViewWaitReceive:
                OrderActivity.start(getContext(), OrderActivity.class, 3);
                break;
            case R.id.textViewWaitBack:
                OrderActivity.start(getContext(), OrderActivity.class, 4);
                break;
            case R.id.gotoAllOrder:
                OrderActivity.start(getContext(), OrderActivity.class, 0);
                break;
            case R.id.gotoMyPoints:
                MyPointsActivity.start(getContext(), MyPointsActivity.class);
                break;
            case R.id.gotoMyCollection:
                MyCollectionActivity.start(getContext(), MyCollectionActivity.class);
                break;
            case R.id.gotoTicket:
                TicketActivity.start(getContext(), TicketActivity.class);
                break;

        }
    }

    private void gotoSign() {
        post(ConstantUrl.sign, null, new BaseBean());
    }


    @Override
    public void moveToPosition(int position) {

    }
}
