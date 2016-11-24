package com.dream.will.company_funny;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Author：Will on 2016/11/21 09:22
 * Mail：heheheqin.will@gmail.com
 */

public class MainApplication extends Application {
    {

        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");


    }
    @Override
    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);
        ZXingLibrary.initDisplayOpinion(this);

    }
}
