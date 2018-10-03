package com.cartoon.tinytips.Personal.PersonalHomepage;

import com.cartoon.tinytips.BaseActivityPresenter;

class PersonalHomepagePresenter extends BaseActivityPresenter<PersonalHomepage> implements IPersonalHomepage.Presenter{
    private IPersonalHomepage.View view;
    private IPersonalHomepage.Model model;

    public PersonalHomepagePresenter(IPersonalHomepage.View view){
        this.view=view;
        this.model = new PersonalHomepageModel();
    }
    @Override
    public void deleteView(){
        view=null;
        model = null;
    }

    @Override
    public void initData(){

    }

}
