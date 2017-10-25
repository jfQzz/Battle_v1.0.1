package com.wangxia.battle.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * Created by 昝奥博 on 2017/7/22 0022
 * Email:18772833900@163.com
 * Explain：
 */
public abstract class BaseFragment extends Fragment {
    public View mView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mView == null){
            mView = initView();
        }
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ViewGroup mContentView = (ViewGroup) getActivity().findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, true);
        }
        initData();
    }

    @Override
    public void onStart() {
        super.onStart();
        initListener();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        recycleMemory();
    }

    /**
     * 4.上面是所有子类都相同的内容
     * 而初始化布局和初始化数据时子类fragment不同的,
     * 写成一个空的方法,留个每个子类去重写,自定义
     */

    //基类一般都是抽象的,要写一些抽象方法,让子类必须去重写---比如下面的初始化布局
    public abstract View initView();   //初始化布局

    /**
     * 初始化数据
     */
    public abstract void initData();  //初始化数据
    /**
     * 设置监听
     */
    public abstract void initListener();

    /**
     * 回收资源
     */
    public abstract void recycleMemory();

}
