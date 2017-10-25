package com.wangxia.battle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wangxia.battle.R;
import com.wangxia.battle.model.bean.AppInfo;
import com.wangxia.battle.util.TxtFormatUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 昝奥博 on 2017/9/14 0014
 * Email:18772833900@163.com
 * Explain：更多游戏
 */
public class MoreGameAdapter extends RecyclerView.Adapter<MoreGameAdapter.Holder> {
    private List<AppInfo.AppListBean> mAppList;
    private Context mContext;
    private LayoutInflater inflater;
    private Unbinder mBind;
    private IItemClick iItemClick;

    public MoreGameAdapter( Context mContext,List<AppInfo.AppListBean> mAppList) {
        this.mAppList = mAppList;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    public void setItemClick(IItemClick itemClick){
        this.iItemClick = itemClick;
    }

    public void clearMemory(){
        if(null != mAppList){
            mAppList.clear();
            mAppList = null;
        }
        inflater = null;
        mContext = null;
        mBind.unbind();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(inflater.inflate(R.layout.app_more_item, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        AppInfo.AppListBean appListBean = mAppList.get(position);
        holder.ivAppIcon.setImageURI(appListBean.getImage());
        holder.tvAppTitle.setText(TxtFormatUtil.HtmlFormat(appListBean.getAppname()));
        holder.tvGameType.setText(TxtFormatUtil.HtmlFormat(appListBean.getCatalogname()));
        holder.tvGameSize.setText(TxtFormatUtil.formatNum(Integer.parseInt(appListBean.getSoftsize()))+"M");
        holder.itemView.setTag(R.id.tag_first,appListBean.getID());

    }

    @Override
    public int getItemCount() {
        return mAppList == null ? 0 : mAppList.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.iv_app_icon)
        SimpleDraweeView ivAppIcon;
        @BindView(R.id.tv_app_title)
        TextView tvAppTitle;
        @BindView(R.id.tv_game_type)
        TextView tvGameType;
        @BindView(R.id.tv_game_size)
        TextView tvGameSize;


        public Holder(View itemView) {
            super(itemView);
            mBind = ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iItemClick.getAppId((int)v.getTag(R.id.tag_first));

        }
    }

    public interface IItemClick{
        void getAppId(int id);
    }
}
