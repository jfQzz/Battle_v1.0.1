package com.wangxia.battle.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.DensityUtil;

/**
 * Created by 昝奥博 on 2017/9/22 0022
 * Email:18772833900@163.com
 * Explain：
 */
public class NoTouchRecycleView extends RecyclerView {
    private IViewActionDown iViewActionDown;
    private float mDownY = 0;

    public NoTouchRecycleView(Context context) {
        this(context, null);
    }

    public NoTouchRecycleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NoTouchRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                if (Math.abs((ev.getY() - mDownY)) < DensityUtil.dip2px(Constant.number.TEN)) {
                    iViewActionDown.viewActionDown(this);
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    public void setOnActionDownListener(IViewActionDown iViewActionDown) {
        this.iViewActionDown = iViewActionDown;
    }

    public interface IViewActionDown {
        void viewActionDown(RecyclerView rl);

    }
}
