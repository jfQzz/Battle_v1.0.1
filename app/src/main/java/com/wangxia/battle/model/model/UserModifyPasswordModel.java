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

import okhttp3.Call;

/**
 * 用户密码修改
 */

public class UserModifyPasswordModel implements IModel {
    public void queryList(final int type, String args, int pageNo, final ICallback iCallback) {
        if(TextUtils.isEmpty(args)){
            return;
        }
        String[] split = args.split(Constant.string.COMMA_SEPARATOR);
        HttpUtil.getHttpUtils().post().url(UrlConstant.DOMAIN_NAME + UrlConstant.APP_NAME + UrlConstant.USER_MODIFY_PASSWORD)
                .params(UserUtil.getCookiesParams())
                .addParams("passa",split[0])
                .addParams("pass",split[1])
                .addParams("passs",split[2])
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
