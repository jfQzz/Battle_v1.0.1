package com.wangxia.battle.model.model;

import android.text.TextUtils;

import com.wangxia.battle.model.IModel;
import com.wangxia.battle.model.bean.SuccessBean;
import com.wangxia.battle.model.http.UrlConstant;
import com.wangxia.battle.presenter.callback.ICallback;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.GsonUtil;
import com.wangxia.battle.util.HttpUtil;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 1.注册 2.找回密码
 */
public class RegisterModel implements IModel {
    public void queryList(final int type, String args, int pageNo, final ICallback iCallback) {
        if (TextUtils.isEmpty(args) || !args.contains(Constant.string.COMMA_SEPARATOR)) {
            return;
        }
        String[] split = args.split(Constant.string.COMMA_SEPARATOR);
        String url = null;
        switch (pageNo){
            case Constant.number.ZERO:
                url = UrlConstant.REGISTER_ACCOUNT;
                break;
            case Constant.number.ONE:
                url = UrlConstant.FIND_PASSWORD;
                break;
        }
//        final Request build = new Request.Builder().get().url(UrlConstant.DOMAIN_NAME + UrlConstant.APP_NAME + url).header("code", split[0]).header("name", split[1]).header("pass", split[2]).build();
        HttpUtil.getHttpUtils().get().url(UrlConstant.DOMAIN_NAME + UrlConstant.APP_NAME + url)
                .addParams("code", split[0])
                .addParams("name", split[1])
                .addParams("pass", split[2])
                .addParams("passs",split[2])
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int id) throws Exception {
                        String result = response.body().string();
//                        if(Constant.number.ZERO == type) UserUtil.saveCookies(build.url(), response.headers());
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
                        e.printStackTrace();
                        iCallback.error();
                    }

                    @Override
                    public void onResponse(Object response, int id) {

                    }
                });
    }
}
