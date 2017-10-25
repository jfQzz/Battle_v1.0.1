package com.wangxia.battle.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.wangxia.battle.globe.App;

import java.util.Set;

/**
 *
 *sp存值传值工具类,编码格式转换工具类
 */
public class SpUtil {
    private static final String SHARE_PREFS_NAME = "config";
    public static void putString(Context ctx, String key, String value) {
        if (ctx == null){
            ctx = App.context;
        }
        SharedPreferences pref = null;
        try {
            pref = ctx.getSharedPreferences(SHARE_PREFS_NAME,
                    Context.MODE_PRIVATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //这里有极小可能出错
        if (pref == null || pref.edit() == null){
            return;
        }
        try {
            pref.edit().putString(key, value).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getString(Context ctx, String key, String defaultValue) {
        if (ctx == null){
            ctx = App.context;
        }
        SharedPreferences pref = ctx.getSharedPreferences(SHARE_PREFS_NAME,
                Context.MODE_PRIVATE);

        return pref.getString(key, defaultValue);
    }

    public static void putBoolean(Context ctx, String key, boolean value) {
        if (ctx == null){
            ctx = App.context;
        }
        SharedPreferences pref = ctx.getSharedPreferences(SHARE_PREFS_NAME,
                Context.MODE_PRIVATE);

        try {
            pref.edit().putBoolean(key, value).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean getBoolean(Context ctx, String key,
                                     boolean defaultValue) {
        if (ctx == null){
            ctx = App.context;
        }
        SharedPreferences pref = ctx.getSharedPreferences(SHARE_PREFS_NAME,
                Context.MODE_PRIVATE);

        return pref.getBoolean(key, defaultValue);
    }

    public static void putInt(Context ctx, String key, int value) {
        if (ctx == null){
            ctx = App.context;
        }
        SharedPreferences pref = ctx.getSharedPreferences(SHARE_PREFS_NAME,
                Context.MODE_PRIVATE);

        try {
            pref.edit().putInt(key, value).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getInt(Context ctx, String key, int defaultValue) {
        if (ctx == null){
            ctx = App.context;
        }
        SharedPreferences pref = ctx.getSharedPreferences(SHARE_PREFS_NAME,
                Context.MODE_PRIVATE);

        return pref.getInt(key, defaultValue);
    }

    public static void putLong(Context ctx, String key, long value) {
        if (ctx == null){
            ctx = App.context;
        }
        SharedPreferences pref = ctx.getSharedPreferences(SHARE_PREFS_NAME,
                Context.MODE_PRIVATE);

        try {
            pref.edit().putLong(key, value).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static long getLong(Context ctx, String key, int defaultValue) {
        if (ctx == null){
            ctx = App.context;
        }
        SharedPreferences pref = ctx.getSharedPreferences(SHARE_PREFS_NAME,
                Context.MODE_PRIVATE);

        return pref.getLong(key, defaultValue);
    }

    public static void putSet(Context ctx, String key, Set<String> set) {
        if (ctx == null){
            ctx = App.context;
        }
        SharedPreferences pref = ctx.getSharedPreferences(SHARE_PREFS_NAME,
                Context.MODE_PRIVATE);

        try {
            pref.edit().putStringSet(key, set).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Set<String> getSet(Context ctx, String key, Set<String> set) {
        if (ctx == null){
            ctx = App.context;
        }
        SharedPreferences pref = ctx.getSharedPreferences(SHARE_PREFS_NAME,
                Context.MODE_PRIVATE);

        return pref.getStringSet(key, set);
    }

}
