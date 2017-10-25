package com.wangxia.battle.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wangxia.battle.R;
import com.wangxia.battle.db.bean.ArticleBean;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.Mytime;
import com.wangxia.battle.util.RecycleItemDecortion;
import com.wangxia.battle.util.TxtFormatUtil;
import com.wangxia.battle.view.NoTouchRecycleView;

import java.util.Arrays;
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
    private String[] mAuthorName;

    public UserArticleAdapter(Context mContext, List<ArticleBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mAuthorName = mContext.getResources().getStringArray(R.array.author_name);

        inflater = LayoutInflater.from(mContext);
    }

    public void setiItemClick(IItemClick iItemClick){
        this.iItemClick = iItemClick;
    }

    @Override
    public UserArticleAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(inflater.inflate(R.layout.article_multi_item, parent, false));
    }

    @Override
    public void onBindViewHolder(UserArticleAdapter.Holder holder, int position) {
        ArticleBean itemsBean = mData.get(position);
        holder.tvTitle.setText(TxtFormatUtil.HtmlFormat(itemsBean.getTitle()));
        String pic = itemsBean.getIcon();
        if(!TextUtils.isEmpty(pic)){
            if(pic.contains(",")){
                holder.ivPic.setVisibility(View.GONE);
                holder.rlPics.setVisibility(View.VISIBLE);
                String[] split = pic.split(",");
                BigImgAdapter bigImgAdapter = new BigImgAdapter(mContext, Arrays.asList(split));
                holder.rlPics.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL,false));
                bigImgAdapter.setImageHeight(100);
                bigImgAdapter.setImageWidth(60);
                holder.rlPics.addItemDecoration(new RecycleItemDecortion.SpacesItemDecoration(10,0));
                holder.rlPics.setAdapter(bigImgAdapter);
                bigImgAdapter.setItemClick(new BigImgAdapter.IitemClick() {
                    @Override
                    public void getImgUrl(String url,int index) {
                        return;
                    }
                });
            }else {
                holder.rlPics.setVisibility(View.GONE);
                holder.ivPic.setVisibility(View.VISIBLE);
                holder.ivPic.setImageURI(pic);
            }
        }
        int index = position % Constant.number.TWElVE;
        holder.ivAuthorIco.setImageResource(Constant.getAuthorIcons().get(index));
        holder.tvAuthorName.setText(mAuthorName[index]);
        holder.tvHints.setText(String.valueOf(itemsBean.getHints()));
        holder.tvTime.setText(Mytime.getTwoDaysWords(itemsBean.getTime()));
        holder.itemView.setTag(R.id.tag_first,position);
        holder.rlPics.setTag(R.id.tag_first,position);

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener,NoTouchRecycleView.IViewActionDown{
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.iv_pic)
        SimpleDraweeView ivPic;
        @BindView(R.id.rl_pics)
        NoTouchRecycleView rlPics;
        @BindView(R.id.iv_author_ico)
        SimpleDraweeView ivAuthorIco;
        @BindView(R.id.tv_author_name)
        TextView tvAuthorName;
        @BindView(R.id.tv_hints)
        TextView tvHints;
        @BindView(R.id.tv_time)
        TextView tvTime;

        public Holder(View itemView) {
            super(itemView);
            mBind = ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
            rlPics.setOnActionDownListener(this);
        }

        @Override
        public void onClick(View v) {
            iItemClick.toArticleDetail((int)v.getTag(R.id.tag_first));
        }

        @Override
        public void viewActionDown(RecyclerView rl) {
            iItemClick.toArticleDetail((int)rl.getTag(R.id.tag_first));
        }
    }

    public interface IItemClick{
        void toArticleDetail(int position);
    }
}
