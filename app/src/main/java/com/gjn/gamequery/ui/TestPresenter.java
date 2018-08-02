package com.gjn.gamequery.ui;

import com.gjn.gamequery.mvp.BasePresenter;

/**
 * @author gjn
 * @time 2018/8/1 17:07
 */

public class TestPresenter extends BasePresenter<ITestView, TestModel> {

    public void success(){
        if (isAttached()) {
            getModel().success();
        }
    }
}
