package com.gjn.gamequery.net;

import com.gjn.gamequery.utils.Constants;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author gjn
 * @time 2018/7/31 15:01
 */

public class RetrofitManager {
    private static RetrofitManager retrofitManager;
    private static Retrofit retrofit;
    private static OkHttpClient okHttpClient;
    private static HeaderInterceptor headerInterceptor;

    private RetrofitManager() {
        if (okHttpClient == null) {
            okHttpClient = createClient();
        }
    }

    public static OkHttpClient createClient() {
        if (headerInterceptor == null) {
            headerInterceptor = createInterceptor();
        }
        return new OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .build();
    }

    public static HeaderInterceptor createInterceptor() {
        return new HeaderInterceptor(new HeaderInterceptor.OnChangeHeader() {
            @Override
            public Map<String, String> addRequestHeader() {
                Map<String, String> map = new HashMap<>();
                map.put("x-token", Constants.X_TOKEN);
                map.put("x-client", Constants.CLIENT);
                map.put("x-device", Constants.DEVICE);
                map.put("x-version", Constants.VERSION);
                return map;
            }

            @Override
            public void getResponseHeader(String url, Headers headers) {
                Constants.X_TOKEN = headers.get("x-token");
            }
        });
    }

    public static RetrofitManager getInstance() {
        if (retrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (retrofitManager == null) {
                    retrofitManager = new RetrofitManager();
                }
            }
        }
        return retrofitManager;
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public static void setRetrofit(Retrofit r) {
        retrofit = r;
    }

    public static OkHttpClient getOkHttpClient() {
        getInstance();
        return okHttpClient;
    }

    public static void setOkHttpClient(OkHttpClient client) {
        okHttpClient = client;
    }

    public static void setHeaderInterceptor(HeaderInterceptor interceptor) {
        headerInterceptor = interceptor;
    }

    public static <T> T create(String url, Class<T> service) {
        return getInstance().url(url).create(service);
    }

    public static void link(Observable<ResponseBody> observable, final OnLinkListener onLinkListener) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        onLinkListener.success(responseBody);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        onLinkListener.fail(throwable);
                    }
                });
    }

    public RetrofitManager url(String url) {
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return this;
    }

    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }

    public interface OnLinkListener {
        void success(ResponseBody responseBody) throws Exception;

        void fail(Throwable throwable);
    }
}
