package com.cartoon.tinytips.Personal.Profile;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.data.PersonalInformation;

/**
 * Created by cartoon on 2018/2/6.
 * PersonalProfile的公共接口
 * View类接入IProfile.view，Presenter类接入IProfile.Presenter
 * 在内部接口定义方法，在接入类中重写方法实现功能
 */

interface IProfile {
    interface View{
        void showToast(String code);
        void initData(PersonalInformation information);
    }
    interface Presenter{
        void initData(String nickName);
    }
    interface Model{
        void setNickName(String nickName);
        void initData(ValueCallBack<PersonalInformation> callBack);
    }
}
