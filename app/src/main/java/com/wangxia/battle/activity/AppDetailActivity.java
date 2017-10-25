package com.wangxia.battle.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidkun.xtablayout.XTabLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.analytics.MobclickAgent;
import com.wangxia.battle.R;
import com.wangxia.battle.adapter.PagerAdapter;
import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.fragment.GameDetailFragment;
import com.wangxia.battle.fragment.GameEvaluateFragment;
import com.wangxia.battle.globe.App;
import com.wangxia.battle.model.bean.AppInfo;
import com.wangxia.battle.presenter.impPresenter.GameDetailPresenter;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.MyToast;
import com.wangxia.battle.util.Mytime;
import com.wangxia.battle.util.ShareUtil;
import com.wangxia.battle.util.TxtFormatUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AppDetailActivity extends BaseActivity implements ISuccessCallbackData, View.OnClickListener {


    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_action_right_one)
    ImageView ivActionRightOne;
    @BindView(R.id.iv_action_right_two)
    ImageView ivActionRightTwo;
    @BindView(R.id.iv_action_right_three)
    ImageView ivActionRightThree;
    @BindView(R.id.iv_game_ico)
    SimpleDraweeView ivGameIco;
    @BindView(R.id.tv_game_title)
    TextView tvGameTitle;
    @BindView(R.id.tv_game_size)
    TextView tvGameSize;
    @BindView(R.id.tv_game_label)
    TextView tvGameLabel;
    @BindView(R.id.iv_favorite)
    ImageView ivFavorite;
    @BindView(R.id.tv_attention)
    TextView tvAttention;
    @BindView(R.id.iv_download)
    ImageView ivDownload;
    @BindView(R.id.tab_layout)
    XTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.loading)
    FrameLayout loading;
    private int mGameId;
    private GameDetailPresenter mGameDetailPresenter;
    private AppInfo mAppInfo;
    private boolean mIsFavorite = false;

    public static void startAppDetailActivity(Context context, int id) {
        Intent intent = new Intent(context, AppDetailActivity.class);
        intent.putExtra(Constant.string.ARG_ONE, id);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        Intent intent = getIntent();
        if (null != intent) {
            mGameId = intent.getIntExtra(Constant.string.ARG_ONE, 0);
        }
        return R.layout.activity_app_detail;
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("AppDetailActivity");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void initView() {
        iv_back.setVisibility(View.VISIBLE);
        ivActionRightThree.setVisibility(View.VISIBLE);
        ivActionRightOne.setVisibility(View.GONE);
        ivActionRightTwo.setVisibility(View.GONE);
        MobclickAgent.onEvent(AppDetailActivity.this, Constant.uMengStatistic.WATCH_GAME);
    }

    @Override
    protected void initListener() {
        iv_back.setOnClickListener(this);
        ivFavorite.setOnClickListener(this);
        ivDownload.setOnClickListener(this);
        ivActionRightThree.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mGameDetailPresenter = new GameDetailPresenter(this);
        mGameDetailPresenter.queryList(mGameId, null,Constant.number.ZERO);
        if (App.mReaderManager.isFavoriteAppById(mGameId)) {
            ivFavorite.setImageDrawable(getResources().getDrawable(R.drawable.favorited));
            mIsFavorite = true;
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("AppDetailActivity");
        MobclickAgent.onPause(this);
    }

    @Override
    protected void clearMemory() {
    }

    @Override
    public void getResult(Object dataBean, int type) {
        if (View.VISIBLE == loading.getVisibility()) {
            loading.setVisibility(View.GONE);

        }
        if (null != dataBean) {
            switch (type) {
                case Constant.number.ZERO:
                    mAppInfo = (AppInfo) dataBean;
                    ivGameIco.setImageURI(mAppInfo.getIco());
                    tvGameTitle.setText(Html.fromHtml(mAppInfo.getAppName()));
                    String size = TextUtils.isEmpty(mAppInfo.getSize()) ? String.valueOf(Constant.number.ZERO) : mAppInfo.getSize();
                    tvGameSize.setText(Html.fromHtml(mAppInfo.getCatalogName()).toString() + " | " + (Integer.parseInt(mAppInfo.getSize()) / 1024) + " M");
                    tvGameLabel.setText(TxtFormatUtil.HtmlFormat(mAppInfo.getRemarks()));
                    tvTitle.setText(TxtFormatUtil.HtmlFormat(mAppInfo.getAppName()));
                    App.mReaderManager.addGameBrowseDB(mGameId, mAppInfo.getIco(), mAppInfo.getAppName(), Long.parseLong(size), mAppInfo.getLabels(), mAppInfo.getRemarks(), Mytime.getStringToday());
                    List<Fragment> fragmentList = new ArrayList<>(2);
                    fragmentList.add(GameDetailFragment.newInstance(mAppInfo));
                    fragmentList.add(GameEvaluateFragment.newInstance(mGameId));
                    viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), fragmentList, getResources().getStringArray(R.array.game_detail_tabs)));
                    viewPager.setOffscreenPageLimit(2);
                    tabLayout.setupWithViewPager(viewPager);
                    if (null != mAppInfo.getDown() && null != mAppInfo.getDown().get(Constant.number.ZERO) && !TextUtils.isEmpty(mAppInfo.getDown().get(Constant.number.ZERO).getUrl())) {
                        ivDownload.setVisibility(View.VISIBLE);
                    } else {
                        ivDownload.setVisibility(View.INVISIBLE);
                    }
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        String size = mAppInfo.getSize();
        if (TextUtils.isEmpty(size)) {
            size = String.valueOf(Constant.number.ZERO);
        }
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_favorite:
                if (mIsFavorite) {
                    mIsFavorite = false;
                    ivFavorite.setImageDrawable(getResources().getDrawable(R.drawable.no_favorite));
                    App.mReaderManager.deleteFavoriteGameById(mGameId);
                } else {
                    App.mReaderManager.addGameFavoriteDB(mGameId, mAppInfo.getIco(), mAppInfo.getAppName(), Long.parseLong(size), mAppInfo.getLabels(), mAppInfo.getRemarks(), Mytime.getStringToday());
                    mIsFavorite = true;
                    ivFavorite.setImageDrawable(getResources().getDrawable(R.drawable.favorited));
                }
                break;
            case R.id.iv_download:
                //下载历史
                App.mReaderManager.addDownHistoryDB(mGameId, mAppInfo.getIco(), mAppInfo.getAppTitle(), Long.parseLong(size), mAppInfo.getLabels(), mAppInfo.getRemarks(), Mytime.getStringToday());
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(mAppInfo.getDown().get(Constant.number.ZERO).getUrl()));
//                intent.setClassName("com.android.browser","com.android.browser.BrowserActivity");
                PackageManager pm = getPackageManager();
                List<ResolveInfo> resolveInfo = pm.queryIntentActivities(intent, Constant.number.ZERO);
                if (resolveInfo.size() == Constant.number.ZERO) {
                    MyToast.showToast(this, "您的手机暂未安装浏览器", Toast.LENGTH_SHORT);
                    return;
                } else {
                    //开启一个新的栈
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                break;
            case R.id.iv_action_right_three:
                share();
                break;
        }
    }

    private void share() {
        ShareUtil.localShare(this, mAppInfo.getAppName(), mAppInfo.getRemarks(), mAppInfo.getHtmlUrl());
    }
}
