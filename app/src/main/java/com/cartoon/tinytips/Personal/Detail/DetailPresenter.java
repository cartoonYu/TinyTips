package com.cartoon.tinytips.Personal.Detail;

import com.cartoon.tinytips.BaseActivityPresenter;

class DetailPresenter extends BaseActivityPresenter<Detail> implements IDetail.Presenter{

    private IDetail.View view;

    public DetailPresenter(IDetail.View view){
        this.view=view;
    }
    @Override
    protected void deleteView(){
        view=null;
    }
}
