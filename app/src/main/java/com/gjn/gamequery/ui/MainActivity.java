package com.gjn.gamequery.ui;

import com.gjn.gamequery.MyTinkerApplicationLike;
import com.gjn.gamequery.R;
import com.gjn.gamequery.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        super.init();
        MyTinkerApplicationLike.setTinkerListener(new MyTinkerApplicationLike.TinkerListener() {
            @Override
            public void applyResult(boolean result) {
                if (result) {
                    showToast("合并成功");
                } else {
                    showToast("合并失败");
                }
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
