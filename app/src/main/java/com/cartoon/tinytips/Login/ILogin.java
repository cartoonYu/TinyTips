package com.cartoon.tinytips.Login;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;

interface ILogin {

    interface View{
        void intentToMain();
    }

    interface Presenter{
        void checkInformation(String account,String password);
    }

    interface Model{
        void checkInformation(Information information,ValueCallBack<String> callBack);
    }
}
