package com.cartoon.tinytips.Login;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.HomePage.Favorite.Favorite;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.Register.Register;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.UI.RevampStatusBar;

import butterknife.BindView;
import butterknife.OnClick;

public class Login extends BaseActivity<LoginPresenter> implements ILogin.View{

    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.login_account)
    EditText inputAccount;

    @BindView(R.id.login_password)
    EditText inputPassword;

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

    @OnClick(R.id.login_register)
    public void register(){
        IntentActivity.intentWithoutData(this,Register.class);
        IntentActivity.finishActivity(this);
    }

    @OnClick(R.id.login_login)
    public void login(){
        //登录按钮点击事件
        String account=inputAccount.getText().toString();
        String password=inputPassword.getText().toString();
        presenter.checkInformation(account,password);
    }

    @Override
    public void intentToMain() {
        IntentActivity.intentWithoutData(this,Main.class);
        IntentActivity.finishActivity(this);
    }

    private void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }
}
