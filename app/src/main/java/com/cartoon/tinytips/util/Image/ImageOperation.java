package com.cartoon.tinytips.util.Image;

import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import com.cartoon.tinytips.util.Coder;
import com.cartoon.tinytips.util.JudgeEmpty;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class ImageOperation {

    private static volatile ImageOperation operation;

    private Coder coder;

    public static ImageOperation getOperation(){
        if(JudgeEmpty.isEmpty(operation)){
            synchronized (ImageOperation.class){
                if(JudgeEmpty.isEmpty(operation)){
                    operation=new ImageOperation();
                }
            }
        }
        return operation;
    }

    /**
     * 功能
     * 转换传入的文件成经过Base64处理的String
     *
     * 使用方法
     * 1.传入图片
     * 2.通过返回值得到经过Base64处理的String
     *
     * 注意
     *
     * @param file
     * @return
     */
    public String transFileToString(File file){
        byte[] bytes=null;
        int length=0;
        try {
            FileInputStream stream=new FileInputStream(file);
            bytes=new byte[stream.available()];
            length=stream.read(bytes);
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return coder.encode(bytes);
    }

    /**
     * 功能
     * 根据传入字符串转换成图片
     *
     * 使用方法
     * 1.传入经过Base64处理的图片字符串以及名字
     * 2.通过返回值得到图片
     *
     * 注意
     * 1.传入的字符串必须经过Base64处理
     *
     * @param file
     * @param imageName
     * @return
     */
    public File transStringToFile(String file,String imageName){
        byte[] bytes=coder.decode(file);
        File result=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+imageName);
        try {
            FileOutputStream stream=new FileOutputStream(result);
            stream.write(bytes,0,bytes.length);
            stream.flush();
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private ImageOperation(){
        coder=Coder.getCoder();
    }
}
