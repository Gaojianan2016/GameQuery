package com.gjn.gamequery.ui;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gjn.gamequery.MyTinkerApplicationLike;
import com.gjn.gamequery.R;
import com.gjn.gamequery.base.BaseActivity;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.tinker.TinkerManager;

public class MainActivity extends BaseActivity {

    private Button btnUpdata;
    private Button btnDownload;
    private Button btnApply;
    private Button btnClear;
    private TextView tvLog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        btnUpdata = findViewById(R.id.btn_updata);
        btnDownload = findViewById(R.id.btn_download);
        btnApply = findViewById(R.id.btn_apply);
        btnClear = findViewById(R.id.btn_clear);
        tvLog = findViewById(R.id.tv_log);
    }

    @Override
    protected void initData() {
        btnUpdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Beta.checkUpgrade();
            }
        });

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Beta.downloadPatch();
            }
        });

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Beta.applyDownloadedPatch();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Beta.cleanTinkerPatch(true);
            }
        });

        MyTinkerApplicationLike.setTinkerListener(new MyTinkerApplicationLike.TinkerListener() {
            @Override
            public void addText(String str) {
                addStrText(str);
            }
        });
    }

    private void addStrText(String s) {
        String str = tvLog.getText().toString();
        tvLog.setText(str +"\n"+ s);
    }
}
