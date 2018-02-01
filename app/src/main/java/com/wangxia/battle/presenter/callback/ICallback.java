package com.wangxia.battle.presenter.callback;

/**
 * presenter回调model里面的数据
 */
public interface ICallback <T>{
        void success( T t, int type);
        void error();
        void fail();
}
