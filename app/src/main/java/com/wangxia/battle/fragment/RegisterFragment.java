package com.wangxia.battle.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wangxia.battle.R;
import com.wangxia.battle.activity.HomeActivity;
import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.fragment.base.LazyBaseFragment;
import com.wangxia.battle.model.bean.SuccessBean;
import com.wangxia.battle.presenter.impPresenter.RegisterPresenter;
import com.wangxia.battle.presenter.impPresenter.VerificationCodePresenter;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.MyToast;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 昝奥博 on 2017/11/11 0011
 * Email:18772833900@163.com
 * Explain：账户注册
 */
public class RegisterFragment extends LazyBaseFragment implements View.OnClickListener, ISuccessCallbackData {

    @BindView(R.id.edt_account)
    EditText edtAccount;
    @BindView(R.id.iv_del_account)
    ImageView ivDelAccount;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.iv_show_password)
    ImageView ivShowPassword;
    @BindView(R.id.edt_auth_code)
    EditText edtAuthCode;
    @BindView(R.id.tv_get_auth_code)
    TextView tvGetAuthCode;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_user_register_deal)
    TextView tvUserRegisterDeal;
    private Unbinder mBind;
    private boolean mIsShowPassword;
    private Timer mTimer;
    private int mReTime = 60;
    private boolean mIsGetVerificationCode;

    public static RegisterFragment newInstance() {
        Bundle args = new Bundle();
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_register, null);
        mBind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        tvUserRegisterDeal.setText(Html.fromHtml("轻触上面的“立即注册”按钮，即表示您同意<br><font color='#14b9c8'>《网侠科技用户协议》</font>"));
    }

    @Override
    public void initListener() {
        ivDelAccount.setOnClickListener(this);
        ivShowPassword.setOnClickListener(this);
        tvGetAuthCode.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        tvUserRegisterDeal.setOnClickListener(this);
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
                if (TextUtils.isEmpty(s)) ivShowPassword.setVisibility(View.GONE);
                else ivShowPassword.setVisibility(View.VISIBLE);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void recycleMemory() {
        mBind.unbind();
        if (null != mTimer) {
            mTimer.cancel();
            mTimer = null;
        }
        getActivity().finish();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_del_account:
                edtAccount.setText(null);
                break;
            case R.id.iv_show_password:
                if (mIsShowPassword) {
                    mIsShowPassword = false;
                    ivShowPassword.setImageResource(R.drawable.ic_eye_close);
                    edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                } else {
                    mIsShowPassword = true;
                    ivShowPassword.setImageResource(R.drawable.ic_eye_open);
                    edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                break;
            //获取验证码
            case R.id.tv_get_auth_code:
                if (TextUtils.isEmpty(edtAccount.getText()))
                    MyToast.showToast(mContext, getString(R.string.input_phone_num), Toast.LENGTH_LONG);
                else if (Constant.number.ELEVEN != edtAccount.getText().length())
                    MyToast.showToast(mContext, getString(R.string.input_num_error), Toast.LENGTH_LONG);
                else if (TextUtils.isEmpty(edtPassword.getText()))
                    MyToast.showToast(mContext, getString(R.string.input_password_empty), Toast.LENGTH_LONG);
                else if (Constant.number.SIX > edtPassword.getText().length())
                    MyToast.showToast(mContext, getString(R.string.input_password_error), Toast.LENGTH_LONG);
                else getAuthorCode();
                break;
            //用户协议
            case R.id.tv_user_register_deal:

                break;
            case R.id.btn_login:
                checkInputInfo();
                break;
        }

    }

    private void checkInputInfo() {
        if (TextUtils.isEmpty(edtAccount.getText())|| Constant.number.ELEVEN != edtAccount.getText().length() ) {
            MyToast.showToast(mContext, getString(R.string.input_num_error), Toast.LENGTH_LONG);
            return;
        }else if(TextUtils.isEmpty(edtPassword.getText())){
            MyToast.showToast(mContext, getString(R.string.password_not_null), Toast.LENGTH_LONG);
            return;
        } else if(!mIsGetVerificationCode) {
            MyToast.showToast(mContext, getString(R.string.register_code_please), Toast.LENGTH_LONG);
            return;
        }else if(TextUtils.isEmpty(edtAuthCode.getText()) || Constant.number.SIX != edtAuthCode.getText().length()){
            MyToast.showToast(mContext, getString(R.string.verification_code_not_complete), Toast.LENGTH_LONG);
            return;
        }else{
            //注册操作
            RegisterPresenter registerPresenter = new RegisterPresenter(this);
            registerPresenter.queryList(Constant.number.ONE, String.valueOf(edtAuthCode.getText() + Constant.string.COMMA_SEPARATOR + edtAccount.getText() + Constant.string.COMMA_SEPARATOR + edtPassword.getText()), Constant.number.ZERO);
        }
    }

    private void getAuthorCode() {
        mTimer = new Timer();
        VerificationCodePresenter verificationCodePresenter = new VerificationCodePresenter(this);
        verificationCodePresenter.queryList(Constant.number.ZERO, edtAccount.getText().toString(), Constant.number.ZERO);
    }

    @Override
    public void getResult(Object dataBen, int type) {
        if (null == dataBen) {
            return;
        }

        SuccessBean successBean = (SuccessBean) dataBen;
        switch (type) {
            //获取验证码,后台给予返回码不完整，望后来者做进一步的处理
            case Constant.number.ZERO:
                switch (successBean.getStatus()) {
                    case "100":
                        MyToast.s(mContext, getString(R.string.register_code_send_error));
                        break;
                    case "200":
                        tvGetAuthCode.setClickable(false);
                        mIsGetVerificationCode = true;
                        MyToast.s(mContext, getString(R.string.register_code_hints));
                        //开启倒计时
                        startCountDown();
                        break;
                    case "212":
                        MyToast.s(mContext,getString(R.string.phone_had_register));
                        break;
                    case "300":
                        MyToast.s(mContext, getString(R.string.phone_had_register));
                        break;
                    case "400":
                        MyToast.s(mContext, getString(R.string.register_argument_error));
                        break;
                    case "446":
                        MyToast.s(mContext, getString(R.string.register_code_quick));
                        break;
                    default:
                        MyToast.s(mContext,successBean.getInfo());
                        break;
                }
                break;
            // 注册,由于后台给予的返回码只有200，不完整，望后来者做进一步的处理
            case Constant.number.ONE:
                switch (successBean.getStatus()) {
                    case "200":
                        //注册成功不能自动登录未必知道该用户是否需要绑定以前的账户
                        MyToast.s(mContext, getString(R.string.register_success));
                        //跳转到首页的我的界面
                        HomeActivity.toHome(mContext,Constant.number.THREE);
                        break;
                    default:
                        MyToast.s(mContext, successBean.getInfo());
                        break;
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
    private void startCountDown() {
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        --mReTime;
                        tvGetAuthCode.setText(mReTime + "S");
                        if (mReTime < 0) {
                            mTimer.cancel();
                            tvGetAuthCode.setClickable(true);
                            tvGetAuthCode.setText(getString(R.string.get_auth_code));
                            mReTime = 60;
                        }
                    }
                });
            }
        }, 1000, 1000);
    }

}
