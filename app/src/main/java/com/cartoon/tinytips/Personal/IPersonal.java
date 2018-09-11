package com.cartoon.tinytips.Personal;

import android.graphics.Bitmap;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.PersonalInformation;

interface IPersonal {

    interface View{
        void setHeadPro(Bitmap headPro);   //设置头像
    }

    interface Presenter{
        void initData();    //初始化页面数据
    }

    interface Model{
        void getPersonalInformation(ValueCallBack<PersonalInformation> callBack);   //获取个人信息
    }

}
