package com.wangxia.battle.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.analytics.MobclickAgent;
import com.wangxia.battle.R;
import com.wangxia.battle.adapter.PagerAdapter;
import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.fragment.AboutUsFragment;
import com.wangxia.battle.fragment.ImageShowFragment;
import com.wangxia.battle.fragment.LocalGamesFragment;
import com.wangxia.battle.fragment.list.ArticleListFragment;
import com.wangxia.battle.model.bean.LabelsBean;
import com.wangxia.battle.presenter.impPresenter.LabelsPresenter;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.SpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 容器 0：我的游戏 1:我的下载 2：浏览记录 3：我的收藏 4:关于我们 5:大图展示，宽度一定高度自适应 6:文章列表
 */
public class TabWithPagerActivity extends BaseActivity implements View.OnClickListener, ISuccessCallbackData {

    @BindView(R.id.ll_action_bar)
    LinearLayout llActionBar;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_user_ico)
    SimpleDraweeView ivUserIco;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_action_right_one)
    ImageView ivActionRightOne;
    @BindView(R.id.iv_action_right_two)
    ImageView ivActionRightTwo;
    @BindView(R.id.xtab)
    XTabLayout xTabLayout;
    @BindView(R.id.line_separate)
    View lineSeparate;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private int mType;
    private String mIcons;
    private int mIndex;
    private LabelsPresenter StringPresenter;

    @Override
    protected int getLayoutId() {
        Intent intent = getIntent();
        mType = intent.getIntExtra(Constant.string.ARG_ONE, Constant.number.ZERO);
        mIcons = intent.getStringExtra(Constant.string.ARG_TWO);
        mIndex = intent.getIntExtra(Constant.string.ARG_THREE, Constant.number.ZERO);
        return R.layout.activity_tab_with_pager;
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);

    }

    @Override
    protected void initListener() {
        ivBack.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        List<Fragment> fragmentList = null;
        String[] tabs = null;
        boolean isNeedTab = false;
        xTabLayout.setVisibility(View.GONE);
        boolean needNet = false;
        switch (mType) {
            case Constant.number.ZERO:
                tvTitle.setText(getResources().getString(R.string.my_games));
                fragmentList = new ArrayList<>(Constant.number.ONE);
                fragmentList.add(LocalGamesFragment.newInstance(Constant.number.ZERO));
                MobclickAgent.onEvent(TabWithPagerActivity.this, Constant.uMengStatistic.MINE_LOCAL_GAMES);
                break;
            case Constant.number.ONE:
                tvTitle.setText(getResources().getString(R.string.my_download));
                fragmentList = new ArrayList<>(Constant.number.ONE);
                fragmentList.add(LocalGamesFragment.newInstance(Constant.number.ONE));
                MobclickAgent.onEvent(TabWithPagerActivity.this, Constant.uMengStatistic.MINE_DOWNLOAD_HISTORY);
                break;
            case Constant.number.TWO:
                tvTitle.setText(getResources().getString(R.string.browsing_history));
                fragmentList = new ArrayList<>(Constant.number.ONE);
                fragmentList.add(LocalGamesFragment.newInstance(Constant.number.THREE));
                tabs = getResources().getStringArray(R.array.game_and_article);
                MobclickAgent.onEvent(TabWithPagerActivity.this, Constant.uMengStatistic.MINE_BROWSE);
                break;
            case Constant.number.THREE:
                tvTitle.setText(getResources().getString(R.string.my_favorite));
                fragmentList = new ArrayList<>(Constant.number.ONE);
                fragmentList.add(LocalGamesFragment.newInstance(Constant.number.FIVE));
                tabs = getResources().getStringArray(R.array.game_and_article);
                MobclickAgent.onEvent(TabWithPagerActivity.this, Constant.uMengStatistic.MINE_FAVORITE);
                break;
            case Constant.number.FORE:
                tvTitle.setText(getResources().getString(R.string.about_battle));
                fragmentList = new ArrayList<>(Constant.number.ONE);
                fragmentList.add(AboutUsFragment.newInstance());
                MobclickAgent.onEvent(TabWithPagerActivity.this, Constant.uMengStatistic.MINE_ABOUT_US);
                break;
            case Constant.number.FIVE:
                llActionBar.setVisibility(View.GONE);
                fragmentList = new ArrayList<>(Constant.number.ONE);
                fragmentList.add(ImageShowFragment.newInstance(mIcons, mIndex));
                fullOrNotScreen(true);
                //设置一下全屏属性
                break;
            case Constant.number.SIX:
                tvTitle.setText(mIcons);
                fragmentList = new ArrayList<>(Constant.number.ONE);
                fragmentList.add(ArticleListFragment.newInstance(mIndex, null));
                break;
            case Constant.number.SEVEN:
                //式神大全，装备大全需要请求类型列表
                String label;
                if (Constant.number.ZERO == mIndex)
                    label = getResources().getString(R.string.hero_list);
                else label = getResources().getString(R.string.arm_list);

                StringPresenter = new LabelsPresenter(this);
                StringPresenter.queryList(Constant.number.ZERO, label, Constant.number.ZERO);
                needNet = true;
                break;
        }
        if (!needNet) {
            viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), fragmentList, tabs));
            if (isNeedTab) {
                xTabLayout.setVisibility(View.VISIBLE);
                xTabLayout.setupWithViewPager(viewPager);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        switch (mType) {
            case Constant.number.ZERO:
                MobclickAgent.onPageStart("MyGameActivity");
                break;
            case Constant.number.ONE:
                MobclickAgent.onPageStart("MyDownloadActivity");
                break;
            case Constant.number.TWO:
                MobclickAgent.onPageStart("MyBrowseActivity");
                break;
            case Constant.number.THREE:
                MobclickAgent.onPageStart("MyFavoriteActivity");
                break;
            case Constant.number.FORE:
                MobclickAgent.onPageStart("AboutLegendActivity");
                break;
        }
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        switch (mType) {
            case Constant.number.ZERO:
                MobclickAgent.onPageEnd("MyGameActivity");
                break;
            case Constant.number.ONE:
                MobclickAgent.onPageEnd("MyDownloadActivity");
                break;
            case Constant.number.TWO:
                MobclickAgent.onPageEnd("MyBrowseActivity");
                break;
            case Constant.number.THREE:
                MobclickAgent.onPageEnd("MyFavoriteActivity");
                break;
            case Constant.number.FORE:
                MobclickAgent.onPageEnd("AboutLegendActivity");
                break;
        }
        MobclickAgent.onPause(this);
    }

    @Override
    protected void clearMemory() {
        viewPager = null;
        ivBack = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                if (Constant.number.FIVE == mType) {
                    fullOrNotScreen(false);
                }
                finish();
                break;
        }
    }

    private void fullOrNotScreen(boolean isFull) {
        Window window = getWindow();
        if (isFull) {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

    }

    @Override
    public void getResult(Object dataBen, int type) {
        String result;
        if (Constant.number.ZERO == mIndex)
            result = SpUtil.getString(this, Constant.string.SP_HERO_TYPE, getResources().getString(R.string.hero_type));
        else
            result = SpUtil.getString(this, Constant.string.SP_ARM_TYPE, getResources().getString(R.string.arm_type));

        if (null != dataBen) {
            LabelsBean labelsBean = (LabelsBean) dataBen;
            if (null != labelsBean && !TextUtils.isEmpty(labelsBean.getLabels())) {
                result = labelsBean.getLabels();
                if (Constant.number.ZERO == mIndex)
                    SpUtil.putString(this, Constant.string.SP_HERO_TYPE, result);
                else SpUtil.putString(this, Constant.string.SP_ARM_TYPE, result);

            }

        }
        String[] split = result.split(Constant.string.COMMA_SEPARATOR);
        int count = split.length;
        List<Fragment> fragmentList = new ArrayList<>(count);

        for (int i = 0 ; i < count; i++) {
            fragmentList.add(ArticleListFragment.newInstance(mIndex + Constant.number.SEVEN, split[i]));
        }
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), fragmentList, split));
        viewPager.setOffscreenPageLimit(count);
        xTabLayout.setVisibility(View.VISIBLE);
        xTabLayout.setupWithViewPager(viewPager);
    }
}
