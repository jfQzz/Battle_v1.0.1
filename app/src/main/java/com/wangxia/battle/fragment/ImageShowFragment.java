package com.wangxia.battle.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wangxia.battle.R;
import com.wangxia.battle.adapter.ImgViewPagerAdapter;
import com.wangxia.battle.util.Constant;

import java.lang.ref.WeakReference;
import java.util.Arrays;

/**
 * Created by 昝奥博 on 2017/9/22 0022
 * Email:18772833900@163.com
 * Explain：图片全屏展示，自适应宽高
 */
public class ImageShowFragment extends BaseFragment {

    private String[] mData;
    private Context mContext;
    private ViewPager viewPager;
    private ImgViewPagerAdapter mBigImgAdapter;
    private TextView tvImgIndex;
    private int mIndex;
    private FrameLayout flRoot;

    /**
     * 多张图用" ，”间隔开
     *
     * @param icons
     * @return
     */
    public static ImageShowFragment newInstance(String icons,int index) {

        Bundle args = new Bundle();
        args.putString(Constant.string.ARG_ONE, icons);
        args.putInt(Constant.string.ARG_TWO, index);
        ImageShowFragment fragment = new ImageShowFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        WeakReference<Context> weakReference = new WeakReference<>(context);
        mContext = weakReference.get();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (null != arguments) {
            String icons = arguments.getString(Constant.string.ARG_ONE);
            mIndex = arguments.getInt(Constant.string.ARG_TWO,Constant.number.ZERO);
            if (!TextUtils.isEmpty(icons)) {
                mData = icons.split(Constant.string.COMMA_SEPARATOR);
            } else {
                return;
            }
        }
    }

    @Override
    public View initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_image_show, null);
        flRoot = (FrameLayout)view.findViewById(R.id.frame_toot);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        tvImgIndex = (TextView)view.findViewById(R.id.tv_img_index);
        return view;
    }

    @Override
    public void initData() {
        if(null != mData){
            mBigImgAdapter = new ImgViewPagerAdapter(mContext, Arrays.asList(mData), Constant.number.ONE);
            viewPager.setAdapter(mBigImgAdapter);
            int length = mData.length;
            if(Constant.number.ZERO != length){
                tvImgIndex.setVisibility(View.VISIBLE);
            if(Constant.number.ZERO != mIndex){
                viewPager.setCurrentItem(mIndex);
                tvImgIndex.setText((mIndex+1)+"/"+ length);
            }else {
                tvImgIndex.setText("1/"+ length);
            }
            }
        }

    }

    @Override
    public void initListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int length = mData.length;
                if(Constant.number.ZERO != length){
                    tvImgIndex.setText((position+1)+"/"+ length);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        flRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity)mContext).finish();
            }
        });

    }

    @Override
    public void recycleMemory() {
        mBigImgAdapter.clearMemory();
        mData = null;
        mContext = null;
        viewPager = null;
    }
}
