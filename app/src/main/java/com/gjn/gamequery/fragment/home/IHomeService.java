package com.gjn.gamequery.fragment.home;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author gjn
 * @time 2018/7/31 15:44
 */

public interface IHomeService {

    @GET("member/receiver/list")
    Observable<ResponseBody> list();

    @POST("member/receiver/save")
    Observable<ResponseBody> save(@Body RequestBody json);

    @POST("member/receiver/update")
    Observable<ResponseBody> update(@Body RequestBody json);

    @POST("member/receiver/delete")
    Observable<ResponseBody> delete(@Query("receiverId") String id);

    @GET("front/version/check")
    Observable<ResponseBody> check();
}
