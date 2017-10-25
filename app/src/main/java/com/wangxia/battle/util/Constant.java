package com.wangxia.battle.util;

import android.os.Environment;

import com.wangxia.battle.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
        public static final int TWElVE = 12;
        public static final int FIFTEEN = 15;
        public static final int HUNDRED = 100;
        public static final int HUNDRED_AND_ONE = 101;
        public static final int SQL_VERSION = 1;
        public static final int GAME_ID = 716588;

    }


    public static final class string {
        public static final String REPLAY = "<font color = '#14b9c8'>%1$s</font> <font color = '#4a4a4a'>回复</font> <font color = '#14b9c8'>%2$s</font> <font color = '#4a4a4a'>%3$s</font>";
        public static final String VIDEO_DEFAULT = "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=19523&editionType=default&source=qcloud";
        public static final String VIDEO_TYPE_VIDEO = "video";
        public static final String VIDEO_TYPE_TITLE_HEAD = "textHeader";
        public static final String VIDEO_TYPE_TITLE_FOOT = "textFooter";
        public static final String VIDEO_TYPE_BANNER = "banner2";
        public static final String VIDEO_TYPE_VIDEO_COLLECTION = "videoCollectionWithCover";
        public static final String VIDEO_TYPE_VIDEO_FOLLOW = "videoCollectionOfFollow";
        public static final String IS_FIRST_ENTER = "isFirstEnter";
        public static final String UPDATE_APP_NAME = "updateAppName";
        public static final String DEFAULT_APP_NAME = "battle.apk";
        public static final String DEFAULT_GAME_NAME = "onFire.apk";
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
        public static final String SP_SAVE_ARTICLE_SPLIT_LABELS = "sp_save_article_split_labels";
        public static final String SP_GAME_DOWNLOAD_URL = "sp_save_game_download_url";
        public static final String SP_APK_PACKAGE = "sp_save_apk_package";
        public static final String SP_GAME_PACKAGE = "sp_save_game_package";
        public static final String SP_HERO_TYPE = "sp_save_hero_type";
        public static final String SP_ARM_TYPE = "sp_save_arm_type";
        public static final String SP_SERVER_APK_VERSION = "sp_save_apk_version";
        public static final String DOWNLOAD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "download" + File.separator + "battle";


        //下载历史表 ，app下载管理表 ，游戏浏览历史表，文章浏览历史表，游戏收藏表，文章收藏表
        public static final String SQL_DB_NAME = "legend.db";
        public static final String DB_DOWN_HISTORY = "downHistoryDB";
        public static final String DB_DOWN_MANAGER = "downListDB";
        public static final String DB_GAME_BROWSE = "gameBrowseDB";
        public static final String DB_ARTICLE_BROWSE = "articleBrowseDB";
        public static final String DB_GAME_FAVORITE = "gameFavoriteDB";
        public static final String DB_ARTICLE_FAVORITE = "articleFavoriteDB";

        public static final String CREATE_DOWNLOAD_HISTORY = "create table " + DB_DOWN_HISTORY + "(" +
                "_id integer primary key autoincrement," +
                "icon text," +
                "title text," +
                "size integer," +
                "labels text," +
                "mark text," +
                "time text)";
        public static final String CREATE_DOWNLOAD_MANAGER = "create table " + DB_DOWN_MANAGER + "(" +
                "_id integer primary key autoincrement," +
                "icon text," +
                "title text," +
                "size integer," +
                "labels text," +
                "mark text," +
                "state integer," +
                "currentLength integer," +
                "downUrl text," +
                "path text," +
                "speed text," +
                "downloadId integer)";
        public static final String CREATE_GAME_BROWSE = "create table " + DB_GAME_BROWSE + "(" +
                "_id integer primary key autoincrement," +
                "icon text," +
                "title text," +
                "size integer," +
                "labels text," +
                "mark text," +
                "time text)";
        public static final String CREATE_ARTICLE_BROWSE = "create table " + DB_ARTICLE_BROWSE + "(" +
                "_id integer primary key autoincrement," +
                "icon text," +
                "title text," +
                "hints integer," +
                "publishTime text," +
                "author text," +
                "authorIco text," +
                "time text)";
        public static final String CREATE_GAME_FAVORITE = "create table " + DB_GAME_FAVORITE + "(" +
                "_id integer primary key autoincrement," +
                "icon text," +
                "title text," +
                "size integer," +
                "labels text," +
                "mark text," +
                "time text)";
        public static final String CREATE_ARTICLE_FAVORITE = "create table " + DB_ARTICLE_FAVORITE + "(" +
                "_id integer primary key autoincrement," +
                "icon text," +
                "title text," +
                "hints integer," +
                "publishTime text," +
                "author text," +
                "authorIco text," +
                "time text)";
        public static final String DROP_TABLE = "drop table if exists";
    }

    public static final class authorIcon {
        public static final int ICON_ONE = R.drawable.author_one_ico;
        public static final int ICON_TWO = R.drawable.author_two_ico;
        public static final int ICON_THREE = R.drawable.author_three_ico;
        public static final int ICON_FORE = R.drawable.author_fore_ico;
        public static final int ICON_FIVE = R.drawable.author_five_ico;
        public static final int ICON_SIX = R.drawable.author_six_ico;
        public static final int ICON_SEVEN = R.drawable.author_seven_ico;
        public static final int ICON_EIGHT = R.drawable.author_eight_ico;
        public static final int ICON_NINE = R.drawable.author_nine_ico;
        public static final int ICON_TEN = R.drawable.author_ten_ico;
        public static final int ICON_ELEVEN = R.drawable.author_eleven_ico;
        public static final int ICON_TWELVE = R.drawable.author_twive_ico;
    }

    public static List<Integer> getAuthorIcons() {
        List<Integer> list = new ArrayList<>(12);
        list.add(authorIcon.ICON_ONE);
        list.add(authorIcon.ICON_TWO);
        list.add(authorIcon.ICON_THREE);
        list.add(authorIcon.ICON_FORE);
        list.add(authorIcon.ICON_FIVE);
        list.add(authorIcon.ICON_SIX);
        list.add(authorIcon.ICON_SEVEN);
        list.add(authorIcon.ICON_EIGHT);
        list.add(authorIcon.ICON_NINE);
        list.add(authorIcon.ICON_TEN);
        list.add(authorIcon.ICON_ELEVEN);
        list.add(authorIcon.ICON_TWELVE);
        return list;
    }

    public static int[] getFunctionPic() {
        int[] functionPic = new int[8];

        return functionPic;
    }

    public static final class downloadState {
        //完成
        public static final int DOWNLOAD_COMPLETE = 0;
        //暂停
        public static final int DOWNLOAD_PAUSE = 1;
        //正在下载
        public static final int DOWNLOAD_RUN = 2;
        //下载延迟
        public static final int DOWNLOAD_DELY = 3;
        //下载失败
        public static final int DOWNLOAD_FILE = 4;

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
}
