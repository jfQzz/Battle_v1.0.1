package com.wangxia.battle.util;

import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by 昝奥博 on 2017/9/13 0013
 * Email:18772833900@163.com
 * Explain：更换网络请求框架简单
 */
public class HttpUtil {
    private static OkHttpUtils mOkHttpUtils;
    private static HttpUtil mHttpUtil;
    private HttpUtil() {
        mOkHttpUtils =  OkHttpUtils.getInstance();
    }

    public static HttpUtil getInstance() {
        if(null == mHttpUtil){
            mHttpUtil = new HttpUtil();
        }
        return mHttpUtil;
    }



    /**
     * 获取httpUtils（默认不包含cookies）
     * 注意事项：每次请求需要自己添加cookies或者情况cookies
     * @return
     */
    public static OkHttpUtils getHttpUtils(){
        return mOkHttpUtils;
    }

}
