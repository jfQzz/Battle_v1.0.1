package com.wangxia.battle.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.umeng.analytics.MobclickAgent;
import com.wangxia.battle.R;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.SpUtil;

/**
 * 冷启动
 */
public class ColdBootActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cold_boot);
        MobclickAgent.openActivityDurationTrack(false);
        //统计发送的日志加密
        MobclickAgent.enableEncrypt(true);
        Intent intent;
        if(SpUtil.getBoolean(this, Constant.string.IS_FIRST_ENTER,false)){
            intent = new Intent(this,HomeActivity.class);
            MobclickAgent.onEvent(ColdBootActivity.this,Constant.uMengStatistic.OPEN_TIMES);
        }else {
            intent = new Intent(this,GuideActivity.class);
            MobclickAgent.onEvent(ColdBootActivity.this,Constant.uMengStatistic.NEWLY_INCREASED_USER);
        }
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("ColdBootActivity");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("ColdBootActivity");
        MobclickAgent.onPause(this);
    }
}
