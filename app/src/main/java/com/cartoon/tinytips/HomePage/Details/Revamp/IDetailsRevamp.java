package com.cartoon.tinytips.HomePage.Details.Revamp;

/**
 * Created by cartoon on 2018/2/14.
 * HomePageDetailsRevamp的公共接口
 * View类接入IHomePageDetailsRevamp.view，Presenter类接入IHomePageDetailsRevamp.Presenter
 * 在内部接口定义方法，在接入类中重写方法实现功能
 */

interface IDetailsRevamp {
    interface View{
        void showToast(String code);
    }
    interface Presenter{

    }
}
