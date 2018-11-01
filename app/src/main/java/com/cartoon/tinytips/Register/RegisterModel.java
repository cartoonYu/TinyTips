package com.cartoon.tinytips.Register;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;

class RegisterModel implements IRegister.Model {

    private String authCode;   //验证码

    private Information information;

    private String result;


    @Override
    public void setAuthCode(String authCode){
        this.authCode=authCode;
    }

    @Override
    public void verifyAuthCode(ValueCallBack<Boolean> callBack){
        callBack.onSuccess(true);
    }

    @Override
    public void setInformation(Information information){
        this.information=information;
    }

    @Override
    public void verifyInformation(ValueCallBack<String> callBack){
       callBack.onSuccess(new String("注册成功"));
    }
}
