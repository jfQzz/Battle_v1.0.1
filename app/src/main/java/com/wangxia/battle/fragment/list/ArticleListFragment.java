package com.wangxia.battle.fragment.list;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.umeng.analytics.MobclickAgent;
import com.wangxia.battle.R;
import com.wangxia.battle.activity.WebViewActivity;
import com.wangxia.battle.adapter.ArticleAdapter;
import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.callback.NetworkListener;
import com.wangxia.battle.fragment.base.LazyBaseFragment;
import com.wangxia.battle.model.bean.ArticleList;
import com.wangxia.battle.presenter.impPresenter.ArticleListPresenter;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.NetUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 昝奥博 on 2017/7/22 0022
 * Email:18772833900@163.com
 * Explain：文章列表 0：默认的所有的决战平安京文章
 */
public class ArticleListFragment extends LazyBaseFragment implements ISuccessCallbackData, NetworkListener {
    @BindView(R.id.smart_refresh)
    SwipeRefreshLayout smartRefreshLayout;
    @BindView(R.id.rl_view)
    RecyclerView rl_article;
    private ArticleAdapter mArticleAdapter;
    private ArticleListPresenter mArticleListPresenter;
    private int mCurrentPage = 1;
    private int mPageCount;
    private Unbinder mBind;
    private boolean mIsRefresh = true;
    private int mType;
    private String mLabel;


    public static ArticleListFragment newInstance(int type, String label) {
        Bundle args = new Bundle();
        args.putInt(Constant.string.ARG_ONE, type);
        args.putString(Constant.string.ARG_TWO, label);
        ArticleListFragment fragment = new ArticleListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) NetUtil.setNetListener(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        mType = arguments.getInt(Constant.string.ARG_ONE, Constant.number.ZERO);
        mLabel = arguments.getString(Constant.string.ARG_TWO);
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_article_list, null);
        mBind = ButterKnife.bind(this, view);
        smartRefreshLayout.setColorSchemeResources(R.color.colorAccent,R.color.colorYellow,R.color.colorRad);
        smartRefreshLayout.setRefreshing(true);
        rl_article.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mArticleAdapter = new ArticleAdapter();
        mArticleAdapter.disableLoadMoreIfNotFullPage(rl_article);
        mArticleAdapter.setEnableLoadMore(false);
        rl_article.setAdapter(mArticleAdapter);
        return view;
    }

    @Override
    public void initData() {
        mArticleListPresenter = new ArticleListPresenter(this);
        mArticleListPresenter.queryList(mType, mLabel, mCurrentPage);
    }

    @Override
    public void initListener() {
        smartRefreshLayout.setOnRefreshListener(new MyRefresh());
        mArticleAdapter.setOnLoadMoreListener(new MyLoadMore(), rl_article);
        mArticleAdapter.setiItemClick(new ArticleAdapter.IItemClick() {
            @Override
            public void toArticleDetail(int position) {
                MobclickAgent.onEvent(mContext, Constant.uMengStatistic.FIND_ARTICLE_HINTS);
                ArticleList.ItemsBean itemsBean = mArticleAdapter.getItem(position);
                WebViewActivity.toWebViewActivity(mContext, itemsBean.getID(), itemsBean.getSubtitle(), itemsBean.getDesc(), itemsBean.getPiclist(), Integer.parseInt(itemsBean.getHits()), itemsBean.getTime());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("ArticleListFragment");
        reLoad();
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("ArticleListFragment");
    }

    @Override
    public void recycleMemory() {
        if (null != mBind) {
            mBind.unbind();
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void getResult(Object dataBen, int type) {
        if (null == mContext || ((AppCompatActivity) mContext).isDestroyed()) {
            return;
        }
        if (null != smartRefreshLayout && smartRefreshLayout.isRefreshing()) {
            smartRefreshLayout.setRefreshing(false);
        }
        if (null != dataBen) {
            ArticleList articleList = (ArticleList) dataBen;
            if (null != articleList && Constant.number.ZERO < articleList.getSize()) {
                mPageCount = articleList.getPagecount();
                mCurrentPage = articleList.getCurpage();
                if (mIsRefresh) {
                    mArticleAdapter.setNewData(articleList.getItems());
                    mArticleAdapter.setEnableLoadMore(true);
                } else {
                    mArticleAdapter.addData(articleList.getItems());
                    mArticleAdapter.loadMoreComplete();
                }
            }

        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void failRequest() {
        if (null == mContext || ((AppCompatActivity) mContext).isDestroyed()) {
            return;
        }
        if (smartRefreshLayout.isRefreshing())
            smartRefreshLayout.setRefreshing(false);
        mArticleAdapter.setEnableLoadMore(true);
        if (null != mArticleAdapter && mArticleAdapter.isLoading()) mArticleAdapter.loadMoreFail();

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void errorRequest() {
        if (null == mContext || ((AppCompatActivity) mContext).isDestroyed()) {
            return;
        }
        if (mIsRefresh) {
            smartRefreshLayout.setRefreshing(false);
            mArticleAdapter.setEnableLoadMore(true);
        } else
            mArticleAdapter.loadMoreFail();
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
        if (null != smartRefreshLayout && smartRefreshLayout.isRefreshing())
            smartRefreshLayout.setRefreshing(false);
        mArticleAdapter.setEnableLoadMore(true);
        if (null != mArticleAdapter && mArticleAdapter.isLoading()) mArticleAdapter.loadMoreFail();

    }

    private void reLoad() {
        if (null != mArticleAdapter && mArticleAdapter.getData().isEmpty() && null != mArticleListPresenter) {
            mIsRefresh = true;
            mArticleListPresenter.queryList(mType, mLabel, mCurrentPage);
        }
    }

    private class MyRefresh implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            rl_article.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mCurrentPage = 1;
                    mIsRefresh = true;
                    mArticleAdapter.setEnableLoadMore(false);
                    mArticleListPresenter.queryList(mType, mLabel, mCurrentPage);
                }
            }, 2000);

        }
    }

    private class MyLoadMore implements com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener {
        @Override
        public void onLoadMoreRequested() {
            if (mCurrentPage < mPageCount) {
                mIsRefresh = false;
                ++mCurrentPage;
                mArticleListPresenter.queryList(mType, mLabel, mCurrentPage);
            } else {
                mArticleAdapter.loadMoreEnd();
            }
        }
    }
}
