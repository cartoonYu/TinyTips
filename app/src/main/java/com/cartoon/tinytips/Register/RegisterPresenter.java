package com.cartoon.tinytips.Register;

import android.print.PrinterId;

import com.cartoon.tinytips.BaseActivityPresenter;

class RegisterPresenter extends BaseActivityPresenter<Register> implements IRegister.Presenter{

    private IRegister.View view;

    public RegisterPresenter(IRegister.View view){
        this.view=view;
    }

    @Override
    protected void deleteView(){
        view=null;
    }

}
