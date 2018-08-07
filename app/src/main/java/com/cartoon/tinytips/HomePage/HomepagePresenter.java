package com.cartoon.tinytips.HomePage;

import com.cartoon.tinytips.BaseFragmentPresenter;

class HomepagePresenter extends BaseFragmentPresenter<Homepage> implements IHomepage.Presenter{
    private IHomepage.View view;
    public HomepagePresenter(IHomepage.View view){
        this.view=view;
    }
    @Override
    protected void deleteView(){
        view=null;
    }
}
