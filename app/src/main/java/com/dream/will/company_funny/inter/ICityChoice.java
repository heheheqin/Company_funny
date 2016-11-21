package com.dream.will.company_funny.inter;

import com.dream.will.company_funny.utils.APIManager;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Author：Will on 2016/11/21 16:49
 * Mail：heheheqin.will@gmail.com
 */

public interface ICityChoice {
    @GET(APIManager.CHOICE_CITY)
    Call<String> getCity();
}
