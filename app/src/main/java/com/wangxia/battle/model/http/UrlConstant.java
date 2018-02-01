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
    //fileName
    public static final String APP_NAME = "jzpaj";
    public static final String APP_API_FILE_NAME = APP_NAME+"/app_api.asp?";
    public static final String AJAX_COMMENT_NAME = APP_NAME+"/app_comment.asp?";
    //最新游戏
    public static final String LEGEND_GAME_SEARCH = APP_API_FILE_NAME + "t=newapp&id=1&key=决战";
    //游戏详情
    public static final String GAME_DETAIL = APP_API_FILE_NAME + "t=appinfo";
    //文章列表
    public static final String ARTICLE_LIST = APP_API_FILE_NAME + "t=newskey&id=1&size=10";
    //排行榜-推荐
    public static final String RANK_RECOMMEND_GAMES = APP_API_FILE_NAME + "t=arrapp&type=22";
    //排行榜-新游
    public static final String RANK_NEW_GAMES = APP_API_FILE_NAME + "t=arrapp&type=21";
    //排行榜-破解
    public static final String RANK_MODIFY_GAMES = APP_API_FILE_NAME + "t=newapp&id=1&key=破解&re=1";
    //排行榜-单机
    public static final String RANK_SINGLE_GAMES = APP_API_FILE_NAME + "t=arrapp&type=20";
    //排行榜-BT
    public static final String RANK_BT_GAMES = APP_API_FILE_NAME + "t=newapp&id=1&key=BT&re=1";
    //排行榜-网游
    public static final String RANK_ONLINE_GAMES = APP_API_FILE_NAME + "t=arrapp&type=19";
    //排行榜-汉化
    public static final String RANK_CHINGLISH_GAMES = APP_API_FILE_NAME + "t=newapp&id=1&key=汉化&re=1";
    //关注-精选游戏
    public static final String CHOICE_GAMES = APP_API_FILE_NAME + "t=faxianapp&psize=8";
    //视频列表-关注
    public static final String VIDEO_HOME = "api/v4/tabs/selected";
    //首页banner
    public static final String HOME_BANNER = APP_API_FILE_NAME+"t=indexfalsh";
    //app更新接口
    public static final String UPDATE_APP = "jzpaj/ver.asp";
    //文章的banner
    public static final String BANNER_ARTICLE = APP_API_FILE_NAME+"t=recommendlist&flash=1";
    //决战平安京接口
    public static final String TARGET_GAME_URL = APP_API_FILE_NAME + "t=info";
    //决战平安京文章分类
    public static final String ARTICLE_TYPE = APP_API_FILE_NAME + "t=newskey&id=1&size=10";
    //apkDownloadUrl
    public static final String GAME_DOWN_URL = "https://adl.netease.com/d/g/moba/c/jjsc?type=android";
    //活动咨询
    public static final String ACTIVITY_INFO = APP_API_FILE_NAME + "t=newskey&appname=%E5%86%B3%E6%88%98%E5%B9%B3%E5%AE%89%E4%BA%AC&labels=%E6%B4%BB%E5%8A%A8%E8%B5%84%E8%AE%AF";
    //新手入门
    public static final String BIRD_BOOK = APP_API_FILE_NAME + "t=newskey&appname=%E5%86%B3%E6%88%98%E5%B9%B3%E5%AE%89%E4%BA%AC&labels=%E6%96%B0%E6%89%8B%E5%85%A5%E9%97%A8";
    //高手进阶
    public static final String PLAYER_RAISE = APP_API_FILE_NAME + "t=newskey&appname=%E5%86%B3%E6%88%98%E5%B9%B3%E5%AE%89%E4%BA%AC&labels=%E9%AB%98%E6%89%8B%E8%BF%9B%E9%98%B6";
    //手游问答
    public static final String GAME_ANSWER = APP_API_FILE_NAME + "t=newskey&appname=%E5%86%B3%E6%88%98%E5%B9%B3%E5%AE%89%E4%BA%AC";
    //式神大全
    public static final String HERO_TYPE = APP_API_FILE_NAME + "t=newslab&appname=%E5%86%B3%E6%88%98%E5%B9%B3%E5%AE%89%E4%BA%AC&labels=";
    //式神分类
    public static final String HERO_TYPE_LIST = APP_API_FILE_NAME + "t=newskey&appname=%E5%86%B3%E6%88%98%E5%B9%B3%E5%AE%89%E4%BA%AC&labels=式神大全&sublabels=";
    public static final String ARM_TYPE_LIST = APP_API_FILE_NAME + "t=newskey&appname=%E5%86%B3%E6%88%98%E5%B9%B3%E5%AE%89%E4%BA%AC&labels=装备大全&sublabels=";
    //决战平安京优酷视频
    public static final String BATTLE_VIDEO_BY_UK = "http://www.soku.com/m/y/video?spm=a2h0k.8191393.bodydiv.5!2~5~5~5~1~3!3~A&q=%E5%86%B3%E6%88%98%E5%B9%B3%E5%AE%89%E4%BA%AC&ld=0&site=14&_lg=10&od=3";
    //官网入口
    public static final String OFFICAL_ENTER = "http://moba.163.com/m/";
    //灵咒大全
    public static final String CURSE_ALL= "https://yxzs.163.com/moba/webapp/illustration/mantras?app_share=1&app_which=mobazs&app_gameid=7240";
    //式神大全
    public static final String HERO_ALL = "https://yxzs.163.com/moba/webapp/illustration/heros?type=%E5%85%A8&app_share=1&app_which=mobazs&app_gameid=7240";
    //装备大全
    public static final String ARM_ALL = "https://yxzs.163.com/moba/webapp/illustration/equips?type=%E6%AD%A6%E5%99%A8&app_share=1&app_which=mobazs&app_gameid=7240";
    //微信token获取接口
    public static final String WX_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?";
    //微信获取userinfo
    public static final String WX_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?";
    //登录接口
    public static final String LOGIN_URL = "/load/?s=login";
    //获取注册码
    public static final String GET_REGISTER_CODE = "/load/?s=code";
    //注册账户
    public static final String REGISTER_ACCOUNT = "/load/?s=reg";
    //获取用户信息
    public static final String USER_INFO = "/ajax/?s=info";
    //找回密码验证码
    public static final String GET_FIND_PASSWORD_CODE = "/load/?s=pass";
    //找回密码
    public static final String FIND_PASSWORD = "/load/?s=editpass";
    //用户绑定的账户
    public static final String USER_BIND_INFO = "/safe/?s=bindinfo";
    //修改头像
    public static final String USER_MODIFY_ICO = "/ajax/?s=uppic";
    //修改用户的昵称或者性别
    public static final String USER_MODIFY_NICK_OR_GENDER = "/ajax/?s=edit";
    //绑定手机号码获取验证码
    public static final String USER_BIND_PHONE_CODE = "/safe/?s=bindcode";
    //手机号码绑定
    public static final String USER_PHONE_BIND = "/safe/?s=bindtel";
    //第三方解绑
    public static final String USER_OTHER_ENTER_UNBIN="/safe/?s=unapi";
    //修改密码
    public static final String USER_MODIFY_PASSWORD = "/ajax/?s=pass";
    //微博个人数据读取
    public static final String SINA_USER_INFO = "https://api.weibo.com/2/users/show.json";
    //第三方注册
    public static final String API_REGISTER = "/load/?s=apireg&bind=3";
    //第三方登录
    public static final String API_LOGIN = "/load/?s=apilogin";
    //第三方登录绑定原有的手机号
    public static final String API_LOGIN_BIND_OLD_PHONE = "/load/?s=apicode";
    //第三方绑定手机号老账户验证
   public static final String API_LOGIN_BIND_PHONE_CONFIRM = "/load/?s=apireg&bind=1";
    //第三方绑定API账户验证
    public static final String API_LOGIN_BIND_ACCOUNT_CONFIRM = "/load/?s=apireg&bind=2";
    //用户中心第三方绑定当前账户
    public static final String API_BIND_ACCOUNT_NOW = "/safe/?s=bindapi";
    //1：游戏评论     2：文章评论
    public static final String COMMENT_ARTICLE = "/app_comment.asp?";
    //视频评论或者点赞（s=dig）
    public static final String COMMENT_VIDEO = "/app_ping.asp?";
    //评论列表点赞
    public static final String UP_VOTE_COMMENT="/app_comment.asp?";

    //视频列表
    public static final String VIDEO_LIST = "https://tj.hackhome.com/jzpaj/app_api.asp?t=video";
    //视频详情
    public static final String VIDEO_DETAIL = "https://tj.hackhome.com/jzpaj/app_api.asp?t=videoinfo";
    //英雄大全
    public static final String HERO_LIST = "/json/hero.json";
    //图片集
    public static final String HERO_PIC = "/json/pics.json";
    //灵咒
    public static final String CURSE_LIST = "/json/mantra.json";
    //装备
    public static final String ARM_LIST = "/json/equip.json";
    //文章的样式
    public static final String ARTICLE_CSS = "/css/api/style.css";

}
