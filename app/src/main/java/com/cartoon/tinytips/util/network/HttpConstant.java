package com.cartoon.tinytips.util.network;

import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.TinyTipsApplication;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author cartoon
 * @version 1.2
 *
 * description
 * 1.服务器地址管理
 * 2.数据来源为资源文件
 *
 * how to use
 * 1.需要切换本地调试以及远程服务器连接，修改filePath的值
 * 2.ip地址通过getter获取
 *
 * notice
 * 1.本类为单例
 * 2.对象通过调用静态方法getConstant获取
 */

public class HttpConstant {

    private Properties properties;

    private String filePath;

    private static volatile HttpConstant constant;

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

    private HttpConstant(){
        setFilePath();
        setProperties();
    }

    private void setProperties() {
        InputStream stream=null;
        properties=new Properties();
        try {
            stream=TinyTipsApplication.getContext().getAssets().open(filePath);
            properties.load(stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setFilePath() {
        filePath=new String("url_server.properties");
    }

    public String getURL_Comment(String method) {
        StringBuilder temp=new StringBuilder(properties.getProperty("comment"));
        return temp.append(method).toString();
    }

    public String getURL_Note(String method) {
        StringBuilder temp=new StringBuilder(properties.getProperty("note"));
        return temp.append(method).toString();
    }

    public String getURL_Information(String method) {
        StringBuilder temp=new StringBuilder(properties.getProperty("information"));
        return temp.append(method).toString();
    }

    public String getURL_Social(String method) {
        StringBuilder temp=new StringBuilder(properties.getProperty("social"));
        return temp.append(method).toString();
    }

    public String getURL_StatSocial(String method) {
        StringBuilder temp=new StringBuilder(properties.getProperty("statSocial"));
        return temp.append(method).toString();
    }

    public String getURL_CommentDetails(String method) {
        StringBuilder temp=new StringBuilder(properties.getProperty("commentDetails"));
        return temp.append(method).toString();
    }

}
