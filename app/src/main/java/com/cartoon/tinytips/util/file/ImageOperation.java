package com.cartoon.tinytips.util.file;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import com.cartoon.tinytips.Personal.Personal;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.TinyTipsApplication;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * description
 * 图片格式的互相转换
 */

public class ImageOperation {

    private static volatile ImageOperation imageOperation;

    public static ImageOperation getImageOperation(){
        if(JudgeEmpty.isEmpty(imageOperation)){
            synchronized (ImageOperation.class){
                if(JudgeEmpty.isEmpty(imageOperation)){
                    imageOperation=new ImageOperation();
                }
            }
        }
        return imageOperation;
    }

    public File defaultImage(int drawable,String defaultName){
        Bitmap bitmap=BitmapFactory.decodeResource(TinyTipsApplication.getContext().getResources(),drawable);
        File result=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+defaultName);
        try {
            BufferedOutputStream outputStream=new BufferedOutputStream(new FileOutputStream(result));
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private ImageOperation(){
    }
}
