package com.gjn.gamequery.net;

import com.gjn.gamequery.net.response.WanBannerResponse;
import com.gjn.gamequery.net.response.WanHomeResponse;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * @author gjn
 * @time 2018/9/30 15:16
 */

public class WanAndroidManager implements WanandroidUrl {

    @Override
    public Observable<WanHomeResponse> home(int page) {
        RetrofitManager.getInstance()
                .create(WanandroidUrl.class)
                .home(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WanHomeResponse>() {
                    @Override
                    public void accept(WanHomeResponse wanHomeResponse) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
        return null;
    }

    @Override
    public Observable<WanBannerResponse> banner() {
        return null;
    }

    @Override
    public Observable<ResponseBody> login(String username, String password) {
        return null;
    }

    @Override
    public Observable<ResponseBody> register(String username, String password, String repassword) {
        return null;
    }
}
