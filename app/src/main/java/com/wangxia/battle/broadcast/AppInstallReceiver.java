package com.wangxia.battle.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.DataCleanManager;

import java.io.File;

/**
 * Created by 昝奥博 on 2017/9/21 0021
 * Email:18772833900@163.com
 * Explain：更新app之后删除安装包
 */
public class AppInstallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //新安装和替换都会执行
        if (Intent.ACTION_PACKAGE_ADDED.equals(intent.getAction())
                || Intent.ACTION_PACKAGE_INSTALL.equals(intent.getAction())
                || Intent.ACTION_PACKAGE_REPLACED.equals(intent.getAction())) {
            //检查下载目录是否有安装包，有在删除，没有不处理(不能随便弹出toast)
            File file = new File(Constant.string.DOWNLOAD_PATH);
            if (file.exists()) {
                File[] files = file.listFiles();
                for (int i = 0, count = files.length; i < count; i++) {
                    String apkName = files[i].getName();
                    if (TextUtils.equals(apkName,  Constant.string.DEFAULT_APP_NAME) || TextUtils.equals(apkName,  Constant.string.DEFAULT_GAME_NAME)) {
                            DataCleanManager.deleteFolderFile(Constant.string.DOWNLOAD_PATH, true);
                    }
                }
            }
        }
    }
}
