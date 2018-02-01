package com.wangxia.battle.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.wangxia.battle.R;
import com.wangxia.battle.activity.AppDetailActivity;
import com.wangxia.battle.activity.BaseActivity;
import com.wangxia.battle.activity.TabWithPagerActivity;
import com.wangxia.battle.adapter.EvaluateAdapter;
import com.wangxia.battle.callback.CommentCallback;
import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.fragment.base.LazyBaseFragment;
import com.wangxia.battle.model.bean.CommentBean;
import com.wangxia.battle.model.bean.EvaluateBean;
import com.wangxia.battle.model.bean.SuccessBean;
import com.wangxia.battle.presenter.impPresenter.CommentListPresenter;
import com.wangxia.battle.presenter.impPresenter.UpVotePresenter;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.LogUtil;
import com.wangxia.battle.util.MyToast;
import com.wangxia.battle.util.NetUtil;
import com.wangxia.battle.util.UserUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 昝奥博 on 2017/7/22 0022
 * Email:18772833900@163.com
 * Explain：
 */
public class GameEvaluateFragment extends LazyBaseFragment implements ISuccessCallbackData ,CommentCallback,EvaluateAdapter.IUserHandle {
    @BindView(R.id.tv_empty_view)
    TextView tvEmptyView;
    @BindView(R.id.smart_refresh)
    SwipeRefreshLayout smartRefreshLayout;
    @BindView(R.id.rl_view)
    RecyclerView rlView;
    private Unbinder mBind;
    private EvaluateAdapter mEvaluateAdapter;
    private CommentListPresenter mCommentListPresenter;
    private int mPageCount;
    private int mCurrentPage = 1;
    private int mId;
    private String mLastInput;
    private boolean mIsRefresh;
    private int mUpVoteIndex;
    private boolean mIsNeedUserInfo;


    public static GameEvaluateFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt(Constant.string.ARG_ONE,id);
        GameEvaluateFragment fragment = new GameEvaluateFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("GameEvaluateFragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("GameEvaluateFragment");
    }

