package com.cartoon.tinytips.Personal.PersonalHomepage;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.bean.Information;

import java.io.File;
import java.util.List;

interface IPersonalHomepage {
    interface View{
        void initDynamicState();    //初始化动态列表
        void setHeadPro(File headPro);   //设置头像
        void setNickName(String name);     //设置昵称

        void setSchool(String school);    //设置学习
        void setMajor(String major);      //设置专业
        void setInterest(List<String> interest);    //设置学历

        Information getInformation();

    }

    interface Presenter{
        void initData();   //初始化数据
    }

    interface Model{
        void getHomepageInformation(ValueCallBack<Note> callBack);   //获取个人笔记
        void getHomepagePersonalInformation(ValueCallBack<Information> callBack);   //获取个人信息
        void setInformation(Information information);
        void getNoteList(ValueCallBack<List<Note>> valueCallBack);
    }
}
