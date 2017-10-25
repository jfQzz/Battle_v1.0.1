package com.wangxia.battle.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangxia.battle.R;
import com.wangxia.battle.model.bean.EvaluateBean;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.Mytime;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 昝奥博 on 2017/9/15 0015
 * Email:18772833900@163.com
 * Explain：
 */
public class ReplayAdapter extends BaseAdapter {
    private List<EvaluateBean.ItemsBean.ReBean> mData;
    private Context mContext;
    private LayoutInflater inflater;
    private Unbinder mBind;

    public ReplayAdapter( Context mContext,List<EvaluateBean.ItemsBean.ReBean> mData) {
        this.mData = mData;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
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
        if (null == convertView) {
            view = inflater.inflate(R.layout.replay_item, parent, false);
            holder = new Holder(view);
        }else {
            holder = (Holder) view.getTag();
        }
        EvaluateBean.ItemsBean.ReBean reBean = mData.get(position);
        holder.replayListReplayTxt.setText(Html.fromHtml(String.format(Constant.string.REPLAY,reBean.getUser(),reBean.getReUserName(),reBean.getContent())));
        holder.replayListAnswerTimeTxt.setText(Mytime.formatTime(reBean.getTime()));
        return view;
    }

     class Holder {
        @BindView(R.id.replay_list_replay_txt)
        TextView replayListReplayTxt;
        @BindView(R.id.replay_official_img)
        ImageView replayOfficialImg;
        @BindView(R.id.replay_list_answer_time_txt)
        TextView replayListAnswerTimeTxt;
        public Holder(View view) {
            view.setTag(this);
            mBind = ButterKnife.bind(this,view);
        }
    }
}
