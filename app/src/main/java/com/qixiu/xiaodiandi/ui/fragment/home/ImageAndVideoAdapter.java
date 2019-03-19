package com.qixiu.xiaodiandi.ui.fragment.home;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.qixiu.xiaodiandi.R;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;

public class ImageAndVideoAdapter<T> extends LoopPagerAdapter {
    private String videoUrl;
    String thumb;

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    List<T> datas = new ArrayList<>();
    itemClickListenner itemClickListenner;

    public void setItemClickListenner(ImageAndVideoAdapter.itemClickListenner itemClickListenner) {
        this.itemClickListenner = itemClickListenner;
    }

    public interface itemClickListenner<X> {
        void onItemClick(X data, int position);
    }

    public ImageAndVideoAdapter(RollPagerView viewPager) {
        super(viewPager);
    }

    public void refreshDatas(List datas) {
        if (this.datas.size() > 0) {
            this.datas.clear();
        }
        this.datas.addAll(datas == null ? new ArrayList<T>() : datas);
        notifyDataSetChanged();
    }

    @Override
    public View getView(ViewGroup container, int position) {
        View view = View.inflate(container.getContext(), R.layout.banner_layout, null);
        BannerHolder bannerHolder = new BannerHolder(view, container.getContext());
        bannerHolder.bindView(position, datas.get(position));
        return view;
    }


    @Override
    public int getRealCount() {
        return datas == null ? 0 : datas.size();
    }


    public class BannerHolder {
        ImageView imageView;
        ImageView imageViewPlay;
        Context context;
        View itemView;
        JZVideoPlayerStandard jcplayer;

        public BannerHolder(View itemView, Context context) {
            this.context = context;
            this.itemView = itemView;
            setView(itemView);
        }

        public void setView(View view) {
            imageView = view.findViewById(R.id.imageViewBanner);
            imageViewPlay = view.findViewById(R.id.imageViewPlay);
            jcplayer = view.findViewById(R.id.jcplayer);
        }

        public void bindView(int position, T data) {
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            if (position == 0 && !TextUtils.isEmpty(videoUrl)) {
                jcplayer.setVisibility(View.GONE);//todo 把这个打开
                initJCView(jcplayer, context, videoUrl, thumb);
                imageViewPlay.setVisibility(View.VISIBLE);
            } else {
                jcplayer.setVisibility(View.GONE);
                imageViewPlay.setVisibility(View.GONE);
            }
            if (data instanceof String) {
                Glide.with(context).load(data).into(imageView);
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClickListenner != null) {
                        itemClickListenner.onItemClick(data, position);
                    }
                }
            });
        }
    }

    private void initJCView(JZVideoPlayerStandard jcplayer, Context context, String path, String thumb) {
        if (jcplayer == null) {
            return;
        }
//        jcplayer.setUp(detailsBean.getO().getProduct().getVideo(), JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL);
        jcplayer.setUp(path, JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL);
        Glide.with(context).load(thumb).into(jcplayer.thumbImageView);
        jcplayer.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        jcplayer.battery_level.setVisibility(View.GONE);
    }

}
