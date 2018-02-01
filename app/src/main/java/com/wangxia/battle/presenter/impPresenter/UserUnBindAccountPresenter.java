package com.wangxia.battle.presenter.impPresenter;

import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.model.model.UserUnBindAccountModel;
import com.wangxia.battle.presenter.IPresenter;
import com.wangxia.battle.presenter.callback.ICallback;


/**
 * 第三方解绑
 */
public class UserUnBindAccountPresenter implements IPresenter,ICallback {
    private UserUnBindAccountModel userUnBindAccountModel = new UserUnBindAccountModel();
    private ISuccessCallbackData iAppInfo;

    public UserUnBindAccountPresenter(ISuccessCallbackData iAppInfo) {
        this.iAppInfo = iAppInfo;
    }

    public void queryList(int id ,String args,int pageNo){
        userUnBindAccountModel.queryList(id,args,pageNo, this);
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
