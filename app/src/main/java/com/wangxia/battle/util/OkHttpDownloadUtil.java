package com.wangxia.battle.util;

import android.support.annotation.NonNull;

import com.wangxia.battle.globe.App;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 昝奥博 on 2017/9/21 0021
 * Email:18772833900@163.com
 * Explain：app下载
 */
public class OkHttpDownloadUtil {
    private OkHttpDownloadUtil downloadUtil;
    private final OkHttpClient okHttpClient;
    private static float lastProgress;
    private static boolean mIsSaveSize;

    public OkHttpDownloadUtil get() {
        if (null == downloadUtil) {
            downloadUtil = new OkHttpDownloadUtil();
        }
        return downloadUtil;
    }

    public OkHttpDownloadUtil() {
        okHttpClient = new OkHttpClient();
    }


    public static void download(@NonNull final String url, @NonNull final String saveDir, @NonNull final String saveApkName, @NonNull final OnDownloadListener listener) {
        new Runnable() {
            @Override
            public void run() {
                downApk(url, saveDir, saveApkName, listener);
            }
        }.run();
    }

    /**
     * @param url         下载连接
     * @param saveDir     储存下载文件的SDCard目录
     * @param saveApkName 下载的apk名称
     * @param listener    下载监听
     */
    public void down(@NonNull final String url, @NonNull final String saveDir, @NonNull final String saveApkName, @NonNull final OnDownloadListener listener) {
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败
                listener.onDownloadFailed(e);
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                FileOutputStream fos = null;
                // 储存下载文件的目录
                String savePath = isExistDir(saveDir);
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    SpUtil.putLong(App.context, Constant.string.DOWNLOAD_APK_SIZE + saveApkName, total);
                    File file = new File(savePath, saveApkName);
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    byte[] buf = new byte[2048];
                    int len;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        LogUtil.i("下载     " + progress);
                        // 下载中
                        listener.onDownloading(progress);
                    }
                    fos.flush(); // 下载完成
                    listener.onDownloadSuccess();
                } catch (Exception e) {
                    listener.onDownloadFailed(e);
                } finally {
                    try {
                        if (is != null) is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (fos != null) fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * @param url         下载连接
     * @param saveDir     储存下载文件的SDCard目录
     * @param saveApkName 下载的apk名称
     * @param listener    下载监听
     */
    public static void downApk(@NonNull  String url, @NonNull  String saveDir, @NonNull final String saveApkName, @NonNull final OnDownloadListener listener)  {
        try {
            saveDir = isExistDir(saveDir);
        } catch (IOException e) {
            e.printStackTrace();
            listener.onDownloadFailed(e);
        }
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new FileCallBack(saveDir, saveApkName){
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        call.cancel();
                        e.printStackTrace();
                        listener.onDownloadFailed(e);
                    }



                    @Override
                    public void onResponse(File response, int id) {
                        listener.onDownloadSuccess();
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        if(!mIsSaveSize){
                            SpUtil.putLong(App.context, Constant.string.DOWNLOAD_APK_SIZE + saveApkName, total);
                            mIsSaveSize = true;
                        }
                        //太频繁的接口回调导致UI界面卡顿
                        if(lastProgress+0.01 < progress){
                            lastProgress = progress;
                            listener.onDownloading((int)(progress*100));

                        }

                    }
                });
    }

    /**
     * @param saveDir
     * @return
     * @throws IOException 判断下载目录是否存在
     */
    private static String isExistDir(String saveDir) throws IOException {
        // 下载位置
        File downloadFile = new File(saveDir);
        if (!downloadFile.mkdirs()) {
            downloadFile.createNewFile();
        }

        return downloadFile.getAbsolutePath();
    }

    public static void downFile(String url,String parentFile,String fileName){
        OkHttpUtils.get().url(url).build().execute(new FileCallBack(parentFile,fileName) {
            @Override
            public void onError(Call call, Exception e, int id) {
                call.cancel();
                e.printStackTrace();
                new Exception("下载失败");
            }

            @Override
            public void onResponse(File response, int id) {
            }
        });
    }

    /**
     * @param url * @return * 从下载连接中解析出文件名
     */
    @NonNull
    private String getNameFromUrl(String url) {
        String appName = url.substring(url.lastIndexOf("/") + 1);
        SpUtil.putString(App.context, "updateAppName", appName);
        return appName;
    }

    public interface OnDownloadListener {
        /**
         * 下载成功
         */
        void onDownloadSuccess();

        /**
         * @param progress * 下载进度
         */
        void onDownloading(int progress);

        /**
         * 下载失败
         */
        void onDownloadFailed(Exception e);

    }
}
