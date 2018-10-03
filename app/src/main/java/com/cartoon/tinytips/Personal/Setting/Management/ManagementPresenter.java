package com.cartoon.tinytips.Personal.Setting.Management;

import com.cartoon.tinytips.BaseActivityPresenter;

class ManagementPresenter extends BaseActivityPresenter<Management> implements IManagement.Presenter{

    private IManagement.View view;
    private IManagement.Model model;

    public ManagementPresenter(IManagement.View view){
        this.view = view;
        this.model = new ManagementModel();
    }

    @Override
    protected void deleteView(){
        view = null;
        model = null;
    }

}
