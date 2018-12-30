package com.cartoon.tinytips.Register;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.util.ShowToast;
import com.cartoon.tinytips.util.file.ImageOperation;

import java.util.ArrayList;

class RegisterPresenter extends BaseActivityPresenter<Register> implements IRegister.Presenter{

    private IRegister.View view;

    private IRegister.Model model;

    public RegisterPresenter(IRegister.View view){
        this.view=view;
        this.model=new RegisterModel();
    }

    @Override
    public void handleRegister() {
        Information information=new Information();
        information.setAccount(view.getAccount());
        information.setPassword(view.getPassword());
        information.setNickName(view.nickName());
        information.setHeadPortrait(ImageOperation.getImageOperation().defaultImage(R.drawable.apple,"defaultHeadpro.jpg"));
        information.setInterest(new ArrayList<String>());
        information.setSchool("xx大学");
        information.setMajor("xx专业");
        information.setBackground("本科");
        information.setResume("");
        model.register(information, new ValueCallBack<String>() {
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
        model=null;
    }

}
