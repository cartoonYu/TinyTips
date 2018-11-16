package com.cartoon.tinytips.Personal.Setting;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.Personal.Setting.Management.Management;
import com.cartoon.tinytips.Personal.Setting.Security.Security;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.util.FragmentConstant;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.UI.RevampStatusBar;
import com.cartoon.tinytips.util.UI.RevampToolbar;

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

    private void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }


    private void initToolbar(){
        RevampToolbar.setBack(back);
        RevampToolbar.setText(toolbarText,new String("设置"));
    }


    @OnClick(R.id.personal_setting_management)
    public void onClickManagement(){
        IntentActivity.intentWithoutData(this,Management.class);
        IntentActivity.finishActivity(this);
    }

    @OnClick(R.id.personal_setting_security)
    public void onClickSecurity(){
        IntentActivity.intentWithoutData(this,Security.class);
        IntentActivity.finishActivity(this);
    }

    @OnClick(R.id.toolbarBack)
    public void onClickBack(){
        IntentActivity.intentWithData(this,Main.class,"main",FragmentConstant.getConstant().getPersonal());
        IntentActivity.finishActivity(this);
    }

    @Override
    public void onBackPressed(){
        IntentActivity.intentWithData(this,Main.class,"main",FragmentConstant.getConstant().getPersonal());
        IntentActivity.finishActivity(this);
    }
}
