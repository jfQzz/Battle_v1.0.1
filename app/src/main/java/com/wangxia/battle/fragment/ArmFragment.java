package com.wangxia.battle.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wangxia.battle.R;
import com.wangxia.battle.activity.TabWithPagerActivity;
import com.wangxia.battle.adapter.PicCoverWordAdapter;
import com.wangxia.battle.fragment.base.BaseFragment;
import com.wangxia.battle.globe.App;
import com.wangxia.battle.model.bean.ArmBean;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.TxtFormatUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 昝奥博 on 2018/1/9 0009
 * Email:18772833900@163.com
 * Explain：
 */
public class ArmFragment extends BaseFragment {
    @BindView(R.id.iv_arm)
    SimpleDraweeView ivArm;
    @BindView(R.id.tv_arm_name)
    TextView tvArmName;
    @BindView(R.id.tv_arm_price)
    TextView tvArmPrice;
    @BindView(R.id.tv_arm_consumer)
    TextView tvArmConsumer;
    @BindView(R.id.tv_make_need)
    TextView tvMakeNeed;
    @BindView(R.id.rl_make_need)
    RecyclerView rlMakeNeed;
    @BindView(R.id.tv_make)
    TextView tvMake;
    @BindView(R.id.rl_make)
    RecyclerView rlMake;
    @BindView(R.id.tv_empty_make)
    TextView tvEmptyMake;
    @BindView(R.id.tv_empty_need)
    TextView tvEmptyNeed;
    private Unbinder mBind;
    private String mLabel;

    public static ArmFragment newInstance(String labels) {
        Bundle args = new Bundle();
        args.putString(Constant.string.ARG_ONE, labels);
        ArmFragment fragment = new ArmFragment();
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
        View view = View.inflate(mContext, R.layout.fragment_arm, null);
        mBind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        ArmBean.DataBean dataBean = App.armNameMap.get(mLabel);
        ivArm.setImageURI(TxtFormatUtil.getPic(dataBean.getU56feu6807u8defu5f84()));
        tvArmName.setText(dataBean.getU88c5u5907u540du79f0());
        tvArmPrice.setText("装备价格：" + dataBean.getU88c5u5907u4ef7u683c());
        List<String> u = dataBean.getU88c5u5907u88abu52a8u6280u80fd();
        if (null != u && !u.isEmpty()) {
            String s = Arrays.toString(u.toArray());
            tvArmConsumer.setText(TxtFormatUtil.formatArmColor(s.substring(Constant.number.ONE,s.length()-Constant.number.ONE)));
        } else tvArmConsumer.setText("暂无装备性能描述");
        List<String> makeList = new ArrayList<>(dataBean.getU53efu5408u6210u88c5u5907().size());
        for (int id :
                dataBean.getU53efu5408u6210u88c5u5907()) {
            makeList.add(String.valueOf(id));
        }
        setArm(rlMakeNeed, dataBean.getU5b50u7269u54c1());
        setArm(rlMake, makeList);

    }

    private void setArm(RecyclerView rl, List<String> u) {
        if (null == u || u.isEmpty()) {
            if (rl == rlMakeNeed)
                tvEmptyNeed.setVisibility(View.VISIBLE);
            else
                tvEmptyMake.setVisibility(View.VISIBLE);
            return;
        }
        rl.setLayoutManager(new GridLayoutManager(mContext, Constant.number.FORE));
        PicCoverWordAdapter picCoverWordAdapter = new PicCoverWordAdapter(u);
        picCoverWordAdapter.setType(Constant.number.ONE);
        rl.setAdapter(picCoverWordAdapter);
        picCoverWordAdapter.initItemListener(new PicCoverWordAdapter.IOnItemClickListener() {
            @Override
            public void itemClick(String nick) {
                TabWithPagerActivity.toTabWithPagerActivity(mContext, Constant.number.FOURTEEN, nick, Constant.number.ZERO,Constant.number.ONE);

            }
        });

    }

    @Override
    public void initListener() {

    }

    @Override
    public void recycleMemory() {

    }
}
