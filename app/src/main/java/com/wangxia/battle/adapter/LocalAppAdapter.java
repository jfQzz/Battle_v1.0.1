package com.wangxia.battle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wangxia.battle.R;
import com.wangxia.battle.model.bean.LocalAppBean;
import com.wangxia.battle.util.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 昝奥博 on 2017/9/18 0018
 * Email:18772833900@163.com
 * Explain：
 */
public class LocalAppAdapter extends RecyclerView.Adapter<LocalAppAdapter.Holder> {
    private Context mContext;
    private List<LocalAppBean> mData ;
    private LayoutInflater inflater;
    private Unbinder mBind;
    private IItemClick iItemClick;

    public LocalAppAdapter(Context mContext, List<LocalAppBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        inflater = LayoutInflater.from(mContext);
    }

    public void setItemClick(IItemClick iItemClick){
        this.iItemClick = iItemClick;
    }

    public void clearMemory(){
        if(null != mData){
            mData.clear();
        }
        if(null != mBind){
            mBind.unbind();
        }
        mContext = null;
        inflater = null;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(inflater.inflate(R.layout.local_game_item,parent,false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        LocalAppBean localAppBean = mData.get(position);
        holder.ivIco.setImageDrawable(localAppBean.getAppIcon());
        holder.tvTitle.setText(localAppBean.getAppName());
        holder.tvVersion.setText(localAppBean.getAppVersion());
        holder.btnOpenApp.setTag(R.id.tag_first,localAppBean.getPackagename());
    }

    @Override
    public int getItemCount() {
        return mData == null ? Constant.number.ZERO : mData.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.iv_ico)
        SimpleDraweeView ivIco;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_use_time)
        TextView tvUseTime;
        @BindView(R.id.tv_version)
        TextView tvVersion;
        @BindView(R.id.btn_open_app)
        Button btnOpenApp;

        public Holder(View itemView) {
            super(itemView);
            mBind = ButterKnife.bind(this,itemView);
            btnOpenApp.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iItemClick.openApp((String) v.getTag(R.id.tag_first));
        }
    }
    public interface IItemClick{
        void openApp(String packageName);
    }
}
