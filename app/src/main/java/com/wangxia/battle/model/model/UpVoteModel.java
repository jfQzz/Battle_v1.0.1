package com.wangxia.battle.model.model;

import android.text.TextUtils;

import com.wangxia.battle.model.IModel;
import com.wangxia.battle.model.bean.SuccessBean;
import com.wangxia.battle.model.http.UrlConstant;
import com.wangxia.battle.presenter.callback.ICallback;
import com.wangxia.battle.util.GsonUtil;
import com.wangxia.battle.util.HttpUtil;
import com.wangxia.battle.util.LogUtil;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 *  点赞
 *  需要两个id ，一个objID
 *  type: 1 游戏  2 文章 3 视频
 */
public class UpVoteModel implements IModel {
    public void queryList( int type, String args,final int pageNo, final ICallback iCallback) {
        Map<String,String> param = new HashMap<>();
        String url = null;
        if(3 != type){
            //https://tj.hackhome.com/jzpaj/app_comment.asp?type=1&s=dig&id=120302
            param.put("id",args);
            param.put("s","dig");
            param.put("type",String.valueOf(type));
            url = UrlConstant.APP_NAME+UrlConstant.UP_VOTE_COMMENT;
        }else {
            //https://tj.hackhome.com/jzpaj/app_comment.asp?s=dig&id=14
            param.put("id",args);
            param.put("s","dig");
            url = UrlConstant.APP_NAME+UrlConstant.COMMENT_VIDEO;
        }
        HttpUtil.getHttpUtils().get().url(UrlConstant.DOMAIN_NAME +url)
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
                        LogUtil.i("         点赞 ----"+response);
                        if (!TextUtils.isEmpty(response)) {
                            iCallback.success(GsonUtil.getGson().fromJson(response, SuccessBean.class), pageNo);
                        }else iCallback.fail();
                    }
                });
    }

}
