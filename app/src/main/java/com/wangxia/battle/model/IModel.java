package com.wangxia.battle.model;


import com.wangxia.battle.presenter.callback.ICallback;

/**
 * model基类
 */
public interface IModel {
    void queryList(int id, String arg,int pageNo, final ICallback iCallback);
}
