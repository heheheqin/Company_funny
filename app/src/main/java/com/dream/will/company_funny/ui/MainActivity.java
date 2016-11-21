package com.dream.will.company_funny.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.will.company_funny.R;
import com.dream.will.company_funny.fragment.DiscoverFragment;
import com.dream.will.company_funny.fragment.HomeFragment;
import com.dream.will.company_funny.fragment.MessageFragment;
import com.dream.will.company_funny.fragment.MineFragment;
import com.dream.will.company_funny.utils.SharedUtils;

public class MainActivity extends AppCompatActivity {


    private int[] imgIds = {
            R.drawable.tab_home_select,
            R.drawable.tab_descover_select,
            R.drawable.tab_meg_select,
            R.drawable.tab_mine_select
    };
    //TabHost使用Fragment类
    private Class[] fragments = {
            HomeFragment.class, DiscoverFragment.class, MessageFragment.class, MineFragment.class
    };
    private FrameLayout tabcontent;
    private FrameLayout fragment;
    private FragmentTabHost tabhost;
    private String[] tabTextsName = {
            "首页", "发现", "消息", "我的"
    };
    private LayoutInflater inflater;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedUtils.saveFirstRun(this);
        initView();
    }

    private void initView() {
        inflater = LayoutInflater.from(this);
        tabcontent = (FrameLayout) findViewById(android.R.id.tabcontent);
        tabhost = (FragmentTabHost) findViewById(android.R.id.tabhost);
//        tabhost.getTabWidget().setDividerDrawable(android.R.color.transparent);
        fragment = (FrameLayout) findViewById(R.id.fragment);
        /**
         *参数三：要现实Fragment容器ID
         */
        tabhost.setup(this, getSupportFragmentManager(), R.id.fragment);
        //给tabhost添加Tab
        for (int i = 0; i < tabTextsName.length; i++) {
            //创建新的tab
            TabHost.TabSpec tabItem = tabhost.newTabSpec(i + "");
            //设置内容   ?content
            tabItem.setIndicator(getTabItemView(i));
            /**
             * 参数一：tab标签
             * 参数二：tab内通Fragment类
             * 参数三： Bundle
             */
            tabhost.addTab(tabItem, fragments[i], null);
            //取消分割线
            tabhost.getTabWidget().setDividerDrawable(android.R.color.transparent);
        }
    }

    private View getTabItemView(int index) {
        View view = inflater.inflate(R.layout.tab_item_layout, null);
        //拿到控件值
        ImageView iv = (ImageView) view.findViewById(R.id.tab_img);
        iv.setImageResource(imgIds[index]);
        TextView textView = (TextView) view.findViewById(R.id.tab_text);
        textView.setText(tabTextsName[index]);
        return view;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次返回", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
