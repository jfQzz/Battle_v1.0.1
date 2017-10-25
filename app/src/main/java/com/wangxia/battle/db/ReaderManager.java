package com.wangxia.battle.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wangxia.battle.db.bean.ArticleBean;
import com.wangxia.battle.db.bean.DownListBean;
import com.wangxia.battle.db.bean.GameBean;
import com.wangxia.battle.util.Constant;

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

    public void addDownHistoryDB(int id, String icon, String title, long size, String labels, String mark, String time) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = mReaderOpenHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            cursor = db.rawQuery("select _id from " + Constant.string.DB_DOWN_HISTORY + " where _id=?", new String[]{String.valueOf(id)});
            if (cursor.moveToNext()) {
                values.put("time", time);
                db.update(Constant.string.DB_DOWN_HISTORY, values, "_id = ?", new String[]{String.valueOf(id)});
            } else {
                values.put("_id", id);
                values.put("icon", icon);
                values.put("title", title);
                values.put("size", size);
                values.put("labels", labels);
                values.put("mark", mark);
                values.put("time", time);
                db.insert(Constant.string.DB_DOWN_HISTORY, null, values);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != db) {
                db.close();
            }
            if(null != cursor){
                cursor.close();
            }
        }
    }

    public void addDownListDB(int id, String icon, String title, int size, String labels, String mark, int state, int currentLength, String downUrl, String path, String speed, Long downloadId) {
        SQLiteDatabase db = null;
        db = mReaderOpenHelper.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("_id", id);
            values.put("icon", icon);
            values.put("title", title);
            values.put("size", size);
            values.put("labels", labels);
            values.put("mark", mark);
            values.put("state", state);
            values.put("currentLength", currentLength);
            values.put("downUrl", downUrl);
            values.put("path", path);
            values.put("speed", speed);
            values.put("downloadId", downloadId);
            db.insert(Constant.string.DB_DOWN_MANAGER, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != db) {
                db.close();
            }
        }
    }


    public void addGameBrowseDB(int id, String icon, String title, long size, String labels, String mark, String time) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = mReaderOpenHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            cursor = db.rawQuery("select _id from " + Constant.string.DB_GAME_BROWSE + " where _id=?", new String[]{String.valueOf(id)});
            if (cursor.moveToNext()) {
                values.put("time", time);
                db.update(Constant.string.DB_GAME_BROWSE, values, "_id = ?", new String[]{String.valueOf(id)});
            } else {
                values.put("_id", id);
                values.put("icon", icon);
                values.put("title", title);
                values.put("size", size);
                values.put("labels", labels);
                values.put("mark", mark);
                values.put("time", time);
                db.insert(Constant.string.DB_GAME_BROWSE, null, values);
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
    }

    public void addArticleBrowseDB(int id, String icon, String title, int hints, String publishTime, String author, String authorIco, String time) {
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
                values.put("hints", hints);
                values.put("publishTime", publishTime);
                values.put("author", author);
                values.put("authorIco", authorIco);
                values.put("time", time);
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

    public void addGameFavoriteDB(int id, String icon, String title, long size, String labels, String mark, String time) {
        SQLiteDatabase db = null;
        try {
            db = mReaderOpenHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("_id", id);
            values.put("icon", icon);
            values.put("title", title);
            values.put("size", size);
            values.put("labels", labels);
            values.put("mark", mark);
            values.put("time", time);
            db.insert(Constant.string.DB_GAME_FAVORITE, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != db) {
                db.close();
            }
        }
    }

    public void addArticleFavoriteDB(int id, String icon, String title, int hints, String publishTime, String author, String authorIco, String time) {
        SQLiteDatabase db = null;
        try {
            db = mReaderOpenHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("_id", id);
            values.put("icon", icon);
            values.put("title", title);
            values.put("hints", hints);
            values.put("publishTime", publishTime);
            values.put("author", author);
            values.put("authorIco", authorIco);
            values.put("time", time);
            db.insert(Constant.string.DB_ARTICLE_FAVORITE, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != db) {
                db.close();
            }
        }
    }


    /**
     * 获取全部的下载记录
     *
     * @return
     */
    public List<GameBean> getDownHistroy() {
        List<GameBean> list = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = mReaderOpenHelper.getReadableDatabase();
            cursor = db.query(Constant.string.DB_DOWN_HISTORY, null, null, null, null, null, null);
            GameBean bean;
            while (cursor.moveToNext()) {
                bean = new GameBean();
                bean.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                bean.setIcon(cursor.getString(cursor.getColumnIndex("icon")));
                bean.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                bean.setSize(cursor.getLong(cursor.getColumnIndex("size")));
                bean.setLabels(cursor.getString(cursor.getColumnIndex("labels")));
                bean.setMark(cursor.getString(cursor.getColumnIndex("mark")));
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
     * 获取全部的下载管理记录
     *
     * @return
     */
    public List<DownListBean> getDownList() {
        List<DownListBean> list = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = mReaderOpenHelper.getReadableDatabase();
            cursor = db.query(Constant.string.DB_DOWN_MANAGER, null, null, null, null, null, null);
            DownListBean bean;
            while (cursor.moveToNext()) {
                bean = new DownListBean();
                bean.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                bean.setIcon(cursor.getString(cursor.getColumnIndex("icon")));
                bean.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                bean.setSize(cursor.getLong(cursor.getColumnIndex("size")));
                bean.setLabels(cursor.getString(cursor.getColumnIndex("labels")));
                bean.setMark(cursor.getString(cursor.getColumnIndex("mark")));
                bean.setState(cursor.getInt(cursor.getColumnIndex("state")));
                bean.setCurrentLength(cursor.getLong(cursor.getColumnIndex("currentLength")));
                bean.setDownUrl(cursor.getString(cursor.getColumnIndex("downUrl")));
                bean.setPath(cursor.getString(cursor.getColumnIndex("path")));
                bean.setSpeed(cursor.getString(cursor.getColumnIndex("speed")));
                bean.setDownloadId(cursor.getLong(cursor.getColumnIndex("downloadId")));
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
     * 获取全部的游戏浏览记录
     *
     * @return
     */
    public List<GameBean> getGameBrowse() {
        List<GameBean> list = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = mReaderOpenHelper.getReadableDatabase();
            cursor = db.query(Constant.string.DB_GAME_BROWSE, null, null, null, null, null, null);
            GameBean bean;
            while (cursor.moveToNext()) {
                bean = new GameBean();
                bean.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                bean.setIcon(cursor.getString(cursor.getColumnIndex("icon")));
                bean.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                bean.setSize(cursor.getLong(cursor.getColumnIndex("size")));
                bean.setLabels(cursor.getString(cursor.getColumnIndex("labels")));
                bean.setMark(cursor.getString(cursor.getColumnIndex("mark")));
                bean.setTime(cursor.getString(cursor.getColumnIndex("time")));
//                bean.setId(cursor.getInt(Constant.number.ZERO));
//                bean.setIcon(cursor.getString(Constant.number.TWO));
//                bean.setTitle(cursor.getString(Constant.number.THREE));
//                bean.setSize(cursor.getLong(Constant.number.FORE));
//                bean.setLabels(cursor.getString(Constant.number.FIVE));
//                bean.setMark(cursor.getString(Constant.number.SIX));
//                bean.setTime(cursor.getString(Constant.number.SEVEN));
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
                bean.setHints(cursor.getInt(cursor.getColumnIndex("hints")));
                bean.setPublishTime(cursor.getString(cursor.getColumnIndex("publishTime")));
                bean.setAuthor(cursor.getString(cursor.getColumnIndex("author")));
                bean.setAuthorIco(cursor.getString(cursor.getColumnIndex("authorIco")));
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
     * 获取全部的游戏收藏
     *
     * @return
     */
    public List<GameBean> getGameFavorite() {
        List<GameBean> list = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = mReaderOpenHelper.getReadableDatabase();
            cursor = db.query(Constant.string.DB_GAME_FAVORITE, null, null, null, null, null, null);
            GameBean bean;
            while (cursor.moveToNext()) {
                bean = new GameBean();
                bean.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                bean.setIcon(cursor.getString(cursor.getColumnIndex("icon")));
                bean.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                bean.setSize(cursor.getLong(cursor.getColumnIndex("size")));
                bean.setLabels(cursor.getString(cursor.getColumnIndex("labels")));
                bean.setMark(cursor.getString(cursor.getColumnIndex("mark")));
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
                bean.setHints(cursor.getInt(cursor.getColumnIndex("hints")));
                bean.setPublishTime(cursor.getString(cursor.getColumnIndex("publishTime")));
                bean.setAuthor(cursor.getString(cursor.getColumnIndex("author")));
                bean.setAuthorIco(cursor.getString(cursor.getColumnIndex("authorIco")));
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
     * 查询下载
     *
     * @param id
     * @return
     */
    public boolean isDownAppById(int id) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = mReaderOpenHelper.getReadableDatabase();
            cursor = db.rawQuery("select _id, from " + Constant.string.DB_DOWN_MANAGER + " where _id=?", new String[]{String.valueOf(id)});
            if (null != cursor) {
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
     * 查询游戏收藏
     *
     * @return
     */
    public boolean isFavoriteAppById(int id) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = mReaderOpenHelper.getReadableDatabase();
            cursor = db.rawQuery("select _id from " + Constant.string.DB_GAME_FAVORITE + " where _id = ?", new String[]{String.valueOf(id)});
            if (cursor.moveToNext()) {
//                    int localId = cursor.getInt(0);
//                    LogUtil.i(localId+"     "+id);
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
     * 删除下载的游戏
     *
     * @param id
     */
    public void deleteDownBeanById(int id) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = mReaderOpenHelper.getReadableDatabase();
            db.delete(Constant.string.DB_DOWN_MANAGER, "_id = " + id, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != cursor) {
                cursor.close();
            }
            db.close();
        }
    }


    /**
     * 取消收藏的游戏
     *
     * @param id
     */
    public void deleteFavoriteGameById(int id) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = mReaderOpenHelper.getReadableDatabase();
            db.delete(Constant.string.DB_GAME_FAVORITE, "_id = " + id, null);
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
     * 下载游戏更新
     *
     * @param id
     * @param currentLength
     */
    public void updateDown(int id, int currentLength) {

        SQLiteDatabase db = null;
        try {
            db = mReaderOpenHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("currentLength", currentLength);
            String whereClause = "_id = ?";
            String[] whereArgs = {String.valueOf(id)};
            db.update(Constant.string.DB_DOWN_MANAGER, values, whereClause, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != db) {
                db.close();
            }
        }
    }

    /**
     * 下载游戏状态更新
     *
     * @param id
     * @param state
     */
    public void updateDownState(int id, int state) {

        SQLiteDatabase db = null;
        try {
            db = mReaderOpenHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("state", state);
            String whereClause = "_id = ?";
            String[] whereArgs = {String.valueOf(id)};
            db.update(Constant.string.DB_DOWN_MANAGER, values, whereClause, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != db) {
                db.close();
            }
        }
    }

    /**
     * 获取下载完成的app对象
     */
    public List<Long> getFinishDownloadApp() {
        List<Long> list = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = mReaderOpenHelper.getReadableDatabase();
            cursor = db.rawQuery("select downloadId from " + Constant.string.DB_DOWN_MANAGER + " where state = ?", new String[]{String.valueOf(Constant.downloadState.DOWNLOAD_COMPLETE)});
            while (cursor.moveToNext()) {
                list.add(cursor.getLong(cursor.getColumnIndex("downloadId")));
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

    public void changeDownload(long downloadId, int type) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = mReaderOpenHelper.getReadableDatabase();
            cursor = db.rawQuery("select _id from " + Constant.string.DB_DOWN_MANAGER + " where downloadId = ?", new String[]{String.valueOf(downloadId)});
            while (cursor.moveToNext()) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("state", type);
                db.update(Constant.string.DB_DOWN_MANAGER, contentValues, "_id = ?", new String[]{String.valueOf(cursor.getInt(cursor.getColumnIndex("_id")))});
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
    }

}
