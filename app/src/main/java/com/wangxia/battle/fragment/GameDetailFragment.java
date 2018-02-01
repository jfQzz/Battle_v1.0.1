package com.wangxia.battle.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.wangxia.battle.R;
import com.wangxia.battle.activity.AppDetailActivity;
import com.wangxia.battle.activity.TabWithPagerActivity;
import com.wangxia.battle.adapter.GameDetailAdapter;
import com.wangxia.battle.fragment.base.LazyBaseFragment;
import com.wangxia.battle.model.bean.AppInfo;
import com.wangxia.battle.model.bean.MultipleGameBean;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.MyToast;

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
public class GameDetailFragment extends LazyBaseFragment {
    @BindView(R.id.recycle_view)
    RecyclerView rl_icons;
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
        List<MultipleGameBean> list = new ArrayList<>(3);
        list.add(new MultipleGameBean(labels));
        list.add(new MultipleGameBean(icons));
        list.add(new MultipleGameBean(mAppInfo));
        mGameDetailAdapter = new GameDetailAdapter(list);
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
                if(text.equals(getString(R.string.show_all))){
                        view.setText(getString(R.string.collapse));
                        gameDesc.setMaxLines(Integer.MAX_VALUE/2);
                }else {
                        view.setText(getString(R.string.show_all));
                        gameDesc.setMaxLines(4);
                }
            }

            @Override
            public void toAppDetail(int id) {
                Intent intent = new Intent(mContext, AppDetailActivity.class);
                intent.putExtra(Constant.string.ARG_ONE,id);
                startActivity(intent);

            }

            @Override
            public void addQqGroup() {
                addGroup(Constant.number.ZERO);
            }

            @Override
            public void addWxGroup() {
                addGroup(Constant.number.ONE);

            }
        });

    }

    /**
     *
     * @param type 0:QQ  1:wx
     */
    private void addGroup(int type) {
        Intent intent = new Intent();
        if(Constant.number.ZERO == type){
            intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + "ZxasxSH7FwHTJEQ0XT-uOsH1DSuuOeYk"));
        }else {
            ClipboardManager clipManager = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("simple text", "wxhackhome");
            clipManager.setPrimaryClip(clipData);
            if (clipManager.hasPrimaryClip()) {
                MyToast.s(mContext, "jzpaj520 已复制");
            }
            ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setComponent(cmp);
        }

        PackageManager pm = mContext.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentActivities(intent, 0);
        if (resolveInfo.size() == 0) {
            MyToast.showToast(mContext, "你的手机未安装相关软件", Toast.LENGTH_SHORT);
            return;
        } else {
            //开启一个新的栈
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        startActivity(intent);
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
