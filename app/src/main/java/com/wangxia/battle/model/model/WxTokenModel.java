package com.wangxia.battle.model.model;

import android.text.TextUtils;

import com.wangxia.battle.model.IModel;
import com.wangxia.battle.model.http.UrlConstant;
import com.wangxia.battle.presenter.callback.ICallback;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.HttpUtil;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 微信登录
 */

public class WxTokenModel implements IModel {
    public void queryList(final int type, String args, int pageNo, final ICallback iCallback) {
        if (TextUtils.isEmpty(args)) {
            return;
        }
        HttpUtil.getHttpUtils().get().url(UrlConstant.WX_TOKEN)
                .addParams("appid", Constant.platformID.LOGIN_WX_ID)
                .addParams("secret", Constant.platformID.LOGIN_WX_SECRET)
                .addParams("code", args)
                .addParams("grant_type", Constant.platformID.LOGIN_WX_GRANT_TYPE)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        call.cancel();
                        e.printStackTrace();
                        iCallback.error();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (!TextUtils.isEmpty(response)) {
                            iCallback.success(response, type);
                        }else iCallback.fail();
                    }
                });
    }
}
