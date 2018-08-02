package com.gjn.gamequery.ui;

import com.gjn.gamequery.mvp.BasePresenter;
import com.gjn.gamequery.mvp.IMvpModel;

/**
 * @author gjn
 * @time 2018/8/1 17:46
 */

public class TestPresenter2 extends BasePresenter<ITest2View, Test2Model> {

    public void test(){
        if (isAttached()) {
            getMvpView().test();
        }
    }
}
