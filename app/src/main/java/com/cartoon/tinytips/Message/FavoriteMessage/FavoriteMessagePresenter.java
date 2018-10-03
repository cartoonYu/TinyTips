package com.cartoon.tinytips.Message.FavoriteMessage;

import com.cartoon.tinytips.BaseActivityPresenter;

public class FavoriteMessagePresenter extends BaseActivityPresenter<FavoriteMessage> implements IFavoriteMessage.Presenter {
    private IFavoriteMessage.View view;
    private IFavoriteMessage.Model model;

    public FavoriteMessagePresenter(IFavoriteMessage.View view){
        this.view=view;
        this.model = new FavoriteMessageModel();
    }
    @Override
    protected void deleteView(){
        view=null;
        model = null;
    }
}
