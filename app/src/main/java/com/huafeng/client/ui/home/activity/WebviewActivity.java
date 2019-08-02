package com.huafeng.client.ui.home.activity;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.view.x5.X5WebView;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebviewActivity extends BaseActivity {
    @BindView(R.id.webview)
    X5WebView mX5WebView;
    @BindView(R.id.tv_title)
    TextView tv_title;
    String url;
    String img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        img=getIntent().getStringExtra("img");
        url="http://zhiyierp.cn/factory/views/data/statistics.html?token="+ MyApp.token;
        if(!TextUtils.isEmpty(img)){
            url=img;
            tv_title.setText("详情");
        }
        initWebView();
    }
    private void initWebView() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mX5WebView.setWebViewClient(new WebViewClient() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);

            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                webView.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
//                super.onReceivedSslError(webView, sslErrorHandler, sslError);
                sslErrorHandler.proceed();//忽略SSL证书错误
            }
        });
        mX5WebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsAlert(WebView webView, String s, String s1, JsResult jsResult) {
                return super.onJsAlert(webView, s, s1, jsResult);
            }

            @Override
            public void onReceivedTitle(WebView webView, String s) {
                super.onReceivedTitle(webView, s);
            }

            @Override
            public void onProgressChanged(WebView webView, int progress) {
                super.onProgressChanged(webView, progress);

            }
        });

        mX5WebView.loadUrl(url);
    }
}
