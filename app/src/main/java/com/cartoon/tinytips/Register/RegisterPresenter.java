package com.cartoon.tinytips.Register;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.util.ShowToast;

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
                Information information=new Information();
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
        })  ;

    }

    @Override
    protected void deleteView(){
        view=null;
        model=null;
    }

}
