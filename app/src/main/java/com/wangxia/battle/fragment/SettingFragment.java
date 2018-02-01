package com.wangxia.battle.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wangxia.battle.R;
import com.wangxia.battle.activity.TabWithPagerActivity;
import com.wangxia.battle.fragment.base.BaseFragment;
import com.wangxia.battle.globe.App;
import com.wangxia.battle.util.ApkUtil;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.DataCleanManager;

import java.io.File;
import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 昝奥博 on 2018/1/11 0011
 * Email:18772833900@163.com
 * Explain：app设置
 */
public class SettingFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.tv_cache_size)
    TextView tvCacheSize;
    @BindView(R.id.ll_clear_cache)
    LinearLayout llClearCache;
    @BindView(R.id.tv_app_version)
    TextView tvAppVersion;
    @BindView(R.id.ll_version_check)
    LinearLayout llVersionCheck;
    @BindView(R.id.ll_about_us)
    LinearLayout llAboutUs;
    @BindView(R.id.ll_set)
    LinearLayout llSet;
    private Unbinder mBind;

    public static SettingFragment newInstance() {
        Bundle args = new Bundle();
        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_setting, null);
        mBind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        tvAppVersion.setText("v" + ApkUtil.getVerName(mContext));
        long cacheSize = DataCleanManager.getFolderSize(new File(Constant.string.DOWNLOAD_PATH));
        DecimalFormat df = new DecimalFormat("0.00");
        tvCacheSize.setText(df.format((float) cacheSize / Constant.number.THOUSAND_AND_TWENTY_FORE / Constant.number.THOUSAND_AND_TWENTY_FORE) + "M");

    }

    @Override
    public void initListener() {
        llClearCache.setOnClickListener(this);
        llVersionCheck.setOnClickListener(this);
        llAboutUs.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_clear_cache:
                showDialog(Constant.number.ZERO, "正在清理", 4000);
                break;
            case R.id.ll_version_check:
                showDialog(Constant.number.ONE, "正在获取新版本信息", 2000);
                break;
            case R.id.ll_about_us:
                Intent intent = new Intent(mContext, TabWithPagerActivity.class);
                intent.putExtra(Constant.string.ARG_ONE, Constant.number.ELEVEN);
                startActivity(intent);
                break;
        }
    }


    /**
     * @param type  0：清理缓存 1：版本检测
     * @param hint
     * @param delay
     */
    private void showDialog(final int type, String hint, int delay) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_loading_hint, null);
        final ProgressBar pgRate = (ProgressBar) view.findViewById(R.id.pg_rate);
        final TextView tvHint = (TextView) view.findViewById(R.id.tv_hint);
        tvHint.setText(hint);
        builder.setView(view);
        builder.setCancelable(false);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                pgRate.setVisibility(View.INVISIBLE);
                tvHint.setTextColor(getResources().getColor(R.color.colorAccent));
                switch (type) {
                    case Constant.number.ZERO:
                        DataCleanManager.deleteFolderFile(Constant.string.DOWNLOAD_PATH,true);
                        App.mReaderManager.deleteAllDB();
                        tvHint.setText(getString(R.string.clear_cache_finish));
                        tvCacheSize.setText("0.00M");
                        break;
                    case Constant.number.ONE:
                        tvHint.setText(getString(R.string.app_new_version));
                        break;
                }
                pgRate.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        alertDialog.dismiss();
                    }
                }, 500);
            }
        }, delay);


    }

    @Override
    public void recycleMemory() {

    }
}
