package com.cartoon.tinytips.Login;

import com.cartoon.tinytips.BaseActivityPresenter;

class LoginPresenter extends BaseActivityPresenter<Login> implements ILogin.Presenter{

    private ILogin.View view;

    public LoginPresenter(ILogin.View view){
        this.view=view;
    }

    @Override
    protected void deleteView(){
        view=null;
    }

}
