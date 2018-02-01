package com.wangxia.battle.model.model;

import android.text.TextUtils;

import com.wangxia.battle.model.IModel;
import com.wangxia.battle.model.bean.SuccessBean;
import com.wangxia.battle.model.http.UrlConstant;
import com.wangxia.battle.presenter.callback.ICallback;
import com.wangxia.battle.util.GsonUtil;
import com.wangxia.battle.util.HttpUtil;
import com.wangxia.battle.util.LogUtil;
import com.wangxia.battle.util.UserUtil;
import com.zhy.http.okhttp.callback.Callback;

import java.io.File;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by 昝奥博 on 2017/11/17 0017
 * Email:18772833900@163.com
 * Explain：上传文件
 */
public class UploadFileModel implements IModel {

    @Override
    public void queryList(final int type, final String arg, int pageNo, final ICallback iCallback) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//            //上传图片到服务器
//            try {
//                boolean issucuss = new UpLoadpic().uploadForm(null, "picfile", new File(arg),"head.jpg",UrlConstant.DOMAIN_NAME + UrlConstant.APP_NAME + UrlConstant.USER_MODIFY_ICO+"&"+UserUtil.getCookies());
//            LogUtil.i("图片上传       "+issucuss);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            }
//        }).start();

        HttpUtil.getHttpUtils().post().addFile("picfile", "user_ico.jpg", new File(arg)).url(UrlConstant.DOMAIN_NAME + UrlConstant.APP_NAME + UrlConstant.USER_MODIFY_ICO+"&"+UserUtil.getCookies())
                //说明:建议使用请求头的方式使用cookies,但是测试后cookie发送了后台,后台处理不当获取不到
                //建议后来者从安全角度考虑,做进一步的优化
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int id) throws Exception {
                        String result = response.body().string();
                        LogUtil.i(response.toString()+"     1111      "+response.headers().toString()+"       上传图片            " + "     \n\n    result       " + result);
                        if (!TextUtils.isEmpty(result)) {
                            iCallback.success(GsonUtil.getGson().fromJson(result, SuccessBean.class), type);
                        }else iCallback.fail();
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        call.cancel();
                        e.printStackTrace();
                        iCallback.error();

                    }

                    @Override
                    public void onResponse(Object response, int id) {

                    }
                });
    }
}
