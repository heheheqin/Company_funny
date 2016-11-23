package com.dream.will.company_funny.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.dream.will.company_funny.R;
import com.dream.will.company_funny.fragment.SplashFragment;
import com.dream.will.company_funny.utils.L;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class SplashActivity extends AppCompatActivity {

    List<Fragment> fragments;
    //做动画的view的id数组
    int[] viewId = {
            R.id.iv1, R.id.iv2, R.id.iv3
    };
    private ViewPager splashViewPager;
    private CircleIndicator dotLayout;
    private boolean isLeftScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        initView();
    }

    private void initView() {
        splashViewPager = (ViewPager) findViewById(R.id.splash_viewPager);
        dotLayout = (CircleIndicator) findViewById(R.id.dot_layout);

        fragments = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            SplashFragment splashFragment = new SplashFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(SplashFragment.INIT_KRY, i);
            splashFragment.setArguments(bundle);
            fragments.add(splashFragment);
        }
        splashViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        dotLayout.setViewPager(splashViewPager);

        //设置ViewPager动画  参数二  页面切换接口
        splashViewPager.setPageTransformer(true, new MyTrands());
        splashViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //根据state进行判断
                //如果是手指滑动状态，并互动界面时最后一页
                switch (state) {
                    //滑动
                    case ViewPager.SCROLL_STATE_DRAGGING: {
                        isLeftScroll = false;
                    }
                    break;
                    //停止
                    case ViewPager.SCROLL_STATE_IDLE: {
                        //最后一页时。滑动跳转
                        int currentItem = splashViewPager.getCurrentItem();
                        if (!isLeftScroll&&currentItem==splashViewPager.getAdapter().getCount()-1){
                            goMainActivity();
                        }
                        isLeftScroll = false;
                    }
                    break;
                    //惯性
                    case ViewPager.SCROLL_STATE_SETTLING: {
                        isLeftScroll = true;
                    }
                    break;
                }
            }
        });

    }

    public void skip(View c) {
        goMainActivity();
    }

    private void goMainActivity() {
        Intent intent = new Intent(this, CityChoiceActivity.class);
        startActivity(intent);
        finish();
    }


    class MyTrands implements ViewPager.PageTransformer {

        /**
         * @param page     当前移动view  Fragment 的界面是一个ViewGroup
         * @param position 当前界面偏移量
         */
        @Override
        public void transformPage(View page, float position) {
            //把page --->ViewGroup
            //遍历page 拿到三个做动画的ImageView
            //根据position设置view的属性
            float transx = page.getWidth() * position;
            for (int i = 0; i < viewId.length; i++) {
                View view = page.findViewById(viewId[i]);
                L.d("position=" + position);
                if (view != null) {
                    L.d("view===");
                    view.setTranslationX(transx);
                }
                transx *= 4f;
            }
        }
    }
}
