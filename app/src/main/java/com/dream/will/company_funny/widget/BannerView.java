package com.dream.will.company_funny.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dream.will.company_funny.R;
import com.dream.will.company_funny.bean.BannerBean;
import com.dream.will.company_funny.inter.IHomePageList;
import com.dream.will.company_funny.utils.APIManager;
import com.dream.will.company_funny.utils.L;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author：Will on 2016/11/23 16:29
 * Mail：heheheqin.will@gmail.com
 * 实现自动轮播 向外提供设置城市id，
 * 只要设置城市id，就能自动获取数据，得到图片进行轮播
 */

public class BannerView extends FrameLayout {

    Banner banner;

    public BannerView(Context context) {
        super(context);
        init();
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.home_list_banner, this, true);
        banner = (Banner) findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
//        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(Arrays.asList(titles));
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
//        //banner设置方法全部调用完毕时最后调用
//        banner.start();
    }

    //提供城市id 设置方法
    public  void  setCityId(String cityId){
        //retrofit 联网加载数据
        Retrofit re = new Retrofit.Builder()
                .baseUrl(APIManager.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //获取接口实例
        IHomePageList iHomePageList = re.create(IHomePageList.class);
        Call<BannerBean> call = iHomePageList.getBannerBean(cityId);
        //发请求
        call.enqueue(new Callback<BannerBean>() {
            @Override
            public void onResponse(Call<BannerBean> call, Response<BannerBean> response) {
                //得到BannerBean list
                //解析 list 得到url  标题
                List<String> imgUrls = new ArrayList<String>();
                List<String> titles = new ArrayList<String>();
                BannerBean body = response.body();
                List<BannerBean.DataBean> dataBeen = body.getData() ;
                for (BannerBean.DataBean dataBean1 : dataBeen) {
                    imgUrls.add(dataBean1.getPicurl());
                    titles.add(dataBean1.getTitle());
                }
                L.d("imgUrls"+imgUrls.size());
                banner.setImages(imgUrls);
                banner.setBannerTitles(titles);
                banner.start();
            }

            @Override
            public void onFailure(Call<BannerBean> call, Throwable t) {

            }
        });

    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(context).load(path).into(imageView);
        }
    }
}
