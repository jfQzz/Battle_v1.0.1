package com.wangxia.battle.model.model;

import com.wangxia.battle.callback.VideoCallback;
import com.wangxia.battle.model.IModel;
import com.wangxia.battle.model.http.UrlConstant;
import com.wangxia.battle.presenter.callback.ICallback;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.HttpUtil;

/**
 * 视频列表
 */

public class VideoListModel implements IModel {
    public void queryList(int id, String args,int pageNo, final ICallback iCallback) {
        HttpUtil.getHttpUtils().get().url(UrlConstant.VIDEO_LIST)
                .addParams("page",String.valueOf(pageNo))
                .build()
                .execute(new VideoCallback(iCallback,Constant.number.ZERO));
    }
}
