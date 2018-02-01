package com.wangxia.battle.model.model;

import android.text.TextUtils;

import com.wangxia.battle.model.IModel;
import com.wangxia.battle.model.bean.SuccessBean;
import com.wangxia.battle.model.http.UrlConstant;
import com.wangxia.battle.presenter.callback.ICallback;
import com.wangxia.battle.util.GsonUtil;
import com.wangxia.battle.util.HttpUtil;
import com.wangxia.battle.util.LogUtil;
import com.wangxia.battle.util.UserUtil;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 获取用户解除绑定的信息
 */

public class UserUnBindAccountModel implements IModel {
    public void queryList(final int type, String args, int pageNo, final ICallback iCallback) {
        HttpUtil.getHttpUtils().post().url(UrlConstant.DOMAIN_NAME + UrlConstant.APP_NAME + UrlConstant.USER_OTHER_ENTER_UNBIN)
                .params(UserUtil.getCookiesParams())
                .addParams("type",args)
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
                        LogUtil.i("---             "+response);
                        if (!TextUtils.isEmpty(response)) {
                            iCallback.success(GsonUtil.getGson().fromJson(response, SuccessBean.class), type);
                        }else iCallback.fail();
                    }
                });
    }

}
