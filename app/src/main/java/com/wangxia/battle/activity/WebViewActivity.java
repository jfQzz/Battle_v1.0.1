package com.wangxia.battle.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.wangxia.battle.R;
import com.wangxia.battle.globe.App;
import com.wangxia.battle.model.http.UrlConstant;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.LogUtil;
import com.wangxia.battle.util.MyToast;
import com.wangxia.battle.util.Mytime;
import com.wangxia.battle.util.NetUtil;
import com.wangxia.battle.util.ShareUtil;
import com.wangxia.battle.util.TxtFormatUtil;

import java.util.List;

import butterknife.BindView;

@SuppressLint("SetJavaScriptEnabled")
public class WebViewActivity extends BaseActivity implements View.OnClickListener {
    public static final String SPLIT_UK = "youku://play";
    public static final String REPLACE_UK = "http://m.youku.com/video/id_XMzA2MzE2MzIxMg==.html";
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_action_right_one)
    ImageView ivActionRightOne;
    @BindView(R.id.iv_action_right_two)
    ImageView ivActionRightTwo;
    @BindView(R.id.iv_action_right_three)
    ImageView ivActionRightThree;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.loading)
    FrameLayout loading;
    @BindView(R.id.btn_favorite_article)
    FloatingActionButton btnFavoriteArti;
    private int mId;
    private String mTitle;
    private int mLoadCount;
    private boolean mIsFavorite = false;
    private String mPic;
    private int mHints;
    private String mTime;
    private String mAuthor;
    private String mAuthorIco;
    private String webUrl;

    public static void toWebViewActivity(Context context, int id, String title, String pic, int hints, String publishTime, String author, String authorIco) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(Constant.string.ARG_ONE, id);
        intent.putExtra(Constant.string.ARG_TWO, title);
        intent.putExtra(Constant.string.ARG_THREE, pic);
        intent.putExtra(Constant.string.ARG_FORE, hints);
        intent.putExtra(Constant.string.ARG_FIVE, publishTime);
        intent.putExtra(Constant.string.ARG_SIX, author);
        intent.putExtra(Constant.string.ARG_SEVEN, authorIco);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        Intent intent = getIntent();
        mId = intent.getIntExtra(Constant.string.ARG_ONE, 0);
        mTitle = intent.getStringExtra(Constant.string.ARG_TWO);
        mPic = intent.getStringExtra(Constant.string.ARG_THREE);
        mHints = intent.getIntExtra(Constant.string.ARG_FORE, 0);
        mTime = intent.getStringExtra(Constant.string.ARG_FIVE);
        mAuthor = intent.getStringExtra(Constant.string.ARG_SIX);
        mAuthorIco = intent.getStringExtra(Constant.string.ARG_SEVEN);
        mLoadCount = intent.getIntExtra(Constant.string.ARG_EIGHT, 0);
        webUrl = intent.getStringExtra(Constant.infoVariable.URL);
        return R.layout.activity_web_view;
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        ivActionRightOne.setVisibility(View.GONE);
        ivActionRightTwo.setVisibility(View.GONE);
        ivActionRightThree.setVisibility(View.VISIBLE);
        if (TextUtils.isEmpty(mTitle)) {
            mTime = getResources().getString(R.string.activity);
        }
        tvTitle.setText(TxtFormatUtil.HtmlFormat(mTitle));
        initWebView();
        if (TextUtils.isEmpty(webUrl)) {
            App.mReaderManager.addArticleBrowseDB(mId, mPic, mTitle, mHints, mTime, mAuthor, mAuthorIco, Mytime.getStringToday());
            if (App.mReaderManager.isFavoriteArticleById(mId)) {
                mIsFavorite = true;
                btnFavoriteArti.setImageResource(R.drawable.white_favorited);
            }
        } else {
            btnFavoriteArti.setVisibility(View.GONE);
        }
        MobclickAgent.onEvent(WebViewActivity.this, Constant.uMengStatistic.WATCH_ARTICLE);
    }

    private void initWebView() {
        //得到webView的参数设置对象
        WebSettings settings = webView.getSettings();
        //启用js功能---网页动态图等效果都要js
        settings.setJavaScriptEnabled(true); //支持动态页面,播放视频必须加
        settings.setPluginState(WebSettings.PluginState.ON); //支持视频播放
        //允许获取焦点
        webView.requestFocusFromTouch();
        //缩放操作
        settings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        //自动视屏
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //支持多窗口
        settings.supportMultipleWindows();
        settings.setDomStorageEnabled(true);

        settings.setUseWideViewPort(true); // 关键点
        settings.setAllowFileAccess(true); // 允许访问文件
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 不加载缓存内容

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }

    @SuppressLint("JavascriptInterface")
    @Override
    protected void initListener() {

        ivBack.setOnClickListener(this);
        ivActionRightThree.setOnClickListener(this);
        btnFavoriteArti.setOnClickListener(this);
        webView.addJavascriptInterface(new Object() {
            @JavascriptInterface
            public void showArticle(int id, String title, String image, String desc) {
                //跳转页面
                LogUtil.i(mLoadCount + "");
                Intent intent = new Intent(WebViewActivity.this, WebViewActivity.class);
                intent.putExtra(Constant.string.ARG_ONE, id);
                intent.putExtra(Constant.string.ARG_TWO, title);
                intent.putExtra(Constant.string.ARG_EIGHT, mLoadCount + 1);
                //0是最初
                if (1 < mLoadCount) {
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra(Constant.string.ARG_EIGHT, 0);
                }
                startActivity(intent);
            }

            @JavascriptInterface
            public void loadhttp(String TheUrl) {
                //跳转页面
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(TheUrl));
                if (null != intent) {
                    //检查设备中可以响应的activity
                    PackageManager pm = getPackageManager();
                    List<ResolveInfo> resolveInfos = pm.queryIntentActivities(intent, 0);
                    if (resolveInfos.size() > 0) {
                        //开启一个新的栈
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        MyToast.showToast(WebViewActivity.this, "您的手机暂未安装浏览器", Toast.LENGTH_SHORT);
                    }

                }
            }

            //下载网站广告App Url：app下载地址
            @JavascriptInterface
            public void downhttp(String url, String title) {
                //开启下载
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(url));
                intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
                PackageManager pm = getPackageManager();
                List<ResolveInfo> resolveInfo = pm.queryIntentActivities(intent, Constant.number.ZERO);
                if (resolveInfo.size() == Constant.number.ZERO) {
                    MyToast.showToast(WebViewActivity.this, "您的手机暂未安装浏览器", Toast.LENGTH_SHORT);
                    return;
                } else {
                    //开启一个新的栈
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }

            //文章中的标签
            @JavascriptInterface
            public void loadlalels(String label) {
                //有该标签的相关游戏，跳转至标签详情界面，没有的话就打开浏览器

            }

            //文章中人名点击
            @JavascriptInterface
            public void toUser(int userId) {
                //有该标签的相关游戏，跳转至标签详情界面，没有的话就打开浏览器
                //跳转页面

            }
        }, "Android");

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtil.i("shouldOverrideUrlLoading~~~~~~~~~~~~~~~~~");
                if (url.contains(SPLIT_UK)) {
                    String substring = url.substring(SPLIT_UK.length(), url.length());
                    url = REPLACE_UK+substring;
//                    Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
//                    startActivity(intent);
                    LogUtil.i(url);
                    return true;
                }
                if (!NetUtil.isNetworkAvailable(WebViewActivity.this)) {
                    //无网络连接
                    MyToast.showToast(WebViewActivity.this, "当前无网络连接~!", Toast.LENGTH_SHORT);
                    webView.setVisibility(View.GONE);
                    loading.setVisibility(View.GONE);
                    return true;
                }
