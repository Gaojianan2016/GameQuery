package com.gjn.gamequery.fragment.home;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gjn.gamequery.R;
import com.gjn.gamequery.base.BaseFragment;
import com.gjn.gamequery.net.OkHttpManager;
import com.gjn.gamequery.net.RetrofitManager;
import com.gjn.gamequery.ui.TestActivity;
import com.gjn.gamequery.utils.Constants;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * HomeFragment
 * Created by gjn
 * on 2018-07-28 19:56.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.tv_fh)
    TextView tvFh;
    @BindView(R.id.et_fh)
    EditText etFh;

    private IHomeService service;
    private RetrofitManager.OnLinkListener link;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

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
            R.id.btn_6_fh, R.id.btn_7_fh, R.id.btn_8_fh})
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
            case R.id.btn_8_fh:
                showNextActivity(TestActivity.class);
                break;
            default:
                break;
        }
    }
}
