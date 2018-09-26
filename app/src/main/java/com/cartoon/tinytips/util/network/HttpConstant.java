package com.cartoon.tinytips.util.network;

import com.cartoon.tinytips.util.JudgeEmpty;

/**
 * 服务器地址管理
 */

public class HttpConstant {

    private String address;  //原始项目地址

    private String URL_Comment;    //评论

    private String URL_CommentDetails;   //评论详情

    private String URL_Note;     //笔记

    private String URL_PersonalInformation;   //个人信息

    private String URL_Test;

    private static volatile HttpConstant constant;

    private HttpConstant(){
        address=new String("http://192.168.31.29:8080/TinyTipsWEB");
        URL_Comment=new StringBuilder(address).append("CommentServlet").toString();
        URL_CommentDetails=new StringBuilder(address).append("CommentDetailsServlet").toString();
        URL_Note=new StringBuilder(address).append("NoteServlet").toString();
        URL_PersonalInformation=new StringBuilder(address).append("PersonalInformationServlet").toString();
        URL_Test=new StringBuilder(address).append("Test").toString();
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

    public String getURL_PersonalInformation() {
        return URL_PersonalInformation;
    }

    public String getURL_Test() {
        return URL_Test;
    }
}
