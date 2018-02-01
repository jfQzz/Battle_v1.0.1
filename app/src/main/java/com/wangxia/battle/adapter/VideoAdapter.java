package com.wangxia.battle.adapter;

import android.view.View;
import android.view.View.OnClickListener;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wangxia.battle.R;
import com.wangxia.battle.model.bean.VideoList;
import com.wangxia.battle.util.TxtFormatUtil;

/**
 * Created by 昝奥博 on 2017/9/16 0016
 * Email:18772833900@163.com
 * Explain：
 */
public class VideoAdapter extends BaseQuickAdapter<VideoList.ItemsBean, BaseViewHolder> {
    private IItemClick iItemClick;
    public VideoAdapter() {
        super(R.layout.video_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoList.ItemsBean item) {
        SimpleDraweeView ivPic = helper.getView(R.id.iv_video_pic);
        ivPic.setImageURI(item.getPic());
        ((SimpleDraweeView) helper.getView(R.id.iv_author_ico)).setImageURI(item.getPic());
        helper.setText(R.id.tv_video_title, TxtFormatUtil.HtmlFormat(item.getTitle()));
        helper.setText(R.id.tv_video_time, String.valueOf("#" + item.getRealtime()));
        helper.setText(R.id.tv_video_hints, item.getHits());
        helper.itemView.setTag(R.id.tag_first, item.getID());
        helper.itemView.setTag(R.id.tag_second, item.getTitle());
        helper.itemView.setTag(R.id.tag_third, item.getPic());
        helper.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                iItemClick.playVideo(v, (int) v.getTag(R.id.tag_first), (String) v.getTag(R.id.tag_second), (String) v.getTag(R.id.tag_third));
            }
        });
    }

    public void setiItemClick(IItemClick iItemClick) {
        this.iItemClick = iItemClick;
    }

    public interface IItemClick {
        void playVideo(View v, int id, String title, String pic);
    }
}
