package com.wangxia.battle.model.model;

import android.text.TextUtils;

import com.wangxia.battle.model.IModel;
import com.wangxia.battle.model.bean.AppInfo;
import com.wangxia.battle.model.http.UrlConstant;
import com.wangxia.battle.presenter.callback.ICallback;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.GsonUtil;
import com.wangxia.battle.util.HttpUtil;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 获取游戏详情，id = 0 获取决战平安京游戏info
 */

public class GameDetailModel implements IModel {
    public void queryList(int id ,String args, final int pageNo, final ICallback iCallback){
        GetBuilder getBuilder = HttpUtil.getHttpUtils().get();
        String url ;
       if(Constant.number.ZERO != id){
           url = UrlConstant.DOMAIN_NAME +UrlConstant.GAME_DETAIL;
           getBuilder .addParams("id", String.valueOf(id));
       }else {
           url = UrlConstant.DOMAIN_NAME +UrlConstant.TARGET_GAME_URL;

       }
        getBuilder. url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if(!TextUtils.isEmpty(response)){
                            iCallback.success(GsonUtil.getGson().fromJson(response, AppInfo.class), pageNo);
                        }

                    }
                });
    }
}
