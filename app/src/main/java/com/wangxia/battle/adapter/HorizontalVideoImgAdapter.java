package com.wangxia.battle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wangxia.battle.R;
import com.wangxia.battle.model.bean.VideoList;
import com.wangxia.battle.util.DensityUtil;

import java.util.List;

/**
 * Created by 昝奥博 on 2017/9/16 0016
 * Email:18772833900@163.com
 * Explain：
 */
public class HorizontalVideoImgAdapter extends RecyclerView.Adapter<HorizontalVideoImgAdapter.Holder> {
    private List<VideoList.ItemListBeanX.DataBeanX.ItemListBean> mData;
    private Context mContext;
    private LayoutInflater inflater;
    private IitemClick iItemClick;
    private int mHeight;
    private int mWidth;

    public HorizontalVideoImgAdapter(Context mContext, List<VideoList.ItemListBeanX.DataBeanX.ItemListBean> mData) {
        this.mData = mData;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    public void setItemClick(IitemClick itemClick) {
        this.iItemClick = itemClick;
    }

    public void setImageHeight(int height) {
        this.mHeight = height;

    }

    public void setImageWidth(int width) {
        this.mWidth = width;

    }

    @Override
    public HorizontalVideoImgAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(inflater.inflate(R.layout.big_img_item, parent, false));
    }

    @Override
    public void onBindViewHolder(HorizontalVideoImgAdapter.Holder holder, int position) {
        VideoList.ItemListBeanX.DataBeanX.ItemListBean.DataBean data = mData.get(position).getData();
        String url = data.getCover().getFeed();
        if(!TextUtils.isEmpty(url)){
            holder.iv_icons.setImageURI(url);
        }
        holder.itemView.setTag(R.id.tag_first, data.getPlayUrl());
        holder.itemView.setTag(R.id.tag_second, data.getTitle());
        holder.itemView.setTag(R.id.tag_third, data.getDescription());

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private SimpleDraweeView iv_icons;

        public Holder(View itemView) {
            super(itemView);
            iv_icons = (SimpleDraweeView) itemView.findViewById(R.id.iv_icon);
            if (0 != mHeight) {
                iv_icons.getLayoutParams().height = DensityUtil.dip2px(mHeight);
                iv_icons.requestLayout();
            }
            if (0 != mWidth) {
                iv_icons.getLayoutParams().width = DensityUtil.dip2px(mWidth);
                iv_icons.requestLayout();
            }
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iItemClick.getImgUrl((String) v.getTag(R.id.tag_first),(String) v.getTag(R.id.tag_second),(String) v.getTag(R.id.tag_third));

        }
    }

    public interface IitemClick {
        void getImgUrl(String videoUrl, String title, String desc);
    }
}
