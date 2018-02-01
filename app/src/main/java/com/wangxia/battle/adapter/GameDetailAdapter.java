package com.wangxia.battle.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wangxia.battle.R;
import com.wangxia.battle.model.bean.AppInfo;
import com.wangxia.battle.model.bean.MultipleGameBean;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.Mytime;
import com.wangxia.battle.util.RecycleItemDecortion;
import com.wangxia.battle.util.TxtFormatUtil;

import java.util.List;

/**
 * Created by 昝奥博 on 2017/9/14 0014
 * Email:18772833900@163.com
 * Explain：
 */
public class GameDetailAdapter extends BaseMultiItemQuickAdapter<MultipleGameBean,BaseViewHolder> {
    private IItemClic iItemClic;

    public GameDetailAdapter(List<MultipleGameBean> data) {
        super(data);
        addItemType(MultipleGameBean.LABEL, R.layout.label_items);
        addItemType(MultipleGameBean.PICS, R.layout.recycle_view);
        addItemType(MultipleGameBean.OTHER, R.layout.game_detail_head);
    }

    public void setItemClick(IItemClic itemClick){
        this.iItemClic = itemClick;
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleGameBean item) {
        switch (helper.getItemViewType()) {
            case MultipleGameBean.LABEL:
                String[] split = item.getLabel().split(",");
                helper.setText(R.id.tv_game_label_one,split[Constant.number.ZERO]);
                helper.setText(R.id.tv_game_label_two,split[Constant.number.ONE]);
                helper.setText(R.id.tv_game_label_three,split[Constant.number.TWO]);
                helper.setText(R.id.tv_game_label_fore,split[Constant.number.THREE]);
                break;
            case MultipleGameBean.PICS:
                BigImgAdapter bigImgAdapter = new BigImgAdapter(item.getPics());
                RecyclerView rlPic = helper.getView(R.id.rl_icons);
                rlPic.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL,false));
                rlPic.addItemDecoration(new RecycleItemDecortion.SpacesItemDecoration(Constant.number.TEN,Constant.number.TEN,Constant.number.ZERO));
                bigImgAdapter.setShowMode(Constant.number.ONE);
                rlPic.setAdapter(bigImgAdapter);
                bigImgAdapter.setItemClick(new BigImgAdapter.IitemClick() {
                    @Override
                    public void getImgUrl(String url,int index) {
                        iItemClic.showBigPic(url,index);
                    }
                });
                break;
            case MultipleGameBean.OTHER:
                AppInfo appInfo = item.getAppInfo();
                helper.setText(R.id.tv_game_desc,TxtFormatUtil.HtmlFormat(appInfo.getText()).toString());
                String downloadTimes = "";
                String updateTime = "";
                String versionCode = "";
                String hints = String.valueOf(13897);

                if (!TextUtils.isEmpty(appInfo.getHits())) {
                    hints = appInfo.getHits();
                }
                if (0 != Integer.parseInt(hints)) {
                    downloadTimes = "\n下载次数：" + hints;
                }
                if (!TextUtils.isEmpty(appInfo.getTime())) {
                    updateTime = "\n更新日期：" + Mytime.formatTime(appInfo.getTime());
                }
                if (!TextUtils.isEmpty(appInfo.getVer())) {
                    versionCode = "版本号：" + appInfo.getVer();
                }
                helper.setText(R.id.tv_game_detail,versionCode + updateTime + downloadTimes);
                TextView tvIsOpen = helper.getView(R.id.tv_open_or_close);
                tvIsOpen.setTag(R.id.tag_first,helper.getView(R.id.tv_game_desc));
                RecyclerView rlMoreGames = helper.getView(R.id.rl_more_game);
                rlMoreGames.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL,false));
                rlMoreGames.addItemDecoration(new RecycleItemDecortion.SpacesItemDecoration(5,0));
                MoreGameAdapter moreGameAdapter = new MoreGameAdapter(mContext, appInfo.getAppList());
                rlMoreGames.setAdapter(moreGameAdapter);
                moreGameAdapter.setItemClick(new MoreGameAdapter.IItemClick() {
                    @Override
                    public void getAppId(int id) {
                        iItemClic.toAppDetail(id);
                    }
                });
                tvIsOpen.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            iItemClic.openOrCloseTxt((TextView) v);
                    }
                });
                helper.getView(R.id.ll_add_qq_group).setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    iItemClic.addQqGroup();
                    }
                });
                helper.getView(R.id.ll_add_wx_group).setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       iItemClic.addWxGroup();
                    }
                });
                break;
        }
    }


    public interface IItemClic{
        void showBigPic(String url, int index);
        void openOrCloseTxt(TextView view);
        void toAppDetail(int id);
        void addQqGroup();
        void addWxGroup();

    }
}
