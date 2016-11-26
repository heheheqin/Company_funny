package com.dream.will.company_funny.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.will.company_funny.R;
import com.dream.will.company_funny.fragment.HomeListViewDpinglunFragment;
import com.dream.will.company_funny.fragment.HomeListViewDshowFragment;
import com.dream.will.company_funny.utils.IntentUtils;
import com.dream.will.company_funny.utils.L;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.List;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class HomeListViewDetailActivity extends SwipeBackActivity implements View.OnClickListener {

    String url;
    String title;
    List<Fragment> fragments;
    private ImageView back;
    private ImageView share;
    private ViewPager viewpager;
    private TextView action_title;
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
//            Log.d("plat","platform"+platform);
            Toast.makeText(HomeListViewDetailActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(HomeListViewDetailActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                L.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(HomeListViewDetailActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };
    private TextView fabaiopinglun1;
    SwipeBackLayout swipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_list_view_detail);
        //获取  swipeBackLayout
        swipeBackLayout = getSwipeBackLayout();
        //设置 互动的区域
        swipeBackLayout.setEdgeSize(200);
        // 设定滑动关闭的方向  下左右
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        Intent intent = getIntent();
        url = intent.getStringExtra(IntentUtils.KEY_NEWSID);
        initData();
        initView();
    }

    private void initData() {
        fragments = new ArrayList<>();
        HomeListViewDshowFragment homeListViewDshowFragment = new HomeListViewDshowFragment();
        Bundle bundle = new Bundle();
        bundle.putString(IntentUtils.KEY_NEWSID, url);
        L.d("fragment1::"+url);
        homeListViewDshowFragment.setArguments(bundle);
        fragments.add(homeListViewDshowFragment);

        HomeListViewDpinglunFragment homeListViewDpinglunFragment = new HomeListViewDpinglunFragment();
        fragments.add(homeListViewDpinglunFragment);
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        share = (ImageView) findViewById(R.id.share);
        back.setOnClickListener(this);
        share.setOnClickListener(this);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0: {
                        action_title.setText("咨询详情");
                        swipeBackLayout.setEnabled(true);
                    }
                    break;
                    case 1: {
                        action_title.setText("评论");
                        swipeBackLayout.setEnabled(false);
                    }
                    break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        action_title = (TextView) findViewById(R.id.action_title);
        fabaiopinglun1 = (TextView) findViewById(R.id.fabaiopinglun1);
        fabaiopinglun1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back: {
                finish();
            }
            break;
            case R.id.share: {
                Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
                new ShareAction(HomeListViewDetailActivity.this).withText(title)
                        .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                        .setCallback(umShareListener).open();
            }
            break;
            case  R.id.fabaiopinglun1:{
                if (viewpager.getCurrentItem()==0){
                    viewpager.setCurrentItem(1);
                }else {
                    viewpager.setCurrentItem(0);
                }
            }
        }
    }

    //回调授权
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }
}
