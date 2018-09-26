package com.cartoon.tinytips.util;

import android.widget.Toast;

/**
 * toast出提示信息给用户
 */

public class ShowToast {

    public static void shortToast(String msg){
        Toast.makeText(TinyTipsApplication.getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    public static void longToast(String msg){
        Toast.makeText(TinyTipsApplication.getContext(),msg,Toast.LENGTH_LONG).show();
    }
}
