package com.wangxia.battle.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wangxia.battle.R;
import com.wangxia.battle.model.bean.VideoList;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.RecycleItemDecortion;
import com.wangxia.battle.util.TxtFormatUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 昝奥博 on 2017/9/16 0016
 * Email:18772833900@163.com
 * Explain：
 */
public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<VideoList.ItemListBeanX> mData;
    private LayoutInflater inflater;
    private Unbinder mBind;
    private IItemClick iItemClick;

    public VideoAdapter(Context mContext, List<VideoList.ItemListBeanX> mData) {
        this.mContext = mContext;
        this.mData = mData;
        inflater = LayoutInflater.from(mContext);
    }



    public void setiItemClick(IItemClick iItemClick) {
        this.iItemClick = iItemClick;
    }

    public void clearMemory(){
        if(null != mData){
            mData.clear();
            mData =null;
        }
        if(null != mBind){
            mBind.unbind();
        }
        inflater = null;
        iItemClick = null;
        mContext = null;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case Constant.number.ZERO:
                holder = new VideoHolder(inflater.inflate(R.layout.video_item, parent, false));
                break;
            case Constant.number.ONE:
                holder = new TitleHolder(inflater.inflate(R.layout.video_title_item, parent, false));
                break;
            case Constant.number.TWO:
                holder = new BannerHolder(inflater.inflate(R.layout.video_ad_item, parent, false));
                break;
            case Constant.number.THREE:
                holder = new CollectionHolder(inflater.inflate(R.layout.video_collection_item, parent, false));
                break;
        }
        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VideoList.ItemListBeanX itemListBean = mData.get(position);
        if (holder instanceof VideoHolder) {
            VideoHolder videoHolder = (VideoHolder) holder;
            VideoList.ItemListBeanX.DataBeanX data = itemListBean.getData();
            String feed = data.getCover().getFeed();
            videoHolder.ivVideoPic.setImageURI(feed);
            videoHolder.ivVideoPic.setTag(R.id.tag_first, data.getPlayUrl());
            videoHolder.ivVideoPic.setTag(R.id.tag_second, data.getTitle());
            videoHolder.ivVideoPic.setTag(R.id.tag_third, data.getDescription());
            videoHolder.tvVideoTitle.setText(TxtFormatUtil.HtmlFormat(data.getTitle()));
            videoHolder.tvVideoDesc.setText(TxtFormatUtil.HtmlFormat(data.getSlogan()));
            VideoList.ItemListBeanX.DataBeanX.AuthorBean author = data.getAuthor();
            VideoList.ItemListBeanX.DataBeanX.ProviderBean provider = data.getProvider();
            if (null != author) {
                videoHolder.ivAuthorIco.setImageURI(author.getIcon());
            } else if (null != provider) {
                videoHolder.ivAuthorIco.setImageURI(provider.getIcon());

            }
        } else if (holder instanceof TitleHolder) {
            TitleHolder titleHolder = (TitleHolder) holder;
            titleHolder.tv_title.setText(TxtFormatUtil.HtmlFormat(itemListBean.getData().getText()));
        } else if (holder instanceof BannerHolder) {
            BannerHolder bannerHolder = (BannerHolder) holder;
            VideoList.ItemListBeanX.DataBeanX data = itemListBean.getData();
            String image = data.getImage();
            if (!TextUtils.isEmpty(image)) {
                bannerHolder.ivVideoPic.setImageURI(image);
            }
            VideoList.ItemListBeanX.DataBeanX.HeaderBean header = data.getHeader();
            bannerHolder.ivAuthorIco.setImageURI(header.getIcon());
            bannerHolder.tvVideoTitle.setText(TxtFormatUtil.HtmlFormat(header.getTitle()));
            bannerHolder.tvVideoDesc.setText(TxtFormatUtil.HtmlFormat(header.getDescription()));
        } else if (holder instanceof CollectionHolder) {
            CollectionHolder collectionHolder = (CollectionHolder) holder;
            VideoList.ItemListBeanX.DataBeanX data = itemListBean.getData();
            VideoList.ItemListBeanX.DataBeanX.HeaderBean header = data.getHeader();
            collectionHolder.iv_cover.setImageURI(header.getCover());
            String playUrl = data.getPlayUrl();
            if (TextUtils.isEmpty(playUrl)) {
                playUrl = Constant.string.VIDEO_DEFAULT;
            }
            collectionHolder.iv_cover.setTag(R.id.tag_first, playUrl);
            String title = data.getTitle();
            if (TextUtils.isEmpty(data.getTitle())) {
                title = header.getTitle();
                if (TextUtils.isEmpty(title)) {
                    title = "推荐视频,视频已损坏";
                }
            }
            collectionHolder.iv_cover.setTag(R.id.tag_second, title);

            String description = data.getDescription();
            if (TextUtils.isEmpty(description)) {
                description = header.getDescription();
                if (TextUtils.isEmpty(description)) {
                    description = "暂无视频简介";
                }
            }
            collectionHolder.iv_cover.setTag(R.id.tag_third, description);

            HorizontalVideoImgAdapter horizontalVideoImgAdapter = new HorizontalVideoImgAdapter(mContext, data.getItemList());
            horizontalVideoImgAdapter.setImageHeight(100);
            horizontalVideoImgAdapter.setImageWidth(200);
            collectionHolder.rl_collection.setAdapter(horizontalVideoImgAdapter);
            horizontalVideoImgAdapter.setItemClick(new HorizontalVideoImgAdapter.IitemClick() {
                @Override
                public void getImgUrl(String url, String title, String desc) {
                    iItemClick.playVideo(url, title, desc);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        String dataType = mData.get(position).getType();
        int result = Constant.number.ZERO;
        switch (dataType) {
            case Constant.string.VIDEO_TYPE_VIDEO:
                result = Constant.number.ZERO;
                break;
            case Constant.string.VIDEO_TYPE_TITLE_HEAD:
                result = Constant.number.ONE;
                break;
            case Constant.string.VIDEO_TYPE_TITLE_FOOT:
                result = Constant.number.ONE;
                break;
            case Constant.string.VIDEO_TYPE_BANNER:
                result = Constant.number.TWO;
                break;
            case Constant.string.VIDEO_TYPE_VIDEO_COLLECTION:
                result = Constant.number.THREE;
                break;
            case Constant.string.VIDEO_TYPE_VIDEO_FOLLOW:
                result = Constant.number.THREE;
                break;
        }
        return result;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class VideoHolder extends RecyclerView.ViewHolder implements OnClickListener {
        @BindView(R.id.iv_video_pic)
        SimpleDraweeView ivVideoPic;
        @BindView(R.id.iv_author_ico)
        SimpleDraweeView ivAuthorIco;
        @BindView(R.id.tv_video_title)
        TextView tvVideoTitle;
        @BindView(R.id.tv_video_desc)
        TextView tvVideoDesc;

        public VideoHolder(View itemView) {
            super(itemView);
            mBind = ButterKnife.bind(this, itemView);
            ivVideoPic.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iItemClick.playVideo((String) v.getTag(R.id.tag_first), (String) v.getTag(R.id.tag_second), (String) v.getTag(R.id.tag_third));
        }
    }

    public class TitleHolder extends RecyclerView.ViewHolder {
        private TextView tv_title;

        public TitleHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

    public class BannerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_video_pic)
        SimpleDraweeView ivVideoPic;
        @BindView(R.id.iv_author_ico)
        SimpleDraweeView ivAuthorIco;
        @BindView(R.id.tv_video_title)
        TextView tvVideoTitle;
        @BindView(R.id.tv_video_desc)
        TextView tvVideoDesc;

        public BannerHolder(View itemView) {
            super(itemView);
            mBind = ButterKnife.bind(this, itemView);
        }
    }

    public class CollectionHolder extends RecyclerView.ViewHolder implements OnClickListener {
        private SimpleDraweeView iv_cover;
        private RecyclerView rl_collection;

        public CollectionHolder(View itemView) {
            super(itemView);
            iv_cover = (SimpleDraweeView) itemView.findViewById(R.id.iv_cover);
            rl_collection = (RecyclerView) itemView.findViewById(R.id.rl_collections);
            rl_collection.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            rl_collection.addItemDecoration(new RecycleItemDecortion.SpacesItemDecoration(15, 0));
            iv_cover.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iItemClick.playVideo((String) v.getTag(R.id.tag_first), (String) v.getTag(R.id.tag_second), (String) v.getTag(R.id.tag_third));
        }
    }

    public interface IItemClick {
        void playVideo(String videoUrl, String title, String desc);
    }
}
