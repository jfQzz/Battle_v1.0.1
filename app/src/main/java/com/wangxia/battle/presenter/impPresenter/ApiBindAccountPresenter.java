package com.wangxia.battle.presenter.impPresenter;

import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.model.bean.SuccessBean;
import com.wangxia.battle.model.model.ApiBindAccountModel;
import com.wangxia.battle.presenter.IPresenter;
import com.wangxia.battle.presenter.callback.ICallback;


/**
 * 第三方绑定当前账户
 *
 */
public class ApiBindAccountPresenter implements IPresenter,ICallback<SuccessBean> {
    private ApiBindAccountModel apiBindAccountModel = new ApiBindAccountModel();
    private ISuccessCallbackData iAppInfo;

    public ApiBindAccountPresenter(ISuccessCallbackData iAppInfo) {
        this.iAppInfo = iAppInfo;
    }

    public void queryList(int id , String args, int pageNo){
        apiBindAccountModel.queryList(id,args,pageNo, this);
    }

    @Override
    public void success(SuccessBean successBean,int type) {
          iAppInfo.getResult(successBean,type);
    }


    @Override
    public void error( ) {
        iAppInfo.errorRequest();
    }

    @Override
    public void fail( ) {
        iAppInfo.failRequest();}

}
