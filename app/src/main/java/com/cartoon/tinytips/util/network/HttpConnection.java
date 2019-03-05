package com.cartoon.tinytips.util.network;

import android.util.Log;

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

/**
 * @author cartoon
 * @version 1.3
 *
 * description
 * 1.使用http进行网络传输数据
 *
 * how to use
 * 1.通过调用sendJSONArray发送jsonArray到服务器
 * 2.通过调用sendJSONObject发送json文件到服务器
 *
 */

public class HttpConnection implements IHttpConnection {

    private HttpURLConnection urlConnection;

    private int TIME_OUT;     //连接超时时间

    private OutputStream outputStream;

    private InputStream inputStream;

    private String result;

    @Override
    public void sendJSONObject(final String url, final String method, final JSONObject object,final IDataCallBack<String> callBack) {
        Log.d("network",url);
        Log.d("network",method);
        Log.d("network",object.toString());
        new Thread(new Runnable() {
            @Override
            public void run() {
                StringBuffer buffer=new StringBuffer();
                getURLConnection(url,method);
                if(JudgeEmpty.isNotEmpty(urlConnection)) {
                    getOutputStream(urlConnection);
                    if(JudgeEmpty.isNotEmpty(outputStream)){
                        try {
                            outputStream.write(object.toString().getBytes());
                            getInputStream(urlConnection);
                            if(JudgeEmpty.isNotEmpty(inputStream)){
                                BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
                                String line;
                                while((line=reader.readLine())!=null){
                                    buffer.append(line);
                                }
                                inputStream.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e("networkException",new String("写入错误"));
                        }
                    }
                }
                result=buffer.toString();
                urlConnection.disconnect();
                Log.d("network",result);
                if(result.equals("300")||result.equals("400")){
                    callBack.onFail(result);
                }
                else {
                    callBack.onSuccess(result);
                }
            }
        }).start();
    }

    @Override
    public void sendJSONArray(final String url, final String method, final JSONArray array,final IDataCallBack<String> callBack) {
        Log.d("network",url);
        Log.d("network",method);
        Log.d("network",array.toString());
        new Thread(new Runnable() {
            @Override
            public void run() {
                StringBuffer buffer=new StringBuffer();
                getURLConnection(url,method);
                if(JudgeEmpty.isNotEmpty(urlConnection)) {
                    getOutputStream(urlConnection);
                    if(JudgeEmpty.isNotEmpty(outputStream)){
                        try {
                            outputStream.write(array.toString().getBytes());
                            getInputStream(urlConnection);
                            if(JudgeEmpty.isNotEmpty(inputStream)){
                                BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
                                String line;
                                while((line=reader.readLine())!=null){
                                    buffer.append(line);
                                }
                                inputStream.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e("networkException",new String("写入错误"));
                        }
                    }
                }
                result=buffer.toString();
                urlConnection.disconnect();
                Log.d("network",result);
                if(result.equals("300")||result.equals("400")){
                    callBack.onFail(result);
                }
                else {
                    callBack.onSuccess(result);
                }
            }
        }).start();
    }

    /**
     * 功能
     * 根据传入的url以及方法获取网络连接的输出流
     *
     * @param url
     * @param method
     * @return
     */
    private void getURLConnection(String url, String method){
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

    public HttpConnection() {
        TIME_OUT=2000;
    }
}
