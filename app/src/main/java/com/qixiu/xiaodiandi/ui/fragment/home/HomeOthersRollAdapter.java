package com.qixiu.xiaodiandi.ui.fragment.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantString;
import com.qixiu.xiaodiandi.model.home.HomeBean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by my on 2019/1/7.
 */

public class HomeOthersRollAdapter extends LoopPagerAdapter {
    LinkedList<View> mCaches = new LinkedList<View>();//缓存池子
    List<HomeBean.OBean.ProductBestBean> datas = new ArrayList();

    public HomeOthersRollAdapter(RollPagerView viewPager) {
        super(viewPager);
    }

    @Override
    public View getView(ViewGroup container, int position) {
        Holder viewHolder = null;
        View convertView = null;
        convertView = LayoutInflater.from(container.getContext()).inflate(R.layout.rollpager_group, null);
        viewHolder = new Holder();
        viewHolder.initView(convertView);
        viewHolder.setData(datas.get(position));
//        container.addView(convertView);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListenner != null) {
                    itemClickListenner.onclick(datas.get(position));
                }
            }
        });
        return convertView;
    }


    public void refreshData(List<HomeBean.OBean.ProductBestBean> resIds) {
        if (this.datas.size() > 0) {
            this.datas.clear();
        }
        this.datas.addAll(resIds == null ? new ArrayList() : resIds);
        notifyDataSetChanged();
    }


    @Override
    public int getRealCount() {
        return datas == null ? 0 : datas.size();
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        if (mCaches.size() > 0) {
            mCaches.clear();
        }
        container.removeView((View) object);
        mCaches.add((View) object);
    }

    public class Holder {
        public ImageView imageView;
        public TextView title;
        public TextView name;
        public TextView money;
        private View itemView;

        public void initView(View itemView) {
            this.itemView = itemView;
            imageView = itemView.findViewById(R.id.imagview);
            title = itemView.findViewById(R.id.textViewTitle);
            name = itemView.findViewById(R.id.textViewPhone);
            money = itemView.findViewById(R.id.textViewmoney);
        }

        public void setData(HomeBean.OBean.ProductBestBean data) {
            Glide.with(itemView.getContext()).load(data.getImage()).into(imageView);
            title.setText(data.getStore_name());
            name.setText(data.getStore_info());
            money.setText(ConstantString.RMB_SYMBOL + data.getPrice());
        }
    }

    ItemClickListenner itemClickListenner;

    public void setItemClickListenner(ItemClickListenner itemClickListenner) {
        this.itemClickListenner = itemClickListenner;
    }
}
