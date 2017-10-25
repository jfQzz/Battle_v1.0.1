package com.wangxia.battle.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.analytics.MobclickAgent;
import com.wangxia.battle.R;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.ShareUtil;

import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.video_player)
    JCVideoPlayerStandard videoPlayer;
    @BindView(R.id.tv_video_desc)
    TextView tvVideoDesc;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_user_ico)
    SimpleDraweeView ivUserIco;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_action_right_one)
    ImageView ivActionRightOne;
    @BindView(R.id.iv_action_right_two)
    ImageView ivActionRightTwo;
    @BindView(R.id.iv_action_right_three)
    ImageView ivActionRightThree;
    private String mVideoUrl;
    private String mVideoCover;
    private String mVideoTitle;
    private String mVideoDesc;

    @Override
    protected int getLayoutId() {
        Intent intent = getIntent();
        if (null != intent) {
            mVideoUrl = intent.getStringExtra(Constant.string.ARG_ONE);
            mVideoTitle = intent.getStringExtra(Constant.string.ARG_TWO);
            mVideoDesc = intent.getStringExtra(Constant.string.ARG_THREE);
        }
        return R.layout.activity_video;
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        ivActionRightThree.setVisibility(View.VISIBLE);
        MobclickAgent.onEvent(VideoActivity.this, Constant.uMengStatistic.WATCH_VIDEO);
    }

    @Override
    protected void initListener() {
        ivBack.setOnClickListener(this);
        ivActionRightThree.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        videoPlayer.setUp(mVideoUrl, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, mVideoTitle);
        videoPlayer.titleTextView.setTextSize(12);
        videoPlayer.thumbImageView.setImageResource(R.drawable.bg_video_ready);
        tvTitle.setText(mVideoTitle);
        tvVideoDesc.setText("\t\t" + mVideoDesc);
    }

    @Override
    protected void clearMemory() {
        videoPlayer = null;
        tvTitle = null;
        tvVideoDesc = null;

    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("VideoActivity");
        MobclickAgent.onResume(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("VideoActivity");
        MobclickAgent.onPause(this);
        videoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (videoPlayer.backPress()) {
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
            case R.id.iv_action_right_three:
                share();
                break;
        }
    }

    private void share() {
        ShareUtil.localShare(this,mVideoTitle,mVideoDesc,mVideoUrl);
    }
}
