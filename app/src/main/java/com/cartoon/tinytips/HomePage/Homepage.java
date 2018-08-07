package com.cartoon.tinytips.HomePage;

import android.support.v4.app.Fragment;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.R;

public class Homepage extends BaseFragment<HomepagePresenter> implements IHomepage.View{
    private HomepagePresenter presenter;

    @Override
    protected HomepagePresenter initPresent(){
        presenter=new HomepagePresenter(this);
        return presenter;
    }

    @Override
    protected int getLayout(){
        return R.layout.homepage;
    }

    @Override
    protected void initView(){

    }

    @Override
    protected void onPrepare(){

    }
}
