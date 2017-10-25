package com.wangxia.battle.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.wangxia.battle.activity.AppDetailActivity;
import com.wangxia.battle.activity.VideoActivity;
import com.wangxia.battle.activity.WebViewActivity;
import com.wangxia.battle.util.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by 昝奥博 on 2017/9/24 0024
 * Email:18772833900@163.com
 * Explain：激光推送接受广播
 */
public class JPushReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            String text = bundle.getString(JPushInterface.EXTRA_EXTRA);
            if (TextUtils.isEmpty(text)) {
                return;
            }
            try {
                JSONObject jsonObject = new JSONObject(text);
                String type = jsonObject.optString(Constant.infoVariable.TYPE);
                Intent toOtherIntent = new Intent();
                switch (type) {
                    case Constant.jPushTag.TYPE_GAME:
                        toOtherIntent.setClass(context, AppDetailActivity.class);
                        toOtherIntent.putExtra(Constant.string.ARG_ONE, jsonObject.optString(Constant.infoVariable.ID));
                        break;
                    case Constant.jPushTag.TYPE_ARTICLE:
                        toOtherIntent.setClass(context, WebViewActivity.class);
                        intent.putExtra(Constant.string.ARG_ONE, Integer.parseInt(jsonObject.optString(Constant.infoVariable.ID)));
                        intent.putExtra(Constant.string.ARG_TWO, jsonObject.optString(Constant.infoVariable.TITLE));
                        intent.putExtra(Constant.string.ARG_THREE, jsonObject.optString(Constant.infoVariable.URL));
                        intent.putExtra(Constant.string.ARG_FORE, jsonObject.optString(Constant.infoVariable.HINTS));
                        intent.putExtra(Constant.string.ARG_FIVE, jsonObject.optString(Constant.infoVariable.TIME));
                        break;
                    case Constant.jPushTag.TYPE_VIDEO:
                        toOtherIntent.setClass(context, VideoActivity.class);
                        intent.putExtra(Constant.string.ARG_ONE, jsonObject.optString(Constant.infoVariable.URL));
                        intent.putExtra(Constant.string.ARG_TWO, jsonObject.optString(Constant.infoVariable.TITLE));
                        intent.putExtra(Constant.string.ARG_THREE, jsonObject.optString(Constant.infoVariable.DESC));
                        break;
                    case Constant.jPushTag.TYPE_MESSAGE:
                        //文本展示
                        break;
                    case Constant.jPushTag.TYPE_ACTIVITY:
                        toOtherIntent.setClass(context, WebViewActivity.class);
                        toOtherIntent.putExtra(Constant.infoVariable.URL, jsonObject.optString(Constant.infoVariable.URL));
                        toOtherIntent.putExtra(Constant.string.ARG_TWO, jsonObject.optString(Constant.infoVariable.TITLE));
                        break;
                }
                if (null != toOtherIntent) {
                    toOtherIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(toOtherIntent);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
