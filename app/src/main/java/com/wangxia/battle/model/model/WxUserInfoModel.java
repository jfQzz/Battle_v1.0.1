package com.wangxia.battle.model.model;

import android.text.TextUtils;

import com.wangxia.battle.model.IModel;
import com.wangxia.battle.model.http.UrlConstant;
import com.wangxia.battle.presenter.callback.ICallback;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.HttpUtil;
import com.wangxia.battle.util.LogUtil;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 微信userInfo
 */

public class WxUserInfoModel implements IModel {
    public void queryList(final int type, String args, int pageNo, final ICallback iCallback) {
        if (TextUtils.isEmpty(args) || !args.contains(Constant.string.COMMA_SEPARATOR) || Constant.number.TWO != args.split(Constant.string.COMMA_SEPARATOR).length) {
            return;
        }
        String[] split = args.split(Constant.string.COMMA_SEPARATOR);
        HttpUtil.getHttpUtils().get().url(UrlConstant.WX_USER_INFO)
                .addParams("access_token", split[0])
                .addParams("openid", split[1])
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
                        LogUtil.i(response);
                        if (!TextUtils.isEmpty(response)) {
                            iCallback.success(response, type);
                        }else iCallback.fail();
                    }
                });
    }
}
