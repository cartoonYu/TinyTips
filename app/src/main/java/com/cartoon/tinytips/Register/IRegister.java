package com.cartoon.tinytips.Register;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.PersonalInformation;

interface IRegister {

    interface View{
        String getAccount();  //获取账号
        String getAuthCode();   //获取验证码
        String getPassword();  //获取密码
        void intentToMain();    //跳转到主页
    }

    interface Presenter{
        void handleRegister();    //处理注册事件
    }

    interface Model{
        void setAuthCode(String authCode);    //将验证码从presenter传递到model
        void verifyAuthCode(ValueCallBack<Boolean> callBack);    //验证验证码的真伪
        void setInformation(PersonalInformation information);   //将账号密码从presenter传递到model
        void verifyInformation(ValueCallBack<String> callBack);  //验证账号
    }
}
