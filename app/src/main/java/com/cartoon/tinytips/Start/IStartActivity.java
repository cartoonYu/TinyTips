package com.cartoon.tinytips.Start;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;

interface IStartActivity {
    interface View{
        void intentToMain();    //跳转到主页
        void finishActivity();
    }

    interface Presenter{

    }
    interface Model{
        void getPersonalInformation(ValueCallBack<Information> callBack);
    }
}
