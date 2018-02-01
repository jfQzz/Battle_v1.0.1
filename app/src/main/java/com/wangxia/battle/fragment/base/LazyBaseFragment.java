package com.wangxia.battle.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import java.lang.ref.WeakReference;

/**
 * Created by 昝奥博 on 2018/1/3 0003
 * Email:18772833900@163.com
 * Explain：
 */
public abstract class LazyBaseFragment extends Fragment{
    public View mView;
    public Context mContext;
    private boolean isLazyLoaded;
    private boolean isPrepared;


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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ViewGroup mContentView = (ViewGroup) getActivity().findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, true);
        }
        isPrepared = true;
        lazyLoad();
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


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        lazyLoad();
    }


    /**
     * 调用懒加载
     */
    private void lazyLoad() {
        if (getUserVisibleHint() && isPrepared && !isLazyLoaded) {
            initData();
            isLazyLoaded = true;
        }
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
