package com.wangxia.battle.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by 昝奥博 on 2017/7/22 0022
 * Email:18772833900@163.com
 * Explain：
 */
public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder mBind;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mBind = ButterKnife.bind(this);
        initView();
        initListener();
        initData();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
        clearMemory();
    }


    //页面布局
    protected abstract int getLayoutId();

    //控件初始化
    protected abstract void initView();

    //监听器
    protected abstract void initListener();

    //数据初始化
    protected abstract void initData();
    protected abstract void clearMemory();

    @Override
    protected void onResume() {
        super.onResume();
    }

}
