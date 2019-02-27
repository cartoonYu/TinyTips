package com.cartoon.tinytips.bean.table.Local;

import android.content.Context;
import android.content.SharedPreferences;

import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.TinyTipsApplication;
import com.cartoon.tinytips.util.file.FileOperation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LocalInformation implements ILocalInformation{

    private static volatile LocalInformation localInformation;

    private String fileName;

    private SharedPreferences preferences;

    private SharedPreferences.Editor editor;

    /**
     * 功能
     * 将用户个人信息写到本地数据文件
     * @param information
     * @return
     */
    @Override
    public boolean add(Information information){
        if(JudgeEmpty.isEmpty(information)){
            return false;
        }
        if(information.getId()!=0){
            editor.putLong("id",information.getId());
        }
        editor.putString("account",information.getAccount());
        editor.putString("password",information.getPassword());
        editor.putString("date",information.getDate());
        editor.putString("headPortraitResource",information.getHeadPortraitResource());
        editor.putString("headPortraitName",information.getHeadPortraitName());
        editor.putString("nickName",information.getNickName());
        editor.putBoolean("sex",information.isSex());
        if(JudgeEmpty.isNotEmpty(information.getInterest())){
            editor.putString("interest",changeListToString(information.getInterest(),"$"));
        }
        editor.putString("school",information.getSchool());
        editor.putString("major",information.getMajor());
        editor.putString("background",information.getBackground());
        editor.putString("resume",information.getResume());
        editor.apply();
        return true;
    }

    /**
     * 功能
     * 更新本地数据文件的个人信息
     * @param information
     * @return
     */
    @Override
    public boolean update(Information information){
        if(JudgeEmpty.isEmpty(information)){
            return false;
        }
        editor.putString("headPortraitName",information.getHeadPortraitName());
        editor.putString("headPortraitResource",information.getHeadPortraitResource());
        editor.putString("password",information.getPassword());
        editor.putString("account",information.getAccount());
        editor.putString("nickName",information.getNickName());
        editor.putBoolean("sex",information.isSex());
        if(JudgeEmpty.isNotEmpty(information.getInterest())){
            editor.putString("interest",changeListToString(information.getInterest(),"$"));
        }
        editor.putString("school",information.getSchool());
        editor.putString("major",information.getMajor());
        editor.putString("background",information.getBackground());
        editor.putString("resume",information.getResume());
        editor.apply();
        return true;
    }

    /**
     * 功能
     * 从本地数据文件中读取用户的个人信息
     *
     * @return
     */
    @Override
    public Information query(){
        Information information=new Information();
        information.setId(preferences.getLong("id",0));
        if(information.getId()==0){
            return null;
        }
        information.setAccount(preferences.getString("account",""));
        information.setPassword(preferences.getString("password",""));
        information.setDate(preferences.getString("date",""));
        information.setHeadPortraitResource(preferences.getString("headPortraitResource",""));
        information.setHeadPortraitName(preferences.getString("headPortraitName",""));
        information.setNickName(preferences.getString("nickName",""));
        information.setSex(preferences.getBoolean("sex",false));
        information.setInterest(changeStringToList(preferences.getString("interest",""),"$"));
        information.setSchool(preferences.getString("school",""));
        information.setMajor(preferences.getString("major",""));
        information.setBackground(preferences.getString("background",""));
        information.setResume(preferences.getString("resume",""));
        if(!information.getHeadPortraitResource().equals("")&&!information.getHeadPortraitName().equals("")){
            File headPortrait= FileOperation.getOperation().transStringToFile(information.getHeadPortraitResource(),information.getHeadPortraitName());
            information.setHeadPortrait(headPortrait);
        }
        return information;
    }


    /**
     * 功能
     * 将集合转换成带分隔符的字符串进行存储
     *
     * 使用方法
     * 1.传入集合以及分隔符
     * 2.通过返回值获取带分隔符的字符串
     *
     * @param list
     * @param regex
     * @return
     */
    private String changeListToString(List<String> list,String regex){
        StringBuilder builder=new StringBuilder();
        for(String str:list){
            builder.append(str).append(regex);
        }
        return builder.toString();
    }

    /**
     * 功能
     * 将带分隔符的字符串转换成集合
     *
     * 使用方法
     * 1.传入带分隔符的字符串以及分隔符
     * 2.通过返回值获取集合
     *
     * @param source
     * @return
     */
    private List<String> changeStringToList(String source,String regex){
        List<String> result=new ArrayList<>();
        if(JudgeEmpty.isEmpty(source)){
            return result;
        }
        int index=source.indexOf(regex);
        int length;
        while(index!=-1){
            result.add(source.substring(0,index));
            length=source.length();
            source=source.substring(index+1,length);
            index=source.indexOf(regex);
        }
        return result;
    }

    public static LocalInformation getLocalInformation(){
        if(JudgeEmpty.isEmpty(localInformation)){
            synchronized (LocalInformation.class){
                if(JudgeEmpty.isEmpty(localInformation)){
                    localInformation=new LocalInformation();
                }
            }
        }
        return localInformation;
    }

    private LocalInformation(){
        fileName=new String("information");
        editor=TinyTipsApplication.getContext().
                getSharedPreferences(fileName,Context.MODE_PRIVATE).edit();
        preferences=TinyTipsApplication.getContext().getSharedPreferences(fileName,Context.MODE_PRIVATE);
    }
}
