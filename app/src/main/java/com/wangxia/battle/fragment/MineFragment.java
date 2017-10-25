package com.wangxia.battle.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.analytics.MobclickAgent;
import com.wangxia.battle.R;
import com.wangxia.battle.activity.TabWithPagerActivity;
import com.wangxia.battle.util.Constant;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 昝奥博 on 2017/7/22 0022
 * Email:18772833900@163.com
 * Explain：
 */
public class MineFragment extends BaseFragment implements View.OnClickListener{
    @BindView(R.id.rl_user_info)
    RelativeLayout rlUserInfo;
    @BindView(R.id.iv_user_ico)
    SimpleDraweeView ivUserIco;
    @BindView(R.id.tv_user_nick)
    TextView tvUserNick;
    @BindView(R.id.tv_user_id)
    TextView tvUserId;
    @BindView(R.id.ll_local_game)
    LinearLayout llLocalGames;
    @BindView(R.id.ll_browsing_history)
    LinearLayout llBrowsingHistory;
    @BindView(R.id.ll_my_favorite)
    LinearLayout llMyFavorite;
    @BindView(R.id.ll_feed_back)
    LinearLayout llFeedBack;
    private Context mContext;
    private Unbinder mBind;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        WeakReference<Context> weakReference = new WeakReference<>(context);
        mContext = weakReference.get();
    }

    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_mine, null);
        mBind = ButterKnife.bind(this,view);
        hideWhenExit();
        return view;
    }

    /**
     * 后台说目前不做登录
     */
    private void hideWhenExit() {
        ivUserIco.setVisibility(View.INVISIBLE);
        tvUserNick.setVisibility(View.GONE);
        tvUserId.setVisibility(View.GONE);

    }

    @Override
    public void initData() {
    }

    @Override
    public void initListener() {
        rlUserInfo.setOnClickListener(this);
        llLocalGames.setOnClickListener(this);
        llBrowsingHistory.setOnClickListener(this);
        llMyFavorite.setOnClickListener(this);
        llFeedBack.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("MineFragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MineFragment");
    }

    @Override
    public void recycleMemory() {
        mBind.unbind();

    }



    @Override
    public void onClick(View v) {
            Intent intent = null;
        switch (v.getId()){
            //登录/个人详情
            case R.id.rl_user_info:

                break;
            case R.id.ll_local_game:
                intent = new Intent(mContext, TabWithPagerActivity.class);
                intent.putExtra(Constant.string.ARG_ONE, Constant.number.ZERO);
                break;
//            case R.id.tv_my_download:
//                intent = new Intent(mContext, TabWithPagerActivity.class);
//                intent.putExtra(Constant.string.ARG_ONE, Constant.number.ONE);
//                break;
            case R.id.ll_browsing_history:
                intent = new Intent(mContext, TabWithPagerActivity.class);
                intent.putExtra(Constant.string.ARG_ONE, Constant.number.TWO);
                break;
            case R.id.ll_my_favorite:
                intent = new Intent(mContext, TabWithPagerActivity.class);
                intent.putExtra(Constant.string.ARG_ONE, Constant.number.THREE);
                break;
            //暂时改为关于我们，需要在加反馈
            case R.id.ll_feed_back:
                intent = new Intent(mContext, TabWithPagerActivity.class);
                intent.putExtra(Constant.string.ARG_ONE, Constant.number.FORE);
                break;
        }

        if(null != intent){
            startActivity(intent);
        }
    }
}
