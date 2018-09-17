package com.cartoon.tinytips.util.network;

import android.util.Log;

import com.cartoon.tinytips.util.JudgeEmpty;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpConnection implements Runnable{

    private volatile static HttpConnection httpConnection;

    private String url;

    private String method;

    private HttpURLConnection urlConnection;

    private int TIME_OUT;     //连接超时时间

    private OutputStream outputStream;

    private InputStream inputStream;

    private JSONObject object;   //服务器返回数据

    private StringBuffer result;

    private HttpConnection(){
        result=new StringBuffer();
    }

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
     * 客户端数据发送到服务器端
     * @param url 服务器对应的ip地址
     * @param method 请求的方法，只能是POST
     * @param object 需要发送到服务器的数据
     */
    public void sendData(String url,String method, JSONObject object){
        this.url=url;
        this.method=method;
        this.object=object;
    }

    @Override
    public void run(){
        getURLConnection(url,method);
        Log.d("asd","asd");
        if(JudgeEmpty.isNotEmpty(urlConnection)) {
            getOutputStream(urlConnection);
            if (JudgeEmpty.isNotEmpty(outputStream)) {
                try{
                    String temp=object.toString();
                    outputStream.write(temp.getBytes());
                    outputStream.close();
                    getInputStream(urlConnection);
                    BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    while((line=reader.readLine())!=null){
                        Log.d("asd",line);
                        result.append(line);
                    }
                    Log.d("asd",result.toString());
                }catch (IOException e){
                    e.printStackTrace();
                    Log.e("networkException",new String("写入错误"));
                }
            }
        }

    }

    /**
     * 将服务器返回的结果转换成JSON文件返回
     * @return
     */
    public JSONObject getResult(){
        Log.d("asd",result.toString());
        try {
            return new JSONObject(result.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
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
