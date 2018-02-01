package com.wangxia.battle.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.umeng.analytics.MobclickAgent;
import com.wangxia.battle.R;
import com.wangxia.battle.adapter.EvaluateAdapter;
import com.wangxia.battle.callback.CommentCallback;
import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.fragment.CommentFragment;
import com.wangxia.battle.globe.App;
import com.wangxia.battle.model.bean.CommentBean;
import com.wangxia.battle.model.bean.EvaluateBean;
import com.wangxia.battle.model.bean.SuccessBean;
import com.wangxia.battle.model.http.UrlConstant;
import com.wangxia.battle.presenter.impPresenter.CommentListPresenter;
import com.wangxia.battle.presenter.impPresenter.UpVotePresenter;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.DensityUtil;
import com.wangxia.battle.util.LogUtil;
import com.wangxia.battle.util.MyToast;
import com.wangxia.battle.util.Mytime;
import com.wangxia.battle.util.NetUtil;
import com.wangxia.battle.util.OkHttpDownloadUtil;
import com.wangxia.battle.util.ShareUtil;
import com.wangxia.battle.util.SpUtil;
import com.wangxia.battle.util.StatusBarUtil;
import com.wangxia.battle.util.UserUtil;
import com.wangxia.battle.view.ScrollListenerScrollView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;

