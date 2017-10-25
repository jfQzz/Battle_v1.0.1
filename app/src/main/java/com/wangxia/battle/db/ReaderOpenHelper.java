package com.wangxia.battle.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wangxia.battle.util.Constant;

/**
 * Created by 昝奥博 on 2017/9/19 0019
 * Email:18772833900@163.com
 * Explain：数据库创建
 */
public class ReaderOpenHelper extends SQLiteOpenHelper {


    public ReaderOpenHelper(Context context) {
        super(context, Constant.string.SQL_DB_NAME, null, Constant.number.SQL_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constant.string.CREATE_DOWNLOAD_HISTORY);
        db.execSQL(Constant.string.CREATE_DOWNLOAD_MANAGER);
        db.execSQL(Constant.string.CREATE_GAME_BROWSE);
        db.execSQL(Constant.string.CREATE_ARTICLE_BROWSE);
        db.execSQL(Constant.string.CREATE_GAME_FAVORITE);
        db.execSQL(Constant.string.CREATE_ARTICLE_FAVORITE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion < newVersion){
            db.execSQL(Constant.string.DROP_TABLE + Constant.string.DB_DOWN_HISTORY);
            db.execSQL(Constant.string.DROP_TABLE + Constant.string.DB_DOWN_MANAGER);
            db.execSQL(Constant.string.DROP_TABLE + Constant.string.DB_GAME_BROWSE);
            db.execSQL(Constant.string.DROP_TABLE + Constant.string.DB_ARTICLE_BROWSE);
            db.execSQL(Constant.string.DROP_TABLE + Constant.string.DB_GAME_FAVORITE);
            db.execSQL(Constant.string.DROP_TABLE + Constant.string.DB_ARTICLE_FAVORITE);
            onCreate(db);
        }
    }
}
