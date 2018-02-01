package com.wangxia.battle.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

/**
 * Created by 昝奥博 on 2017/7/22 0022
 * Email:18772833900@163.com
 * Explain：
 */
public abstract class BaseFragment extends Fragment {
    public View mView;
    public Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        WeakReference<Context> weakReference = new WeakReference<>(context);
        mContext = weakReference.get();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mView == null){
            mView = initView();
        }
        return mView;
    }


    @Override
    public void onStart() {
        super.onStart();
        initListener();
        initData();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        recycleMemory();
    }


    public abstract View initView();

    /**
     * 初始化数据
     */
    public abstract void initData();
    /**
     * 设置监听
     */
    public abstract void initListener();

    /**
     * 回收资源
     */
    public abstract void recycleMemory();

}
