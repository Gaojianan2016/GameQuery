package com.gjn.gamequery.ui.login;

import android.support.design.widget.TextInputLayout;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.gjn.gamequery.R;
import com.gjn.gamequery.base.BaseGQActivity;
import com.gjn.gamequery.net.RetrofitManager;
import com.gjn.gamequery.net.WanandroidUrl;
import com.gjn.gamequery.utils.SimpleTextWatcher;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * @author gjn
 * @time 2018/9/20 17:33
 */

public class LoginActivity extends BaseGQActivity {

    @BindView(R.id.textInputLayout_pwd_login)
    TextInputLayout textInputLayoutPwdLogin;
    @BindView(R.id.textInputLayout_name_login)
    TextInputLayout textInputLayoutNameLogin;
    @BindView(R.id.iv_pwd_login)
    ImageView ivPwdLogin;
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

    @OnClick({R.id.btn_login, R.id.tv_re, R.id.iv_pwd_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String name = etName.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();

                RetrofitManager.getInstance()
                        .url(WanandroidUrl.BASE)
                        .create(WanandroidUrl.class)
                        .login(name, pwd)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResponseBody>() {
                            @Override
                            public void accept(ResponseBody responseBody) throws Exception {
                                String str = responseBody.string();
                                Log.e("-s-login", "str = " + str);
                                showToast("登录成功");
//                                finish();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                                showToast("登录失败");
                            }
                        });
                break;
            case R.id.tv_re:
                showToast("注册");
                RetrofitManager.getInstance()
                        .url(WanandroidUrl.BASE)
                        .create(WanandroidUrl.class)
                        .register("test20180920","123456","123456")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResponseBody>() {
                            @Override
                            public void accept(ResponseBody responseBody) throws Exception {
                                String str = responseBody.string();
                                Log.e("-s-register", "str = " + str);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.e("-s-", "注册错误" + throwable.getMessage());
                            }
                        });
                break;
            case R.id.iv_pwd_login:
                if (ivPwdLogin.isSelected()) {
                    ivPwdLogin.setSelected(false);
                    //隐藏密码
                    etPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else {
                    ivPwdLogin.setSelected(true);
                    //显示密码
                    etPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                //移动到末尾
                etPwd.setSelection(etPwd.getText().length());
                break;
        }
    }
}
