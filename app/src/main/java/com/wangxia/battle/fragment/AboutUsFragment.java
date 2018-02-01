package com.wangxia.battle.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.analytics.MobclickAgent;
import com.wangxia.battle.R;
import com.wangxia.battle.fragment.base.LazyBaseFragment;
import com.wangxia.battle.util.MyToast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 昝奥博 on 2017/9/20 0020
 * Email:18772833900@163.com
 * Explain：
 */
public class AboutUsFragment extends LazyBaseFragment implements View.OnClickListener {

    @BindView(R.id.about_us_mipmap_img)
    SimpleDraweeView aboutUsMipmapImg;
    @BindView(R.id.vertical_line)
    View verticalLine;
    @BindView(R.id.about_us_wx_txt)
    TextView aboutUsWxTxt;
    @BindView(R.id.about_us_qqqun_txt)
    TextView aboutUsQqqunTxt;
    @BindView(R.id.about_us_phone_net_txt)
    TextView aboutUsPhoneNetTxt;
    @BindView(R.id.about_us_pc_net_txt)
    TextView aboutUsPcNetTxt;
    @BindView(R.id.about_us_email_txt)
    TextView aboutUsEmailTxt;
    @BindView(R.id.about_us_introduce_txt)
    TextView aboutUsIntroduceTxt;
    Unbinder unbinder;
    private Unbinder mBind;

    public static AboutUsFragment newInstance() {
        Bundle args = new Bundle();
        AboutUsFragment fragment = new AboutUsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_about_us, null);
        mBind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        aboutUsMipmapImg.setImageResource(R.mipmap.ic_launcher);
        aboutUsWxTxt.setText("微信公众号: jzpaj520");
        aboutUsQqqunTxt.setText("官方QQ群: 597341641");
        aboutUsPhoneNetTxt.setText("手机访问: http://m.hackhome.com");
        aboutUsPcNetTxt.setText("电脑访问: http://www.hackhome.com");
        aboutUsEmailTxt.setText("联系邮箱: 2659359106@qq.com");
        aboutUsIntroduceTxt.setText("网站简介: 海量手机网游、单机游戏、应用app，最新手游资讯、攻略和app使用教程，网侠手机站期待您的关注。");
    }

    @Override
    public void initListener() {
        aboutUsWxTxt.setOnClickListener(this);
        aboutUsPhoneNetTxt.setOnClickListener(this);
        aboutUsPcNetTxt.setOnClickListener(this);
        aboutUsEmailTxt.setOnClickListener(this);
        aboutUsIntroduceTxt.setOnClickListener(this);
        aboutUsQqqunTxt.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("AboutUsFragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("AboutUsFragment");
    }

    @Override
    public void recycleMemory() {
        mBind.unbind();

    }


    @Override
    public void onClick(View v) {
        Uri uri;
        Intent intent = null;
        switch (v.getId()) {
            //微信号
            case R.id.about_us_wx_txt:
                //复制文本
                ClipboardManager clipManager = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("simple text", "wxhackhome");
                clipManager.setPrimaryClip(clipData);
                if (clipManager.hasPrimaryClip()) {
                    MyToast.showToast(mContext, "jzpaj520 已复制", Toast.LENGTH_SHORT);
                }
                intent = new Intent();
                ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.setComponent(cmp);
                //http://net.chuai.net/ewind.php?id=63
//                intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://net.chuai.net/ewind.php?id=63"));
                break;
            //一键添加qq群
            case R.id.about_us_qqqun_txt:
                intent = new Intent();
                intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + "ZxasxSH7FwHTJEQ0XT-uOsH1DSuuOeYk"));
                break;
            //手机网址
            case R.id.about_us_phone_net_txt:
                uri = Uri.parse("http://m.hackhome.com");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                break;
            //电脑网址
            case R.id.about_us_pc_net_txt:
                uri = Uri.parse("http://www.hackhome.com");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                break;
            //打开邮箱
            case R.id.about_us_email_txt:
                intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("text/plain");
                //主题
                intent.putExtra(Intent.EXTRA_SUBJECT, "致网侠科技的一封信");
                //内容
                intent.putExtra(Intent.EXTRA_TEXT, "你好，网侠。\n\t");
                //收件人
                intent.setData(Uri.parse("mailto:2659359106@qq.com"));
                break;
        }
        if (null != intent) {
            //检查设备中可以响应的activity
            PackageManager pm = mContext.getPackageManager();
            List<ResolveInfo> resolveInfos = pm.queryIntentActivities(intent, 0);
            if (resolveInfos.size() > 0) {
                //开启一个新的栈
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } else {
                MyToast.showToast(mContext, "您的手机暂不支持", Toast.LENGTH_SHORT);
            }

        }
    }
}
