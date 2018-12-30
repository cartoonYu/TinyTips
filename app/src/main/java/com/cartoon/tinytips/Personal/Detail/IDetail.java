package com.cartoon.tinytips.Personal.Detail;

import android.net.Uri;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Information;

import java.io.File;
import java.util.List;

interface IDetail {
    interface View{
        void setHeadPro(File headPro);   //设置头像
        void setNickName(String name);     //设置昵称
        void setResume(String resume);   //设置简介
        void setSchool(String school);    //设置学习
        void setMajor(String major);      //设置专业
        void setDegree(String degree);    //设置学历
        void setRegisterData(String data);  //设置注册日期
        void setInterest(List<String> interests);  //设置兴趣
    }

    interface Presenter{
        void initData();    //初始化页面数据
        void revampHeadPro(Uri source);   //修改头像
        void resume(String resume);
    }

    interface Model{
        void getInformation(ValueCallBack<Information> callBack);   //获取个人信息
        void revampInformation(Information information,ValueCallBack<String> callBack);
    }
}
