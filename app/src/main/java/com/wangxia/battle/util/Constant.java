package com.wangxia.battle.util;

import android.os.Environment;

import java.io.File;

/**
 * Created by 昝奥博 on 2017/9/13 0013
 * Email:18772833900@163.com
 * Explain：常量
 */
public class Constant {
    public static final class number {
        public static final int ZERO = 0;
        public static final int ONE = 1;
        public static final int TWO = 2;
        public static final int THREE = 3;
        public static final int FORE = 4;
        public static final int FIVE = 5;
        public static final int SIX = 6;
        public static final int SEVEN = 7;
        public static final int EIGHT = 8;
        public static final int NINE = 9;
        public static final int TEN = 10;
        public static final int ELEVEN = 11;
        public static final int TWElVE = 12;
        public static final int THIRTEEN = 13;
        public static final int FOURTEEN = 14;
        public static final int FIFTEEN = 15;
        public static final int TWENTY = 20;
        public static final int TWENTY_SIX = 20;
        public static final int THIRTY = 30;
        public static final int THIRTY_SIX = 36;
        public static final int HUNDRED = 100;
        public static final int HUNDRED_AND_ONE = 101;
        public static final int HUNDRED_AND_TWO = 102;
        public static final int THOUSAND_AND_TWENTY_FORE = 1024;
        public static final int SQL_VERSION = 3;
        public static final int PERMISSION_REQUEST_CODE = 10101;

    }


    public static final class string {
        public static final String REPLAY = "<font color = '#81bcd7'>%1$s</font> <font color = '#4a4a4a'>回复</font> <font color = '#81bcd7'>%2$s</font> <font color = '#4a4a4a'>%3$s</font> <font color = '#D4D4D4'>%4$s</font>";
        public static final String REPLAY_COMMENT = "<font color = '#81bcd7'>%1$s</font> <font color = '#4a4a4a'>:</font>  <font color = '#4a4a4a'>%2$s</font> <font color = '#D4D4D4'>%3$s</font>";
        public static final String IS_FIRST_ENTER = "isFirstEnter";
        public static final String UPDATE_APP_NAME = "updateAppName";
        public static final String DEFAULT_APP_NAME = "battle.apk";
        public static final String DEFAULT_GAME_NAME = "onFire.apk";
        public static final String ZERO = "0";
        public static final String ONE = "1";
        public static final String TWO = "2";
        public static final String THREE = "3";
        public static final String FORE = "4";
        public static final String FIVE = "5";
        public static final String SIX = "6";

        //下载的游戏和更新的apk的大小
        public static final String DOWNLOAD_APK_SIZE = "downloadApkSize";
        public static final String COMMA_SEPARATOR = ",";
        public static final String ARG_ONE = "argOne";
        public static final String ARG_TWO = "argTwo";
        public static final String ARG_THREE = "argThree";
        public static final String ARG_FORE = "argFore";
        public static final String ARG_FIVE = "argFive";
        public static final String ARG_SIX = "argSix";
        public static final String ARG_SEVEN = "argSeven";
        public static final String ARG_EIGHT = "argEight";
        public static final String ARG_NINE = "argNine";
        public static final String ARG_TEN = "argTen";
        public static final String SP_SAVE_ARTICLE_SPLIT_LABELS = "sp_save_article_split_labels";
        public static final String SP_GAME_DOWNLOAD_URL = "sp_save_game_download_url";
        public static final String SP_APK_PACKAGE = "sp_save_apk_package";
        public static final String SP_GAME_PACKAGE = "sp_save_game_package";
        public static final String SP_HERO_TYPE = "sp_save_hero_type";
        public static final String SP_ARM_TYPE = "sp_save_arm_type";
        public static final String DOWNLOAD_APK_VERSION = "sp_save_apk_version";
        public static final String LOAD_CONTET = "javascript:window.local_obj.print('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');";
        public static final String DOWNLOAD_PATH = Environment.getExternalStorageDirectory()+File.separator+"download"+ File.separator + "battle";
        public static final String DOWNLOAD_APK_PATH = Environment.getExternalStorageDirectory()+File.separator+"download"+ File.separator + "battle"+File.separator+"apk";


        //下载历史表 ，app下载管理表 ，游戏浏览历史表，文章浏览历史表，游戏收藏表，文章收藏表
        public static final String SQL_DB_NAME = "legend.db";
        public static final String DB_ARTICLE_BROWSE = "articleBrowseDB";
        public static final String DB_ARTICLE_FAVORITE = "articleFavoriteDB";
        public static final String DB_VIDEO_BROWSE = "videoBrowseDB";
        public static final String DB_VIDEO_FAVORITE = "videoFavoriteDB";

        public static final String CREATE_ARTICLE_BROWSE = "create table " + DB_ARTICLE_BROWSE + "(" +
                "_id integer primary key autoincrement," +
                "icon text," +
                "title text," +
                "desc text," +
                "hints integer," +
                "time text," +
                "addTime text," +
                "savePath text)";
        public static final String CREATE_VIDEO_BROWSE = "create table " + DB_VIDEO_BROWSE + "(" +
                "_id integer primary key autoincrement," +
                "icon text," +
                "title text," +
                "desc text," +
                "hints integer," +
                "publishTime text," +
                "time text)";

