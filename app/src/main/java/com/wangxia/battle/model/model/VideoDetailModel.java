package com.wangxia.battle.model.model;

import android.text.TextUtils;

import com.wangxia.battle.model.IModel;
import com.wangxia.battle.model.bean.VideoDetailBean;
import com.wangxia.battle.model.http.UrlConstant;
import com.wangxia.battle.presenter.callback.ICallback;
import com.wangxia.battle.util.GsonUtil;
import com.wangxia.battle.util.HttpUtil;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 视频详情
 */

public class VideoDetailModel implements IModel {
    public void queryList(final int type, String args, int pageNo, final ICallback iCallback) {
        HttpUtil.getHttpUtils().get().url(UrlConstant.VIDEO_DETAIL)
                .addParams("id",args)
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
                        if (!TextUtils.isEmpty(response)) {
                            iCallback.success(GsonUtil.getGson().fromJson(response, VideoDetailBean.class), type);
                        }else iCallback.fail();
                    }
                });
    }

}
