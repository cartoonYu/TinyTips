package com.cartoon.tinytips.Login;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;

interface ILogin {

    interface View{

    }

    interface Presenter{

    }

    interface Model{
        void getPersonalInformation(ValueCallBack<Information> callBack);
    }
}
