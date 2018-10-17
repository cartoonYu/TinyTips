package com.cartoon.tinytips.Start;

import com.cartoon.tinytips.BaseActivityPresenter;

public class StartActivityPresenter extends BaseActivityPresenter<StartActivity> implements IStartActivity.Presenter {
    private IStartActivity.View view;


    @Override
    protected void deleteView() {

    }

    public StartActivityPresenter(IStartActivity.View view){
        this.view=view;
    }
}
