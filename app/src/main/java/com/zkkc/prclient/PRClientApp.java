package com.zkkc.prclient;

import android.app.Application;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.luoxudong.app.threadpool.ThreadPoolHelp;
import com.vise.utils.assist.SSLUtil;
import com.vise.xsnow.common.ViseConfig;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.core.ApiCookie;
import com.vise.xsnow.http.interceptor.HttpLogInterceptor;
import com.vise.xsnow.http.interceptor.NoCacheInterceptor;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;

import okhttp3.Cache;

import static com.zkkc.prclient.PRCConstant.BASE_URL;
import static com.zkkc.prclient.login.act.LoginAct.ACCESSTOKEN;


/**
 * Created by ShiJunRan on 2019/4/26
 * 客户端Application
 */
public class PRClientApp extends Application {
    private static PRClientApp mInstance;
    public static ExecutorService threadPool;

    // 单例模式中获取唯一的ExitApplication 实例
    public static PRClientApp getInstance() {
        if (null == mInstance) {
            mInstance = new PRClientApp();
        }
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    /**
     * 第三方初始化操作
     */
    private void init() {
        threadPool = ThreadPoolHelp.Builder
                .cached()
                .builder();
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
        //ViseHttp初始化
        HashMap<String, String> globalHeaders = new HashMap<String, String>();
        globalHeaders.put("Content-Type", "application/json");
        globalHeaders.put("accessToken", SPUtils.getInstance().getString(ACCESSTOKEN));
        LogUtils.i("accessToken",SPUtils.getInstance().getString(ACCESSTOKEN));
//        HashMap<String, String> globalParams = new HashMap<String, String>();
//        globalParams.put("accessToken", SPUtils.getInstance().getString(ACCESSTOKEN));//app类型
        ViseHttp.init(this);
        ViseHttp.CONFIG()
                //配置请求主机地址
                .baseUrl(BASE_URL)
                //配置全局请求头
                .globalHeaders(globalHeaders)
                //配置全局请求参数
//                .globalParams(globalParams)
                //配置读取超时时间，单位秒
                .readTimeout(30)
                //配置写入超时时间，单位秒
                .writeTimeout(30)
                //配置连接超时时间，单位秒
                .connectTimeout(5)
                //配置请求失败重试次数
                .retryCount(0)
                //配置请求失败重试间隔时间，单位毫秒
                .retryDelayMillis(1000)
                //配置是否使用cookie
                .setCookie(false)
//                //配置自定义cookie
//                .apiCookie(new ApiCookie(this))
                //配置是否使用OkHttp的默认缓存
                .setHttpCache(true)
                //配置OkHttp缓存路径
                .setHttpCacheDirectory(new File(ViseHttp.getContext().getCacheDir(), ViseConfig.CACHE_HTTP_DIR))
                //配置自定义OkHttp缓存
                .httpCache(new Cache(new File(ViseHttp.getContext().getCacheDir(), ViseConfig.CACHE_HTTP_DIR), ViseConfig.CACHE_MAX_SIZE))
                //配置自定义离线缓存
                .cacheOffline(new Cache(new File(ViseHttp.getContext().getCacheDir(), ViseConfig.CACHE_HTTP_DIR), ViseConfig.CACHE_MAX_SIZE))
                //配置自定义在线缓存
                .cacheOnline(new Cache(new File(ViseHttp.getContext().getCacheDir(), ViseConfig.CACHE_HTTP_DIR), ViseConfig.CACHE_MAX_SIZE))
//                //配置开启Gzip请求方式，需要服务器支持
//                .postGzipInterceptor()
                //配置应用级拦截器
//                .interceptor(new HttpLogInterceptor().setLevel(HttpLogInterceptor.Level.BODY))
                .interceptor(new HttpLogInterceptor().setLevel(HttpLogInterceptor.Level.BODY))
                //配置网络拦截器
                .networkInterceptor(new NoCacheInterceptor())
//                //配置主机证书验证
//                .hostnameVerifier(new SSLUtil.UnSafeHostnameVerifier("http://192.168.1.100/"))
//                //配置SSL证书验证
//                .SSLSocketFactory(SSLUtil.getSslSocketFactory(null, null, null));
        ;
    }


}