        public static final String CREATE_ARTICLE_FAVORITE = "create table " + DB_ARTICLE_FAVORITE + "(" +
                "_id integer primary key autoincrement," +
                "icon text," +
                "title text," +
                "desc text," +
                "hints integer," +
                "time text," +
                "addTime text)";

        public static final String CREATE_VIDEO_FAVORITE = "create table " + DB_VIDEO_FAVORITE + "(" +
                "_id integer primary key autoincrement," +
                "icon text," +
                "title text," +
                "desc text," +
                "hints integer," +
                "publishTime text," +
                "time text)";
        public static final String DROP_TABLE = "drop table if exists";
        public static final String CLEAR_TABLE = "delete from ";
    }


    public static final class uMengStatistic {
        //新增用户以及打开次数
        public static final String NEWLY_INCREASED_USER = "001";
        public static final String OPEN_TIMES = "002";
        //总的看游戏，文章，视频的统计
        public static final String WATCH_GAME = "003";
        public static final String WATCH_ARTICLE = "004";
        public static final String WATCH_VIDEO = "005";
        //各个模块统计
        public static final String HOME_BANNER_HINTS = "006";
        public static final String HOME_GAME_HINTS = "007";
        public static final String RANK_GAME_HINTS = "008";
        public static final String FIND_ARTICLE_HINTS = "009";
        public static final String FOCUS_VIDEO_HINTS = "010";
        //我的界面的统计
        public static final String MINE_LOCAL_GAMES = "011";
        public static final String MINE_DOWNLOAD_HISTORY = "012";
        public static final String MINE_FAVORITE = "013";
        public static final String MINE_BROWSE = "014";
        public static final String MINE_ABOUT_US = "015";
        //新版本安装情况
        public static final String NEW_VERSION_UPDATE = "016";
        public static final String NEW_VERSION_EXIT = "017";
        public static final String NEW_VERSION_INSTALL_AUTOMATION = "018";
        public static final String NEW_VERSION_INSTALL_PASSIVE = "019";

    }

    public static final class jPushTag {
        public static final String GAME = "game";
        public static final String ARTICLE = "article";
        public static final String VIDEO = "video";
        //站内通知
        public static final String MESSAGE = "message";
        //活动
        public static final String ACTIVITY = "activity";
        // -----类型-----
        public static final String TYPE_GAME = "1";
        public static final String TYPE_ARTICLE = "2";
        public static final String TYPE_VIDEO = "3";
        public static final String TYPE_MESSAGE = "4";
        public static final String TYPE_ACTIVITY = "5";
        //----- 参数------
    }

    public static final class infoVariable {
        public static final String TYPE = "type";
        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String HINTS = "hints";
        public static final String DESC = "desc";
        public static final String URL = "url";
        public static final String TIME = "time";
    }


    public static final class platformID {
        public static final String LOGIN_QQ_ID = "1106493892";
        public static final String LOGIN_WX_ID = "wxfdc5771ff741abd7";
        public static final String LOGIN_WX_SECRET = "5471d7300100aa6c3290fcaa9c9be991";
        public static final String LOGIN_WX_GRANT_TYPE = "authorization_code";
        public static final String LOGIN_SINA_KEY = "2189999667";
        //应用的回调页,建议使用默认回调页：https://api.weibo.com/oauth2/default.html
        public static final String LOGIN_SINA_REDIRECT_URL = "http://www.hackhome.com";
        //WeiboSDK 应用对应的权限
        public static final String LOGIN_SINA_SCOPE = "email,direct_messages_read,direct_messages_write,"
                + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                + "follow_app_official_microblog," + "invitation_write";
    }

    public static final class userInfo {
        public static final String USER_COOKIES = "SP_BATTLE_USER_COOKIES";
        public static final String USER_COOKIES_TAG = "Quser";
        public static final String USER_STATE = "IS_USER_ENTER";
        public static final String USER_NICK = "USER_NICK";
        public static final String USER_ICON = "USER_ICON";
        public static final String USER_ID = "USER_ID";
        public static final String USER_TYPE = "USER_TYPE";
        public static final String USER_GENDER = "USER_GENDER";
        public static final String USER_LOGIN_TIME = "USER_LOGIN_TIME";
        public static final String NEED_USER_INFO = "need_user_inco";
    }
    public static final class savePath{
        public static final String STORAGE_PATH = string.DOWNLOAD_PATH;
        public static final String WEB_PAGE_CACHE = STORAGE_PATH +File.separator + "web"+File.separator+"cache";
        public static final String WEB_PAGE_CACHE_JS = STORAGE_PATH +File.separator + "a"+File.separator+"c"+File.separator+"j";
        public static final String WEB_PAGE_CACHE_CSS = STORAGE_PATH +File.separator + "a"+File.separator+"c"+File.separator+"c";
        public static final String WEB_PAGE_CACHE_HTML = WEB_PAGE_CACHE+File.separator + "h";
        public static final String WEB_PAGE_CACHE_PIC = WEB_PAGE_CACHE+File.separator + "p";
    }

    public static final class saveTag{
        public static final String HERO_SAVE = "hero_save";
        public static final String ARM_SAVE = "arm_save";
        public static final String PIC_SAVE = "pic_save";
        public static final String CURSE_SAVE = "curse_save";
        public static final String HERO_EFFECTIVE_TIME = "hero_effective_time";
        public static final String ARM_EFFECTIVE_TIME = "arm_effective_time";
        public static final String PIC_EFFECTIVE_TIME = "pic_effective_time";
    }
}
