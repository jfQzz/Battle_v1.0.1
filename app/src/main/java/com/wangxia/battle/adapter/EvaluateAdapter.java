package com.wangxia.battle.adapter;

import android.content.Context;
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
import com.wangxia.battle.util.Mytime;
import com.wangxia.battle.util.TxtFormatUtil;
import com.wangxia.battle.view.CustomListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 昝奥博 on 2017/9/15 0015
 * Email:18772833900@163.com
 * Explain：
 */
public class EvaluateAdapter extends RecyclerView.Adapter<EvaluateAdapter.Holder> {
    private Context mContext;
    private List<EvaluateBean.ItemsBean> mData;
    private LayoutInflater inflater;

    public EvaluateAdapter(Context mContext, List<EvaluateBean.ItemsBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        inflater = LayoutInflater.from(mContext);
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(inflater.inflate(R.layout.evaluate_item, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        EvaluateBean.ItemsBean itemsBean = mData.get(position);
        String userPic = itemsBean.getUserPic();
        if(TextUtils.isEmpty(userPic)){
            holder.userIconImg.setImageResource(R.drawable.author_five_ico);
        }else {
            holder.userIconImg.setImageURI(userPic);
        }
        holder.userNameTxt.setText(getName(TxtFormatUtil.HtmlFormat(itemsBean.getUser()),itemsBean.getID()));
        setCityPhoneTime(itemsBean.getUserCity(),itemsBean.getPhone(),itemsBean.getPalyMinute(),itemsBean.isUserSex());
        holder.userCommentTimeTxt.setText(setCityPhoneTime(Html.fromHtml(itemsBean.getUser()).toString(),itemsBean.getPhone(),itemsBean.getPalyMinute(),itemsBean.isUserSex()));
        holder.userAnswerListView.setAdapter(new ReplayAdapter(mContext,itemsBean.getRe()));
        holder.userCommentTxt.setText(TxtFormatUtil.HtmlFormat(itemsBean.getContent()));
        holder.tvGamePinglunTime.setText(Mytime.getTwoDaysWords(itemsBean.getTime()));
        EvaluateBean.ItemsBean.UserAppBean userApp = itemsBean.getUserApp();
        if(null != userApp && !TextUtils.isEmpty(userApp.getPic())){
            holder.recentlyPlayGameLl.setVisibility(View.VISIBLE);
            holder.recentlyPlayGameIco.setImageURI(userApp.getPic());
        }else {
            holder.recentlyPlayGameLl.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.user_icon_img)
        SimpleDraweeView userIconImg;
        @BindView(R.id.user_name_txt)
        TextView userNameTxt;
        @BindView(R.id.user_sex)
        ImageView userSex;
        @BindView(R.id.user_rank_txt)
        TextView userRankTxt;
        @BindView(R.id.recently_play_game_ico)
        SimpleDraweeView recentlyPlayGameIco;
        @BindView(R.id.recently_play_game_ll)
        LinearLayout recentlyPlayGameLl;
        @BindView(R.id.user_comment_time_txt)
        TextView userCommentTimeTxt;
        @BindView(R.id.user_comment_txt)
        TextView userCommentTxt;
        @BindView(R.id.tv_game_pinglun_time)
        TextView tvGamePinglunTime;
        @BindView(R.id.user_answer_img)
        ImageView userAnswerImg;
        @BindView(R.id.user_answer_txt)
        TextView userAnswerTxt;
        @BindView(R.id.user_hits_img)
        ImageView userHitsImg;
        @BindView(R.id.user_hits_txt)
        TextView userHitsTxt;
        @BindView(R.id.user_support_linear)
        RelativeLayout userSupportLinear;
        @BindView(R.id.user_support_replay_mode)
        LinearLayout userSupportReplayMode;
        @BindView(R.id.user_answer_content)
        LinearLayout userAnswerContent;
        @BindView(R.id.user_answer_list_view)
        CustomListView userAnswerListView;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private String getName(Spanned spanned,int id) {

        if ("网侠网友".equals(spanned) || "网侠玩家".equals(spanned) || "网侠用户".equals(spanned )|| "网侠网友".equals(spanned) || TextUtils.isEmpty(spanned)) {
            return   spanned.toString() + id;
        }
        return spanned.toString();
    }

    private String setCityPhoneTime(String city, String phone, String playMinute,boolean girl) {
        if(TextUtils.isEmpty(city)){
            city = "网侠科技网友";
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
        if(TextUtils.isEmpty(phone)){
            phone = "";
        }
        return TxtFormatUtil.HtmlFormat(city).toString()+"\t"+phone+playMinute;
    }
}
