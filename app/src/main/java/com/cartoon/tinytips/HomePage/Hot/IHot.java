package com.cartoon.tinytips.HomePage.Hot;

import com.cartoon.tinytips.ValueCallBack;

import java.util.List;

interface IHot {
    interface View{
        void initData(List<HotItem> hotItems);
    }

    interface Presenter{
        void initData();
    }

    interface Model{
        void initData(ValueCallBack<List<HotItem>> callBack);
    }
}
