package com.gjn.gamequery.ui.test;

import com.gjn.mvpannotationlibrary.base.BaseModel;

/**
 * @author gjn
 * @time 2018/8/3 16:11
 */

public class TestModel extends BaseModel<ITestView> {

    public void test(){
        getMvpView().test();
    }
}
