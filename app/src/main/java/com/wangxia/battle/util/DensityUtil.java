package com.wangxia.battle.util;


import com.wangxia.battle.globe.App;

/**
 * Created by 昝奥博 on 2017/9/12 0012
 * Email:18772833900@163.com
 * Explain：
 */
public final class DensityUtil {

    private static float density = -1F;
    private static int widthPixels = -1;
    private static int heightPixels = -1;

    private DensityUtil() {
    }

    public static float getDensity() {
        if (density <= 0F) {
            density = App.context.getResources().getDisplayMetrics().density;
        }
        return density;
    }

    public static int dip2px(float dpValue) {
        return (int) (dpValue * getDensity() + 0.5F);
    }

    public static int px2dip(float pxValue) {
        return (int) (pxValue / getDensity() + 0.5F);
    }

    public static int getScreenWidth() {
        if (widthPixels <= 0) {
            widthPixels = App.context.getResources().getDisplayMetrics().widthPixels;
        }
        return widthPixels;
    }


    public static int getScreenHeight() {
        if (heightPixels <= 0) {
            heightPixels = App.context.getResources().getDisplayMetrics().heightPixels;
        }
        return heightPixels;
    }
}