package com.cartoon.tinytips.Register;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.UI.RevampStatusBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 尚未完成的功能
 * 1.发送验证码的点击事件
 * 2.注册时的验证码的校对
 */

public class Register extends BaseActivity<RegisterPresenter> implements IRegister.View{

    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.register_account)
    EditText account;    //账号

    @BindView(R.id.register_authCode)
    EditText authCode;    //验证码

    @BindView(R.id.register_password)
    EditText password;    //密码

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
        revampStatusBar();
    }

    @Override
    protected void onPrepare(){

    }

    private void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }

    @OnClick(R.id.register_register)
    public void onClickRegister(){
        //点击注册按钮
        presenter.handleRegister();
    }

    @OnClick(R.id.register_sendAuthCode)
    public void onClickSendAuthCode(){
        //点击发送验证码按钮
    }

    @Override
    public String getAccount(){
        return account.getText().toString();
    }

    @Override
    public String getAuthCode(){
        return authCode.getText().toString();
    }

    @Override
    public String getPassword(){
        return password.getText().toString();
    }

    @Override
    public void intentToMain(){
        IntentActivity.intentWithoutData(this,Main.class);
        IntentActivity.finishActivity(this);
    }
}
