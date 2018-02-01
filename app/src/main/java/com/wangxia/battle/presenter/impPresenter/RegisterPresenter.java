package com.wangxia.battle.presenter.impPresenter;

import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.model.model.RegisterModel;
import com.wangxia.battle.presenter.IPresenter;
import com.wangxia.battle.presenter.callback.ICallback;


/**
 *注册
 */
public class RegisterPresenter implements IPresenter,ICallback {
    private RegisterModel registerModel = new RegisterModel();
    private ISuccessCallbackData iAppInfo;

    public RegisterPresenter(ISuccessCallbackData iAppInfo) {
        this.iAppInfo = iAppInfo;
    }

    public void queryList(int id ,String args,int pageNo){
        registerModel.queryList(id,args,pageNo, this);
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
