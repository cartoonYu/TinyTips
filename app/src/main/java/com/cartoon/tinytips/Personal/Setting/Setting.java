package com.cartoon.tinytips.Personal.Setting;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.Personal.Setting.Management.Management;
import com.cartoon.tinytips.Personal.Setting.Security.Security;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.UI.FragmentConstant;
import com.cartoon.tinytips.util.UI.RevampStatusBar;

import butterknife.BindView;
import butterknife.OnClick;

public class Setting extends BaseActivity<SettingPresenter> implements ISetting.View{

    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.toolbarBack)
    TextView back;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @Override
    protected SettingPresenter initPresent(){
        return new SettingPresenter(this);
    }

    @Override
    protected int getLayout(){
        return R.layout.personal_setting;
    }

    @Override
    protected void initView(){
        revampStatusBar();
        initToolbar();
    }

    @Override
    protected void onPrepare(){

    }

    @Override
    public void onBackPressed(){
        intentToMain();
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
    public void initToolbar(){
        back.setBackground(getResources().getDrawable(R.mipmap.personal_detail_back));
        toolbarText.setText("设置");
    }

    @Override
    public void intentToMain(){
        Intent intent=new Intent(this, Main.class);
        intent.putExtra("main", FragmentConstant.personal);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.toolbarBack)
    public void onClickBack(){
        intentToMain();
    }

    @OnClick(R.id.personal_setting_management)
    public void onClickManagement(){
        Intent intent=new Intent(this, Management.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.personal_setting_security)
    public void onClickSecurity(){
        Intent intent=new Intent(this, Security.class);
        startActivity(intent);
        finish();
    }

}
