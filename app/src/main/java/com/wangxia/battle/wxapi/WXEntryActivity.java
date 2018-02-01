package com.wangxia.battle.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.presenter.impPresenter.WxTokenPresenter;
import com.wangxia.battle.presenter.impPresenter.WxUserInfoPresenter;
import com.wangxia.battle.util.Constant;
import com.wangxia.battle.util.LogUtil;
import com.wangxia.battle.util.MyToast;
import com.wangxia.battle.util.UserUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 昝奥博 on 2017/11/14 0014
 * Email:18772833900@163.com
 * Explain： 微信登录
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler, ISuccessCallbackData {
    private static final int RETURN_MSG_TYPE_LOGIN = 1;
    private static final int RETURN_MSG_TYPE_SHARE = 2;
    private Bundle bundle;
    private WxTokenPresenter mWxTokenPresenter;
    private WxUserInfoPresenter mWxUserInfoPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WXAPIFactory.createWXAPI(this, Constant.platformID.LOGIN_WX_ID, false).handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        WXAPIFactory.createWXAPI(this, Constant.platformID.LOGIN_WX_ID, false).handleIntent(getIntent(), this);
    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req) {
        System.out.println();
    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    //app发送消息给微信，处理返回消息的回调
    @Override
    public void onResp(BaseResp resp) {
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                if (RETURN_MSG_TYPE_SHARE == resp.getType())
                    MyToast.showToast(this, "分享失败", Toast.LENGTH_LONG);
                else MyToast.showToast(this, "登录失败", Toast.LENGTH_LONG);
                break;
            case BaseResp.ErrCode.ERR_OK:
                switch (resp.getType()) {
                    case RETURN_MSG_TYPE_LOGIN:
                        //获取openID
                        String code = ((SendAuth.Resp) resp).code;
                        //就在这个地方，用网络库什么的或者自己封的网络api，发请求去咯，注意是get请求
                        mWxTokenPresenter = new WxTokenPresenter(this);
                        mWxTokenPresenter.queryList(Constant.number.ZERO, code, Constant.number.ZERO);
                        break;
                    case RETURN_MSG_TYPE_SHARE:
                        MyToast.showToast(this, "微信分享成功", Toast.LENGTH_LONG);
                        finish();
                        break;
                }
                break;
        }
    }


    @Override
    public void getResult(Object dataBen, int type) {
        if (null == dataBen) {
            return;
        }
        LogUtil.i(dataBen.toString());
        switch (type) {
            case Constant.number.ZERO:
                try {
                    JSONObject response = new JSONObject(dataBen.toString());
                    String access_token = response.optString("access_token");
                    String openid = response.optString("openid");
                    if (!TextUtils.isEmpty(access_token) && !TextUtils.isEmpty(openid)) {
                        mWxUserInfoPresenter = new WxUserInfoPresenter(this);
                        mWxUserInfoPresenter.queryList(Constant.number.ONE, String.valueOf(access_token + Constant.string.COMMA_SEPARATOR + openid), Constant.number.ZERO);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case Constant.number.ONE:
                try {
                    JSONObject userInfo = new JSONObject(dataBen.toString());
                    String openid = userInfo.optString("openid");
                    String unionid = userInfo.optString("unionid");//跨平台使用
                    String city = userInfo.optString("city");
                    String province = userInfo.optString("province");
                    String nickname = userInfo.optString("nickname");
                    String gender = userInfo.optInt("sex") == 1 ? "男" : "女";
                    String icon = userInfo.optString("headimgurl");
                    UserUtil.setGender(this,gender);
                    //拿到openID 获取用户信息之后跳转到登录界面会有闪屏，最好还是在这儿做操作，然后直接登录，跳转到我的界面
                    UserUtil.setWxInfo(String.valueOf(openid+Constant.string.COMMA_SEPARATOR+nickname+Constant.string.COMMA_SEPARATOR+icon));
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
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
}
