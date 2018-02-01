package com.wangxia.battle.model.model;

import android.text.TextUtils;

import com.wangxia.battle.model.IModel;
import com.wangxia.battle.model.bean.CommentBean;
import com.wangxia.battle.model.bean.SuccessBean;
import com.wangxia.battle.model.http.UrlConstant;
import com.wangxia.battle.presenter.callback.ICallback;
import com.wangxia.battle.util.GsonUtil;
import com.wangxia.battle.util.HttpUtil;
import com.wangxia.battle.util.LogUtil;
import com.wangxia.battle.util.UserUtil;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * 评论
 */

public class CommentModel implements IModel {
    public void queryList(final int type, String args, int pageNo, final ICallback iCallback) {
        Map<String,String> param = new HashMap<>();
        LogUtil.i(args);
        CommentBean commentBean = GsonUtil.getGson().fromJson(args, CommentBean.class);
        param.put("phone",commentBean.getPhone());
        param.put("brand",commentBean.getBrand());
        param.put("user",commentBean.getUserName());
        param.put("icon",commentBean.getUserIcon());
        param.put("id",String.valueOf(commentBean.getObjId()));
        param.put("pid",String.valueOf(commentBean.getPId()));
        param.put("text", commentBean.getInputTxt());
        String url ;
        if(3 != type){
            param.put("s","save");
            param.put("type",String.valueOf(type));
            url =  UrlConstant.COMMENT_ARTICLE;
        }else {
            param.put("s","videosave");
            param.put("type","1");
            url = UrlConstant.COMMENT_VIDEO;
        }
        param.putAll(UserUtil.getCookiesParams());
        LogUtil.i(type+"            "+UrlConstant.DOMAIN_NAME+UrlConstant.APP_NAME+url+"s=videosave&type="+type+"&phone="+commentBean.getPhone()+"&brand="+commentBean.getBrand()+"&user="+commentBean.getUserName()
        +"&icon="+commentBean.getUserIcon()+"&id="+commentBean.getObjId()+"&pid="+commentBean.getPId()+"&text="+commentBean.getInputTxt());
        HttpUtil.getHttpUtils().get().url(UrlConstant.DOMAIN_NAME+UrlConstant.APP_NAME +url)
                .params(param)
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
                        LogUtil.i(response);
                        if (!TextUtils.isEmpty(response)) {
                            iCallback.success(GsonUtil.getGson().fromJson(response, SuccessBean.class), type);
                        }else {
                            iCallback.fail();
                        }
                    }
                });
    }

}
