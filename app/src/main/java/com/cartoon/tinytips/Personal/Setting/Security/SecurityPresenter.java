package com.cartoon.tinytips.Personal.Setting.Security;

import com.cartoon.tinytips.BaseActivityPresenter;

class SecurityPresenter extends BaseActivityPresenter<Security> implements ISecurity.Presenter{

    private ISecurity.View view;

    public SecurityPresenter(ISecurity.View view){
        this.view=view;
    }

    @Override
    public void deleteView(){
        view=null;
    }

}
