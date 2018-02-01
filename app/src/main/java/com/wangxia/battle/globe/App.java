package com.wangxia.battle.globe;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.tencent.smtt.sdk.QbSdk;
import com.umeng.analytics.MobclickAgent;
import com.wangxia.battle.db.ReaderManager;
import com.wangxia.battle.model.bean.ArmBean;
import com.wangxia.battle.model.bean.CurseBean;
import com.wangxia.battle.model.bean.HeroBean;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.GsonUtil;
import com.wangxia.battle.util.HttpUtil;
import com.wangxia.battle.util.LogUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
    public static  Map<String, String> picMap;
    public static  Map<String, List<HeroBean.DataBean>> heroMap;
    public static  Map<String, HeroBean.DataBean> heroNameMap;
    public static  Map<Integer, CurseBean.DataBean> curseMap;
    public static Map<String,Map<String,List<ArmBean.DataBean>>> armMap;
    public static Map<String,ArmBean.DataBean> armNameMap;
    public static Map<Integer,ArmBean.DataBean> armIdMap;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Fresco.initialize(this);
        HttpUtil.getInstance();
        GsonUtil.getInstance();
        mReaderManager = new ReaderManager(context);
        MobclickAgent.setScenarioType(context, MobclickAgent.EScenarioType.E_UM_NORMAL);
        WbSdk.install(this,new AuthInfo(this,Constant.platformID.LOGIN_SINA_KEY,Constant.platformID.LOGIN_SINA_REDIRECT_URL,Constant.platformID.LOGIN_SINA_SCOPE));
//        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
//        options.setAcceptInvitationAlways(false);
//        options.setAutoLogin(true);
//        options.allowChatroomOwnerLeave(true);
//        EMClient.getInstance().init(context, options);
//        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
//        EMClient.getInstance().setDebugMode(true);
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
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                LogUtil.i("app", " onViewInitFinished is " + arg0);
            }
            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(),  cb);
    }
}
