package com.qixiu.qixiu.utils.html_utils;

import android.graphics.drawable.Drawable;

import java.util.HashMap;
import java.util.Map;

public class DrawbleCacheUtils {
    static Map<String, Drawable> map;
    static DrawbleCacheUtils drawbleCacheUtils;

    public static  DrawbleCacheUtils getInstance() {
        if (drawbleCacheUtils == null) {
            drawbleCacheUtils = new DrawbleCacheUtils();
            map = new HashMap<>();
        }
        return drawbleCacheUtils;
    }

    public Drawable getCache(String key) {
        return map.get(key);
    }

    public void saveCache(String key, Drawable drawable) {
        map.put(key, drawable);
    }

}
