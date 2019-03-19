package com.qixiu.xiaodiandi.utils;

import java.math.RoundingMode;
import java.text.NumberFormat;

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


    public static String formatDouble3(double d) {
        NumberFormat nf = NumberFormat.getNumberInstance();

        // 保留两位小数
        nf.setMaximumFractionDigits(2);


        // 如果不需要四舍五入，可以使用RoundingMode.DOWN
        nf.setRoundingMode(RoundingMode.UP);

        return nf.format(d);
    }

    public static String formatDouble3(double d, int lenght) {
        NumberFormat nf = NumberFormat.getNumberInstance();

        // 保留两位小数
        nf.setMaximumFractionDigits(lenght);


        // 如果不需要四舍五入，可以使用RoundingMode.DOWN
        nf.setRoundingMode(RoundingMode.UP);

        return nf.format(d);
    }

}
