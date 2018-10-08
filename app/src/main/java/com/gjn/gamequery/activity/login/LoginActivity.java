package com.gjn.gamequery.activity.login;

import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.gjn.gamequery.R;
import com.gjn.gamequery.base.BaseGQActivity;
import com.gjn.gamequery.utils.TextViewUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author gjn
 * @time 2018/9/20 17:33
 */

public class LoginActivity extends BaseGQActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.textInputLayout_pwd_login)
    TextInputLayout textInputLayoutPwdLogin;
    @BindView(R.id.textInputLayout_name_login)
    TextInputLayout textInputLayoutNameLogin;
    @BindView(R.id.iv_pwd_login)
    ImageView ivPwdLogin;
    @BindView(R.id.btn_login)
    Button btnLogin;
    EditText etName, etPwd;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        etName = textInputLayoutNameLogin.getEditText();
        etPwd = textInputLayoutPwdLogin.getEditText();
    }

    @Override
    protected void initData() {
        TextViewUtils.setViewsMonitor(btnLogin, etName, etPwd);
    }

    @OnClick({R.id.btn_login, R.id.tv_re, R.id.iv_pwd_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                getPresenter().login(TextViewUtils.getTextString(etName), TextViewUtils.getTextString(etPwd));
                break;
            case R.id.tv_re:
//                WanAndroidManager.register("test20180920", "123456", "123456",
//                        new RetrofitManager.OnLinkListener<WanRegisterResponse>() {
//                            @Override
//                            public void success(WanRegisterResponse response) throws Exception {
//                                if (response != null && response.getErrorCode() == 0) {
//                                    showToast("注册失败");
//                                } else {
//                                    showToast(response.getErrorMsg());
//                                }
//                            }
//
//                            @Override
//                            public void fail(Throwable throwable) {
//                                showToast("注册错误, 请检查网络");
//                            }
//                        });
                break;
            case R.id.iv_pwd_login:
                if (ivPwdLogin.isSelected()) {
                    ivPwdLogin.setSelected(false);
                    //隐藏密码
                    etPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    ivPwdLogin.setSelected(true);
                    //显示密码
                    etPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                //移动到末尾
                etPwd.setSelection(etPwd.getText().length());
                break;
        }
    }

    @Override
    public void loginSuccess() {
        showToast("登录成功");
        finish();
    }

    @Override
    public void loginFailer(String msg) {
        showToast(msg);
    }

    @Override
    public void showProgressUI(boolean isShow) {
        if (isShow) {
            showLoadingDialog();
        }else {
            dismissDialog();
        }
    }
}
