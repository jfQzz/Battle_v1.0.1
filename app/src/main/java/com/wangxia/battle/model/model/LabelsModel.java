package com.wangxia.battle.model.model;

import com.wangxia.battle.callback.LabelsCallback;
import com.wangxia.battle.model.IModel;
import com.wangxia.battle.model.http.UrlConstant;
import com.wangxia.battle.presenter.callback.ICallback;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.HttpUtil;
import com.zhy.http.okhttp.builder.GetBuilder;

/**
 * 获取传奇文章列表
 * 获取字符串分类
 */

public class LabelsModel implements IModel {
    public void queryList(final int type, String args, int pageNo, final ICallback iCallback) {
        GetBuilder builder = HttpUtil.getHttpUtils().get();
        switch (type) {
            case Constant.number.ZERO:
                builder.url(UrlConstant.DOMAIN_NAME + UrlConstant.HERO_TYPE + args);
                break;
            case Constant.number.ONE:
                break;
        }
        builder.build()
                .execute(new LabelsCallback(iCallback, type));
    }

}
