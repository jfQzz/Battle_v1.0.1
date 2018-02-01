package com.wangxia.battle.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.analytics.MobclickAgent;
import com.wangxia.battle.R;
import com.wangxia.battle.activity.AppDetailActivity;
import com.wangxia.battle.activity.TabWithPagerActivity;
import com.wangxia.battle.activity.WebViewActivity;
import com.wangxia.battle.adapter.ArticleAdapter;
import com.wangxia.battle.adapter.BannerAdapter;
import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.callback.NetworkListener;
import com.wangxia.battle.fragment.base.LazyBaseFragment;
import com.wangxia.battle.model.bean.ArticleList;
import com.wangxia.battle.model.http.UrlConstant;
import com.wangxia.battle.presenter.impPresenter.ArticleListPresenter;
import com.wangxia.battle.util.ApkUtil;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.DataCleanManager;
import com.wangxia.battle.util.MyToast;
import com.wangxia.battle.util.NetUtil;
import com.wangxia.battle.util.OkHttpDownloadUtil;
import com.wangxia.battle.util.SpUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 昝奥博 on 2017/9/26 0026
 * Email:18772833900@163.com
 * Explain：首页 一个recycleView可复用
 */
public class HomeFragment extends LazyBaseFragment implements ISuccessCallbackData, View.OnClickListener, NetworkListener {
    @BindView(R.id.banner)
    ConvenientBanner banner;
    @BindView(R.id.iv_app_ico)
    SimpleDraweeView ivAppIco;
    @BindView(R.id.tv_app_title)
    TextView tvAppTitle;
    @BindView(R.id.tv_app_mark)
    TextView tvAppMark;
    @BindView(R.id.tv_app_desc)
    TextView tvAppDesc;
    @BindView(R.id.pg_download_rate)
    ProgressBar pgDownloadRate;
    @BindView(R.id.tv_download_state)
    TextView tvDownloadState;
    @BindView(R.id.rl_game_info)
    RelativeLayout rlGameInfo;
    @BindView(R.id.tv_today_update)
    TextView tvTodayUpdate;
    @BindView(R.id.tv_hero_list)
    TextView tvHeroList;
    @BindView(R.id.tv_arm_list)
    TextView tvArmList;
    @BindView(R.id.tv_curse_all)
    TextView tvCurseAll;
    @BindView(R.id.tv_official_enter)
    TextView tvGoodPic;
    @BindView(R.id.tv_game_video)
    TextView tvGameVideo;
    @BindView(R.id.tv_game_answer)
    TextView tvGameAnswer;
    @BindView(R.id.tv_bird_book)
    TextView tvBirdBook;
    Unbinder unbinder;
    private RecyclerView rl_view;
    private ArticleListPresenter mArticleListPresenter;
    private Unbinder mBindHeader;
    private List<ArticleList.ItemsBean> mBannerData;
    private int mType;
    private int mPageCount;
    private int mCurrentPage = Constant.number.TWO;
    private ArticleAdapter mArticleAdapter;
    private SwipeRefreshLayout swRefresh;
    private boolean mIsRefresh = false;
    //已安装
    private boolean mIsInstall = false;
    //已下载完成
    private boolean mIsDownloadComplete = false;
    private boolean mIsDowning = false;

