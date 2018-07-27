package com.gjn.gamequery.ui.splash;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gjn.gamequery.R;
import com.gjn.gamequery.base.BaseActivity;
import com.gjn.gamequery.ui.MainActivity;

/**
 * @author gjn
 * @time 2018/7/25 17:35
 */

public class SplashActivity extends BaseActivity{

    private String url = "https://ww1.sinaimg.cn/large/0065oQSqly1ftf1snjrjuj30se10r1kx.jpg";
    private ImageView imageView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        imageView = findViewById(R.id.iv_splash);
        Glide.with(mActivity).load(url).into(imageView);
    }

    @Override
    protected void initData() {
        imageView.postDelayed(new Runnable() {
            @Override
            public void run() {
                toNextActivity(MainActivity.class);
            }
        }, 3000);
    }
}
