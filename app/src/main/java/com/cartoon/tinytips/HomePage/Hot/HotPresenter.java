package com.cartoon.tinytips.HomePage.Hot;

import com.cartoon.tinytips.BaseFragmentPresenter;

class HotPresenter extends BaseFragmentPresenter<Hot> implements IHot.Presenter{

    private IHot.View view;

    private IHot.Model model;

    public HotPresenter(IHot.View view){
        this.view=view;
        this.model = new HotModel();
    }

    @Override
    protected void deleteView(){
        view=null;
        model = null;
    }
}
