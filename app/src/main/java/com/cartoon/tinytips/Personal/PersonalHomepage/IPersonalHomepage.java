package com.cartoon.tinytips.Personal.PersonalHomepage;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.bean.view.StatSocial;

import java.io.File;
import java.util.List;

public interface IPersonalHomepage {

    interface View{
        void initDynamicState();
        void initDynamicState(List<StatSocial> list);    //初始化动态列表
        void setHeadPro(File headPro);   //设置头像
        void setNickName(String name);     //设置昵称
        void setSchool(String school);    //设置学习
        void setMajor(String major);      //设置专业
        void setInterest(List<String> interest);    //设置学历

    }

    interface Presenter{
        void initInformation(Information information);   //初始化个人信息
        void initDynamicState();
    }

    interface Model{
        void getInformation(Information information,ValueCallBack<Information> callBack);   //获取个人信息
        void getDynamicStateList(ValueCallBack<List<StatSocial>> callBack);
        void clickLike(StatSocial social,ValueCallBack<String> callBack);
        void clickCollect(StatSocial social,ValueCallBack<String> callBack);
    }
}
