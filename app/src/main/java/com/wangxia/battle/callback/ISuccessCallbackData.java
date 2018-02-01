package com.wangxia.battle.callback;


/**
 * view回调presenter的数据
 */
public interface ISuccessCallbackData<T>{
    void getResult(T t,int type);
    void failRequest( );
    void errorRequest( );
}
