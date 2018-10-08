package com.gjn.gamequery.activity.login;

import com.gjn.mvpannotationlibrary.base.IMvpView;

/**
 * @author gjn
 * @time 2018/10/8 13:44
 */

public interface LoginContract {
    interface View extends IMvpView{
        void loginSuccess();
        void loginFailer(String msg);
    }

    interface Presenter{
        void login(String name, String pwd);
    }
}
