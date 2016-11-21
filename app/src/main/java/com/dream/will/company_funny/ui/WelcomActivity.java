package com.dream.will.company_funny.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dream.will.company_funny.R;
import com.dream.will.company_funny.utils.SharedUtils;

public class WelcomActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //延迟两秒跳转
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //判断是狗屎第一次启动，如果是a第一个跳转到引导界面
                if (SharedUtils.isFirstRun(WelcomActivity.this)) {
                    Intent intent = new Intent(WelcomActivity.this, SplashActivity.class);
                    startActivity(intent);
                }else {
                    //不是第一个启动天赚到主界面
                    Intent intent = new Intent(WelcomActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        },2000);
    }

    public static class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_welcome);
        }
    }
}











