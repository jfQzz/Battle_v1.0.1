package com.wangxia.battle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wangxia.battle.R;
import com.wangxia.battle.util.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 昝奥博 on 2017/10/17 0017
 * Email:18772833900@163.com
 * Explain：
 */
public class FunctionBaseAdapter extends BaseAdapter {
    private Context mContext;
    private String[] mFunctionName;
    private int[] mFunctionDrawable;
    private LayoutInflater inflater;
    private Unbinder mBind;
    private IItemClickListener iItemClickListener;

    public FunctionBaseAdapter(Context context, String[] mFunctionName, int[] mFunctionDrawable) {
        this.mContext = context;
        this.mFunctionName = mFunctionName;
        this.mFunctionDrawable = mFunctionDrawable;
        inflater = LayoutInflater.from(context);
    }

    public void setItemClick(IItemClickListener iItemClickListener){
        this.iItemClickListener = iItemClickListener;

    }

    public void clearMemory(){
        if(null != mBind){
            mBind.unbind();
        }
        mFunctionName = null;
        mFunctionDrawable = null;
    }
    @Override
    public int getCount() {
        return mFunctionDrawable.length == mFunctionName.length ? mFunctionDrawable.length : Constant.number.ZERO;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder;
        if(null == view){
            view = inflater.inflate(R.layout.function_type_item, parent, false);
            holder = new Holder(view);
        }else {
            holder = (Holder) view.getTag();
        }
        holder.ivTypeBg.setImageResource(R.drawable.game_bride_ico);
        holder.tvTypeName.setText(mFunctionName[position]);
        holder.itemView.setTag(R.id.tag_first,position);
        return view;
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.iv_type_bg)
        SimpleDraweeView ivTypeBg;
        @BindView(R.id.tv_type_name)
        TextView tvTypeName;

        public Holder(View itemView) {
            super(itemView);
            itemView.setTag(this);
            mBind = ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iItemClickListener.itemClick((int)v.getTag(R.id.tag_first));
        }
    }

    public interface IItemClickListener {
        void itemClick(int position);
    }
}
