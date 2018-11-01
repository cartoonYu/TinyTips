package com.cartoon.tinytips.HomePage.Favorite;

import com.cartoon.tinytips.ValueCallBack;

import java.util.List;

interface IFavorite {
    interface View{
        void initData(List<FavoriteItem> items);
    }

    interface Presenter{
        void initData();
    }

    interface Model{
        void initData(ValueCallBack<List<FavoriteItem>> callBack);
    }
}
