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
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.wangxia.battle.model.bean.AppInfo;
import com.wangxia.battle.presenter.impPresenter.GameDetailPresenter;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.MyToast;
import com.wangxia.battle.util.ShareUtil;
import com.wangxia.battle.util.SpUtil;
import com.wangxia.battle.util.StatusBarUtil;
import com.wangxia.battle.util.TxtFormatUtil;
import com.wangxia.battle.util.UserUtil;

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
    @BindView(R.id.iv_download)
    ImageView ivDownload;
    @BindView(R.id.tab_layout)
    XTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.ll_evaluate)
    LinearLayout llEvaluate;
    @BindView(R.id.ll_bottom_function)
    LinearLayout llFunction;
    @BindView(R.id.tv_comment_count)
    TextView tvCommentCount;
    @BindView(R.id.iv_user_ic)
    SimpleDraweeView ivUser;
    private int mGameId;
    private GameDetailPresenter mGameDetailPresenter;
    private AppInfo mAppInfo;
    private GameEvaluateFragment mGameEvaluateFragment;
    private boolean mIsNeedUserInfo;

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
        if(mIsNeedUserInfo && UserUtil.getUserState(this)){
            String userIcon = SpUtil.getString(this, Constant.userInfo.USER_ICON, null);
            if(!TextUtils.isEmpty(userIcon) && null != ivUser) ivUser.setImageURI(userIcon);
            mIsNeedUserInfo = false;
        }
    }

    @Override
    protected void initView() {
        StatusBarUtil.setTransparentForImageView(this,null);
        iv_back.setVisibility(View.VISIBLE);
        ivActionRightThree.setVisibility(View.VISIBLE);
        ivActionRightOne.setVisibility(View.GONE);
        ivActionRightTwo.setVisibility(View.GONE);
        MobclickAgent.onEvent(AppDetailActivity.this, Constant.uMengStatistic.WATCH_GAME);
    }

    @Override
    protected void initListener() {
        iv_back.setOnClickListener(this);
        ivDownload.setOnClickListener(this);
        ivActionRightThree.setOnClickListener(this);
        llEvaluate.setOnClickListener(this);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(Constant.number.ZERO == position){
                    llFunction.setVisibility(View.GONE);
                }else {
                    llFunction.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initData() {
        ivUser.setImageURI(SpUtil.getString(this, Constant.userInfo.USER_ICON, null));
        mGameDetailPresenter = new GameDetailPresenter(this);
        mGameDetailPresenter.queryList(mGameId, null, Constant.number.ZERO);

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
        if (null != dataBean) {
            switch (type) {
                case Constant.number.ZERO:
                    mAppInfo = (AppInfo) dataBean;
                    ivGameIco.setImageURI(mAppInfo.getIco());
                    tvGameTitle.setText(Html.fromHtml(mAppInfo.getAppName()));
                    tvGameSize.setText(Html.fromHtml(mAppInfo.getCatalogName()).toString() + " | " + (Integer.parseInt(mAppInfo.getSize()) / 1024) + " M");
                    tvGameLabel.setText(TxtFormatUtil.HtmlFormat(mAppInfo.getRemarks()));
                    tvTitle.setText(TxtFormatUtil.HtmlFormat(mAppInfo.getAppName()));
                    List<Fragment> fragmentList = new ArrayList<>(2);
                    fragmentList.add(GameDetailFragment.newInstance(mAppInfo));
                    mGameEvaluateFragment = GameEvaluateFragment.newInstance(mAppInfo.getID());
                    fragmentList.add(mGameEvaluateFragment);
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
    public void failRequest() {

    }

    @Override
    public void errorRequest() {

    }

    @Override
    public void onClick(View v) {
        String size = String.valueOf("0");
        if (null != mAppInfo) {
            if (!TextUtils.isEmpty(mAppInfo.getSize())) {
                size = mAppInfo.getSize();
            }
        }
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_evaluate:
                if(UserUtil.getUserState(this))
                    mGameEvaluateFragment.evaluateGame();
                else{
                    Intent intent = new Intent(this, TabWithPagerActivity.class);
                    intent.putExtra(Constant.string.ARG_ONE, Constant.number.EIGHT);
                    intent.putExtra(Constant.string.ARG_TWO, Constant.userInfo.NEED_USER_INFO);
                    this.startActivity(intent);
                    mIsNeedUserInfo = true;
                }
                break;
            case R.id.iv_download:
                if(null == mAppInfo){
                    return;
                }
                //下载历史
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

    public void setEvaluateCount(String count){
        if(!TextUtils.isEmpty(count)){
            tvCommentCount.setText(count);
        }

    }

    private void share() {
        ShareUtil.localShare(this, mAppInfo.getAppName(), mAppInfo.getRemarks(), mAppInfo.getHtmlUrl());
    }
}
