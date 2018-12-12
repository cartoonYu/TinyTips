package com.cartoon.tinytips.Personal.Setting.Security;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

    @BindView(R.id.personal_setting_security_phoneNum)
    EditText phoneNum;      //电话号码

    @BindView(R.id.personal_setting_security_authCode)
    EditText authCode;     //验证码

    @BindView(R.id.personal_setting_security_sendAuthCode)
    TextView sendAuthCode;    //发送验证码按钮

    @BindView(R.id.personal_setting_security_newPassword)
    EditText newPassword;

    @BindView(R.id.personal_setting_security_confirmPassword)
    EditText confirmPassword;

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
        revampStatusBar();
    }


    @Override
    protected void onPrepare(){

    }

    @OnClick(R.id.personal_setting_security_sendAuthCode)
    public void sendAuthCode(){
        //发送验证码按钮点击事件
    }

    @OnClick(R.id.personal_setting_security_finish)
    public void revampPassword(){
        //完成按钮点击事件
        presenter.handleChangePassword();
    }

    private void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }

    @Override
    public void onBackPressed(){
        intentToSetting();
    }

    @OnClick(R.id.toolbarBack)
    public void onClickBack(){
        intentToSetting();
    }

    @Override
    public void intentToSetting() {
        IntentActivity.intentWithoutData(this,Setting.class);
        IntentActivity.finishActivity(this);
    }

    @Override
    public String getPhoneNumber() {
        return phoneNum.getText().toString();
    }

    @Override
    public String getAuthCode(){
        return authCode.getText().toString();
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
