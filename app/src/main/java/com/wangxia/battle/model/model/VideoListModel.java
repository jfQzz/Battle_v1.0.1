package com.wangxia.battle.model.model;

import android.text.TextUtils;

import com.wangxia.battle.model.IModel;
import com.wangxia.battle.model.bean.VideoList;
import com.wangxia.battle.model.http.UrlConstant;
import com.wangxia.battle.presenter.callback.ICallback;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.GsonUtil;
import com.wangxia.battle.util.HttpUtil;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 获取传奇文章列表
 */

public class VideoListModel implements IModel {
    public void queryList(int id, String args,int pageNo, final ICallback iCallback) {
        GetBuilder url = HttpUtil.getHttpUtils().get().url(UrlConstant.VIDEO_DOMAIN_NAME + UrlConstant.VIDEO_HOME);
        if (Constant.number.ZERO != pageNo) {
            url.addParams("date", +pageNo + "00000");
        }
        url
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (!TextUtils.isEmpty(response)) {
                            iCallback.success(GsonUtil.getGson().fromJson(response, VideoList.class),Constant.number.ZERO);
                        }
                    }
                });
    }
}
