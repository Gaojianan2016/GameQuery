package com.gjn.gamequery.net.response;

import com.gjn.gamequery.net.data.WanLoginData;

/**
 * @author gjn
 * @time 2018/10/8 11:38
 */

public class WanLoginResponse extends WanBaseResponse {
    private WanLoginData data;

    public WanLoginData getData() {
        return data;
    }

    public void setData(WanLoginData data) {
        this.data = data;
    }
}
