package com.cartoon.tinytips.Register;

import android.view.View;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.UI.RevampStatusBar;

import butterknife.BindView;

public class Register extends BaseActivity<RegisterPresenter> implements IRegister.View{

    @BindView(R.id.statusBar)
    View statusBar;

    @Override
    protected RegisterPresenter initPresent(){
        return new RegisterPresenter(this);
    }

    @Override
    protected int getLayout(){
        return R.layout.register;
    }

    @Override
    protected void initView(){

    }

    @Override
    protected void onPrepare(){

    }

    private void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }

}
