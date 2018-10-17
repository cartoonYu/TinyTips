package com.cartoon.tinytips.Personal.Setting.Security;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;

public class SecurityModel implements ISecurity.Model {
    private String authCode;   //验证码

    private Information information;


    @Override
    public void getPersonalInformation(ValueCallBack<Information> callBack) {

    }

    @Override
    public void verifyAuthCode(ValueCallBack<Boolean> callBack) {

    }
}
