package com.cartoon.tinytips.Discover;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.BaseFragmentPresenter;

public class DiscoverPresenter extends BaseFragmentPresenter<Discover> implements IDiscover.Presenter {

    private IDiscover.View view;
    public DiscoverPresenter(IDiscover.View view){
        this.view=view;

    }
    @Override
    protected void deleteView(){
        view=null;
    }
}
