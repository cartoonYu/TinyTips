package com.cartoon.tinytips.Login;

import com.cartoon.tinytips.BaseActivityPresenter;

class LoginPresenter extends BaseActivityPresenter<Login> implements ILogin.Presenter{

    private ILogin.View view;

    private ILogin.Model model;

    public LoginPresenter(ILogin.View view){
        this.view=view;
        this.model = new LoginModel();
    }

    @Override
    protected void deleteView(){
        view=null;
        model = null;
    }

}
