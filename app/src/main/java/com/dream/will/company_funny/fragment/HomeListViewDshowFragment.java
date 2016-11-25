package com.dream.will.company_funny.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dream.will.company_funny.R;
import com.dream.will.company_funny.bean.HomeNewsDetail;
import com.dream.will.company_funny.inter.IHomePageList;
import com.dream.will.company_funny.utils.APIManager;
import com.dream.will.company_funny.utils.IntentUtils;
import com.dream.will.company_funny.utils.L;
import com.dream.will.company_funny.widget.HomeDetailView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author：Will on 2016/11/25 16:28
 * Mail：heheheqin.will@gmail.com
 * 
 */

public class HomeListViewDshowFragment extends Fragment {

    String url;
    String title;

    HomeDetailView mHomeDetailView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        url = arguments.getString(IntentUtils.KEY_NEWSID);
        L.d("fragment::"+url);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.framgmet_home_detail1,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHomeDetailView = (HomeDetailView) view.findViewById(R.id.homeDetailView);
        initData();
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IHomePageList home = retrofit.create(IHomePageList.class);
        Call<HomeNewsDetail> call = home.getHomeNewsDetail(url);
        call.enqueue(new Callback<HomeNewsDetail>() {
            @Override
            public void onResponse(Call<HomeNewsDetail> call, Response<HomeNewsDetail> response) {
                HomeNewsDetail body = response.body();
                L.d("fragment::"+body);
                mHomeDetailView.setData(body);
                title = body.getTitle();
            }

            @Override
            public void onFailure(Call<HomeNewsDetail> call, Throwable t) {
                L.d("fragment::加载失败");
            }
        });

    }
}
