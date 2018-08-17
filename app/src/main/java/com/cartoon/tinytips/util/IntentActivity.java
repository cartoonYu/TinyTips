package com.cartoon.tinytips.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

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
     * 使用intent携带bean对象跳转活动
     * context为开始的活动，des为目标活动,data为控制fragment显示的变量
     * @param context
     * @param des
     * @param obj
     */
    public static void intentWithBean(Context context,Class<?> des,Object obj){

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
     * 返回活动跳转中intent携带的bean对象
     * @param context
     * @param str
     * @param defaultData
     * @return
     */
    public static Object getIntentData(Context context,String str,Object defaultData){
        return null;
    }

    private static void intent(Context context,Intent intent){
        context.startActivity(intent);
        Activity activity=(Activity)context;
        activity.finish();
    }

}
