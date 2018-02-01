package com.wangxia.battle.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.analytics.MobclickAgent;
import com.wangxia.battle.R;
import com.wangxia.battle.activity.TabWithPagerActivity;
import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.fragment.base.BaseFragment;
import com.wangxia.battle.model.bean.UserInfo;
import com.wangxia.battle.presenter.impPresenter.UserInfoPresenter;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.SpUtil;
import com.wangxia.battle.util.UserUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 昝奥博 on 2017/7/22 0022
 * Email:18772833900@163.com
 * Explain：
 */
public class MineFragment extends BaseFragment implements View.OnClickListener, ISuccessCallbackData {
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
    @BindView(R.id.ll_set)
    LinearLayout llSet;
    private Unbinder mBind;


    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_mine, null);
        mBind = ButterKnife.bind(this, view);
        return view;
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
        llSet.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("MineFragment");
        //检测登录状态
        if (SpUtil.getBoolean(mContext, Constant.userInfo.USER_STATE, false)) {
            showUerInfo();
        }else {
            //显示登录等相关的信息
            tvUserNick.setText(getString(R.string.enter_now));
            tvUserId.setText(getString(R.string.enter_hints));
            ivUserIco.setImageResource(R.drawable.ic_user_icon_empty);
        }
    }

    /**
     * 用户的信息
     */
    private void showUerInfo() {
        UserInfoPresenter userInfoPresenter = new UserInfoPresenter(this);
        userInfoPresenter.queryList(Constant.number.ZERO, UserUtil.getCookies(), Constant.number.ZERO);

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
        switch (v.getId()) {
            //登录/个人详情
            case R.id.rl_user_info:
                intent = new Intent(mContext, TabWithPagerActivity.class);
                if (SpUtil.getBoolean(mContext, Constant.userInfo.USER_STATE, false))
                    intent.putExtra(Constant.string.ARG_ONE, Constant.number.ONE);
                else
                    intent.putExtra(Constant.string.ARG_ONE, Constant.number.EIGHT);
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
            case R.id.ll_set:
                intent = new Intent(mContext, TabWithPagerActivity.class);
                intent.putExtra(Constant.string.ARG_ONE, Constant.number.FORE);
                break;
        }

        if (null != intent) {
            startActivity(intent);
        }
    }

    @Override
    public void getResult(Object dataBen, int type) {
        if (null == dataBen) {
            return;
        }
        switch (type) {
            case Constant.number.ZERO:
                com.wangxia.battle.model.bean.UserInfo userInfo = (com.wangxia.battle.model.bean.UserInfo) dataBen;
                if (null != userInfo) {
                    if(TextUtils.equals("200",userInfo.getStatus())){
                            ivUserIco.setImageURI(userInfo.getUserpic());
                            tvUserNick.setText(userInfo.getUsernike());
                            tvUserId.setText("ID : "+userInfo.getUserid());
                            saveUserInfo(userInfo);
                    }else {
                        //显示登录等相关的信息(出现意外及时处理本地缓存的信息，以及登录状态还原)
                        UserUtil.exit(mContext);
                        tvUserNick.setText(getString(R.string.enter_now));
                        tvUserId.setText(getString(R.string.enter_hints));
                        ivUserIco.setImageResource(R.drawable.ic_user_icon_empty);
                    }
                }
                break;
        }
    }

    @Override
    public void failRequest() {

    }
    @Override
    public void errorRequest() {

    }

    private void saveUserInfo(UserInfo userInfo) {
        String userId = userInfo.getUserid();
        String userNike = userInfo.getUsernike();
        SpUtil.putString(mContext, Constant.userInfo.USER_NICK, userNike);
        SpUtil.putString(mContext, Constant.userInfo.USER_ICON, userInfo.getUserpic());
        SpUtil.putString(mContext, Constant.userInfo.USER_ID, userId);
        SpUtil.putString(mContext, Constant.userInfo.USER_TYPE, userInfo.getIspass());
        String gender = TextUtils.equals("1",userInfo.getUsersex()) ? "男":"女";
        SpUtil.putString(mContext, Constant.userInfo.USER_GENDER, gender);
        SpUtil.putString(mContext, Constant.userInfo.USER_LOGIN_TIME, getString(R.string.last_login_time) + userInfo.getUserretime());
        //环信注册
//        try {
//            EMClient.getInstance().createAccount(userNike, userId);
//        } catch (HyphenateException e) {
//            e.printStackTrace();
//            MyToast.s(mContext,"环信注册失败");
//        }
        // 最好在会话的主页加上
//        EMClient.getInstance().chatManager().loadAllConversations();
//        EMClient.getInstance().groupManager().loadAllGroups();
    }


    public void changeToMobile() {

    }


    public void changeToWifi() {

    }


}
