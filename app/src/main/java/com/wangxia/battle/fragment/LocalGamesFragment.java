package com.wangxia.battle.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.wangxia.battle.R;
import com.wangxia.battle.activity.VideoActivity;
import com.wangxia.battle.activity.WebViewActivity;
import com.wangxia.battle.adapter.LocalAppAdapter;
import com.wangxia.battle.adapter.UserArticleAdapter;
import com.wangxia.battle.adapter.UserVideoAdapter;
import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.db.bean.ArticleBean;
import com.wangxia.battle.db.bean.VideoBean;
import com.wangxia.battle.fragment.base.LazyBaseFragment;
import com.wangxia.battle.model.bean.LocalAppBean;
import com.wangxia.battle.presenter.impPresenter.LocalAppListPresenter;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.MyToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 昝奥博 on 2017/7/22 0022
 * Email:18772833900@163.com
 * Explain：0:本地游戏 1：游戏下载历史 2：视频浏览历史 3：文章浏览历史 4：视频收藏 5：文章收藏
 */
public class LocalGamesFragment extends LazyBaseFragment implements ISuccessCallbackData {
    @BindView(R.id.smart_refresh)
    SwipeRefreshLayout smartRefreshLayout;
    @BindView(R.id.rl_view)
    RecyclerView rlLocalGames;
    @BindView(R.id.tv_empty_view)
    TextView tvEmptyView;
    private Unbinder mBind;
    private List<LocalAppBean> mLocalAppData = new ArrayList<>();
    private List<ArticleBean> mLocalArticleData = new ArrayList<>();
    private List<VideoBean> mLocalVideoData = new ArrayList<>();
    private LocalAppAdapter mLocalAppAdapter;
    private int mType;
    private LocalAppListPresenter mLocalAppListPresenter;
    private UserVideoAdapter mUserVideoAdapter;
    private UserArticleAdapter mUserArticleAdapter;


