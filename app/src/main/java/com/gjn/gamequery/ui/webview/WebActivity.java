package com.gjn.gamequery.ui.webview;

import android.os.Build;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gjn.gamequery.R;
import com.gjn.gamequery.base.BaseGQActivity;

import butterknife.BindView;

/**
 * @author gjn
 * @time 2018/8/3 16:49
 */

public class WebActivity extends BaseGQActivity {
    @BindView(R.id.wb_aw)
    WebView webView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initView() {
        WebSettings setting = webView.getSettings();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setting.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        setting.setJavaScriptEnabled(true);
        setting.setBlockNetworkImage(false);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    protected void initData() {
        String url = getUrl();
        if (url.isEmpty()) {
            showToast("url错误");
            finish();
            return;
        }
        Log.e("-s-", "load = " + url);
        webView.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
