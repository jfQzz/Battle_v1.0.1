package com.wangxia.battle.model.model;

import android.os.AsyncTask;

import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.db.bean.ArticleBean;
import com.wangxia.battle.db.bean.GameBean;
import com.wangxia.battle.globe.App;
import com.wangxia.battle.model.IModel;
import com.wangxia.battle.presenter.callback.ICallback;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.LocalAppUtil;

import java.util.List;

/**
 * Created by 昝奥博 on 2017/9/18 0018
 * Email:18772833900@163.com
 * Explain：本地数据库数据调用
 */
public class LocalAppMode implements IModel {

    @Override
    public void queryList(final int type,String args, int pageNo, final ICallback iCallback) {
        switch (type) {
            case Constant.number.ZERO:
                new LocalAppUtil().init(App.context).setLocalAppInterface(new ISuccessCallbackData() {

                    @Override
                    public void getResult(Object dataBen, int type) {
                        iCallback.success(dataBen,type);
                    }
                });
                break;
            case Constant.number.ONE:
                new AsyncTask<String, Integer, List<GameBean>>() {
                    @Override
                    protected List<GameBean> doInBackground(String... params) {
                        return App.mReaderManager.getDownHistroy();
                    }

                    @Override
                    protected void onPostExecute(List<GameBean> downListBeen) {
                        iCallback.success(downListBeen, type);
                    }
                }.execute();

                break;
            case Constant.number.TWO:
                new AsyncTask<String, Integer, List<GameBean>>() {
                    @Override
                    protected List<GameBean> doInBackground(String... params) {
                        return App.mReaderManager.getGameBrowse();
                    }

                    @Override
                    protected void onPostExecute(List<GameBean> gameBrowseBeen) {
                        iCallback.success(gameBrowseBeen, type);
                    }
                }.execute();

                break;
            case Constant.number.THREE:
                new AsyncTask<String, Integer, List<ArticleBean>>() {
                    @Override
                    protected List<ArticleBean> doInBackground(String... params) {
                        return App.mReaderManager.getArticleBrowse();
                    }

                    @Override
                    protected void onPostExecute(List<ArticleBean> articleBrowseBeen) {
                        iCallback.success(articleBrowseBeen, type);
                    }
                }.execute();

                break;
            case Constant.number.FORE:
                new AsyncTask<String, Integer, List<GameBean>>() {
                    @Override
                    protected List<GameBean> doInBackground(String... params) {
                        return App.mReaderManager.getGameFavorite();
                    }

                    @Override
                    protected void onPostExecute(List<GameBean> gameFavoriteBeen) {
                        iCallback.success(gameFavoriteBeen, type);
                    }
                }.execute();
                break;
            case Constant.number.FIVE:
                new AsyncTask<String, Integer, List<ArticleBean>>() {
                    @Override
                    protected List<ArticleBean> doInBackground(String... params) {
                        return App.mReaderManager.getArticleFavorite();
                    }

                    @Override
                    protected void onPostExecute(List<ArticleBean> articleFavoriteBeen) {
                        iCallback.success(articleFavoriteBeen, type);
                    }
                }.execute();
                break;
        }
    }
}
