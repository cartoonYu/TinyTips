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
import com.cartoon.tinytips.util.UI.RevampStatusBar;

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
        toolbarText.setText("账号管理");
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
