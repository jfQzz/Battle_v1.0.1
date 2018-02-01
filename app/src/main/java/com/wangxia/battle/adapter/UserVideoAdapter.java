package com.wangxia.battle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wangxia.battle.R;
import com.wangxia.battle.db.bean.VideoBean;
import com.wangxia.battle.util.Mytime;
import com.wangxia.battle.util.TxtFormatUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 昝奥博 on 2017/12/28 0028
 * Email:18772833900@163.com
 * Explain：
 */
public class UserVideoAdapter  extends RecyclerView.Adapter<UserVideoAdapter.VideoHolder>{
    private Context mContext;
    private List<VideoBean> mData;
    private LayoutInflater inflater;
    private Unbinder mBind;
    private IItemClick iItemClick;

    public UserVideoAdapter(Context mContext, List<VideoBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        inflater = LayoutInflater.from(mContext);
    }


    public void setiItemClick(IItemClick iItemClick) {
        this.iItemClick = iItemClick;
    }

    public void clearMemory() {
        if (null != mData) {
            mData.clear();
            mData = null;
        }
        if (null != mBind) {
            mBind.unbind();
        }
        inflater = null;
        iItemClick = null;
        mContext = null;
    }

    @Override
    public UserVideoAdapter.VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new VideoHolder(inflater.inflate(R.layout.article_single_item, parent, false));
    }

    @Override
    public void onBindViewHolder(UserVideoAdapter.VideoHolder holder, int position) {
        VideoBean itemsBean = mData.get(position);
        holder.ivPic.setImageURI(itemsBean.getIcon());
        holder.tvTitle.setText(TxtFormatUtil.HtmlFormat(itemsBean.getTitle()));
        holder.tvDesc.setText(TxtFormatUtil.HtmlFormat(itemsBean.getDesc()));
        holder.tvTime.setText(Mytime.getTwoDaysWords(itemsBean.getTime()));
        holder.tvHints.setText(String.valueOf(itemsBean.getHints()));
        holder.itemView.setTag(R.id.tag_first, itemsBean.getId());
        holder.itemView.setTag(R.id.tag_second, itemsBean.getTitle());
        holder.itemView.setTag(R.id.tag_third, itemsBean.getIcon());

    }


    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class VideoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_pic)
        SimpleDraweeView ivPic;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_desc)
        TextView tvDesc;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_times)
        TextView tvHints;
        public VideoHolder(View itemView) {
            super(itemView);
            mBind = ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iItemClick.playVideo(v,(int) v.getTag(R.id.tag_first), (String) v.getTag(R.id.tag_second), (String) v.getTag(R.id.tag_third));
        }
    }

    public interface IItemClick {
        void playVideo(View v,int id, String title, String pic);
    }
}
