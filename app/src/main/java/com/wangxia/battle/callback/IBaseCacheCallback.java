package com.wangxia.battle.callback;

import android.text.TextUtils;

import com.wangxia.battle.globe.App;
import com.wangxia.battle.util.NetUtil;
import com.wangxia.battle.util.SpUtil;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;


/**
 * Created by 昝奥博 on 2018/1/4 0004
 * Email:18772833900@163.com
 * Explain：
 */
public abstract class IBaseCacheCallback extends Callback<String>{

    @Override
    public String parseNetworkResponse(Response response, int id) throws Exception {

        if(response.isSuccessful()) {
            String result = response.body().string();
            SpUtil.putString(App.context, response.request().url().toString(), result);
            return result;
        }else {
            //判断是否有网（没网返回缓存，有网络返回失败）
            String cache = SpUtil.getString(App.context, response.request().url().toString(), null);
            if (!NetUtil.isNetAvailable(App.context) && !TextUtils.isEmpty(cache)) {
                Response cacheResponse = response.cacheResponse();
                if (null != cacheResponse) return cacheResponse.body().string();
                return cache;
            }
            return null;
        }
    }

}
