package com.wangxia.battle.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wangxia.battle.R;
import com.wangxia.battle.activity.HomeActivity;
import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.fragment.base.LazyBaseFragment;
import com.wangxia.battle.model.bean.SuccessBean;
import com.wangxia.battle.presenter.impPresenter.LoginPresenter;
import com.wangxia.battle.presenter.impPresenter.RegisterPresenter;
import com.wangxia.battle.presenter.impPresenter.VerificationCodePresenter;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.MyToast;
import com.wangxia.battle.util.SpUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 昝奥博 on 2017/11/11 0011
 * Email:18772833900@163.com
 * Explain：
 */
public class FindPasswordFragment extends LazyBaseFragment implements View.OnClickListener, ISuccessCallbackData {
    @BindView(R.id.tv_previous_step)
    TextView tvPreStep;
    @BindView(R.id.tv_next_step)
    TextView tvNextStep;
    @BindView(R.id.tv_find_get_code)
    TextView tvGetAuthorCode;
    private Unbinder mBind;
    private int mStepIndex;
    @BindView(R.id.edt_find_phone)
    EditText edtPhone;
    @BindView(R.id.edt_confirm_password)
    EditText edtConfirm;
    @BindView(R.id.edt_verification_code)
    EditText edtVerificationCode;
    @BindView(R.id.edt_new_password)
    EditText edtNewPassword;
    @BindView(R.id.ll_step_one)
    LinearLayout llStepOne;
    @BindView(R.id.ll_step_two)
    LinearLayout llStepTwo;
    @BindView(R.id.ll_step_three)
    LinearLayout llStepThree;
    @BindView(R.id.iv_show_password)
    ImageView ivShowPass;
    @BindView(R.id.iv_show_confirm_password)
    ImageView ivShowConfirmPass;
    private boolean mIsShowPass;
    private boolean mIsShowConfirmPass;
    private String mPhoneNum;
    private String mCode;
    private Timer mTimer;
    private int mReTime = 60;
    private boolean mIsGetVerificationCode = true;
    private List<LinearLayout> mRootViewList = new ArrayList<>(3);
    private AnimationSet mAnimationSet;

