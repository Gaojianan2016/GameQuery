package com.gjn.gamequery.activity.login;

import com.gjn.mvpannotationlibrary.base.BasePresenter;

/**
 * @author gjn
 * @time 2018/10/8 14:00
 */

public class LoginPresenter extends BasePresenter<LoginContract.View, LoginModel> implements LoginContract.Presenter {

    @Override
    public void login(String name, String pwd) {
        if (isAttached()) {
            getModel().login(name, pwd);
        }
    }
}
