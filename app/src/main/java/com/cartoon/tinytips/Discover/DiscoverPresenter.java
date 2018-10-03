package com.cartoon.tinytips.Discover;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.BaseFragmentPresenter;

public class DiscoverPresenter extends BaseFragmentPresenter<Discover> implements IDiscover.Presenter {

    private IDiscover.View view;

    private IDiscover.Model model;

    public DiscoverPresenter(IDiscover.View view){
        this.view=view;
        this.model = new DiscoverModel();
    }
    @Override
    protected void deleteView(){
        view=null;
        model = null;
    }
}
