package com.gjn.gamequery.ui;

import com.gjn.gamequery.mvp.BaseModel;

/**
 * @author gjn
 * @time 2018/8/2 16:03
 */

public class Test2Model extends BaseModel<ITest2View> {

    void test(){
        if (getMvpView() != null) {
            getMvpView().test();
        }
    }
}
