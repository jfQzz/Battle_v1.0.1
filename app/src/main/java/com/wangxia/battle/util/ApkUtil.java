package com.wangxia.battle.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;

import com.wangxia.battle.globe.App;

import java.io.File;
import java.util.List;

/**
 * Created by 昝奥博 on 2017/10/9 0009
 * Email:18772833900@163.com
 * Explain：apk安装，卸载，打开，工具
 */
public class ApkUtil {
    /**
     * @param context 上下文
     * @param path apk的绝对路径
     */
    public static void installApk(@NonNull Context context,@NonNull String path) {
        try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            File file = new File(path);
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M ) {
                Uri apkUri = FileProvider.getUriForFile(App.context, App.context.getPackageName() + ".apkDownload",file );
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(apkUri,  "application/vnd.android.package-archive");
                LogUtil.i(apkUri.toString());
            }else{
                intent.setDataAndType(Uri.fromFile(file),
                        "application/vnd.android.package-archive");
            }

            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getApkPath(String path, String name) {
        return String.valueOf(path + File.separator + name + ".apk");
    }

    public static boolean isInstall(Context context, String packageStr,@NonNull String appName) {
        return TextUtils.isEmpty(packageStr) ? isInstallByName(context,appName) : isInstallByPackage(context,packageStr);
    }

    public static boolean isInstallByPackage(Context context, String packageName) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
        }
        return packageInfo != null;
    }

    /**
     * 根据app名称查看是否安装,安装了的返回包名
     */
    public static boolean isInstallByName(Context context, String appName) {

        List<PackageInfo> pInfo = context.getPackageManager().getInstalledPackages(PackageManager.GET_PERMISSIONS);
        for (int i = 0; i < pInfo.size(); i++) {
            //app名称
            String appPackName = pInfo.get(i).applicationInfo.loadLabel(context.getPackageManager()).toString();
            if (appName.equals(appPackName)) {
                //用sp保存包名,在打开时用
                SpUtil.putString(context, appName, pInfo.get(i).packageName);
                return true;
            }
        }
        return false;
    }

    public static void openApk(Context context,String  packageStr) {

        // 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等
        PackageInfo packageinfo = null;
        try {
            packageinfo = context.getPackageManager().getPackageInfo(packageStr, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageinfo == null) {
            return;
        }
        // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(packageinfo.packageName);

        // 通过getPackageManager()的queryIntentActivities方法遍历
        List<ResolveInfo> resolveinfoList = context.getPackageManager()
                .queryIntentActivities(resolveIntent, 0);
        ResolveInfo resolveinfo = resolveinfoList.iterator().next();
        if (resolveinfo != null) {
            String packageName = resolveinfo.activityInfo.packageName;
            String className = resolveinfo.activityInfo.name;
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName cn = new ComponentName(packageName, className);
            intent.setComponent(cn);
            context.startActivity(intent);
        }
    }

    /**
     * 获取当前本地apk的版本
     *
     * @param mContext
     * @return
     */
    public static int getVersionCode(Context mContext) {
        int versionCode = 0;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = mContext.getPackageManager().
                    getPackageInfo(mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取版本号名称
     *
     * @param context 上下文
     * @return
     */
    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }
}
