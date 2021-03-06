package com.gjn.gamequery.net;

import com.gjn.gamequery.net.response.WanBannerResponse;
import com.gjn.gamequery.net.response.WanHomeResponse;
import com.gjn.gamequery.net.response.WanLoginResponse;
import com.gjn.gamequery.net.response.WanRegisterResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author gjn
 * @time 2018/8/3 17:06
 */

public interface WanandroidUrl {
    String BASE = "http://www.wanandroid.com/";

    @GET("article/list/{page}/json")
    Observable<WanHomeResponse> home(@Path("page") int page);

    @GET("banner/json")
    Observable<WanBannerResponse> banner();

    @POST("user/login")
    @FormUrlEncoded
    Observable<WanLoginResponse> login(@Field("username") String username, @Field("password") String password);

    @POST("user/register")
    @FormUrlEncoded
    Observable<WanRegisterResponse> register(@Field("username") String username, @Field("password") String password,
                                             @Field("repassword") String repassword);
}
