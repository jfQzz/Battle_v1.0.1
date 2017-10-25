package com.wangxia.battle.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;

import com.androidkun.xtablayout.XTabLayout;
import com.umeng.analytics.MobclickAgent;
import com.wangxia.battle.R;
import com.wangxia.battle.adapter.PagerAdapter;
import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.fragment.list.AppListFragment;
import com.wangxia.battle.model.bean.AppInfo;
import com.wangxia.battle.presenter.impPresenter.GameDetailPresenter;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.SpUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 昝奥博 on 2017/7/22 0022
 * Email:18772833900@163.com
 * Explain：
 */
public class RankFragment extends BaseFragment implements ISuccessCallbackData{

    @BindView(R.id.tab)
    XTabLayout tab;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private Context mContext;
    private Unbinder mBind;
    private GameDetailPresenter mGameDetailPresenter;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        WeakReference<Context> weakReference = new WeakReference<>(context);
        mContext = weakReference.get();
    }

    public static RankFragment newInstance() {
        Bundle args = new Bundle();
        RankFragment fragment = new RankFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_rank, null);
        mBind = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("RankFragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("RankFragment");
    }




    @Override
    public void initData() {
        mGameDetailPresenter = new GameDetailPresenter(this);
        mGameDetailPresenter.queryList(Constant.number.ZERO,null,Constant.number.ONE);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void recycleMemory() {
        mBind.unbind();
    }


    @Override
    public void getResult(Object dataBen, int type) {
        String labelSplit = getResources().getString(R.string.article_labels);
        if(null != dataBen){
            AppInfo appInfo = (AppInfo) dataBen;
            List<AppInfo.DownBean> down = appInfo.getDown();
            if(null != down && Constant.number.ZERO < down.size()){
                AppInfo.DownBean downBean = down.get(Constant.number.ZERO);
                if(null != downBean){
                    String url = downBean.getUrl();
                    if(!TextUtils.isEmpty(url)){
                        SpUtil.putString(mContext,Constant.string.SP_GAME_DOWNLOAD_URL,url);
                    }
                }
            }
            String packageName = appInfo.getPackageName();
            if(!TextUtils.isEmpty(packageName)){
                SpUtil.putString(mContext,Constant.string.SP_GAME_PACKAGE,packageName);
            }

            String labels = appInfo.getLableSplit();
            if(!TextUtils.isEmpty(labels)){
                SpUtil.putString(mContext,Constant.string.SP_SAVE_ARTICLE_SPLIT_LABELS,labels);
                labelSplit = labels;
            }else {
                labelSplit = SpUtil.getString(mContext,Constant.string.SP_SAVE_ARTICLE_SPLIT_LABELS,null);
            }
        }
        String[] split = labelSplit.split(Constant.string.COMMA_SEPARATOR);
        List<Fragment> fragmentList = new ArrayList<>(split.length);
        for (int i = 0 , count = split.length ; i < count; i++) {
            fragmentList.add(AppListFragment.newInstance(Constant.number.ZERO,split[i]));
        }
        viewPager.setAdapter(new PagerAdapter(getChildFragmentManager(),fragmentList,split));
        viewPager.setOffscreenPageLimit(split.length);
        tab.setupWithViewPager(viewPager);
    }
}
