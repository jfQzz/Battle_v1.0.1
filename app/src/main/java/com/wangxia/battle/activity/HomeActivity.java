package com.wangxia.battle.activity;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.analytics.MobclickAgent;
import com.wangxia.battle.R;
import com.wangxia.battle.adapter.PagerAdapter;
import com.wangxia.battle.broadcast.AppInstallReceiver;
import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.fragment.HomeFragment;
import com.wangxia.battle.fragment.MineFragment;
import com.wangxia.battle.fragment.RankFragment;
import com.wangxia.battle.fragment.list.ArticleListFragment;
import com.wangxia.battle.globe.App;
import com.wangxia.battle.model.bean.AppUpdateBean;
import com.wangxia.battle.presenter.impPresenter.AppUpdatePresenter;
import com.wangxia.battle.util.ApkUtil;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.DataCleanManager;
import com.wangxia.battle.util.LogUtil;
import com.wangxia.battle.util.MyToast;
import com.wangxia.battle.util.OkHttpDownloadUtil;
import com.wangxia.battle.util.SpUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeActivity extends BaseActivity implements View.OnClickListener, ISuccessCallbackData {
    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.view_pager)
    ViewPager vp_contain;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.ll_bottom_menu)
    LinearLayout ll_bottom_menu;
    @BindView(R.id.ll_home)
    LinearLayout ll_home;
    @BindView(R.id.ll_rank)
    LinearLayout ll_rank;
    @BindView(R.id.ll_attention)
    LinearLayout ll_attention;
    @BindView(R.id.ll_find)
    LinearLayout ll_find;
    @BindView(R.id.ll_my_game)
    LinearLayout ll_my_game;
    @BindView(R.id.iv_user_ico)
    SimpleDraweeView iv_user_ico;
    @BindView(R.id.iv_action_right_one)
    ImageView iv_action_right_one;
    @BindView(R.id.iv_action_right_two)
    ImageView iv_action_right_two;
    private LinearLayout ll_last_selected = null;
    private List<LinearLayout> mBottomLinear = new ArrayList<>(Constant.number.FIVE);
    private long exitTime = 0;
    private boolean mIsUpdateComplete = false;
    private AppUpdatePresenter mAppUpdatePresenter;
    private Button mBtnUpdate;
    private ProgressBar mProgressAppUpdate;
    private AppInstallReceiver mApkInstallListener;
    private AlertDialog mUpdateDialog;
    private AppUpdateBean updateBean;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        mBottomLinear.add(ll_home);
        mBottomLinear.add(ll_rank);
        mBottomLinear.add(ll_find);
        mBottomLinear.add(ll_attention);
        mBottomLinear.add(ll_my_game);
        setCurrentFragment(Constant.number.ZERO);
        registerApkInstalListener();
    }


    @Override
    protected void onResume() {
        //用versionCode和最新的对比,若相等更新成功，反之显示更新
        if (TextUtils.equals(ApkUtil.getVerName(this), SpUtil.getString(this, Constant.string.SP_SERVER_APK_VERSION, null))) {
            if (null != mUpdateDialog) {
                mUpdateDialog.dismiss();
            }
        }
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) { //返回键
            if ((System.currentTimeMillis() - exitTime) > 3000) {
//                ResolveInfo homeInfo = getPackageManager().resolveActivity(new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME), 0);
//                ActivityInfo ai = homeInfo.activityInfo;
//                Intent startIntent = new Intent(Intent.ACTION_MAIN);
//                startIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//                startIntent.setComponent(new ComponentName(ai.packageName, ai.name));
//                startActivity(startIntent);
                MyToast.showToast(this, getResources().getString(R.string.exit_battle), Toast.LENGTH_SHORT);
                exitTime = System.currentTimeMillis();
            } else {
                MobclickAgent.onKillProcess(this);
                clearCache();
                finish();
                System.exit(Constant.number.ZERO);
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private void clearCache() {
        long externalCacheSize = 0;
        long innerCacheSize = 0;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                externalCacheSize = DataCleanManager.getFolderSize(getExternalCacheDir());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            innerCacheSize = DataCleanManager.getFolderSize(getCacheDir());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //总缓存
        long totalCache = externalCacheSize + innerCacheSize;
        if (totalCache >= 40 * 1048576) {
            if (externalCacheSize > 0) {
                DataCleanManager.cleanExternalCache(this);
            }
            if (innerCacheSize > 0) {
                DataCleanManager.cleanInternalCache(this);
            }
        }
    }

    private void setCurrentFragment(int position) {
        for (int i = 0, count = mBottomLinear.size(); i < count; i++) {
            vp_contain.setCurrentItem(i);
            LinearLayout linear = mBottomLinear.get(i);
            if (i == position) {
                linear.getChildAt(0).setEnabled(true);
                linear.getChildAt(1).setEnabled(true);
//                linear.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                ll_last_selected = linear;
            } else {
                linear.getChildAt(0).setEnabled(false);
                linear.getChildAt(1).setEnabled(false);
//                linear.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }
        }
    }

    @Override
    protected void initListener() {
        ll_home.setOnClickListener(this);
        ll_rank.setOnClickListener(this);
        ll_find.setOnClickListener(this);
        ll_attention.setOnClickListener(this);
        ll_my_game.setOnClickListener(this);
        iv_user_ico.setOnClickListener(this);

        vp_contain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeViewAndTitle(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int offset = appBarLayout.getHeight() + verticalOffset;
                if(0 <= offset){
                    ll_bottom_menu.getLayoutParams().height = offset;
                    ll_bottom_menu.requestLayout();
                }
            }
        });
    }


    private void changeViewAndTitle(int position) {
        LinearLayout ll_select = null;
        switch (position) {
            case 0:
                ll_select = ll_home;
                break;
            case 1:
                ll_select = ll_rank;
                break;
            case 2:
                ll_select = ll_find;
                break;
//            case 3:
//                ll_select = ll_attention;
//                break;
            case 3:
                ll_select = ll_my_game;
                break;
        }
        initClickLinear(ll_select, position);
    }

    private void initClickLinear(LinearLayout ll_select, int position) {
        switch (position) {
            case 0:
                tv_title.setText(getResources().getString(R.string.game_name));
//                iv_action_right_one.setVisibility(View.VISIBLE);
//                iv_action_right_two.setVisibility(View.VISIBLE);
                break;
            case 1:
                tv_title.setText(getResources().getString(R.string.recommend));
//                iv_action_right_one.setVisibility(View.GONE);
//                iv_action_right_two.setVisibility(View.VISIBLE);
                break;
            case 2:
                tv_title.setText(getResources().getString(R.string.strategy));
//                iv_action_right_one.setVisibility(View.GONE);
//                iv_action_right_two.setVisibility(View.VISIBLE);
                break;
//            case 3:
//                tv_title.setText(getResources().getString(R.string.amusement));
////                iv_action_right_one.setVisibility(View.GONE);
////                iv_action_right_two.setVisibility(View.VISIBLE);
//                break;
            case 3:
                tv_title.setText(getResources().getString(R.string.my_games));
//                iv_action_right_one.setVisibility(View.GONE);
//                iv_action_right_two.setVisibility(View.GONE);
                break;
        }
        if (null != ll_last_selected) {
            ll_last_selected.getChildAt(0).setEnabled(false);
            ll_last_selected.getChildAt(1).setEnabled(false);
//            ll_last_selected.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
        ll_select.getChildAt(0).setEnabled(true);
        ll_select.getChildAt(1).setEnabled(true);
//        ll_select.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        ll_last_selected = ll_select;

    }

    @Override
    protected void initData() {
        ArrayList<Fragment> mFragmentData = new ArrayList<>();
        mFragmentData.add(HomeFragment.newInstance(Constant.number.ZERO));
        mFragmentData.add(RankFragment.newInstance());
        mFragmentData.add(ArticleListFragment.newInstance(Constant.number.ZERO, null));
        // 视频列表
//        mFragmentData.add(VideoListFragment.newInstance());
        mFragmentData.add(MineFragment.newInstance());
        vp_contain.setAdapter(new PagerAdapter(getSupportFragmentManager(), mFragmentData));
        vp_contain.setOffscreenPageLimit(Constant.number.FORE);
        mAppUpdatePresenter = new AppUpdatePresenter(this);
        mAppUpdatePresenter.queryList(Constant.number.ZERO, null, Constant.number.ZERO);

    }


    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void clearMemory() {
        if (null != mApkInstallListener) {
            unregisterReceiver(mApkInstallListener);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home:
                setSelect(ll_home, 0);
                break;
            case R.id.ll_rank:
                setSelect(ll_rank, 1);
                break;
            case R.id.ll_find:
                setSelect(ll_find, 2);
                break;
//            case R.id.ll_attention:
//                setSelect(ll_attention, 3);
//                break;
            case R.id.ll_my_game:
                setSelect(ll_my_game, 3);
                break;
            case R.id.iv_user_ico:

                break;
        }
    }

    private void setSelect(LinearLayout ll_select, int position) {
        initClickLinear(ll_select, position);
        vp_contain.setCurrentItem(position);
    }

    private void updateApp(final AppUpdateBean updateBean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_updata_app, null);
        TextView tvAppVersion = (TextView) view.findViewById(R.id.tv_version);
        TextView tvAppSize = (TextView) view.findViewById(R.id.tv_size);
        TextView tvAppLog = (TextView) view.findViewById(R.id.tv_update_desc);
        tvAppVersion.setText(String.valueOf(updateBean.getVer() + " V"));
        tvAppSize.setText(updateBean.getSize());
        tvAppLog.setText(updateBean.getDesc());
        LogUtil.i(updateBean.toString());
        mBtnUpdate = (Button) view.findViewById(R.id.btn_update);
        mProgressAppUpdate = (ProgressBar) view.findViewById(R.id.progress_app_update);
        TextView tvExit = (TextView) view.findViewById(R.id.tv_exit);
        builder.setCancelable(false);
        builder.setView(view);
        mUpdateDialog = builder.create();
        Window window = mUpdateDialog.getWindow();
        window.setGravity(Gravity.CENTER_HORIZONTAL);
        window.getDecorView().setBackgroundColor(Color.TRANSPARENT);
        mUpdateDialog.show();
        File file = new File(Constant.string.DOWNLOAD_PATH);
        if (file.exists()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission_group.STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //检查是否拥有权限
                ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS}, Constant.number.HUNDRED_AND_ONE);
            } else {
                File[] files = file.listFiles();
                if (null != files) {
                    for (int i = 0, count = files.length; i < count; i++) {
                        String apkName = files[i].getName();
                        if (TextUtils.equals(apkName, SpUtil.getString(this, Constant.string.UPDATE_APP_NAME, Constant.string.DEFAULT_APP_NAME)) && SpUtil.getLong(this, Constant.string.DOWNLOAD_APK_SIZE + Constant.string.DEFAULT_APP_NAME, Constant.number.ZERO) == files[i].length()) {
                            mIsUpdateComplete = true;
                            mBtnUpdate.setText(getResources().getString(R.string.install_now));
                        }
                    }
                }
            }
        }
        mBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsUpdateComplete) {
                    MobclickAgent.onEvent(HomeActivity.this, Constant.uMengStatistic.NEW_VERSION_INSTALL_PASSIVE);
                    //app安装
                    ApkUtil.installApk(HomeActivity.this, String.valueOf(Constant.string.DOWNLOAD_PATH + File.separator + Constant.string.DEFAULT_APP_NAME));
                } else {
                    MobclickAgent.onEvent(HomeActivity.this, Constant.uMengStatistic.NEW_VERSION_UPDATE);
                    v.setVisibility(View.GONE);
                    //执行下载，更新进度条，下载完成自动安装
                    downloadNewApp(updateBean.getDown());
                }
            }
        });

        tvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobclickAgent.onEvent(HomeActivity.this, Constant.uMengStatistic.NEW_VERSION_EXIT);
                MobclickAgent.onKillProcess(HomeActivity.this);
                finish();
                System.exit(Constant.number.ZERO);
            }
        });
    }


    @Override
    public void getResult(Object dataBen, int type) {
        if (null != dataBen) {
            switch (type) {
                case Constant.number.ZERO:
                    updateBean = (AppUpdateBean) dataBen;
                    //检测本地和获取到app版本号
                    checkAppUpdate();
                    break;
            }
        }
    }

    /**
     * 检测app是否需要更新
     */
    private void checkAppUpdate() {
        String localVersion = getPackageVersion();
        //版本判断
        if (!TextUtils.isEmpty(localVersion) && !TextUtils.isEmpty(updateBean.getVer()) && !TextUtils.equals(localVersion, updateBean.getVer()) &&
                !TextUtils.isEmpty(updateBean.getDown()) && !TextUtils.isEmpty(updateBean.getDesc()) && !TextUtils.isEmpty(updateBean.getSize())) {
            //弹出升级提示框
            if (newVersion(localVersion, updateBean.getVer())) checkStoragePermission();
        }
    }

    private void checkStoragePermission() {
        //做一下权限申请
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //检查是否拥有权限
            ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS}, Constant.number.HUNDRED);
        } else {
            updateApp(updateBean);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constant.number.HUNDRED:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    updateApp(updateBean);
                } else {
                    return;
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                break;
            case Constant.number.HUNDRED_AND_ONE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    File file = new File(Constant.string.DOWNLOAD_PATH);
                    File[] files = file.listFiles();
                    if (null != files) {
                        for (int i = 0, count = files.length; i < count; i++) {
                            String apkName = files[i].getName();
                            if (TextUtils.equals(apkName, SpUtil.getString(this, Constant.string.UPDATE_APP_NAME, Constant.string.DEFAULT_APP_NAME)) && SpUtil.getLong(this, Constant.string.DOWNLOAD_APK_SIZE + Constant.string.DEFAULT_APP_NAME, Constant.number.ZERO) == files[i].length()) {
                                mIsUpdateComplete = true;
                                mBtnUpdate.setText(getResources().getString(R.string.install_now));
                            }
                        }
                    }
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    /**
     * 对传入的服务器版本号和本地版本号进行细致的分析,避免服务器缓存的问题
     * 1.0.1   1.0.2  分成3段,依次判断即可
     */
    private boolean newVersion(String localVersion, String serviceVersion) {
        SpUtil.getString(this, Constant.string.SP_SERVER_APK_VERSION, serviceVersion);
        String[] localVersionArray = new String[0];
        String[] serverVersionArray = new String[0];
        try {
            //本地版本
            localVersionArray = localVersion.split("\\.");
            //服务器
            serverVersionArray = serviceVersion.split("\\.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //先判断有3段,没有3段的,直接返回true算了,怕出意外
        if (localVersionArray.length == Constant.number.THREE && serverVersionArray.length == Constant.number.THREE) {
            //分解
            int local_0 = Integer.parseInt(localVersionArray[0]);
            int local_1 = Integer.parseInt(localVersionArray[1]);
            int local_2 = Integer.parseInt(localVersionArray[2]);
            int service_0 = Integer.parseInt(serverVersionArray[0]);
            int service_1 = Integer.parseInt(serverVersionArray[1]);
            int service_2 = Integer.parseInt(serverVersionArray[2]);
            if (service_0 > local_0) { //服务器版本大于本地
                return true; //
            } else if (service_0 == local_0) {
                if (service_1 > local_1) {
                    return true;
                } else if (service_1 == local_1) {
                    return service_2 > local_2;
                }
            }
            //其他是小于的
            return false;
        } else {
            return true; //更新
        }
    }

    private String getPackageVersion() {
        try {
            PackageInfo packageInfo = App.context.getPackageManager().getPackageInfo(App.context.getPackageName(), 0);
            String version = packageInfo.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 最不爱做的一件事,在activity里面获取数据(以后必须处理掉)
     *
     * @param downUrl
     */
    private void downloadNewApp(String downUrl) {
        new OkHttpDownloadUtil().get().download(downUrl, Constant.string.DOWNLOAD_PATH, Constant.string.DEFAULT_APP_NAME, new OkHttpDownloadUtil.OnDownloadListener() {
            @Override
            public void onDownloadSuccess() {
                runOnUiThread(new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mIsUpdateComplete = true;
                        mProgressAppUpdate.setVisibility(View.GONE);
                        mBtnUpdate.setVisibility(View.VISIBLE);
                        mBtnUpdate.setText(getResources().getString(R.string.install_now));
                        MobclickAgent.onEvent(HomeActivity.this, Constant.uMengStatistic.NEW_VERSION_INSTALL_AUTOMATION);
                        ApkUtil.installApk(HomeActivity.this, String.valueOf(Constant.string.DOWNLOAD_PATH + File.separator + Constant.string.DEFAULT_APP_NAME));
                    }
                }));
            }

            @Override
            public void onDownloading(int progress) {
                mProgressAppUpdate.setProgress(progress);
            }

            @Override
            public void onDownloadFailed(Exception e) {
                //清除缓存，退出app，进入再次操作
                DataCleanManager.deleteFolderFile(Constant.string.DOWNLOAD_PATH, true);
                MobclickAgent.onKillProcess(HomeActivity.this);
                finish();
                System.exit(Constant.number.ZERO);
            }
        });
    }

    // 动态注册apk安装监听器
    private void registerApkInstalListener() {
        mApkInstallListener = new AppInstallReceiver();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_MEDIA_MOUNTED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED); //添加
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED); //删除
        intentFilter.addAction(Intent.ACTION_PACKAGE_REPLACED); //替换
        intentFilter.addDataScheme("package");
        registerReceiver(mApkInstallListener, intentFilter);
    }

}
