package com.wangxia.battle.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.wangxia.battle.R;
import com.wangxia.battle.activity.TabWithPagerActivity;
import com.wangxia.battle.callback.ICallbackWxInfo;
import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.fragment.base.LazyBaseFragment;
import com.wangxia.battle.model.bean.SinaUserBean;
import com.wangxia.battle.model.bean.SuccessBean;
import com.wangxia.battle.model.bean.UserBindBean;
import com.wangxia.battle.presenter.impPresenter.ApiBindAccountPresenter;
import com.wangxia.battle.presenter.impPresenter.SinaInfoPresenter;
import com.wangxia.battle.presenter.impPresenter.UploadFilePresenter;
import com.wangxia.battle.presenter.impPresenter.UserBindInfoPresenter;
import com.wangxia.battle.presenter.impPresenter.UserBindPresenter;
import com.wangxia.battle.presenter.impPresenter.UserModifyInfoPresenter;
import com.wangxia.battle.presenter.impPresenter.UserModifyPasswordPresenter;
import com.wangxia.battle.presenter.impPresenter.UserUnBindAccountPresenter;
import com.wangxia.battle.presenter.impPresenter.VerificationCodePresenter;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.LogUtil;
import com.wangxia.battle.util.MyToast;
import com.wangxia.battle.util.SpUtil;
import com.wangxia.battle.util.TxtFormatUtil;
import com.wangxia.battle.util.UserUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * Created by 昝奥博 on 2017/11/15 0015
 * Email:18772833900@163.com
 * Explain：用户设置界面
 */
public class UserFragment extends LazyBaseFragment implements View.OnClickListener, ISuccessCallbackData , ICallbackWxInfo {

