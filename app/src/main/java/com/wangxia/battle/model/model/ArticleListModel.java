package com.wangxia.battle.model.model;

import android.text.TextUtils;

import com.wangxia.battle.callback.ArticleCallback;
import com.wangxia.battle.model.IModel;
import com.wangxia.battle.model.http.UrlConstant;
import com.wangxia.battle.presenter.callback.ICallback;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.HttpUtil;
import com.zhy.http.okhttp.builder.GetBuilder;

/**
 * 获取传奇文章列表
 */

public class ArticleListModel implements IModel {
    public void queryList(final int mId, String args, int pageNo, final ICallback iCallback) {
        GetBuilder builder = HttpUtil.getHttpUtils().get();
        switch (mId) {
            case Constant.number.ZERO:
                builder.url(UrlConstant.DOMAIN_NAME + UrlConstant.ARTICLE_LIST).addParams("key", "决战平安京")
                        .addParams("page", String.valueOf(pageNo));
                break;
            case Constant.number.ONE:
                builder.url(UrlConstant.DOMAIN_NAME + UrlConstant.BANNER_ARTICLE).addParams("size", String.valueOf(Constant.number.FIVE));
                break;
            //决战文章分类
            case Constant.number.TWO:
                String url = "&appname=%25u51b3%25u6218%25u5e73%25u5b89%25u4eac";
                if (!TextUtils.isEmpty(args)) {
                    url += "&labels=" + args;
                }
                builder.url(UrlConstant.DOMAIN_NAME + UrlConstant.ARTICLE_TYPE + url + "&page=" + pageNo);
                break;
            //每日一更
            case Constant.number.THREE:
                builder.url(UrlConstant.DOMAIN_NAME + UrlConstant.ACTIVITY_INFO).addParams("page", String.valueOf(pageNo));
                break;
            //入门秘籍
            case Constant.number.FORE:
                builder.url(UrlConstant.DOMAIN_NAME + UrlConstant.BIRD_BOOK).addParams("page", String.valueOf(pageNo));
                break;
            //手游问答
            case Constant.number.FIVE:
                builder.url(UrlConstant.DOMAIN_NAME + UrlConstant.GAME_ANSWER + "&labels=手游问答").addParams("page", String.valueOf(pageNo));
                break;
            //玩家攻略
            case Constant.number.SIX:
                builder.url(UrlConstant.DOMAIN_NAME + UrlConstant.PLAYER_RAISE).addParams("page", String.valueOf(pageNo));
                break;
            case Constant.number.SEVEN:
                builder.url(UrlConstant.DOMAIN_NAME + UrlConstant.HERO_TYPE_LIST + args).addParams("page", String.valueOf(pageNo));
                break;
            case Constant.number.EIGHT:
                builder.url(UrlConstant.DOMAIN_NAME + UrlConstant.ARM_TYPE_LIST + args).addParams("page", String.valueOf(pageNo));
                break;
        }
        builder.build()
                .execute(new ArticleCallback(iCallback,mId));
    }}
