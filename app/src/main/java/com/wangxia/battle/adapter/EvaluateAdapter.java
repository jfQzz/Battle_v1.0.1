package com.wangxia.battle.adapter;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wangxia.battle.R;
import com.wangxia.battle.model.bean.EvaluateBean;
import com.wangxia.battle.util.Mytime;
import com.wangxia.battle.util.TxtFormatUtil;

/**
 * Created by 昝奥博 on 2017/9/15 0015
 * Email:18772833900@163.com
 * Explain：游戏评论
 */
public class EvaluateAdapter extends BaseQuickAdapter<EvaluateBean.ItemsBean, BaseViewHolder> {
    private IUserHandle iUserHandle;
    private String colorTxt;
    private String colorSub;

    public EvaluateAdapter() {
        super(R.layout.evaluate_item);
    }


    public void setColorStyle(String colorTxt, String colorSub) {
        this.colorTxt = colorTxt;
        this.colorSub = colorSub;
    }

    public void initUserHandler(IUserHandle iUserHandle){
        this.iUserHandle = iUserHandle;
    }


    @Override
    protected void convert(final BaseViewHolder helper, EvaluateBean.ItemsBean item) {
        SimpleDraweeView ivUser = helper.getView(R.id.iv_user_icon);
        String userPic = TextUtils.isEmpty(item.getUserPic()) ? item.getIcon() : item.getUserPic();
        if (TextUtils.isEmpty(userPic))
            ivUser.setImageResource(R.drawable.ic_user_icon_empty);
         else
            ivUser.setImageURI(userPic);

        helper.setText(R.id.tv_user_name, getName(TxtFormatUtil.HtmlFormat(item.getUser()), item.getID()));
        ImageView ivNice = helper.getView(R.id.iv_nice_ic);
        TextView tvNiceCount = helper.getView(R.id.tv_nice_count);
        if (item.isDig()) {
            ivNice.setColorFilter(ContextCompat.getColor(mContext, R.color.colorAccent));
            tvNiceCount.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        } else {
            ivNice.setColorFilter(ContextCompat.getColor(mContext, R.color.colorTxtSub));
            tvNiceCount.setTextColor(mContext.getResources().getColor(R.color.colorTxtSub));
        }
        tvNiceCount.setText(item.getGood());
        helper.setText(R.id.tv_user_mark, setCityPhoneTime(item.getUserCity(), item.getPhone(), item.getPalyMinute(), item.isUserSex()));
        ReplayAdapter replayAdapter = new ReplayAdapter(mContext, item.getRe(), new ReplayAdapter.IReplayComment() {
            @Override
            public void success(int id, String userName) {
                iUserHandle.replay(id, userName);
            }
        });
        ((ListView) helper.getView(R.id.lv_replay)).setAdapter(replayAdapter);
        helper.setText(R.id.tv_comment_desc, TxtFormatUtil.HtmlFormat(item.getContent()));
        helper.setText(R.id.tv_comment_time, Mytime.getTwoDaysWords(item.getTime()));
        if (!TextUtils.isEmpty(colorTxt)) {
            helper.setTextColor(R.id.tv_user_name, Color.parseColor(colorTxt));
            helper.setTextColor(R.id.tv_comment_desc, Color.parseColor(colorTxt));
            replayAdapter.setColor(colorTxt);
        }
        if (!TextUtils.isEmpty(colorSub)) {
            helper.setTextColor(R.id.tv_user_mark, Color.parseColor(colorSub));
            helper.setTextColor(R.id.tv_comment_time, Color.parseColor(colorSub));
        }
        helper.getView(R.id.ll_nice).setTag(R.id.tag_first, helper.getLayoutPosition());
        helper.getView(R.id.iv_replay).setTag(R.id.tag_first, item.getID());
        helper.getView(R.id.iv_replay).setTag(R.id.tag_second, Html.fromHtml(item.getUser()));
        helper.getView(R.id.ll_nice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = (int) v.getTag(R.id.tag_first);
                if (!mData.get(index).isDig()) {
                    iUserHandle.nice((int) v.getTag(R.id.tag_first));
                }
            }
        });

       helper.getView(R.id.iv_replay).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                iUserHandle.replay((int) v.getTag(R.id.tag_first), String.valueOf(v.getTag(R.id.tag_second)));

           }
       });
    }

    public interface IUserHandle {
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
            city = "";
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
        return phone + playMinute + "." + city;
    }
}
