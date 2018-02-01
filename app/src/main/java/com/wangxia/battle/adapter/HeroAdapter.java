package com.wangxia.battle.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wangxia.battle.R;
import com.wangxia.battle.model.bean.HeroBean;
import com.wangxia.battle.util.TxtFormatUtil;

import java.util.List;

/**
 * Created by 昝奥博 on 2017/9/26 0026
 * Email:18772833900@163.com
 * Explain：
 */
public class HeroAdapter extends BaseQuickAdapter<HeroBean.DataBean, BaseViewHolder> {
    private IItemClickListener iItemClickListener;
    public HeroAdapter(List<HeroBean.DataBean> dataBean) {
        super(R.layout.function_type_item,dataBean);
    }

    public void setItemClick(IItemClickListener iItemClickListener) {
        this.iItemClickListener = iItemClickListener;

    }
    @Override
    protected void convert(BaseViewHolder helper, HeroBean.DataBean item) {
        ((SimpleDraweeView) helper.getView(R.id.iv_type_bg)).setImageURI(TxtFormatUtil.getPic(item.getU5f0fu795eu65b9u5934u50cf()));
        helper.setText(R.id.tv_type_name, TxtFormatUtil.HtmlFormat(item.getU5f0fu795eu540du79f0()));
        helper.itemView.setTag(R.id.tag_first, helper.getLayoutPosition());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iItemClickListener.itemClick((int) v.getTag(R.id.tag_first));
            }
        });
    }

    public interface IItemClickListener {
        void itemClick(int position);
    }
}
