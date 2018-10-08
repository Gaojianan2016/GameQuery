package com.gjn.gamequery.net;

import com.gjn.gamequery.utils.LogUtils;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
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
    private static Interceptor interceptor;

    private RetrofitManager(Interceptor it) {
        interceptor = it;
        if (okHttpClient == null) {
            okHttpClient = createClient();
        }
    }

    public static OkHttpClient createClient() {
        if (interceptor == null) {
            interceptor = new HeaderInterceptor();
        }
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    public static RetrofitManager getInstance() {
        if (retrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (retrofitManager == null) {
                    retrofitManager = new RetrofitManager(null);
                }
            }
        }
        return retrofitManager;
    }

    public static RetrofitManager getInstance(Interceptor interceptor) {
        if (retrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (retrofitManager == null) {
                    retrofitManager = new RetrofitManager(interceptor);
                }
            }
        }
        return retrofitManager;
    }

    public static void setRetrofit(Retrofit r) {
        retrofit = r;
    }

    public static void setOkHttpClient(OkHttpClient client) {
        okHttpClient = client;
    }

    public static <T> T create(String url, Class<T> service) {
        return getInstance().url(url).create(service);
    }

    public static <T> void linkOnMainThread(Observable<T> observable, final OnLinkListener<T> onLinkListener) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<T>() {
                    @Override
                    public void accept(T t) throws Exception {
                        onLinkListener.success(t);
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

    public interface OnLinkListener<T> {
        void success(T t) throws Exception;

        void fail(Throwable throwable);
    }
}
