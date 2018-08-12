package com.cartoon.tinytips.Personal.Detail;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.UI.RevampStatusBar;

import butterknife.BindView;

public class PersonalDetail extends BaseActivity<PersonalDetailPresenter> implements IPersonalDetail.View{

    private PersonalDetailPresenter presenter;

    @BindView(R.id.statusBar)
    View statusBar;

    @Override
    protected PersonalDetailPresenter initPresent(){
        presenter=new PersonalDetailPresenter(this);
        return presenter;
    }
    @Override
    protected int getLayout(){
        return R.layout.personal_detail;
    }
    @Override
    protected void initView(){ revampStatusBar();
    }
    @Override
    protected void onPrepare(){

    }
    @Override
    public void intentMain(){
        Intent intent=new Intent(this, Main.class);
        intent.putExtra("main",4);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed(){
        intentMain();
    }

    @Override
    public void revampStatusBar(){
        RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)statusBar.getLayoutParams();
        params.width=RelativeLayout.LayoutParams.MATCH_PARENT;
        params.height= RevampStatusBar.getStatusBar(this);
        statusBar.setLayoutParams(params);
        statusBar.setBackgroundColor(getResources().getColor(R.color.white));
    }
}
