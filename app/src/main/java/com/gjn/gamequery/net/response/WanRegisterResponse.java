package com.gjn.gamequery.net.response;

import com.gjn.gamequery.net.data.WanRegisterData;

/**
 * @author gjn
 * @time 2018/10/8 11:48
 */

public class WanRegisterResponse extends WanBaseResponse {
    private WanRegisterData data;

    public WanRegisterData getData() {
        return data;
    }

    public void setData(WanRegisterData data) {
        this.data = data;
    }
}
