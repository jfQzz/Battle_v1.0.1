package com.wangxia.battle.presenter.impPresenter;

import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.model.model.CurseListModel;
import com.wangxia.battle.presenter.IPresenter;
import com.wangxia.battle.presenter.callback.ICallback;


/**
 *
 *
 */
public class CurseListPresenter implements IPresenter,ICallback {
    private CurseListModel curseListModel = new CurseListModel();
    private ISuccessCallbackData iAppInfo;

    public CurseListPresenter(ISuccessCallbackData iAppInfo) {
        this.iAppInfo = iAppInfo;
    }

    public void queryList(int id ,String args,int pageNo){
        curseListModel.queryList(id,args,pageNo, this);
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
