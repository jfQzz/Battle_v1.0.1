package com.wangxia.battle.fragment.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.umeng.analytics.MobclickAgent;
import com.wangxia.battle.R;
import com.wangxia.battle.activity.VideoActivity;
import com.wangxia.battle.adapter.VideoAdapter;
import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.fragment.BaseFragment;
import com.wangxia.battle.model.bean.VideoList;
import com.wangxia.battle.presenter.impPresenter.VideoListPresenter;
import com.wangxia.battle.util.Constant;
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
 * Explain：
 */
public class VideoListFragment extends BaseFragment implements ISuccessCallbackData , OnLoadMoreListener.ILoadMoreListener{

    @BindView(R.id.smart_refresh)
    SwipeRefreshLayout smartRefreshLayout;
    @BindView(R.id.rl_view)
    RecyclerView rl_view;
    private Context mContext;
    private VideoListPresenter mVideoListPresenter;
    private int mNextPage = Constant.number.ZERO;
    private Unbinder mBind;
    private int mPageCount;
    private List<VideoList.ItemListBeanX> mData = new ArrayList<>();
    private VideoAdapter mVideoAdapter;
    private boolean mIsRefreshData = false;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        WeakReference<Context> weakReference = new WeakReference<Context>(context);
        mContext = weakReference.get();
    }

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
        rl_view.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL,false));
        mVideoAdapter = new VideoAdapter(mContext,mData);
        rl_view.setAdapter(mVideoAdapter);
        return view;
    }

    @Override
    public void initData() {
        mVideoListPresenter = new VideoListPresenter(this);
        mVideoListPresenter.queryList(Constant.number.ZERO,null,mNextPage);

    }

    @Override
    public void initListener() {
        smartRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mNextPage = Constant.number.ZERO;
                mIsRefreshData = true;
                mVideoListPresenter.queryList(Constant.number.ZERO,null,mNextPage);
            }
        });
       rl_view.addOnScrollListener(new OnLoadMoreListener(this));

        mVideoAdapter.setiItemClick(new VideoAdapter.IItemClick() {
            @Override
            public void playVideo(String videoUrl, String title, String desc) {
                MobclickAgent.onEvent(mContext, Constant.uMengStatistic.FOCUS_VIDEO_HINTS);
                Intent intent = new Intent(mContext, VideoActivity.class);
                intent.putExtra(Constant.string.ARG_ONE,videoUrl);
                intent.putExtra(Constant.string.ARG_TWO,title);
                intent.putExtra(Constant.string.ARG_THREE,desc);
                startActivity(intent);
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
        if(null != mData){
            mData.clear();
        }
        if(null != mVideoAdapter){
            mVideoAdapter.clearMemory();
        }
        mBind.unbind();
        smartRefreshLayout = null;
    }

    @Override
    public void getResult(Object dataBen,int type) {
        if(mIsRefreshData){
            smartRefreshLayout.setRefreshing(false);
        }
        if(null != dataBen){
            if(dataBen instanceof VideoList){
                VideoList videoList = (VideoList) dataBen;
                mPageCount = videoList.getCount();
                if(mIsRefreshData && mData != null){
                    mData.clear();
                    mIsRefreshData = false;
                }
                mData.addAll(videoList.getItemList());
                mVideoAdapter.notifyDataSetChanged();
                String nextPageUrl = videoList.getNextPageUrl();
                if(!TextUtils.isEmpty(nextPageUrl) && nextPageUrl.contains("date=") && nextPageUrl.contains("&num")){
                    int dataStart = nextPageUrl.indexOf("date=");
                    int dataEnd = nextPageUrl.indexOf("&num");
                    String numString = nextPageUrl.substring(dataStart+5, dataEnd);
                    if(!TextUtils.isEmpty(numString)) {
                        String intStr = numString.substring(0, numString.length() - 5);
                        mNextPage = Integer.parseInt(intStr);
                    }
                }
            }
        }
    }

    @Override
    public void loadMoreData() {
        mVideoListPresenter.queryList(Constant.number.ZERO,null,mNextPage);
    }
}
