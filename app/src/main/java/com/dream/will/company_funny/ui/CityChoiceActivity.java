package com.dream.will.company_funny.ui;

import android.os.Bundle;

import com.dream.will.company_funny.R;
import com.dream.will.company_funny.adapter.CityChoiceAdapter;
import com.dream.will.company_funny.bean.CityBean;
import com.dream.will.company_funny.inter.ICityChoice;
import com.dream.will.company_funny.utils.APIManager;
import com.dream.will.company_funny.utils.CityJsonUtils;
import com.dream.will.company_funny.utils.L;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class CityChoiceActivity extends BaseNoActionBarActivity {

    List<CityBean> data;
    private StickyListHeadersListView stickyListView;
    CityChoiceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_citychoice);
        initView();
        initData();
        adapter = new CityChoiceAdapter(this,data);
        stickyListView.setAdapter(adapter);
        L.d("CityChoiceActivity");
        getCityData();


    }

    private void getCityData() {
        //联网下载数据
        //1。定义Retrofit对象，构建者模式
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        //2。创建接口ICityChoice实例
        ICityChoice iCityChoice = retrofit.create(ICityChoice.class);
        //3。调用接口实例
        Call<String> call = iCityChoice.getCity();
        //4。发请求
        call.enqueue(new Callback<String>() {
            /**请求成功
             * @param call
             * @param response
             */
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                L.d(response.body());
                String body = response.body();
                try {
                    List<CityBean> citys = CityJsonUtils.getCityByJson(body);
                    data.clear();
                    data.addAll(citys);
                    //解析成功打印长度
                    adapter.notifyDataSetChanged();
                    L.d(citys.size() + "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            /**请求失败
             * 网络不通畅  服务器挂了
             * @param call
             * @param t
             */
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                L.d("  请求失败");
            }
        });
    }

    private void initData() {
        data = new ArrayList<>();
    }

    private void initView() {
        stickyListView = (StickyListHeadersListView) findViewById(R.id.stickyListView);
    }
}
