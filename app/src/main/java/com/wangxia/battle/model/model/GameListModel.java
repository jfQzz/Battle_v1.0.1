package com.wangxia.battle.model.model;

import android.text.TextUtils;

import com.wangxia.battle.model.IModel;
import com.wangxia.battle.model.bean.AppInfoList;
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

public class GameListModel implements IModel {
    public void queryList(final int type , String args, int pageNo, final ICallback iCallback){
        String url = null;
        switch (type){
            case Constant.number.ZERO:
                url = UrlConstant.LEGEND_GAME_SEARCH;
                break;
            case Constant.number.ONE:
                url = UrlConstant.RANK_RECOMMEND_GAMES;
                break;
            case Constant.number.TWO:
                url = UrlConstant.RANK_NEW_GAMES;
                break;
            case Constant.number.THREE:
                url = UrlConstant.RANK_MODIFY_GAMES;
                break;
            case Constant.number.FORE:
                url = UrlConstant.RANK_SINGLE_GAMES;
                break;
            case Constant.number.FIVE:
                url = UrlConstant.RANK_BT_GAMES;
                break;
            case Constant.number.SIX:
                url = UrlConstant.RANK_ONLINE_GAMES;
                break;
            case Constant.number.SEVEN:
                url = UrlConstant.RANK_CHINGLISH_GAMES;
                break;
            case Constant.number.EIGHT:
                url = UrlConstant.CHOICE_GAMES;
                break;
        }
        HttpUtil.getHttpUtils().get(). url(UrlConstant.DOMAIN_NAME +url)
                .addParams("page", String.valueOf(pageNo))
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
                        if(!TextUtils.isEmpty(response)){
                            iCallback.success(GsonUtil.getGson().fromJson(response, AppInfoList.class),type);
                        }else {
                            iCallback.fail();
                        }

                    }
                });
    }
}
