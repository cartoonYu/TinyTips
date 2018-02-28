package com.cartoon.tinytips.Personal.Profile;

/**
 * Created by cartoon on 2018/2/6.
 * PersonalProfile的公共接口
 * View类接入IProfile.view，Presenter类接入IProfile.Presenter
 * 在内部接口定义方法，在接入类中重写方法实现功能
 */

interface IProfile {
    interface View{
        void handleClickBack();     //handleClick开头的方法为点击事件的处理函数
        void handleClickHeadPortrait();
        void handleClickSex();
        void handleClickSchool();
        void handleClickResume();
        void handleClickSignature();
    }
    interface Presenter{

    }
}
