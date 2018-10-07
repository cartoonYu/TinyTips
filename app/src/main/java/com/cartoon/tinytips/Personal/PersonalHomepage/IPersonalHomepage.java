package com.cartoon.tinytips.Personal.PersonalHomepage;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.bean.Information;

interface IPersonalHomepage {
    interface View{
        void initDynamicState();    //初始化动态列表
    }

    interface Presenter{
        void initData();   //初始化数据
    }

    interface Model{
        void getHomepageInformation(ValueCallBack<Note> callBack);   //获取个人笔记
        void getHomepagePersonalInformation(ValueCallBack<Information> callBack);   //获取个人信息
    }
}
