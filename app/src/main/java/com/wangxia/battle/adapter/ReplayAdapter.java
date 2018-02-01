package com.wangxia.battle.adapter;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wangxia.battle.R;
import com.wangxia.battle.model.bean.EvaluateBean;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.Mytime;
import com.wangxia.battle.util.TxtFormatUtil;

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
    private IReplayComment iReplayComment;
    private String colorTxt;

    public ReplayAdapter(Context mContext, List<EvaluateBean.ItemsBean.ReBean> mData, IReplayComment iReplayComment) {
        this.mData = mData;
        this.mContext = mContext;
        this.iReplayComment = iReplayComment;
        inflater = LayoutInflater.from(mContext);
    }

    public void setColor(String colorTxt) {
        this.colorTxt = colorTxt;

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
        } else {
            holder = (Holder) view.getTag();
        }
        EvaluateBean.ItemsBean.ReBean reBean = mData.get(position);
        String reUserName = reBean.getReUserName();
        Spanned replayStr;
        if (!TextUtils.isEmpty(colorTxt)) {
            if (TextUtils.isEmpty(reUserName)) {
                replayStr = Html.fromHtml(String.format("<font color = '"+colorTxt+"'>%1$s</font> <font color = '"+colorTxt+"'>:</font>  <font color = '"+colorTxt+"'>%2$s</font> <font color = '#D4D4D4'>%3$s</font>", reBean.getUser(), reBean.getContent(), Mytime.formatTime(reBean.getTime())));
            } else {
                replayStr = Html.fromHtml(String.format("<font color = '"+colorTxt+"'>%1$s</font> <font color = '"+colorTxt+"'>回复</font> <font color = '"+colorTxt+"'>%2$s</font> <font color = '"+colorTxt+"'>%3$s</font> <font color = '#D4D4D4'>%4$s</font>", reBean.getUser(), reUserName, reBean.getContent(), Mytime.formatTime(reBean.getTime())));
            }
        } else {
            if (TextUtils.isEmpty(reUserName)) {
                replayStr = Html.fromHtml(String.format(Constant.string.REPLAY_COMMENT, reBean.getUser(), reBean.getContent(), Mytime.formatTime(reBean.getTime())));
            } else {
                replayStr = Html.fromHtml(String.format(Constant.string.REPLAY, reBean.getUser(), reUserName, reBean.getContent(), Mytime.formatTime(reBean.getTime())));
            }
        }
        holder.replayListReplayTxt.setText(replayStr);
        view.setTag(R.id.tag_first, reBean.getID());
        view.setTag(R.id.tag_second, reBean.getUser());
        return view;
    }

    class Holder implements View.OnClickListener {
        @BindView(R.id.replay_list_replay_txt)
        TextView replayListReplayTxt;

        public Holder(View view) {
            view.setTag(this);
            mBind = ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iReplayComment.success((int) v.getTag(R.id.tag_first), TxtFormatUtil.HtmlFormat(v.getTag(R.id.tag_second).toString()).toString());
        }
    }

    public interface IReplayComment {
        void success(int id, String userName);
    }


}
