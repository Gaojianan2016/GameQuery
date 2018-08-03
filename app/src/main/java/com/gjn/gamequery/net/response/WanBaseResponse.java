package com.gjn.gamequery.net.response;

/**
 * @author gjn
 * @time 2018/8/3 17:22
 */

public class WanBaseResponse {
    private int errorCode;
    private String errorMsg;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
