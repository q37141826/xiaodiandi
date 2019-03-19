package com.qixiu.xiaodiandi.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qixiu.xiaodiandi.R;

public class GlideUtils {

    public static void loadImage(String path, ImageView imageView, Context context) {
        Glide.with(context).load(path)
                .error(R.mipmap.mine_head)
                .into(imageView);
    }

}
