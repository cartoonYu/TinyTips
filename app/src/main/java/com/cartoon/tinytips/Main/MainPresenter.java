package com.cartoon.tinytips.Main;

import com.cartoon.tinytips.BaseActivityPresenter;

public class MainPresenter extends BaseActivityPresenter<Main> implements IMain.Presenter{
    protected IMain.View view;
    public MainPresenter(IMain.View view){
        this.view=view;
    }
    @Override
    protected void deleteView(){
        view=null;
    }
}
