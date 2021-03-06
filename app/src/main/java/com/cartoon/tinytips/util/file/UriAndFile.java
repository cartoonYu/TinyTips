package com.cartoon.tinytips.util.file;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.TinyTipsApplication;

import java.io.File;

/**
 * @author cartoon
 * @version 1.0
 *
 *
 *description
 * between file and Uri transform
 *
 * how to use
 *
 * notice
 * 1.this uri is come from Matisse or android itself
 */

public class UriAndFile {

    private static volatile UriAndFile uriAndFile;

    public static UriAndFile getInstance(){
        if(JudgeEmpty.isEmpty(uriAndFile)){
            synchronized (UriAndFile.class){
                if(JudgeEmpty.isEmpty(uriAndFile)){
                    uriAndFile=new UriAndFile();
                }
            }
        }
        return uriAndFile;
    }

    /**
     * 功能
     * 将Uri转换成File存储
     *
     * 使用方法
     *
     * 注意
     *
     * @param source
     * @return
     */
    public File uriToFile(Uri source){
        String path=null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = TinyTipsApplication.getContext().getContentResolver().query(source, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            path = cursor.getString(columnIndex);
        }
        cursor.close();
        return new File(path);
    }

    /**
     * 功能
     * 将File转换成Uri存储
     *
     * 使用方法
     *
     * 注意
     *
     * @param file
     * @return
     */
    public Uri fileToUri(File file){
        Uri uri=Uri.fromFile(file);
        return uri;
    }

    private UriAndFile(){
    }
}
