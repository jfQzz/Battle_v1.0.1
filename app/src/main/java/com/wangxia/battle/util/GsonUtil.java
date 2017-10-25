package com.wangxia.battle.util;

import com.google.gson.Gson;

/**
 * Created by 昝奥博 on 2017/9/13 0013
 * Email:18772833900@163.com
 * Explain：
 */
public class GsonUtil {
    private static Gson mGson;
    private static GsonUtil mGsonUtil;
    private GsonUtil() {
        mGson = new Gson();
    }

    public static GsonUtil getInstance() {
        if(null == mGsonUtil){
            mGsonUtil = new GsonUtil();
        }
        return mGsonUtil;
    }

    public static Gson getGson(){
        return mGson;
    }
}
