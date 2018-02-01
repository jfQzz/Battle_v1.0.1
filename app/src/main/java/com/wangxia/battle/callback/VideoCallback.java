package com.wangxia.battle.callback;

import android.text.TextUtils;

import com.wangxia.battle.globe.App;
import com.wangxia.battle.model.bean.VideoList;
import com.wangxia.battle.presenter.callback.ICallback;
import com.wangxia.battle.util.GsonUtil;
import com.wangxia.battle.util.NetUtil;
import com.wangxia.battle.util.SpUtil;

import okhttp3.Call;

/**
 * Created by 昝奥博 on 2018/1/4 0004
 * Email:18772833900@163.com
 * Explain：
 */
public class VideoCallback extends IBaseCacheCallback {
    private ICallback iCallback;
    private int type;

    public VideoCallback(ICallback iCallback, int type) {
        super();
        this.iCallback = iCallback;
        this.type = type;
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        e.printStackTrace();
        String cache = SpUtil.getString(App.context, call.request().url().toString(), null);
        //网络问题拿缓存
        if (!NetUtil.isNetAvailable(App.context) && !TextUtils.isEmpty(cache)) {
            iCallback.success(GsonUtil.getGson().fromJson(cache, VideoList.class), type);
        }else {
            iCallback.error();
        }
        call.cancel();
        e.printStackTrace();
    }

    @Override
    public void onResponse(String response, int id) {
        if (!TextUtils.isEmpty(response)) {
            iCallback.success(GsonUtil.getGson().fromJson(response, VideoList.class), type);
        } else {
            iCallback.fail();
        }
    }
}
