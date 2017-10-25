package com.wangxia.battle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wangxia.battle.R;
import com.wangxia.battle.model.bean.ArticleList;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.TxtFormatUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 昝奥博 on 2017/9/26 0026
 * Email:18772833900@163.com
 * Explain：
 */
public class FunctionTypeAdapter extends RecyclerView.Adapter<FunctionTypeAdapter.Holder> {
    private Context mContext;
    private LayoutInflater inflater;
    private Unbinder mBind;
    private IItemClickListener iItemClickListener;
    private List<ArticleList.ItemsBean> mData;

    public FunctionTypeAdapter(Context context, List<ArticleList.ItemsBean> mData) {
        this.mContext = context;
        this.mData = mData;
        inflater = LayoutInflater.from(context);
    }

    public void setItemClick(IItemClickListener iItemClickListener) {
        this.iItemClickListener = iItemClickListener;

    }

    public void clearMemory() {
        if (null != mBind) {
            mBind.unbind();
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(inflater.inflate(R.layout.function_type_item, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ArticleList.ItemsBean itemsBean = mData.get(position);
        holder.ivTypeBg.setImageURI(itemsBean.getPic());
        holder.tvTypeName.setText(TxtFormatUtil.HtmlFormat(itemsBean.getExttitle()));
        holder.itemView.setTag(R.id.tag_first, position);
    }

    @Override
    public int getItemCount() {

        return mData == null ? Constant.number.ZERO : mData.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_type_bg)
        SimpleDraweeView ivTypeBg;
        @BindView(R.id.tv_type_name)
        TextView tvTypeName;

        public Holder(View itemView) {
            super(itemView);
            mBind = ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iItemClickListener.itemClick((int) v.getTag(R.id.tag_first));
        }
    }

    public interface IItemClickListener {
        void itemClick(int position);
    }
}
