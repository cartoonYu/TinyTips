package com.cartoon.tinytips.Personal.Setting.Management;

interface IManagement {

    interface View{
        void revampStatusBar();   //沉浸式状态栏
        void initToolbar();      //初始化toolbar
        void initAccount();      //初始化账号信息
        void intentToSetting();    //跳转回设置页
    }

    interface Presenter{

    }

}
