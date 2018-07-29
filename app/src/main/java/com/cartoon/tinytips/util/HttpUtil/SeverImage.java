package com.cartoon.tinytips.util.HttpUtil;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import static android.provider.Telephony.Mms.Part.CHARSET;

/**
 * 功能：与服务器进行联动，传输图片
 * 完成人：cartoon
 * 完成时间：18.7.24
 */

public class SeverImage {
    private static String getImagePath(Uri uri, String selection, Context context){
        //根据形参给出的Uri获取图片在本地的真实路径
        String path=null;
        Cursor cursor=context.getContentResolver().query(uri,null,selection,null,null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
    public static void uploadFile(Uri uri, String selection, Context context,final String requestUrl){
        //将本地图片上传到服务器
        final File file=new File(getImagePath(uri, selection, context));
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String bound= UUID.randomUUID().toString();
                    String prefix="--";
                    String line="\r\n";
                    String content_type="multipart/form-data";
                    URL url=new URL(requestUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(8000);
                    conn.setRequestMethod("POST"); // 请求方式
                    conn.setRequestProperty("Charset", CHARSET); // 设置编码
                    conn.setRequestProperty("connection", "keep-alive");
                    conn.setRequestProperty("Content-Type", content_type + ";boundary="
                            + bound);
                    if(file!=null){
                        OutputStream outputStream=conn.getOutputStream();
                        DataOutputStream dataOutputStream=new DataOutputStream(outputStream);
                        StringBuffer buffer=new StringBuffer();
                        buffer.append(prefix).append(bound).append(line);
                        buffer.append("Content-Disposition: form-data; name=\"img\"; filename=\""
                                + file.getName() + "\"" + line);
                        buffer.append("Content-Type: application/octet-stream; charset="
                                + CHARSET + line);
                        buffer.append(line);
                        dataOutputStream.write(buffer.toString().getBytes());
                        InputStream inputStream=new FileInputStream(file);
                        byte[] bytes=new byte[1024];
                        int len=0;
                        while((len=inputStream.read(bytes))!=-1){
                            dataOutputStream.write(bytes,0,len);
                        }
                        inputStream.close();
                        dataOutputStream.write(line.getBytes());
                        byte[] end_data=(prefix+bound+prefix+line).getBytes();
                        dataOutputStream.write(end_data);
                        dataOutputStream.flush();
                        if(conn.getResponseCode()==200){
                            buffer = new StringBuffer();
                            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                            line = null;
                            while ((line = reader.readLine()) != null) {
                                buffer.append(line).append("\n");
                            }
                            reader.close();
                            reader = null;
                        }
                    }
                }catch (MalformedURLException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public static Bitmap downloadFile(){
        //下载服务器中的图片到本地
        Bitmap bitmap=null;
        String requestUrl="http://192.168.31.29:8080/ServletTest/DownloadImage";
        try{
            URL url=new URL(requestUrl);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(1000 * 6);
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.connect();
            int resultCode=conn.getResponseCode();
            if(HttpURLConnection.HTTP_OK==resultCode){
                InputStream is=conn.getInputStream();
                bitmap= BitmapFactory.decodeStream(is);
                is.close();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return bitmap;
    }
}
