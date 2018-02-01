package com.wangxia.battle.util;

import android.content.Context;
import android.content.Intent;

import java.lang.ref.WeakReference;

/**
 * Created by 昝奥博 on 2017/9/23 0023
 * Email:18772833900@163.com
 * Explain：分享工具集成
 */
public class ShareUtil {

    /**
     * 本地化分享
     * @param context
     * @param title
     * @param desc
     * @param shareUrl
     */
    public static void localShare(Context context,String title  , String desc,String shareUrl) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        Context weakContext = weakReference.get();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, desc);
        intent.putExtra(Intent.EXTRA_TEXT, ("决战平安京助手\n\n"+title+ "查看链接➸ " + shareUrl));
        weakContext.startActivity(Intent.createChooser(intent, "分享到"));
    }

}
