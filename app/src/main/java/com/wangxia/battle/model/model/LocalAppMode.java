package com.wangxia.battle.model.model;

import android.os.AsyncTask;

import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.db.bean.ArticleBean;
import com.wangxia.battle.db.bean.VideoBean;
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

                    @Override
                    public void failRequest() {
                        iCallback.fail();
                    }

                    @Override
                    public void errorRequest() {
                        iCallback.error();

                    }
                });
                break;
            case Constant.number.ONE:

                break;
            case Constant.number.TWO:
                new AsyncTask<String, Integer, List<VideoBean>>() {
                    @Override
                    protected List<VideoBean> doInBackground(String... params) {
                        return App.mReaderManager.getVideoBrowse();
                    }

                    @Override
                    protected void onPostExecute(List<VideoBean> videoBrowseBeen) {
                        iCallback.success(videoBrowseBeen, type);
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
                new AsyncTask<String, Integer, List<VideoBean>>() {
                    @Override
                    protected List<VideoBean> doInBackground(String... params) {
                        return App.mReaderManager.getVideoFavorite();
                    }

                    @Override
                    protected void onPostExecute(List<VideoBean> videoFavoriteBeen) {
                        iCallback.success(videoFavoriteBeen, type);
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
