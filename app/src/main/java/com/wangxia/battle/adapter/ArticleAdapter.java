package com.wangxia.battle.adapter;

import android.graphics.Color;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wangxia.battle.R;
import com.wangxia.battle.globe.App;
import com.wangxia.battle.model.bean.ArticleList;
import com.wangxia.battle.util.Mytime;
import com.wangxia.battle.util.TxtFormatUtil;

/**
 * Created by 昝奥博 on 2017/9/15 0015
 * Email:18772833900@163.com
 * Explain：文章列表
 */
public class ArticleAdapter extends BaseQuickAdapter<ArticleList.ItemsBean, BaseViewHolder> {
    private IItemClick iItemClick;
    public ArticleAdapter() {
        super(R.layout.article_single_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleList.ItemsBean item) {
        ((SimpleDraweeView)helper.getView(R.id.iv_pic)).setImageURI(item.getPic());
        helper.setText(R.id.tv_title,TxtFormatUtil.HtmlFormat(item.getSubtitle()));
        helper.setText(R.id.tv_desc,TxtFormatUtil.HtmlFormat(item.getDesc()));
        helper.setText(R.id.tv_time,Mytime.getTwoDaysWords(item.getTime()));
        helper.setText(R.id.tv_times,item.getHits());
        helper.itemView.setTag(R.id.tag_first, helper.getAdapterPosition());
        if(App.mReaderManager.isBrowseArticleById(item.getID()))
            helper.setTextColor(R.id.tv_title, Color.parseColor("#82868c"));
        else
            helper.setTextColor(R.id.tv_title, Color.parseColor("#4a4a4a"));
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iItemClick.toArticleDetail((int) v.getTag(R.id.tag_first));
            }
        });
    }

    public void setiItemClick(IItemClick iItemClick) {
        this.iItemClick = iItemClick;
    }

    public interface IItemClick {
        void toArticleDetail(int position);
    }

}
