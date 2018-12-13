package com.cartoon.tinytips.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Note;

import java.io.Serializable;

/**
 * 功能：根据调用的方法以及参数的不同跳转活动
 */

public class IntentActivity{

    private static Intent intent;

    /**
     * 使用intent无数据跳转活动
     * @param context
     * @param des
     */
    public static void intentWithoutData(Context context,Class<?> des){
        //context为开始的活动，des为目标活动
        intent=new Intent(context,des);
        intent(context,intent);
    }

    /**
     * 使用intent携带整型数值数据跳转活动
     * context为开始的活动，des为目标活动,data为控制fragment显示的变量
     * @param context
     * @param des
     * @param str
     * @param data(传递的数据)
     */
    public static void intentWithData(Context context,Class<?> des,String str,int data){
        intent=new Intent(context,des);
        intent.putExtra(str, data);
        intent(context,intent);
    }

    /**
     * 使用intent携带字符串数值数据跳转活动
     * context为开始的活动，des为目标活动,data为控制fragment显示的变量
     * @param context
     * @param des
     * @param str
     * @param data(传递的数据)
     */
    public static void intentWithData(Context context,Class<?> des,String str,String data){
        intent=new Intent(context,des);
        intent.putExtra(str, data);
        intent(context,intent);
    }

    /**
     * 使用intent携带bean对象跳转活动
     * context为开始的活动，des为目标活动,data为控制fragment显示的变量
     * @param context
     * @param des
     * @param str
     * @param bean
     */
    public static void intentWithData(Context context,Class<?> des,String str,Serializable bean){
        intent=new Intent(context,des);
        intent.putExtra(str, bean);
        intent(context,intent);
    }

    /**
     * 使用intent携带整型数值数据以及bean对象跳转活动
     * context为开始的活动，des为目标活动,data为控制fragment显示的变量
     * @param context
     * @param des
     * @param str
     * @param data(传递的数据)
     * @param str1
     * @param bean
     */
    public static void intentWithData(Context context, Class<?> des, String str, int data, String str1, Serializable bean){
        intent=new Intent(context,des);
        intent.putExtra(str, data);
        intent.putExtra(str1,bean);
        intent(context,intent);
    }

    public static void finishActivity(Context context){
        Activity activity=(Activity)context;
        activity.finish();
    }

    /**
     * 返回活动跳转中intent携带的整型数值
     * @param context
     * @param str
     * @param defaultData（默认返回数值）
     * @return
     */
    public static int getIntentData(Context context,String str,int defaultData){
        Activity activity=(Activity)context;
        intent=activity.getIntent();
        return intent.getIntExtra(str,defaultData);
    }

    /**
     * 返回活动跳转中intent携带的字符串
     * @param context
     * @param str
     * @return
     */
    public static String getIntentData(Context context,String str){
        Activity activity=(Activity)context;
        intent=activity.getIntent();
        return intent.getStringExtra(str);
    }

    /**
     * 返回活动跳转中intent携带的Information
     * @param context
     * @param str
     * @return
     */
    public static Information getIntentInformation(Context context,String str){
        Activity activity=(Activity)context;
        intent=activity.getIntent();
        Serializable bean=intent.getSerializableExtra(str);
        Information information=null;
        if(bean instanceof Information){
            information=(Information)bean;
        }
        return information;
    }

    /**
     * 返回活动跳转中intent携带的Note
     * @param context
     * @param str
     * @return
     */
    public static Note getIntentNote(Context context,String str){
        Activity activity=(Activity)context;
        intent=activity.getIntent();
        Serializable bean=intent.getSerializableExtra(str);
        Note note=null;
        if(bean instanceof Note){
            note=(Note) bean;
        }
        return note;
    }

    private static void intent(Context context,Intent intent){
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
