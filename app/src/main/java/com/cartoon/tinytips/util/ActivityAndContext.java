package com.cartoon.tinytips.util;

import android.app.Activity;
import android.content.Context;

public class ActivityAndContext {

    public static Activity getActivity(Context context){
        return (Activity)context;
    }
}
