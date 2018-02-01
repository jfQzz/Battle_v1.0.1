package com.wangxia.battle.util;

import android.os.Environment;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by 昝奥博 on 2017/9/13 0013
 * Email:18772833900@163.com
 * Explain：更换网络请求框架简单
 */
public class HttpUtil {
    private static HttpUrl cookies_url = HttpUrl.get(URI.create("jzpaj_cookies_tag"));
    private static OkHttpUtils mOkHttpUtils;
    private static HttpUtil mHttpUtil;
    private static final long cacheSize = 1024 * 1024 * 20;//缓存文件大小20M
    private static String cacheDirectory = Environment.getExternalStorageDirectory() + "/okHttp/caches"; // 设置缓存文件路径

    private HttpUtil() {
        mOkHttpUtils = OkHttpUtils.getInstance();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addNetworkInterceptor(new MyCacheInterceptor())
                .cache(new Cache(new File(cacheDirectory), cacheSize))
                //其他配置
                .build();
        mOkHttpUtils.initClient(okHttpClient);
    }

    public static HttpUtil getInstance() {
        if (null == mHttpUtil) {
            mHttpUtil = new HttpUtil();
        }
        return mHttpUtil;
    }

    /**
     *  全部都设置缓存，缓存的有效时间是12小时（cookies的有效时间是7天，再次请求即为刷新）
     */
    private class MyCacheInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originResponse = chain.proceed(chain.request());
            //设置缓存时间为，并移除了pragma消息头，移除它的原因是因为pragma也是控制缓存的一个消息头属性
            return originResponse.newBuilder().removeHeader("pragma")
                    .header("Cache-Control", "max-age=43200")//缓存12小时
                    .header("Cache-Control", "max-stale=10")//超过10秒才能请求数据
                    .build();
        }
    }

    /**
     * 获取httpUtils（默认不包含cookies）
     * 注意事项：每次请求需要自己添加cookies或者情况cookies
     *
     * @return
     */
    public static OkHttpUtils getHttpUtils() {
        return mOkHttpUtils;
    }

    public static void  setCookieJar(CookieJar cookieJar){
//        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(App.context));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }


    public void clearSession() {
        CookieJar cookieJar = mOkHttpUtils.getOkHttpClient().cookieJar();
        if (cookieJar instanceof CookieJarImpl) {
            ((CookieJarImpl) cookieJar).getCookieStore().removeAll();
        }
    }

    private static CookieJar getCookieJar() {
        return new CookieJar() {

            //Cookie缓存区
            private final Map<String, List<Cookie>> cookiesMap = new HashMap<>();
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                // TODO Auto-generated method stub
                //移除相同的url的Cookie
                String host = url.host();
                List<Cookie> cookiesList = cookiesMap.get(host);
                if (cookiesList != null){
                    cookiesMap.remove(host);
                }
                cookiesMap.put(host,cookies);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookiesList = cookiesMap.get(url.host());
                //注：这里不能返回null，否则会报NULLException的错误。
                //原因：当Request 连接到网络的时候，OkHttp会调用loadForRequest()
                return cookiesList != null ? cookiesList : new ArrayList<Cookie>();
            }
        };

    }

}
