package com.cartoon.tinytips.Personal.Homepage;

import com.cartoon.tinytips.BaseActivityPresenter;

class HomepagePresenter extends BaseActivityPresenter<Homepage> implements IHomepage.Presenter{
    private IHomepage.View view;
    private IHomepage.Model model;

    public HomepagePresenter(IHomepage.View view){
        this.view=view;
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
