package com.dream.will.company_funny.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.will.company_funny.R;
import com.dream.will.company_funny.utils.L;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

public class DiscoverActivity extends BaseNoActionBarActivity implements View.OnClickListener {

    String url;
    private ImageView back;
    private TextView action_title;
    private ImageView share;
    private WebView webView;
    private ImageView back_button;
    private ImageView next_button;
    private ImageView refresh_button;
    private ImageView close_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        initView();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        action_title = (TextView) findViewById(R.id.action_title);
        share = (ImageView) findViewById(R.id.share);
        back.setOnClickListener(this);
        share.setOnClickListener(this);
        webView = (WebView) findViewById(R.id.webview);
        webView.canGoBack();
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setJavaScriptEnabled(true);
        //监听
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                action_title.setText(title);
            }

        });
        webView.setWebViewClient(new WebViewClient() {
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
                webView.loadUrl(url);
                return true;
            }
        });
        //加载网址
        webView.loadUrl(url);
        back_button = (ImageView) findViewById(R.id.back_button);
        back_button.setOnClickListener(this);
        next_button = (ImageView) findViewById(R.id.next_button);
        next_button.setOnClickListener(this);
        refresh_button = (ImageView) findViewById(R.id.refresh_button);
        refresh_button.setOnClickListener(this);
        close_button = (ImageView) findViewById(R.id.close_button);
        close_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
            case R.id.back_button:
            {
//                Intent intent = new Intent(this,)
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish();
                }
            }
            break;
            case R.id.share: {
                Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
                new ShareAction(DiscoverActivity.this).withText("hello")
                        .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                        .setCallback(umShareListener).open();
            }
            break;

            case R.id.refresh_button:{
                webView.loadUrl(url);
            }break;
            case R.id.close_button:{
                finish();
            }break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    //回调授权
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
//            Log.d("plat","platform"+platform);

            Toast.makeText(DiscoverActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(DiscoverActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                L.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(DiscoverActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

}
