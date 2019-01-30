package com.qixiu.xiaodiandi.ui.fragment.cart;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qixiu.qixiu.application.BaseApplication;
import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseHolder;
import com.qixiu.qixiu.request.OKHttpRequestModel;
import com.qixiu.qixiu.request.OKHttpUIUpdataListener;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.request.parameter.StringConstants;
import com.qixiu.qixiu.utils.ArshowDialogUtils;
import com.qixiu.qixiu.utils.ToastUtil;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.model.shopcar.ShopCartBean;
import com.qixiu.xiaodiandi.presenter.home.MarketPresenter;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/5/8 0008.
 */

public class ShopCarHolder extends RecyclerBaseHolder implements View.OnClickListener, ShopCarCallBackInterface, OKHttpUIUpdataListener<BaseBean>, ArshowDialogUtils.ArshowDialogListener {
    private MarketPresenter.RefreshInterf interf;

    private final TextView mTv_shopcar_add;
    private final TextView mTv_repertory;
    private final TextView mTv_subtract;
    private final ImageView mIv_shopcar_select_icon;
    private final TextView mTv_good_title;
    private final TextView mTv_shopcar_color;
    private final TextView mTv_size;
    private final TextView mTv_pice;
    private final ImageView mIv_good_picture;
    private final View rl_select;
    private ShopCartBean.OBean.ValidBean shopCarInfo = null;
    private int mNumber = 1;
    private int tempNumber = 1;
    private ShopCarOnItemSelectedListener shopCarOnItemSelectedListener;
    private int position;
    private final OKHttpRequestModel mOkHttpRequestModel;
    private final View mRl_item_delect;

    public void setInterf(MarketPresenter.RefreshInterf interf) {
        this.interf = interf;
    }

    public ShopCarHolder(View itemView, Context context, RecyclerView.Adapter adapter) {
        super(itemView, context, adapter);
        mRl_item_delect = itemView.findViewById(R.id.rl_item_delect);
        mRl_item_delect.setOnClickListener(this);
        mTv_good_title = (TextView) itemView.findViewById(R.id.tv_good_title);

        mTv_shopcar_color = (TextView) itemView.findViewById(R.id.tv_shopcar_color);
        mTv_size = (TextView) itemView.findViewById(R.id.tv_size);
        mTv_pice = (TextView) itemView.findViewById(R.id.tv_pice);
        mIv_good_picture = (ImageView) itemView.findViewById(R.id.iv_good_picture);
        mTv_shopcar_add = (TextView) itemView.findViewById(R.id.tv_shopcar_minus);
        mTv_shopcar_add.setOnClickListener(this);
        mTv_repertory = (TextView) itemView.findViewById(R.id.tv_repertory);
        mTv_subtract = (TextView) itemView.findViewById(R.id.tv_subtract);
        mIv_shopcar_select_icon = (ImageView) itemView.findViewById(R.id.iv_shopcar_select_icon);
        rl_select = itemView.findViewById(R.id.rl_select);
        rl_select.setOnClickListener(this);
        mTv_subtract.setOnClickListener(this);
        mOkHttpRequestModel = new OKHttpRequestModel(this);
//        setEditEnable(true);
    }

    @Override
    public void setShopCarOnItemSelectedListener(ShopCarOnItemSelectedListener shopCarOnItemSelectedListener) {
        this.shopCarOnItemSelectedListener = shopCarOnItemSelectedListener;
    }

    @Override
    public void onSuccess(BaseBean data, int i) {
//        if (data instanceof ShopCarEditCountBean) {
//            mNumber = tempNumber;
//            shopCarInfo.setCart_num(tempNumber );
//            if (shopCarOnItemSelectedListener != null) {
//                shopCarOnItemSelectedListener.shopCarOnItemSelected(position);
//            }
//        } else {
//            if (ConstantUrl.ClearShopCarURl != data.getUrl()) {
//
//                ToastUtil.showToast(BaseApplication.getContext(), "库存编辑失败，库存已不足");
//            } else {
//                RecyclerBaseAdapter recyclerBaseAdapter = (RecyclerBaseAdapter) mAdapter;
//                recyclerBaseAdapter.getDatas().remove(this.mData);
//                recyclerBaseAdapter.notifyItemRemoved(position);
//                if (recyclerBaseAdapter.getDatas().size() <= 0) {
//                    recyclerBaseAdapter.holderNotifyRefresh(null);
//                }
//            }
//        }
//        if (ConstantUrl.STORE_SHOPCAR_DELECTED != data.getUrl()) {
//            setShowRepertoryNum();
//        }

    }

