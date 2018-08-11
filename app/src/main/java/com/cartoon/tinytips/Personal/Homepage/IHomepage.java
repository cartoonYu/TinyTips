package com.cartoon.tinytips.Personal.Homepage;

interface IHomepage {
    interface View{
        void revampStatusBar();   //修改状态栏颜色
        void initDynamicState();    //初始化动态列表
        void intentToMain();    //跳转到主页
    }
    interface Presenter{

    }
}
