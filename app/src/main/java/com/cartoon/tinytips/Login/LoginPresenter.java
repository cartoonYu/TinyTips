package com.cartoon.tinytips.Login;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.util.ShowToast;

class LoginPresenter extends BaseActivityPresenter<Login> implements ILogin.Presenter{

    private ILogin.View view;

    private ILogin.Model model;

    private Information info;

    public LoginPresenter(ILogin.View view){
        this.view=view;
        this.model = new LoginModel();
    }

    public Information getInformation(){   //从数据库获取个人信息

        /*model.getPersonalInformation(new ValueCallBack<Information>() {
            @Override
            public void onSuccess(Information information) {
                info = information;
                ShowToast.shortToast("成功啦");
            }

            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
            }
        });*/

        return info;

    }


    @Override
    protected void deleteView(){
        view=null;
        model = null;
    }

}
