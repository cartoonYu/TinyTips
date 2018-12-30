package com.cartoon.tinytips.Main;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Information;

public interface IMain {
    interface View{

    }

    interface Presenter{

    }

    interface Model{
        void getPersonalInformation(ValueCallBack<Information> callBack);
    }
}
