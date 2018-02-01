package com.wangxia.battle.presenter.impPresenter;

import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.model.model.ApiRegisterModel;
import com.wangxia.battle.presenter.IPresenter;
import com.wangxia.battle.presenter.callback.ICallback;


/**
 * 第三方祖册
 *
 */
public class ApiRegisterPresenter implements IPresenter,ICallback {
    private ApiRegisterModel apiRegisterModel = new ApiRegisterModel();
    private ISuccessCallbackData iAppInfo;

    public ApiRegisterPresenter(ISuccessCallbackData iAppInfo) {
        this.iAppInfo = iAppInfo;
    }

    public void queryList(int id ,String args,int pageNo){
        apiRegisterModel.queryList(id,args,pageNo, this);
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
