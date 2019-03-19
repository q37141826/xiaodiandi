package com.qixiu.xiaodiandi.utils;

public class SizeUtils {
    public  static int windowHeight;
    public  static int windowWith;
    public static boolean isBarUp;

    public static boolean isIsBarUp() {
        return isBarUp;
    }

    public static void setIsBarUp(boolean isBarUp) {
        SizeUtils.isBarUp = isBarUp;
    }

    public static int getWindowHeight() {
        return windowHeight;
    }

    public static void setWindowHeight(int windowHeight) {
        SizeUtils.windowHeight = windowHeight;
    }

    public static int getWindowWith() {
        return windowWith;
    }

    public static void setWindowWith(int windowWith) {
        SizeUtils.windowWith = windowWith;
    }
}
