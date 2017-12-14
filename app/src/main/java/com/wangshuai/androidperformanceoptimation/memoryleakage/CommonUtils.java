package com.wangshuai.androidperformanceoptimation.memoryleakage;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by 王帅 on 2017/12/11.
 */

public class CommonUtils {
    private Context context;
    private static CommonUtils instance;

    public CommonUtils(Context context) {
        this.context = context;
    }

    public static CommonUtils getInstance(Context context){
        if (instance == null){
            instance = new CommonUtils(context);
        }

        return instance;
    }

    /**
     * 正确用法：
     *
     * public CommonUtils(Context context){
     *     this.context = context.getApplicationContext();
     * }
     * 保证context生命周期和Application的一样长
     */
}
