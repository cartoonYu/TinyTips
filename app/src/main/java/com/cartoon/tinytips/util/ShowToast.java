package com.cartoon.tinytips.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

/**
 * toast出提示信息给用户
 */

public class ShowToast {

    private static Handler shortHandler=new Handler(){
        public void handleMessage(Message message){
            String msg=(String)message.obj;
            Toast.makeText(TinyTipsApplication.getContext(),msg,Toast.LENGTH_SHORT).show();
        }
    };

    private static Handler longHandler=new Handler(){
        public void handleMessage(Message message){
            String msg=(String)message.obj;
            Toast.makeText(TinyTipsApplication.getContext(),msg,Toast.LENGTH_LONG).show();
        }
    };


    public static void shortToast(String msg){
        Message message=new Message();
        message.obj=msg;
        shortHandler.sendMessage(message);
    }

    public static void longToast(String msg){
        Message message=new Message();
        message.obj=msg;
        longHandler.sendMessage(message);
    }
}
