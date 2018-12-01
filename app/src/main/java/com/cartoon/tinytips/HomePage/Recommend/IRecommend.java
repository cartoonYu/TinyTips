package com.cartoon.tinytips.HomePage.Recommend;

import com.cartoon.tinytips.ValueCallBack;

import java.util.List;

public interface IRecommend {

    interface View{
        void initData(List<RecommendItem> recommendItems);
    }
    interface Presenter{
        void initData();
    }

    interface Model{
        void initData(ValueCallBack<List<RecommendItem>> callBack);
        void addFavorites(RecommendItem item,String operate,ValueCallBack<String> callBack);
    }
}
