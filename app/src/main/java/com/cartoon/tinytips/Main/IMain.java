package com.cartoon.tinytips.Main;

/**
 * Created by cartoon on 2018/2/28.
 * * Created by cartoon on 2018/2/4.
 * Main的公共接口
 * View类接入IMain.view，Presenter类接入IMain.Presenter
 * 在内部接口定义方法，在接入类中重写方法实现功能
 */

interface IMain {
    interface View{
        void switchFragment(int flag);
    }
    interface Presenter{

    }
}
