package com.cartoon.tinytips.Personal.Setting.Security;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.ShowToast;

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
        model.changePassword(view.getPhoneNumber(), view.getAuthCode(), view.getPassword(), view.getConfirmPassword(), new ValueCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                ShowToast.shortToast(s);
                view.intentToSetting();
            }

            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
            }
        });
    }
}
