package com.gjn.gamequery.fragment.home;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gjn.gamequery.R;
import com.gjn.gamequery.base.BaseFragment;
import com.gjn.gamequery.net.OkHttpManager;
import com.gjn.gamequery.net.RetrofitManager;
import com.gjn.gamequery.utils.Constants;

import io.reactivex.functions.Consumer;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * HomeFragment
 * Created by gjn
 * on 2018-07-28 19:56.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private TextView tv;
    private EditText et;
    private IHomeService service;
    private RetrofitManager.OnLinkListener link;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        btn1 = findViewById(R.id.btn_1_fh);
        btn2 = findViewById(R.id.btn_2_fh);
        btn3 = findViewById(R.id.btn_3_fh);
        btn4 = findViewById(R.id.btn_4_fh);
        btn5 = findViewById(R.id.btn_5_fh);
        btn6 = findViewById(R.id.btn_6_fh);
        btn7 = findViewById(R.id.btn_7_fh);
        btn8 = findViewById(R.id.btn_8_fh);
        btn9 = findViewById(R.id.btn_9_fh);
        tv = findViewById(R.id.tv_fh);
        et = findViewById(R.id.et_fh);

        et.setText("5553");
    }

    @Override
    protected void initData() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);

        service = RetrofitManager.create("http://v1.gumiss.com/", IHomeService.class);

        link = new RetrofitManager.OnLinkListener() {
            @Override
            public void success(ResponseBody responseBody) throws Exception {
                String str = responseBody.string();
                Log.e("-s-", str);
                tv.setText(str);
            }

            @Override
            public void fail(Throwable throwable) {
                Log.e("-s-", "fail");
                tv.setText(throwable.getMessage());
            }
        };
    }

    @Override
    public void onClick(View v) {
        String str;
        RequestBody body;
        switch (v.getId()) {
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
                RetrofitManager.link(service.delete(et.getText().toString().trim()), link);
                break;
            case R.id.btn_7_fh:
                str = "{" +
                        "  \"consignee\": \"string\"," +
                        "  \"id\": " + et.getText().toString().trim() + "," +
                        "  \"isDefault\": false," +
                        "  \"phone\": \"13787654321\"" +
                        "}";
                body = RequestBody.create(OkHttpManager.MEDIA_TYPE_JSON, str);
                RetrofitManager.link(service.update(body), link);
                break;
            case R.id.btn_8_fh:
                break;
            case R.id.btn_9_fh:
                break;
            default:
                break;
        }
    }
}
