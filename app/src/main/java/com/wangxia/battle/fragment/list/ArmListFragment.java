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
import com.wangxia.battle.adapter.ArmAdapter;
import com.wangxia.battle.fragment.base.BaseFragment;
import com.wangxia.battle.globe.App;
import com.wangxia.battle.model.bean.ArmBean;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.LogUtil;
import com.wangxia.battle.util.RecycleItemDecortion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 昝奥博 on 2018/1/8 0008
 * Email:18772833900@163.com
 * Explain：
 */
public class ArmListFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.smart_refresh)
    SwipeRefreshLayout smartRefresh;
    @BindView(R.id.tv_rank_normal)
    TextView tvRankNormal;
    @BindView(R.id.tv_rank_mid)
    TextView tvRankMid;
    @BindView(R.id.tv_rank_height)
    TextView tvRankHeight;
    @BindView(R.id.rl_view)
    RecyclerView rlView;
    @BindView(R.id.tv_empty_view)
    TextView tvEmptyView;
    private String mLabel;
    private ArmAdapter mArmAdapter;
    private Unbinder mBind;
    private TextView mLaseTxt;

    public static ArmListFragment newInstance(String key) {
        Bundle args = new Bundle();
        args.putString(Constant.string.ARG_ONE, key);
        ArmListFragment fragment = new ArmListFragment();
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
        View view = View.inflate(mContext, R.layout.fragment_arm_list, null);
        mBind = ButterKnife.bind(this, view);
        smartRefresh.setEnabled(false);
        smartRefresh.setColorSchemeResources(R.color.colorAccent, R.color.colorYellow, R.color.colorRad);
        smartRefresh.setRefreshing(true);
        rlView.setLayoutManager(new GridLayoutManager(mContext, Constant.number.FORE));
        rlView.addItemDecoration(new RecycleItemDecortion.GridSpacingItemDecoration(Constant.number.FORE,Constant.number.TWENTY_SIX,Constant.number.TWENTY,true));
        mArmAdapter = new ArmAdapter(null);
        rlView.setAdapter(mArmAdapter);
        return view;
    }

    @Override
    public void initData() {
        mLaseTxt = tvRankNormal;
        smartRefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                smartRefresh.setRefreshing(false);
                setData(mLabel, null);
            }
        },1500);

    }

    private void setData(String mLabel, String range) {
        if (View.VISIBLE == tvEmptyView.getVisibility()) {
            tvEmptyView.setVisibility(View.GONE);
        }
        List<ArmBean.DataBean> mData = new ArrayList<>();
        switch (mLabel) {
            case "全部":
                for (String type
                        : App.armMap.keySet()) {
                    if (null == range) {
                        for (String rank :
                                App.armMap.get(type).keySet()){
                            mData.addAll(App.armMap.get(type).get(rank));
                        }
                    } else {
                        List<ArmBean.DataBean> dataBeen = App.armMap.get(type).get(range);
                        if (null != dataBeen)
                            mData.addAll(dataBeen);
                    }
                }
                break;
            default:
                if (null == range) {
                    for (String rank :
                            App.armMap.get(mLabel).keySet())
                        mData.addAll(App.armMap.get(mLabel).get(rank));
                } else {
                    List<ArmBean.DataBean> dataBeen = App.armMap.get(mLabel).get(range);
                    if (null != dataBeen)
                        mData.addAll(dataBeen);
                    else mData.clear();
                }
                break;
        }
        //在对mData过滤一遍去除重复的
        mData = new ArrayList(new HashSet(mData));
        mArmAdapter.setNewData(mData);
        if (null == mData || mData.isEmpty()) {
            tvEmptyView.setVisibility(View.VISIBLE);
            tvEmptyView.setText("暂无数据~");
        }
    }

    @Override
    public void initListener() {
        tvRankNormal.setOnClickListener(this);
        tvRankMid.setOnClickListener(this);
        tvRankHeight.setOnClickListener(this);
        mArmAdapter.setItemClick(new ArmAdapter.IItemClickListener() {
            @Override
            public void itemClick(int position) {
                TabWithPagerActivity.toTabWithPagerActivity(mContext, Constant.number.FOURTEEN, mArmAdapter.getData().get(position).getU88c5u5907u540du79f0(), Constant.number.ZERO);
            }
        });

    }

    @Override
    public void recycleMemory() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_rank_normal:
                selectRank(tvRankNormal);
                break;
            case R.id.tv_rank_mid:
                selectRank(tvRankMid);
                break;
            case R.id.tv_rank_height:
                selectRank(tvRankHeight);
                break;
        }
    }

    private void selectRank(TextView v){
            if(mLaseTxt == v) return;
        else
            mLaseTxt.setTextColor(getResources().getColor(R.color.colorTxtSub));

        v.setTextColor(getResources().getColor(R.color.colorAccent));
        mLaseTxt = v;
        LogUtil.i("     "+v.getText().toString());
        setData(mLabel,  v.getText().toString());

    }
}
