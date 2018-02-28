package com.cartoon.tinytips.Personal.Profile.RevampSignature;

/**
 * Created by cartoon on 2018/2/22.
 * PersonalProfileSignature的公共接口
 * View类接入IPersonalProfileSignature.view，Presenter类接入IPersonalProfileSignature.Presenter
 * 在内部接口定义方法，在接入类中重写方法实现功能
 */

interface IPersonalProfileSignature {
    interface View{
        void handleClickBack();      //handleClick开头的方法为点击事件的处理函数
        void handleClickSave();
    }
    interface Presenter{

    }
}