    public static LocalGamesFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt(Constant.string.ARG_ONE, type);
        LocalGamesFragment fragment = new LocalGamesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (null != getArguments()) {
            mType = arguments.getInt(Constant.string.ARG_ONE);
        }
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_article_list, null);
        mBind = ButterKnife.bind(this, view);
        smartRefreshLayout.setEnabled(false);
        smartRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorYellow, R.color.colorRad);
        smartRefreshLayout.setRefreshing(true);
        rlLocalGames.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        switch (mType) {
            case Constant.number.ZERO:
                mLocalAppAdapter = new LocalAppAdapter(mContext, mLocalAppData);
                rlLocalGames.setAdapter(mLocalAppAdapter);
                break;
            case Constant.number.ONE:

                break;
            case Constant.number.TWO:
                mUserVideoAdapter = new UserVideoAdapter(mContext, mLocalVideoData);
                rlLocalGames.setAdapter(mUserVideoAdapter);
                break;
            case Constant.number.THREE:
                mUserArticleAdapter = new UserArticleAdapter(mContext, mLocalArticleData);
                rlLocalGames.setAdapter(mUserArticleAdapter);
                break;
            case Constant.number.FORE:
                mUserVideoAdapter = new UserVideoAdapter(mContext, mLocalVideoData);
                rlLocalGames.setAdapter(mUserVideoAdapter);
                break;
            case Constant.number.FIVE:
                mUserArticleAdapter = new UserArticleAdapter(mContext, mLocalArticleData);
                rlLocalGames.setAdapter(mUserArticleAdapter);
                break;
        }
        return view;
    }

    @Override
    public void initData() {
        smartRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLocalAppListPresenter = new LocalAppListPresenter(LocalGamesFragment.this);
                mLocalAppListPresenter.queryList(mType, null, Constant.number.ZERO);
            }
        },1000);
    }

    @Override
    public void initListener() {
        if (Constant.number.ZERO == mType) {
            mLocalAppAdapter.setItemClick(new LocalAppAdapter.IItemClick() {
                @Override
                public void openApp(String packageName) {
                    try {
                        Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(packageName);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                        MyToast.showToast(mContext, getResources().getString(R.string.launcher_fail), Toast.LENGTH_SHORT);
                    }
                }
            });
        } else if (Constant.number.ONE == mType) {

        } else if (Constant.number.TWO == mType || Constant.number.FORE == mType) {
            mUserVideoAdapter.setiItemClick(new UserVideoAdapter.IItemClick() {
                @Override
                public void playVideo(View v, int id, String title, String pic) {
                    Intent intent = new Intent(mContext, VideoActivity.class);
                    intent.putExtra(Constant.string.ARG_ONE,id);
                    intent.putExtra(Constant.string.ARG_TWO,title);
                    intent.putExtra(Constant.string.ARG_THREE,pic);
                    mContext.startActivity(intent);
                }
            });

        } else {
            mUserArticleAdapter.setiItemClick(new UserArticleAdapter.IItemClick() {
                @Override
                public void toArticleDetail(int position) {
                    ArticleBean articleBean = mLocalArticleData.get(position);
                    WebViewActivity.toWebViewActivity(mContext, articleBean.getId(), articleBean.getTitle(), articleBean.getDesc(),articleBean.getIcon(), articleBean.getHints(), articleBean.getTime());
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("LocalDateBaseFragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("LocalDateBaseFragment");
    }

    @Override
    public void recycleMemory() {
        mBind.unbind();
        if (null != mLocalAppAdapter) {
            mLocalAppAdapter.clearMemory();
        }
        if (null != mLocalAppData) {
            mLocalAppData.clear();
        }
        smartRefreshLayout = null;
        rlLocalGames = null;

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void getResult(Object dataBen, int type) {
        if (null == mContext || ((AppCompatActivity) mContext).isDestroyed()) {
            return;
        }
        if(smartRefreshLayout.isRefreshing()){
            smartRefreshLayout.setRefreshing(false);
        }
        if (null == dataBen || Constant.number.ZERO == ((List<Object>) dataBen).size()) {
            if (null != tvEmptyView) {
                tvEmptyView.setText("暂无记录~");
                tvEmptyView.setVisibility(View.VISIBLE);
            }
            if (null != rlLocalGames) {
                rlLocalGames.setVisibility(View.GONE);
            }
            return;
        } else {
            if (null != tvEmptyView) {
                tvEmptyView.setVisibility(View.GONE);
            }
            if (null != rlLocalGames) {
                rlLocalGames.setVisibility(View.VISIBLE);
            }
        }
        switch (type) {
            case Constant.number.ZERO:
                List<LocalAppBean> localAppBeen = (List<LocalAppBean>) dataBen;
                if (null != localAppBeen && 0 < localAppBeen.size()) {
                    mLocalAppData.addAll(localAppBeen);
                    mLocalAppAdapter.notifyDataSetChanged();
                }
                break;
            case Constant.number.ONE:

                break;
            case Constant.number.TWO:
                List<VideoBean> videoBrowseBeen = (List<VideoBean>) dataBen;
                if (null != videoBrowseBeen && Constant.number.ZERO < videoBrowseBeen.size()) {
                    mLocalVideoData.addAll(videoBrowseBeen);
                    mUserVideoAdapter.notifyDataSetChanged();
                }
                break;
            case Constant.number.THREE:
                List<ArticleBean> articleBrowseBeen = (List<ArticleBean>) dataBen;
                if (null != articleBrowseBeen && Constant.number.ZERO < articleBrowseBeen.size()) {
                    mLocalArticleData.addAll(articleBrowseBeen);
                    mUserArticleAdapter.notifyDataSetChanged();
                }
                break;
            case Constant.number.FORE:
                List<VideoBean> videoFavoriteBeen = (List<VideoBean>) dataBen;
                if (null != videoFavoriteBeen && Constant.number.ZERO < videoFavoriteBeen.size()) {
                    mLocalVideoData.addAll(videoFavoriteBeen);
                    mUserVideoAdapter.notifyDataSetChanged();
                }
                break;
            case Constant.number.FIVE:
                List<ArticleBean> articleFavoriteBeen = (List<ArticleBean>) dataBen;
                if (null != articleFavoriteBeen && Constant.number.ZERO < articleFavoriteBeen.size()) {
                    mLocalArticleData.addAll(articleFavoriteBeen);
                    mUserArticleAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    public void failRequest() {
        if(null == mContext || ((AppCompatActivity)mContext).isFinishing()){
            return;
        }
    }

    @Override
    public void errorRequest() {
        if(null == mContext || ((AppCompatActivity)mContext).isFinishing()){
            return;
        }
    }
}
