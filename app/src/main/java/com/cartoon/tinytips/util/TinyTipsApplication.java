package com.cartoon.tinytips.util;

import android.app.Application;
import android.content.Context;

/**
 * 通过静态方法getContext()获取全局Context
 */

public class TinyTipsApplication extends Application{

    private static Context context;

    @Override
    public void onCreate(){
        super.onCreate();
        context=getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }

}
