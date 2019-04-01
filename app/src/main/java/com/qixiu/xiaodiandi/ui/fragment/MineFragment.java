package com.qixiu.xiaodiandi.ui.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
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
import com.qixiu.xiaodiandi.ui.activity.baseactivity.GotoWebActivity;
import com.qixiu.xiaodiandi.ui.activity.home.BindWebActivity;
import com.qixiu.xiaodiandi.ui.activity.home.address.AddressListActivity;
import com.qixiu.xiaodiandi.ui.activity.mine.MyPublishActivity;
import com.qixiu.xiaodiandi.ui.activity.mine.MyprofileActivity;
import com.qixiu.xiaodiandi.ui.activity.mine.SettingActivity;
import com.qixiu.xiaodiandi.ui.activity.mine.TicketActivity;
import com.qixiu.xiaodiandi.ui.activity.mine.mycollection.MyCollectionActivity;
import com.qixiu.xiaodiandi.ui.activity.mine.mypoints.MyPointsActivity;
import com.qixiu.xiaodiandi.ui.activity.mine.order.OrderActivity;
import com.qixiu.xiaodiandi.ui.activity.mine.vip.InviteActivity;
import com.qixiu.xiaodiandi.ui.activity.mine.vip.VipActivity;
import com.qixiu.xiaodiandi.ui.fragment.basefragment.base.RequestFragment;
import com.qixiu.xiaodiandi.utils.GlideUtils;
import com.qixiu.xiaodiandi.utils.ImageUrlUtils;

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


    @BindView(R.id.textViewGotoVip)
    TextView textViewGotoVip;
    @BindView(R.id.circularHead)
    CircleImageView circularHead;
    @BindView(R.id.textViewPhone)
    TextView textViewPhone;
    @BindView(R.id.textViewVipname)
    TextView textViewVipname;
    @BindView(R.id.textViewSign)
    TextView textViewSign;
    @BindView(R.id.textViewVipId)
    TextView textViewVipId;
    @BindView(R.id.imageViewSetting)
    ImageView imageViewSetting;
    @BindView(R.id.gotoAllOrder)
    GotoView gotoAllOrder;
    @BindView(R.id.textViewWaitPay)
    TextView textViewWaitPay;
    @BindView(R.id.textViewNumWaitPay)
    TextView textViewNumWaitPay;
    @BindView(R.id.textViewNumWaitSend)
    TextView textViewNumWaitSend;
    @BindView(R.id.textViewWaitSend)
    TextView textViewWaitSend;
    @BindView(R.id.textViewNumWaitReceive)
    TextView textViewNumWaitReceive;
    @BindView(R.id.textViewWaitReceive)
    TextView textViewWaitReceive;
    @BindView(R.id.textViewNumChange)
    TextView textViewNumChange;
    @BindView(R.id.textViewWaitBack)
    TextView textViewWaitBack;
    @BindView(R.id.gotoMyPoints)
    GotoView gotoMyPoints;
    @BindView(R.id.gotoTicket)
    GotoView gotoTicket;
    @BindView(R.id.gotoAddress)
    GotoView gotoAddress;
    @BindView(R.id.gotoMyCollection)
    GotoView gotoMyCollection;
    @BindView(R.id.gotoVip)
    GotoView gotoVip;
    @BindView(R.id.gotoPhone)
    GotoView gotoPhone;
    @BindView(R.id.gotoViewHelp)
    GotoView gotoViewHelp;
    @BindView(R.id.gotoTest)
    GotoView gotoTest;
    @BindView(R.id.vg_title)
    RelativeLayout vgTitle;
    Unbinder unbinder1;
    private String serivicePhone;
    private boolean isVip;
    private UserBean bean;
    private PopupWindow popupWindow;
    private View contentView;

    @Override
    public void onSuccess(BaseBean data) {
        if (data instanceof UserBean) {
            bean = (UserBean) data;
            serivicePhone = bean.getO().getServicetelephone();
            GlideUtils.loadImage(ImageUrlUtils.getFinnalImageUrl(bean.getO().getAvatar()),circularHead,getContext());
//            Glide.with(getContext()).load().into(circularHead);
            textViewPhone.setText(bean.getO().getPhone());
            textViewVipname.setText(bean.getO().getGroup_name());
            textViewVipId.setText("ID:  " + bean.getO().getAccount());
//            textViewWaitPay.setText(bean.geteBean().getNoBuy() + "");
//            textViewWaitSend.setText(bean.geteBean().getNoTake() + "");
//            textViewWaitBack.setText(bean.geteBean().getNoReply() + "");
            gotoMyPoints.setSecondText(bean.getO().getIntegral() + "点滴");

            textViewNumWaitPay.setText(bean.getE().getNoBuy() + "");
            textViewNumWaitPay.setVisibility(bean.getE().getNoBuy() == 0 ? View.GONE : View.VISIBLE);
            textViewNumWaitSend.setText(bean.getE().getNoReply() + "");
            textViewNumWaitSend.setVisibility(bean.getE().getNoReply() == 0 ? View.GONE : View.VISIBLE);
            textViewNumWaitReceive.setText(bean.getE().getNoTake() + "");
            textViewNumWaitReceive.setVisibility(bean.getE().getNoTake() == 0 ? View.GONE : View.VISIBLE);
            isVip = !bean.getO().getGroup_name().contains("普通");
            if (isVip) {
                gotoVip.setFirstText("我要推荐");
            } else {
                gotoVip.setFirstText("我要成为会员");
            }
            if (bean.getO().getSigned() == 1) {
                textViewSign.setEnabled(false);
                textViewSign.setText("已签到");
            } else {
                textViewSign.setEnabled(true);
                textViewSign.setText("每日签到");
            }
        }
        if (data.getUrl().equals(ConstantUrl.sign)) {
            getData();
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
        gotoAllOrder.getSecondView().setTextSize(12);
    }

    @Override
    public void onStart() {
        super.onStart();
        getData();
    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", LoginStatus.getId());
        post(ConstantUrl.mineCenter, map, new UserBean());
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_mine;
    }


    @OnClick({R.id.circularHead, R.id.imageViewSetting, R.id.gotoAddress,
            R.id.textViewGotoVip, R.id.textViewWaitPay, R.id.textViewWaitSend,
            R.id.textViewWaitReceive, R.id.textViewWaitBack, R.id.gotoAllOrder,
            R.id.gotoMyPoints, R.id.gotoMyCollection, R.id.gotoTicket, R.id.textViewSign,
            R.id.gotoViewHelp, R.id.gotoVip, R.id.gotoTest, R.id.gotoPhone, R.id.gotoMyPublish
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
                AddressListActivity.start(getContext(), AddressListActivity.class,"收货地址");
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

            case R.id.gotoVip:
                if (isVip) {
                    InviteActivity.start(getContext(), InviteActivity.class);
                } else {
                    BindWebActivity.start(getContext(), BindWebActivity.class);
                }
                break;

            case R.id.gotoViewHelp:
                GotoWebActivity.start(getContext(), GotoWebActivity.class, ConstantUrl.helpUrl);
                break;
            case R.id.gotoMyPublish:
                MyPublishActivity.start(getContext(),MyPublishActivity.class);

                break;
            case R.id.gotoPhone:
                if (popupWindow != null) {
                    showPop();
                } else {
                    createPop();
                }
//                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + serivicePhone));//跳转到拨号界面，同时传递电话号码
//                startActivity(dialIntent);
                break;
        }
    }

    private void createPop() {
        contentView = View.inflate(getContext(), R.layout.pop_qrcode, null);
        popupWindow = new PopupWindow(contentView);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,
                true);
//这句话让popuwindow沉浸状态栏
        popupWindow.setClippingEnabled(false);
        ImageView imageView = contentView.findViewById(R.id.imageViewQrcode);
        Glide.with(getContext()).load(BuildConfig.BASE_URL + bean.getO().getCode().replace(BuildConfig.BASE_URL, "")).into(imageView);
        RelativeLayout relativeLayout = contentView.findViewById(R.id.relativeLayout_back_for_dismiss);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        showPop();
    }

    private void showPop() {
        popupWindow.showAtLocation(contentView, Gravity.CENTER, 0, 0);
    }


    private void gotoSign() {
        post(ConstantUrl.sign, null, new BaseBean());
    }


    @Override
    public void moveToPosition(int position) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }
}
