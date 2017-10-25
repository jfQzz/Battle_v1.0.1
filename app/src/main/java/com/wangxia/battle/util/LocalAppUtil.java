package com.wangxia.battle.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.model.bean.LocalAppBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 昝奥博 on 2017/9/18 0018
 * Email:18772833900@163.com
 * Explain：
 */
public class LocalAppUtil {

private static ISuccessCallbackData iSuccessCallbackData;

    public void setLocalAppInterface(ISuccessCallbackData iSuccessCallbackData){
        this.iSuccessCallbackData = iSuccessCallbackData;
    }

    public LocalAppUtil init(Context context){
        new MyAsyncTask().execute(context.getPackageManager());
        return this;
    }

     static class MyAsyncTask extends AsyncTask<PackageManager, Integer, List<LocalAppBean>> {

        @Override
        protected List<LocalAppBean> doInBackground(PackageManager... packageManager) {
            return findLocalApp(packageManager[0]);
        }

        @Override
        protected void onPostExecute(List<LocalAppBean> localAppInfos) {
            super.onPostExecute(localAppInfos);
            //请求网络得到本地安装的游戏，发送app名称以“，”号隔开
            iSuccessCallbackData.getResult(localAppInfos,Constant.number.ZERO);
        }
    }

    /**
     * 搜索本地游戏
     */
    private static List<LocalAppBean> findLocalApp(PackageManager mPackageManager) {
        //创建要返回的集合对象
        List<LocalAppBean> appInfos = new ArrayList<>();
        //获取手机中所有安装的应用集合
        List<ApplicationInfo> applicationInfos = mPackageManager.getInstalledApplications(0);
        //遍历所有的应用集合
        for (ApplicationInfo info : applicationInfos) {
            //排除系统自带应用
            if (filterApp(info)) {
                //排除网侠手游宝
                if (!TextUtils.equals("com.wangxia.legend", info.packageName)) {
                    LocalAppBean appInfo = new LocalAppBean();
                    //获取应用程序的图标
                    Drawable app_icon = info.loadIcon(mPackageManager);
                    appInfo.setAppIcon(app_icon);
                    //获取应用的名称
                    String app_name = info.loadLabel(mPackageManager).toString();
                    appInfo.setAppName(app_name);
                    appInfo.setFilePath(info.sourceDir);
                    //获取应用的包名
                    try {
                        String packageName = info.packageName;
                        appInfo.setPackagename(packageName);
                        //获取应用的版本号
                        PackageInfo packageInfo = mPackageManager.getPackageInfo(packageName, 0);
                        String app_version = packageInfo.versionName;
                        appInfo.setAppVersion(app_version);
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                    appInfo.setUserApp(true);
                    appInfos.add(appInfo);
                }

            }
        }
        return appInfos;
    }


    //判断应用程序是否是用户程序
    private static boolean filterApp(ApplicationInfo info) {
        //原来是系统应用，用户手动升级(info标志位等于0代表用户个人安装的)
        if ((info.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
            return true;
            //用户自己安装的应用程序
        } else if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
            return true;
        }
        return false;
    }

}