    @Override
    public void recycleMemory() {
        mBind.unbind();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mId = getArguments().getInt(Constant.string.ARG_ONE);
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_article_list, null);
        mBind = ButterKnife.bind(this, view);
        smartRefreshLayout.setColorSchemeResources(R.color.colorAccent,R.color.colorYellow,R.color.colorRad);
        smartRefreshLayout.setRefreshing(true);
        rlView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL,false));
        mEvaluateAdapter = new EvaluateAdapter();
        rlView.setAdapter(mEvaluateAdapter);
        return view;
    }

    @Override
    public void initData() {
        mCommentListPresenter = new CommentListPresenter(this);
        mCommentListPresenter.queryList(Constant.number.ONE,String.valueOf(mId),mCurrentPage);
    }

    @Override
    public void initListener() {
        smartRefreshLayout.setOnRefreshListener(new MyRefresh());
        mEvaluateAdapter.setOnLoadMoreListener(new MyLoadMore(),rlView);
        mEvaluateAdapter.initUserHandler(this);

    }

    @Override
    public void nice( int index) {
        mUpVoteIndex = index;
        new UpVotePresenter(this).queryList(Constant.number.ONE,String.valueOf(mEvaluateAdapter.getItem(index).getID()),Constant.number.TWO);
    }

    @Override
    public void replay(int pid, String userName) {
        if(UserUtil.getUserState(mContext))
            CommentFragment.newInstance(this,new CommentBean(Constant.number.ONE,mId,pid,String.valueOf("回复："+userName),mLastInput)).show(((AppDetailActivity)mContext).getFragmentManager(), CommentCallback.CALLBACK);
        else{
            Intent intent = new Intent(mContext, TabWithPagerActivity.class);
            intent.putExtra(Constant.string.ARG_ONE, Constant.number.EIGHT);
            intent.putExtra(Constant.string.ARG_TWO, Constant.userInfo.NEED_USER_INFO);
            mContext.startActivity(intent);
            mIsNeedUserInfo = true;
        }
    }

    @Override
    public void getResult(Object dataBen,int type) {
        if(null == mContext || ((BaseActivity)mContext).isFinishing()){
            return;
        }
        if(Constant.number.ONE == type){
                    if(mIsRefresh){
                        smartRefreshLayout.setRefreshing(false);
                    }
                    if(null != dataBen){
                            EvaluateBean evaluateBean = (EvaluateBean) dataBen;
                        if(null != evaluateBean && null != evaluateBean.getItems() && Constant.number.ZERO < evaluateBean.getItems().size()){
                            if(View.VISIBLE == tvEmptyView.getVisibility()){
                                tvEmptyView.setVisibility(View.GONE);
                            }
                            ((AppDetailActivity)mContext).setEvaluateCount(String.valueOf(evaluateBean.getDatacount()));
                            mCurrentPage = evaluateBean.getCurpage();
                            mPageCount = evaluateBean.getPagecount();
                            if(mIsRefresh){
                               mEvaluateAdapter.setNewData(evaluateBean.getItems());
                                mEvaluateAdapter.setEnableLoadMore(true);
                            }else {
                                mEvaluateAdapter.addData(evaluateBean.getItems());
                                mEvaluateAdapter.loadMoreComplete();
                            }
                        }else {
                            tvEmptyView.setText("暂无评论~");
                            tvEmptyView.setVisibility(View.VISIBLE);
                        }
                    }
        }else {
            SuccessBean successBean = (SuccessBean) dataBen;
            if("ok".equals(successBean.getSuccess())){
                MyToast.s(mContext,"点赞成功!");
                EvaluateBean.ItemsBean itemsBean = mEvaluateAdapter.getItem(mUpVoteIndex);
                itemsBean.setDig(true);
                itemsBean.setGood(String.valueOf(Integer.parseInt(itemsBean.getGood())+1));
                mEvaluateAdapter.notifyItemChanged(mUpVoteIndex);
            }


        }
    }

    @Override
    public void failRequest() {
        if(null == mContext || ((AppCompatActivity)mContext).isFinishing()){
            return;
        }
        if(smartRefreshLayout.isRefreshing()){
            smartRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void errorRequest() {
        if(null == mContext || ((AppCompatActivity)mContext).isFinishing()){
            return;
        }
        if(smartRefreshLayout.isRefreshing())
            smartRefreshLayout.setRefreshing(false);


        if(mIsRefresh)
            mEvaluateAdapter.setEnableLoadMore(true);
        else
            mEvaluateAdapter.loadMoreFail();

            if (NetUtil.isNetAvailable(mContext))
                MyToast.s(mContext, getString(R.string.client_busy));
             else
                MyToast.s(mContext, getString(R.string.no_net));


    }


    @Override
    public void saveCommentText(String commentWords) {
        this.mLastInput = commentWords;
    }

    /**
     * 评论成功的回调
     */
    @Override
    public void successComment() {
        LogUtil.i("不可能啊！！！！！！！！！！");
        if(null == mContext || null == mCommentListPresenter){
            return;
        }
        mIsRefresh = true;
        mCurrentPage = Constant.number.ONE;
        mCommentListPresenter.queryList(Constant.number.ONE,String.valueOf(mId),mCurrentPage);
    }

    /**
     *  评论失败的回调
     */
    @Override
    public void failComment() {

    }

    public void evaluateGame(){
        CommentFragment.newInstance(this,new CommentBean(Constant.number.ONE,mId,Constant.number.ZERO,String.valueOf("最少8个字哟！"),mLastInput)).show(((AppDetailActivity)mContext).getFragmentManager(), CommentCallback.CALLBACK);
    }

    private class MyRefresh implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            rlView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mEvaluateAdapter.setEnableLoadMore(false);
                    mIsRefresh = true;
                    mCurrentPage = Constant.number.ONE;
                    mCommentListPresenter.queryList(Constant.number.ONE,String.valueOf(mId),mCurrentPage);
                }
            },2000);

        }
    }

    private class MyLoadMore implements com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener {
        @Override
        public void onLoadMoreRequested() {
            mIsRefresh = false;
            if(mCurrentPage < mPageCount){
                ++mCurrentPage;
                mCommentListPresenter.queryList(Constant.number.ONE,String.valueOf(mId),mCurrentPage);
            }else {
                MyToast.s(mContext,"没有更多的数据啦！");
                mEvaluateAdapter.setEnableLoadMore(false);
            }
        }
    }
}
