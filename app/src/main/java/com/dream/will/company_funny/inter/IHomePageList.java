package com.dream.will.company_funny.inter;

import com.dream.will.company_funny.bean.BannerBean;
import com.dream.will.company_funny.bean.HomeNewsDetail;
import com.dream.will.company_funny.utils.APIManager;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Author：Will on 2016/11/21 16:49
 * Mail：heheheqin.will@gmail.com
 */

public interface IHomePageList {
    @GET(APIManager.HOUSE_PAGE_FIRST_PAGE_LISTVIEW)   //3)刷新时：pageflag=1,buttonmore=1,cityid;
    Call<String> getListContent(@Query("pageflag") String pageflag,@Query("buttonmore") String buttonmore,@Query("cityid") String cityid,@Query("lastid") String lastid);

    @GET(APIManager.HOUSE_PAGE_FIRST_PAGE_LISTVIEN)
    Call<String> getMore(@Query("cityid") String cityid,@Query("lastid") String lastid);

    @GET(APIManager.HOME_BANNER)
    Call<BannerBean> getBannerBean(@Query("cityid") String cityid);


    @GET(APIManager.NEWS_DETAIL)
    Call<HomeNewsDetail> getHomeNewsDetail(@Query("newsid") String newsid);
}
