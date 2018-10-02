package com.demo.joe.radiorv.utils;

import com.demo.joe.radiorv.footprints.DynamicApiService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by weizijie on 2018/8/3.
 */

public class RetrofitHelper {


    public static final String Host = "";
    private volatile static Retrofit retrofitInstance = null;

    /**
     * 动态url获取数据
     * @return
     */
    public static DynamicApiService getDynamicDataService(){

        DynamicApiService dynamicApiService = RetrofitHelper.initService(DynamicApiService.class);

        return dynamicApiService;
    }
    /**
     * 获得想要的 retrofit service
     * @param clazz    想要的 retrofit service 接口，Retrofit会代理生成对应的实体类
     * @param <T>      api service
     * @return
     */
    public static <T> T initService(Class<T> clazz) {
        return getRetrofitInstance().create(clazz);
    }

    private static Retrofit getRetrofitInstance(){
        if (retrofitInstance == null) {
            synchronized (RetrofitHelper.class) {
                if (retrofitInstance == null) {
                    retrofitInstance = new Retrofit.Builder()
                            .baseUrl(Host)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(buildOKHttpClient())
                            .build();
                }
            }
        }
        return retrofitInstance;
    }
    /**
     * 构建OKHttpClient
     *
     * @return
     */
    private static OkHttpClient buildOKHttpClient() {
        //添加日志拦截器,非debug模式不打印任何日志
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)//添加日志拦截器
                .retryOnConnectionFailure(true)//设置自动重连
                .connectTimeout(20, TimeUnit.SECONDS)//20秒连接超时
                .readTimeout(60, TimeUnit.SECONDS)//60秒读取超时
                .writeTimeout(60, TimeUnit.SECONDS)//60秒写入超时
                .build();
    }

}
