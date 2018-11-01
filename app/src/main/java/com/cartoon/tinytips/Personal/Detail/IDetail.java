package com.cartoon.tinytips.Personal.Detail;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;

import java.io.File;

interface IDetail {
    interface View{
        Information getInformation();    //presenter获取information
        void setHeadPro(File headPro);   //设置头像
        void setNickName(String name);     //设置昵称
        void setResume(String resume);   //设置简介
        void setSchool(String school);    //设置学习
        void setMajor(String major);      //设置专业
        void setDegree(String degree);    //设置学历
        void setRegisterData(String data);  //设置注册日期
    }

    interface Presenter{
        void initData();    //初始化页面数据
    }

    interface Model{
        void getPersonalInformation(ValueCallBack<Information> callBack);   //获取个人信息
        void setInformation(Information information);
    }
}
