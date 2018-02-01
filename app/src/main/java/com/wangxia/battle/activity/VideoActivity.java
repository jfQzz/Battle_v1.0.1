package com.wangxia.battle.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.analytics.MobclickAgent;
import com.wangxia.battle.R;
import com.wangxia.battle.adapter.EvaluateAdapter;
import com.wangxia.battle.callback.CommentCallback;
import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.fragment.CommentFragment;
import com.wangxia.battle.globe.App;
import com.wangxia.battle.model.bean.CommentBean;
import com.wangxia.battle.model.bean.EvaluateBean;
import com.wangxia.battle.model.bean.SuccessBean;
import com.wangxia.battle.model.bean.VideoDetailBean;
import com.wangxia.battle.presenter.impPresenter.CommentListPresenter;
import com.wangxia.battle.presenter.impPresenter.UpVotePresenter;
import com.wangxia.battle.presenter.impPresenter.VideoDetailPresenter;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.ImageUtil;
import com.wangxia.battle.util.Mytime;
import com.wangxia.battle.util.ShareUtil;
import com.wangxia.battle.util.SpUtil;
import com.wangxia.battle.util.StatusBarUtil;
import com.wangxia.battle.util.TxtFormatUtil;
import com.wangxia.battle.util.UserUtil;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayerStandard;

