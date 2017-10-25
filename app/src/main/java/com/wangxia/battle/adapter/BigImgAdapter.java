package com.wangxia.battle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wangxia.battle.R;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.DensityUtil;
import com.wangxia.battle.util.ImageUtil;

import java.util.List;

/**
 * Created by 昝奥博 on 2017/9/14 0014
 * Email:18772833900@163.com
 * Explain：
 */
public class BigImgAdapter extends RecyclerView.Adapter<BigImgAdapter.Holder> {
    private List<String> mData;
    private Context mContext;
    private LayoutInflater inflater;
    private IitemClick iItemClick;
    private int mHeight;
    private int mWidth;
    //默认不宽度固定，高度自适应
    private int mMode = Constant.number.ZERO;

    public BigImgAdapter(Context mContext, List<String> mData) {
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

    public void setShowMode(int mode) {
        this.mMode = mode;
    }

    @Override
    public BigImgAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(inflater.inflate(R.layout.big_img_item, parent, false));
    }

    @Override
    public void onBindViewHolder(BigImgAdapter.Holder holder, int position) {
        String url = mData.get(position);
        if (Constant.number.ZERO == mMode) {
            holder.iv_icons.setImageURI(url);
        } else if (Constant.number.ONE == mMode) {
            ImageUtil.fitHeight(holder.iv_icons, url);
        }
        holder.itemView.setTag(R.id.tag_first, position);

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
    }

    public interface IitemClick {
        void getImgUrl(String url, int index);
    }

}
