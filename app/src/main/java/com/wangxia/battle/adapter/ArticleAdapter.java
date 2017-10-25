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
import com.wangxia.battle.model.bean.ArticleList;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.DensityUtil;
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
 * Explain：文章列表
 */
public class ArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<ArticleList.ItemsBean> mData;
    private LayoutInflater inflater;
    private Unbinder mBind;
    private IItemClick iItemClick;
    private String[] mAuthorName;
    private int type;
    private int width;
    public ArticleAdapter(Context mContext, List<ArticleList.ItemsBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mAuthorName = mContext.getResources().getStringArray(R.array.author_name);

        inflater = LayoutInflater.from(mContext);
    }

    /**
     * type = 0默认的多图布局 ， type = 1 单图布局
     *
     * @param type
     */
    public void setType(int type) {
        this.type = type;

    }

    public void setPicWidth(int width){
        this.width = width;

    }

    public void setiItemClick(IItemClick iItemClick) {
        this.iItemClick = iItemClick;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (Constant.number.ZERO == type) {
            return new MultiHolder(inflater.inflate(R.layout.article_multi_item, parent, false));
        }
        return new SingleArticle(inflater.inflate(R.layout.article_single_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ArticleList.ItemsBean itemsBean = mData.get(position);
        if (Constant.number.ZERO == type) {
            MultiHolder multiHolder = (MultiHolder) holder;
            multiHolder.tvTitle.setText(TxtFormatUtil.HtmlFormat(itemsBean.getTitle()));
            String pic = itemsBean.getPiclist();
            if(!TextUtils.isEmpty(pic)){
                if (pic.contains(",")) {
                    multiHolder.ivPic.setVisibility(View.GONE);
                    multiHolder.rlPics.setVisibility(View.VISIBLE);
                    String[] split = pic.split(",");
                    BigImgAdapter bigImgAdapter = new BigImgAdapter(mContext, Arrays.asList(split));
                    bigImgAdapter.setImageHeight(60);
                    bigImgAdapter.setImageWidth(100);
                    multiHolder.rlPics.setAdapter(bigImgAdapter);
                    bigImgAdapter.setItemClick(new BigImgAdapter.IitemClick() {
                        @Override
                        public void getImgUrl(String url, int index) {
                            return;
                        }
                    });
                } else {
                    pic = itemsBean.getPic();
                    multiHolder.rlPics.setVisibility(View.GONE);
                    multiHolder.ivPic.setVisibility(View.VISIBLE);
                    multiHolder.ivPic.setImageURI(pic);
                }
            }
            int index = position % Constant.number.TWElVE;
            multiHolder.ivAuthorIco.setImageResource(Constant.getAuthorIcons().get(index));
            multiHolder.tvAuthorName.setText(mAuthorName[index]);
            multiHolder.tvHints.setText(itemsBean.getHits());
            multiHolder.tvTime.setText(Mytime.getTwoDaysWords(itemsBean.getTime()));
            multiHolder.rlPics.setTag(R.id.tag_first, position);
        } else {
//            RotationOptions rotationOptions = RotationOptions.forceRotation(RotationOptions.ROTATE_90);
//            ImageRequest build = ImageRequestBuilder.newBuilderWithSource(Uri.parse(itemsBean.getPic()))
//                    .setRotationOptions(rotationOptions)
//                    .build();
//            PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
//                    .setImageRequest(build)
//                    .build();
            SingleArticle singleArticle = (SingleArticle) holder;
            singleArticle.ivPic.setImageURI(itemsBean.getPic());
            singleArticle.tvTitle.setText(TxtFormatUtil.HtmlFormat(itemsBean.getTitle()));
            singleArticle.tvDesc.setText(TxtFormatUtil.HtmlFormat(itemsBean.getDesc()));
            singleArticle.tvTime.setText(Mytime.getTwoDaysWords(itemsBean.getTime()));
            singleArticle.tvTimes.setText(itemsBean.getHits());
        }
        holder.itemView.setTag(R.id.tag_first, position);

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class MultiHolder extends RecyclerView.ViewHolder implements View.OnClickListener, NoTouchRecycleView.IViewActionDown {
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

        public MultiHolder(View itemView) {
            super(itemView);
            mBind = ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            rlPics.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            rlPics.addItemDecoration(new RecycleItemDecortion.SpacesItemDecoration(Constant.number.TEN, Constant.number.ZERO, Constant.number.ZERO));
            rlPics.setOnActionDownListener(this);
        }

        @Override
        public void onClick(View v) {
            iItemClick.toArticleDetail((int) v.getTag(R.id.tag_first));

        }

        @Override
        public void viewActionDown(RecyclerView view) {
            iItemClick.toArticleDetail((int) view.getTag(R.id.tag_first));
        }
    }

    public interface IItemClick {
        void toArticleDetail(int position);
    }

    public class SingleArticle extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_pic)
        SimpleDraweeView ivPic;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_desc)
        TextView tvDesc;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_times)
        TextView tvTimes;
        public SingleArticle(View itemView) {
            super(itemView);
            mBind = ButterKnife.bind(this, itemView);
            if(0 != width){
                ivPic.getLayoutParams().width = DensityUtil.dip2px(width);
                ivPic.requestLayout();
            }
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iItemClick.toArticleDetail((int) v.getTag(R.id.tag_first));

        }
    }
}
