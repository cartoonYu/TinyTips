package com.cartoon.tinytips.Personal;

import com.cartoon.tinytips.BaseFragmentPresenter;

class PersonalPresenter extends BaseFragmentPresenter<Personal> implements IPersonal.Presenter {
    private IPersonal.View view;
    public PersonalPresenter(IPersonal.View view){
        this.view=view;
    }
    @Override
    protected void deleteView(){
        view=null;
    }
}
