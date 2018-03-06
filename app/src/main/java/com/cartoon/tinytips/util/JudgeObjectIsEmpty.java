package com.cartoon.tinytips.util;

import java.util.List;

/**
 * Created by cartoon on 2018/3/6.
 */

public class JudgeObjectIsEmpty {
    public static boolean isNotEmpty(Object obj){
        if(obj==null){
            return false;
        }
        else{
            return true;
        }
    }
}