    @Override
    public void onError(Call call, Exception e, int i) {
        setShowRepertoryNum();
        ToastUtil.showToast(BaseApplication.getContext(), R.string.link_error);
    }

    @Override
    public void onFailure(C_CodeBean c_codeBean) {
        setShowRepertoryNum();
        ToastUtil.showToast(BaseApplication.getContext(), c_codeBean.getM());
    }

    private void setShowRepertoryNum() {
        tempNumber = mNumber;
        mTv_repertory.setText(mNumber + "");
//        setEditEnable(true);
    }

    @Override
    public void onClickPositive(DialogInterface dialogInterface, int which) {
        if (interf != null) {
            interf.delete(mData);
        }
    }

    @Override
    public void onClickNegative(DialogInterface dialogInterface, int which) {

    }

    public interface ShopCarOnItemSelectedListener {

        void shopCarOnItemSelected(int position);
    }


    @Override
    public void bindHolder(int position) {
        this.position = position;
        if (mData instanceof ShopCartBean.OBean.ValidBean) {
            shopCarInfo = (ShopCartBean.OBean.ValidBean) mData;
            mTv_good_title.setText(shopCarInfo.getProductInfo().getStore_name());
            mTv_pice.setText(StringConstants.STRING_RMB + shopCarInfo.getProductInfo().getAttrInfo().getPrice());
            Glide.with(BaseApplication.getContext()).load(shopCarInfo.getProductInfo().getImage()).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mIv_good_picture);
        }
        mNumber = shopCarInfo.getCart_num();
        tempNumber = mNumber;

        mTv_repertory.setText(mNumber + "");
        if (shopCarInfo != null) {
            mIv_shopcar_select_icon.setImageResource(shopCarInfo.isSelected() ? R.mipmap.shopcar_goods_select : R.mipmap.shopcar_goods_notselect);
        }
    }

//    private void setEditEnable(boolean editEnable) {
//        mTv_subtract.setEnabled(editEnable);
//        mTv_shopcar_add.setEnabled(editEnable);
//    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_shopcar_minus:
            case R.id.tv_subtract:
//                setEditEnable(false);
                if (mTv_repertory != null && !TextUtils.isEmpty(mTv_repertory.getText())) {
                    int currentShowNumber = Integer.parseInt(mTv_repertory.getText().toString());
                    if (v.getId() == R.id.tv_shopcar_minus) {
                        tempNumber = currentShowNumber + 1;
                    } else {
                        if (currentShowNumber > 1) {
                            tempNumber = currentShowNumber - 1;
                        } else {
                            tempNumber = 1;
                            ToastUtil.showToast(BaseApplication.getContext(), "库存不能为0");
//                            setEditEnable(true);
                            return;
                        }
                    }

//                    Map<String, String> map = new HashMap();
//                    map.put(IntentDataKeyConstant.VIPID, Preference.get(IntentDataKeyConstant.ID, StringConstants.STRING_EMPTY));
//                    map.put(IntentDataKeyConstant.ID, shopCarInfo.getId());
//                    if (shopCarInfo.getId() != null) {
//                        map.put("goodsid", shopCarInfo.getProduct_id());
//                    }
//                    map.put(IntentDataKeyConstant.NUM, tempNumber + StringConstants.STRING_EMPTY);
//                    mOkHttpRequestModel.okhHttpPost(ConstantUrl.STORE_SHOPCAR_EDIT_COUNT, map, new ShopCarEditCountBean(), true);
                    shopCarInfo.setCart_num(tempNumber);
                    if (interf != null) {
                        interf.add(mData);
                    }
                }
                break;
            case R.id.rl_select:
                if (shopCarInfo != null) {
                    shopCarInfo.setSelected(!shopCarInfo.isSelected());
                    mIv_shopcar_select_icon.setImageResource(shopCarInfo.isSelected() ? R.mipmap.shopcar_goods_select : R.mipmap.shopcar_goods_notselect);
                    if (shopCarOnItemSelectedListener != null) {
                        shopCarOnItemSelectedListener.shopCarOnItemSelected(position);
                    }
                }
                if (interf != null) {
                    interf.selected(mData);
                }
                break;

            case R.id.rl_item_delect:
                ArshowDialogUtils.showDialog(itemView.getContext(), "确定删除吗？", this);

                break;
        }
    }
}
