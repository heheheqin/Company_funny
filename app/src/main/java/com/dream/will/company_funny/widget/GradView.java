package com.dream.will.company_funny.widget;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.will.company_funny.R;
import com.dream.will.company_funny.ui.Main2Activity;
import com.dream.will.company_funny.utils.L;


/**
 * Author：Will on 2016/11/24 17:58
 * Mail：heheheqin.will@gmail.com
 */

public class GradView extends FrameLayout implements View.OnClickListener {

    ImageView xinfang;
    ImageView ershou;
    ImageView zufang;
    ImageView zixun;
    ImageView dazhe;
    ImageView zuixin;
    ImageView fangdaijisuan;
    ImageView gengduo;
    LinearLayout moreLinearLayout;
    LinearLayout zhuanwei;
    TextView lingqbi;
    TextView kanfangtuan;
    TextView huandai;


    public GradView(Context context) {
        super(context);
        init();
    }

    public GradView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.home_list_grad, this, true);
        xinfang = (ImageView) findViewById(R.id.xinfang);
        ershou = (ImageView) findViewById(R.id.ershou);
        zufang = (ImageView) findViewById(R.id.zufang);
        zixun = (ImageView) findViewById(R.id.zixun);
        dazhe = (ImageView) findViewById(R.id.dazhe);
        zuixin = (ImageView) findViewById(R.id.zuixin);
        fangdaijisuan = (ImageView) findViewById(R.id.fangdaijisuan);
        gengduo = (ImageView) findViewById(R.id.gengduo);
        xinfang.setOnClickListener(this);
        ershou.setOnClickListener(this);
        zufang.setOnClickListener(this);
        zixun.setOnClickListener(this);
        dazhe.setOnClickListener(this);
        zuixin.setOnClickListener(this);
        fangdaijisuan.setOnClickListener(this);
        gengduo.setOnClickListener(this);

        moreLinearLayout = (LinearLayout) findViewById(R.id.morelinearLayout);
        zhuanwei = (LinearLayout) findViewById(R.id.zhuanwei);
        moreLinearLayout.setVisibility(View.GONE);
        lingqbi = (TextView) findViewById(R.id.lingqbi);
        kanfangtuan = (TextView) findViewById(R.id.kanfangtuan);
        huandai = (TextView) findViewById(R.id.huandai);
        lingqbi.setOnClickListener(this);
        kanfangtuan.setOnClickListener(this);
        huandai.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        String tmp = null;
        switch (v.getId()) {
            case R.id.xinfang: {
                tmp = "xinfang";


            }
            break;
            case R.id.ershou: {tmp = "ershou";
            }
            break;
            case R.id.zufang: {tmp = "zufang";
            }
            break;
            case R.id.zixun: {tmp = "zixun";
            }
            break;
            case R.id.dazhe: {tmp = "dezhe";
            }
            break;
            case R.id.zuixin: {tmp = "zuixin";
            }
            break;
            case R.id.fangdaijisuan: {tmp = "fangdaijisuan";
            }
            break;
            case R.id.gengduo: {
                if (moreLinearLayout.getVisibility() == View.GONE) {
                    moreLinearLayout.setVisibility(View.VISIBLE);
                    zhuanwei.setVisibility(View.GONE);
                } else {
                    zhuanwei.setVisibility(View.VISIBLE);
                    moreLinearLayout.setVisibility(View.GONE);
                }
                tmp="gengduo";
            }
            break;
            case R.id.lingqbi: {tmp = "lingqibi";
            }
            break;
            case R.id.kanfangtuan: {tmp = "kanfangtuan";
            }
            break;
            case R.id.huandai: {tmp = "huandai";
            }
            break;

        }
        L.d("GradView :" + tmp);
        Toast.makeText(getContext(), tmp, Toast.LENGTH_SHORT).show();
    }
}
