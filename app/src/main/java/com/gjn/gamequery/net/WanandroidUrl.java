package com.gjn.gamequery.net;

import com.gjn.gamequery.net.response.WanBannerResponse;
import com.gjn.gamequery.net.response.WanHomeResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
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
}
