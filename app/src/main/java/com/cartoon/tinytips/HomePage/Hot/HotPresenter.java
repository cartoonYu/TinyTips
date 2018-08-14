package com.cartoon.tinytips.HomePage.Hot;

import com.cartoon.tinytips.BaseFragmentPresenter;

class HotPresenter extends BaseFragmentPresenter<Hot> implements IHot.Presenter{

    private IHot.View view;

    public HotPresenter(IHot.View view){
        this.view=view;
    }

    @Override
    protected void deleteView(){
        view=null;
    }
}
