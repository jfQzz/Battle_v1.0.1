package com.wangxia.battle.model.model;

import com.wangxia.battle.model.IModel;
import com.wangxia.battle.model.bean.SuccessBean;
import com.wangxia.battle.model.http.UrlConstant;
import com.wangxia.battle.presenter.callback.ICallback;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.GsonUtil;
import com.wangxia.battle.util.HttpUtil;
import com.wangxia.battle.util.TxtFormatUtil;
import com.wangxia.battle.util.UserUtil;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by 昝奥博 on 2017/11/17 0017
 * Email:18772833900@163.com
 * Explain：修改用户信息
 */
public class UserModifyInfoModel implements IModel {
    @Override
    public void queryList(final int type, String arg, int pageNo, final ICallback iCallback) {
        PostFormBuilder params = HttpUtil.getHttpUtils().post().url(UrlConstant.DOMAIN_NAME + UrlConstant.APP_NAME + UrlConstant.USER_MODIFY_NICK_OR_GENDER)
                .params(UserUtil.getCookiesParams());
        switch (pageNo){
            case Constant.number.ZERO:
                params.addParams("nike", TxtFormatUtil.escape(arg));
                break;
            case Constant.number.ONE:
                params.addParams("sex",arg);
                break;
        }
                params
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
                        if(null != response){
                            iCallback.success(GsonUtil.getGson().fromJson(response, SuccessBean.class),type);
                        }else iCallback.fail();
                    }
                });
    }
}
