package com.wangxia.battle.activity;

import android.Manifest;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;

import com.ToxicBakery.viewpager.transforms.RotateDownTransformer;
import com.umeng.analytics.MobclickAgent;
import com.wangxia.battle.R;
import com.wangxia.battle.adapter.ImgViewPagerAdapter;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.SpUtil;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseActivity {
    private ViewPager mViewPager;
    private List<Integer> mData = new ArrayList<>();
    private ImgViewPagerAdapter mAdapter;
    private Button mIntoBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView() {
        mViewPager = (ViewPager) findViewById(R.id.guide_view_pager);
        //BackgroundToForegroundTransformer 放大退出，缩小进入叠层效果 ForegroundToBackgroundTransformer
        //CubeOutTransformer 3D旋转效果
        //DepthPageTransformer 叠层效果
        //FlipHorizontalTransformer 水平翻转
        //RotateDownTransformer 扇形旋转
        //StackTransformer 层叠退出
        mViewPager.setPageTransformer(true, new RotateDownTransformer());
        mIntoBtn = (Button) findViewById(R.id.guide_btn);
        mData.add(R.drawable.guide_one_bg);
        mData.add(R.drawable.guide_two_bg);
        mData.add(R.drawable.guide_three_bg);
        mData.add(R.drawable.guide_fore_bg);
        mAdapter = new ImgViewPagerAdapter(this, mData);
        mViewPager.setAdapter(mAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("GuideActivity");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("GuideActivity");
        MobclickAgent.onPause(this);
    }

    @Override
    protected void initListener() {
        mIntoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuideActivity.this, HomeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter_right_to_left, R.anim.exit_right_to_left);
                SpUtil.putBoolean(GuideActivity.this, Constant.string.IS_FIRST_ENTER, true);
                finish();
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mIntoBtn.setVisibility(View.GONE);
                if ((mData.size() - 1) == position) {
                    mIntoBtn.setVisibility(View.VISIBLE);
                    AnimatorSet animatorSet = new AnimatorSet();
                    ObjectAnimator scaleX = ObjectAnimator.ofFloat(mIntoBtn, "scaleX", 0.7f, 1.2f, 1f);
                    ObjectAnimator scaleY = ObjectAnimator.ofFloat(mIntoBtn, "scaleY", 0.7f, 1.2f, 1f);
                    animatorSet.setDuration(600).play(scaleX).with(scaleY);
                    animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
                    animatorSet.start();
                } else {
                    if (Constant.number.ONE == position) {
                        //做一下权限申请
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                                ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                                ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {
                            //检查是否拥有权限
                            ActivityCompat.requestPermissions(GuideActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,Manifest.permission.CAMERA,Manifest.permission.READ_PHONE_STATE}, Constant.number.HUNDRED);
                        }
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (Constant.number.HUNDRED == requestCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission was granted, yay! Do the
                // contacts-related task you need to do.
            } else {
                // permission denied, boo! Disable the
                // functionality that depends on this permission.
            }
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void clearMemory() {
        if (null != mData) {
            mData.clear();
        }
        if (null != mAdapter) {
            mAdapter.clearMemory();
        }
        mViewPager = null;
        mIntoBtn = null;
    }
}
