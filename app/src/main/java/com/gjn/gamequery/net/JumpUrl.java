package com.gjn.gamequery.net;

import com.gjn.gamequery.net.data.JumpListData;
import com.gjn.gamequery.net.data.JumpMatchData;
import com.gjn.gamequery.net.data.JumpRoleData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * JumpUrl
 * Created by gjn
 * on 2018-08-05 23:05.
 */
public interface JumpUrl {
    String BASE = "http://300report.jumpw.com/";
    String IMGURL = "http://300report.jumpw.com/static/images/";

    @GET("api/getrole")
    Observable<JumpRoleData> getrole(@Query("name") String name);

    @GET("api/getlist")
    Observable<JumpListData> getlist(@Query("name") String name);

    @GET("api/getmatch")
    Observable<JumpMatchData> getmatch(@Query("id") int id);

}
