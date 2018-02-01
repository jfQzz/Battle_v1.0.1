package com.wangxia.battle.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.umeng.analytics.MobclickAgent;
import com.wangxia.battle.R;
import com.wangxia.battle.adapter.PagerAdapter;
import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.fragment.AboutUsFragment;
import com.wangxia.battle.fragment.ArmFragment;
import com.wangxia.battle.fragment.FindPasswordFragment;
import com.wangxia.battle.fragment.HeroFragment;
import com.wangxia.battle.fragment.ImageShowFragment;
import com.wangxia.battle.fragment.LocalGamesFragment;
import com.wangxia.battle.fragment.LoginFragment;
import com.wangxia.battle.fragment.RegisterFragment;
import com.wangxia.battle.fragment.SettingFragment;
import com.wangxia.battle.fragment.UserFragment;
import com.wangxia.battle.fragment.base.LazyBaseFragment;
import com.wangxia.battle.fragment.list.ArmListFragment;
import com.wangxia.battle.fragment.list.ArticleListFragment;
import com.wangxia.battle.fragment.list.HeroListFragment;
import com.wangxia.battle.fragment.list.VideoListFragment;
import com.wangxia.battle.globe.App;
import com.wangxia.battle.model.bean.ArmBean;
import com.wangxia.battle.model.bean.HeroBean;
import com.wangxia.battle.model.bean.LabelsBean;
import com.wangxia.battle.model.bean.PicBean;
import com.wangxia.battle.model.http.UrlConstant;
import com.wangxia.battle.presenter.impPresenter.ArmListPresenter;
import com.wangxia.battle.presenter.impPresenter.HeroListPresenter;
import com.wangxia.battle.presenter.impPresenter.HeroPicPresenter;
import com.wangxia.battle.presenter.impPresenter.LabelsPresenter;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.GsonUtil;
import com.wangxia.battle.util.LogUtil;
import com.wangxia.battle.util.Mytime;
import com.wangxia.battle.util.NetUtil;
import com.wangxia.battle.util.SpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 容器 0：我的游戏 1:个人中心 2：浏览记录 3：我的收藏 4:设置 5:大图展示，宽度一定高度自适应 6:文章列表
 * 7：式神大全，装备大全 8:登录 9:注册 10：找回密码 11:关于我们    12:视频列表 13:英雄详情 14:装备详情
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
    private int mType;
    private String mIcons;
    private int mIndex;
    private LabelsPresenter StringPresenter;
    private FragmentManager mSupportFragmentManager;
    private SsoHandler mSsoHandler;
    private int mLoginType;
    //需要使用activity的onActivityResult的fragment,一般只需要在fragmnet重写即可
    private LazyBaseFragment mNeedRequest;
    private HeroListPresenter mHeroListPresenter;
    private HeroPicPresenter mHeroPicPresenter;
    private ArmListPresenter mArmListPresenter;


    public static void toTabWithPagerActivity(Context context, int type, String arg, int index) {
        Intent intent = new Intent(context, TabWithPagerActivity.class);
        intent.putExtra(Constant.string.ARG_ONE, type);
        intent.putExtra(Constant.string.ARG_TWO, arg);
        intent.putExtra(Constant.string.ARG_THREE, index);
        context.startActivity(intent);
    }

    public static void toTabWithPagerActivity(Context context, int type, String arg, int index, int mode) {
        Intent intent = new Intent(context, TabWithPagerActivity.class);
        intent.putExtra(Constant.string.ARG_ONE, type);
        intent.putExtra(Constant.string.ARG_TWO, arg);
        intent.putExtra(Constant.string.ARG_THREE, index);
        if (Constant.number.ONE == mode) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        context.startActivity(intent);
    }

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
        mSupportFragmentManager = getSupportFragmentManager();
        switch (mType) {
            case Constant.number.ZERO:
                tvTitle.setText(getResources().getString(R.string.local_games));
                addToFragment(LocalGamesFragment.newInstance(Constant.number.ZERO));
                MobclickAgent.onEvent(TabWithPagerActivity.this, Constant.uMengStatistic.MINE_LOCAL_GAMES);
                break;
            case Constant.number.ONE:
                tvTitle.setText(getResources().getString(R.string.person_manager));
                mNeedRequest = UserFragment.newInstance();
                addToFragment(mNeedRequest);
                MobclickAgent.onEvent(TabWithPagerActivity.this, Constant.uMengStatistic.MINE_DOWNLOAD_HISTORY);
                break;
            case Constant.number.TWO:
                tvTitle.setText(getResources().getString(R.string.browsing_history));
                List<Fragment> fragmentList = new ArrayList<>(2);
                fragmentList.add(LocalGamesFragment.newInstance(Constant.number.TWO));
                fragmentList.add(LocalGamesFragment.newInstance(Constant.number.THREE));
                addToViewPager(fragmentList, getResources().getStringArray(R.array.video_and_article));
                MobclickAgent.onEvent(TabWithPagerActivity.this, Constant.uMengStatistic.MINE_BROWSE);
                break;
            case Constant.number.THREE:
                tvTitle.setText(getResources().getString(R.string.my_favorite));
                List<Fragment> fragmentFaList = new ArrayList<>(2);
                fragmentFaList.add(LocalGamesFragment.newInstance(Constant.number.FORE));
                fragmentFaList.add(LocalGamesFragment.newInstance(Constant.number.FIVE));
                addToViewPager(fragmentFaList, getResources().getStringArray(R.array.video_and_article));
                MobclickAgent.onEvent(TabWithPagerActivity.this, Constant.uMengStatistic.MINE_FAVORITE);
                break;
            case Constant.number.FORE:
                tvTitle.setText(getResources().getString(R.string.set));
                addToFragment(SettingFragment.newInstance());
                MobclickAgent.onEvent(TabWithPagerActivity.this, Constant.uMengStatistic.MINE_ABOUT_US);
                break;
            case Constant.number.FIVE:
                llActionBar.setVisibility(View.GONE);
                addToFragment(ImageShowFragment.newInstance(mIcons, mIndex));
                fullOrNotScreen(true);
                //设置一下全屏属性
                break;
            case Constant.number.SIX:
                tvTitle.setText(mIcons);
                addToFragment(ArticleListFragment.newInstance(mIndex, null));
                break;
            //式神大全，装备大全需要请求类型列表
            case Constant.number.SEVEN:
                String label;
                addPic();
                if (Constant.number.ZERO == mIndex) {
                    label = getResources().getString(R.string.hero_list);
                    addHero();
                } else {
                    label = getResources().getString(R.string.arm_list);
                    addArm();
                }
                tvTitle.setText(label);
//                StringPresenter = new LabelsPresenter(this);
//                StringPresenter.queryList(Constant.number.ZERO, label, Constant.number.ZERO);
                break;
            case Constant.number.EIGHT:
                if (!TextUtils.isEmpty(mIcons) && mIcons.equals(Constant.userInfo.NEED_USER_INFO)) {
                    mNeedRequest = LoginFragment.newInstance(true);
                } else {
                    mNeedRequest = LoginFragment.newInstance(false);
                }
                addToFragment(mNeedRequest);
                break;
            case Constant.number.NINE:
                tvTitle.setText(getString(R.string.account_register));
                addToFragment(RegisterFragment.newInstance());
                break;
            case Constant.number.TEN:
                tvTitle.setText(getString(R.string.find_password));
                addToFragment(FindPasswordFragment.newInstance());
                break;
            case Constant.number.ELEVEN:
                tvTitle.setText(getString(R.string.about_us));
                addToFragment(AboutUsFragment.newInstance());
                break;
            case Constant.number.TWElVE:
                tvTitle.setText(getString(R.string.game_video));
                addToFragment(VideoListFragment.newInstance());
                break;
            case Constant.number.THIRTEEN:
                tvTitle.setText(null);
                addToFragment(HeroFragment.newInstance(mIcons, mIndex));
                break;
            case Constant.number.FOURTEEN:
                tvTitle.setText(null);
                addToFragment(ArmFragment.newInstance(mIcons));
                break;
        }
    }

    private void addArm() {
        String oldArmTime = SpUtil.getString(this, Constant.saveTag.ARM_EFFECTIVE_TIME, null);
        String armData = SpUtil.getString(this, Constant.saveTag.ARM_SAVE, null);
        if (!TextUtils.isEmpty(oldArmTime) && !TextUtils.isEmpty(armData) && !(Mytime.isDelay(Constant.number.SEVEN, oldArmTime) && NetUtil.isNetAvailable(this))) {
            addArmFragment(GsonUtil.getGson().fromJson(armData, ArmBean.class));
        } else {
            mArmListPresenter = new ArmListPresenter(this);
            mArmListPresenter.queryList(Constant.number.THREE, null, Constant.number.ZERO);
        }
    }

    private void addHero() {
        String oldHeroTime = SpUtil.getString(this, Constant.saveTag.HERO_EFFECTIVE_TIME, null);
        String heroData = SpUtil.getString(this, Constant.saveTag.HERO_SAVE, null);
        if (!TextUtils.isEmpty(oldHeroTime) && !TextUtils.isEmpty(heroData) && !(Mytime.isDelay(Constant.number.SEVEN, oldHeroTime) && NetUtil.isNetAvailable(this))) {
            addHeroFragment(GsonUtil.getGson().fromJson(heroData, HeroBean.class));
        } else {
            mHeroListPresenter = new HeroListPresenter(this);
            mHeroListPresenter.queryList(Constant.number.ONE, null, Constant.number.ZERO);
        }
    }

    private void addPic() {
        String oldPicTime = SpUtil.getString(this, Constant.saveTag.PIC_EFFECTIVE_TIME, null);
        String picData = SpUtil.getString(this, Constant.saveTag.PIC_SAVE, null);
        if (!TextUtils.isEmpty(oldPicTime) && !TextUtils.isEmpty(picData) && !(Mytime.isDelay(Constant.number.SEVEN, oldPicTime) && NetUtil.isNetAvailable(this))) {
            addToPic(GsonUtil.getGson().fromJson(picData, PicBean.class));
        } else {
            mHeroPicPresenter = new HeroPicPresenter(this);
            mHeroPicPresenter.queryList(Constant.number.TWO, null, Constant.number.ZERO);
        }
    }

    private void addToFragment(Fragment fragment) {
        ((ViewStub) findViewById(R.id.vs_fragment)).inflate();
        FragmentTransaction fragmentTransaction = mSupportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_fragment_contain, fragment);
        fragmentTransaction.commit();
    }

    private void addToViewPager(List<Fragment> fragmentList, String[] title) {
        ((ViewStub) findViewById(R.id.vs_tab_and_vp)).inflate();
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        XTabLayout xTabLayout = (XTabLayout) findViewById(R.id.x_tab);
        viewPager.setAdapter(new PagerAdapter(mSupportFragmentManager, fragmentList, title));
        viewPager.setOffscreenPageLimit(title.length);
        xTabLayout.setVisibility(View.VISIBLE);
        xTabLayout.setupWithViewPager(viewPager);
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
        if (null == dataBen || this.isFinishing()) {
            return;
        }
        switch (type) {
            case Constant.number.ZERO:
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
                for (int i = 0; i < count; i++) {
                    fragmentList.add(ArticleListFragment.newInstance(mIndex + Constant.number.SEVEN, split[i]));
                }
                addToViewPager(fragmentList, split);
                break;
            case Constant.number.ONE:
                SpUtil.putString(this, Constant.saveTag.HERO_EFFECTIVE_TIME, Mytime.getStringToday());
                SpUtil.putString(this, Constant.saveTag.HERO_SAVE, GsonUtil.getGson().toJson(dataBen));
                addHeroFragment((HeroBean) dataBen);
                break;
            case Constant.number.TWO:
                SpUtil.putString(this, Constant.saveTag.PIC_EFFECTIVE_TIME, Mytime.getStringToday());
                SpUtil.putString(this, Constant.saveTag.PIC_SAVE, GsonUtil.getGson().toJson(dataBen));
                addToPic((PicBean) dataBen);
                break;
            case Constant.number.THREE:
                SpUtil.putString(this, Constant.saveTag.ARM_EFFECTIVE_TIME, Mytime.getStringToday());
                SpUtil.putString(this, Constant.saveTag.ARM_SAVE, GsonUtil.getGson().toJson(dataBen));
                addArmFragment((ArmBean) dataBen);
                break;
        }
    }

    private void addArmFragment(ArmBean dataBen) {
        if (null == dataBen || null == dataBen.getData() || dataBen.getData().isEmpty()) {
            return;
        }
        List<String> labels = new ArrayList<>();
        labels.add(getString(R.string.all));
        Map<String, Map<String, List<ArmBean.DataBean>>> armMap = new HashMap<>(dataBen.getData().size());
        Map<String, ArmBean.DataBean> armNameMap = new HashMap<>();
        Map<Integer, ArmBean.DataBean> armIdMap = new HashMap<>();
        for (ArmBean.DataBean bean : dataBen.getData()) {
            if (TextUtils.isEmpty(bean.getU56feu6807u8defu5f84())) continue;
            LogUtil.i(bean.getU88c5u5907u540du79f0() + "               " + bean.getU56feu6807u8defu5f84());
            armNameMap.put(bean.getU88c5u5907u540du79f0(), bean);
            armIdMap.put(bean.getU88c5u5907ID(), bean);
            String armType = bean.getU88c5u5907u7c7bu578b();
            String armRank = bean.getU88c5u5907u7b49u7ea7();
            if (armMap.containsKey(armType)) {
                if (armMap.get(armType).containsKey(armRank))
                    armMap.get(armType).get(armRank).add(bean);
                else {
                    List<ArmBean.DataBean> armList = new ArrayList<>();
                    armList.add(bean);
                    Map<String, List<ArmBean.DataBean>> listMap = armMap.get(armType);
                    if (null == listMap) {
                        listMap = new HashMap<>();
                        listMap.put(armRank, armList);
                        armMap.put(armType, listMap);
                    } else
                        armMap.get(armType).put(armRank, armList);
                }
            } else {
                labels.add(armType);
                Map<String, List<ArmBean.DataBean>> armListMap = new HashMap<>();
                List<ArmBean.DataBean> armList = new ArrayList<>();
                armList.add(bean);
                armListMap.put(armRank, armList);
                armMap.put(armType, armListMap);
            }
            App.armNameMap = armNameMap;
            App.armIdMap = armIdMap;
            App.armMap = armMap;
        }
        List<Fragment> fragmentLists = new ArrayList<>(labels.size());
        for (String label :
                labels) {
            fragmentLists.add(ArmListFragment.newInstance(label));
        }
        addToViewPager(fragmentLists, labels.toArray(new String[labels.size()]));
    }

    private void addHeroFragment(HeroBean dataBen) {
        Map<String, List<HeroBean.DataBean>> heroMap = new HashMap<>(dataBen.getData().size());
        Map<String, HeroBean.DataBean> heroNameMap = new HashMap<>(dataBen.getData().size());
        List<String> labels = new ArrayList<>();
        heroMap.put(getString(R.string.all), dataBen.getData());
        labels.add(getString(R.string.all));
        for (HeroBean.DataBean bean : dataBen.getData()) {
            List<String> position = bean.getU5f0fu795eu5b9au4f4d();
            for (String label : position) {
                if (TextUtils.isEmpty(bean.getU5f0fu795eu65b9u5934u50cf())) continue;
                heroNameMap.put(label, bean);
                if (heroMap.containsKey(label)) heroMap.get(label).add(bean);
                else {
                    List<HeroBean.DataBean> heroList = new ArrayList<>();
                    heroList.add(bean);
                    heroMap.put(label, heroList);
                    labels.add(label);
                }
            }
        }
        App.heroNameMap = heroNameMap;
        App.heroMap = heroMap;
        List<Fragment> fragmentLists = new ArrayList<>(labels.size());
        for (String label :
                labels) {
            fragmentLists.add(HeroListFragment.newInstance(label));
        }
        addToViewPager(fragmentLists, labels.toArray(new String[labels.size()]));
    }

    private void addToPic(PicBean picBean) {
        Map<String, String> picMap;
        if (picBean.isSuccess()) {
            String pathdict = picBean.getPathdict();
            String[] data = pathdict.split(",");
            picMap = new HashMap<>(data.length);
            for (String str : data) {
                String[] pic = str.split("\\u0024");
                if (Constant.number.TWO == pic.length)
                    picMap.put(pic[Constant.number.ZERO], pic[Constant.number.ONE]);
            }
            App.picMap = picMap;
        }
    }

    @Override
    public void failRequest() {

    }

    @Override
    public void errorRequest() {
        switch (mType) {
            case Constant.number.SEVEN:
                Intent intent = new Intent(this, WebViewActivity.class);
                if (Constant.number.ZERO == mIndex) {
                    intent.putExtra(Constant.infoVariable.URL, UrlConstant.HERO_ALL);
                    intent.putExtra(Constant.string.ARG_TWO, getResources().getString(R.string.hero_list));

                } else {
                    intent.putExtra(Constant.infoVariable.URL, UrlConstant.ARM_ALL);
                    intent.putExtra(Constant.string.ARG_TWO, getResources().getString(R.string.arm_list));
                }

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                this.finish();
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (null != mNeedRequest) {
            mNeedRequest.onActivityResult(requestCode, resultCode, data);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
