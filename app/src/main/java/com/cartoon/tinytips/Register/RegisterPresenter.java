package com.cartoon.tinytips.Register;

import android.print.PrinterId;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.PersonalInformation;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.ShowToast;
import com.cartoon.tinytips.util.TinyTipsApplication;

class RegisterPresenter extends BaseActivityPresenter<Register> implements IRegister.Presenter{

    private IRegister.View view;

    private IRegister.Model model;

    public RegisterPresenter(IRegister.View view){
        this.view=view;
        this.model=new RegisterModel();
    }

    @Override
    public void handleRegister(){
        model.setAuthCode(view.getAuthCode());
        model.verifyAuthCode(new ValueCallBack<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                PersonalInformation information=new PersonalInformation();
                information.setAccount(view.getAccount());
                information.setPassword(view.getPassword());
                model.setInformation(information);
                model.verifyInformation(new ValueCallBack<String>() {
                    @Override
                    public void onSuccess(String s) {
                        ShowToast.shortToast(s);
                        view.intentToMain();
                    }

                    @Override
                    public void onFail(String msg) {
                        ShowToast.shortToast(msg);
                    }

                });
            }

            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
            }
        });

    }

    @Override
    protected void deleteView(){
        view=null;
        model=null;
    }

}
