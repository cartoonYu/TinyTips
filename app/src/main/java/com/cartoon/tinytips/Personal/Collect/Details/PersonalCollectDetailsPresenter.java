package com.cartoon.tinytips.Personal.Collect.Details;

import com.cartoon.tinytips.BaseActivityPresenter;

/**
 * Created by cartoon on 2018/3/6.
 */

class PersonalCollectDetailsPresenter extends BaseActivityPresenter<PersonalCollectDetails> implements IPersonalCollectDetails.Presenter{

    private IPersonalCollectDetails.View view;
    private IPersonalCollectDetails.Model model;

    public PersonalCollectDetailsPresenter(IPersonalCollectDetails.View view){
        this.view=view;
        this.model=new PersonalCollectDetailsModel();
    }
}
