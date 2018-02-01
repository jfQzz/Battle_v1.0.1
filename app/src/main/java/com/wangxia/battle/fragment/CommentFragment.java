package com.wangxia.battle.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wangxia.battle.R;
import com.wangxia.battle.activity.BaseActivity;
import com.wangxia.battle.callback.CommentCallback;
import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.model.bean.CommentBean;
import com.wangxia.battle.model.bean.SuccessBean;
import com.wangxia.battle.presenter.impPresenter.CommentPresenter;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.GsonUtil;
import com.wangxia.battle.util.MyToast;
import com.wangxia.battle.util.SpUtil;
import com.wangxia.battle.util.TxtFormatUtil;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by 昝奥博 on 2017/12/21 0021
 * Email:18772833900@163.com
 * Explain：
 */
public class CommentFragment extends DialogFragment implements View.OnClickListener,ISuccessCallbackData{
    private Dialog mDialog;
    private ProgressBar pgSendRate;
    private EditText edtInput;
    private ImageView ivSelectPic;
    private ImageView ivAtPerson;
    private ImageView ivSend;
    private InputMethodManager inputMethodManager;
    private CommentCallback commentCallback;
    private Context mContext;
    private CommentBean mCommentBean;
    private CommentPresenter mCommentPresenter;
    private Timer mTimer;


    public static CommentFragment newInstance(CommentCallback callback , CommentBean bean) {
        Bundle args = new Bundle();
        args.putSerializable(CommentCallback.CALLBACK,callback);
        args.putSerializable(CommentBean.COMMENT_BEAN,bean);
        CommentFragment fragment = new CommentFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mContext = new WeakReference<>(getActivity()).get();
        mDialog = new Dialog(getActivity(), R.style.BottomDialog);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.fragment_comment);
        mDialog.setCanceledOnTouchOutside(true);
        Window window = mDialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(layoutParams);
        edtInput = (EditText) mDialog.findViewById(R.id.edt_input);
        pgSendRate = (ProgressBar)mDialog.findViewById(R.id.pg_send_rate);
        ivSelectPic = (ImageView) mDialog.findViewById(R.id.iv_select_pic);
        ivAtPerson = (ImageView) mDialog.findViewById(R.id.iv_at_person);
        ivSend = (ImageView) mDialog.findViewById(R.id.iv_send);
        fillEditText();
        setSoftKeyboard();
        edtInput.addTextChangedListener(mTextWatcher);
        ivSelectPic.setOnClickListener(this);
        ivAtPerson.setOnClickListener(this);
        ivSend.setOnClickListener(this);
        return mDialog;
    }

    private void fillEditText() {
        Bundle arguments = getArguments();
        commentCallback = (CommentCallback) arguments.getSerializable(CommentCallback.CALLBACK);
        mCommentBean = (CommentBean) arguments.getSerializable(CommentBean.COMMENT_BEAN);
        String saveTxt = mCommentBean.getSaveTxt();
        String hints = mCommentBean.getHints();
        if(TextUtils.isEmpty(saveTxt) && TextUtils.isEmpty(hints)){
            throw new NullPointerException(" hints  must not be empty");
        }
        edtInput.setHint(hints);
        if(!TextUtils.isEmpty(saveTxt)) {
            edtInput.setText(saveTxt);
            edtInput.setSelection(saveTxt.length());
        }
    }

    private void setSoftKeyboard() {
        edtInput.setFocusable(true);
        edtInput.setFocusableInTouchMode(true);
        edtInput.requestFocus();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            }
        }, 110);
    }

    private TextWatcher mTextWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.toString().length() > Constant.number.EIGHT) {
                ivSend.setEnabled(true);
                ivSend.setClickable(true);
                ivSend.setColorFilter(ContextCompat.getColor(mContext, R.color.colorAccent));
            } else {
                ivSend.setEnabled(false);
                ivSend.setColorFilter(ContextCompat.getColor(mContext, R.color.colorTxtOther));
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_select_pic:
                Toast.makeText(mContext, "Pick photo Activity", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_at_person:
                Toast.makeText(mContext, "Pick people you want to at Activity", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_send:
                mCommentPresenter = new CommentPresenter(this);
                mCommentBean.setInputTxt(edtInput.getText().toString());
                mCommentBean.setUserIcon(SpUtil.getString(mContext, Constant.userInfo.USER_ICON, null));
                mCommentBean.setUserName(SpUtil.getString(mContext, Constant.userInfo.USER_NICK, "来自小程序-决战平安京管家"));
                mCommentBean.setPhone(Build.MODEL);
                mCommentBean.setBrand(Build.PRODUCT);
                mCommentPresenter.queryList(mCommentBean.getType(), GsonUtil.getGson().toJson(mCommentBean),Constant.number.ZERO);
                 mTimer = new Timer(true);
                mTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        ((BaseActivity)mContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pgSendRate.setProgress(pgSendRate.getProgress()+1);
                            }
                        });
                    }
                },20,19);
                mTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        dismiss();
                    }
                },2000);
                break;
            default:
                break;
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        commentCallback.saveCommentText(edtInput.getText().toString());
        super.onDismiss(dialog);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        commentCallback.saveCommentText(edtInput.getText().toString());
        super.onCancel(dialog);
    }

    /**
     * 评论成功
     * @param dataBen
     * @param type
     */
    @Override
    public void getResult(Object dataBen, int type) {
        if(null != dataBen){
            SuccessBean successBean = (SuccessBean) dataBen;
            MyToast.s(mContext, TxtFormatUtil.HtmlFormat(successBean.getMsg()).toString());
            edtInput.setText(null);
            if(SuccessBean.SUCCESS_TAG.equals(successBean)){
            }
        }
        commentCallback.successComment();

    }

    @Override
    public void failRequest() {
        MyToast.s(mContext, "操作失败，请稍后重试");
        commentCallback.failComment();
    }

    @Override
    public void errorRequest() {
        MyToast.s(mContext, "操作失败，请稍后重试");
        commentCallback.failComment();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        recoveryMemory(mCommentPresenter);
        recoveryMemory(mCommentBean);
        recoveryMemory(mDialog);
        recoveryMemory(inputMethodManager);
        if(null != mTimer){
            mTimer.cancel();
            mTimer = null;
        }
    }

    public void recoveryMemory(Object obj){
        if(null != obj){
            obj = null;
        }
    }
}
