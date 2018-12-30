package com.cartoon.tinytips.HomePage.Favorite;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Information;

import java.util.List;

public interface IFavorite {
    interface View{
        void initData(List<FavoriteItem> items);
    }

    interface Presenter{
        void initData();
    }

    interface Model{
        void initData(ValueCallBack<List<FavoriteItem>> callBack);
        void onClickItem(FavoriteItem item,String type,ValueCallBack<String> callBack);
        void clickUser(Information information, ValueCallBack<Information> callBack);
    }
}
