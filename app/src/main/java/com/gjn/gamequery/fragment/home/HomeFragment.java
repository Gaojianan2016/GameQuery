package com.gjn.gamequery.fragment.home;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gjn.gamequery.R;
import com.gjn.gamequery.annotation.AnnotationsUtils;
import com.gjn.gamequery.annotation.Test;
import com.gjn.gamequery.annotation.Test2;
import com.gjn.gamequery.base.BaseFragment;
import com.gjn.gamequery.net.OkHttpManager;
import com.gjn.gamequery.net.RetrofitManager;
import com.gjn.gamequery.ui.TestActivity;
import com.gjn.gamequery.utils.Constants;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * HomeFragment
 * Created by gjn
 * on 2018-07-28 19:56.
 */

@Test(R.layout.fragment_home)
public class HomeFragment extends BaseFragment{
    @BindView(R.id.tv_fh)
    TextView tvFh;
    @BindView(R.id.et_fh)
    EditText etFh;

    @Test2(R.id.btn_8_fh)
    Button button;

    private IHomeService service;
    private RetrofitManager.OnLinkListener link;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

        Test test = AnnotationsUtils.getAnnotations(mFragment, Test.class);
        if (test != null) {
            int id = test.value();
            String str = "当前Test注解id为 = " + id;
            Log.e("-s-", str);
        }

        try {
            AnnotationsUtils.setFieldAnnotations(this, Test2.class, new AnnotationsUtils.setAnnotations() {
                @Override
                public void set(Object obj, List<Field> fields, Annotation annotation) throws IllegalAccessException {
                    Field field = fields.get(0);
                    field.setAccessible(true);
                    field.set(mFragment, ((BaseFragment) mFragment).findViewById(((Test2) annotation).value()));
                }
            });
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void initData() {

        button.setText("进入测试按钮");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextActivity(TestActivity.class);
            }
        });

        service = RetrofitManager.create("http://v1.gumiss.com/", IHomeService.class);

        link = new RetrofitManager.OnLinkListener() {
            @Override
            public void success(ResponseBody responseBody) throws Exception {
                String str = responseBody.string();
                Log.e("-s-", str);
                tvFh.setText(str);
            }

            @Override
            public void fail(Throwable throwable) {
                Log.e("-s-", "fail");
                tvFh.setText(throwable.getMessage());
            }
        };
    }

    @OnClick({R.id.btn_1_fh, R.id.btn_2_fh, R.id.btn_3_fh, R.id.btn_4_fh, R.id.btn_5_fh,
            R.id.btn_6_fh, R.id.btn_7_fh})
    public void onViewClicked(View view) {
        String str;
        RequestBody body;
        switch (view.getId()) {
            case R.id.btn_1_fh:
                Constants.X_TOKEN = "6387a47fa40f0ea69ff93fd2853b4f02";
                break;
            case R.id.btn_2_fh:
                Constants.X_TOKEN = "";
                break;
            case R.id.btn_3_fh:
                RetrofitManager.link(service.check(), link);
                break;
            case R.id.btn_4_fh:
                RetrofitManager.link(service.list(), link);
                break;
            case R.id.btn_5_fh:
                str = "{" +
                        "  \"consignee\": \"string\"," +
                        "  \"id\": 0," +
                        "  \"isDefault\": false," +
                        "  \"phone\": \"13712345678\"" +
                        "}";
                body = RequestBody.create(OkHttpManager.MEDIA_TYPE_JSON, str);
                RetrofitManager.link(service.save(body), link);
                break;
            case R.id.btn_6_fh:
                RetrofitManager.link(service.delete(etFh.getText().toString().trim()), link);
                break;
            case R.id.btn_7_fh:
                str = "{" +
                        "  \"consignee\": \"string\"," +
                        "  \"id\": " + etFh.getText().toString().trim() + "," +
                        "  \"isDefault\": false," +
                        "  \"phone\": \"13787654321\"" +
                        "}";
                body = RequestBody.create(OkHttpManager.MEDIA_TYPE_JSON, str);
                RetrofitManager.link(service.update(body), link);
                break;
            default:
                break;
        }
    }
}