    public static FindPasswordFragment newInstance() {
        Bundle args = new Bundle();
        FindPasswordFragment fragment = new FindPasswordFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_find_password, null);
        mBind = ButterKnife.bind(this, view);
        llStepOne.setVisibility(View.VISIBLE);
        llStepTwo.setVisibility(View.GONE);
        llStepThree.setVisibility(View.GONE);
        return view;
    }

    @Override
    public void initData() {
        mAnimationSet = new AnimationSet(true);
        mAnimationSet.addAnimation(AnimationUtils.loadAnimation(mContext,R.anim.enter_left_to_right));
        mAnimationSet.addAnimation(AnimationUtils.loadAnimation(mContext,R.anim.alpha_from_zero_to_one));
        mAnimationSet.setDuration(2000);
        mAnimationSet.setInterpolator(new DecelerateInterpolator());
        mRootViewList.add(llStepOne);
        mRootViewList.add(llStepTwo);
        mRootViewList.add(llStepThree);
        changeVs(Constant.number.THREE);
    }


    @Override
    public void initListener() {
        tvPreStep.setOnClickListener(this);
        tvNextStep.setOnClickListener(this);
        tvGetAuthorCode.setOnClickListener(this);
        ivShowPass.setOnClickListener(this);
        ivShowConfirmPass.setOnClickListener(this);
        edtNewPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) ivShowPass.setVisibility(View.GONE);
                else ivShowPass.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) ivShowConfirmPass.setVisibility(View.GONE);
                else ivShowConfirmPass.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void recycleMemory() {
        mBind.unbind();
        if(null != mTimer){
            mTimer.cancel();
            mTimer = null;
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_previous_step:
                changeVs(Constant.number.ZERO);
                break;
            case R.id.tv_next_step:
                checkInput();
                break;
            case R.id.tv_find_get_code:
                getVerificationCode();
                break;
            case R.id.iv_show_password:
                if (mIsShowPass) {
                    mIsShowPass = false;
                    ivShowPass.setImageResource(R.drawable.ic_eye_close);
                    edtNewPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                } else {
                    mIsShowPass = true;
                    ivShowPass.setImageResource(R.drawable.ic_eye_open);
                    edtNewPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                break;
            case R.id.iv_show_confirm_password:
                if (mIsShowConfirmPass) {
                    mIsShowConfirmPass = false;
                    ivShowConfirmPass.setImageResource(R.drawable.ic_eye_close);
                    edtConfirm.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                } else {
                    mIsShowConfirmPass = true;
                    ivShowConfirmPass.setImageResource(R.drawable.ic_eye_open);
                    edtConfirm.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                break;
        }
    }

    private void checkInput() {
        checkAndSaveData();
    }

    /**
     * 逻辑操作
     */
    private void checkAndSaveData() {
        switch (mStepIndex) {
            case Constant.number.ZERO:
                Editable text = edtPhone.getText();
                if (TextUtils.isEmpty(text) || text.length() != Constant.number.ELEVEN) {
                    MyToast.showToast(mContext, getString(R.string.input_error), Toast.LENGTH_LONG);
                    return;
                } else mPhoneNum = text.toString();
                //可以先核对电话号码是否有效
                changeVs(Constant.number.ONE);

                break;
            case Constant.number.ONE:
                Editable verificationCodeText = edtVerificationCode.getText();
                if (TextUtils.isEmpty(verificationCodeText) || verificationCodeText.length() != Constant.number.SIX) {
                    MyToast.showToast(mContext, getString(R.string.input_error), Toast.LENGTH_LONG);
                    return;
                } else {
                    //最后一步将全部数据传入做验证，其实最好先做验证码处理
                    if (mIsGetVerificationCode) {
                        mCode = verificationCodeText.toString();
                        changeVs(Constant.number.ONE);
//                    ConfirmCode();
                    } else {
                        MyToast.safeShow(mContext, getString(R.string.register_code_please));
                    }
                }
                break;
            case Constant.number.TWO:
                Editable newPasswordText = edtNewPassword.getText();
                Editable confirmPassword = edtConfirm.getText();
                if (!TextUtils.isEmpty(newPasswordText) && !TextUtils.isEmpty(confirmPassword) && TextUtils.equals(newPasswordText, confirmPassword))
                    loginSuccess();
                else
                    MyToast.showToast(mContext, getString(R.string.new_password_input_error), Toast.LENGTH_LONG);
                break;

        }

    }

    private void loginSuccess() {
        RegisterPresenter registerPresenter = new RegisterPresenter(this);
        registerPresenter.queryList(Constant.number.TWO, String.valueOf(edtVerificationCode.getText()
                + Constant.string.COMMA_SEPARATOR + edtPhone.getText() + Constant.string.COMMA_SEPARATOR +
                edtNewPassword.getText() + Constant.string.COMMA_SEPARATOR + edtVerificationCode.getText()), Constant.number.ONE);
    }


    /**
     * @param type 0：上一步 1：下一步  初始化布局
     */
    private void changeVs(int type) {
        if (Constant.number.ZERO == type) {
            if (Constant.number.ZERO < mStepIndex) --mStepIndex;
            else return;
        } else if (Constant.number.ONE == type) {
            if (Constant.number.TWO > mStepIndex) ++mStepIndex;
            else return;
        }
        switch (mStepIndex) {
            case Constant.number.ZERO:
                if (TextUtils.isEmpty(mPhoneNum)) {
                    edtPhone.setHint(getString(R.string.input_find_phone_num));
                } else {
                    edtPhone.setText(mPhoneNum);
                    edtPhone.setSelection(mPhoneNum.length());
                }
                tvNextStep.setText(getString(R.string.next));
                tvPreStep.setVisibility(View.GONE);
                llStepOne.setVisibility(View.VISIBLE);
                llStepOne.startAnimation(mAnimationSet);
                llStepTwo.setVisibility(View.GONE);
                break;
            case Constant.number.ONE:
                if (TextUtils.isEmpty(mCode)) {
                    edtVerificationCode.setText(null);
                    edtVerificationCode.setHint(getString(R.string.input_auth_code));
                } else {
                    edtVerificationCode.setText(mCode);
                    edtVerificationCode.setSelection(mCode.length());
                }
                tvNextStep.setText(getString(R.string.next));
                tvPreStep.setVisibility(View.VISIBLE);
                if (Constant.number.ONE == type) {
                    llStepOne.setVisibility(View.GONE);
                }
                else {
                    llStepThree.setVisibility(View.GONE);
                }
                llStepTwo.startAnimation(mAnimationSet);
                llStepTwo.setVisibility(View.VISIBLE);
                break;
            case Constant.number.TWO:
                tvNextStep.setText(getString(R.string.sure));
                tvPreStep.setVisibility(View.VISIBLE);
                llStepTwo.setVisibility(View.GONE);
                llStepThree.startAnimation(mAnimationSet);
                llStepThree.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void getResult(Object dataBen, int type) {
        if (null == dataBen) {
            return;
        }
        SuccessBean successBean = (SuccessBean) dataBen;
        switch (type) {
            case Constant.number.ONE:
                switch (successBean.getStatus()) {
                    case "100":
                        MyToast.s(mContext, getString(R.string.register_code_send_error));
                        break;
                    case "200":
                        mIsGetVerificationCode = true;
                        tvGetAuthorCode.setClickable(false);
                        MyToast.s(mContext, getString(R.string.register_code_hints));
                        //开启倒计时
                        startCountDown();
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
                        MyToast.s(mContext, successBean.getInfo());
                        break;
                }
                break;
            case Constant.number.TWO:
                switch (successBean.getStatus()) {
                    case "200":
                        MyToast.s(mContext, getString(R.string.find_password_success));
                        autoLogin();
                        break;
                    default:
                        break;
                }
                break;
            case Constant.number.THREE:
                switch (successBean.getStatus()) {
                    //200：登陆成功
                    case "200":
                        MyToast.safeShow(mContext, getString(R.string.auto_login_success));
                        SpUtil.putBoolean(mContext, Constant.userInfo.USER_STATE, true);
                        //跳转到首页的我的界面
                        HomeActivity.toHome(mContext, Constant.number.THREE);
                        break;
                    default:
                        MyToast.safeShow(mContext, successBean.getInfo());
                        getActivity().finish();
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
    private void autoLogin() {
        LoginPresenter loginPresenter = new LoginPresenter(this);
        loginPresenter.queryList(Constant.number.THREE, String.valueOf(edtPhone.getText() + Constant.string.COMMA_SEPARATOR + edtNewPassword.getText()), Constant.number.ZERO);
    }


    private void startCountDown() {
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        --mReTime;
                        tvGetAuthorCode.setText(mReTime + "S");
                        if (mReTime < 0) {
                            mTimer.cancel();
                            tvGetAuthorCode.setClickable(true);
                            tvGetAuthorCode.setText(getString(R.string.get_auth_code));
                            mReTime = 60;
                        }
                    }
                });
            }
        }, 1000, 1000);
    }

    public void getVerificationCode() {
        mTimer = new Timer();
        VerificationCodePresenter verificationCodePresenter = new VerificationCodePresenter(this);
        verificationCodePresenter.queryList(Constant.number.ONE, mPhoneNum, Constant.number.ZERO);
    }
}
