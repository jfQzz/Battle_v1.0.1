package com.wangxia.battle.model.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by 昝奥博 on 2017/9/18 0018
 * Email:18772833900@163.com
 * Explain：本地游戏对象
 */
public class LocalAppBean {
    //图标
    private Drawable appIcon;
    //应用名称
    private String appName;
    //应用版本号
    private String appVersion;
    //应用包名
    private String packagename;
    //是否是用户app
    private boolean isUserApp;

    private String filePath;


    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public boolean isUserApp() {
        return isUserApp;
    }

    public void setUserApp(boolean userApp) {
        isUserApp = userApp;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalAppBean(Drawable appIcon, String appName, String appVersion, String packagename, boolean isUserApp, String filePath) {
        this.appIcon = appIcon;
        this.appName = appName;
        this.appVersion = appVersion;
        this.packagename = packagename;
        this.isUserApp = isUserApp;
        this.filePath = filePath;
    }

    public LocalAppBean() {
    }
}
