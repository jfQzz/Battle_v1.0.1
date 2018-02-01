package com.wangxia.battle.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wangxia.battle.R;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.DensityUtil;

import java.util.List;

/**
 * Created by 昝奥博 on 2017/9/14 0014
 * Email:18772833900@163.com
 * Explain：
 */
public class BigImgAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private List<String> mData;
    private IitemClick iItemClick;
    private int mHeight;
    private int mWidth;
    //默认宽度固定，高度自适应
    private int mMode = Constant.number.ZERO;

    public BigImgAdapter(@Nullable List<String> data) {
        super(R.layout.big_img_item, data);
        this.mData = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, String url) {
        SimpleDraweeView img = helper.getView(R.id.iv_icon);
        if (0 != mHeight) {
            img.getLayoutParams().height = DensityUtil.dip2px(mHeight);
            img.requestLayout();
        }
        if (0 != mWidth) {
            img.getLayoutParams().width = DensityUtil.dip2px(mWidth);
            img.requestLayout();
        }
        if (Constant.number.ZERO == mMode) {
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else if (Constant.number.ONE == mMode) {
            img.setScaleType(ImageView.ScaleType.CENTER);
        }
            img.setImageURI(url);
        helper.itemView.setTag(R.id.tag_first, helper.getLayoutPosition());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder icons = new StringBuilder();
                for (int i = 0, count = mData.size(); i < count; i++) {
                    if (i != (count - Constant.number.ONE)) {
                        icons.append(mData.get(i) + Constant.string.COMMA_SEPARATOR);
                    } else {
                        icons.append(mData.get(i));
                    }
                }
                iItemClick.getImgUrl(icons.toString(), (int) v.getTag(R.id.tag_first));
            }
        });
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

    public void setShowMode(int mode) {
        this.mMode = mode;
    }


    public interface IitemClick {
        void getImgUrl(String url, int index);
    }

}
