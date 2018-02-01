package com.wangxia.battle.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wangxia.battle.R;
import com.wangxia.battle.model.bean.EvaluateBean;
import com.wangxia.battle.model.bean.VideoDetailBean;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.Mytime;
import com.wangxia.battle.util.SpUtil;
import com.wangxia.battle.util.TxtFormatUtil;
import com.wangxia.battle.view.CustomListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 昝奥博 on 2017/9/15 0015
 * Email:18772833900@163.com
 * Explain：游戏评论
 */
public class EvaluateWithHeadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<EvaluateBean.ItemsBean> mData;
    private VideoDetailBean videoDetailBean;
    private LayoutInflater inflater;
    private IUserHandle iUserHandle;

    public EvaluateWithHeadAdapter(Context mContext, List<EvaluateBean.ItemsBean> mData, VideoDetailBean videoDetailBean,IUserHandle iUserHandle) {
        this.mContext = mContext;
        this.mData = mData;
        this.videoDetailBean = videoDetailBean;
        this.iUserHandle = iUserHandle;
        inflater = LayoutInflater.from(mContext);
    }



    public List<EvaluateBean.ItemsBean> getData(){
        return mData;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == Constant.number.ZERO){
            return Constant.number.ZERO;
        }
        return Constant.number.ONE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(Constant.number.ZERO == viewType){
            return new TitleHolder(inflater.inflate(R.layout.video_head, parent, false));
        }else {
            return new Holder(inflater.inflate(R.layout.evaluate_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if(Constant.number.ZERO == getItemViewType(position)){
            TitleHolder titleHolder = (TitleHolder) viewHolder;
            titleHolder.tvVideoTitle.setText(TxtFormatUtil.HtmlFormat(videoDetailBean.getTitle()));
            titleHolder.tvVideoDesc.setText(TxtFormatUtil.HtmlFormat(videoDetailBean.getText()));
            titleHolder.ivUser.setImageURI(SpUtil.getString(mContext, Constant.userInfo.USER_ICON,null));
        }else {
            Holder holder = (Holder) viewHolder;
            EvaluateBean.ItemsBean itemsBean = mData.get(position);
            String userPic = TextUtils.isEmpty(itemsBean.getUserPic()) ? itemsBean.getIcon():itemsBean.getUserPic();
            if (TextUtils.isEmpty(userPic))
                holder.ivUserIcon.setImageResource(R.drawable.ic_user_icon_empty);
             else
                holder.ivUserIcon.setImageURI(userPic);

            if(itemsBean.isDig()){
                holder.ivNiceIc.setColorFilter(ContextCompat.getColor(mContext, R.color.colorAccent));
                holder.tvNiceCount.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
            }else {
                holder.ivNiceIc.setColorFilter(ContextCompat.getColor(mContext, R.color.colorTxtSub));
                holder.tvNiceCount.setTextColor(mContext.getResources().getColor(R.color.colorTxtSub));
            }
            holder.tvUserName.setText(getName(TxtFormatUtil.HtmlFormat(itemsBean.getUser()), itemsBean.getID()));
            setCityPhoneTime(itemsBean.getUserCity(), itemsBean.getPhone(), itemsBean.getPalyMinute(), itemsBean.isUserSex());
            holder.tvUserMark.setText(setCityPhoneTime(itemsBean.getUserCity(), itemsBean.getPhone(), itemsBean.getPalyMinute(), itemsBean.isUserSex()));
            holder.lvReplay.setAdapter(new ReplayAdapter(mContext, itemsBean.getRe(), new ReplayAdapter.IReplayComment() {
                @Override
                public void success(int id, String userName) {
                    iUserHandle.replay(id,userName);
                }
            }));
            holder.tvNiceCount.setText(itemsBean.getGood());
            holder.tvCommentDesc.setText(TxtFormatUtil.HtmlFormat(itemsBean.getContent()));
            holder.tvCommentTime.setText(Mytime.getTwoDaysWords(itemsBean.getTime()));
            holder.llNice.setTag(R.id.tag_first,position);
            holder.ivReplay.setTag(R.id.tag_first,itemsBean.getID());
            holder.ivReplay.setTag(R.id.tag_second,Html.fromHtml(itemsBean.getUser()));

        }

    }


    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.iv_user_icon)
        SimpleDraweeView ivUserIcon;
        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.tv_user_sex)
        ImageView tvUserSex;
        @BindView(R.id.tv_user_rank)
        TextView tvUserRank;
        @BindView(R.id.ll_nice)
        LinearLayout llNice;
        @BindView(R.id.tv_nice_count)
        TextView tvNiceCount;
        @BindView(R.id.iv_nice_ic)
        ImageView ivNiceIc;
        @BindView(R.id.iv_replay)
        ImageView ivReplay;
        @BindView(R.id.tv_user_mark)
        TextView tvUserMark;
        @BindView(R.id.tv_comment_desc)
        TextView tvCommentDesc;
        @BindView(R.id.tv_comment_time)
        TextView tvCommentTime;
        @BindView(R.id.lv_replay)
        CustomListView lvReplay;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            llNice.setOnClickListener(this);
            ivReplay.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(null == iUserHandle){
                return;
            }
            switch (v.getId()){
                case R.id.ll_nice:
                    int index = (int)v.getTag(R.id.tag_first);
                    if(!mData.get(index).isDig()){
                        iUserHandle.nice((int)v.getTag(R.id.tag_first));
                    }
                    break;
                case R.id.iv_replay:
                    iUserHandle.replay((int)v.getTag(R.id.tag_first),String.valueOf(v.getTag(R.id.tag_second)));
                    break;
            }
        }
    }

    private class TitleHolder extends RecyclerView.ViewHolder{
        private TextView tvVideoTitle;
        private TextView tvVideoDesc;
        private TextView tvVideoFavorite;
        private TextView tvVideoShare;
        private TextView tvVideoComment;
        private SimpleDraweeView ivUser;
        private RelativeLayout rlVideoComment;

        public TitleHolder(View itemView) {
            super(itemView);
            tvVideoTitle = (TextView)itemView.findViewById(R.id.tv_video_title);
            tvVideoDesc = (TextView)itemView.findViewById(R.id.tv_video_desc);
            tvVideoFavorite = (TextView)itemView.findViewById(R.id.tv_favorite);
            tvVideoShare = (TextView)itemView.findViewById(R.id.tv_share);
            tvVideoComment= (TextView)itemView.findViewById(R.id.tv_comment_count);
            ivUser = (SimpleDraweeView) itemView.findViewById(R.id.iv_user);
            rlVideoComment = (RelativeLayout)itemView.findViewById(R.id.rl_comment);
        }
    }




    public interface IUserHandle{
        void nice(int pid);
        void replay(int pid, String userName);
    }





    private String getName(Spanned spanned, int id) {
        if ("网侠网友".equals(spanned) || "网侠玩家".equals(spanned) || "网侠用户".equals(spanned) || "网侠网友".equals(spanned) || TextUtils.isEmpty(spanned)) {
            return spanned.toString() + id;
        }
        return spanned.toString();
    }

    private String setCityPhoneTime(String city, String phone, String playMinute, boolean girl) {
        if (TextUtils.isEmpty(city)) {
            city = "小程序-决战平安京管家";
        }
        if (!TextUtils.isEmpty(playMinute) && 0 < Long.parseLong(playMinute)) {
            if (girl) {
                playMinute = "\t她已玩" + Mytime.formatTime(Long.parseLong(playMinute));
            } else {
                playMinute = "\t他已玩" + Mytime.formatTime(Long.parseLong(playMinute));
            }
        } else {
            playMinute = "";
        }
        if (TextUtils.isEmpty(phone)) {
            phone = "";
        }
        return  phone + playMinute+ "." +TxtFormatUtil.HtmlFormat(city).toString() ;
    }
}
