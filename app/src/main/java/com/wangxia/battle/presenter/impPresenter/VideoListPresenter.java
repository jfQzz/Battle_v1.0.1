package com.wangxia.battle.presenter.impPresenter;

import com.wangxia.battle.presenter.IPresenter;
import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.model.model.VideoListModel;
import com.wangxia.battle.presenter.callback.ICallback;


/**
 * 首页的presenter
 *
 */
public class VideoListPresenter implements IPresenter,ICallback {
    private VideoListModel videoListModel = new VideoListModel();
    private ISuccessCallbackData iAppInfo;

    public VideoListPresenter(ISuccessCallbackData iAppInfo) {
        this.iAppInfo = iAppInfo;
    }

    public void queryList(int id ,String args,int pageNo){
        videoListModel.queryList(id,args,pageNo, this);
    }

    @Override
    public void success(Object appInfo,int type) {
          iAppInfo.getResult(appInfo,type);
    }
    @Override
    public void error( ) {
        iAppInfo.errorRequest();
    }

    @Override
    public void fail() {
        iAppInfo.failRequest();}
}
