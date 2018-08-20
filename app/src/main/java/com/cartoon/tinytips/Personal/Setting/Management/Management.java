package com.cartoon.tinytips.Personal.Setting.Management;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Personal.Setting.Setting;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.Adapters.Personal.Setting.Management.Account;
import com.cartoon.tinytips.util.Adapters.Personal.Setting.Management.AccountAdapter;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.UI.RevampStatusBar;
import com.cartoon.tinytips.util.UI.RevampToolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class Management extends BaseActivity<ManagementPresenter> implements IManagement.View {

    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.toolbarBack)
    TextView back;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    private List<Account> accountList;
    private AccountAdapter adapter;

    @BindView(R.id.personal_setting_management_account)
    RecyclerView account;

    @Override
    protected ManagementPresenter initPresent(){
        return new ManagementPresenter(this);
    }

    @Override
    protected int getLayout(){
        return R.layout.personal_setting_management;
    }

    @Override
    protected void initView(){
        revampStatusBar();
        initToolbar();
        initAccount();
    }

    @Override
    protected void onPrepare(){

    }

    private void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }

    private void initToolbar(){
        RevampToolbar.setBack(back);
        RevampToolbar.setText(toolbarText,new String("账号管理"));
    }

    @Override
    public void initAccount(){
        accountList=new ArrayList<>();
        Account a=new Account(getResources().getDrawable(R.drawable.nav_icon),"cartoon");
        for(int i=0;i<2;i++){
            accountList.add(a);
        }
        LinearLayoutManager manager=new LinearLayoutManager(this);
        account.setLayoutManager(manager);
        adapter=new AccountAdapter(accountList);
        account.setAdapter(adapter);
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

}
