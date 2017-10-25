package com.wangxia.battle.globe;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.umeng.analytics.MobclickAgent;
import com.wangxia.battle.db.ReaderManager;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.GsonUtil;
import com.wangxia.battle.util.HttpUtil;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by 昝奥博 on 2017/9/12 0012
 * Email:18772833900@163.com
 * Explain：
 */
public class App extends Application {
    /**
     * 全局上下文
     */
    public static Context context;
    public static ReaderManager mReaderManager;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Fresco.initialize(this);
        HttpUtil.getInstance();
        GsonUtil.getInstance();
        mReaderManager = new ReaderManager(context);
        MobclickAgent.setScenarioType(context, MobclickAgent.EScenarioType.E_UM_NORMAL);
        //初始化sdk
        JPushInterface.setDebugMode(false);//正式版的时候设置false，关闭调试
        JPushInterface.init(this);
        //添加tag标签，发送消息的之后就可以指定tag标签来发送了
        Set<String> set = new HashSet<>(Constant.number.FIVE);
        set.add(Constant.jPushTag.GAME);
        set.add(Constant.jPushTag.ARTICLE);
        set.add(Constant.jPushTag.VIDEO);
        set.add(Constant.jPushTag.MESSAGE);
        set.add(Constant.jPushTag.ACTIVITY);
        JPushInterface.setTags(this, set, null);//设置标签
    }
}
