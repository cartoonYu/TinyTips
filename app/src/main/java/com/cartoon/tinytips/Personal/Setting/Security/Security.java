package com.cartoon.tinytips.Personal.Setting.Security;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Personal.Setting.Setting;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.UI.RevampStatusBar;

import butterknife.BindView;
import butterknife.OnClick;

public class Security extends BaseActivity<SecurityPresenter> implements ISecurity.View{

    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.personal_setting_security_sendAuthCode)
    EditText authcode;

    @BindView(R.id.personal_setting_security_newPassword)
    EditText newPassword;

    @BindView(R.id.personal_setting_security_confirmPassword)
    EditText confirmPassword;

    @BindView(R.id.personal_setting_security_phoneNum)
    EditText phoneNum;
    @Override
    protected SecurityPresenter initPresent(){
        return new SecurityPresenter(this);
    }

    @Override
    protected int getLayout(){
        return R.layout.personal_setting_security;
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

    @Override
    public void onBackPressed(){
        IntentActivity.intentWithoutData(this,Setting.class);
        IntentActivity.finishActivity(this);
    }

    @OnClick(R.id.toolbarBack)
    public void onClickBack(){
        IntentActivity.intentWithoutData(this,Setting.class);
        IntentActivity.finishActivity(this);
    }

    @Override
    public String getPhoneNumber() {
        return phoneNum.getText().toString();
    }

    @Override
    public String getAuthCode(){
        return authcode.getText().toString();
    }

    @Override
    public String getPassword() {
        return newPassword.getText().toString();
    }

    @Override
    public String getConfirmPassword() {
        return confirmPassword.getText().toString();
    }
}
