package com.cartoon.tinytips.HomePage.Favorite;

import com.cartoon.tinytips.BaseFragmentPresenter;

class FavoritePresenter extends BaseFragmentPresenter<Favorite> implements IFavorite.Presenter{

    private IFavorite.View view;

    private IFavorite.Model model;

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
