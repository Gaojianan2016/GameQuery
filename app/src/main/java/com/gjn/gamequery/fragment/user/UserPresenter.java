package com.gjn.gamequery.fragment.user;

import com.gjn.mvpannotationlibrary.base.BasePresenter;

/**
 * @author gjn
 * @time 2018/8/3 16:11
 */

public class UserPresenter extends BasePresenter<IUserView, UserModel> {

    public void getUser(String name){
        if (isAttached()) {
            getModel().getRole(name);
        }
    }

    public void getList(String name){
        if (isAttached()) {
            getModel().getList(name);
        }
    }

    public void getMatch(long id, boolean isCheck){
        if (isAttached()) {
            getModel().getMatch(id, isCheck);
        }
    }
}