//                webView.loadUrl(url);
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                LogUtil.i("onPageStarted~~~~~~~~~~~~~~~~~");
                if (WebViewActivity.this == null || WebViewActivity.this.isFinishing()) {
                    return;
                }
                view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                LogUtil.i("onPageFinished~~~~~~~~~~~~~~~~~");
                if (WebViewActivity.this == null || WebViewActivity.this.isFinishing()) {
                    return;
                }
                loading.setVisibility(View.GONE);
                view.setLayerType(View.LAYER_TYPE_NONE, null);
                super.onPageFinished(view, url);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    @Override
    protected void initData() {
        if (!NetUtil.isNetworkAvailable(this)) {
            //无网络连接
            MyToast.showToast(this, "当前无网络连接~!", Toast.LENGTH_SHORT);
            webView.setVisibility(View.GONE); //隐藏
        } else {
            if (TextUtils.isEmpty(webUrl)) {
                webView.loadUrl(UrlConstant.ARTICLE_DOMAIN_NAME + "app/a/" + mId + ".html");
            } else {
                webView.loadUrl(webUrl);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
        MobclickAgent.onPageStart("ArticleActivity");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
        MobclickAgent.onPageEnd("ArticleActivity");
        MobclickAgent.onPause(this);
    }

    @Override
    protected void clearMemory() {
        if (webView != null) {
            webView.stopLoading();
            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.removeAllViews();
            webView.setWebChromeClient(null);
            webView.setWebViewClient(null);
            webView.destroy();
            webView = null;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_favorite_article:
                if (mIsFavorite) {
                    btnFavoriteArti.setImageResource(R.drawable.white_no_favorite);
                    App.mReaderManager.deleteFavoriteArticleById(mId);
                    mIsFavorite = false;
                } else {
                    btnFavoriteArti.setImageResource(R.drawable.white_favorited);
                    App.mReaderManager.addArticleFavoriteDB(mId, mPic, mTitle, mHints, mTime, mAuthor, mAuthorIco, Mytime.getStringToday());
                    mIsFavorite = true;
                }
                break;
            case R.id.iv_action_right_three:
                share();
                break;
        }

    }

    private void share() {
        ShareUtil.localShare(this, mTitle, "手游攻略", UrlConstant.ARTICLE_DOMAIN_NAME + "app/a/" + mId + ".html");
    }
}
