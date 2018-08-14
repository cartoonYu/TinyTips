package com.cartoon.tinytips.HomePage.Favorite;

import com.cartoon.tinytips.BaseFragmentPresenter;

class FavoritePresenter extends BaseFragmentPresenter<Favorite> implements IFavorite.Presenter{

    private IFavorite.View view;

    public FavoritePresenter(IFavorite.View view){
        this.view=view;
    }

    @Override
    protected void deleteView(){
        view=null;
    }

}
