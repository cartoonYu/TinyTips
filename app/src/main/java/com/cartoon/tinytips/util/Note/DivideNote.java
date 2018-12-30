package com.cartoon.tinytips.util.Note;

import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.cartoon.tinytips.bean.table.Note;
import com.cartoon.tinytips.util.file.FileOperation;
import com.cartoon.tinytips.util.file.UriAndFile;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.TinyTipsApplication;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cartoon
 * @version 1.0
 *
 * description
 * 切割合并笔记中的图片与文字
 *
 * how to use
 *
 * notice
 *
 */

public class DivideNote {

    private static volatile DivideNote divideNote;

    private FileOperation fileOperation;

    public static DivideNote getDivideNote(){
        if(JudgeEmpty.isEmpty(divideNote)){
            synchronized (DivideNote.class){
                if(JudgeEmpty.isEmpty(divideNote)){
                    divideNote=new DivideNote();
                }
            }
        }
        return divideNote;
    }

    /**
     * 功能
     * 将传入的note中的文字以及图片混编成字符串显示在editText
     *
     * 使用方法
     * 1.传入笔记对象
     * 2.通过返回值得到图文混编的字符串集合
     *
     * 注意
     *
     * @param note
     * @return
     */
    public List<SpannableString> transNoteToString(Note note){
        if(JudgeEmpty.isEmpty(note)){
            return null;
        }
        List<Uri> uris=new ArrayList<>();
        if(JudgeEmpty.isNotEmpty(note.getPhotoDetails())&&!note.getPhotoDetails().isEmpty()){
            for(File file:note.getPhotoDetails()){
                uris.add(UriAndFile.getInstance().fileToUri(file));
            }
        }
        List<String> words=note.getWordDetails();
        List<SpannableString> result=new ArrayList<>();
        int photoFlag=0;
        if(JudgeEmpty.isNotEmpty(words)){
            for(String word:words){
                SpannableString temp=null;
                if(word.equals("￥￥")){
                    if(photoFlag<uris.size()){
                        Uri photo=uris.get(photoFlag++);
                        temp=new SpannableString("&"+photo.toString()+"&");
                        ImageSpan imageSpan=new ImageSpan(TinyTipsApplication.getContext(),photo);
                        temp.setSpan(imageSpan,0,photo.toString().length()+2,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        result.add(temp);
                    }
                }
                else {
                    temp=new SpannableString(word);
                    result.add(temp);
                }
            }
        }

        return result;
    }

    /**
     * 功能
     * 将图片文字混合的字符串拆分成可存储对象
     *
     * 使用方法
     * 1.传入图片文字混合的字符串
     * @param source
     * @return
     */
    public Note transStringToNote(String source){
        if(JudgeEmpty.isEmpty(source)){
            return null;
        }
        String[] str=source.split("&");
        List<String> word=new ArrayList<>();
        List<String> photo=new ArrayList<>();

        for(int i=0;i<str.length;i++){
            if(str[i].startsWith("content")){
                word.add("￥￥");
                photo.add(str[i]);
            }
            else {
                word.add(str[i]);
            }
        }
        List<File> files=new ArrayList<>();
        for(String s:photo){
            Uri uri=Uri.parse(s);
            files.add(UriAndFile.getInstance().uriToFile(uri));
        }
        Note note=new Note();
        note.setPhotoDetails(files);
        note.setWordDetails(word);
        return note;
    }

    private DivideNote(){
    }

}
