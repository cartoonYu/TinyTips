package com.cartoon.tinytips.util;


import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;

/**
 * @author cartoon
 * @version 1.0
 *
 * description
 * decode and encode String
 *
 * how to use
 * 1.use public method
 *
 * notice
 * none
 */

public class Coder {

    private static volatile Coder coder;

    public static Coder getCoder(){
        if(JudgeEmpty.isEmpty(coder)){
            synchronized (Coder.class){
                if(JudgeEmpty.isEmpty(coder)){
                    coder=new Coder();
                }
            }
        }
        return coder;
    }

    /**
     * 功能
     * 1.对byte数组进行Base64编码
     *
     * @param bytes
     * @return
     */
    public String encode(byte[] bytes){
        String result=Base64.encodeToString(bytes,0,bytes.length,Base64.DEFAULT);
        return result.replace("\n","");
    }

    /**
     * 功能
     * 1.对String进行Base64解码
     *
     * @param source
     * @return
     */
    public byte[] decode(String source){
        return Base64.decode(source,Base64.DEFAULT);
    }

    private Coder(){
    }
}
