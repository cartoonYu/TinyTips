package com.cartoon.tinytips.Personal;

import com.cartoon.tinytips.BaseFragmentPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.util.ShowToast;

import java.util.IllegalFormatCodePointException;

class PersonalPresenter extends BaseFragmentPresenter<Personal> implements IPersonal.Presenter {

    private IPersonal.View view;

    private IPersonal.Model model;

    Information information;

    public PersonalPresenter(IPersonal.View view){
        this.view=view;
        this.model=new PersonalModel();
    }

    @Override
    public void initData(){
        saveInformation();
        model.setInformation(information);
        model.getPersonalInformation(new ValueCallBack<Information>() {
            @Override
            public void onSuccess(Information personalInformation) {
                view.setHeadPro(personalInformation.getHeadPortrait());
                view.setNotes(personalInformation.getNumOfNote());
                view.setFans(personalInformation.getNumOfFans());
                view.setAttentions(personalInformation.getNumOfAttentions());
                view.setNickName(personalInformation.getNickName());
            }

            @Override
            public void onFail(String msg) {


            }
        });
    }

    public void saveInformation(){
       information = view.getInformation();
    }

    @Override
    protected void deleteView(){
        view=null;
        model=null;
    }
}
