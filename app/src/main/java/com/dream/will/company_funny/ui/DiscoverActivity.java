package com.dream.will.company_funny.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.dream.will.company_funny.R;

public class DiscoverActivity extends AppCompatActivity {

    private ImageView back;
    private TextView action_title;
    private ImageView share;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        initView();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        action_title = (TextView) findViewById(R.id.action_title);
        share = (ImageView) findViewById(R.id.share);
        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().getJavaScriptEnabled();
        //小视频浏览优化 保证对PC等宽页面能良好显示
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        //图片自动
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.canGoBack();
        webView.loadUrl("javascript:window.history.back();");
        //监听
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }

        });
        webView.setWebViewClient(new WebViewClient(){
            @Override//网页加载结束回调
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                ptrFrame.refreshComplete();
//                anim.stop();
//                iv.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                Intent intent = new Intent()
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        //加载网址
        webView.loadUrl("");
    }
}
