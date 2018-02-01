package com.wangxia.battle.adapter;

import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wangxia.battle.R;
import com.wangxia.battle.model.bean.AppInfoList;
import com.wangxia.battle.util.Constant;

import java.util.List;

/**
 * Created by 昝奥博 on 2017/9/13 0013
 * Email:18772833900@163.com
 * Explain：游戏列表
 */
public class AppAdapter extends BaseQuickAdapter<AppInfoList.ItemsBean, BaseViewHolder> {
    private ItemClick itemClick;

    public AppAdapter(@Nullable List<AppInfoList.ItemsBean> data) {
        super(R.layout.appinfo_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppInfoList.ItemsBean item) {
        ((SimpleDraweeView) helper.getView(R.id.iv_app_pic)).setImageURI(item.getImage());
        helper.setText(R.id.tv_app_name, Html.fromHtml(item.getAppname()));
        String labels = item.getLabels().replaceAll(Constant.string.COMMA_SEPARATOR, "\t");
        String label = String.valueOf(Integer.parseInt(item.getSoftsize()) / 1024 + "M\t\t");
        if (!TextUtils.isEmpty(labels)) {
            label += labels;
        }
        helper.setText(R.id.tv_app_remark, label);
        helper.setText(R.id.tv_app_desc, Html.fromHtml(item.getDesc()));
        helper.itemView.setTag(R.id.tag_first, item.getID());
        helper.getView(R.id.rl_app_download).setTag(R.id.tag_first, item);
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.toAppDetail((int) v.getTag(R.id.tag_first));
            }
        });

        helper.getView(R.id.rl_app_download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.downloadApp((AppInfoList.ItemsBean) v.getTag(R.id.tag_first));
            }
        });
    }


    public void initItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    public interface ItemClick {
        void toAppDetail(int id);

        void downloadApp(AppInfoList.ItemsBean itemsBean);
    }
}
