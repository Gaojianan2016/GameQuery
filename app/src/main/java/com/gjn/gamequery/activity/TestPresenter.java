package com.gjn.gamequery.activity;

import com.gjn.gamequery.activity.test.ITestView;
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
