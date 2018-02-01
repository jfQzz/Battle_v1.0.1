package com.wangxia.battle.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wangxia.battle.R;
import com.wangxia.battle.activity.BaseActivity;
import com.wangxia.battle.callback.NetworkListener;
import com.wangxia.battle.globe.App;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 昝奥博 on 2017/9/14 0014
 * Email:18772833900@163.com
 * Explain：网络判断
 */
public class NetUtil{
    private static List<AlertDialog> mDialogList = new ArrayList<>();

    public static NetworkListener networkListener;
    public  static void setNetListener(NetworkListener netListener){
        networkListener = netListener;
    }

    /**
     * 获取当前网络状态：true网络良好，false：无网络需要提醒用户处理
     * @param
     * @return
     */
    public static boolean isNetAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable())
            return true;
        else
            return false;
    }

    public boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }


    /**
     * 通用的dialog提示
     *
     * @param context
     * @param hints        提示语
     * @param desc         内容
     * @param cancelWords  取消，或者隐藏
     * @param confirmWords 提交 ，确定。。。
     */
    public static void universalDialog(@NonNull final Context context, String hints, @NonNull String desc, String cancelWords, String confirmWords, final IUniversalDialogCallback iuniversalDialogCallback) {
        if (null == context ||(null !=mDialogList && mDialogList.size() > Constant.number.ZERO)) {
            return;
        }
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        //弹出对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = View.inflate(context, R.layout.dialog_universal, null);
        TextView tvHint = (TextView) view.findViewById(R.id.tv_dialog_hint);
        TextView tvDesc = (TextView) view.findViewById(R.id.tv_dialog_desc);
        TextView tvCancel = (TextView) view.findViewById(R.id.tv_dialog_cancel);
        TextView tvConfirm = (TextView) view.findViewById(R.id.tv_dialog_confirm);
        if (!TextUtils.isEmpty(hints)) {
            tvHint.setText(hints);
        }
        if (!TextUtils.isEmpty(desc)) {
            tvDesc.setText(desc);
        }
        if (!TextUtils.isEmpty(cancelWords)) {
            tvCancel.setText(cancelWords);
        }
        if (!TextUtils.isEmpty(confirmWords)) {
            tvConfirm.setText(confirmWords);
        }

        builder.setView(view);
        builder.setCancelable(false);
        final AlertDialog alertDialog = builder.create();
        mDialogList.add(alertDialog);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                if (null != iuniversalDialogCallback) {
                    iuniversalDialogCallback.cancel();
                }
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                if (null != iuniversalDialogCallback) {
                    iuniversalDialogCallback.confirm();
                }
            }
        });

        if (null == context) {
            return;
        }
        if (context instanceof BaseActivity) {
            if (((BaseActivity) context).isFinishing()) {
                return;
            }
        }
        alertDialog.show();
    }


    public static void dismissUniversalDialog(){
        if(null != mDialogList && !mDialogList.isEmpty()){
            for (AlertDialog dialog :
                    mDialogList) {
                dialog.dismiss();
            }
        }
    }

    public interface IUniversalDialogCallback {
        void cancel();

        void confirm();
    }

    public static void toSetNet(Context context) {
        if (null == context) {
            return;
        }
        Intent intent;
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
