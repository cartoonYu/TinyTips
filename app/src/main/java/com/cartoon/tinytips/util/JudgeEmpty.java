package com.cartoon.tinytips.util;

public class JudgeEmpty {

    /**
     * 判断对象是否为空
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj){
        if(obj==null){
            return true;
        }
        return false;
    }

    /**
     * 判断对象是否不为空
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(Object obj){
        if(obj==null){
            return false;
        }
        return true;
    }
}
