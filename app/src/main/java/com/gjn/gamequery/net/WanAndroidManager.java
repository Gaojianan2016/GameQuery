package com.gjn.gamequery.net;

import com.gjn.gamequery.net.response.WanBannerResponse;
import com.gjn.gamequery.net.response.WanHomeResponse;
import com.gjn.gamequery.net.response.WanLoginResponse;
import com.gjn.gamequery.net.response.WanRegisterResponse;

/**
 * @author gjn
 * @time 2018/9/30 15:16
 */

public class WanAndroidManager {

    public static void home(int page, RetrofitManager.OnLinkListener<WanHomeResponse> listener) {
        RetrofitManager.linkOnMainThread(RetrofitManager.create(WanandroidUrl.BASE, WanandroidUrl.class)
                .home(page), listener);
    }

    public static void banner(RetrofitManager.OnLinkListener<WanBannerResponse> listener) {
        RetrofitManager.linkOnMainThread(RetrofitManager.create(WanandroidUrl.BASE, WanandroidUrl.class)
                        .banner(), listener);
    }

    public static void login(String username, String password,
                             RetrofitManager.OnLinkListener<WanLoginResponse> listener) {
        RetrofitManager.linkOnMainThread(RetrofitManager.create(WanandroidUrl.BASE, WanandroidUrl.class)
                .login(username, password), listener);
    }

    public static void register(String username, String password, String repassword,
                                RetrofitManager.OnLinkListener<WanRegisterResponse> listener) {
        RetrofitManager.linkOnMainThread(RetrofitManager.create(WanandroidUrl.BASE, WanandroidUrl.class)
                        .register(username, password, repassword), listener);
    }
}
