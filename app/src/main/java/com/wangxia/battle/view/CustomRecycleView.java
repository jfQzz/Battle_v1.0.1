package com.wangxia.battle.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by 昝奥博 on 2017/12/25 0025
 * Email:18772833900@163.com
 * Explain：
 */
public class CustomRecycleView extends RecyclerView {
    public CustomRecycleView(Context context) {
        this(context,null);
    }

    public CustomRecycleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthSpec, expandSpec);
    }
}
