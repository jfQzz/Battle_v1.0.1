package com.wangxia.battle.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import com.wangxia.battle.presenter.impPresenter.ApiLoginPresenter;
import com.wangxia.battle.presenter.impPresenter.ApiRegisterPresenter;
import com.wangxia.battle.presenter.impPresenter.LoginPresenter;
import com.wangxia.battle.presenter.impPresenter.SinaInfoPresenter;
import com.wangxia.battle.presenter.impPresenter.UserBindPresenter;
import com.wangxia.battle.presenter.impPresenter.UserInfoPresenter;
import com.wangxia.battle.presenter.impPresenter.VerificationCodePresenter;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.LogUtil;
import com.wangxia.battle.util.MyToast;
import com.wangxia.battle.util.SpUtil;
import com.wangxia.battle.util.TxtFormatUtil;
import com.wangxia.battle.util.UserUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 昝奥博 on 2017/11/11 0011
 * Email:18772833900@163.com
 * Explain：登录
 */
public class LoginFragment extends LazyBaseFragment implements View.OnClickListener, ISuccessCallbackData, RadioGroup.OnCheckedChangeListener, ICallbackWxInfo {


    @BindView(R.id.edt_account)
    EditText edtAccount;
    @BindView(R.id.iv_del_account)
    ImageView ivDelAccount;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.iv_del_password)
    ImageView ivDelPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_create_account)
    TextView tvCreateAccount;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.ll_function)
    LinearLayout llFunction;
    @BindView(R.id.tv_login_xl)
    TextView tvLoginXl;
    @BindView(R.id.tv_login_wx)
    TextView tvLoginWx;
    @BindView(R.id.tv_login_qq)
    TextView tvLoginQq;
    private Unbinder mBind;
    private Tencent mTencentAuthor;
    private IWXAPI mWxApi;
    private SsoHandler mSsoHandler;
    private Oauth2AccessToken mAccessToken;
    private boolean mIsSina;
    private String mSinaLocation;
    private TextView tvFreshMem;
    private TextView tvOldMem;
    private EditText edtOldAccount;
    private Button btnBindOld;
    private LinearLayout llOldContain;
    private AlertDialog mJudgeUserDialog;
    private EditText edtPassOrCode;
    private LinearLayout llConfirmContain;
    private RadioGroup rgAccountType;
    private TextView tvBindCode;
    private View lineJudge;
    private String mApiId;
    private String mApiName;
    private String mApiIcon;
    private int mApiType;
    private boolean mIsPhoneOrUserId;
    private boolean mIsBindOldAccount;
    private Timer mTimer;
    private int mReTime = 60;
    private boolean mIsNeedUserInfo;


    public static LoginFragment newInstance(boolean needInfo) {
        Bundle args = new Bundle();
        args.putBoolean(Constant.string.ARG_ONE,needInfo);
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View initView() {
        mIsNeedUserInfo = getArguments().getBoolean(Constant.string.ARG_ONE,false);
        View view = View.inflate(mContext, R.layout.fragment_login, null);
        mBind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        ivDelAccount.setOnClickListener(this);
        ivDelPassword.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        tvCreateAccount.setOnClickListener(this);
        tvForgetPassword.setOnClickListener(this);
        tvLoginXl.setOnClickListener(this);
        tvLoginWx.setOnClickListener(this);
        tvLoginQq.setOnClickListener(this);
        listenerAccount();
        listenerPassword();
    }


    private void listenerAccount() {
        edtAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) ivDelAccount.setVisibility(View.GONE);
                else ivDelAccount.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void listenerPassword() {
        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) ivDelPassword.setVisibility(View.GONE);
                else ivDelPassword.setVisibility(View.VISIBLE);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void recycleMemory() {
        mBind.unbind();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_del_account:
                edtAccount.setText(null);
                break;
            case R.id.iv_del_password:
                edtPassword.setText(null);
                break;
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_create_account:
                toRegisterOrFind(Constant.number.ZERO);
                break;
            case R.id.tv_forget_password:
                toRegisterOrFind(Constant.number.ONE);
                break;
            case R.id.tv_login_xl:
                mApiType = Constant.number.THREE;
                loginSina();
                break;
            case R.id.tv_login_wx:
                mApiType = Constant.number.TWO;
                loginWX();
                break;
            case R.id.tv_login_qq:
                mApiType = Constant.number.ONE;
                loginQQ();
                break;
            //新用户注册
            case R.id.tv_fresh_member:
                new ApiRegisterPresenter(this).queryList(Constant.number.THREE,
                        String.valueOf(mApiType + Constant.string.COMMA_SEPARATOR + mApiId + Constant.string.COMMA_SEPARATOR + TxtFormatUtil.escape(mApiName) + Constant.string.COMMA_SEPARATOR + mApiIcon), Constant.number.TWO);
                if (null != mJudgeUserDialog) {
                    mJudgeUserDialog.dismiss();
                }
                break;
            case R.id.tv_old_member:
                tvFreshMem.setVisibility(View.GONE);
                lineJudge.setVisibility(View.GONE);
                llOldContain.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_get_auth_code:
                if (mIsPhoneOrUserId && null != edtOldAccount && !TextUtils.isEmpty(edtOldAccount.getText()) && Constant.number.ELEVEN == edtOldAccount.getText().length()) {
                    //第三方绑定手机手机号,获取验证码
                    mIsBindOldAccount = true;
                    new VerificationCodePresenter(this).queryList(Constant.number.FIVE, String.valueOf(edtOldAccount.getText() + Constant.string.COMMA_SEPARATOR + mApiId), Constant.number.ZERO);
                } else {
                    MyToast.s(mContext, getString(R.string.input_num_error));
                }
                break;
            //绑定老账户
            case R.id.btn_bind_old_account:
                if(mIsPhoneOrUserId){
                    if(!TextUtils.isEmpty(edtOldAccount.getText()) && Constant.number.ELEVEN == edtOldAccount.getText().length() && !TextUtils.isEmpty(edtPassOrCode.getText())){
                        mJudgeUserDialog.dismiss();
                        //手机号绑定
                        new UserBindPresenter(this).queryList(Constant.number.SIX, String.valueOf(mApiType+Constant.string.COMMA_SEPARATOR+mApiId+Constant.string.COMMA_SEPARATOR+mApiName+Constant.string.COMMA_SEPARATOR+mApiIcon+Constant.string.COMMA_SEPARATOR+edtOldAccount.getText() + Constant.string.COMMA_SEPARATOR + edtPassOrCode.getText()), Constant.number.ONE);
                        return;
                    }else {
                        MyToast.s(mContext,"请获取验证码");
                        return;
                    }

                }else {
                    if(!TextUtils.isEmpty(edtOldAccount.getText()) && Constant.number.SIX == edtOldAccount.getText().length() && !TextUtils.isEmpty(edtPassOrCode.getText())){
                        mJudgeUserDialog.dismiss();
                        //API账户绑定
                        new UserBindPresenter(this).queryList(Constant.number.SIX, String.valueOf(mApiType+Constant.string.COMMA_SEPARATOR+mApiId+Constant.string.COMMA_SEPARATOR+mApiName+Constant.string.COMMA_SEPARATOR+mApiIcon+Constant.string.COMMA_SEPARATOR+edtOldAccount.getText() + Constant.string.COMMA_SEPARATOR + edtPassOrCode.getText()), Constant.number.TWO);
                        return;
                    }
                }
                MyToast.s(mContext,"您输入的信息不完整");
                break;
        }
    }

    private void loginSina() {
        // 从 SharedPreferences 中读取上次已保存好 AccessToken 等信息，
        // 第一次启动本应用，AccessToken 不可用
//        mAccessToken = AccessTokenKeeper.readAccessToken(mContext);
//        if (mAccessToken.isSessionValid()) {
//            if (!TextUtils.isEmpty(mAccessToken.getRefreshToken())) {
//                AccessTokenKeeper.refreshToken(Constant.platformID.LOGIN_SINA_KEY, mContext, new RequestListener() {
//                    @Override
//                    public void onComplete(String s) {
//                        Oauth2AccessToken oauth2AccessToken = Oauth2AccessToken.parseAccessToken(s);
//                        AccessTokenKeeper.writeAccessToken(mContext, oauth2AccessToken);
//                        if (null != oauth2AccessToken) {
//                            new SinaInfoPresenter(LoginFragment.this).queryList(Constant.number.ONE, oauth2AccessToken.getToken() + Constant.string.COMMA_SEPARATOR + oauth2AccessToken.getUid(), Constant.number.ZERO);
//                        } else {
//                            //为空重新登录
//                        }
//                    }
//
//                    @Override
//                    public void onWeiboException(WeiboException e) {
//                        e.printStackTrace();
//                        //如果失败了，重新登录
//
//                    }
//                });
//                updateTokenView();
//            } else {
//                //重新登录
//
//            }
//        } else {
//        }
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
        mIsSina = false;
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
     * 检查账户和密码是否符合要求然后登录
     */
    private void login() {
        if (TextUtils.isEmpty(edtAccount.getText()) || TextUtils.isEmpty(edtPassword.getText())|| !(Constant.number.SIX == edtAccount.getText().length() || Constant.number.ELEVEN == edtAccount.getText().length())) {
            MyToast.showToast(mContext, getString(R.string.input_error), Toast.LENGTH_LONG);
            return;
        } else {
            //登录操作
            LoginPresenter mLoginPresenter = new LoginPresenter(this);
            mLoginPresenter.queryList(Constant.number.ZERO, String.valueOf(edtAccount.getText().toString() + Constant.string.COMMA_SEPARATOR + edtPassword.getText().toString()), Constant.number.ZERO);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // SSO 授权回调
        if (mIsSina && mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
        if (!mIsSina) {
            Tencent.onActivityResultData(requestCode, resultCode, data, new QQListener(Constant.number.ZERO));
        }
    }

    @Override
    public void successGetInfo(String result) {
        //获取到微信登录的数据
        if (!TextUtils.isEmpty(result)) {
            String[] split = result.split(Constant.string.COMMA_SEPARATOR);
            mApiId = split[0];
            mApiName = split[1];
            mApiIcon = split[2];
            //前往登录
            new ApiLoginPresenter(this).queryList(Constant.number.TWO,
                    String.valueOf(mApiType + Constant.string.COMMA_SEPARATOR + mApiId + Constant.string.COMMA_SEPARATOR + TxtFormatUtil.escape(split[1]) + Constant.string.COMMA_SEPARATOR + split[2]), Constant.number.ZERO);
        } else {
            //微信的数据获取失败
            MyToast.s(mContext, "微信账户读取失败");
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
        //没有授权再次申请
        if(ret == 100030){
            getActivity().runOnUiThread(new Runnable(){

                @Override
                public void run() {
                    mTencentAuthor.reAuth(getActivity(), "all", new QQListener(Constant.number.ONE));
                }
            });
            return;
        }
        //获取头像 ，昵称 ，性别 ，城市 头像
        String headUrl = userInfo.optString("figureurl_qq_2");
        String nick = userInfo.optString("nickname", "画姑娘");
        String gender = userInfo.optString("gender", "男");
        String province = userInfo.optString("province", "湖北");
        String city = userInfo.optString("city", "武汉");
        UserUtil.setGender(mContext,gender);
        mApiIcon = headUrl;
        mApiName = nick;
        //前往登录
        new ApiLoginPresenter(this).queryList(Constant.number.TWO,
                String.valueOf(mApiType + Constant.string.COMMA_SEPARATOR + mApiId + Constant.string.COMMA_SEPARATOR + TxtFormatUtil.escape(nick) + Constant.string.COMMA_SEPARATOR + headUrl), Constant.number.ZERO);
    }


    /**
     * 获取腾讯登录用户的信息
     *
     * @param o
     */
    private void authorQQ(Object o) {
        LogUtil.i("       QQ签名      "+o.toString());
        JSONObject jsonObject = (JSONObject) o;
        try {
            mApiId = jsonObject.getString("openid");
            String token = jsonObject.getString("access_token");
            String expires = jsonObject.getString("expires_in");
            if (null == mTencentAuthor) {
                mTencentAuthor = Tencent.createInstance(Constant.platformID.LOGIN_QQ_ID, mContext);
            }
            mTencentAuthor.setOpenId(mApiId);
            mTencentAuthor.setAccessToken(token, expires);
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
                MyToast.showToast(mContext, "授权成功", Toast.LENGTH_SHORT);
                new SinaInfoPresenter(LoginFragment.this).queryList(Constant.number.ONE, mAccessToken.getToken() + Constant.string.COMMA_SEPARATOR + mAccessToken.getUid(), Constant.number.ZERO);
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


    /**
     * 显示当前 Token 信息。
     */
    private void updateTokenView() {
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(
                new java.util.Date(mAccessToken.getExpiresTime()));
        LogUtil.i(mAccessToken.getToken() + "          " + date);
    }


    @Override
    public void getResult(Object dataBen, int type) {
        if (null == dataBen) {
            return;
        }
        switch (type) {
            //普通账户登录
            case Constant.number.ZERO:
                SuccessBean bean = (SuccessBean) dataBen;
                switch (bean.getStatus()) {
                    //200：登陆成功
                    case "200":
                        MyToast.safeShow(mContext, getString(R.string.login_success));
                        SpUtil.putBoolean(mContext, Constant.userInfo.USER_STATE, true);
                        if(mIsNeedUserInfo){
                            getUerInfo();
                        }else {
                            getActivity().finish();
                        }
                        break;
//                    //211：账号或密码错误
//                    case "211":
//                        MyToast.s(mContext, getString(R.string.account_password_error));
//                        break;
//                    // 222：账户被锁定，请联系管理员
//                    case "222":
//                        MyToast.s(mContext, getString(R.string.account_locked));
//                        break;
//                    // 223：登陆错误
//                    case "223":
//                        break;
                    default:
                        MyToast.s(mContext, getString(R.string.login_error));
                        break;
                }
                break;
            //微博登录获取用户的信息
            case Constant.number.ONE:
                SinaUserBean sinaUserBean = (SinaUserBean) dataBen;
                if (null != sinaUserBean) {
                    mApiId = String.valueOf(sinaUserBean.getId());
                    mApiName = sinaUserBean.getName();
                    String gender = TextUtils.equals(sinaUserBean.getGender(), "m") ? "男" : "女";
                    UserUtil.setGender(mContext,gender);
                    mSinaLocation = sinaUserBean.getLocation();
                    mApiIcon = sinaUserBean.getProfile_image_url();
                    //和后台交互，实现登录
                    new ApiLoginPresenter(this).queryList(Constant.number.TWO,
                            String.valueOf(mApiType + Constant.string.COMMA_SEPARATOR + mApiId + Constant.string.COMMA_SEPARATOR + TxtFormatUtil.escape(mApiName) + Constant.string.COMMA_SEPARATOR + mApiIcon), Constant.number.ZERO);
                }
                break;
            //第三方登录
            case Constant.number.TWO:
                SuccessBean loginBean = (SuccessBean) dataBen;
                if (null != loginBean) {
                    switch (loginBean.getStatus()) {
                        //已绑定登录完成
                        case "200":
                            UserUtil.login(mContext);
                            if(mIsNeedUserInfo){
                                getUerInfo();
                            }else {
                                getActivity().finish();
                            }
                            break;
                        //已经注册但是未绑定
                        case "222":
                            //弹出dialog,选择是注册新用户还是绑定以前的账户
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showJudgeUserDialog();
                                }
                            });
                            break;
                        default:
                            MyToast.s(mContext, "错误终止码:" + loginBean.getStatus() + "原因：" + loginBean.getInfo());
                            break;
                    }
                }
                break;
            //第三方注册
            case Constant.number.THREE:
                SuccessBean registerBean = (SuccessBean) dataBen;
                MyToast.s(mContext, registerBean.getInfo());
                if (TextUtils.equals("200", registerBean.getStatus())) {
                    MyToast.s(mContext,"注册成功");
                    UserUtil.login(mContext);
                    if(mIsNeedUserInfo){
                        getUerInfo();
                    }else {
                        getActivity().finish();
                    }
                }
                break;
            //绑定老账户获取验证码
            case Constant.number.FIVE:
                SuccessBean verificationBean = (SuccessBean) dataBen;
                MyToast.s(mContext, verificationBean.getInfo());
                if (TextUtils.equals("200", verificationBean.getStatus())) {
                    mTimer = new Timer(true);
                    //开启倒计时
                    tvBindCode.setClickable(false);
                    startCountDown();
                }
                break;
            //api登录绑定老账户(成功与否)
            case Constant.number.SIX:
                SuccessBean successBean = (SuccessBean) dataBen;
                MyToast.s(mContext, successBean.getInfo());
                if (TextUtils.equals("200", successBean.getStatus())) {
                    UserUtil.login(mContext);
                    if(mIsNeedUserInfo){
                        getUerInfo();
                    }else {
                        getActivity().finish();
                    }
                }
                break;
            case Constant.number.SEVEN:
                com.wangxia.battle.model.bean.UserInfo userInfo = (com.wangxia.battle.model.bean.UserInfo) dataBen;
                if (null != userInfo) {
                    getActivity().finish();
                    if (TextUtils.equals("200", userInfo.getStatus())) {
                        SpUtil.putString(mContext, Constant.userInfo.USER_NICK, userInfo.getUsernike());
                        SpUtil.putString(mContext, Constant.userInfo.USER_ICON, userInfo.getUserpic());
                        SpUtil.putString(mContext, Constant.userInfo.USER_ID, userInfo.getUserid());
                        SpUtil.putString(mContext, Constant.userInfo.USER_TYPE, userInfo.getIspass());
                        String gender = TextUtils.equals("1",userInfo.getUsersex()) ? "男":"女";
                        SpUtil.putString(mContext, Constant.userInfo.USER_GENDER, gender);
                        SpUtil.putString(mContext, Constant.userInfo.USER_LOGIN_TIME, getString(R.string.last_login_time) + userInfo.getUserretime());
                    }
                }
                break;
        }
    }

    @Override
    public void failRequest() {

    }

    @Override
    public void errorRequest() {

    }
    public void getUerInfo() {
        UserInfoPresenter userInfoPresenter = new UserInfoPresenter(this);
        userInfoPresenter.queryList(Constant.number.SEVEN, UserUtil.getCookies(), Constant.number.ZERO);
    }



    private void startCountDown() {
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        --mReTime;
                        tvBindCode.setText(mReTime + "S");
                        if (mReTime < 0) {
                            mTimer.cancel();
                            tvBindCode.setClickable(true);
                            tvBindCode.setText(getString(R.string.get_auth_code));
                            mReTime = 60;
                        }
                    }
                });
            }
        }, 1000, 1000);
    }

    /**
     * 用户选择个人的账户, 注册新账户 或者绑定旧账户
     */
    private void showJudgeUserDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_judge_user, null);
        tvFreshMem = (TextView) view.findViewById(R.id.tv_fresh_member);
        tvOldMem = (TextView) view.findViewById(R.id.tv_old_member);
        lineJudge = view.findViewById(R.id.view_separator);
        rgAccountType = (RadioGroup) view.findViewById(R.id.rg_account_type);
        llOldContain = (LinearLayout) view.findViewById(R.id.ll_old_contain);
        edtOldAccount = (EditText) view.findViewById(R.id.edt_old_account);
        llConfirmContain = (LinearLayout) view.findViewById(R.id.ll_confirm_contain);
        edtPassOrCode = (EditText) view.findViewById(R.id.edt_verification_code_or_pass);
        tvBindCode = (TextView) view.findViewById(R.id.tv_get_auth_code);
        btnBindOld = (Button) view.findViewById(R.id.btn_bind_old_account);
        tvFreshMem.setOnClickListener(this);
        tvOldMem.setOnClickListener(this);
        tvBindCode.setOnClickListener(this);
        btnBindOld.setOnClickListener(this);
        rgAccountType.setOnCheckedChangeListener(this);
        builder.setView(view);
        mJudgeUserDialog = builder.create();
        mJudgeUserDialog.show();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        btnBindOld.setVisibility(View.VISIBLE);
        edtOldAccount.setVisibility(View.VISIBLE);
        llConfirmContain.setVisibility(View.VISIBLE);
         edtOldAccount.setText(null);
        edtPassOrCode.setText(null);
        switch (checkedId) {
            case R.id.rb_phone:
                tvBindCode.setVisibility(View.VISIBLE);
                edtPassOrCode.setHint("请输入验证码");
                mIsPhoneOrUserId = true;
                break;
            case R.id.rb_api:
                tvBindCode.setVisibility(View.GONE);
                edtPassOrCode.setHint("请输入密码");
                mIsPhoneOrUserId = false;
                break;
        }
    }


}
