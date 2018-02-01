package com.wangxia.battle.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.umeng.analytics.MobclickAgent;
import com.wangxia.battle.R;
import com.wangxia.battle.adapter.VideoAdapter;
import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.model.bean.VideoList;
import com.wangxia.battle.presenter.impPresenter.VideoListPresenter;
import com.wangxia.battle.util.Constant;

import butterknife.BindView;

/**
 * Created by 昝奥博 on 2017/12/26 0026
 * Email:18772833900@163.com
 * Explain：视频列表
 */
public class VideoListActivity extends BaseActivity implements ISuccessCallbackData{
    @BindView(R.id.smart_refresh)
    SwipeRefreshLayout smartRefreshLayout;
    @BindView(R.id.rl_view)
    RecyclerView rl_view;
    @BindView(R.id.loading)
    FrameLayout loading;
    private VideoListPresenter mVideoListPresenter;
    private int mPageNo =1;
    private int mPageCount;
    private VideoAdapter mVideoAdapter;
    private boolean mIsRefresh = false;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video_list;
    }

    @Override
    protected void initView() {
        rl_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        mVideoAdapter = new VideoAdapter();
        rl_view.setAdapter(mVideoAdapter);
    }

    @Override
    protected void initListener() {
        smartRefreshLayout.setOnRefreshListener(new MyRefresh());
        mVideoAdapter.setOnLoadMoreListener(new MyLoadMore(),rl_view);

        mVideoAdapter.setiItemClick(new VideoAdapter.IItemClick() {
            @Override
            public void playVideo(View v, int id, String title, String url) {
                Intent intent = new Intent(VideoListActivity.this, VideoActivity.class);
                intent.putExtra(Constant.string.ARG_ONE,id);
                intent.putExtra(Constant.string.ARG_TWO,title);
                intent.putExtra(Constant.string.ARG_THREE,url);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(VideoListActivity.this,v,"img");
                    ActivityCompat.startActivity(VideoListActivity.this, intent, activityOptions.toBundle());
                }else {
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void initData() {
        mVideoListPresenter = new VideoListPresenter(this);
        mVideoListPresenter.queryList(Constant.number.ZERO,null,mPageNo);
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
    protected void clearMemory() {
        smartRefreshLayout = null;
    }
    
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void getResult(Object dataBen,int type) {
        if(null == VideoListActivity.this ||VideoListActivity.this.isDestroyed()){
            return;
        }
        if(mIsRefresh && null != smartRefreshLayout){
            smartRefreshLayout.setRefreshing(false);
        }
            loading.setVisibility(View.GONE);
        if(null != dataBen){
            if(dataBen instanceof VideoList){
                VideoList videoList = (VideoList) dataBen;
                mPageCount = videoList.getPagecount();
                if(mIsRefresh){
                    mVideoAdapter.setNewData(videoList.getItems());
                    mVideoAdapter.setEnableLoadMore(true);
                }else {
                    mVideoAdapter.addData(videoList.getItems());
                    mVideoAdapter.loadMoreComplete();
                }
            }
        }
    }

    @Override
    public void failRequest() {
        if(mIsRefresh)
            mVideoAdapter.setEnableLoadMore(true);
        else
            mVideoAdapter.loadMoreFail();
    }

    @Override
    public void errorRequest() {
        if(mIsRefresh)
            mVideoAdapter.setEnableLoadMore(true);
        else
            mVideoAdapter.loadMoreFail();

    }


    private class MyRefresh implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            rl_view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mPageNo = 1;
                    mIsRefresh = true;
                    mVideoAdapter.setEnableLoadMore(false);
                    mVideoListPresenter.queryList(Constant.number.ZERO,null,mPageNo);
                }
            },2000);

        }
    }

    private class MyLoadMore implements com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener {
        @Override
        public void onLoadMoreRequested() {
            if(null == this || VideoListActivity.this.isFinishing()){
                return;
            }
            if(mPageNo < mPageCount){
                mIsRefresh = false;
                loading.setVisibility(View.VISIBLE);
                ++mPageNo;
                mVideoListPresenter.queryList(Constant.number.ZERO,null,mPageNo);
            }else {
                mVideoAdapter.setEnableLoadMore(false);
            }

        }
    }
}
