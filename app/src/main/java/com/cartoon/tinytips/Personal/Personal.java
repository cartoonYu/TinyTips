package com.cartoon.tinytips.Personal;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.R;

public class Personal extends BaseFragment<PersonalPresenter> implements IPersonal.View {
    private PersonalPresenter presenter;

    @Override
    protected PersonalPresenter initPresent(){
        presenter=new PersonalPresenter(this);
        return presenter;
    }

    @Override
    protected int getLayout(){
        return R.layout.personal;
    }

    @Override
    protected void initView(){

    }

    @Override
    protected void onPrepare(){

    }
}
