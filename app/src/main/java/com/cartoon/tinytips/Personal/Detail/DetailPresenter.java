package com.cartoon.tinytips.Personal.Detail;

import com.cartoon.tinytips.BaseActivityPresenter;

class DetailPresenter extends BaseActivityPresenter<Detail> implements IDetail.Presenter{

    private IDetail.View view;

    private IDetail.Model model;


    public DetailPresenter(IDetail.View view){
        this.view=view;
        this.model = new DetailModel();
    }
    @Override
    protected void deleteView(){
        view = null;
        model = null;
    }
}
