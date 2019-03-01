package com.qixiu.xiaodiandi.utils;

import com.qixiu.xiaodiandi.BuildConfig;

public class ImageUrlUtils {

    public static String getFinnalImageUrl(String orignUrl){
        if(orignUrl.startsWith("http")){
            return orignUrl;
        }else{
            return BuildConfig.BASE_URL+orignUrl;
        }
    }
}
