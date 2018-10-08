package com.gjn.gamequery.activity.login;

import com.gjn.gamequery.net.RetrofitManager;
import com.gjn.gamequery.net.WanAndroidManager;
import com.gjn.gamequery.net.response.WanLoginResponse;
import com.gjn.gamequery.utils.ExceptionEngine;
import com.gjn.mvpannotationlibrary.base.BaseModel;

/**
 * @author gjn
 * @time 2018/10/8 14:01
 */

public class LoginModel extends BaseModel<LoginContract.View> implements LoginContract.Presenter {

    @Override
    public void login(String name, String pwd) {
        getMvpView().showProgressUI(true);
        WanAndroidManager.login(name, pwd, new RetrofitManager.OnLinkListener<WanLoginResponse>() {
            @Override
            public void success(WanLoginResponse response) throws Exception {
                getMvpView().showProgressUI(false);
                if (response != null && response.getErrorCode() == 0) {
                    getMvpView().loginSuccess();
                } else {
                    getMvpView().loginFailer(response.getErrorMsg());
                }
            }

            @Override
            public void fail(Throwable throwable) {
                getMvpView().showProgressUI(false);
                getMvpView().loginFailer("登录失败，"+ExceptionEngine.handleException(throwable));
            }
        });
    }
}
