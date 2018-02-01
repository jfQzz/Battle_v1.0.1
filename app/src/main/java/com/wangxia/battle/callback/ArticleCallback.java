package com.wangxia.battle.callback;

import android.text.TextUtils;

import com.wangxia.battle.globe.App;
import com.wangxia.battle.model.bean.ArticleList;
import com.wangxia.battle.presenter.callback.ICallback;
import com.wangxia.battle.util.GsonUtil;
import com.wangxia.battle.util.LogUtil;
import com.wangxia.battle.util.RequestExceptionHandle;
import com.wangxia.battle.util.SpUtil;

import okhttp3.Call;

/**
 * Created by 昝奥博 on 2018/1/4 0004
 * Email:18772833900@163.com
 * Explain：
 */
public class ArticleCallback extends IBaseCacheCallback {
    private ICallback iCallback;
    private int type;

    public ArticleCallback(ICallback iCallback, int type) {
        super();
        this.iCallback = iCallback;
        this.type = type;
    }

    @Override
    public void onResponse(String response, int id) {
        if (!TextUtils.isEmpty(response)) {
            iCallback.success(GsonUtil.getGson().fromJson(response, ArticleList.class), type);
        } else {
            iCallback.fail();
        }
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        e.printStackTrace();
        LogUtil.i("------   "+e.getMessage()+"----      "+e.getLocalizedMessage());
        String cache = SpUtil.getString(App.context, call.request().url().toString(), null);
        //网络问题拿缓存
        if ( !TextUtils.isEmpty(cache)) {
            iCallback.success(GsonUtil.getGson().fromJson(cache, ArticleList.class), type);
        }else {
            iCallback.error();
            RequestExceptionHandle.handleException(e);
        }
        call.cancel();
        e.printStackTrace();
    }
}
