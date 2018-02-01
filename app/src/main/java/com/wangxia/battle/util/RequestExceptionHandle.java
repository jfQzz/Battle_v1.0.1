package com.wangxia.battle.util;

import android.media.DeniedByServerException;
import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.wangxia.battle.globe.App;

import org.apache.http.HttpException;
import org.json.JSONException;

import java.net.ConnectException;

import javax.net.ssl.SSLHandshakeException;

/**
 * @author ZABone
 * @data 2018/1/31 0031
 * @Email:darryy@foxmail.com
 * @Explain：捕获网络请求失败的异常
 */
public class RequestExceptionHandle {
    //常见的网络异常返回码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static void handleException(Exception e) {
        String message = "网络错误 ";
        //网络错误
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            if (httpException.getMessage().contains(String.valueOf(UNAUTHORIZED))) {
                message += UNAUTHORIZED;
            } else if (httpException.getMessage().contains(String.valueOf(FORBIDDEN))) {
                message += FORBIDDEN;
            } else if (httpException.getMessage().contains(String.valueOf(NOT_FOUND))) {
                message += NOT_FOUND;
            } else if (httpException.getMessage().contains(String.valueOf(REQUEST_TIMEOUT))) {
                message += REQUEST_TIMEOUT;
            } else if (httpException.getMessage().contains(String.valueOf(INTERNAL_SERVER_ERROR))) {
                message += INTERNAL_SERVER_ERROR;
            } else if (httpException.getMessage().contains(String.valueOf(BAD_GATEWAY))) {
                message += BAD_GATEWAY;
            } else if (httpException.getMessage().contains(String.valueOf(SERVICE_UNAVAILABLE))) {
                message += SERVICE_UNAVAILABLE;
            } else if (httpException.getMessage().contains(String.valueOf(GATEWAY_TIMEOUT))) {
                message += GATEWAY_TIMEOUT;
            }

            //解析错误
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            message = "解析错误";
            //网络错误连接失败
        } else if (e instanceof ConnectException) {
            message = "网络连接失败";
            //网络错误连接超时
        } else if (e instanceof DeniedByServerException) {
            message = "网络连接超时";
            //证书错误
        } else if (e instanceof SSLHandshakeException) {
            message = "SSL证书错误";
            //未知错误
        } else {
            message = "未知错误";
        }
        MyToast.s(App.context,message);

    }


}
