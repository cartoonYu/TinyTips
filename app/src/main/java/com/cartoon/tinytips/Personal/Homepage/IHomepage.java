package com.cartoon.tinytips.Personal.Homepage;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Note;

interface IHomepage {
    interface View{
        void initDynamicState();    //初始化动态列表
    }

    interface Presenter{
        void initData();   //初始化数据
    }

    interface Model{
        void getHomepageInformation(ValueCallBack<Note> callBack);   //获取笔记
    }
}
