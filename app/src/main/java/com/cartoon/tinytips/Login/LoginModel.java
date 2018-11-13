package com.cartoon.tinytips.Login;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;

import java.util.List;

public class LoginModel implements ILogin.Model {

    private List<Information> list;

    private Information info;

    @Override
    public void getPersonalInformation(ValueCallBack<Information> callBack) {

    }
}
