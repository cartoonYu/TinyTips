package com.cartoon.tinytips.Main;

import com.cartoon.tinytips.BaseActivityPresenter;

public class MainPresenter extends BaseActivityPresenter<Main> implements IMain.Presenter{
    protected IMain.View view;

    protected IMain.Model model;

    public MainPresenter(IMain.View view){
        this.view=view;
        this.model = new MainModel();
    }
    @Override
    protected void deleteView(){
        view=null;
        model = null;
    }
}
