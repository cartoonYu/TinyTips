package com.cartoon.tinytips.util.network;

import android.util.Log;

import com.cartoon.tinytips.util.JudgeEmpty;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * @author cartoon
 * @version 1.0
 * Description
 * 本类功能:与服务器进行交互
 *
 * 使用方法
 * 1.获取实例:调用静态方法getConnection获取
 * 1.向服务器发送数据:调用方法sendData，通过getResult获知发送结果
 * 2.获取服务器数据:调用方法getData
 *
 * bug
 * 1.发送以及接收到的数据在主线程获取不了
 */

public class HttpConnection {

    private volatile static HttpConnection httpConnection;

    private HttpURLConnection urlConnection;

    private int TIME_OUT;     //连接超时时间

    private OutputStream outputStream;

    private InputStream inputStream;

    private JSONObject object;   //服务器返回数据

    private String result;   //判断发送数据是否成功

    private HttpConnection(){
        urlConnection=null;
        outputStream=null;
        inputStream=null;
        object=null;
        result=new String("");
        TIME_OUT=8000;
    }

    /**
     * 返回HttpConnection实例
     * @return
     */
    public static HttpConnection getConnection(){
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
     * 客户端数据发送到服务器端
     * @param url 服务器对应的ip地址
     * @param method 请求的方法，只能是POST
     * @param object 需要发送到服务器的数据
     */
    public void sendData(final String url,final String method, final JSONObject object){
        new Thread(){
            @Override
            public void run(){
                getURLConnection(url,method);
                if(JudgeEmpty.isNotEmpty(urlConnection)){
                    getOutputStream(urlConnection);
                    if(JudgeEmpty.isNotEmpty(outputStream)){
                        try{
                            String temp=object.toString();
                            outputStream.write(temp.getBytes());
                            outputStream.close();
                            getInputStream(urlConnection);
                            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
                            String line;
                            while((line=reader.readLine())!=null){
                                result=line;
                            }
                        }catch (IOException e){
                            e.printStackTrace();
                            Log.e("networkException",new String("写入错误"));
                        }
                    }
                }
            }
        }.start();
    }

    /**
     * 确定发送数据是否成功
     * @return
     */
    public boolean getResult(){
        Log.d("asd","75757");
        Log.d("asd",result);
        if(result.equals(200)){
            return true;
        }
        return false;
    }

    /**
     *
     * @param url  服务器对应的ip地址
     * @param method 请求的方法，只能是GET
     * @return
     */
    public JSONObject getData(final String url,final String method){
        JSONObject object=null;
        new Thread(){
            @Override
            public void run(){
                getURLConnection(url,method);
                getInputStream(urlConnection);
            }
        }.start();
        return object;
    }

    /**
     * 根据传入的url以及方法获取网络连接的输出流
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
     * 根据传入的connection获取对应的输出流
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
