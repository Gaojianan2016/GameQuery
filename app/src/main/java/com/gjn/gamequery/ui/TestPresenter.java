package com.gjn.gamequery.ui;

import com.gjn.gamequery.ui.test.ITestView;
import com.gjn.mvpannotationlibrary.base.BasePresenter;

/**
 * @author gjn
 * @time 2018/8/3 16:11
 */

public class TestPresenter extends BasePresenter<ITestView, TestModel> {

    public void test(){
        if (isAttached()) {
            getModel().test();
        }
    }
}
