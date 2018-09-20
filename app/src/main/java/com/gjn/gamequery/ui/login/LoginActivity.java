package com.gjn.gamequery.ui.login;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gjn.gamequery.R;
import com.gjn.gamequery.base.BaseGQActivity;
import com.gjn.gamequery.utils.SimpleTextWatcher;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author gjn
 * @time 2018/9/20 17:33
 */

public class LoginActivity extends BaseGQActivity {

    @BindView(R.id.textInputLayout_pwd_login)
    TextInputLayout textInputLayoutPwdLogin;
    @BindView(R.id.textInputLayout_name_login)
    TextInputLayout textInputLayoutNameLogin;
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
        etName.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    textInputLayoutNameLogin.setErrorEnabled(false);
                }
            }
        });

        etPwd.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etName.getText().toString().trim().isEmpty()) {
                    textInputLayoutNameLogin.setErrorEnabled(true);
                    textInputLayoutNameLogin.setError("未输入账号");
                }else {
                    textInputLayoutNameLogin.setErrorEnabled(false);
                }
            }
        });
    }

    @OnClick({R.id.btn_login, R.id.tv_re})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                showToast("登录成功");
                finish();
                break;
            case R.id.tv_re:
                showToast("注册");
                break;
        }
    }
}
