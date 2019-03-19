package com.qixiu.xiaodiandi.utils;

public class DimenUtils {
    public static int windowWith;

    public static void setWindowWith(int windowWith) {
        DimenUtils.windowWith = windowWith;
    }

    public static float getGotoDimen(int dp) {
        return (float) (windowWith * dp/ 1080);
    }

}
