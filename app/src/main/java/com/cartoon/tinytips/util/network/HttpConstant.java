package com.cartoon.tinytips.util.network;

import com.cartoon.tinytips.util.JudgeEmpty;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author cartoon
 * @version 1.1
 *
 * description
 * 服务器地址管理
 *
 * notice
 * 1.本类为单例，对象通过调用静态方法getConstant获取
 */

public class HttpConstant {

    private String address;  //原始项目地址

    private String URL_Comment;    //评论

    private String URL_CommentDetails;   //评论详情

    private String URL_Note;     //笔记

    private String URL_Information;   //个人信息

    private String URL_Text;

    private static volatile HttpConstant constant;

    private HttpConstant(){
        address=new String("http://192.168.31.29:8080/TinyTipsWEB/");
        URL_Comment=new StringBuilder(address).append("Comment").toString();
        URL_CommentDetails=new StringBuilder(address).append("CommentDetails").toString();
        URL_Note=new StringBuilder(address).append("Note").toString();
        URL_Information=new StringBuilder(address).append("Information").toString();
        URL_Text=new StringBuilder(address).append("Text").toString();
    }

    public static HttpConstant getConstant(){
        if(JudgeEmpty.isEmpty(constant)){
            synchronized (HttpConstant.class){
                if(JudgeEmpty.isEmpty(constant)){
                    constant=new HttpConstant();
                }
            }
        }
        return constant;
    }

    public String getURL_Comment() {
        return URL_Comment;
    }

    public String getURL_CommentDetails() {
        return URL_CommentDetails;
    }

    public String getURL_Note() {
        return URL_Note;
    }

    public String getURL_Information() {
        return URL_Information;
    }

    public String getURL_Text() {
        return URL_Text;
    }

}
