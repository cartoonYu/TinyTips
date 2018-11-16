package com.cartoon.tinytips.Login;

import android.content.SharedPreferences;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.util.ShowToast;

class LoginPresenter extends BaseActivityPresenter<Login> implements ILogin.Presenter{

    private ILogin.View view;

    private ILogin.Model model;

    public LoginPresenter(ILogin.View view){
        this.view=view;
        this.model = new LoginModel();
    }

    @Override
    public void checkInformation(String account, String password) {
        if(account.equals("")&&password.equals("")){
            ShowToast.shortToast("请输入账号以及密码");
            return;
        }
        if(account.equals("")){
            ShowToast.shortToast("请输入账号");
            return;
        }
        if (password.equals("")){
            ShowToast.shortToast("请输入密码");
            return;
        }
        Information information=new Information();
        information.setAccount(account);
        information.setPassword(password);
        model.checkInformation(information, new ValueCallBack<String>() {
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
    protected void deleteView(){
        view=null;
        model = null;
    }

}
