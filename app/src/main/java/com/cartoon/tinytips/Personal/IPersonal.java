package com.cartoon.tinytips.Personal;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Information;

import java.io.File;

interface IPersonal {

    interface View{
        void setHeadPro(File headPro);   //设置头像
        void setNotes(Integer notes);       //设置笔记数据
        void setAttentions(Integer attentions);  //设置关注数据
        void setFans(Integer fans);   //设置粉丝数据
        void setNickName(String name);  //设置用户昵称
    }

    interface Presenter{
        void initData();    //初始化页面数据
    }

    interface Model{
        void getPersonalInformation(ValueCallBack<Information> callBack);   //获取个人信息
        void getNoteNum(ValueCallBack<Integer> callBack);
    }

}
