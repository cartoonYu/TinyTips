package com.cartoon.tinytips.Personal.Setting.Security;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Personal.Setting.Setting;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.UI.RevampStatusBar;

import butterknife.BindView;
import butterknife.OnClick;

public class Security extends BaseActivity<SecurityPresenter> implements ISecurity.View{

    @BindView(R.id.statusBar)
    View statusBar;

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

    @Override
    public void revampStatusBar(){
        RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)statusBar.getLayoutParams();
        params.width=RelativeLayout.LayoutParams.MATCH_PARENT;
        params.height= RevampStatusBar.getStatusBar(this);
        statusBar.setLayoutParams(params);
        statusBar.setBackgroundColor(getResources().getColor(R.color.white));
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
    public void intentToSetting(){
        Intent intent=new Intent(this, Setting.class);
        startActivity(intent);
        finish();
    }
}
