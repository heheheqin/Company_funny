package com.dream.will.company_funny.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.dream.will.company_funny.R;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.IOException;

public class SaoActivity extends AppCompatActivity implements CodeUtils.AnalyzeCallback {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sao);
        //创建
        CaptureFragment captureFragment = new CaptureFragment();

        //设置布局
        CodeUtils.setFragmentArgs(captureFragment,R.layout.capture_fragment);
        //设置解析回调接口
        captureFragment.setAnalyzeCallback(this);
        //
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout,captureFragment).commit();

    }

    @Override
    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
        //扫码成功后回调
        //获取intent
        Intent intent = getIntent();
        //设置结果
        Bundle bundle = new Bundle();
        bundle.putString(CodeUtils.RESULT_STRING,result);
        intent.putExtras(bundle);
        //setResult
        setResult(3,intent);
        //finish
        finish();
    }

    @Override
    public void onAnalyzeFailed() {
//扫码失败后回调
        //获取intent
        Intent intent = getIntent();
        //设置结果
        Bundle bundle = new Bundle();
        bundle.putString(CodeUtils.RESULT_STRING,"失败");
        intent.putExtras(bundle);
        //setResult
        setResult(3,intent);
        //finish
        finish();
    }
}