@SuppressLint("SetJavaScriptEnabled")
public class WebViewActivity extends BaseActivity implements View.OnClickListener, CommentCallback, ISuccessCallbackData, EvaluateAdapter.IUserHandle {
    @BindView(R.id.sl_view)
    ScrollListenerScrollView slView;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_action_bar)
    LinearLayout llActionBar;
    @BindView(R.id.iv_action_right_one)
    ImageView ivActionRightOne;
    @BindView(R.id.iv_action_right_two)
    ImageView ivActionRightTwo;
    @BindView(R.id.iv_action_right_three)
    ImageView ivActionRightThree;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.iv_favorite)
    ImageView ivFavorite;
    @BindView(R.id.rl_comment)
    RelativeLayout rlComment;
    @BindView(R.id.ll_evaluate)
    LinearLayout llEvaluate;
    @BindView(R.id.tv_evaluate)
    TextView tvEvaluate;
    @BindView(R.id.iv_user_ic)
    SimpleDraweeView ivUser;
    @BindView(R.id.ll_bottom_function)
    LinearLayout llBottomFunction;
    @BindView(R.id.rl_view)
    RecyclerView rlView;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    @BindView(R.id.pg_load_rate)
    ProgressBar pgLoadRate;
    @BindView(R.id.tv_evaluate_title)
    TextView tvEvaluateTitle;
    public static final String netErrorHtml = "file:///android_asset/net_error.html";
    private int mId;
    private String mTitle;
    private int mLoadCount;
    private boolean mIsFavorite = false;
    private String mPic;
    private int mHints;
    private String mTime;
    private String webUrl;
    private String mLastInput;
    private CommentListPresenter mCommentListPresenter;
    private int mCurrentPage = 1;
    private int mPageCount;
    private EvaluateAdapter mEvaluateAdapter;
    private Timer mTimer;
    private int mUpVoteIndex;
    private boolean mIsRefresh;
    private boolean mIsNeedUserInfo;
    private String mDesc;
    private int mCacheTag;
    private boolean mIsReTry = false;
    private int distency = DensityUtil.dip2px(5);
    private int mScrollDistency = 0;

    public static void toWebViewActivity(Context context, int id, String title, String desc, String pic, int hints, String publishTime) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(Constant.string.ARG_ONE, id);
        intent.putExtra(Constant.string.ARG_TWO, title);
        intent.putExtra(Constant.string.ARG_THREE, pic);
        intent.putExtra(Constant.string.ARG_FORE, hints);
        intent.putExtra(Constant.string.ARG_FIVE, publishTime);
        intent.putExtra(Constant.string.ARG_EIGHT, desc);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
        mDesc = intent.getStringExtra(Constant.string.ARG_EIGHT);
        mCacheTag = intent.getIntExtra(Constant.string.ARG_TEN, 0);
        mLoadCount = intent.getIntExtra(Constant.string.ARG_NINE, 0);
        webUrl = intent.getStringExtra(Constant.infoVariable.URL);
        return R.layout.activity_web_view;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setTransparentForImageView(this, null);
        ivBack.setVisibility(View.VISIBLE);
        ivActionRightOne.setVisibility(View.GONE);
        ivActionRightTwo.setVisibility(View.GONE);
        ivActionRightThree.setVisibility(View.VISIBLE);
        if (TextUtils.isEmpty(mTitle)) {
            mTitle = getResources().getString(R.string.activity);
        }
        initWebView();
        tvTitle.setText(null);
        if (TextUtils.isEmpty(webUrl)) {
            if (App.mReaderManager.isFavoriteArticleById(mId)) {
                mIsFavorite = true;
                ivFavorite.setImageResource(R.drawable.ic_favorited);
            }
            rlView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            mEvaluateAdapter = new EvaluateAdapter();
            rlView.setAdapter(mEvaluateAdapter);
            mEvaluateAdapter.setOnLoadMoreListener(new MyLoadMore(), rlView);
            mEvaluateAdapter.initUserHandler(this);
        } else {
            llBottomFunction.setVisibility(View.GONE);
            rlView.setVisibility(View.GONE);
        }
        MobclickAgent.onEvent(WebViewActivity.this, Constant.uMengStatistic.WATCH_ARTICLE);
    }

    @Override
    protected void initData() {
        mCommentListPresenter = new CommentListPresenter(this);
        if (!NetUtil.isNetAvailable(this)) {
            if (App.mReaderManager.isBrowseArticleById(mId)) {
                File file = new File(Constant.savePath.WEB_PAGE_CACHE_HTML);
                if (file.exists()) {
                    String[] files = file.list();
                    if (null != files) {
                        for (String fileName : files) {
                            if (String.valueOf(mId + ".html").equals(fileName)) {
                                File htmlFile = new File(Constant.savePath.WEB_PAGE_CACHE_HTML, String.valueOf(mId + ".html"));
                                if (Build.VERSION_CODES.N <= Build.VERSION.SDK_INT) {
                                    Uri uri = FileProvider.getUriForFile(App.context, App.context.getPackageName() + ".apkDownload", htmlFile);
                                    webView.loadUrl("file:///" + htmlFile.getAbsolutePath());
                                    return;
                                } else {
                                    webView.loadUrl("file:///" + htmlFile.getAbsolutePath());
                                }
                                return;
                            }
                        }
                    }
                }
            } else webView.loadUrl(netErrorHtml);
            mCacheTag = Constant.number.TWO;

        } else {
            if (!TextUtils.isEmpty(webUrl)) {
                mCacheTag = Constant.number.TWO;
                webView.loadUrl(webUrl);
            } else
                webView.loadUrl(UrlConstant.ARTICLE_DOMAIN_NAME + "app/a/" + mId + ".html");
        }
        ivUser.setImageURI(SpUtil.getString(this, Constant.userInfo.USER_ICON, null));
    }

    private void initWebView() {
        mTimer = new Timer(true);
        webView.addJavascriptInterface(new JavaScriptLocalObj(), "local_obj");
        webView.setWebViewClient(new myWebClient());
        webView.setWebChromeClient(new myWebChromeClient());
        webView.requestFocusFromTouch();
        webView.setLayerType(View.LAYER_TYPE_NONE, null);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setPluginState(WebSettings.PluginState.ON);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        //自动视屏
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.supportMultipleWindows();
        settings.setDomStorageEnabled(true);
//        settings.setSupportMultipleWindows(true);// 新加
        settings.setUseWideViewPort(true);
    }


    @SuppressLint("JavascriptInterface")
    @Override
    protected void initListener() {
        ivBack.setOnClickListener(this);
        ivActionRightThree.setOnClickListener(this);
        ivFavorite.setOnClickListener(this);
        rlComment.setOnClickListener(this);
        llEvaluate.setOnClickListener(this);
        slView.setOnScrollChangeListener(new ScrollListenerScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChanged(int x, int y) {
                LogUtil.i("-----            "+y);
                if(Constant.number.ZERO < y){
                    mScrollDistency +=y;
                }else mScrollDistency = Constant.number.ZERO;
               if(llActionBar.getHeight() < mScrollDistency && View.VISIBLE == llActionBar.getVisibility()){
                   llActionBar.setVisibility(View.GONE);
                   llBottomFunction.setVisibility(View.GONE);
               }
                if(y < -5 && View.GONE == llActionBar.getVisibility()){
                   llActionBar.setVisibility(View.VISIBLE);
                   llBottomFunction.setVisibility(View.VISIBLE);
               }
            }
        });


        webView.addJavascriptInterface(new Object() {
            @JavascriptInterface
            public void showArticle(int id, String title, String image, String desc) {
                //跳转页面
                mIsReTry = false;
                Intent intent = new Intent(WebViewActivity.this, WebViewActivity.class);
                intent.putExtra(Constant.string.ARG_ONE, id);
                intent.putExtra(Constant.string.ARG_TWO, title);
                intent.putExtra(Constant.string.ARG_NINE, mLoadCount + 1);
                intent.putExtra(Constant.string.ARG_TEN, Constant.number.TWO);
                //0是最初
                if (1 < mLoadCount) {
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra(Constant.string.ARG_NINE, 0);
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
                intent.setAction(Intent.ACTION_VIEW);
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

            //加载失败
            @JavascriptInterface
            public void errClick() {
                LogUtil.i(mIsReTry + "   ---      刷新      ");
                if (!mIsReTry) {
                    mIsReTry = true;
                    webView.loadUrl(UrlConstant.ARTICLE_DOMAIN_NAME + "app/a/" + mId + ".html");
                } else
                    finish();
            }

        }, "Android");

    }

    class myWebClient extends WebViewClient {
        @TargetApi(Build.VERSION_CODES.M)
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            LogUtil.i("onReceivedError 111     " + error.getDescription() + "      " + error.getErrorCode());
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            LogUtil.i("onReceivedError      " + description + "      " + errorCode);
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            super.onReceivedHttpError(view, request, errorResponse);
            LogUtil.i(request.getRequestHeaders().toString() + "      onReceivedHttpError    " + errorResponse.toString());

        }

        /**
         * 防止加载网页时调起系统浏览器
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if (WebViewActivity.this == null || WebViewActivity.this.isFinishing()) {
                return;
            }
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            if (WebViewActivity.this == null || WebViewActivity.this.isFinishing()) {
                return;
            }
            if (View.VISIBLE == pgLoadRate.getVisibility()) pgLoadRate.setVisibility(View.GONE);
            super.onPageFinished(view, url);
            if (TextUtils.isEmpty(webUrl)) {
                if (NetUtil.isNetAvailable(WebViewActivity.this)) {
                    mTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (null == WebViewActivity.this || WebViewActivity.this.isFinishing()) {
                                        return;
                                    }
                                    tvEvaluateTitle.setVisibility(View.VISIBLE);
                                }
                            });
                            mCommentListPresenter.queryList(Constant.number.TWO, String.valueOf(mId), mCurrentPage);
                        }
                    }, 2000);
                }
            }
            view.loadUrl(Constant.string.LOAD_CONTET);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    final class JavaScriptLocalObj {
        @JavascriptInterface
        public void print(String html) {
            //如果已经保存了就不要在处理了
            if (TextUtils.isEmpty(html) || html.length() < Constant.number.HUNDRED_AND_ONE || !NetUtil.isNetAvailable(WebViewActivity.this) || !TextUtils.isEmpty(webUrl) || Constant.number.TWO == mCacheTag || App.mReaderManager.isBrowseArticleById(mId))
                return;
            saveWebPage(html);
            App.mReaderManager.addArticleBrowseDB(mId, mPic, mTitle, mDesc, mHints, mTime, Mytime.getStringToday());
        }
    }

    /**
     * 下载看过的文章
     * 最好判断一下只下载首界面的
     *
     * @param result
     */
    public void saveWebPage(@NonNull String result) {
        //css ,js 判断本地是否有,没有下载，并修改其路径     2.判断是否存在图片，没有下载,并替换图片的路径
        /**
         * 1.正则匹配所有的 "<link href="/css/api/style.css" rel="stylesheet" type="text/css" />" 公用一个css，判断本地是否存在，替换就行了
         * 2.正则匹配所有的 "<script type="text/javascript" src="/js/api/jquery.min.js"></script>"
         * 3.正则匹配所有的 "<img src="https://img.hackhome.com/img2018/1/4/11/305210855.jpg">"
         *
         */

        result = result.replace(UrlConstant.ARTICLE_CSS, String.valueOf(Constant.savePath.WEB_PAGE_CACHE_CSS + "/style.css"));
        Pattern pattern = Pattern.compile(".*src.*");
        Matcher matcher = pattern.matcher(result);
        while (matcher.find()) {
            String group = matcher.group();
            if (group.startsWith("<script")) {
                String js = group.substring(Constant.number.THIRTY_SIX, group.length() - Constant.number.ELEVEN);
                String[] split = js.split("/");
                result = result.replace(js, String.valueOf(Constant.savePath.WEB_PAGE_CACHE + File.separator + split[split.length - Constant.number.ONE]));
                File file = new File(Constant.savePath.WEB_PAGE_CACHE_JS, split[split.length - Constant.number.ONE]);
                if (file.exists()) continue;
                else
                    OkHttpDownloadUtil.downFile(UrlConstant.ARTICLE_DOMAIN_NAME + js, Constant.savePath.WEB_PAGE_CACHE_JS, split[split.length - Constant.number.ONE]);

            } else if (group.contains("img")) {
//                String img = group.substring(Constant.number.ELEVEN, group.length() - Constant.number.TWO);
//                String[] split = img.split("/");
//                String path = String.valueOf(Constant.savePath.WEB_PAGE_CACHE_PIC + File.separator + split[split.length - Constant.number.ONE]);
//                result = result.replace(img, path);
//                File file = new File(Constant.savePath.WEB_PAGE_CACHE_PIC, split[split.length - Constant.number.ONE]);
//                if (file.exists()) continue;
//                else
//                    ImageUtil.saveImg(this, img, path);
            }
        }
        File file = new File(Constant.savePath.WEB_PAGE_CACHE_CSS, "style.css");
        if (!file.exists())
            OkHttpDownloadUtil.downFile(UrlConstant.ARTICLE_DOMAIN_NAME + UrlConstant.ARTICLE_CSS, Constant.savePath.WEB_PAGE_CACHE_CSS, "style.css");
        File htmlFile = new File(Constant.savePath.WEB_PAGE_CACHE_HTML, String.valueOf(mId + ".html"));
        if (htmlFile.exists()) return;
        else saveHtml(result);
    }

    private void saveHtml(final String result) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    File parentFile = new File(Constant.savePath.WEB_PAGE_CACHE_HTML);
                    if (!parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                    File file = new File(Constant.savePath.WEB_PAGE_CACHE_HTML, String.valueOf(mId + ".html"));
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    readStream(result, new FileOutputStream(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }


    private static void readStream(String result, FileOutputStream outputStream) throws IOException {
        BufferedOutputStream out = new BufferedOutputStream(outputStream);
        out.write(result.getBytes(), Constant.number.ZERO, result.length());
        out.flush();
        out.close();
        outputStream.flush();
        outputStream.close();
    }


    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
        MobclickAgent.onPageStart("ArticleActivity");
        MobclickAgent.onResume(this);
        if (mIsNeedUserInfo && UserUtil.getUserState(this)) {
            String userIcon = SpUtil.getString(this, Constant.userInfo.USER_ICON, null);
            if (!TextUtils.isEmpty(userIcon) && null != ivUser) ivUser.setImageURI(userIcon);
            mIsNeedUserInfo = false;
        }
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
            if (null != mTimer) {
                mTimer.cancel();
                mTimer = null;
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_favorite:
                if (mIsFavorite) {
                    ivFavorite.setImageResource(R.drawable.ic_gray_favorite);
                    App.mReaderManager.deleteFavoriteArticleById(mId);
                    mIsFavorite = false;
                } else {
                    ivFavorite.setImageResource(R.drawable.ic_favorited);
                    App.mReaderManager.addArticleFavoriteDB(mId, mPic, mTitle, mDesc, mHints, mTime, Mytime.getStringToday());
                    mIsFavorite = true;
                }
                break;
            case R.id.iv_action_right_three:
                share();
                break;
            case R.id.ll_evaluate:
                if (UserUtil.getUserState(this))
                    CommentFragment.newInstance(this, new CommentBean(Constant.number.TWO, mId, Constant.number.ZERO, String.valueOf("评价：" + mTitle), mLastInput)).show(getFragmentManager(), CommentCallback.CALLBACK);
                else {
                    Intent intent = new Intent(this, TabWithPagerActivity.class);
                    intent.putExtra(Constant.string.ARG_ONE, Constant.number.EIGHT);
                    intent.putExtra(Constant.string.ARG_TWO, Constant.userInfo.NEED_USER_INFO);
                    this.startActivity(intent);
                    mIsNeedUserInfo = true;
                }
                break;
            case R.id.rl_comment:
                //展示评论列表
                slView.scrollTo(0, webView.getView().getHeight());
                break;
        }

    }

    private void share() {
        ShareUtil.localShare(this, mTitle, "手游攻略", UrlConstant.ARTICLE_DOMAIN_NAME + "app/a/" + mId + ".html");
    }

    @Override
    public void saveCommentText(String commentWords) {
        this.mLastInput = commentWords;
    }

    @Override
    public void successComment() {
        if (null == this || this.isFinishing() || null == mCommentListPresenter) {
            return;
        }
        mIsRefresh = true;
        mCurrentPage = Constant.number.ONE;
        mCommentListPresenter.queryList(Constant.number.TWO, String.valueOf(mId), mCurrentPage);
    }

    @Override
    public void failComment() {

    }

    @Override
    public void getResult(Object dataBen, int type) {
        if (null == WebViewActivity.this || null == dataBen || this.isFinishing()) {
            return;
        }
        switch (type) {
            case Constant.number.ZERO:
                SuccessBean successBean = (SuccessBean) dataBen;
                if (Constant.number.ZERO == successBean.getError()) {
                    EvaluateBean.ItemsBean itemsBean = mEvaluateAdapter.getItem(mUpVoteIndex);
                    itemsBean.setGood(String.valueOf(Integer.parseInt(itemsBean.getGood()) + 1));
                    itemsBean.setDig(true);
                    mEvaluateAdapter.notifyItemChanged(mUpVoteIndex);
                }
                break;
            //评论列表
            case Constant.number.TWO:
                EvaluateBean evaluateBean = (EvaluateBean) dataBen;
                if (evaluateBean.isSuccess()) {
                    mPageCount = evaluateBean.getPagecount();
                    ((TextView) rlComment.getChildAt(1)).setText(String.valueOf(evaluateBean.getDatacount()));
                    if (null != evaluateBean.getItems() && Constant.number.ZERO < evaluateBean.getItems().size()) {
                        if (View.GONE == rlView.getVisibility()) {
                            rlView.setVisibility(View.VISIBLE);
                        }
                        if (null != tvEmpty && View.VISIBLE == tvEmpty.getVisibility())
                            tvEmpty.setVisibility(View.GONE);
                        if (mIsRefresh)
                            mEvaluateAdapter.setNewData(evaluateBean.getItems());
                        else
                            mEvaluateAdapter.addData(evaluateBean.getItems());
                    } else {
                        if (null != tvEmpty)
                            tvEmpty.setVisibility(View.VISIBLE);
                        rlView.setVisibility(View.GONE);
                    }
                } else {
                    if (null != tvEmpty)
                        tvEmpty.setVisibility(View.VISIBLE);
                    rlView.setVisibility(View.GONE);
                }
                break;
        }
    }

    @Override
    public void failRequest() {
        if (!mIsRefresh && null != mEvaluateAdapter) {
            mEvaluateAdapter.loadMoreFail();
        }
    }

    @Override
    public void errorRequest() {
        if (!mIsRefresh && null != mEvaluateAdapter) {
            mEvaluateAdapter.loadMoreFail();
        }

    }

    @Override
    public void nice(int index) {
        mUpVoteIndex = index;
        new UpVotePresenter(this).queryList(Constant.number.TWO, String.valueOf(mEvaluateAdapter.getItem(index).getID()), Constant.number.ZERO);

    }

    @Override
    public void replay(int pid, String userName) {
        if (UserUtil.getUserState(this))
            CommentFragment.newInstance(this, new CommentBean(Constant.number.TWO, mId, pid, String.valueOf("回复：" + userName), mLastInput)).show(getFragmentManager(), CommentCallback.CALLBACK);
        else {
            Intent intent = new Intent(this, TabWithPagerActivity.class);
            intent.putExtra(Constant.string.ARG_ONE, Constant.number.EIGHT);
            this.startActivity(intent);
        }
    }


    private class MyLoadMore implements com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener {
        @Override
        public void onLoadMoreRequested() {
            if (mCurrentPage < mPageCount) {
                mIsRefresh = false;
                ++mCurrentPage;
                mCommentListPresenter.queryList(Constant.number.TWO, String.valueOf(mId), mCurrentPage);
            } else {
                mEvaluateAdapter.setEnableLoadMore(false);
            }
        }
    }


    private class myWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (WebViewActivity.this == null || WebViewActivity.this.isFinishing()) {
                return;
            }
            if (View.GONE == pgLoadRate.getVisibility()) pgLoadRate.setVisibility(View.VISIBLE);
            pgLoadRate.setProgress(newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (TextUtils.isEmpty(title) || TextUtils.equals("Err", title)) {
                App.mReaderManager.deleteBrowseArticleById(mId);
            }
//            if (TextUtils.isEmpty(title) || TextUtils.equals("Err",title)) {
            //加载网络走丢了的布局
//                view.loadUrl(netErrorHtml);
//            }
        }


    }
}
