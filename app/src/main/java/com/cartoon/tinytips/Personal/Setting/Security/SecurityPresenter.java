package com.cartoon.tinytips.Personal.Setting.Security;

import com.cartoon.tinytips.BaseActivityPresenter;

class SecurityPresenter extends BaseActivityPresenter<Security> implements ISecurity.Presenter{

    private ISecurity.View view;

    private ISecurity.Model model;

    public SecurityPresenter(ISecurity.View view){
        this.view = view;
        this.model = new SecurityModel();
    }

    @Override
    public void deleteView(){
        view = null;
        model = null;
    }

    @Override
    public void handleChangePassword() {

    }
}
