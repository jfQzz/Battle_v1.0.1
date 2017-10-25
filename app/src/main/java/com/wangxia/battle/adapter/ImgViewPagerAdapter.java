package com.wangxia.battle.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.ImageUtil;

import java.util.List;

/**
 * Created by 昝奥博 on 2017/2/13 0013
 * Email:18772833900@163.com
 * Explain：全屏大图
 */
public class ImgViewPagerAdapter extends PagerAdapter {
    private List<Integer> mImgId;
    private List<String> mData;
    private Context mContext;
    //默认不宽度固定，高度自适应
    private int mMode = Constant.number.ZERO;

    public ImgViewPagerAdapter(Context context, List<Integer> mImgId) {
        this.mImgId = mImgId;
        this.mContext = context;
    }

    public ImgViewPagerAdapter(Context context, List<String> mImgId, int mode) {
        this.mData = mImgId;
        this.mContext = context;
        this.mMode = mode;
    }

    public void setMode(int mode) {
        this.mMode = mode;
    }

    public void clearMemory() {
        if (null != mImgId) {
            mImgId.clear();
            mImgId = null;
        }
        mData = null;
        mContext = null;
    }

    @Override
    public int getCount() {
        if (Constant.number.ZERO == mMode) {
            return mImgId == null ? 0 : mImgId.size();
        } else {
            return mData == null ? 0 : mData.size();
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        SimpleDraweeView imageView = new SimpleDraweeView(mContext);
        container.addView(imageView);
        if (Constant.number.ZERO == mMode) {
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(mImgId.get(position));
        } else if (Constant.number.ONE == mMode) {
            ImageUtil.fitHeight(imageView,mData.get(position));
        }
        return imageView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
