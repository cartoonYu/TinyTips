package com.cartoon.tinytips.Personal.Collect;

interface ICollect {
    interface View{
        void revampStatusBar();    //沉浸式状态栏
        void initToolbar();     //初始化toolbar
        void initCollect();     //初始化收藏列表
        void intentMain();     //返回上一级
    }
    interface Presenter{

    }
}
