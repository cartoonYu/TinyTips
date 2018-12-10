package com.cartoon.tinytips.HomePage.Favorite;

import com.cartoon.tinytips.BaseFragmentPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.util.ShowToast;

import java.util.List;

class FavoritePresenter extends BaseFragmentPresenter<Favorite> implements IFavorite.Presenter{

    private IFavorite.View view;

    private IFavorite.Model model;

    @Override
    public void initData(){
        model.initData(new ValueCallBack<List<FavoriteItem>>() {
            @Override
            public void onSuccess(List<FavoriteItem> items) {
                view.initData(items);
            }

            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
            }
        });
    }


    public FavoritePresenter(IFavorite.View view){
        this.view=view;
        this.model = new FavoriteModel();
    }

    @Override
    protected void deleteView(){
        view=null;
        model = null;
    }

}
