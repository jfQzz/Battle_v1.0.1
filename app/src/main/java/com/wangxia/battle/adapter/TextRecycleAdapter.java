package com.wangxia.battle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 昝奥博 on 2017/7/23 0023
 * Email:18772833900@163.com
 * Explain：
 */
public class TextRecycleAdapter extends RecyclerView.Adapter<TextRecycleAdapter.Holder> {
    private Context mContext;
    private List<String> mDatas;
    private LayoutInflater inflater;

    public TextRecycleAdapter(Context mContext, List<String> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(inflater.inflate(android.R.layout.simple_list_item_1,parent,false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        String s = mDatas.get(position);
        holder.txt.setText(s);

    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView txt;
        public Holder(View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}
