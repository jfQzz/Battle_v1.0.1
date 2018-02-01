package com.wangxia.battle.callback;

/**
 * Created by 昝奥博 on 2018/1/6 0006
 * Email:18772833900@163.com
 * Explain：监听网络变化
 */
public interface NetworkListener {
    void changeToMobile();
    void changeToWifi();
    void noNet();
}
