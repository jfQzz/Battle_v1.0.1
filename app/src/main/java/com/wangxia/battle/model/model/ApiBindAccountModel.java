package com.wangxia.battle.model.model;

import android.text.TextUtils;

import com.wangxia.battle.model.IModel;
import com.wangxia.battle.model.bean.SuccessBean;
import com.wangxia.battle.model.http.UrlConstant;
import com.wangxia.battle.presenter.callback.ICallback;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.GsonUtil;
import com.wangxia.battle.util.HttpUtil;
import com.wangxia.battle.util.LogUtil;
import com.wangxia.battle.util.UserUtil;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * 第三方绑定当前账户
 */

public class ApiBindAccountModel implements IModel {
    public void queryList(final int type, String args, int pageNo, final ICallback iCallback) {
        String[] split = args.split(Constant.string.COMMA_SEPARATOR);
        Map<String,String> param = new HashMap<>(split.length);
        param.put("type",split[0]);
        param.put("uid",split[1]);
        param.put("name",split[2]);
        param.put("pic",split[3]);
        param.putAll(UserUtil.getCookiesParams());
        HttpUtil.getHttpUtils().get().url(UrlConstant.DOMAIN_NAME + UrlConstant.APP_NAME + UrlConstant.API_BIND_ACCOUNT_NOW)
                .params(UserUtil.getSignParams(param))
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
                            iCallback.success(GsonUtil.getGson().fromJson(response, SuccessBean.class), type);
                        }else {
                            iCallback.fail();
                        }
                    }
                });
    }

}
