package com.dream.will.company_funny.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.will.company_funny.R;
import com.dream.will.company_funny.utils.L;
import com.dream.will.company_funny.widget.ViewDragLayout;

public class Main2Activity extends AppCompatActivity {

    private ViewDragLayout viewDraglayout;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }


    private void initView() {
        viewDraglayout = (ViewDragLayout) findViewById(R.id.viewDraglayout);
        viewDraglayout.setOnViewDragListener(new ViewDragLayout.ViewDragListener() {
            @Override
            public void onOpen() {
                title.setText("heheheqin  ---向右滑--->");
                L.d("onOpen");
            }

            @Override
            public void onClose() {
                title.setText(R.string.heheheqin);
                L.d("onClose");
            }

            @Override
            public void onDrag(float percent) {
            }
        });
        title = (TextView) findViewById(R.id.title);
    }

    public void clickbutton1(View c) {
        Toast.makeText(this, "打我呀", Toast.LENGTH_SHORT).show();
    }

    public void clickbutton2(View c) {
        Toast.makeText(this, "捉迷藏", Toast.LENGTH_SHORT).show();
    }
}
