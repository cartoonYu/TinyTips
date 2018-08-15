package com.cartoon.tinytips.Personal.Setting.Management;

import com.cartoon.tinytips.BaseActivityPresenter;

class ManagementPresenter extends BaseActivityPresenter<Management> implements IManagement.Presenter{

    private IManagement.View view;

    public ManagementPresenter(IManagement.View view){
        this.view=view;
    }

    @Override
    protected void deleteView(){
        view=null;
    }

}
