package com.cartoon.tinytips.Discover;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.R;

public class Discover extends BaseFragment<DiscoverPresenter> implements IDiscover.View{

    private DiscoverPresenter presenter;

    @Override
    protected DiscoverPresenter initPresent(){
        presenter=new DiscoverPresenter(this);
        return presenter;
    }

    @Override
    protected int getLayout(){
        return R.layout.discover;
    }

    @Override
    protected void initView(){

    }

    @Override
    protected void onPrepare(){

    }
}
