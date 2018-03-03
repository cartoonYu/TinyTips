package com.cartoon.tinytips.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by cartoon on 2018/3/3.
 * 全局获取context
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
