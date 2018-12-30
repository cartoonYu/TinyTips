package com.cartoon.tinytips.Personal.Collect;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.bean.view.StatSocial;

import java.util.List;

public interface ICollect {

    interface View{
        void initCollect();
        void initCollect(List<StatSocial> collects);     //初始化收藏列表
    }

    interface Presenter{
        void initData();
    }

    interface Model{
        void initData(ValueCallBack<List<StatSocial>> callBack);
        void clickUser(StatSocial social, ValueCallBack<Information> callBack);
        void clickLike(StatSocial social,ValueCallBack<String> callBack);
        void clickCollect(StatSocial social,ValueCallBack<String> callBack);
    }

}
