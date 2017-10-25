package com.wangxia.battle.fragment.list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.wangxia.battle.R;
import com.wangxia.battle.activity.WebViewActivity;
import com.wangxia.battle.adapter.ArticleAdapter;
import com.wangxia.battle.adapter.FunctionTypeAdapter;
import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.fragment.BaseFragment;
import com.wangxia.battle.model.bean.ArticleList;
import com.wangxia.battle.presenter.impPresenter.ArticleListPresenter;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.MyToast;
import com.wangxia.battle.util.OnLoadMoreListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 昝奥博 on 2017/7/22 0022
 * Email:18772833900@163.com
 * Explain：文章列表 0：默认的所有的决战平安京文章
 */
public class ArticleListFragment extends BaseFragment implements ISuccessCallbackData, OnLoadMoreListener.ILoadMoreListener {
    @BindView(R.id.smart_refresh)
    SwipeRefreshLayout smartRefreshLayout;
    @BindView(R.id.rl_view)
    RecyclerView rl_article;
    private Context mContext;
    private List<ArticleList.ItemsBean> mData = new ArrayList<>();
    private ArticleAdapter mArticleAdapter;
    private ArticleListPresenter mArticleListPresenter;
    private int mCurrentPage = 1;
    private int mPageCount;
    private Unbinder mBind;
    private boolean mIsRefresh = false;
    private int mType;
    private String mLabel;
    private FunctionTypeAdapter mFunctionTypeAdapter;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        WeakReference<Context> weakReference = new WeakReference<>(context);
        mContext = weakReference.get();
    }

    public static ArticleListFragment newInstance(int type, String label) {
        Bundle args = new Bundle();
        args.putInt(Constant.string.ARG_ONE, type);
        args.putString(Constant.string.ARG_TWO, label);
        ArticleListFragment fragment = new ArticleListFragment();
        fragment.setArguments(args);
        return fragment;
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
        if(Constant.number.SEVEN != mType && Constant.number.EIGHT != mType){
            rl_article.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            mArticleAdapter = new ArticleAdapter(mContext, mData);
            rl_article.setAdapter(mArticleAdapter);
        }else {
            rl_article.setLayoutManager(new GridLayoutManager(mContext,Constant.number.FORE));
            mFunctionTypeAdapter = new FunctionTypeAdapter(mContext,mData);
//            rl_article.addItemDecoration(new RecycleItemDecortion.DividerGridItemDecoration(mContext));
            rl_article.setAdapter(mFunctionTypeAdapter);
        }
        return view;
    }

    @Override
    public void initData() {
        mArticleListPresenter = new ArticleListPresenter(this);
        mArticleListPresenter.queryList(mType, mLabel, mCurrentPage);
    }

    @Override
    public void initListener() {
        if (null != smartRefreshLayout) {
            smartRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    if(Constant.number.TEN > mData.size()){
                        smartRefreshLayout.setRefreshing(false);
                        return;
                    }
                    mCurrentPage = 1;
                    mIsRefresh = true;
                    mArticleListPresenter.queryList(mType, mLabel, mCurrentPage);
                }
            });
        }
        if (null != rl_article) {
            rl_article.addOnScrollListener(new OnLoadMoreListener(this));
        }
        if (null != mArticleAdapter) {
            mArticleAdapter.setiItemClick(new ArticleAdapter.IItemClick() {
                @Override
                public void toArticleDetail(int position) {
                    MobclickAgent.onEvent(mContext, Constant.uMengStatistic.FIND_ARTICLE_HINTS);
                    ArticleList.ItemsBean itemsBean = mData.get(position);
                    WebViewActivity.toWebViewActivity(mContext, itemsBean.getID(), itemsBean.getSubtitle(), itemsBean.getPiclist(), Integer.parseInt(itemsBean.getHits()), itemsBean.getTime(), "大麦子", "小蘑菇");
                }
            });
        }
        if(null != mFunctionTypeAdapter){
            mFunctionTypeAdapter.setItemClick(new FunctionTypeAdapter.IItemClickListener() {
                @Override
                public void itemClick(int position) {
                    MobclickAgent.onEvent(mContext, Constant.uMengStatistic.FIND_ARTICLE_HINTS);
                    ArticleList.ItemsBean itemsBean = mData.get(position);
                    WebViewActivity.toWebViewActivity(mContext, itemsBean.getID(), itemsBean.getSubtitle(), itemsBean.getPiclist(), Integer.parseInt(itemsBean.getHits()), itemsBean.getTime(), "大麦子", "小蘑菇");
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("ArticleListFragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("ArticleListFragment");
    }

    @Override
    public void recycleMemory() {
        if(null != mBind){
            mBind.unbind();
        }
    }

    @Override
    public void getResult(Object dataBen, int type) {
        if (mIsRefresh) {
            smartRefreshLayout.setRefreshing(false);
        }
        if (null != dataBen) {
            ArticleList articleList = (ArticleList) dataBen;
            if (null != articleList && Constant.number.ZERO < articleList.getSize()) {
                if (mIsRefresh && null != mData) {
                    mData.clear();
                    mIsRefresh = false;
                }
                mPageCount = articleList.getPagecount();
                mCurrentPage = articleList.getCurpage();
                mData.addAll(articleList.getItems());
                if(Constant.number.SEVEN != mType && Constant.number.EIGHT != mType)
                    mArticleAdapter.notifyDataSetChanged();
                else mFunctionTypeAdapter.notifyDataSetChanged();

            }

        }
    }

    @Override
    public void loadMoreData() {
        if(Constant.number.TEN > mData.size()){
            return;
        }
        if (mCurrentPage < mPageCount) {
            ++mCurrentPage;
            mArticleListPresenter.queryList(mType, mLabel, mCurrentPage);
        } else {
            MyToast.showToast(mContext, "没有更多的数据啦 !", Toast.LENGTH_SHORT);
        }
    }
}
