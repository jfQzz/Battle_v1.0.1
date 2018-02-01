package com.wangxia.battle.fragment.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wangxia.battle.R;
import com.wangxia.battle.activity.TabWithPagerActivity;
import com.wangxia.battle.adapter.HeroAdapter;
import com.wangxia.battle.fragment.base.LazyBaseFragment;
import com.wangxia.battle.globe.App;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.RecycleItemDecortion;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 昝奥博 on 2018/1/8 0008
 * Email:18772833900@163.com
 * Explain：
 */
public class HeroListFragment extends LazyBaseFragment {
    @BindView(R.id.rl_view)
    RecyclerView rlView;
    @BindView(R.id.tv_empty_view)
    TextView tvEmptyView;
    @BindView(R.id.smart_refresh)
    SwipeRefreshLayout smartRefresh;
    private Unbinder mBind;
    private String mLabel;
    private HeroAdapter mHeroAdapter;

    public static HeroListFragment newInstance(String label) {
        Bundle args = new Bundle();
        args.putString(Constant.string.ARG_ONE, label);
        HeroListFragment fragment = new HeroListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLabel = getArguments().getString(Constant.string.ARG_ONE);

    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_article_list, null);
        mBind = ButterKnife.bind(this, view);
        smartRefresh.setEnabled(false);
        smartRefresh.setColorSchemeResources(R.color.colorAccent, R.color.colorYellow, R.color.colorRad);
        smartRefresh.setRefreshing(true);
        rlView.setLayoutManager(new GridLayoutManager(mContext,Constant.number.FORE));
        rlView.addItemDecoration(new RecycleItemDecortion.GridSpacingItemDecoration(Constant.number.FORE,Constant.number.TWENTY_SIX,Constant.number.TWENTY,true));
        mHeroAdapter = new HeroAdapter(App.heroMap.get(mLabel));
        return view;
    }

    @Override
    public void initData() {
        smartRefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                smartRefresh.setRefreshing(false);
                rlView.setAdapter(mHeroAdapter);
            }
        },1500);
    }

    @Override
    public void initListener() {
        mHeroAdapter.setItemClick(new HeroAdapter.IItemClickListener() {
            @Override
            public void itemClick(int position) {
                TabWithPagerActivity.toTabWithPagerActivity(mContext,Constant.number.THIRTEEN,mLabel,position);
            }
        });
    }

    @Override
    public void recycleMemory() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }
}
