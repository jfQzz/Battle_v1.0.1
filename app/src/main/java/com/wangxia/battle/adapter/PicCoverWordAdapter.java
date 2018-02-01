package com.wangxia.battle.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wangxia.battle.R;
import com.wangxia.battle.globe.App;
import com.wangxia.battle.model.bean.ArmBean;
import com.wangxia.battle.model.bean.HeroBean;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.TxtFormatUtil;

import java.util.List;

/**
 * Created by 昝奥博 on 2018/1/10 0010
 * Email:18772833900@163.com
 * Explain：文字图片
 */
public class PicCoverWordAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    //0：武器 1：装备
    private int mType;
    private IOnItemClickListener iOnItemClickListener;
    public PicCoverWordAdapter(@Nullable List<String> data) {
        super(R.layout.pic_cover_word,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String nick) {
        String pic;
        if(Constant.number.ZERO == mType){
            HeroBean.DataBean dataBean = App.heroNameMap.get(nick);
            pic = TxtFormatUtil.getPic(dataBean.getU5f0fu795eu65b9u5934u50cf());

        }else {
            ArmBean.DataBean dataBean;
            try {
                dataBean = App.armIdMap.get(Integer.parseInt(nick));
                nick = dataBean.getU88c5u5907u540du79f0();
            }catch (NumberFormatException e){
                dataBean = App.armNameMap.get(nick);
            }
            pic = TxtFormatUtil.getPic(dataBean.getU56feu6807u8defu5f84());
        }
        ((SimpleDraweeView)helper.getView(R.id.iv_icon)).setImageURI(pic);
        helper.setText(R.id.tv_nick,nick);
        helper.itemView.setTag(R.id.tag_first,nick);
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnItemClickListener.itemClick(v.getTag(R.id.tag_first).toString());
            }
        });
    }
    public void setType(int type){
        this.mType = type;
    }
    public void initItemListener(IOnItemClickListener iOnItemClickListener){
        this.iOnItemClickListener = iOnItemClickListener;

    }
    public interface IOnItemClickListener{
        void itemClick(String nick);
    }

}
