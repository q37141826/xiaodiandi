package com.qixiu.xiaodiandi.ui.fragment.cart;

import android.content.Context;
import android.view.View;

import com.qixiu.qixiu.recyclerview_lib.RecyclerBaseAdapter;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.model.shopcar.ShopCartBean;
import com.qixiu.xiaodiandi.presenter.home.MarketPresenter;


/**
 * Created by Administrator on 2017/5/8 0008.
 */

public class ShopCarAdapter extends RecyclerBaseAdapter<ShopCartBean.OBean.ValidBean, ShopCarHolder> implements ShopCarCallBackInterface, ShopCarHolder.ShopCarOnItemSelectedListener {
    private MarketPresenter.RefreshInterf interf;


    public void setInterf(MarketPresenter.RefreshInterf interf) {
        this.interf = interf;
    }

    private ShopCarHolder.ShopCarOnItemSelectedListener shopCarOnItemSelectedListener;

    @Override
    public int getLayoutId() {
        return R.layout.item_rv_shopcar;
    }

    @Override
    public ShopCarHolder createViewHolder(View itemView, Context context, int viewType) {
        ShopCarHolder shopCarHolder = new ShopCarHolder(itemView, context, this);
        shopCarHolder.setShopCarOnItemSelectedListener(this);
        shopCarHolder.setInterf(interf);
        return shopCarHolder;
    }

    @Override
    public void setShopCarOnItemSelectedListener(ShopCarHolder.ShopCarOnItemSelectedListener shopCarOnItemSelectedListener) {

        this.shopCarOnItemSelectedListener = shopCarOnItemSelectedListener;
    }

    @Override
    public void shopCarOnItemSelected(int position) {
        if (shopCarOnItemSelectedListener != null) {
            shopCarOnItemSelectedListener.shopCarOnItemSelected(position);
        }
    }
}
