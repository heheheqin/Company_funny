package com.dream.will.company_funny.fragment;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.dream.will.company_funny.R;
import com.dream.will.company_funny.ui.DiscoverActivity;
import com.dream.will.company_funny.utils.L;

/**
 * Author：Will on 2016/11/21 14:33
 * Mail：heheheqin.will@gmail.com
 */

public class DiscoverFragment extends Fragment {
    View view;
    WebView webView;
    ImageView iv;
    AnimationDrawable anim;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.d("DiscoverFragment --- onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.discover_fragment_layout, container, false);
        return view ;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        webView = (WebView) view.findViewById(R.id.webView);
        //设置动画
        iv = (ImageView) view.findViewById(R.id.imageView);
        anim = (AnimationDrawable) iv.getBackground();
        anim.start();
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
                anim.stop();
                iv.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Intent intent = new Intent(getActivity(), DiscoverActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        //加载网址
        webView.loadUrl("http://m.db.house.qq.com/index.php?mod=appkft&act=discover&cityid=4&rf=kanfang");
//        webView.
    }
    public  WebView getWebview(){
        return webView;
    }
}
