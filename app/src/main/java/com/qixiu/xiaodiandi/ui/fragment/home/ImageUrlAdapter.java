package com.qixiu.xiaodiandi.ui.fragment.home;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.qixiu.qixiu.R;
import com.qixiu.wigit.show_dialog.ArshowContextUtil;
import com.qixiu.xiaodiandi.model.comminity.news.NewsHomeBean;
import com.qixiu.xiaodiandi.model.home.HomeBean;

import java.util.ArrayList;
import java.util.List;

public class ImageUrlAdapter extends LoopPagerAdapter {
    List<Object> datas = new ArrayList<>();

    public ImageUrlAdapter(RollPagerView viewPager) {
        super(viewPager);
    }

    public void refreshData(List datas) {
        if (this.datas.size() > 0) {
            this.datas.clear();
        }
        if (datas.size() != 0) {
            if (datas.get(0) instanceof HomeBean.OBean.BannerBean) {
                this.datas.addAll(datas == null ? new ArrayList<HomeBean.OBean.BannerBean>() : datas);
            } else if (datas.get(0) instanceof String) {
                this.datas.addAll(datas == null ? new ArrayList<String>() : datas);
            } else if (datas.get(0) instanceof Integer) {
                this.datas.addAll(datas == null ? new ArrayList<Integer>() : datas);
            } else if (datas.get(0) instanceof NewsHomeBean.OBean.BannerBean) {
                this.datas.addAll(datas == null ? new ArrayList<NewsHomeBean.OBean.BannerBean>() : datas);
            }
        }

        notifyDataSetChanged();
    }


    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        view.setLayoutParams(new ViewGroup.LayoutParams(ArshowContextUtil.getWidthPixels() - 16, (ArshowContextUtil.getWidthPixels() - 16) * 3 / 4));
        if (datas.get(position) instanceof Integer) {
            Glide.with(container.getContext()).load((int) datas.get(position)).error(R.mipmap.ic_launcher).skipMemoryCache(false).into(view);
        } else if (datas.get(position) instanceof HomeBean.OBean.BannerBean) {
            Glide.with(container.getContext()).load(((HomeBean.OBean.BannerBean) datas.get(position)).getPic()).error(R.mipmap.ic_launcher).skipMemoryCache(false).into(view);

        } else if (datas.get(position) instanceof String) {
            Glide.with(container.getContext()).load(datas.get(position)).error(R.mipmap.ic_launcher).skipMemoryCache(false).into(view);
        } else if (datas.get(position) instanceof NewsHomeBean.OBean.BannerBean) {
            NewsHomeBean.OBean.BannerBean bannerBean = (NewsHomeBean.OBean.BannerBean) datas.get(position);
            Glide.with(container.getContext()).load(bannerBean.getPic()).error(R.mipmap.ic_launcher).skipMemoryCache(false).into(view);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListenner != null) {
                    itemClickListenner.onclick(datas.get(position));
                }
            }
        });
        return view;
    }

    @Override
    public int getRealCount() {
        return datas == null ? 0 : datas.size();
    }

    ItemClickListenner itemClickListenner;

    public void setItemClickListenner(ItemClickListenner itemClickListenner) {
        this.itemClickListenner = itemClickListenner;
    }
}