    @BindView(R.id.iv_user_icon)
    SimpleDraweeView ivUserIcon;
    @BindView(R.id.ll_user_icon)
    LinearLayout llUserIcon;
    @BindView(R.id.tv_user_id)
    TextView tvUserId;
    @BindView(R.id.tv_user_nick)
    TextView tvUserNick;
    @BindView(R.id.iv_editnick)
    ImageView ivEditnick;
    @BindView(R.id.ll_user_nick)
    LinearLayout llUserNick;
    @BindView(R.id.tv_phone_state)
    TextView tvPhoneState;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.tv_sina_nick)
    TextView tvSinaNick;
    @BindView(R.id.iv_sina)
    SimpleDraweeView ivSina;
    @BindView(R.id.ll_sina)
    LinearLayout llSina;
    @BindView(R.id.tv_qq_nick)
    TextView tvQQNick;
    @BindView(R.id.iv_qq)
    SimpleDraweeView ivQQ;
    @BindView(R.id.ll_qq)
    LinearLayout llQq;
    @BindView(R.id.tv_wx_nick)
    TextView tvWxNick;
    @BindView(R.id.iv_wx)
    SimpleDraweeView ivWx;
    @BindView(R.id.ll_wx)
    LinearLayout llWx;
    @BindView(R.id.tv_user_gender)
    TextView tvUserGender;
    @BindView(R.id.ll_user_gender)
    LinearLayout llUserGender;
    @BindView(R.id.tv_exchange_password)
    TextView tvExchangePassword;
    @BindView(R.id.tv_user_login_time)
    TextView tvUserLoginTime;
    @BindView(R.id.tv_app_setting)
    TextView tvAppSetting;
    @BindView(R.id.btn_user_exit)
    Button btnUserExit;
    @BindView(R.id.tv_pic)
    TextView tvPic;
    @BindView(R.id.tv_take_photo)
    TextView tvTakePhoto;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.ll_select_pic)
    LinearLayout llSelectPic;
    @BindView(R.id.view_gone)
    View picGone;
    @BindView(R.id.tv_safe_sw)
    TextView tvSafeSw;
    @BindView(R.id.ll_user_safe_contain)
    LinearLayout llSafeContain;
    @BindView(R.id.ll_user_safety)
    LinearLayout llUserSafety;
    private Unbinder mBind;
    private boolean mIsSafeState;
    private UserBindInfoPresenter mUserBindInfoPresenter;
    private Animation mInAnimation;
    private Animation mOutAnimation;
    private String imagePath;
    private boolean mIsFromPic;
    private Uri mTakePhotoUri;
    private int mGenderWhich;
    private UserModifyInfoPresenter mUserModifyInfoPresenter;
    private String mModifyNick;
    private boolean mIsHadBindPhone;
    private boolean mIsHadBindQq;
    private boolean mIsHadBindWx;
    private boolean mIsHadBindSina;
    private boolean mIsGetAuthCode;
    private TextView tvGetCode;
    private Timer mTimer;
    private int mReTime = 60;
    private Editable mBindPhone;
    private int mUnBindTyep;
    private boolean mIsOldError;
    private boolean mIsNewError;
    private boolean mIsSina;
    private boolean mIsQQ;
    private SsoHandler mSsoHandler;
    private IWXAPI mWxApi;
    private Tencent mTencentAuthor;
    private Oauth2AccessToken mAccessToken;
    private String mApiIcon;
    private String mApiName;
    private int mApiType;
    private String mApiId;
    private String mUserPic;

    public static UserFragment newInstance() {
        Bundle args = new Bundle();
        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_user, null);
        mBind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        mInAnimation = AnimationUtils.loadAnimation(mContext, R.anim.enter_bottom_to_top);
        mOutAnimation = AnimationUtils.loadAnimation(mContext, R.anim.exit_top_to_bottom);
        String userIcon = SpUtil.getString(mContext, Constant.userInfo.USER_ICON, null);
        if (!TextUtils.isEmpty(userIcon)) {
            ivUserIcon.setImageURI(userIcon);
        }
        tvUserNick.setText(SpUtil.getString(mContext, Constant.userInfo.USER_NICK, getString(R.string.default_nick)));
        tvUserId.setText(SpUtil.getString(mContext, Constant.userInfo.USER_ID, getString(R.string.default_ID)));
        tvUserGender.setText(SpUtil.getString(mContext, Constant.userInfo.USER_GENDER, getString(R.string.default_gender)));
        tvUserLoginTime.setText(SpUtil.getString(mContext, Constant.userInfo.USER_LOGIN_TIME, getString(R.string.default_ID)));
        mUserBindInfoPresenter = new UserBindInfoPresenter(this);
        mUserBindInfoPresenter.queryList(Constant.number.ZERO, null, Constant.number.ZERO);
    }

    @Override
    public void initListener() {
        llUserIcon.setOnClickListener(this);
        llUserNick.setOnClickListener(this);
        llUserGender.setOnClickListener(this);
        llPhone.setOnClickListener(this);
        llSina.setOnClickListener(this);
        llQq.setOnClickListener(this);
        llWx.setOnClickListener(this);
        tvExchangePassword.setOnClickListener(this);
        tvAppSetting.setOnClickListener(this);
        btnUserExit.setOnClickListener(this);
        llUserSafety.setOnClickListener(this);
        picGone.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        tvPic.setOnClickListener(this);
        tvTakePhoto.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_user_icon:
                llSelectPic.startAnimation(mInAnimation);
                llSelectPic.setVisibility(View.VISIBLE);
                picGone.setClickable(true);
                break;
            case R.id.ll_user_nick:
                showModifyNickDialog();
                break;
            case R.id.ll_user_gender:
                showModifyGenderDialog();
                break;
            case R.id.ll_phone:
                //第三方用户只能绑定一次手机号
                if (!mIsHadBindPhone) bindAccount(Constant.number.ZERO, Constant.number.ZERO);
                break;
            case R.id.ll_sina:
                if (mIsHadBindSina) bindAccount(Constant.number.ONE, Constant.number.THREE);
                else bindAccount(Constant.number.ZERO, Constant.number.THREE);
                break;
            case R.id.ll_qq:
                if (mIsHadBindQq) bindAccount(Constant.number.ONE, Constant.number.ONE);
                else bindAccount(Constant.number.ZERO, Constant.number.ONE);
                break;
            case R.id.ll_wx:
                if (mIsHadBindWx) bindAccount(Constant.number.ONE, Constant.number.TWO);
                else bindAccount(Constant.number.ZERO, Constant.number.TWO);
                break;
            case R.id.tv_exchange_password:
                showModifyPasswordDialog();
                break;
            case R.id.tv_app_setting:
                toAppSetting();
                break;
            case R.id.btn_user_exit:
                userExit();
                break;
            case R.id.view_gone:
                if (null != llSelectPic) {
                    picGone.setClickable(false);
                    llSelectPic.startAnimation(mOutAnimation);
                    llSelectPic.setVisibility(View.GONE);
                }
                break;
            case R.id.ll_user_safety:
                if (mIsSafeState) {
                    mIsSafeState = false;
                    llSafeContain.setVisibility(View.GONE);
                    tvSafeSw.setText(getString(R.string.show));
                } else {
                    mIsSafeState = true;
                    llSafeContain.setVisibility(View.VISIBLE);
                    tvSafeSw.setText(getString(R.string.hide));
                }
                break;
            //相册选择图片
            case R.id.tv_pic:
                if (null != llSelectPic) {
                    picGone.setClickable(false);
                    llSelectPic.setVisibility(View.GONE);
                }
                mIsFromPic = true;
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                getActivity().startActivityForResult(intent, Constant.number.HUNDRED);
                break;
            //拍照获取
            case R.id.tv_take_photo:
                if (null != llSelectPic) {
                    picGone.setClickable(false);
                    llSelectPic.setVisibility(View.GONE);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, Constant.number.HUNDRED_AND_TWO);

                } else {
                    mIsFromPic = false;
                    openCamera();
                }
                break;
            //取消
            case R.id.tv_cancel:
                if (null != llSelectPic) {
                    picGone.setClickable(false);
                    llSelectPic.startAnimation(mOutAnimation);
                    llSelectPic.setVisibility(View.GONE);
                }
                break;
        }
    }

    /**
     * 退出当前账户
     * 个人认为最好发送请求，告知后台当前用户退出，将当前后台的cookies设置无效，同时监听用户的行为
     */
    private void userExit() {
        UserUtil.exit(mContext);
        getActivity().finish();
    }

    /**
     * 跳转到App的设置界面
     */
    private void toAppSetting() {

    }

    /**
     * 修改账户密码
     */
    private void showModifyPasswordDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        final View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_modify_password, null);
        final EditText edtOldPass = (EditText) view.findViewById(R.id.edt_old_pass);
        final EditText edtNewPass = (EditText) view.findViewById(R.id.edt_new_pass);
        final EditText edtConfirmPass = (EditText) view.findViewById(R.id.edt_confirm_pass);
        final TextView tvErrorHints = (TextView) view.findViewById(R.id.tv_error_hints);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        if (UserUtil.canModifyPass(mContext)) edtOldPass.setVisibility(View.VISIBLE);
        view.findViewById(R.id.tv_sure_modify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserUtil.canModifyPass(mContext)) {
                    if (TextUtils.isEmpty(edtOldPass.getText())) {
                        tvErrorHints.setText("请输入原密码");
                        mIsOldError = true;
                        return;
                    }

                }
                //判断不为空前后一致即可
                if (TextUtils.isEmpty(edtNewPass.getText())) {
                    tvErrorHints.setText("请设置新的密码");
                    mIsNewError = true;
                    return;
                }
                if (!TextUtils.equals(edtNewPass.getText(), edtConfirmPass.getText())) {
                    tvErrorHints.setText("新密码输入不一致");
                    mIsNewError = true;
                    return;
                }
                alertDialog.dismiss();
                new UserModifyPasswordPresenter(UserFragment.this).queryList(Constant.number.SEVEN, String.valueOf(edtOldPass.getText() + Constant.string.COMMA_SEPARATOR +
                        edtNewPass.getText() + Constant.string.COMMA_SEPARATOR + edtConfirmPass.getText()), Constant.number.ZERO);
            }
        });
        edtOldPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mIsOldError) tvErrorHints.setText(null);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtNewPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mIsNewError) tvErrorHints.setText(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtConfirmPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mIsNewError) tvErrorHints.setText(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * @param type        0：绑定 1：解绑
     * @param accountType 0：手机号码 1：QQ 2：WX 3：SINA
     *                    未绑定手机号的用户不能解绑其他账户 （后台API要求）
     */
    private void bindAccount(int type, int accountType) {
        mApiType = accountType;
        if (Constant.number.ZERO == type) {
            switch (accountType) {
                case Constant.number.ZERO:
                    showBindPhoneDialog();
                    break;
                case Constant.number.ONE:
                    //QQ登录
                    loginQQ();
                    break;
                case Constant.number.TWO:
                    loginWX();
                    break;
                case Constant.number.THREE:
                    MyToast.s(mContext, "绑定新浪");
                    loginSina();
                    break;
            }
        } else {
            //手机号一经绑定不能更换,没有绑定手机号不能解绑
            if (!mIsHadBindPhone) {
                MyToast.s(mContext, getString(R.string.unbind_error));
                return;
            } else showUnBindDialog(accountType);
        }
    }

    private void loginSina() {
        mIsSina = true;
        mSsoHandler = new SsoHandler(getActivity());
        mSsoHandler.authorize(new WbAuthListener());
    }

    private void loginWX() {
        UserUtil.setWxCallback(this);
        mWxApi = WXAPIFactory.createWXAPI(mContext, Constant.platformID.LOGIN_WX_ID, false);
        mWxApi.registerApp(Constant.platformID.LOGIN_WX_ID);
        if (!mWxApi.isWXAppInstalled()) {
            //打开微信下载界面
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://weixin.qq.com/"));
            if (null != intent) {
                //检查设备中可以响应的activity
                PackageManager pm = mContext.getPackageManager();
                List<ResolveInfo> resolveInfos = pm.queryIntentActivities(intent, 0);
                if (resolveInfos.size() > 0) {
                    //开启一个新的栈
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    MyToast.showToast(mContext, "您的手机暂未安装浏览器", Toast.LENGTH_SHORT);
                }
            }
            return;
        }
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "diandi_wx_login";
        mWxApi.sendReq(req);
    }

    private void loginQQ() {
        mIsQQ = true;
        mTencentAuthor = Tencent.createInstance(Constant.platformID.LOGIN_QQ_ID, mContext);
        if (!mTencentAuthor.isSessionValid())
            mTencentAuthor.login(this, "get_user_info", new QQListener(Constant.number.ZERO));
        else mTencentAuthor.logout(mContext);
    }

    private void toRegisterOrFind(int type) {
        Intent intent = new Intent();
        intent.setClass(mContext, TabWithPagerActivity.class);
        if (Constant.number.ZERO == type)
            intent.putExtra(Constant.string.ARG_ONE, Constant.number.NINE);
        else intent.putExtra(Constant.string.ARG_ONE, Constant.number.TEN);
        startActivity(intent);

    }

    /**
     * 解除绑定
     *
     * @param type 1：qq 2:微信 3：微博
     *             手机账户登录可以随意解绑，第三方登录用户
     */
    private void showUnBindDialog(final int type) {
        String hints = null;
        switch (type) {
            case Constant.number.ONE:
                hints = "QQ账号：" + tvQQNick.getText();
                break;
            case Constant.number.TWO:
                hints = "微信账号：" + tvWxNick.getText();
                break;
            case Constant.number.THREE:
                hints = "新浪账号：" + tvSinaNick.getText();
                break;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        TextView textView = new TextView(mContext);
        textView.setText(String.format(getString(R.string.unbind_hints), hints));
        textView.setTextColor(getResources().getColor(R.color.colorAccent));
        textView.setTextSize(13);
        textView.setPadding(10, 15, 10, 15);
        builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                unBindAccount(type);
            }
        });

        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.setView(textView).setCancelable(true).create();
        alertDialog.show();
    }

    private void unBindAccount(int type) {
        mUnBindTyep = type;
        new UserUnBindAccountPresenter(this).queryList(Constant.number.SIX, String.valueOf(type), Constant.number.ZERO);

    }

    private void showBindPhoneDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_bind_phone, null);
        final EditText edtPhone = (EditText) view.findViewById(R.id.edt_phone);
        final LinearLayout llCodeContain = (LinearLayout) view.findViewById(R.id.ll_code_contain);
        final EditText edtAuthCode = (EditText) view.findViewById(R.id.edt_auth_code);
        tvGetCode = (TextView) view.findViewById(R.id.tv_get_auth_code);
        TextView tvBind = (TextView) view.findViewById(R.id.tv_bind_phone);
        builder.setView(view);
        builder.setCancelable(true);
        final AlertDialog dialog = builder.create();
        edtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (Constant.number.ELEVEN == s.length()) llCodeContain.setVisibility(View.VISIBLE);
                else llCodeContain.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tvGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable phone = edtPhone.getText();
                if (TextUtils.isEmpty(phone) || Constant.number.ELEVEN != phone.length()) {
                    MyToast.s(mContext, getString(R.string.input_num_error));
                } else {
                    mTimer = new Timer(true);
                    new VerificationCodePresenter(UserFragment.this).queryList(Constant.number.FORE, phone.toString(), Constant.number.ZERO);
                }
            }
        });

        tvBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Editable phone = edtPhone.getText();
                Editable authCode = edtAuthCode.getText();
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(authCode) || Constant.number.ELEVEN != phone.length() || Constant.number.SIX != authCode.length()) {
                    MyToast.s(mContext, getString(R.string.input_error));
                    return;
                } else if (!mIsGetAuthCode) {
                    MyToast.s(mContext, getString(R.string.get_auth_code));
                    return;
                } else {
                    mBindPhone = phone;
                    new UserBindPresenter(UserFragment.this).queryList(Constant.number.FIVE, String.valueOf(phone + Constant.string.COMMA_SEPARATOR + authCode), Constant.number.ZERO);
                }
            }
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    /**
     * 更改用户的性别
     */
    private void showModifyGenderDialog() {
        mGenderWhich = 0;
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("请选择性别");
        String[] items = new String[]{"男", "女"};
        builder.setSingleChoiceItems(items, 0,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mGenderWhich = which;
                        LogUtil.i(mGenderWhich+"      "+which);
                    }
                });

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //判断是否与原来的性别相同，相同不设置
                int defaultGender = TextUtils.equals("男", UserUtil.getGender(mContext)) ? Constant.number.ZERO : Constant.number.ONE;
               LogUtil.i(defaultGender+"                "+mGenderWhich);
                if (defaultGender != mGenderWhich) {
                    changeUserInfoTOServer(Constant.number.ZERO, null);
                }
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }

    /**
     * 上传用户的性别
     */
    private void changeUserInfoTOServer(int type, String nick) {
        if (null == mUserModifyInfoPresenter) {
            mUserModifyInfoPresenter = new UserModifyInfoPresenter(this);
        }
        if (Constant.number.ZERO == type) {
            mUserModifyInfoPresenter.queryList(Constant.number.TWO, String.valueOf(mGenderWhich), Constant.number.ONE);
        } else {
            mModifyNick = nick;
            mUserModifyInfoPresenter.queryList(Constant.number.THREE, nick, Constant.number.ZERO);
        }

    }

    /**
     * 更改用户的昵称
     */
    private void showModifyNickDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        final EditText edit = new EditText(mContext);
        edit.setPadding(10, 10, 10, 10);
        edit.setHint("小樱最喜欢好听的名字了~");
        edit.setTextSize(12);
        edit.setHintTextColor(getResources().getColor(R.color.colorAccent));
        edit.setBackgroundColor(Color.TRANSPARENT);
        edit.requestFocus();
        builder.setView(edit);
        TextView text = new TextView(mContext);
        text.setTextColor(Color.BLACK);
        text.setTextSize(14);
        text.setPadding(10, 10, 10, 10);
        text.setText("修改昵称:");
        builder.setCustomTitle(text);
        builder.setPositiveButton("保存", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //设置网名
                String nick = edit.getText().toString().trim();
                if (TextUtils.equals(nick, SpUtil.getString(mContext, Constant.userInfo.USER_NICK, null))) {
                    MyToast.showToast(mContext, "和以前的昵称相同", Toast.LENGTH_LONG);
                    return;
                }
                if (TextUtils.isEmpty(nick)) {
                    MyToast.showToast(mContext, "昵称不能为空", Toast.LENGTH_LONG);
                    return;
                }
                if (!TextUtils.equals(nick, strFilter(nick))) {
                    MyToast.showToast(mContext, "昵称不能含有特殊字符", Toast.LENGTH_LONG);
                    return;
                }
                if (nick.length() <= 1) {
                    MyToast.showToast(mContext, "您设置的昵称过短~!", Toast.LENGTH_LONG);
                    return;
                }
                if (nick.length() > 8) {
                    MyToast.showToast(mContext, "您设置的昵称过长~!", Toast.LENGTH_LONG);
                    return;
                }
                changeUserInfoTOServer(Constant.number.ONE, nick);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

    }

    // 只允许字母、数字和汉字
    public String strFilter(String str) throws PatternSyntaxException {
        String regEx = "[^a-zA-Z0-9\u4E00-\u9FA5 _-]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }


    /**
     * 打开系统相机
     */
    private void openCamera() {
        File fileParent = new File(Environment.getExternalStorageDirectory(),
                "battle");
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
        File file = new File(fileParent.getAbsoluteFile(), "user_icon.jpg");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mTakePhotoUri = FileProvider.getUriForFile(mContext, mContext.getPackageName() + ".apkDownload", file);//通过FileProvider创建一个content类型的Uri
        } else {
            mTakePhotoUri = Uri.fromFile(file);
        }
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mTakePhotoUri);//将拍取的照片保存到指定URI
        getActivity().startActivityForResult(intent, Constant.number.HUNDRED_AND_TWO);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // SSO 授权回调
        if (mIsSina && mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
            mIsSina = false;
            return;
        }
        if (mIsQQ) {
            Tencent.onActivityResultData(requestCode, resultCode, data, new QQListener(Constant.number.ZERO));
            mIsQQ = false;
            return;
        }
        LogUtil.i("         返回结果    " + requestCode + "          " + resultCode);
        switch (requestCode) {
            //获取到图片
            case Constant.number.HUNDRED:
                if (resultCode == getActivity().RESULT_OK) {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT)
                        addPic(Constant.number.ZERO, data);
                    else addPic(Constant.number.ONE, data);
                } else MyToast.s(mContext, getString(R.string.had_cancel));
                break;
            //裁剪图片
            case Constant.number.HUNDRED_AND_ONE:
                if (resultCode == getActivity().RESULT_OK) {
                    String picFilePath;
                    Bitmap bitmap;
                    try {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = true;
                        if (mIsFromPic) {
                            picFilePath = imagePath;
//                            LogUtil.i("压缩前      " + new File(imagePath).length() / 1024 + "K");
                            if (new File(imagePath).length() > 1048576) {
                                BitmapFactory.decodeFile(imagePath, options);
                                options.inSampleSize = calculateInSampleSize(options, 100, 1000);
                                options.inPreferredConfig = Bitmap.Config.RGB_565;
                                options.inJustDecodeBounds = false;
                                bitmap = BitmapFactory.decodeFile(imagePath, options);
                            } else
                                bitmap = BitmapFactory.decodeFile(imagePath);
                        } else {
                            picFilePath = mTakePhotoUri.getPath();
                            InputStream inputStream = mContext.getContentResolver().openInputStream(mTakePhotoUri);
//                            LogUtil.i("压缩前      " + new File(mTakePhotoUri.getPath()).length() / 1024 + "K");
                            if (new File(mTakePhotoUri.getPath()).length() > 1048576) {
                                BitmapFactory.decodeStream(inputStream, new Rect(2, 2, 2, 2), options);
                                options.inSampleSize = calculateInSampleSize(options, 100, 1000);
                                options.inPreferredConfig = Bitmap.Config.RGB_565;
                                options.inJustDecodeBounds = false;
                                bitmap = BitmapFactory.decodeStream(inputStream, new Rect(2, 2, 2, 2), options);
                            } else
                                bitmap = BitmapFactory.decodeStream(inputStream);
                        }
                        ivUserIcon.setImageBitmap(bitmap);
                        zipFile(picFilePath);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else MyToast.s(mContext, getString(R.string.pic_crop_error));
                break;
            //拍照获取图片
            case Constant.number.HUNDRED_AND_TWO:
                if (resultCode == getActivity().RESULT_OK)
                    cropPhoto(mTakePhotoUri);
                else MyToast.s(mContext, getString(R.string.had_cancel));
                break;
        }
    }

    private void zipFile(final String picFilePath) {
        Luban.with(mContext)
                .load(picFilePath)                               // 传人要压缩的图片列表
                .ignoreBy(100)                                  // 忽略不压缩图片的大小
                .setTargetDir(Environment.getExternalStorageDirectory() + File.separator +
                        "battle" + File.separator)  // 设置压缩后文件存储位置
                .setCompressListener(new OnCompressListener() { //设置回调
                    @Override
                    public void onStart() {
                        //  压缩开始前调用，可以在方法内启动 loading UI
                    }

                    @Override
                    public void onSuccess(File file) {
                        // 压缩成功后调用，返回压缩后的图片文件
                        uploadFile(file.getAbsolutePath());
                        LogUtil.i("压缩后图片的大小   --------  " + (file.length() / 1024) + "K");
                    }

                    @Override
                    public void onError(Throwable e) {
                        //  当压缩过程出现问题时调用
                        e.printStackTrace();
                        uploadFile(picFilePath);

                    }
                }).launch();    //启动压缩
    }

    private void uploadFile(String path) {
        mUserPic = path;
        new UploadFilePresenter(this).queryList(Constant.number.ONE, path, Constant.number.ZERO);
    }

    /**
     * 指定图片的缩放比例
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(android.graphics.BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // 原始图片的宽、高
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        /**
         * 压缩方式一
         */
        // 计算压缩的比例：分为宽高比例
        final int heightRatio = Math.round((float) height
                / (float) reqHeight);
        final int widthRatio = Math.round((float) width / (float) reqWidth);
        inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
