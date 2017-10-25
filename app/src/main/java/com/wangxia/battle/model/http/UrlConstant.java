package com.wangxia.battle.model.http;

/**
 * Created by 昝奥博 on 2017/9/14 0014
 * Email:18772833900@163.com
 * Explain：接口
 */
public class UrlConstant {
    //域名
    public static final String DOMAIN_NAME = "http://btj.hackhome.com/";
    //文章的域名
    public static final String ARTICLE_DOMAIN_NAME = "http://m.hackhome.com/";
    //视频域名
    public static final String VIDEO_DOMAIN_NAME = "http://baobab.kaiyanapp.com/";
    //fileName
    public static final String APP_API_FILE_NAME = "jzpaj/app_api.asp?";
    public static final String AJAX_COMMENT_NAME = "chuanqi/app_comment.asp?";
    //最新游戏
    public static final String LEGEND_GAME_SEARCH = APP_API_FILE_NAME +"t=newapp&id=1&key=决战";
    //游戏详情
    public static final String GAME_DETAIL = APP_API_FILE_NAME +"t=appinfo";
     //游戏评价
    public static final String GAME_EVALUATE = AJAX_COMMENT_NAME +"s=commentlist&type=1";
    //文章列表
    public static final String ARTICLE_LIST = APP_API_FILE_NAME +"t=newskey&id=1&size=10";
    //排行榜-推荐
    public static final String RANK_RECOMMEND_GAMES = APP_API_FILE_NAME +"t=arrapp&type=22";
    //排行榜-新游
    public static final String RANK_NEW_GAMES = APP_API_FILE_NAME +"t=arrapp&type=21";
    //排行榜-破解
    public static final String RANK_MODIFY_GAMES = APP_API_FILE_NAME +"t=newapp&id=1&key=破解&re=1";
    //排行榜-单机
    public static final String RANK_SINGLE_GAMES = APP_API_FILE_NAME +"t=arrapp&type=20";
    //排行榜-BT
    public static final String RANK_BT_GAMES = APP_API_FILE_NAME +"t=newapp&id=1&key=BT&re=1";
    //排行榜-网游
    public static final String RANK_ONLINE_GAMES = APP_API_FILE_NAME +"t=arrapp&type=19";
    //排行榜-汉化
    public static final String RANK_CHINGLISH_GAMES = APP_API_FILE_NAME +"t=newapp&id=1&key=汉化&re=1";
    //关注-精选游戏
    public static final String CHOICE_GAMES = APP_API_FILE_NAME +"t=faxianapp&psize=8";
    //视频列表-关注
    public static final String VIDEO_HOME = "api/v4/tabs/selected";
    //首页banner
    public static final String HOME_BANNER = "app_api.asp?t=indexfalsh";
    //app更新接口
    public static final String UPDATE_APP = "jzpaj/ver.asp";
    //文章的banner
    public static final String BANNER_ARTICLE = "app_api.asp?t=recommendlist";
    //决战平安京接口
    public static final String TARGET_GAME_URL = APP_API_FILE_NAME +"t=info";
    //决战平安京文章分类
    public static final String ARTICLE_TYPE = APP_API_FILE_NAME+"t=newskey&id=1&size=10";
    //apkDownloadUrl
    public static final String GAME_DOWN_URL = "http://syb.yasnk.cn/vp/yx_ljun1/daihaoMOBA.apk";
    //活动咨询
    public static final String ACTIVITY_INFO = APP_API_FILE_NAME+"t=newskey&appname=%E5%86%B3%E6%88%98%E5%B9%B3%E5%AE%89%E4%BA%AC&labels=%E6%B4%BB%E5%8A%A8%E8%B5%84%E8%AE%AF";
    //新手入门
    public static final String BIRD_BOOK = APP_API_FILE_NAME+"t=newskey&appname=%E5%86%B3%E6%88%98%E5%B9%B3%E5%AE%89%E4%BA%AC&labels=%E6%96%B0%E6%89%8B%E5%85%A5%E9%97%A8";
    //高手进阶
    public static final String PLAYER_RAISE = APP_API_FILE_NAME+"t=newskey&appname=%E5%86%B3%E6%88%98%E5%B9%B3%E5%AE%89%E4%BA%AC&labels=%E9%AB%98%E6%89%8B%E8%BF%9B%E9%98%B6";
    //手游问答
    public static final String GAME_ANSWER = APP_API_FILE_NAME+"t=newskey&appname=%E5%86%B3%E6%88%98%E5%B9%B3%E5%AE%89%E4%BA%AC";
    //式神大全
    public static final String HERO_TYPE = APP_API_FILE_NAME+"t=newslab&appname=%E5%86%B3%E6%88%98%E5%B9%B3%E5%AE%89%E4%BA%AC&labels=";
    //式神分类
    public static final String HERO_TYPE_LIST = APP_API_FILE_NAME+"t=newskey&appname=%E5%86%B3%E6%88%98%E5%B9%B3%E5%AE%89%E4%BA%AC&labels=式神大全&sublabels=";
    public static final String ARM_TYPE_LIST = APP_API_FILE_NAME+"t=newskey&appname=%E5%86%B3%E6%88%98%E5%B9%B3%E5%AE%89%E4%BA%AC&labels=装备大全&sublabels=";
    //决战平安京优酷视频
    public static final String BATTLE_VIDEO_BY_UK = "http://www.soku.com/m/y/video?spm=a2h0k.8191393.bodydiv.5!2~5~5~5~1~3!3~A&q=%E5%86%B3%E6%88%98%E5%B9%B3%E5%AE%89%E4%BA%AC&ld=0&site=14&_lg=10&od=3";
    //官网入口
    public static final String OFFICAL_ENTER = "http://moba.163.com/m/";

}
