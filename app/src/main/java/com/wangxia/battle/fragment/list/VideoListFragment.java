package com.wangxia.battle.fragment.list;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.umeng.analytics.MobclickAgent;
import com.wangxia.battle.R;
import com.wangxia.battle.activity.BaseActivity;
import com.wangxia.battle.activity.VideoActivity;
import com.wangxia.battle.adapter.VideoAdapter;
import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.fragment.base.LazyBaseFragment;
import com.wangxia.battle.model.bean.VideoList;
import com.wangxia.battle.presenter.impPresenter.VideoListPresenter;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.MyToast;
import com.wangxia.battle.util.NetUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 昝奥博 on 2017/7/22 0022
 * Email:18772833900@163.com
 * Explain：
 */
public class VideoListFragment extends LazyBaseFragment implements ISuccessCallbackData {

    @BindView(R.id.smart_refresh)
    SwipeRefreshLayout smartRefreshLayout;
    @BindView(R.id.rl_view)
    RecyclerView rl_view;
    private VideoListPresenter mVideoListPresenter;
    private int mPageNo =1;
    private Unbinder mBind;
    private int mPageCount;
    private VideoAdapter mVideoAdapter;
    private boolean mIsRefreshData = false;



    public static VideoListFragment newInstance() {
        Bundle args = new Bundle();
        VideoListFragment fragment = new VideoListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_video_list, null);
        mBind = ButterKnife.bind(this,view);
        smartRefreshLayout.setColorSchemeResources(R.color.colorAccent,R.color.colorYellow,R.color.colorRad);
        smartRefreshLayout.setRefreshing(true);
        rl_view.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL,false));
        mVideoAdapter = new VideoAdapter();
        rl_view.setAdapter(mVideoAdapter);
        return view;
    }

    @Override
    public void initData() {
        mIsRefreshData = true;
        mVideoListPresenter = new VideoListPresenter(this);
        mVideoListPresenter.queryList(Constant.number.ZERO,null,mPageNo);

    }

    @Override
    public void initListener() {
        smartRefreshLayout.setOnRefreshListener(new MyRefresh());
        mVideoAdapter.setOnLoadMoreListener(new MyLoadMore(),rl_view);
        mVideoAdapter.setiItemClick(new VideoAdapter.IItemClick() {
            @Override
            public void playVideo(View v,int id, String title, String url) {
                Intent intent = new Intent(mContext, VideoActivity.class);
                intent.putExtra(Constant.string.ARG_ONE,id);
                intent.putExtra(Constant.string.ARG_TWO,title);
                intent.putExtra(Constant.string.ARG_THREE,url);
                mContext.startActivity(intent);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation((BaseActivity)mContext,v,"img");
//                    ActivityCompat.startActivity(mContext, intent, activityOptions.toBundle());
//                }else {
//                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("VideoListFragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageStart("VideoListFragment");
    }

    @Override
    public void recycleMemory() {
        mBind.unbind();
        smartRefreshLayout = null;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void getResult(Object dataBen,int type) {
        if(null == mContext || ((AppCompatActivity)mContext).isDestroyed()){
            return;
        }
        if( null != smartRefreshLayout && smartRefreshLayout.isRefreshing()){
            smartRefreshLayout.setRefreshing(false);
        }
        if(null != dataBen){
            if(dataBen instanceof VideoList){
                VideoList videoList = (VideoList) dataBen;
                mPageCount = videoList.getPagecount();
                if(mIsRefreshData){
                    mVideoAdapter.setNewData(videoList.getItems());
                    mVideoAdapter.setEnableLoadMore(true);
                }else {
                    mVideoAdapter.addData(videoList.getItems());
                    mVideoAdapter.loadMoreComplete();
                }
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void failRequest() {
        if (null == mContext || ((BaseActivity) mContext).isDestroyed()) {
            return;
        }
        if (smartRefreshLayout.isRefreshing()) {
            smartRefreshLayout.setRefreshing(false);
        }
        if(mIsRefreshData){
            mVideoAdapter.setEnableLoadMore(true);
        }else {
            MyToast.s(mContext,getString(R.string.load_more_error));
            mVideoAdapter.loadMoreFail();
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void errorRequest() {
        if (null == mContext || ((BaseActivity) mContext).isDestroyed()) {
            return;
        }
        if (smartRefreshLayout.isRefreshing()) {
            smartRefreshLayout.setRefreshing(false);
        }
        if(mIsRefreshData){
            mVideoAdapter.setEnableLoadMore(true);
        }else {
            mVideoAdapter.loadMoreFail();
        }
            if (NetUtil.isNetAvailable(mContext))
                MyToast.s(mContext, getString(R.string.client_busy));
            else
                MyToast.s(mContext, getString(R.string.no_net));

    }


    private class MyRefresh implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            rl_view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mPageNo = 1;
                    mIsRefreshData = true;
                    mVideoListPresenter.queryList(Constant.number.ZERO,null,mPageNo);
                }
            },2000);
        }
    }

    private class MyLoadMore implements com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener {
        @Override
        public void onLoadMoreRequested() {
            if(null == mContext){
                return;
            }
            if(mPageNo < mPageCount){
                ++mPageNo;
                mIsRefreshData = false;
                mVideoListPresenter.queryList(Constant.number.ZERO,null,mPageNo);
            }else {
                mVideoAdapter.loadMoreEnd();
            }

        }
    }
}
