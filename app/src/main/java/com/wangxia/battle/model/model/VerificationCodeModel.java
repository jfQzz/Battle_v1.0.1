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
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 获取注册码
 */

public class VerificationCodeModel implements IModel {
    public void queryList(final int type, String args, int pageNo, final ICallback iCallback) {
        if (TextUtils.isEmpty(args)) {
            return;
        }
        String url = null;
        GetBuilder getBuilder = null;
        switch (type) {
            //注册验证码
            case Constant.number.ZERO:
                url = UrlConstant.GET_REGISTER_CODE;
                getBuilder = HttpUtil.getHttpUtils().get();
                break;
            //找回密码验证码
            case Constant.number.ONE:
                url = UrlConstant.GET_FIND_PASSWORD_CODE;
                getBuilder = HttpUtil.getHttpUtils().get();
                break;
            //绑定手机号码（需要cookies）
            case Constant.number.FORE:
                url = UrlConstant.USER_BIND_PHONE_CODE;
                getBuilder = HttpUtil.getHttpUtils().get();
                getBuilder.params(UserUtil.getCookiesParams());
                break;
            case Constant.number.FIVE:
                url = UrlConstant.API_LOGIN_BIND_OLD_PHONE;
                if (args.contains(Constant.string.COMMA_SEPARATOR)) {
                    String[] split = args.split(Constant.string.COMMA_SEPARATOR);
                    getBuilder = HttpUtil.getHttpUtils().get();
                    getBuilder.addParams("uid", split[1]);
                    args = split[0];
                } else return;
                break;
        }
        getBuilder.url(UrlConstant.DOMAIN_NAME + UrlConstant.APP_NAME + url)
                .addParams("name", args)
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
                        LogUtil.i("验证码        "+response);
                        if (!TextUtils.isEmpty(response)) {
                            iCallback.success(GsonUtil.getGson().fromJson(response, SuccessBean.class), type);
                        }else iCallback.fail();
                    }
                });

    }
}
