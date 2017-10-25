package com.wangxia.battle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wangxia.battle.R;
import com.wangxia.battle.db.bean.GameBean;
import com.wangxia.battle.model.bean.AppInfoList;
import com.wangxia.battle.util.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 昝奥博 on 2017/9/13 0013
 * Email:18772833900@163.com
 * Explain： 收藏游戏或者浏览游戏
 */
public class UserAppAdapter extends RecyclerView.Adapter<UserAppAdapter.Holder> {
    private Context mContext;
    private List<GameBean> mData;
    private LayoutInflater inflater;
    private ItemClick itemClick;

    public UserAppAdapter(Context mContext, List<GameBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        inflater = LayoutInflater.from(mContext);
    }

    public void initItemClick(ItemClick itemClick){
        this.itemClick = itemClick;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(inflater.inflate(R.layout.appinfo_item, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        GameBean itemsBean = mData.get(position);
        holder.ivAppPic.setImageURI(itemsBean.getIcon());
        holder.tvAppName.setText(Html.fromHtml(itemsBean.getTitle()));
        String labels = itemsBean.getLabels().replaceAll(Constant.string.COMMA_SEPARATOR,"\t");
        String label = String.valueOf(itemsBean.getSize() / 1024+"M\t\t");
        if(!TextUtils.isEmpty(labels)){
            label += labels;
        }
        holder.tvAppRemark.setText(label);
        holder.tvAppDesc.setText(Html.fromHtml(itemsBean.getMark()));
        holder.itemView.setTag(R.id.tag_first,itemsBean.getId());
        holder.rlAppDownload.setTag(R.id.tag_first,itemsBean);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.iv_app_pic)
        SimpleDraweeView ivAppPic;
        @BindView(R.id.tv_app_name)
        TextView tvAppName;
        @BindView(R.id.tv_app_remark)
        TextView tvAppRemark;
        @BindView(R.id.tv_app_desc)
        TextView tvAppDesc;
        @BindView(R.id.pb_app_rate)
        ProgressBar pbAppRate;
        @BindView(R.id.tv_app_rate)
        TextView tvAppRate;
        @BindView(R.id.rl_app_download)
        RelativeLayout rlAppDownload;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            rlAppDownload.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.rl_app_download:
                    itemClick.downloadApp((AppInfoList.ItemsBean)v.getTag(R.id.tag_first));
                    break;
                default:
                    itemClick.toAppDetail((int)v.getTag(R.id.tag_first));
                    break;
            }
        }
    }

    public interface ItemClick{
        void toAppDetail(int id);
        void downloadApp(AppInfoList.ItemsBean itemsBean);
    }
}
