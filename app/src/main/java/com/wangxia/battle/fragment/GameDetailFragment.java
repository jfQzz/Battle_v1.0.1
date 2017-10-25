package com.wangxia.battle.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.wangxia.battle.R;
import com.wangxia.battle.activity.AppDetailActivity;
import com.wangxia.battle.activity.TabWithPagerActivity;
import com.wangxia.battle.adapter.GameDetailAdapter;
import com.wangxia.battle.model.bean.AppInfo;
import com.wangxia.battle.util.Constant;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 昝奥博 on 2017/7/22 0022
 * Email:18772833900@163.com
 * Explain：游戏详情页
 */
public class GameDetailFragment extends BaseFragment {
    @BindView(R.id.recycle_view)
    RecyclerView rl_icons;
    private Context mContext;
    private AppInfo mAppInfo;
    private Unbinder mBind;
    private GameDetailAdapter mGameDetailAdapter;

    public static GameDetailFragment newInstance(AppInfo appInfo) {
        Bundle args = new Bundle();
        args.putSerializable(Constant.string.ARG_ONE, appInfo);
        GameDetailFragment fragment = new GameDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        WeakReference<Context> weakReference = new WeakReference<>(context);
        mContext = weakReference.get();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (null != arguments) {
            mAppInfo = (AppInfo) arguments.getSerializable(Constant.string.ARG_ONE);
        }
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_game_detail, null);
        mBind = ButterKnife.bind(this, view);

        rl_icons.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        List<AppInfo.PicBean> pic = mAppInfo.getPic();
        int count = pic.size();
        List<String> icons = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            icons.add(pic.get(i).getName());
        }
        String labels = mAppInfo.getLabels();
        List<Object> list = new ArrayList<>(3);
        list.add(labels);
        list.add(icons);
        list.add(mAppInfo);
        mGameDetailAdapter = new GameDetailAdapter(mContext, list);
        rl_icons.setAdapter(mGameDetailAdapter);
        return view;
    }



    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        mGameDetailAdapter.setItemClick(new GameDetailAdapter.IItemClic() {
            @Override
            public void showBigPic(String url ,int index) {
                Intent intent = new Intent(mContext, TabWithPagerActivity.class);
                intent.putExtra(Constant.string.ARG_ONE, Constant.number.FIVE);
                intent.putExtra(Constant.string.ARG_TWO, url);
                intent.putExtra(Constant.string.ARG_THREE, index);
               startActivity(intent);
            }

            @Override
            public void openOrCloseTxt(TextView view) {
                CharSequence text = view.getText();
                 TextView gameDesc = (TextView) view.getTag(R.id.tag_first);
                if(text.equals("显示全部")){
                        view.setText("收起");
                        gameDesc.setMaxLines(Integer.MAX_VALUE/2);
                }else {
                        view.setText("显示全部");
                        gameDesc.setMaxLines(4);
                }
            }

            @Override
            public void toAppDetail(int id) {
                Intent intent = new Intent(mContext, AppDetailActivity.class);
                intent.putExtra(Constant.string.ARG_ONE,id);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("GameDetailFragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("GameDetailFragment");
    }

    @Override
    public void recycleMemory() {
        mBind.unbind();

    }



}
