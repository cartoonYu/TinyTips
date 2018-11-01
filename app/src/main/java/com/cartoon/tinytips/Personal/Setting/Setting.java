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
import com.cartoon.tinytips.util.UI.RevampStatusBar;
import com.cartoon.tinytips.util.UI.RevampToolbar;

import butterknife.BindView;
import butterknife.OnClick;

public class Setting extends BaseActivity<SettingPresenter> implements ISetting.View{

    Information information;

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
        if ((Information)getIntent().getSerializableExtra("personal")!=null){
            information = (Information)getIntent().getSerializableExtra("personal");
            Log.d("text", "Detail: "+information.getAccount());
        }
    }

    @Override
    public void onBackPressed(){
        IntentActivity.intentWithData(this,Main.class,new String("main"),FragmentConstant.getConstant().getPersonal(),new String("personalSetting"),information);
        IntentActivity.finishActivity(this);
    }


    private void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }


    private void initToolbar(){
        RevampToolbar.setBack(back);
        RevampToolbar.setText(toolbarText,new String("设置"));
    }


    @OnClick(R.id.toolbarBack)
    public void onClickBack(){
        IntentActivity.intentWithData(this,Main.class,new String("main"),FragmentConstant.getConstant().getPersonal(),new String("personalSetting"),information);
        IntentActivity.finishActivity(this);
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

}
