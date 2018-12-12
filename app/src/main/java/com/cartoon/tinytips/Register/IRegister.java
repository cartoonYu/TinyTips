package com.cartoon.tinytips.Register;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;

interface IRegister {

    interface View{
        String getAccount();  //获取账号
        String getAuthCode();   //获取验证码
        String getPassword();  //获取密码
        String nickName();    //获取昵称
        void intentToMain();    //跳转到主页
    }

    interface Presenter{
        void handleRegister();    //处理注册按钮点击事件
    }

    interface Model{
        void register(Information information,ValueCallBack<String> callBack);
    }
}
