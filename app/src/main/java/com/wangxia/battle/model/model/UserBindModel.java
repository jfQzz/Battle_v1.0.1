package com.wangxia.battle.model.model;

import android.text.TextUtils;

import com.wangxia.battle.model.IModel;
import com.wangxia.battle.model.bean.SuccessBean;
import com.wangxia.battle.model.bean.UserBindBean;
import com.wangxia.battle.model.http.UrlConstant;
import com.wangxia.battle.presenter.callback.ICallback;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.GsonUtil;
import com.wangxia.battle.util.HttpUtil;
import com.wangxia.battle.util.LogUtil;
import com.wangxia.battle.util.UserUtil;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.callback.Callback;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 绑定
 */

public class UserBindModel implements IModel {
    public void queryList(final int type, String args, final int pageNo, final ICallback iCallback) {
        if (TextUtils.isEmpty(args)) {
            return;
        }
        GetBuilder getBuilder = null;
        String[] split = args.split(Constant.string.COMMA_SEPARATOR);

        Request build = null;
        Map<String,String> param =null;
        switch (pageNo) {
            //绑定手机号
            case Constant.number.ZERO:
                getBuilder = HttpUtil.getHttpUtils().get().url(UrlConstant.DOMAIN_NAME + UrlConstant.APP_NAME + UrlConstant.USER_PHONE_BIND)
                        .params(UserUtil.getCookiesParams())
                        .addParams("name", split[0])
                        .addParams("code", split[1]);
                break;
            //api登录绑定以前的手机号账户
            case Constant.number.ONE:
                param = new HashMap<>(6);
                param.put("type",split[0]);
                param.put("uid",split[1]);
                param.put("name",split[2]);
                param.put("pic",split[3]);
                param.put("user",split[4]);
                param.put("code",split[5]);
                getBuilder = HttpUtil.getHttpUtils().get().url(UrlConstant.DOMAIN_NAME + UrlConstant.APP_NAME + UrlConstant.API_LOGIN_BIND_PHONE_CONFIRM)
                        .params(UserUtil.getSignParams(param));

                break;
            //api登录绑定以前的api账户
            case Constant.number.TWO:
                param = new HashMap<>(6);
                param.put("type",split[0]);
                param.put("uid",split[1]);
                param.put("name",split[2]);
                param.put("pic",split[3]);
                param.put("user",split[4]);
                param.put("pass",split[5]);
                getBuilder = HttpUtil.getHttpUtils().get().url(UrlConstant.DOMAIN_NAME + UrlConstant.APP_NAME + UrlConstant.API_LOGIN_BIND_ACCOUNT_CONFIRM)
                        .params(UserUtil.getSignParams(param));
                break;
        }
        if (Constant.number.ZERO != pageNo) {
            build = new Request.Builder().get().url(UrlConstant.DOMAIN_NAME + UrlConstant.APP_NAME + UrlConstant.LOGIN_URL).header("name", split[0]).header("pass", split[1]).build();

        }
        final Request finalBuild = build;
        getBuilder
                .build()
                .execute(new Callback() {
                             @Override
                             public Object parseNetworkResponse(Response response, int id) throws Exception {
                                 String result = response.body().string();
                                 LogUtil.i("        "+result);
                                 if (!TextUtils.isEmpty(result)) {
                                     if (Constant.number.ZERO != pageNo) {
                                         UserUtil.saveCookies(finalBuild.url(), response.headers());
                                         iCallback.success(GsonUtil.getGson().fromJson(result, SuccessBean.class), type);
                                         LogUtil.i("cook     "+UserUtil.getCookies());
                                     } else
                                         iCallback.success(GsonUtil.getGson().fromJson(result, UserBindBean.class), type);
                                 }else iCallback.fail();
                                 return response;
                             }

                             @Override
                             public void onError(Call call, Exception e, int id) {
                                 call.cancel();
                                 e.printStackTrace();
                                 iCallback.error();

                             }

                             @Override
                             public void onResponse(Object response, int id) {
                                 LogUtil.i("---             " + response.toString());

                             }
                         }

                );
    }


}
