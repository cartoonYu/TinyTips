package com.cartoon.tinytips.Personal;

import com.cartoon.tinytips.BaseFragmentPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.PersonalInformation;
import com.cartoon.tinytips.util.ShowToast;

class PersonalPresenter extends BaseFragmentPresenter<Personal> implements IPersonal.Presenter {

    private IPersonal.View view;

    private IPersonal.Model model;

    public PersonalPresenter(IPersonal.View view){
        this.view=view;
        this.model=new PersonalModel();
    }

    @Override
    public void initData(){
        model.getPersonalInformation(new ValueCallBack<PersonalInformation>() {
            @Override
            public void onSuccess(PersonalInformation personalInformation) {
                view.setHeadPro(personalInformation.getHeadPortrait());
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
