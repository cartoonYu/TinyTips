package com.cartoon.tinytips.Message.FavoriteMessage;

import com.cartoon.tinytips.BaseActivityPresenter;

public class FavoriteMessagePresenter extends BaseActivityPresenter<FavoriteMessage> implements IFavoriteMessage.Presenter {
    private IFavoriteMessage.View view;
    public FavoriteMessagePresenter(IFavoriteMessage.View view){
        this.view=view;
    }
    @Override
    protected void deleteView(){
        view=null;
    }
}
