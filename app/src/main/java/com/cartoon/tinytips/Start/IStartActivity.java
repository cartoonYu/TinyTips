package com.cartoon.tinytips.Start;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Information;

interface IStartActivity {

    interface View{
        void intentToMain(Information information);    //跳转到主页
        void intentToLogin();    //跳转到登录页
    }

    interface Presenter{
        void getInformation();
    }

    interface Model{
        void getPersonalInformation(ValueCallBack<Information> callBack);
    }
}
