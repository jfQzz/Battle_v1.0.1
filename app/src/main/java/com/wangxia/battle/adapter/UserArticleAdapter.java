package com.wangxia.battle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wangxia.battle.R;
import com.wangxia.battle.db.bean.ArticleBean;
import com.wangxia.battle.util.Mytime;
import com.wangxia.battle.util.TxtFormatUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 昝奥博 on 2017/9/15 0015
 * Email:18772833900@163.com
 * Explain：收藏文章或者浏览文章
 */
public class UserArticleAdapter extends RecyclerView.Adapter<UserArticleAdapter.Holder> {
    private Context mContext;
    private List<ArticleBean> mData;
    private LayoutInflater inflater;
    private Unbinder mBind;
    private IItemClick iItemClick;

    public UserArticleAdapter(Context mContext, List<ArticleBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        inflater = LayoutInflater.from(mContext);
    }

    public void setiItemClick(IItemClick iItemClick) {
        this.iItemClick = iItemClick;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(inflater.inflate(R.layout.article_single_item, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ArticleBean itemsBean = mData.get(position);
        holder.ivPic.setImageURI(itemsBean.getIcon());
        holder.tvTitle.setText(TxtFormatUtil.HtmlFormat(itemsBean.getTitle()));
        holder.tvDesc.setText(TxtFormatUtil.HtmlFormat(itemsBean.getDesc()));
        holder.tvHints.setText(String.valueOf(itemsBean.getHints()));
        holder.tvTime.setText(Mytime.getTwoDaysWords(itemsBean.getTime()));
        holder.itemView.setTag(R.id.tag_first, position);;

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

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

        public Holder(View itemView) {
            super(itemView);
            mBind = ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            iItemClick.toArticleDetail((int) v.getTag(R.id.tag_first));
        }

    }

    public interface IItemClick {
        void toArticleDetail(int position);
    }
}
