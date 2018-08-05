package com.gjn.gamequery.fragment.home;

import com.gjn.mvpannotationlibrary.base.BasePresenter;

/**
 * @author gjn
 * @time 2018/8/3 16:11
 */

public class HomePresenter extends BasePresenter<IHomeView, HomeModel> {

    public void list(){
        list(1);
    }

    public void list(int page){
        if (isAttached()) {
            getModel().getHomeList(page);
        }
    }

    public void banner(){
        if (isAttached()) {
            getModel().getBanner();
        }
    }
}
