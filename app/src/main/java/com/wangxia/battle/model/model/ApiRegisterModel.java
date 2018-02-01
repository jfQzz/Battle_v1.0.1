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

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 注册
 */
public class ApiRegisterModel implements IModel {
    public void queryList( final int type, String args,  int pageNo, final ICallback iCallback) {
        if (TextUtils.isEmpty(args) || !args.contains(Constant.string.COMMA_SEPARATOR) || Constant.number.FORE != args.split(Constant.string.COMMA_SEPARATOR).length) {
            return;
        }

        String[] split = args.split(Constant.string.COMMA_SEPARATOR);
        Map<String,String> param = new HashMap<>();
        param.put("type",split[0]);
        param.put("uid",split[1]);
        param.put("name",split[2]);
        param.put("pic",split[3]);
        String url = UrlConstant.DOMAIN_NAME + UrlConstant.APP_NAME + UrlConstant.API_REGISTER+"&type="+split[0]+"&uid="+split[1]+"&name="+split[2]+"&pic="+split[3]+UserUtil.getSingStr();
        final Request build = new Request.Builder().get().url(url).build();
        LogUtil.i("新浪           "+url);
        HttpUtil.getHttpUtils().get().url(UrlConstant.DOMAIN_NAME + UrlConstant.APP_NAME + UrlConstant.API_REGISTER)
                .params(UserUtil.getSignParams(param)).build().execute(new com.zhy.http.okhttp.callback.Callback() {
            @Override
            public Object parseNetworkResponse(Response response, int id) throws Exception {
                String result = response.body().string();
                UserUtil.saveCookies(build.url(), response.headers());
                LogUtil.i(response.headers().toString()+"       第三方登录       "+result+"           "+UserUtil.getCookies());
                if (!TextUtils.isEmpty(result)) {
                    iCallback.success(GsonUtil.getGson().fromJson(result, SuccessBean.class), type);
                }else {
                    iCallback.fail();
                }
                return response;
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                iCallback.error();
                call.cancel();
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
