package com.cartoon.tinytips.HomePage;

import com.cartoon.tinytips.BaseFragmentPresenter;

class HomepagePresenter extends BaseFragmentPresenter<Homepage> implements IHomepage.Presenter{
    private IHomepage.View view;

    private IHomepage.Model model;

    public HomepagePresenter(IHomepage.View view){
        this.view=view;
        this.model = new HomepageModel();
    }
    @Override
    protected void deleteView(){
        view=null;
        model = null;
    }
}
