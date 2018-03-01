package com.cartoon.tinytips.Personal.Profile.RevampResume;

/**
 * Created by cartoon on 2018/2/22.
 * PersonalProfileResume的公共接口
 * View类接入IPersonalProfileResume.view，Presenter类接入IPersonalProfileResume.Presenter
 * 在内部接口定义方法，在接入类中重写方法实现功能
 */

interface IPersonalProfileResume {
    interface View{
        void handleClickBack();      //handleClick开头的方法为点击事件的处理函数
        void handleClickSave();
    }
    interface Presenter{

    }
}