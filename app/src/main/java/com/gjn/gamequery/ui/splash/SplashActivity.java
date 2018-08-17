package com.gjn.gamequery.ui.splash;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.gjn.gamequery.R;
import com.gjn.gamequery.base.BaseGQActivity;
import com.gjn.gamequery.ui.MainActivity;
import com.gjn.gamequery.utils.GlideUtils;
import com.gjn.permissionlibrary.PermissionCallBack;
import com.gjn.permissionlibrary.PermissionUtils;

import butterknife.BindView;

/**
 * @author gjn
 * @time 2018/7/25 17:35
 */

public class SplashActivity extends BaseGQActivity {

    private static final String IMG_URL = "https://ww1.sinaimg.cn/large/0065oQSqly1ftf1snjrjuj30se10r1kx.jpg";

    @BindView(R.id.iv_splash)
    ImageView imageView;

    private boolean isCheck;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        GlideUtils.loadImg(IMG_URL, imageView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isCheck) {
            isCheck = true;
            if (PermissionUtils.requestPermissions(this, PermissionUtils.CODE_STORAGE, PermissionUtils.CODE_PHONE)) {
                toMain();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionUtils.requestPermissionsResult(this, requestCode, permissions, grantResults, new PermissionCallBack() {
            @Override
            public void onSuccess(int i) {
                toMain();
            }

            @Override
            public void onFail(int i) {

            }

            @Override
            public void onRetry(int i) {

            }

            @Override
            public void onSetting(int i) {
                isCheck = false;
            }
        });
    }

    private void toMain() {
        imageView.postDelayed(new Runnable() {
            @Override
            public void run() {
                toNextActivity(MainActivity.class);
            }
        }, 1000);
    }
}
