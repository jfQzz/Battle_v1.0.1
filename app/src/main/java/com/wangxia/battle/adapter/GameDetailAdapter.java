package com.wangxia.battle.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangxia.battle.R;
import com.wangxia.battle.model.bean.AppInfo;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.Mytime;
import com.wangxia.battle.util.RecycleItemDecortion;
import com.wangxia.battle.util.TxtFormatUtil;

import java.util.List;

import butterknife.Unbinder;

/**
 * Created by 昝奥博 on 2017/9/14 0014
 * Email:18772833900@163.com
 * Explain：
 */
public class GameDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Object> mData;
    private LayoutInflater inflater;
    private Unbinder mBind;
    private IItemClic iItemClic;

    public GameDetailAdapter(Context mContext, List<Object> mData) {
        this.mContext = mContext;
        this.mData = mData;
        inflater = LayoutInflater.from(mContext);
    }

    public void setItemClick(IItemClic itemClick){
        this.iItemClic = itemClick;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType){
            case Constant.number.ZERO:
                holder =  new LabelHolder(inflater.inflate(R.layout.label_items,parent,false));
                break;
            case Constant.number.ONE:
                holder =  new IconHolder(inflater.inflate(R.layout.recycle_view,parent,false));
                break;
            case Constant.number.TWO:
                holder =  new TxtAndAppHolder(inflater.inflate(R.layout.game_detail_head,parent,false));
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof LabelHolder){
            LabelHolder labelHolder = (LabelHolder) holder;
            String s = (String) mData.get(Constant.number.ZERO);
            String[] split = s.split(",");
            labelHolder.tv_label_one.setText(split[Constant.number.ZERO]);
            labelHolder.tv_label_two.setText(split[Constant.number.ONE]);
            labelHolder.tv_label_three.setText(split[Constant.number.TWO]);
            labelHolder.tv_label_fore.setText(split[Constant.number.THREE]);
        }else if(holder instanceof IconHolder){
            IconHolder iconHolder = (IconHolder) holder;
            List<String> list = (List<String>) mData.get(1);
            iconHolder.rl_icons.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL,false));
            iconHolder.rl_icons.addItemDecoration(new RecycleItemDecortion.SpacesItemDecoration(Constant.number.FIFTEEN,Constant.number.FIFTEEN,Constant.number.ZERO));
            BigImgAdapter bigImgAdapter = new BigImgAdapter(mContext, list);
            iconHolder.rl_icons.setAdapter(bigImgAdapter);
            bigImgAdapter.setItemClick(new BigImgAdapter.IitemClick() {
                @Override
                public void getImgUrl(String url,int index) {
                    iItemClic.showBigPic(url,index);
                }
            });
        }else if(holder instanceof TxtAndAppHolder){
            TxtAndAppHolder txtAndAppHolder = (TxtAndAppHolder) holder;
            AppInfo appInfo = (AppInfo) mData.get(2);
            txtAndAppHolder.tv_desc.setText(TxtFormatUtil.HtmlFormat(appInfo.getText()).toString());
            txtAndAppHolder.tv_open_or_close.setTag(R.id.tag_first,txtAndAppHolder.tv_desc);
            String downloadTimes = "";
            String updateTime = "";
            String versionCode = "";
            String hints = String.valueOf(13897);

            if (!TextUtils.isEmpty(appInfo.getHits())) {
                hints = appInfo.getHits();
            }
            if (0 != Integer.parseInt(hints)) {
                downloadTimes = "\n下载次数：" + hints;
            }
            if (!TextUtils.isEmpty(appInfo.getTime())) {
                updateTime = "\n更新日期：" + Mytime.formatTime(appInfo.getTime());
            }
            if (!TextUtils.isEmpty(appInfo.getVer())) {
                versionCode = "版本号：" + appInfo.getVer();
            }
            txtAndAppHolder.tv_detail.setText(versionCode + updateTime + downloadTimes);
            txtAndAppHolder.rl_more_games.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL,false));
            txtAndAppHolder.rl_more_games.addItemDecoration(new RecycleItemDecortion.SpacesItemDecoration(5,0));
            MoreGameAdapter moreGameAdapter = new MoreGameAdapter(mContext, appInfo.getAppList());
            txtAndAppHolder.rl_more_games.setAdapter(moreGameAdapter);
            moreGameAdapter.setItemClick(new MoreGameAdapter.IItemClick() {
                @Override
                public void getAppId(int id) {
                    iItemClic.toAppDetail(id);
                }
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        Object object = mData.get(position);
        if(object instanceof String){
            return 0;
        }else if(object instanceof List){
            return 1;
        }else {
            return 2;
        }
    }

    @Override
    public int getItemCount() {

        return mData == null ? 0 : 3;
    }

    public class LabelHolder extends RecyclerView.ViewHolder {

        private TextView tv_label_one;
        private TextView tv_label_two;
        private TextView tv_label_three;
        private TextView tv_label_fore;
        public LabelHolder(View itemView) {
            super(itemView);
            tv_label_one = (TextView) itemView.findViewById(R.id.tv_game_label_one);
            tv_label_two = (TextView) itemView.findViewById(R.id.tv_game_label_two);
            tv_label_three = (TextView) itemView.findViewById(R.id.tv_game_label_three);
            tv_label_fore = (TextView) itemView.findViewById(R.id.tv_game_label_fore);

        }
    }

    public class IconHolder extends RecyclerView.ViewHolder {


        private RecyclerView rl_icons;
        public IconHolder(View itemView) {
            super(itemView);
            rl_icons = (RecyclerView) itemView.findViewById(R.id.rl_icons);

        }
    }

    public class TxtAndAppHolder extends RecyclerView.ViewHolder implements OnClickListener{
        private TextView tv_desc;
        private TextView tv_open_or_close;
        private TextView tv_detail;
        private RecyclerView rl_more_games;
        public TxtAndAppHolder(View itemView) {
            super(itemView);
            tv_desc = (TextView) itemView.findViewById(R.id.tv_game_desc);
            tv_open_or_close = (TextView) itemView.findViewById(R.id.tv_open_or_close);
            tv_detail = (TextView) itemView.findViewById(R.id.tv_game_detail);
            rl_more_games = (RecyclerView)itemView.findViewById(R.id.rl_more_game);
            tv_open_or_close.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_open_or_close:
                    iItemClic.openOrCloseTxt((TextView) v);
                    break;
            }
        }
    }

    public interface IItemClic{
        void showBigPic(String url, int index);
        void openOrCloseTxt(TextView view);
        void toAppDetail(int id);
    }
}
