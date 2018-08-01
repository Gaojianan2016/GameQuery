package com.gjn.gamequery.ui;

import com.gjn.gamequery.mvp.BasePresenter;

/**
 * @author gjn
 * @time 2018/8/1 17:46
 */

public class TestPresenter2 extends BasePresenter<ITest2View> {
    public void test(){
        getMvpView().test();
    }
}
