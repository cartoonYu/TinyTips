package com.cartoon.tinytips.util;

import android.app.Application;
import android.content.Context;

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
