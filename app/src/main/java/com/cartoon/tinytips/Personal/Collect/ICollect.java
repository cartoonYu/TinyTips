package com.cartoon.tinytips.Personal.Collect;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.util.Adapters.Personal.Collect.Collect;

import java.util.List;

interface ICollect {
    interface View{
        void initCollect(List<Collect> collects);     //初始化收藏列表
    }

    interface Presenter{
        void initData();
    }

    interface Model{
        void initData(ValueCallBack<List<Collect>> callBack);
    }
}
