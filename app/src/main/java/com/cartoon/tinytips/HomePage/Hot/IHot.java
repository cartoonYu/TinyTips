package com.cartoon.tinytips.HomePage.Hot;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.view.StatSocial;

import java.util.List;

public interface IHot {

    interface View{
        void initData();
        void initData(List<StatSocial> hotItems);
    }

    interface Presenter{
        void initData();
    }

    interface Model{
        void initData(ValueCallBack<List<StatSocial>> callBack);
    }
}
