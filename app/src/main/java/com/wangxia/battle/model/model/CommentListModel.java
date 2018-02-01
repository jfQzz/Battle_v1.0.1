package com.wangxia.battle.model.model;

import com.wangxia.battle.callback.EvaluateCallback;
import com.wangxia.battle.model.IModel;
import com.wangxia.battle.model.http.UrlConstant;
import com.wangxia.battle.presenter.callback.ICallback;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取评论列表
 */

public class CommentListModel implements IModel {
    public void queryList(final int type ,String args,int pageNo, final ICallback iCallback){
        Map<String,String> param = new HashMap<>();
        param.put("id",args);
        param.put("page",String.valueOf(pageNo));
        param.put("s","commentlist");
        String url ;
        if(Constant.number.THREE != type){
            url = UrlConstant.AJAX_COMMENT_NAME;
            param.put("type",String.valueOf(type));
        }else {
            param.put("type",Constant.string.ONE);
            url = UrlConstant.APP_NAME+UrlConstant.COMMENT_VIDEO;
        }

        HttpUtil.getHttpUtils().get(). url( UrlConstant.DOMAIN_NAME+url)
                .params(param)
                .build()
                .execute(new EvaluateCallback(iCallback,type));
    }
}
