package com.wangxia.battle.model.model;

import android.text.TextUtils;

import com.wangxia.battle.model.IModel;
import com.wangxia.battle.model.bean.AppUpdateBean;
import com.wangxia.battle.model.http.UrlConstant;
import com.wangxia.battle.presenter.callback.ICallback;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.GsonUtil;
import com.wangxia.battle.util.HttpUtil;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 获取app的下载地址更新日志，大小等相关信息
 */

public class CheckAppModel implements IModel {
    public void queryList(int id ,String args,int pageNo, final ICallback iCallback){
        HttpUtil.getHttpUtils().get(). url(UrlConstant.DOMAIN_NAME +UrlConstant.UPDATE_APP)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if(!TextUtils.isEmpty(response)){
                            iCallback.success(GsonUtil.getGson().fromJson(response, AppUpdateBean.class), Constant.number.ZERO);
                        }
                    }
                });
    }
}
