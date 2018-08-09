package com.cartoon.tinytips.Personal.Detail;

import com.cartoon.tinytips.BaseActivityPresenter;

class PersonalDetailPresenter extends BaseActivityPresenter<PersonalDetail> implements IPersonalDetail.Presenter{

    private IPersonalDetail.View view;

    public PersonalDetailPresenter(IPersonalDetail.View view){
        this.view=view;
    }
    @Override
    protected void deleteView(){
        view=null;
    }
}