//      }
        return inSampleSize;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Constant.number.HUNDRED_AND_TWO:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mIsFromPic = false;
                    openCamera();
                } else {
                    MyToast.s(mContext, "您取消了授权,无法进行拍照");
                    picGone.setClickable(false);
                    llSelectPic.startAnimation(mOutAnimation);
                    llSelectPic.setVisibility(View.GONE);
                }
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void addPic(int one, Intent data) {
        Uri imageUri = null;
        switch (one) {
            case Constant.number.ZERO:
                imageUri = data.getData();
                imagePath = getImagePath(imageUri, null);
                break;
            case Constant.number.ONE:
                imagePath = null;
                imageUri = data.getData();
                if (DocumentsContract.isDocumentUri(mContext, imageUri)) {
                    String docId = DocumentsContract.getDocumentId(imageUri);
                    if ("com.android.providers.media.documents".equals(imageUri.getAuthority())) {
                        String id = docId.split(":")[1];
                        String selection = MediaStore.Images.Media._ID + "=" + id;
                        imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
                    } else if ("com.android.downloads.documents".equals(imageUri.getAuthority())) {
                        Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                        imagePath = getImagePath(contentUri, null);
                    }
                } else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
                    imagePath = getImagePath(imageUri, null);
                } else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
                    imagePath = imageUri.getPath();
                }
                cropPhoto(imageUri);
                break;
        }
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        //通过Uri和selection老获取真实的图片路径
        Cursor cursor = mContext.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }


    /**
     * 裁剪
     */
    private void cropPhoto(Uri uri) {
        File file = new File(Environment.getExternalStorageDirectory(),
                "battle" + File.separator + "user_icon_small.jpg");
        if (!mIsFromPic) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mTakePhotoUri = FileProvider.getUriForFile(mContext, mContext.getPackageName() + ".apkDownload", file);//通过FileProvider创建一个content类型的Uri
            } else {
                mTakePhotoUri = Uri.fromFile(file);
            }
        }
        Uri outputUri = Uri.fromFile(file);
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 720);
        intent.putExtra("outputY", 460);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        intent.putExtra("outputFormat",
                Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        getActivity().startActivityForResult(intent, Constant.number.HUNDRED_AND_ONE);
    }

    @Override
    public void getResult(Object dataBen, int type) {
        if (null == dataBen) {
            return;
        }
        switch (type) {
            //用户绑定的信息
            case Constant.number.ZERO:
                UserBindBean userBindBean = (UserBindBean) dataBen;
                setUserInfo(userBindBean);
                break;
            //用户上传的头像
            case Constant.number.ONE:
                SuccessBean userIcoBean = (SuccessBean) dataBen;
                if (TextUtils.equals("200", userIcoBean.getStatus())) {
                    MyToast.s(mContext, "头像更换成功,正在审核");
                }else MyToast.s(mContext, userIcoBean.getInfo());
                break;
            //用户修改性别
            case Constant.number.TWO:
                SuccessBean userSexBean = (SuccessBean) dataBen;
                MyToast.s(mContext, userSexBean.getInfo());
                if (TextUtils.equals("200", userSexBean.getStatus())) {
                    String gender = mGenderWhich == Constant.number.ZERO ? "男" : "女";
                    tvUserGender.setText(gender);
                    UserUtil.setGender(mContext,gender);
                }
                break;
            //用户修改昵称
            case Constant.number.THREE:
                SuccessBean userNickBean = (SuccessBean) dataBen;
                MyToast.s(mContext, userNickBean.getInfo());
                if (TextUtils.equals("200", userNickBean.getStatus())) {
                    tvUserNick.setText(mModifyNick);
                    SpUtil.putString(mContext, Constant.userInfo.USER_NICK, mModifyNick);
                }
                break;
            //手机号码绑定验证码
            case Constant.number.FORE:
                SuccessBean authCodeBean = (SuccessBean) dataBen;
                MyToast.s(mContext, authCodeBean.getInfo());
                if (TextUtils.equals("200", authCodeBean.getStatus())) {
                    mIsGetAuthCode = true;
                    tvGetCode.setClickable(false);
                    startCountDown();
                }
                break;
            //手机号码绑定
            case Constant.number.FIVE:
                SuccessBean PhoneBean = (SuccessBean) dataBen;
                MyToast.s(mContext, PhoneBean.getInfo());
                if (TextUtils.equals("200", PhoneBean.getStatus())) {
                    tvPhoneState.setText(mBindPhone);
                    mIsHadBindPhone = true;
                }
                break;
            //第三方解除绑定
            case Constant.number.SIX:
                SuccessBean OtherAccountBean = (SuccessBean) dataBen;
                MyToast.s(mContext, OtherAccountBean.getInfo());
                if (TextUtils.equals("200", OtherAccountBean.getStatus())) {
                    //清除该第三方的状态（头像和昵称）
                    clearApiBindInfo();
                }
                break;
            //密码修改
            case Constant.number.SEVEN:
                SuccessBean PasswordModifyBean = (SuccessBean) dataBen;
                MyToast.s(mContext, PasswordModifyBean.getInfo());
                if (TextUtils.equals("200", PasswordModifyBean.getStatus())) {
                    //前往登录,清空用户当前的数据
                    UserUtil.exit(mContext);
                    Intent intent = new Intent(mContext, TabWithPagerActivity.class);
                    intent.putExtra(Constant.string.ARG_ONE, Constant.number.EIGHT);
                    startActivity(intent);
                }
                break;
            //第三方绑定当前账户
            case Constant.number.EIGHT:
                SuccessBean apiBindAccountNow = (SuccessBean) dataBen;
                MyToast.s(mContext, apiBindAccountNow.getInfo());
                if (TextUtils.equals("200", apiBindAccountNow.getStatus())) {
                    switch (mApiType){
                        case Constant.number.ONE:
                            ivQQ.setVisibility(View.VISIBLE);
                            ivQQ.setImageURI(mApiIcon);
                            tvQQNick.setText(mApiName);
                            break;
                        case Constant.number.TWO:
                            ivWx.setVisibility(View.VISIBLE);
                            ivWx.setImageURI(mApiIcon);
                            tvWxNick.setText(mApiName);
                            break;
                        case Constant.number.THREE:
                            ivSina.setVisibility(View.VISIBLE);
                            ivSina.setImageURI(mApiIcon);
                            tvSinaNick.setText(mApiName);
                            break;
                    }
                }
                break;
            //获取新浪用户的信息
            case Constant.number.NINE:
                SinaUserBean sinaUserBean = (SinaUserBean) dataBen;
                if (null != sinaUserBean) {
                    mApiId = String.valueOf(sinaUserBean.getId());
                    mApiName = sinaUserBean.getName();
                    String gender = TextUtils.equals(sinaUserBean.getGender(), "m") ? "男" : "女";
                    UserUtil.setGender(mContext,gender);
                    mApiIcon = sinaUserBean.getProfile_image_url();
                    //和后台交互，实现登录
                    new ApiBindAccountPresenter(this).queryList(Constant.number.EIGHT,
                            String.valueOf(mApiType + Constant.string.COMMA_SEPARATOR + mApiId + Constant.string.COMMA_SEPARATOR + TxtFormatUtil.escape(mApiName) + Constant.string.COMMA_SEPARATOR + mApiIcon), Constant.number.ZERO);
                }
                break;

        }
    }

    @Override
    public void failRequest() {

    }

    private void clearApiBindInfo() {
        switch (mUnBindTyep){
            case Constant.number.ONE:
                tvQQNick.setText(getString(R.string.no_bind));
                ivQQ.setVisibility(View.INVISIBLE);
                mIsHadBindQq = false;
                break;
            //wx
            case Constant.number.TWO:
                tvWxNick.setText(getString(R.string.no_bind));
                ivWx.setVisibility(View.INVISIBLE);
                mIsHadBindWx = false;
                break;
            //sina
            case Constant.number.THREE:
                tvSinaNick.setText(getString(R.string.no_bind));
                ivSina.setVisibility(View.INVISIBLE);
                mIsHadBindSina = false;
                break;
        }
    }

    private void startCountDown() {
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        --mReTime;
                        tvGetCode.setText(mReTime + "S");
                        if (mReTime < 0) {
                            mTimer.cancel();
                            tvGetCode.setClickable(true);
                            tvGetCode.setText(getString(R.string.get_auth_code));
                            mReTime = 60;
                        }
                    }
                });
            }
        }, 1000, 1000);
    }

    @Override
    public void errorRequest() {

    }

    private void setUserInfo(UserBindBean userInfo) {
        if (null == userInfo) {
            return;
        }
        String phone = userInfo.getUsertel();
        if (!TextUtils.isEmpty(phone)) {
            tvPhoneState.setText(phone);
            mIsHadBindPhone = true;
        }
        List<UserBindBean.ItemsBean> items = userInfo.getItems();
        if (null != items && Constant.number.ZERO < items.size()) {
            for (UserBindBean.ItemsBean bind : items) {
                switch (bind.getType()) {
                    //qq
                    case Constant.string.ONE:
                        tvQQNick.setText(bind.getName());
                        ivQQ.setImageURI(bind.getPic());
                        mIsHadBindQq = true;
                        break;
                    //wx
                    case Constant.string.TWO:
                        tvWxNick.setText(bind.getName());
                        ivWx.setImageURI(bind.getPic());
                        mIsHadBindWx = true;
                        break;
                    //sina
                    case Constant.string.THREE:
                        tvSinaNick.setText(bind.getName());
                        ivSina.setImageURI(bind.getPic());
                        mIsHadBindSina = true;
                        break;
                }
            }
        }
    }

    public class QQListener implements IUiListener {
        int type;

        public QQListener(int typeCode) {
            type = typeCode;
        }

        @Override
        public void onComplete(Object o) {
            switch (type) {
                case Constant.number.ZERO:
                    authorQQ(o);
                    break;
                case Constant.number.ONE:
                    formatQQInfo(o);
                    break;
            }
        }

        @Override
        public void onError(UiError uiError) {
            switch (type) {
                case Constant.number.ZERO:
                    Log.e("QQ_LOGIN_ERROR", "qq登录信息错误");
                    Toast.makeText(mContext, "qq登录信息错误", Toast.LENGTH_SHORT).show();
                    break;
                case Constant.number.ONE:
                    Log.e("GET_QQ_INFO_ERROR", "获取qq用户信息错误");
                    Toast.makeText(mContext, "获取qq用户信息错误", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        @Override
        public void onCancel() {
            switch (type) {
                case Constant.number.ZERO:
                    Log.e("QQ_LOGIN_ERROR", "qq登录取消");
                    Toast.makeText(mContext, "qq登录取消", Toast.LENGTH_SHORT).show();
                    break;
                case Constant.number.ONE:
                    Log.e("GET_QQ_INFO_CANCEL", "获取qq用户信息取消");
                    Toast.makeText(mContext, "获取qq用户信息取消", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    private void formatQQInfo(Object o) {
        JSONObject userInfo = (JSONObject) o;
        int ret = userInfo.optInt("ret");
        if(ret == 100030){
            //再次申请
            getActivity().runOnUiThread(new Runnable(){

                @Override
                public void run() {
                    mTencentAuthor.reAuth(getActivity(), "all", new QQListener(Constant.number.ONE));
                }
            });
            return;
        }
        //获取头像 ，昵称 ，性别 ，城市 头像
        mApiIcon = userInfo.optString("figureurl_qq_2");
        mApiName = userInfo.optString("nickname", "画姑娘");
        String gender = userInfo.optString("gender", "男");
        String province = userInfo.optString("province", "湖北");
        String city = userInfo.optString("city", "武汉");
        UserUtil.setGender(mContext,gender);
        //前往绑定
        new ApiBindAccountPresenter(this).queryList(Constant.number.EIGHT,mApiType+Constant.string.COMMA_SEPARATOR+mApiId+Constant.string.COMMA_SEPARATOR+TxtFormatUtil.escape(mApiName) + Constant.string.COMMA_SEPARATOR + mApiIcon,Constant.number.ZERO);
    }


    /**
     * 获取腾讯登录用户的信息
     *
     * @param o
     */
    private void authorQQ(Object o) {
        LogUtil.i("   "+o.toString());
        JSONObject jsonObject = (JSONObject) o;
        try {
            mApiId = jsonObject.getString("openid");
            String token = jsonObject.getString("access_token");
            String expires = jsonObject.getString("expires_in");
            if (null == mTencentAuthor) {
                mTencentAuthor = Tencent.createInstance(Constant.platformID.LOGIN_QQ_ID, mContext);
            }
            mTencentAuthor.setAccessToken(token, expires);
            mTencentAuthor.setOpenId(mApiId);
        } catch (JSONException e) {
            e.printStackTrace();
            MyToast.showToast(mContext, "腾讯签证解析失败", Toast.LENGTH_LONG);
            return;
        }
        //获取QQ用户的头像昵称等信息
        getQQUserInfo();
    }

    private void getQQUserInfo() {
        //sdk给我们提供了一个类UserInfo，这个类中封装了QQ用户的一些信息，我么可以通过这个类拿到这些信息
        QQToken mQQToken = mTencentAuthor.getQQToken();
        UserInfo userInfo = new UserInfo(mContext, mQQToken);
        userInfo.getUserInfo(new QQListener(Constant.number.ONE));
    }

    private class WbAuthListener implements com.sina.weibo.sdk.auth.WbAuthListener {
        @Override
        public void onSuccess(final Oauth2AccessToken token) {
            LogUtil.i(token.toString());
            mAccessToken = token;
            if (mAccessToken.isSessionValid()) {
                // 保存 Token 到 SharedPreferences
                AccessTokenKeeper.writeAccessToken(mContext, mAccessToken);
                new SinaInfoPresenter(UserFragment.this).queryList(Constant.number.NINE, mAccessToken.getToken() + Constant.string.COMMA_SEPARATOR + mAccessToken.getUid(), Constant.number.ZERO);
            }
        }


        @Override
        public void cancel() {
            LogUtil.i("授权被终止");
            MyToast.showToast(mContext, "授权被终止", Toast.LENGTH_LONG);
        }


        @Override
        public void onFailure(WbConnectErrorMessage errorMessage) {
            LogUtil.i("授权失败" + errorMessage.getErrorCode() + "      " + errorMessage.getErrorMessage());
            MyToast.showToast(mContext, "授权失败", Toast.LENGTH_LONG);
        }
    }


    @Override
    public void successGetInfo(String result) {
        if(!TextUtils.isEmpty(result)){
            String[] split = result.split(Constant.string.COMMA_SEPARATOR);
            mApiId = split[0];
            mApiName = split[1];
            mApiIcon = split[2];
            new ApiBindAccountPresenter(this).queryList(Constant.number.EIGHT,mApiType+Constant.string.COMMA_SEPARATOR+mApiId+Constant.string.COMMA_SEPARATOR+TxtFormatUtil.escape(mApiName) + Constant.string.COMMA_SEPARATOR + mApiIcon,Constant.number.ZERO);
        }
    }

    @Override
    public void recycleMemory() {
        mBind.unbind();
        if(null != mTimer){
            mTimer.cancel();
            mTimer = null;
        }
    }
}
