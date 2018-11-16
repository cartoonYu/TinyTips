package com.cartoon.tinytips.Personal.Setting.Security;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;

interface ISecurity {
    interface View{
        String getPhoneNumber();
        String getAuthCode();   //获取验证码
        String getPassword();  //获取密码
        String getConfirmPassword(); //二次获取密码
    }

    interface Presenter{
        void handleChangePassword();  //处理密码修改事件
    }

    interface Model{
        void getPersonalInformation(ValueCallBack<Information> callBack);   //获取个人信息
        void verifyAuthCode(ValueCallBack<Boolean> callBack);
    }
}
