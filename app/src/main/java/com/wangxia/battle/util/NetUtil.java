package com.wangxia.battle.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wangxia.battle.R;
import com.wangxia.battle.globe.App;

/**
 * Created by 昝奥博 on 2017/9/14 0014
 * Email:18772833900@163.com
 * Explain：网络判断
 */
public class NetUtil {

    private static ConnectivityManager connMgr;
    private static NetworkInfo wifiNetworkInfo;
    private static NetworkInfo dataNetworkInfo;
    private static boolean isshowClosed = false;

    /**
     * 获取当前网络状态：true网络良好，false：无网络需要提醒用户处理
     *
     * @param
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        wifiNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        dataNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return (wifiNetworkInfo.isConnected() || dataNetworkInfo.isConnected());
    }


    /**
     * 当前无网络，弹出dialog去设置网络
     *
     * @param
     */
    private static AlertDialog alertDialog;

    public static void failDialog(final Context context) {
        if (!isshowClosed) { //已弹出的对话框没有关闭
            return;
        }
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        //弹出对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View theview = View.inflate(context, R.layout.download_delete_showdialog, null);
        TextView tv_delete_no = (TextView) theview.findViewById(R.id.tv_delete_no);
        TextView tv_delete_sure = (TextView) theview.findViewById(R.id.tv_delete_sure);
        TextView tv_tishi_infos = (TextView) theview.findViewById(R.id.tv_tishi_infos);
        tv_delete_no.setText("取消");
        tv_delete_sure.setText("去设置");
        tv_tishi_infos.setText("当前无网络连接,请检查您的网络~!");
        //取消
        tv_delete_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isshowClosed = true; //关闭
                alertDialog.dismiss();
            }
        });
        //设置
        tv_delete_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isshowClosed = true; //关闭
                //关闭对话框
                alertDialog.dismiss();
                Intent intent;
                //判断手机系统的版本  即API大于10 就是3.0或以上版本
                if (android.os.Build.VERSION.SDK_INT > 10) {
                    intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                } else {
                    intent = new Intent();
                    ComponentName component = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
                    intent.setComponent(component);
                    intent.setAction("android.intent.action.VIEW");
                }
                context.startActivity(intent);
            }
        });
        builder.setView(theview);
        builder.setCancelable(false);
        //5.显示出对话框
        alertDialog = builder.show();
        isshowClosed = false; //标识是否关闭了已经弹出的对话框,否则不再重复弹出
    }


    /**
     * 再判断有网络但是网络请求失败的情况下，提醒用户问题原因
     *
     * @param
     */
    public static void netErrorHint(Context context) {
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        MyToast.showToast(App.context, "后台服务繁忙，请稍后再试！", Toast.LENGTH_SHORT);
    }

}
