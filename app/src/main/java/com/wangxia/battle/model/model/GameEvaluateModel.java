package com.wangxia.battle.model.model;

import android.text.TextUtils;

import com.wangxia.battle.model.IModel;
import com.wangxia.battle.model.bean.EvaluateBean;
import com.wangxia.battle.model.http.UrlConstant;
import com.wangxia.battle.presenter.callback.ICallback;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.GsonUtil;
import com.wangxia.battle.util.HttpUtil;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 获取传奇游戏
 */

public class GameEvaluateModel implements IModel {
    public void queryList(int id ,String args,int pageNo, final ICallback iCallback){
        HttpUtil.getHttpUtils().get(). url(UrlConstant.DOMAIN_NAME +UrlConstant.GAME_EVALUATE)
                .addParams("id", String.valueOf(id))
                .addParams("page", String.valueOf(pageNo))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        if(!TextUtils.isEmpty(response)){
                            iCallback.success(GsonUtil.getGson().fromJson(response, EvaluateBean.class), Constant.number.ZERO);
                        }

                    }
                });
    }
}
