package com.dream.will.company_funny.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dream.will.company_funny.R;
import com.dream.will.company_funny.utils.L;

/**
 * Author：Will on 2016/11/21 14:33
 * Mail：heheheqin.will@gmail.com
 */

public class DiscoverFragment extends Fragment {
    View view;
    WebView webView;

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
            }
        });
        //加载网址
        webView.loadUrl("http://m.db.house.qq.com/index.php?mod=appkft&act=discover&cityid=4&rf=kanfang");

    }
    public  WebView getWebview(){
        return webView;
    }
}
