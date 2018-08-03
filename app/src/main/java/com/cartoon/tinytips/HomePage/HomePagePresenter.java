package com.cartoon.tinytips.Homepage;

import com.cartoon.tinytips.BaseFragmentPresenter;

class HomepagePresenter extends BaseFragmentPresenter implements IHomepage.Presenter{
    private IHomepage.View view;
    public HomepagePresenter(IHomepage.View view){
        this.view=view;
    }
    @Override
    protected void deleteView(){
        view=null;
    }
}
