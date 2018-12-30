package com.cartoon.tinytips.Personal.PersonalHomepage;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.util.Adapters.Personal.PersonalHomepage.DynamicState;

import java.io.File;
import java.util.List;

interface IPersonalHomepage {
    interface View{
        void initDynamicState(List<DynamicState> list);    //初始化动态列表
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
        void getDynamicStateList(ValueCallBack<List<DynamicState>> callBack);
    }
}
