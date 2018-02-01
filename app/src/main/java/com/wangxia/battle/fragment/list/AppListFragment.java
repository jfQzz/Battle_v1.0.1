package com.wangxia.battle.fragment.list;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.umeng.analytics.MobclickAgent;
import com.wangxia.battle.R;
import com.wangxia.battle.activity.WebViewActivity;
import com.wangxia.battle.adapter.ArticleAdapter;
import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.fragment.base.LazyBaseFragment;
import com.wangxia.battle.model.bean.ArticleList;
import com.wangxia.battle.presenter.impPresenter.ArticleListPresenter;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.LogUtil;
import com.wangxia.battle.util.MyToast;
import com.wangxia.battle.util.NetUtil;
import com.wangxia.battle.util.TxtFormatUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 昝奥博 on 2017/7/22 0022
 * Email:18772833900@163.com
 * Explain：
 */
public class AppListFragment extends LazyBaseFragment implements ISuccessCallbackData<ArticleList> {

    @BindView(R.id.rl_view)
    RecyclerView rlView;
    @BindView(R.id.refresh_Layout)
    SwipeRefreshLayout smartRefreshLayout;
    private int mCurrentPage = 1;
    private boolean mIsRefresh = false;
    private Unbinder mBind;
    private int mType;
    private String mLabel;
    private ArticleListPresenter mArticleListPresenter;
    private ArticleAdapter mArticleAdapter;
    private int mPageCount;


    /**
     * @param type 0:文章分类   1:
     * @return
     */
    public static AppListFragment newInstance(int type, String label) {
        Bundle args = new Bundle();
        args.putInt(Constant.string.ARG_ONE, type);
        args.putString(Constant.string.ARG_TWO, label);
        AppListFragment fragment = new AppListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("AppListFragment");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (null != arguments) {
            mType = arguments.getInt(Constant.string.ARG_ONE);
            mLabel = arguments.getString(Constant.string.ARG_TWO);
        }
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_app_list, null);
        mBind = ButterKnife.bind(this, view);
        smartRefreshLayout.setColorSchemeResources(R.color.colorAccent,R.color.colorYellow,R.color.colorRad);
        smartRefreshLayout.setRefreshing(true);
        switch (mType) {
            case Constant.number.ZERO:
                rlView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                mArticleAdapter = new ArticleAdapter();
                rlView.setAdapter(mArticleAdapter);
                break;
        }
        return view;
    }

    @Override
    public void initData() {
        switch (mType) {
            case Constant.number.ZERO:
                mArticleListPresenter = new ArticleListPresenter(this);
                mLabel = TxtFormatUtil.myescape(mLabel);
                mIsRefresh = true;
                mArticleListPresenter.queryList(Constant.number.TWO, mLabel, mCurrentPage);
                break;
        }
    }


    @Override
    public void initListener() {
        smartRefreshLayout.setOnRefreshListener(new MyRefresh());
        mArticleAdapter.setOnLoadMoreListener(new MyLoadMore(), rlView);
        switch (mType) {
            case Constant.number.ZERO:
                mArticleAdapter.setiItemClick(new ArticleAdapter.IItemClick() {
                    @Override
                    public void toArticleDetail(int position) {
                        ArticleList.ItemsBean itemsBean = mArticleAdapter.getItem(position);
                        WebViewActivity.toWebViewActivity(mContext, itemsBean.getID(), itemsBean.getTitle(), itemsBean.getDesc(), itemsBean.getPic(), Integer.parseInt(TextUtils.isEmpty(itemsBean.getHits()) ? String.valueOf(Constant.number.ZERO) : itemsBean.getHits()), itemsBean.getTime());
                    }
                });
                break;
        }

    }


    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("AppListFragment");
    }


    /**
     * @param appInfo
     * @param type    100: banner
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void getResult(ArticleList appInfo, int type) {
        if (null == mContext || ((AppCompatActivity) mContext).isDestroyed()) {
            return;
        }
        if (null != smartRefreshLayout&&smartRefreshLayout.isRefreshing()) {
            smartRefreshLayout.setRefreshing(false);
        }
        switch (type) {
            case Constant.number.TWO:
                ArticleList articleList = appInfo;
                if (null != articleList) {
                    List<ArticleList.ItemsBean> items = articleList.getItems();
                    mCurrentPage = articleList.getCurpage();
                    mPageCount = articleList.getPagecount();
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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void failRequest() {
        if(null != mContext && !((AppCompatActivity) mContext).isDestroyed()){
            if(smartRefreshLayout.isRefreshing()){
                smartRefreshLayout.setRefreshing(false);
            }
            if(mIsRefresh){
                mArticleAdapter.setEnableLoadMore(true);
            }else {
                mArticleAdapter.loadMoreFail();
            }

        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void errorRequest() {
        if(null != mContext && !((AppCompatActivity) mContext).isDestroyed()) {
            if (smartRefreshLayout.isRefreshing()) {
                smartRefreshLayout.setRefreshing(false);
            }
            if (mIsRefresh) {
                mArticleAdapter.setEnableLoadMore(true);
            } else {
                mArticleAdapter.loadMoreFail();
            }
                if (NetUtil.isNetAvailable(mContext))
                    MyToast.s(mContext, getString(R.string.client_busy));
                 else
                    MyToast.s(mContext, getString(R.string.no_net));

        }
    }


    @Override
    public void recycleMemory() {
        mBind.unbind();
        mArticleAdapter = null;
    }


    private class MyRefresh implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            rlView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mCurrentPage = 1;
                    mIsRefresh = true;
                    mArticleAdapter.setEnableLoadMore(false);
                    mArticleListPresenter.queryList(Constant.number.TWO, mLabel, Constant.number.ONE);
                }
            }, 2000);
        }
    }

    private class MyLoadMore implements com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener {
        @Override
        public void onLoadMoreRequested() {
            LogUtil.i(mCurrentPage+"            "+mPageCount);
                    if (mCurrentPage < mPageCount) {
                        mIsRefresh = false;
                        ++mCurrentPage;
                        mArticleListPresenter.queryList(Constant.number.TWO, mLabel, mCurrentPage);
                    } else {
                        LogUtil.i("显示没有更多的数据啊~~~");
                        mArticleAdapter.loadMoreEnd();
                    }

        }
    }
}
