package com.dream.will.company_funny.utils;

import android.inputmethodservice.Keyboard;
import android.util.Log;

/**
 * Author：Will on 2016/11/21 11:16
 * Mail：heheheqin.will@gmail.com
 *
 * 发布时关闭
 *
 */

public class L {
    private static final boolean  DEBAG= true;
    private static final String TAG = "TAG";
    public static void d(String msg){
        if (DEBAG) {
            Log.d(TAG, "--------------"+msg);
        }
    }
    public static void d(String tag,String msg){
        if (DEBAG) {
            Log.d(tag, "-------tag-------"+msg);
        }
    }
}
