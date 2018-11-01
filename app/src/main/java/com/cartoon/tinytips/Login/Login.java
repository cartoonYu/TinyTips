package com.cartoon.tinytips.Login;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.HomePage.Favorite.Favorite;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.util.UI.RevampStatusBar;

import butterknife.BindView;

public class Login extends BaseActivity<LoginPresenter> implements ILogin.View{



    @BindView(R.id.statusBar)
    View statusBar;

    @Override
    protected LoginPresenter initPresent(){
        return new LoginPresenter(this);
    }

    @Override
    protected int getLayout(){
        return R.layout.login;
    }

    @Override
    protected void initView(){
        revampStatusBar();
    }

    @Override
    protected void onPrepare(){


    }

    private void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }
}
