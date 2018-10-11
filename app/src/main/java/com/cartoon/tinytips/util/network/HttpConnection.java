package com.cartoon.tinytips.util.network;

import android.util.Log;

import com.cartoon.tinytips.util.JSON.JSONArrayOperation;
import com.cartoon.tinytips.util.JudgeEmpty;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cartoon
 * @version 1.1
 *
 * description
 * 1.使用http进行网络传输数据
 *
 * how to use
 * 1.通过静态方法getConstant获取获取本类对象
 * 2.通过调用sendJSONObject或者sendJSONArray方法将数据传入类中
 * 3.开启线程，调用run方法进行数据传输
 * 4.线程等待，通过getResult方法返回数据
 *
 * notice
 * 1.本类为单例
 * 2.对象通过调用静态方法getConstant获取
 */

public class HttpConnection implements Runnable{

    private volatile static HttpConnection httpConnection;

    private String url;   //ip地址

    private String method;   //服务器中servlet的位置

    private HttpURLConnection urlConnection;

    private int TIME_OUT;     //连接超时时间

    private OutputStream outputStream;

    private InputStream inputStream;

    private JSONObject object;

    private JSONArray data;

    private String result;

    private HttpConnection(){
        result=new String();
    }

    /**
     * 功能
     * 获取本类对象
     *
     * @return
     */
    public static HttpConnection getHttpConnection(){
        if(JudgeEmpty.isEmpty(httpConnection)){
            synchronized (HttpConnection.class){
                if(JudgeEmpty.isEmpty(httpConnection)){
                    httpConnection=new HttpConnection();
                }
            }
        }
        return httpConnection;
    }

    /**
     * 功能
     * 客户端数据发送到服务器端
     *
     * 使用方法
     * 1.传入ip地址，处理方法，需要发送的JSONObject对象
     *
     * @param url
     * @param method
     * @param object
     */
    public void sendJSONObject(String url,String method, JSONObject object){
        this.url=url;
        this.method=method;
        this.object=object;
        List<JSONObject> temp=new ArrayList<>();
        temp.add(object);
        data=JSONArrayOperation.getOperation().setObjectToArray(temp);
    }

    /**
     * 功能
     * 客户端数据发送到服务器端
     *
     * 使用方法
     * 1.传入ip地址，处理方法，需要发送的JSONArray对象
     *
     * @param url
     * @param method
     * @param array
     */
    public void sendJSONArray(String url,String method,JSONArray array){
        this.url=url;
        this.method=method;
        this.data=array;
    }

    /**
     * 功能
     * 网络传输数据
     *
     * 使用方法
     * 1.新建线程，将本类对象传入
     * 2.调用新建线程的run方法启动网络传输
     */
    @Override
    public void run(){
        StringBuffer buffer=new StringBuffer();
        getURLConnection(url,method);
        if(JudgeEmpty.isNotEmpty(urlConnection)) {
            getOutputStream(urlConnection);
            if (JudgeEmpty.isNotEmpty(outputStream)) {
                try{
                    String temp=data.toString();
                    outputStream.write(temp.getBytes());
                    outputStream.close();
                    getInputStream(urlConnection);
                    BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    while((line=reader.readLine())!=null){
                        buffer.append(line);
                    }
                    inputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                    Log.e("networkException",new String("写入错误"));
                }
            }
        }
        result=buffer.toString();
        urlConnection.disconnect();
    }

    /**
     * 功能
     * 将服务器返回的结果转换成JSON文件返回
     *
     * 使用方法
     * 1.子线程完成后通过对象调用获取返回值
     * @return
     */
    public String getResult(){
        return result;
    }

    /**
     * 功能
     * 根据传入的url以及方法获取网络连接的输出流
     *
     * @param url
     * @param method
     * @return
     */
    private void getURLConnection(String url,String method){
        try{
            urlConnection=(HttpURLConnection)new URL(url).openConnection();
            urlConnection.setConnectTimeout(TIME_OUT);
            urlConnection.setRequestMethod(method);
        }catch(MalformedURLException e){
            Log.e("networkException",new String("ip地址错误"));
            e.printStackTrace();
        }catch (IOException e){
            Log.e("networkException",new String("io错误"));
            e.printStackTrace();
        }
    }

    /**
     * 功能
     * 根据传入的connection获取对应的输出流
     *
     * @param connection
     * @return
     */
    private void getOutputStream(HttpURLConnection connection){
        try{
            outputStream=connection.getOutputStream();
        }catch (IOException e){
            e.printStackTrace();
            Log.e("networkException",new String("获取输出流错误"));
        }
    }

    /**
     * 功能
     * 根据传入的connection获取对应的输入流
     *
     * @param connection
     * @return
     */
    private void getInputStream(HttpURLConnection connection){
        inputStream=null;
        try{
            inputStream=connection.getInputStream();
        }catch (IOException e){
            Log.e("networkException",new String("获取输入流错误"));
            e.printStackTrace();
        }
    }

}
