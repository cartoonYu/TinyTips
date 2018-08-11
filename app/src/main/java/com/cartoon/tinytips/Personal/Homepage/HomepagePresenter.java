package com.cartoon.tinytips.Personal.Homepage;

import com.cartoon.tinytips.BaseActivityPresenter;

class HomepagePresenter extends BaseActivityPresenter<Homepage> implements IHomepage.Presenter{
    private IHomepage.View view;
    public HomepagePresenter(IHomepage.View view){
        this.view=view;
    }
    @Override
    public void deleteView(){
        view=null;
    }
}
