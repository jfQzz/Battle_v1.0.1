package com.wangxia.battle.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by 昝奥博 on 2016/12/15 0015
 * Email:18772833900@163.com
 * Explain：带有title的fragment的适配器
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments ;
    private String[] mTitles = null;
    private List<String> mTabNameLis = null;

    public PagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] mTitles) {
        super(fm);
        this.fragments = fragments;
        this.mTitles = mTitles;
    }
    public PagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    public PagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> mTabNameLis) {
        super(fm);
        this.fragments = fragments;
        this.mTabNameLis = mTabNameLis;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0: fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mTabNameLis != null && position < mTabNameLis.size()){
            return mTabNameLis.get(position);
        }
        if (mTitles != null && position < mTitles.length){
            return mTitles[position];
        }
        return null;
    }
}
