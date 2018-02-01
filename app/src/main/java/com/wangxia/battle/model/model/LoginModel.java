package com.wangxia.battle.model.model;

import android.text.TextUtils;

import com.wangxia.battle.model.IModel;
import com.wangxia.battle.model.bean.SuccessBean;
import com.wangxia.battle.model.http.UrlConstant;
import com.wangxia.battle.presenter.callback.ICallback;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.GsonUtil;
import com.wangxia.battle.util.HttpUtil;
import com.wangxia.battle.util.UserUtil;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 登录
 */
public class LoginModel implements IModel {
    public void queryList(final int type, String args, int pageNo, final ICallback iCallback) {
        if (TextUtils.isEmpty(args) || !args.contains(Constant.string.COMMA_SEPARATOR) || Constant.number.TWO != args.split(Constant.string.COMMA_SEPARATOR).length) {
            return;
        }
        String[] split = args.split(Constant.string.COMMA_SEPARATOR);
        final Request build = new Request.Builder().get().url(UrlConstant.DOMAIN_NAME + UrlConstant.APP_NAME + UrlConstant.LOGIN_URL).header("name", split[0]).header("pass", split[1]).build();
        HttpUtil.getHttpUtils().get().url(UrlConstant.DOMAIN_NAME + UrlConstant.APP_NAME + UrlConstant.LOGIN_URL).addParams("name", split[0]).addParams("pass", split[1]).build().execute(new com.zhy.http.okhttp.callback.Callback() {
            @Override
            public Object parseNetworkResponse(Response response, int id) throws Exception {
                String result = response.body().string();
                UserUtil.saveCookies(build.url(), response.headers());
                if (!TextUtils.isEmpty(result)) {
                    iCallback.success(GsonUtil.getGson().fromJson(result, SuccessBean.class), type);
                }else {
                    iCallback.fail();
                }
                return response;
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                call.cancel();
                iCallback.error();
                e.printStackTrace();
            }

            @Override
            public void onResponse(Object re, int id) {
                if (null == re) {
                    return;
                }
                Response response = (Response) re;
                String header = response.headers().toString();
                response.close();
            }
        });
    }
}
