package com.cartoon.tinytips.Personal;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.data.PersonalInformation;

/**
 * Created by cartoon on 2018/2/4.
 * Personal的公共接口
 * View类接入IPersonal.view，Presenter类接入IPersonal.Presenter
 * 在内部接口定义方法，在接入类中重写方法实现功能
 */

interface IPersonal {
    interface View{
        void showToast(String code);
        void initHeadPortrait(String str);
        void initNickName(String str);
        void initSignature(String str);
    }
    interface Presenter{
        void initData();
    }
    interface Model{
        void getPersonalInformation(ValueCallBack<PersonalInformation> callBack);
    }
}
