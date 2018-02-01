package com.wangxia.battle.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by 昝奥博 on 2018/1/17 0017
 * Email:18772833900@163.com
 * Explain：webview滑动监听
 */
public class ScrollListenerScrollView extends ScrollView {
    public ScrollListenerScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet,0);
    }

    public ScrollListenerScrollView(Context context) {
        this(context,null);
    }

    public ScrollListenerScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }


    private OnScrollChangeListener onScrollChangeListener;

    public void setOnScrollChangeListener(OnScrollChangeListener onScrollChangeListener ){
        this.onScrollChangeListener = onScrollChangeListener;
    }


    /**
     * 重写滑动改变时间，获取滑动时变化的值
     * @param l
     * @param t
     * @param oldl
     * @param oldt
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(null != onScrollChangeListener){
            onScrollChangeListener.onScrollChanged(l-oldl,t-oldt);
        }
    }


    public interface OnScrollChangeListener {
        void onScrollChanged(int x,int y);
    }

}
