package com.cartoon.tinytips.HomePage.Details.Revamp;

/**
 * Created by cartoon on 2018/2/14.
 * HomePageDetailsRevamp的公共接口
 * View类接入IHomePageDetailsRevamp.view，Presenter类接入IHomePageDetailsRevamp.Presenter
 * 在内部接口定义方法，在接入类中重写方法实现功能
 */

interface IHomePageDetailsRevamp {
    interface View{
        void handleClickBack();//handleClick开头的方法为点击事件的处理函数
        void handleClickSave();
        void handleClickMenu();
        void handleClickRevampTitle();
        void handleClickRevampClassify();
        void handleClickSelectPhoto();
        void showToast(String code);
    }
    interface Presenter{

    }
}
