package com.gjn.gamequery.net.response;

import com.gjn.gamequery.net.data.WanHomeData;

/**
 * @author gjn
 * @time 2018/8/3 17:24
 */

public class WanHomeResponse extends WanBaseResponse {
    private WanHomeData data;

    public WanHomeData getData() {
        return data;
    }

    public void setData(WanHomeData data) {
        this.data = data;
    }
}
