package com.gjn.gamequery.net.response;

import com.gjn.gamequery.net.data.WanBannerData;

import java.util.List;

/**
 * WanBannerResponse
 * Created by gjn
 * on 2018-08-05 1:46.
 */
public class WanBannerResponse extends WanBaseResponse {
    private List<WanBannerData> data;

    public List<WanBannerData> getData() {
        return data;
    }

    public void setData(List<WanBannerData> data) {
        this.data = data;
    }
}
