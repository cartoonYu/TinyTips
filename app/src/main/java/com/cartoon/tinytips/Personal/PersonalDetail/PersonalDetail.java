package com.cartoon.tinytips.Personal.PersonalDetail;

import android.content.Intent;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.R;

public class PersonalDetail extends BaseActivity<PersonalDetailPresenter> implements IPersonalDetail.View{

    private PersonalDetailPresenter presenter;

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
    protected void initView(){

    }
    @Override
    protected void onPrepare(){
        Intent intent=new Intent(this, Main.class);
        intent.putExtra("main",4);
        startActivity(intent);
        finish();
    }
}
