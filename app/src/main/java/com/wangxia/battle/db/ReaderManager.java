package com.wangxia.battle.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wangxia.battle.db.bean.ArticleBean;
import com.wangxia.battle.db.bean.VideoBean;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 昝奥博 on 2017/9/19 0019
 * Email:18772833900@163.com
 * Explain：数据库辅助类
 */
public class ReaderManager {

    private ReaderOpenHelper mReaderOpenHelper;

    public ReaderManager(Context context) {
        mReaderOpenHelper = new ReaderOpenHelper(context);
    }


    public void addArticleBrowseDB(int id, String icon, String title, String desc,int hints, String time, String addTime) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = mReaderOpenHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            cursor = db.rawQuery("select _id from " + Constant.string.DB_ARTICLE_BROWSE + " where _id=?", new String[]{String.valueOf(id)});
            if (cursor.moveToNext()) {
                values.put("time", time);
                db.update(Constant.string.DB_ARTICLE_BROWSE, values, "_id = ?", new String[]{String.valueOf(id)});
            } else {
                values.put("_id", id);
                values.put("icon", icon);
                values.put("title", title);
                values.put("desc", desc);
                values.put("hints", hints);
                values.put("time", time);
                values.put("addTime", addTime);
                db.insert(Constant.string.DB_ARTICLE_BROWSE, null, values);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != db) {
                db.close();
            }
        }
    }

    public void addVideoBrowseDB(int id, String icon, String title,String desc, int hints, String publishTime, String time) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = mReaderOpenHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            cursor = db.rawQuery("select _id from " + Constant.string.DB_VIDEO_BROWSE + " where _id=?", new String[]{String.valueOf(id)});
            if (cursor.moveToNext()) {
                values.put("time", time);
                db.update(Constant.string.DB_VIDEO_BROWSE, values, "_id = ?", new String[]{String.valueOf(id)});
            } else {
                values.put("_id", id);
                values.put("icon", icon);
                values.put("title", title);
                values.put("desc", desc);
                values.put("hints", hints);
                values.put("publishTime", publishTime);
                values.put("time", time);
                db.insert(Constant.string.DB_VIDEO_BROWSE, null, values);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != db) {
                db.close();
            }
        }
    }



    public void addArticleFavoriteDB(int id, String icon, String title,String desc, int hints, String publishTime, String time) {
        SQLiteDatabase db = null;
        try {
            db = mReaderOpenHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("_id", id);
            values.put("icon", icon);
            values.put("title", title);
            values.put("desc",desc);
            values.put("hints", hints);
            values.put("time", publishTime);
            values.put("addTime", time);
            db.insert(Constant.string.DB_ARTICLE_FAVORITE, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != db) {
                db.close();
            }
        }
    }

    public void addVideoFavoriteDB(int id, String icon, String title,String desc, int hints, String publishTime, String time) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = mReaderOpenHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            cursor = db.rawQuery("select _id from " + Constant.string.DB_VIDEO_FAVORITE + " where _id=?", new String[]{String.valueOf(id)});
            if (cursor.moveToNext()) {
                values.put("time", time);
                db.update(Constant.string.DB_VIDEO_FAVORITE, values, "_id = ?", new String[]{String.valueOf(id)});
            } else {
                values.put("_id", id);
                values.put("icon", icon);
                values.put("title", title);
                values.put("desc", desc);
                values.put("hints", hints);
                values.put("publishTime", publishTime);
                values.put("time", time);
                db.insert(Constant.string.DB_VIDEO_FAVORITE, null, values);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != db) {
                db.close();
            }
        }
    }


    /**
     * 获取全部的文章浏览记录
     *
     * @return
     */
    public List<ArticleBean> getArticleBrowse() {
        List<ArticleBean> list = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = mReaderOpenHelper.getReadableDatabase();
            cursor = db.query(Constant.string.DB_ARTICLE_BROWSE, null, null, null, null, null, null);
            ArticleBean bean;
            while (cursor.moveToNext()) {
                bean = new ArticleBean();
                bean.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                bean.setIcon(cursor.getString(cursor.getColumnIndex("icon")));
                bean.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                bean.setDesc(cursor.getString(cursor.getColumnIndex("desc")));
                bean.setHints(cursor.getInt(cursor.getColumnIndex("hints")));
                bean.setTime(cursor.getString(cursor.getColumnIndex("time")));
                bean.setAddTime(cursor.getString(cursor.getColumnIndex("addTime")));
                list.add(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != cursor) {
                cursor.close();
            }
            if (null != db) {
                db.close();
            }
        }
        return list;
    }

    /**
     * 获取全部的视频浏览记录
     *
     * @return
     */
    public List<VideoBean> getVideoBrowse() {
        List<VideoBean> list = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = mReaderOpenHelper.getReadableDatabase();
            cursor = db.query(Constant.string.DB_VIDEO_BROWSE, null, null, null, null, null, null);
            VideoBean bean;
            while (cursor.moveToNext()) {
                bean = new VideoBean();
                bean.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                bean.setIcon(cursor.getString(cursor.getColumnIndex("icon")));
                bean.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                bean.setDesc(cursor.getString(cursor.getColumnIndex("desc")));
                bean.setHints(cursor.getInt(cursor.getColumnIndex("hints")));
                bean.setPublishTime(cursor.getString(cursor.getColumnIndex("publishTime")));
                bean.setTime(cursor.getString(cursor.getColumnIndex("time")));
                list.add(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != cursor) {
                cursor.close();
            }
            if (null != db) {
                db.close();
            }
        }
        return list;
    }


    /**
     * 获取全部的文章收藏
     *
     * @return
     */
    public List<ArticleBean> getArticleFavorite() {
        List<ArticleBean> list = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = mReaderOpenHelper.getReadableDatabase();
            cursor = db.query(Constant.string.DB_ARTICLE_FAVORITE, null, null, null, null, null, null);
            ArticleBean bean;
            while (cursor.moveToNext()) {
                bean = new ArticleBean();
                bean.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                bean.setIcon(cursor.getString(cursor.getColumnIndex("icon")));
                bean.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                bean.setDesc(cursor.getString(cursor.getColumnIndex("desc")));
                bean.setHints(cursor.getInt(cursor.getColumnIndex("hints")));
                bean.setTime(cursor.getString(cursor.getColumnIndex("time")));
                bean.setAddTime(cursor.getString(cursor.getColumnIndex("addTime")));
                list.add(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != cursor) {
                cursor.close();
            }
            if (null != db) {
                db.close();
            }
        }
        return list;
    }

    /**
     * 获取全部的视频收藏
     *
     * @return
     */
    public List<VideoBean> getVideoFavorite() {
        List<VideoBean> list = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = mReaderOpenHelper.getReadableDatabase();
            cursor = db.query(Constant.string.DB_VIDEO_FAVORITE, null, null, null, null, null, null);
            VideoBean bean;
            while (cursor.moveToNext()) {
                bean = new VideoBean();
                bean.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                bean.setIcon(cursor.getString(cursor.getColumnIndex("icon")));
                bean.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                bean.setDesc(cursor.getString(cursor.getColumnIndex("desc")));
                bean.setHints(cursor.getInt(cursor.getColumnIndex("hints")));
                bean.setPublishTime(cursor.getString(cursor.getColumnIndex("publishTime")));
                bean.setTime(cursor.getString(cursor.getColumnIndex("time")));
                list.add(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != cursor) {
                cursor.close();
            }
            if (null != db) {
                db.close();
            }
        }
        return list;
    }

    /**
     * 查询文章浏览
     *
     * @return
     */
    public boolean isBrowseArticleById(int id) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = mReaderOpenHelper.getReadableDatabase();
            cursor = db.rawQuery("select _id from " + Constant.string.DB_ARTICLE_BROWSE + " where _id = ?", new String[]{String.valueOf(id)});
            if (cursor.moveToNext()) {
                LogUtil.i("查询的id =  "+cursor.getInt(cursor.getColumnIndex("_id")));
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != cursor) {
                cursor.close();
            }
            if (null != db) {
                db.close();
            }
        }
        return false;
    }



    /**
     * 查询文章收藏
     *
     * @return
     */
    public boolean isFavoriteArticleById(int id) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = mReaderOpenHelper.getReadableDatabase();
            cursor = db.rawQuery("select _id from " + Constant.string.DB_ARTICLE_FAVORITE + " where _id = ?", new String[]{String.valueOf(id)});
            if (cursor.moveToNext()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != cursor) {
                cursor.close();
            }
            if (null != db) {
                db.close();
            }
        }
        return false;
    }

    /**
     * 查询视频收藏
     *
     * @return
     */
    public boolean isFavoriteVideoById(int id) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = mReaderOpenHelper.getReadableDatabase();
            cursor = db.rawQuery("select _id from " + Constant.string.DB_VIDEO_FAVORITE + " where _id = ?", new String[]{String.valueOf(id)});
            if (cursor.moveToNext()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != cursor) {
                cursor.close();
            }
            if (null != db) {
                db.close();
            }
        }
        return false;
    }

    /**
     * 删除所有的文章浏览记录
     *
     */
    public void deleteBrowseArticle( ) {
        SQLiteDatabase db = mReaderOpenHelper.getReadableDatabase();
        db.execSQL(Constant.string.CLEAR_TABLE+Constant.string.DB_ARTICLE_BROWSE);
    }

    /**
     * 删除所有的文章浏览记录
     *
     */
    public void deleteFrvoriteArticle( ) {
        SQLiteDatabase db = mReaderOpenHelper.getReadableDatabase();
        db.execSQL(Constant.string.CLEAR_TABLE+Constant.string.DB_ARTICLE_FAVORITE);
    }

    /**
     * 删除所有的视频浏览记录
     *
     */
    public void deleteBrowseVideo( ) {
        SQLiteDatabase db = mReaderOpenHelper.getReadableDatabase();
        db.execSQL(Constant.string.CLEAR_TABLE+Constant.string.DB_VIDEO_BROWSE);
    }
    /**
     * 删除所有的视频收藏
     *
     */
    public void deleteFavoriteVideo( ) {
        SQLiteDatabase db = mReaderOpenHelper.getReadableDatabase();
        db.execSQL(Constant.string.CLEAR_TABLE+Constant.string.DB_VIDEO_FAVORITE);
    }

    /**
     * 清空数据库
     *
     */
    public void deleteAllDB( ) {
        deleteBrowseArticle();
        deleteFrvoriteArticle();
        deleteFavoriteVideo();
        deleteBrowseVideo();
    }


    /**
     * 删除谋篇的文章浏览记录
     *
     */
    public void deleteBrowseArticleById(int id ) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = mReaderOpenHelper.getReadableDatabase();
            db.delete(Constant.string.DB_ARTICLE_BROWSE, "_id = " + id, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != cursor) {
                cursor.close();
            }
            if (null != db) {
                db.close();
            }
        }

    }



    /**
     * 取消收藏的文章
     *
     * @param id
     */
    public void deleteFavoriteArticleById(int id) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = mReaderOpenHelper.getReadableDatabase();
            db.delete(Constant.string.DB_ARTICLE_FAVORITE, "_id = " + id, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != cursor) {
                cursor.close();
            }
            if (null != db) {
                db.close();
            }
        }
    }

    /**
     * 取消收藏的视频
     *
     * @param id
     */
    public void deleteFavoriteVideoById(int id) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = mReaderOpenHelper.getReadableDatabase();
            db.delete(Constant.string.DB_VIDEO_FAVORITE, "_id = " + id, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != cursor) {
                cursor.close();
            }
            if (null != db) {
                db.close();
            }
        }
    }


}
