package com.wangxia.battle.fragment.list;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.wangxia.battle.R;
import com.wangxia.battle.activity.WebViewActivity;
import com.wangxia.battle.adapter.ArticleAdapter;
import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.fragment.BaseFragment;
import com.wangxia.battle.model.bean.ArticleList;
import com.wangxia.battle.presenter.impPresenter.ArticleListPresenter;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.MyToast;
import com.wangxia.battle.util.OnLoadMoreListener;
import com.wangxia.battle.util.TxtFormatUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 昝奥博 on 2017/7/22 0022
 * Email:18772833900@163.com
 * Explain：
 */
public class AppListFragment extends BaseFragment implements ISuccessCallbackData, OnLoadMoreListener.ILoadMoreListener {

    @BindView(R.id.list_view)
    RecyclerView lv_home;
    @BindView(R.id.loading)
    FrameLayout loading_view;
    @BindView(R.id.refresh_Layout)
    SwipeRefreshLayout smartRefreshLayout;
    private Context mContext;
    private ArrayList<ArticleList.ItemsBean> mData = new ArrayList<>();
    private int mCurrentPage = 1;
    private boolean mIsRefresh = false;
    private Unbinder mBind;
    private int mType;
    private String mLabel;
    private ArticleListPresenter mArticleListPresenter;
    private ArticleAdapter mArticleAdapter;
    private int mPageCount;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        WeakReference<Context> weakReference = new WeakReference<>(context);
        mContext = weakReference.get();
    }


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
        switch (mType){
            case Constant.number.ZERO:
                lv_home.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                mArticleAdapter = new ArticleAdapter(mContext,mData);
                mArticleAdapter.setType(Constant.number.ONE);
                Resources resources = getResources();
                if(mLabel.equals(resources.getString(R.string.hero_list)) || mLabel.equals(resources.getString(R.string.arm_list))){
                    mArticleAdapter.setPicWidth(60);
                }
                lv_home.setAdapter(mArticleAdapter);
                break;
        }
        return view;
    }

    @Override
    public void initData() {
        switch (mType){
            case Constant.number.ZERO:
                    mArticleListPresenter = new ArticleListPresenter(this);
                    mLabel = TxtFormatUtil.myescape(mLabel);
                    mArticleListPresenter.queryList(Constant.number.TWO, mLabel, mCurrentPage);
                break;
        }
    }


    @Override
    public void initListener() {
        smartRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCurrentPage = 1;
                mIsRefresh = true;
                switch (mType){
                    case Constant.number.ZERO:
                        mArticleListPresenter.queryList(Constant.number.TWO,mLabel, Constant.number.ONE);
                        break;
                }

            }
        });
        lv_home.addOnScrollListener(new OnLoadMoreListener(this));
        switch (mType){
            case Constant.number.ZERO:
                mArticleAdapter.setiItemClick(new ArticleAdapter.IItemClick() {
                    @Override
                    public void toArticleDetail(int position) {
                        ArticleList.ItemsBean itemsBean = mData.get(position);
                        WebViewActivity.toWebViewActivity(mContext, itemsBean.getID(), itemsBean.getTitle(), itemsBean.getPic(), Integer.parseInt(TextUtils.isEmpty(itemsBean.getHits()) ? String.valueOf(Constant.number.ZERO) : itemsBean.getHits()), itemsBean.getTime(), null, null);
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
    @Override
    public void getResult(Object appInfo, int type) {
        if (mIsRefresh) {
            smartRefreshLayout.setRefreshing(false);
        }
        loading_view.setVisibility(View.GONE);
        switch (type) {

            case Constant.number.TWO:
                ArticleList articleList = (ArticleList) appInfo;
                if(null != articleList){
                    List<ArticleList.ItemsBean> items = articleList.getItems();
                    mCurrentPage = articleList.getCurpage();
                    mPageCount = articleList.getPagecount();
                    if(null != items && Constant.number.ZERO < items.size()){
                        if(mIsRefresh && null != mData){
                            mData.clear();
                            mIsRefresh = false;
                        }
                        mData.addAll(items);
                        if(null != mArticleAdapter){
                            mArticleAdapter.notifyDataSetChanged();
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void loadMoreData() {
        if (mCurrentPage < mPageCount) {
            ++mCurrentPage;
            switch (mType){
                case Constant.number.ZERO:
                    mArticleListPresenter.queryList(Constant.number.TWO,mLabel, mCurrentPage);
                    break;
            }
        } else {
            MyToast.showToast(mContext, getResources().getString(R.string.no_more_data), Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void recycleMemory() {
        mBind.unbind();
        if (null != mData) {
            mData.clear();
        }
        mArticleAdapter = null;


    }

}
