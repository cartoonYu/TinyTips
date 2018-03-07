package com.cartoon.tinytips.Personal.Profile.RevampSchool;

import com.cartoon.tinytips.ValueCallBack;

/**
 * Created by cartoon on 2018/2/22.
 * PersonalProfileSchool的公共接口
 * View类接入IPersonalProfileSchool.view，Presenter类接入IPersonalProfileSchool.Presenter
 * 在内部接口定义方法，在接入类中重写方法实现功能
 */

interface IPersonalProfileSchool {
    interface View{
        void handleClickBack();      //handleClick开头的方法为点击事件的处理函数
        void handleClickSave();
        String getNickName();
        String getNewSchool();
        void showToast(String code);
    }
    interface Presenter{
        void revampSchool();
    }
    interface Model{
        void setNickName(String nickName);
        void setNewSchool(String newSchool);
        void revampSchool(ValueCallBack<String> callBack);
    }
}
