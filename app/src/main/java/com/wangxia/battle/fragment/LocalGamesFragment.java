package com.wangxia.battle.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.wangxia.battle.R;
import com.wangxia.battle.activity.AppDetailActivity;
import com.wangxia.battle.activity.WebViewActivity;
import com.wangxia.battle.adapter.LocalAppAdapter;
import com.wangxia.battle.adapter.UserAppAdapter;
import com.wangxia.battle.adapter.UserArticleAdapter;
import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.db.bean.ArticleBean;
import com.wangxia.battle.db.bean.GameBean;
import com.wangxia.battle.model.bean.AppInfoList;
import com.wangxia.battle.model.bean.LocalAppBean;
import com.wangxia.battle.presenter.impPresenter.LocalAppListPresenter;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.MyToast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 昝奥博 on 2017/7/22 0022
 * Email:18772833900@163.com
 * Explain：0:本地游戏 1：游戏下载历史 2：游戏浏览历史 3：文章浏览历史 4：游戏收藏 5：文章收藏
 */
public class LocalGamesFragment extends BaseFragment implements ISuccessCallbackData {
    @BindView(R.id.smart_refresh)
    SwipeRefreshLayout smartRefreshLayout;
    @BindView(R.id.rl_view)
    RecyclerView rlLocalGames;
    @BindView(R.id.tv_empty_view)
    TextView tvEmptyView;
    private Context mContext;
    private Unbinder mBind;
    private List<LocalAppBean> mLocalAppData = new ArrayList<>();
    private List<GameBean> mLocalGameData = new ArrayList<>();
    private List<ArticleBean> mLocalArticleData = new ArrayList<>();
    private LocalAppAdapter mLocalAppAdapter;
    private int mType;
    private LocalAppListPresenter mLocalAppListPresenter;
    private UserAppAdapter mUserAppAdapter;
    private UserArticleAdapter mUserArticleAdapter;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        WeakReference<Context> weakReference = new WeakReference<>(context);
        mContext = weakReference.get();
    }

    public static LocalGamesFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt(Constant.string.ARG_ONE, type);
        LocalGamesFragment fragment = new LocalGamesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (null != getArguments()) {
            mType = arguments.getInt(Constant.string.ARG_ONE);
        }
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_article_list, null);
        mBind = ButterKnife.bind(this, view);
        smartRefreshLayout.setEnabled(false);
        rlLocalGames.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        switch (mType) {
            case Constant.number.ZERO:
                mLocalAppAdapter = new LocalAppAdapter(mContext, mLocalAppData);
                rlLocalGames.setAdapter(mLocalAppAdapter);
                break;
            case Constant.number.ONE:
                mUserAppAdapter = new UserAppAdapter(mContext, mLocalGameData);
                rlLocalGames.setAdapter(mUserAppAdapter);
                break;
            case Constant.number.TWO:
                mUserAppAdapter = new UserAppAdapter(mContext, mLocalGameData);
                rlLocalGames.setAdapter(mUserAppAdapter);
                break;
            case Constant.number.THREE:
                mUserArticleAdapter = new UserArticleAdapter(mContext, mLocalArticleData);
                rlLocalGames.setAdapter(mUserArticleAdapter);
                break;
            case Constant.number.FORE:
                mUserAppAdapter = new UserAppAdapter(mContext, mLocalGameData);
                rlLocalGames.setAdapter(mUserAppAdapter);
                break;
            case Constant.number.FIVE:
                mUserArticleAdapter = new UserArticleAdapter(mContext, mLocalArticleData);
                rlLocalGames.setAdapter(mUserArticleAdapter);
                break;
        }
        return view;
    }

    @Override
    public void initData() {
        mLocalAppListPresenter = new LocalAppListPresenter(this);
        mLocalAppListPresenter.queryList(mType, null,Constant.number.ZERO);
    }

    @Override
    public void initListener() {
        if (Constant.number.ZERO == mType) {
            mLocalAppAdapter.setItemClick(new LocalAppAdapter.IItemClick() {
                @Override
                public void openApp(String packageName) {
                    try {
                        Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(packageName);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }catch (Exception e){
                        e.printStackTrace();
                        MyToast.showToast(mContext,getResources().getString(R.string.launcher_fail), Toast.LENGTH_SHORT);
                    }
                }
            });
        } else if(Constant.number.ONE == mType || Constant.number.TWO == mType || Constant.number.FORE == mType){
            mUserAppAdapter.initItemClick(new UserAppAdapter.ItemClick() {
                @Override
                public void toAppDetail(int id) {
                    Intent intent = new Intent(mContext, AppDetailActivity.class);
                    intent.putExtra(Constant.string.ARG_ONE,id);
                    startActivity(intent);
                }
                @Override
                public void downloadApp(AppInfoList.ItemsBean itemsBean) {

                }
            });
        }else {
            mUserArticleAdapter.setiItemClick(new UserArticleAdapter.IItemClick() {
                @Override
                public void toArticleDetail(int position) {
                    ArticleBean articleBean = mLocalArticleData.get(position);
                    WebViewActivity.toWebViewActivity(mContext,articleBean.getId(),articleBean.getTitle(),articleBean.getIcon(),articleBean.getHints(),articleBean.getTime(),Constant.string.ARG_ONE,Constant.string.ARG_ONE);
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("LocalDateBaseFragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("LocalDateBaseFragment");
    }

    @Override
    public void recycleMemory() {
        mBind.unbind();
        if (null != mLocalAppAdapter) {
            mLocalAppAdapter.clearMemory();
        }
        if (null != mLocalAppData) {
            mLocalAppData.clear();
        }
        smartRefreshLayout = null;
        rlLocalGames = null;

    }

    @Override
    public void getResult(Object dataBen, int type) {
        if(null == dataBen || Constant.number.ZERO == ((List<Object>) dataBen).size()){
                tvEmptyView.setText("暂无记录~");
                tvEmptyView.setVisibility(View.VISIBLE);
                rlLocalGames.setVisibility(View.GONE);
                return;
            }else {
                tvEmptyView.setVisibility(View.GONE);
                rlLocalGames.setVisibility(View.VISIBLE);
            }
            switch (type) {
                case Constant.number.ZERO:
                    List<LocalAppBean> localAppBeen = (List<LocalAppBean>) dataBen;
                    if (null != localAppBeen && 0 < localAppBeen.size()) {
                        mLocalAppData.addAll(localAppBeen);
                        mLocalAppAdapter.notifyDataSetChanged();
                    }
                    break;
                case Constant.number.ONE:
                    List<GameBean> gameHistoryBeen = (List<GameBean>) dataBen;
                    if (null != gameHistoryBeen && Constant.number.ZERO < gameHistoryBeen.size()) {
                        mLocalGameData.addAll(gameHistoryBeen);
                        mUserAppAdapter.notifyDataSetChanged();
                    }
                    break;
                case Constant.number.TWO:
                    List<GameBean> gameBrowseBeen = (List<GameBean>) dataBen;
                    if (null != gameBrowseBeen && Constant.number.ZERO < gameBrowseBeen.size()) {
                        mLocalGameData.addAll(gameBrowseBeen);
                        mUserAppAdapter.notifyDataSetChanged();
                    }
                    break;
                case Constant.number.THREE:
                    List<ArticleBean> articleBrowseBeen = (List<ArticleBean>) dataBen;
                    if (null != articleBrowseBeen && Constant.number.ZERO < articleBrowseBeen.size()) {
                        mLocalArticleData.addAll(articleBrowseBeen);
                        mUserArticleAdapter.notifyDataSetChanged();
                    }
                    break;
                case Constant.number.FORE:
                    List<GameBean> gameFavoriteBeen = (List<GameBean>) dataBen;
                    if (null != gameFavoriteBeen && Constant.number.ZERO < gameFavoriteBeen.size()) {
                        mLocalGameData.addAll(gameFavoriteBeen);
                        mUserAppAdapter.notifyDataSetChanged();
                    }
                    break;
                case Constant.number.FIVE:
                    List<ArticleBean> articleFavoriteBeen = (List<ArticleBean>) dataBen;
                    if (null != articleFavoriteBeen && Constant.number.ZERO < articleFavoriteBeen.size()) {
                        mLocalArticleData.addAll(articleFavoriteBeen);
                        mUserArticleAdapter.notifyDataSetChanged();
                    }
                    break;
        }
    }
}