    public static HomeFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt(Constant.string.ARG_ONE, type);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getInt(Constant.string.ARG_ONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        reLoad();
        if (null != banner) {
            banner.setScrollDuration(3000);
            banner.startTurning(6000);
        }
        if (null != tvDownloadState) {
            if (ApkUtil.isInstallByPackage(mContext, SpUtil.getString(mContext, Constant.string.SP_APK_PACKAGE, getResources().getString(R.string.game_package)))) {
                tvDownloadState.setText(getResources().getString(R.string.open));
                mIsInstall = true;
            }
            File file = new File(Constant.string.DOWNLOAD_APK_PATH + File.separator + Constant.string.DEFAULT_APP_NAME);
            if (file.exists()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //检查是否拥有权限
                    this.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Constant.number.HUNDRED_AND_ONE);
                } else {

                    if (SpUtil.getLong(mContext, Constant.string.DOWNLOAD_APK_SIZE + Constant.string.DEFAULT_GAME_NAME, Constant.number.ZERO) == file.length()) {
                        mIsDownloadComplete = true;
                        tvDownloadState.setText(getResources().getString(R.string.install));

                    }
                }
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (null != banner) {
            banner.stopTurning();
        }
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        swRefresh = (SwipeRefreshLayout) view.findViewById(R.id.sw_refresh);
        rl_view = (RecyclerView) view.findViewById(R.id.rl_view);
        swRefresh.setColorSchemeResources(R.color.colorAccent, R.color.colorYellow, R.color.colorRad);
        swRefresh.setRefreshing(true);
        switch (mType) {
            case Constant.number.ZERO:
                rl_view.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                mArticleAdapter = new ArticleAdapter();
                rl_view.setAdapter(mArticleAdapter);
                addHeader();
                break;
        }
        return view;
    }

    private void addHeader() {
        View header = LayoutInflater.from(mContext).inflate(R.layout.home_head, null);
        mArticleAdapter.addHeaderView(header);
        mBindHeader = ButterKnife.bind(this, header);
        if (ApkUtil.isInstallByPackage(mContext, SpUtil.getString(mContext, Constant.string.SP_APK_PACKAGE, getResources().getString(R.string.game_package)))) {
            tvDownloadState.setText(getResources().getString(R.string.open));
            mIsInstall = true;
        }
        File file = new File(Constant.string.DOWNLOAD_APK_PATH + File.separator + Constant.string.DEFAULT_GAME_NAME);
        if (file.exists()) {
            if (SpUtil.getLong(mContext, Constant.string.DOWNLOAD_APK_SIZE + Constant.string.DEFAULT_GAME_NAME, Constant.number.ZERO) == file.length()) {
                mIsDownloadComplete = true;
                tvDownloadState.setText(getResources().getString(R.string.install));
            }
        }
    }

    @Override
    public void initData() {
        switch (mType) {
            case Constant.number.ZERO:
                mArticleListPresenter = new ArticleListPresenter(this);
                mIsRefresh = true;
                mArticleListPresenter.queryList(Constant.number.ONE, null, Constant.number.ONE);
                mArticleListPresenter.queryList(Constant.number.TWO, null, mCurrentPage);
                ivAppIco.setImageResource(R.drawable.ic_game);
                tvAppTitle.setText(getResources().getString(R.string.game_name));
                tvAppMark.setText(getResources().getString(R.string.game_remark));
                tvAppDesc.setText(getResources().getString(R.string.game_desc));
                break;
        }
    }

