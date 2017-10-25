package com.wangxia.battle.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.wangxia.battle.R;
import com.wangxia.battle.adapter.EvaluateAdapter;
import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.model.bean.EvaluateBean;
import com.wangxia.battle.presenter.impPresenter.GameEvaluatePresenter;
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
 * Explain：
 */
public class GameEvaluateFragment extends BaseFragment implements ISuccessCallbackData ,OnLoadMoreListener.ILoadMoreListener{
    @BindView(R.id.tv_empty_view)
    TextView tvEmptyView;
    @BindView(R.id.smart_refresh)
    SwipeRefreshLayout smartRefreshLayout;
    @BindView(R.id.rl_view)
    RecyclerView rlView;
    private Context mContext;
    private Unbinder mBind;
    private List<EvaluateBean.ItemsBean> mData = new ArrayList<>();
    private EvaluateAdapter mEvaluateAdapter;
    private GameEvaluatePresenter mGameEvaluatePresenter;
    private int mPageCount;
    private int mCurrentPage = 1;
    private int mId;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        WeakReference<Context> weakReference = new WeakReference<>(context);
        mContext = weakReference.get();
    }

    public static GameEvaluateFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt(Constant.string.ARG_ONE,id);
        GameEvaluateFragment fragment = new GameEvaluateFragment();
        fragment.setArguments(args);
        return fragment;
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
        rlView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL,false));
        mEvaluateAdapter = new EvaluateAdapter(mContext,mData);
        rlView.setAdapter(mEvaluateAdapter);
        return view;
    }

    @Override
    public void initData() {
        mGameEvaluatePresenter = new GameEvaluatePresenter(this);
        mGameEvaluatePresenter.queryList(mId,null,mCurrentPage);
    }

    @Override
    public void initListener() {
        smartRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCurrentPage = Constant.number.ONE;
                mGameEvaluatePresenter.queryList(mId,null,mCurrentPage);

            }
        });
        rlView.addOnScrollListener(new OnLoadMoreListener(this));
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
    public void getResult(Object dataBen,int type) {
        if(null != dataBen){
                EvaluateBean evaluateBean = (EvaluateBean) dataBen;
            if(null != evaluateBean && null != evaluateBean.getItems() && Constant.number.ZERO < evaluateBean.getItems().size()){
                mCurrentPage = evaluateBean.getCurpage();
                mPageCount = evaluateBean.getPagecount();
                mData.addAll(evaluateBean.getItems());
                mEvaluateAdapter.notifyDataSetChanged();
            }else {
                tvEmptyView.setText("暂无评论~");
                tvEmptyView.setVisibility(View.VISIBLE);
                rlView.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void loadMoreData() {
        if(mCurrentPage < mPageCount){
            ++mCurrentPage;
            mGameEvaluatePresenter.queryList(mId,null,mCurrentPage);

        }else {
            MyToast.showToast(mContext,"没有更多的数据啦！", Toast.LENGTH_SHORT);
        }
    }
}
