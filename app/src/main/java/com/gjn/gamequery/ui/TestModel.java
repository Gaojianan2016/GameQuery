package com.gjn.gamequery.ui;

import com.gjn.gamequery.mvp.BaseModel;

/**
 * @author gjn
 * @time 2018/8/2 16:03
 */

public class TestModel extends BaseModel<ITestView> {

    void success(){
        if (getMvpView() != null) {
            getMvpView().success();
        }
    }
}