    @Override
    public void initListener() {
        NetUtil.setNetListener(this);
        rlGameInfo.setOnClickListener(this);
        tvDownloadState.setOnClickListener(this);
        mArticleAdapter.setOnLoadMoreListener(new MyLoadMore());
        banner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                MobclickAgent.onEvent(mContext, Constant.uMengStatistic.HOME_BANNER_HINTS);
                ArticleList.ItemsBean itemsBean = mBannerData.get(position);
                WebViewActivity.toWebViewActivity(mContext, itemsBean.getID(), itemsBean.getSubtitle(), itemsBean.getDesc(), itemsBean.getPic(), Integer.parseInt(TextUtils.isEmpty(itemsBean.getHits()) ? String.valueOf(Constant.number.ZERO) : itemsBean.getHits()), itemsBean.getTime());
            }
        });

        swRefresh.setOnRefreshListener(new MyRefresh());
        switch (mType) {
            case Constant.number.ZERO:
                tvTodayUpdate.setOnClickListener(this);
                tvHeroList.setOnClickListener(this);
                tvArmList.setOnClickListener(this);
                tvCurseAll.setOnClickListener(this);
                tvGoodPic.setOnClickListener(this);
                tvGameVideo.setOnClickListener(this);
                tvGameAnswer.setOnClickListener(this);
                tvBirdBook.setOnClickListener(this);
                mArticleAdapter.setiItemClick(new ArticleAdapter.IItemClick() {
                    @Override
                    public void toArticleDetail(int position) {
                        ArticleList.ItemsBean itemsBean = mArticleAdapter.getItem(position - 1);
                        WebViewActivity.toWebViewActivity(mContext, itemsBean.getID(), itemsBean.getSubtitle(), itemsBean.getDesc(), itemsBean.getPic(), Integer.parseInt(TextUtils.isEmpty(itemsBean.getHits()) ? String.valueOf(Constant.number.ZERO) : itemsBean.getHits()), itemsBean.getTime());
                    }
                });
                break;
        }

    }

    @Override
    public void getResult(Object dataBen, int type) {
        if (swRefresh.isRefreshing()) {
            swRefresh.setRefreshing(false);
        }
        if (null != dataBen) {
            switch (type) {
                case Constant.number.ONE:
                    ArticleList bannerBean = (ArticleList) dataBen;
                    if (null != bannerBean && null != bannerBean.getItems() && Constant.number.ZERO < bannerBean.getItems().size()) {
                        mBannerData = bannerBean.getItems();
                        List<String> bannerPic = new ArrayList<>(mBannerData.size());
                        for (ArticleList.ItemsBean bean : mBannerData) {
                            bannerPic.add(bean.getPic());
                        }
                        banner.setPages(new BannerAdapter(), bannerPic)
                                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                                .setPageIndicator(new int[]{R.drawable.point_color_white_round_shape, R.drawable.point_color_accent_round_shape})
                                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                                .setPageTransformer(new AccordionTransformer()).setCanLoop(true);
                        banner.setScrollDuration(3000);
                        banner.startTurning(6000);
                    }
                    break;
                case Constant.number.TWO:
                    ArticleList articleList = (ArticleList) dataBen;
                    if (null != articleList) {
                        List<ArticleList.ItemsBean> items = articleList.getItems();
                        mPageCount = articleList.getPagecount();
                        mCurrentPage = articleList.getCurpage();
                        if (null != items && Constant.number.ZERO < items.size()) {
                            if (mIsRefresh) {
                                mArticleAdapter.setNewData(items);
                                mArticleAdapter.setEnableLoadMore(true);
                            } else {
                                mArticleAdapter.addData(items);
                                mArticleAdapter.loadMoreComplete();
                            }
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public void failRequest() {
        if (null == mContext || ((AppCompatActivity) mContext).isFinishing()) {
            return;
        }
        if (mIsRefresh) {
            swRefresh.setRefreshing(false);
            mArticleAdapter.setEnableLoadMore(true);
        } else {
            MyToast.s(mContext, getString(R.string.load_more_error));
            mArticleAdapter.loadMoreFail();
        }
    }


    @Override
    public void errorRequest() {
        if (null == mContext || ((AppCompatActivity) mContext).isFinishing())
            return;

        if (mIsRefresh) {
            swRefresh.setRefreshing(false);
            mArticleAdapter.setEnableLoadMore(true);
        } else
            mArticleAdapter.loadMoreFail();
        if (NetUtil.isNetAvailable(mContext))
            MyToast.s(mContext, getString(R.string.client_busy));
        else
            MyToast.s(mContext, getString(R.string.no_net));

    }


    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.rl_game_info:
                intent = new Intent(mContext, AppDetailActivity.class);
                intent.putExtra(Constant.string.ARG_ONE, Constant.number.ZERO);
                break;
            case R.id.tv_download_state:
                if (mIsInstall) {
                    ApkUtil.openApk(mContext, getResources().getString(R.string.game_package));
                    return;
                }
                if (mIsDownloadComplete) {
                    ApkUtil.installApk(mContext, Constant.string.DOWNLOAD_APK_PATH + File.separator + Constant.string.DEFAULT_GAME_NAME);
                    return;
                }
                if (!mIsDowning) {
                    checkStoragePermission();
                }
                break;
            case R.id.tv_today_update://每日一更
                intent = new Intent(mContext, TabWithPagerActivity.class);
                intent.putExtra(Constant.string.ARG_ONE, Constant.number.SIX);
                intent.putExtra(Constant.string.ARG_THREE, Constant.number.THREE);
                intent.putExtra(Constant.string.ARG_TWO, getResources().getString(R.string.update_every_day));
                break;
            case R.id.tv_hero_list://式神大全
                intent = new Intent(mContext, TabWithPagerActivity.class);
                intent.putExtra(Constant.string.ARG_ONE, Constant.number.SEVEN);
                intent.putExtra(Constant.string.ARG_THREE, Constant.number.ZERO);
                intent.putExtra(Constant.string.ARG_TWO, getResources().getString(R.string.hero_list));
                break;
            case R.id.tv_arm_list://装备全解
                intent = new Intent(mContext, TabWithPagerActivity.class);
                intent.putExtra(Constant.string.ARG_ONE, Constant.number.SEVEN);
                intent.putExtra(Constant.string.ARG_THREE, Constant.number.ONE);
                intent.putExtra(Constant.string.ARG_TWO, getResources().getString(R.string.arm_pic_list));
                break;
            case R.id.tv_curse_all://灵咒大全
                intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra(Constant.infoVariable.URL, UrlConstant.CURSE_ALL);
                intent.putExtra(Constant.string.ARG_TWO, getResources().getString(R.string.all_curse));
                break;
            case R.id.tv_official_enter://官网入口
                intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra(Constant.infoVariable.URL, UrlConstant.OFFICAL_ENTER);
                intent.putExtra(Constant.string.ARG_TWO, getResources().getString(R.string.official_enter));
                break;
            case R.id.tv_game_video://游戏视频
                intent = new Intent(mContext, TabWithPagerActivity.class);
                intent.putExtra(Constant.string.ARG_ONE, Constant.number.TWElVE);
                intent.putExtra(Constant.string.ARG_TWO, getResources().getString(R.string.game_video));
                break;
            case R.id.tv_game_answer://手游问答
                intent = new Intent(mContext, TabWithPagerActivity.class);
                intent.putExtra(Constant.string.ARG_ONE, Constant.number.SIX);
                intent.putExtra(Constant.string.ARG_THREE, Constant.number.FIVE);
                intent.putExtra(Constant.string.ARG_TWO, getResources().getString(R.string.game_answer));
                break;
            case R.id.tv_bird_book://入门秘籍
                intent = new Intent(mContext, TabWithPagerActivity.class);
                intent.putExtra(Constant.string.ARG_ONE, Constant.number.SIX);
                intent.putExtra(Constant.string.ARG_THREE, Constant.number.FORE);
                intent.putExtra(Constant.string.ARG_TWO, getResources().getString(R.string.bird_book));
                break;
        }
        if (null != intent) {
            startActivity(intent);
        }
    }

    private void checkStoragePermission() {
        //做一下权限申请
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_DENIED) {
            //弹出对话框接收权限
            this.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS}, Constant.number.HUNDRED);
        } else {
            downloadGame(SpUtil.getString(mContext, Constant.string.SP_GAME_DOWNLOAD_URL, UrlConstant.GAME_DOWN_URL));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constant.number.HUNDRED:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    downloadGame(SpUtil.getString(mContext, Constant.string.SP_GAME_DOWNLOAD_URL, UrlConstant.GAME_DOWN_URL));
                } else {
                    MyToast.showToast(mContext, "已取消下载", Toast.LENGTH_SHORT);
                    // permission denied, boo! Disable the
                }
                break;
            case Constant.number.HUNDRED_AND_ONE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    File file = new File(Constant.string.DOWNLOAD_APK_PATH + File.separator + Constant.string.DEFAULT_GAME_NAME);
                    if (file.exists() && SpUtil.getLong(mContext, Constant.string.DOWNLOAD_APK_SIZE + Constant.string.DEFAULT_GAME_NAME, Constant.number.ZERO) == file.length()) {
                        mIsDownloadComplete = true;
                        tvDownloadState.setText(getResources().getString(R.string.install));
                    }
                } else {
                    MyToast.showToast(mContext, "已取消内存查看权限", Toast.LENGTH_SHORT);
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 最不爱做的一件事,在activity里面获取数据(以后必须处理掉)
     *
     * @param downUrl
     */
    private void downloadGame(String downUrl) {
        mIsDowning = true;
        new OkHttpDownloadUtil().get().download(downUrl, Constant.string.DOWNLOAD_APK_PATH, Constant.string.DEFAULT_GAME_NAME, new OkHttpDownloadUtil.OnDownloadListener() {
            @Override
            public void onDownloadSuccess() {
                mIsDowning = false;
                mIsDownloadComplete = true;
                tvDownloadState.setText(getResources().getString(R.string.install));
                ApkUtil.installApk(mContext, String.valueOf(Constant.string.DOWNLOAD_APK_PATH + File.separator + Constant.string.DEFAULT_GAME_NAME));

            }

            @Override
            public void onDownloading(final int progress) {
                pgDownloadRate.setProgress(progress);
                tvDownloadState.setText(progress + "%");

            }

            @Override
            public void onDownloadFailed(Exception e) {
                e.printStackTrace();
                tvDownloadState.setText(getResources().getString(R.string.download));
                mIsDowning = false;
                //清除缓存
                DataCleanManager.deleteFolderFile(Constant.string.DOWNLOAD_APK_PATH + File.separator + Constant.string.DEFAULT_GAME_NAME, false);
            }
        });
    }

    @Override
    public void changeToMobile() {
        reLoad();
    }

    @Override
    public void changeToWifi() {
        reLoad();

    }


    @Override
    public void noNet() {
        if (null != swRefresh) swRefresh.setRefreshing(false);
        if (null != mArticleAdapter && mArticleAdapter.isLoading()) mArticleAdapter.loadMoreFail();


    }

    public void reLoad() {
        if (null != mArticleListPresenter) {
            if (null == mBannerData || mBannerData.isEmpty())
                mArticleListPresenter.queryList(Constant.number.ONE, null, Constant.number.ONE);

            if (null == mArticleAdapter.getData() || mArticleAdapter.getData().isEmpty())
                mIsRefresh = true;
            mArticleListPresenter.queryList(Constant.number.TWO, null, mCurrentPage);
        }
    }

    /**
     * 刷新
     */
    private class MyRefresh implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            rl_view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mIsRefresh = true;
                    mCurrentPage = Constant.number.ONE;
                    mArticleAdapter.setEnableLoadMore(false);
                    switch (mType) {
                        case Constant.number.ZERO:
                            if (null != mArticleListPresenter) {
                                mArticleListPresenter.queryList(Constant.number.TWO, null, mCurrentPage);
                                if (null == mBannerData) {
                                    mArticleListPresenter.queryList(Constant.number.ONE, null, mCurrentPage);
                                }
                            }
                            break;
                    }
                }
            }, 2000);

        }
    }

    /**
     * 加载更多
     */
    private class MyLoadMore implements com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener {
        @Override
        public void onLoadMoreRequested() {
            if (mCurrentPage < mPageCount) {
                mIsRefresh = false;
                ++mCurrentPage;
                mArticleListPresenter.queryList(Constant.number.TWO, null, mCurrentPage);
            } else {
                mArticleAdapter.loadMoreEnd();
            }

        }
    }

    @Override
    public void recycleMemory() {
        if (null != mBindHeader) {
            mBindHeader.unbind();
        }
    }

}
