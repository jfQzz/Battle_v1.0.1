package com.wangxia.battle.util;

import android.content.Context;
import android.text.TextUtils;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.wangxia.battle.R;
import com.wangxia.battle.callback.ICallbackWxInfo;
import com.wangxia.battle.globe.App;
import com.wangxia.battle.model.bean.UserInfo;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Cookie;
import okhttp3.Headers;
import okhttp3.HttpUrl;

/**
 * Created by 昝奥博 on 2017/11/18 0018
 * Email:18772833900@163.com
 * Explain：用户登录，退出，相关的操作
 */
public class UserUtil {

    public static ICallbackWxInfo iCallbackWxInfo;

    public static void login(Context context) {
        SpUtil.putBoolean(context, Constant.userInfo.USER_STATE, true);
    }


    public static void exit(Context context) {
        SpUtil.putBoolean(context, Constant.userInfo.USER_STATE, false);
        clearUserInfo(context);
        clearCookies();
        clearMemory();
//        EMClient.getInstance().logout(true);
    }

    private static void clearMemory() {
        Fresco.getImagePipeline().clearMemoryCaches();
        Fresco.getImagePipeline().clearDiskCaches();
        Fresco.getImagePipeline().clearCaches();
        //数据库最好也清空一下
    }

    //用户登录的方式 0 账户 1 QQ 2 微信 3 新浪
    public static boolean getUserState(Context context) {
        return SpUtil.getBoolean(context, Constant.userInfo.USER_STATE, false);
    }


    public static void saveCookies(HttpUrl url, Headers headers) {
        for (Cookie cook : Cookie.parseAll(url, headers)) {
            LogUtil.i("cookie     " + cook.name() + "                   " + cook.value());
            if (TextUtils.equals(Constant.userInfo.USER_COOKIES_TAG, cook.name())) {
                SpUtil.putString(App.context, Constant.userInfo.USER_COOKIES, cook.value());
                break;
            }
        }
    }

    public static Map<String, String> getCookiesParams() {
        String cookies = getCookies();
        if (TextUtils.isEmpty(cookies)) {
            return null;
        }
        int index = cookies.indexOf("&Qcka=");
        String qckb = cookies.substring(5, index);
        String qcka = cookies.substring(index + 6, cookies.length());
        Map<String, String> params = new HashMap<>(2);
        params.put("Qcka", qcka);
        params.put("Qckb", qckb);
        return params;
    }

    public static String getCookies() {
        return SpUtil.getString(App.context, Constant.userInfo.USER_COOKIES, null);
    }

    public static void clearCookies() {
        SpUtil.putString(App.context, Constant.userInfo.USER_COOKIES, null);
    }

    public static void saveUerInfo(Context context, UserInfo userInfo) {
        SpUtil.putString(context, Constant.userInfo.USER_NICK, userInfo.getUsernike());
        SpUtil.putString(context, Constant.userInfo.USER_ICON, userInfo.getUserpic());
        SpUtil.putString(context, Constant.userInfo.USER_ID, userInfo.getUserid());
        SpUtil.putString(context, Constant.userInfo.USER_TYPE, userInfo.getIspass());
        SpUtil.putString(context, Constant.userInfo.USER_GENDER, TextUtils.equals("0", userInfo.getUsersex()) ? "男" : "女");
        SpUtil.putString(context, Constant.userInfo.USER_LOGIN_TIME, context.getString(R.string.last_login_time) + userInfo.getUserretime());
    }


    public static void clearUserInfo(Context context) {
        SpUtil.putString(context, Constant.userInfo.USER_NICK, null);
        SpUtil.putString(context, Constant.userInfo.USER_ICON, null);
        SpUtil.putString(context, Constant.userInfo.USER_ID, null);
        SpUtil.putString(context, Constant.userInfo.USER_TYPE, null);
        SpUtil.putString(context, Constant.userInfo.USER_GENDER, null);
        SpUtil.putString(context, Constant.userInfo.USER_LOGIN_TIME, null);
    }

    public static void setGender(Context context, String gender) {
        if (!TextUtils.isEmpty(gender) && (TextUtils.equals("男", gender) || TextUtils.equals("女", gender))) {
            SpUtil.putString(context, Constant.userInfo.USER_GENDER, gender);
        }
    }

    public static void modifyNick(Context context, String nick) {
        SpUtil.putString(context, Constant.userInfo.USER_NICK, nick);
    }

    public static void modifyGender(Context context, String gender) {
        SpUtil.putString(context, Constant.userInfo.USER_NICK, gender);
    }

    public static String getGender(Context context){
        return SpUtil.getString(context, Constant.userInfo.USER_GENDER, "男");
    }



    public static boolean canModifyPass(Context context) {
        return TextUtils.equals(SpUtil.getString(context, Constant.userInfo.USER_TYPE, "True"), "True") ? true : false;
    }

    public static void modifyPass(Context context) {
        //如果是第一次修改改变属性，否则不用
        if (TextUtils.equals(SpUtil.getString(context, Constant.userInfo.USER_TYPE, "True"), "False"))
            SpUtil.putString(context, Constant.userInfo.USER_TYPE, "True");
    }

    /**
     *
     */
    public static Map<String, String> getSignParams(Map<String, String> arg) {
        String[] sign = SignUtil.getSign();
        Map<String, String> params = new HashMap<>();
        params.put("int", sign[0]);
        params.put("str", sign[1]);
        params.put("sign", sign[2]);
        params.putAll(arg);
        return params;
    }

    /**
     * 签名
     *
     * @return
     */
    public static Map<String, String> getSign() {
        String[] sign = SignUtil.getSign();
        Map<String, String> params = new HashMap<>(3);
        params.put("int", sign[0]);
        params.put("str", sign[1]);
        params.put("sign", sign[2]);
        return params;
    }


    public static String getSingStr() {
        StringBuilder stringBuilder = new StringBuilder();
        String[] sign = SignUtil.getSign();
        stringBuilder.append("&int=");
        stringBuilder.append(sign[0]);
        stringBuilder.append("&str=");
        stringBuilder.append(sign[1]);
        stringBuilder.append("&sign=");
        stringBuilder.append(sign[2]);
        return stringBuilder.toString();
    }

    public static void setWxCallback(ICallbackWxInfo ic) {
        if (null != ic) {
            iCallbackWxInfo = ic;
        }
    }

    public static void setWxInfo(String result) {
        if (null != iCallbackWxInfo) {
            iCallbackWxInfo.successGetInfo(result);
        } else {
            throw new NullPointerException("ICallbackWxInfo is null");
        }
    }
}