public class VideoActivity extends BaseActivity implements View.OnClickListener,ISuccessCallbackData,EvaluateAdapter.IUserHandle,CommentCallback{
    @BindView(R.id.video_player)
    JZVideoPlayerStandard mVideoPlayer;
    @BindView(R.id.rl_video)
    RecyclerView rlVideo;
    @BindView(R.id.iv_bg)
    SimpleDraweeView ivBg;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.tv_video_title)
     TextView tvVideoTitle;
    @BindView(R.id.tv_video_desc)
     TextView tvVideoDesc;
    @BindView(R.id.iv_share)
     ImageView ivVideoShare;
    @BindView(R.id.iv_favorite)
     ImageView ivVideoFavorite;
    @BindView(R.id.tv_comment_count)
     TextView tvVideoComment;
    @BindView(R.id.iv_user)
     SimpleDraweeView ivUser;
    @BindView(R.id.rl_comment)
     RelativeLayout rlVideoComment;
    @BindView(R.id.tv_no_comment)
    TextView tvNoComment;
    private int mVideoId;
    private String mVideoTitle;
    private String mVideoPic;
    private String mVideoUrl;
    private VideoDetailPresenter mVideoDetailPresenter;
    private String mVideoDesc;
    private CommentListPresenter mCommentListPresenter;
    private int mPageNo =1;
    private int mPageCount;
    private EvaluateAdapter mEvaluateAdapter;
    private String mLastInput;
    private int mUpVoteIndex;
    private boolean mIsRefresh;
    private boolean mIsNeedLoadUserInfo;
    private boolean mIsFavorite;
    private String mRealTime;


    @Override
    protected int getLayoutId() {
        Intent intent = getIntent();
        if (null != intent) {
            mVideoId = intent.getIntExtra(Constant.string.ARG_ONE,Constant.number.ZERO);
            mVideoTitle = intent.getStringExtra(Constant.string.ARG_TWO);
            mVideoPic = intent.getStringExtra(Constant.string.ARG_THREE);
        }
        return R.layout.activity_video;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setTransparentForImageView(this,null);
        ImageUtil.loadBitmap(this,mVideoPic,mVideoPlayer.thumbImageView);
//        mVideoPlayer.thumbImageView.setImageURI(Uri.parse(mVideoPic));
        ivBack.setVisibility(View.VISIBLE);
        MobclickAgent.onEvent(VideoActivity.this, Constant.uMengStatistic.WATCH_VIDEO);
        mEvaluateAdapter = new EvaluateAdapter();
        mEvaluateAdapter.disableLoadMoreIfNotFullPage(rlVideo);
        mEvaluateAdapter.setColorStyle("#ffffff","#eeeeee");
        rlVideo.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rlVideo.setAdapter(mEvaluateAdapter);
    }


    @Override
    protected void initListener() {
        ivBack.setOnClickListener(this);
        rlVideoComment.setOnClickListener(this);
        ivVideoShare.setOnClickListener(this);
        ivVideoFavorite.setOnClickListener(this);
        tvNoComment.setOnClickListener(this);
        mEvaluateAdapter.initUserHandler(this);

    }

    @Override
    protected void initData() {
        tvTitle.setText(mVideoTitle);
        ImageUtil.showUrlBlur(ivBg,mVideoPic,2,30);
        mVideoPlayer.titleTextView.setTextSize(12);
        mVideoDetailPresenter = new VideoDetailPresenter(VideoActivity.this);
        mVideoDetailPresenter.queryList(Constant.number.ZERO,String.valueOf(mVideoId),Constant.number.ZERO);
    }

    @Override
    protected void clearMemory() {
        mVideoPlayer = null;
        tvTitle = null;
    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("VideoActivity");
        MobclickAgent.onResume(this);
        if(mIsNeedLoadUserInfo && UserUtil.getUserState(this)){
            String userIcon = SpUtil.getString(this, Constant.userInfo.USER_ICON, null);
            if(!TextUtils.isEmpty(userIcon) && null != ivUser) ivUser.setImageURI(userIcon);
            mIsNeedLoadUserInfo = false;
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("VideoActivity");
        MobclickAgent.onPause(this);
        mVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (mVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:
                share();
                break;
            case R.id.iv_favorite:
                if(mIsFavorite){
                    App.mReaderManager.deleteFavoriteVideoById(mVideoId);
                    ivVideoFavorite.setImageResource(R.drawable.ic_white_favorite);
                    mIsFavorite = false;
                }else {
                    mIsFavorite = true;
                    ivVideoFavorite.setImageResource(R.drawable.ic_favorited);
                    tvVideoComment.getText().toString();
                    CharSequence text = tvVideoComment.getText();
                    App.mReaderManager.addVideoFavoriteDB(mVideoId,mVideoPic,mVideoTitle,mVideoDesc,TextUtils.isEmpty(text)?Constant.number.ZERO:Integer.parseInt(text.toString()),mRealTime,Mytime.getStringToday());
                }

                break;
            case R.id.rl_comment:
                toCommit();
                break;
            case R.id.tv_no_comment:
                toCommit();
                break;
        }
    }

    private void toCommit() {
        if(UserUtil.getUserState(this))
            CommentFragment.newInstance(this,new CommentBean(Constant.number.THREE,mVideoId,Constant.number.ZERO,String.valueOf("评价："+mVideoTitle),mLastInput)).show(this.getFragmentManager(), CommentCallback.CALLBACK);
        else{
            Intent intent = new Intent(this, TabWithPagerActivity.class);
            intent.putExtra(Constant.string.ARG_ONE, Constant.number.EIGHT);
            intent.putExtra(Constant.string.ARG_TWO, Constant.userInfo.NEED_USER_INFO);
            this.startActivity(intent);
            mIsNeedLoadUserInfo = true;
        }
    }

    private void share() {
        ShareUtil.localShare(this,mVideoTitle, mVideoPic, mVideoUrl);
    }





    @Override
    public void getResult(Object dataBen, int type) {
        if(this.isFinishing() || null == dataBen){
            return;
        }
        switch (type){
            //视频详情
            case Constant.number.ZERO:
                VideoDetailBean videoDetailBean = (VideoDetailBean) dataBen;
                if(null != videoDetailBean){
                    mVideoUrl = videoDetailBean.getSrc();
                    mVideoDesc = TxtFormatUtil.HtmlFormat(videoDetailBean.getText()).toString();
                    mVideoPlayer.setUp(mVideoUrl, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, mVideoTitle);
                    tvVideoTitle.setText(mVideoTitle);
                    tvVideoDesc.setText(mVideoDesc);
                    mRealTime = videoDetailBean.getRealtime();
                    if(App.mReaderManager.isFavoriteVideoById(mVideoId)){
                        mIsFavorite = true;
                        ivVideoFavorite.setImageResource(R.drawable.ic_favorited);
                    }
                    App.mReaderManager.addVideoBrowseDB(mVideoId,mVideoPic,mVideoTitle,mVideoDesc,Integer.parseInt(videoDetailBean.getHits()),mRealTime, Mytime.getStringToday());
                    ivUser.setImageURI(SpUtil.getString(this,Constant.userInfo.USER_ICON,null));
                    rlHead.setVisibility(View.VISIBLE);
                    Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha_from_zero_to_one);
                    animation.setDuration(1000);
                    rlHead.setAnimation(animation);
                    mCommentListPresenter = new CommentListPresenter(this);
                    mCommentListPresenter.queryList(Constant.number.THREE,String.valueOf(mVideoId),mPageNo);
                }
                break;
            case Constant.number.ONE:
                SuccessBean successBean = (SuccessBean) dataBen;
                if(null != successBean && TextUtils.equals(SuccessBean.SUCCESS_TAG,successBean.getSuccess())){
                    EvaluateBean.ItemsBean itemsBean = mEvaluateAdapter.getItem(mUpVoteIndex);
                    itemsBean.setGood(String.valueOf(Integer.parseInt(itemsBean.getGood())+1));
                    itemsBean.setDig(true);
                    mEvaluateAdapter.notifyItemChanged(mUpVoteIndex);
                }
                break;
            //评论
            case Constant.number.THREE:
                EvaluateBean evaluateBean = (EvaluateBean) dataBen;
                if(null != evaluateBean && null != evaluateBean.getItems() && 0 < evaluateBean.getItems().size()){
                    if(View.VISIBLE == tvNoComment.getVisibility()){
                        tvNoComment.setVisibility(View.GONE);
                    }
                    mPageCount = evaluateBean.getPagecount();
                    tvVideoComment.setText(String.valueOf(evaluateBean.getDatacount()));
                    if(mIsRefresh){
                       mEvaluateAdapter.setNewData(evaluateBean.getItems());
                    }else {
                        mEvaluateAdapter.addData(evaluateBean.getItems());
                        mEvaluateAdapter.loadMoreComplete();
                    }
                }else {
                    tvNoComment.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public void failRequest() {
        if(!mIsRefresh){
            mEvaluateAdapter.loadMoreFail();
        }
    }

    @Override
    public void errorRequest() {
        if(!mIsRefresh){
            mEvaluateAdapter.loadMoreFail();
        }

    }

    @Override
    public void nice(int index) {
        mUpVoteIndex = index;
        new UpVotePresenter(this).queryList(Constant.number.THREE,String.valueOf(mEvaluateAdapter.getItem(index).getID()),Constant.number.ONE);

    }

    @Override
    public void replay(int pid, String userName) {
        if(UserUtil.getUserState(this))
            CommentFragment.newInstance(this,new CommentBean(Constant.number.THREE,mVideoId,pid,String.valueOf("回复："+userName),mLastInput)).show(this.getFragmentManager(), CommentCallback.CALLBACK);
        else{
            Intent intent = new Intent(this, TabWithPagerActivity.class);
            intent.putExtra(Constant.string.ARG_ONE, Constant.number.EIGHT);
            this.startActivity(intent);
        }
    }

    @Override
    public void saveCommentText(String commentWords) {
        mLastInput = commentWords;

    }

    @Override
    public void successComment() {
        if(null == this && this.isFinishing()){
            return;
        }
        mIsRefresh = true;
        mPageNo = Constant.number.ONE;
        mCommentListPresenter.queryList(Constant.number.THREE,String.valueOf(mVideoId),mPageNo);

    }

    @Override
    public void failComment() {

    }
}
