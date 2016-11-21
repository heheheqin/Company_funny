package com.dream.will.company_funny.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dream.will.company_funny.inter.ICityChoice;
import com.dream.will.company_funny.utils.APIManager;
import com.dream.will.company_funny.utils.L;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class CityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.d("CityActivity");
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
                L.d(response.body());
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
}
