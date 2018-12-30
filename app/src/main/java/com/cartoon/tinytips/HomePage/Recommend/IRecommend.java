package com.cartoon.tinytips.HomePage.Recommend;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.bean.table.Note;
import com.cartoon.tinytips.bean.view.StatSocial;

import java.util.List;

public interface IRecommend {

    interface View{
        void initData();
        void initData(List<StatSocial> recommendItems);
    }
    interface Presenter{
        void initData();
    }

    interface Model{
        void initData(ValueCallBack<List<StatSocial>> callBack);
        void clickLike(StatSocial social,ValueCallBack<String> callBack);
        void clickCollect(StatSocial social,ValueCallBack<String> callBack);
        void clickUser(StatSocial social,ValueCallBack<Information> callBack);
    }
}
