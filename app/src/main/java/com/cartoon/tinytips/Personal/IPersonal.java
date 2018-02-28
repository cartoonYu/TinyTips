package com.cartoon.tinytips.Personal;

/**
 * Created by cartoon on 2018/2/4.
 * Personal的公共接口
 * View类接入IPersonal.view，Presenter类接入IPersonal.Presenter
 * 在内部接口定义方法，在接入类中重写方法实现功能
 */

interface IPersonal {
    interface View{
        void handleClickProfile();//handleClick开头的方法为点击事件的处理函数
        void handleClickSecurity();
    }
    interface Presenter{

    }
}
