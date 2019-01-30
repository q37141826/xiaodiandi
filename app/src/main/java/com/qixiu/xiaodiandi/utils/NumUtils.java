package com.qixiu.xiaodiandi.utils;

public class NumUtils {

    public static int getInterger(String str) {
        int num = 0;
        try {
            num = Integer.parseInt(str);

        } catch (Exception e) {
        }
        return num;
    }


    public static double getDouble(String str) {
        double num = 0;
        try {
            num = Double.parseDouble(str);
        } catch (Exception e) {
        }
        return num;
    }
}
